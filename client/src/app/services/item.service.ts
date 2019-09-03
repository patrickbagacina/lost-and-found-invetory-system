import { Item, ItemModel } from './../models/item';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ItemService {
  create(item: ItemModel) {
    return Item.create(item);
  }

  get(id: string) {
    return Item.get(id);
  }

  listAllItems() {
    return Item.listAll();
  }

  listItemsInStorage() {
    return Item.list(false);
  }

  listRedeemedItems() {
    return Item.list(true);
  }

  redeem(item) {
    return Item.redeem(item);
  }

  update(item: ItemModel) {
    return Item.update(item);
  }
}
