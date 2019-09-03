import { ApiClient } from './../apis/api-client';

export const dateTimeFormat = 'MMM D, YYYY HH:mm';
export const timeFormat = 'HH:mm';

export abstract class Model {
  // tslint:disable-next-line:variable-name
  private static _api;

  id: string;
  created: Date;
  updated: Date;

  static setApiClient(arg: ApiClient) {
    this._api = arg;
  }

  static api(): ApiClient {
    return this._api;
  }
}
