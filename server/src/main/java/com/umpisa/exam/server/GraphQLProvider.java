package com.umpisa.exam.server;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import com.umpisa.exam.server.fetchers.ItemDataFetcher;
import com.umpisa.exam.server.models.Item;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.TypeResolver;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.TypeDefinitionRegistry;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.SchemaGenerator;

import javax.annotation.PostConstruct;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import static graphql.schema.idl.TypeRuntimeWiring.newTypeWiring;

import com.umpisa.exam.server.fetchers.UserDataFetcher;

@Component
public class GraphQLProvider {
  private GraphQL graphQL;

  @Bean
  public GraphQL graphQL() {
    return graphQL;
  }

  @PostConstruct
  public void init() throws IOException {
    this.graphQL = GraphQL.newGraphQL(
      buildSchema(
        Resources.toString(Resources.getResource("schema.graphqls"), Charsets.UTF_8)
      )
    ).build();
  }

  @Autowired
  UserDataFetcher userDataFetcher;

  @Autowired
  ItemDataFetcher itemDataFetcher;

  private GraphQLSchema buildSchema(String sdl) {
    TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(sdl);
    RuntimeWiring runtimeWiring = buildWiring();
    SchemaGenerator schemaGenerator = new SchemaGenerator();
    return schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring);
  }

  private RuntimeWiring buildWiring() {
    TypeResolver modelTypeResolver = env -> {
      Object javaObject = env.getObject();
      if(javaObject instanceof Item)
        return env.getSchema().getObjectType("Item");
      else
        return env.getSchema().getObjectType("User");
    };

    return RuntimeWiring.newRuntimeWiring()
      .type(
        newTypeWiring("Mutation")
          .dataFetcher("login", userDataFetcher.login())

          // Methods for items
          .dataFetcher("addItem", itemDataFetcher.addItem())
          .dataFetcher("updateItem", itemDataFetcher.updateItem())
          .dataFetcher("redeemItem", itemDataFetcher.redeemItem())
      )
      .type(
        newTypeWiring("Query")
          .dataFetcher("item", itemDataFetcher.getItem())
          .dataFetcher("items", itemDataFetcher.listItems())
          .dataFetcher("allItems", itemDataFetcher.listAllItems())
      )
      .type("Model", typeWriting -> typeWriting.typeResolver(modelTypeResolver))
      .build();
  }
}
