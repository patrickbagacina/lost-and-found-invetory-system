import { Model } from './model';

export class Item extends Model {
  static create(item: ItemModel) {
    return this.api().items().create(item);
  }

  static get(id: string) {
    return this.api().items().get(id);
  }

  static listAll() {
    return this.api().items().listAll();
  }

  static list(isRedeemed: boolean) {
    return this.api().items().list(isRedeemed);
  }

  static redeem(item: ItemModel) {
    return this.api().items().redeem(item);
  }

  static update(item: ItemModel) {
    return this.api().items().update(item);
  }
}

export interface ItemModel {
  id: string;
  brand: string;
  category: string;
  type: string;
  description: string;
}
