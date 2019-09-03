package com.umpisa.exam.server.fetchers;

import com.umpisa.exam.server.InvalidCredentialsException;
import com.umpisa.exam.server.services.UserService;
import graphql.schema.DataFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class UserDataFetcher extends BaseDataFetcher {
  @Autowired
  UserService userService;

  // TODO: Improve authentication and authorization
  public DataFetcher login() {
    return dataFetchingEnvironment -> {
      Map<String, String> args = dataFetchingEnvironment.getArgument("input");

      try {
        return userService.authenticate(args.get("username"), args.get("password"));
      }
      catch (InvalidCredentialsException ex) {
        dataFetchingEnvironment.getExecutionContext().addError(ex);
        return null;
      }
    };
  }
}
