package com.umpisa.exam.server.services;

import com.umpisa.exam.server.models.Item;
import com.umpisa.exam.server.utils.ListOpts;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class ItemService extends Service {
  public Item create(Item item) {
    // TODO: Add validation
    return transaction(() -> {
      item.save();

      return item;
    });
  }

  public Optional<Item> get(String id) { return Item.get(id); }

  public List<Item> list(ListOpts opts) { return Item.list(opts); }

  public List<Item> listByFlag(ListOpts opts, Boolean isRedeemed) { return Item.listByFlag(opts, isRedeemed); }

  public Item update(Item item) {
    // TODO: Add validation
    return transaction(() -> (Item) item.save());
  }
}
