package com.umpisa.exam.server.fetchers;

import com.umpisa.exam.server.CategoryNotFoundException;
import com.umpisa.exam.server.ItemNotFoundException;
import com.umpisa.exam.server.models.Category;
import com.umpisa.exam.server.models.Item;
import com.umpisa.exam.server.models.User;
import com.umpisa.exam.server.services.ItemService;
import com.umpisa.exam.server.services.UserService;
import com.umpisa.exam.server.utils.ListOpts;
import graphql.schema.DataFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ItemDataFetcher extends BaseDataFetcher {
  @Autowired
  ItemService itemService;

  @Autowired
  UserService userService;

  public DataFetcher addItem() {
    return dataFetchingEnvironment -> {
      Map<String, String> args = dataFetchingEnvironment.getArgument("input");

      try {
        Item item = new Item()
          .withCategory(Category.fromString(args.get("category")))
          .withBrand(args.get("brand"))
          .withType(args.get("type"))
          .withDescription(args.get("description"));

        return itemService.create(item);
      }
      catch(CategoryNotFoundException ex) {
        dataFetchingEnvironment.getExecutionContext().addError(ex);
        return null;
      }
    };
  }

  public DataFetcher updateItem() {
    return dataFetchingEnvironment -> {
      Map<String, Object> args = dataFetchingEnvironment.getArgument("input");

      Item item = itemService.get(args.get("id").toString()).orElseGet(() -> {
        dataFetchingEnvironment.getExecutionContext().addError(new ItemNotFoundException());
        return null;
      });

      if(dataFetchingEnvironment.getExecutionContext().getErrors().isEmpty()) {
        return itemService.get(args.get("id").toString())
          .map(i ->
            itemService.update(
              i
                .withCategory(Category.fromString(args.get("category").toString()))
                .withBrand(args.get("brand").toString())
                .withType(args.get("type").toString())
                .withDescription(args.get("description").toString())
            )
          ).orElseGet(() -> {
            dataFetchingEnvironment.getExecutionContext().addError(new ItemNotFoundException());
            return null;
          });
      }
      else
        return null;
    };
  }

  public DataFetcher getItem() {
    return dataFetchingEnvironment -> {
      String id = dataFetchingEnvironment.getArgument("id");
      return itemService.get(id).orElse(null);
    };
  }

  public DataFetcher listItems() {
    return dataFetchingEnvironment -> itemService.listByFlag(
      ListOpts.DEFAULT,
      dataFetchingEnvironment.getArgument("isRedeemed")
    );
  }

  public DataFetcher listAllItems() {
    return dataFetchingEnvironment -> itemService.list(
      ListOpts.DEFAULT
    );
  }

  public DataFetcher redeemItem() {
    return dataFetchingEnvironment -> {
      Map<String, Object> args = dataFetchingEnvironment.getArgument("input");

      Item item = itemService.get(args.get("itemId").toString()).orElseGet(() -> {
        dataFetchingEnvironment.getExecutionContext().addError(new ItemNotFoundException());
        return null;
      });

      if(dataFetchingEnvironment.getExecutionContext().getErrors().isEmpty()) {
        User user = userService.create(
          new User()
            .withFirstName(args.get("firstName").toString())
            .withLastName(args.get("lastName").toString())
            .withEmail(args.get("email").toString())
            .withMobileNumber(args.get("mobileNumber").toString())
            .withTypeOfId(args.get("typeOfId").toString())
            .withIdNumber(args.get("idNumber").toString())
        );

        return itemService.get(args.get("itemId").toString())
          .map(i ->
            itemService.update(
              i
                .withOwner(user)
                .withRedeemed(true)
            )
          ).orElseGet(() -> {
            dataFetchingEnvironment.getExecutionContext().addError(new ItemNotFoundException());
            return null;
          });
      }
      else
        return null;
    };
  }
}
