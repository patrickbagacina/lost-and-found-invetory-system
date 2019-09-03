import { UsersApi } from './users-api';
import { ItemsApi } from './items-api';
import { Apollo } from 'apollo-angular';

export class ApiClient {
  private readonly itemsApi;
  private readonly usersApi;

  constructor(private apollo: Apollo) {
    this.itemsApi = new ItemsApi(apollo);
    this.usersApi = new UsersApi(apollo);
  }

  items(): ItemsApi {
    return this.itemsApi;
  }

  users(): UsersApi {
    return this.usersApi;
  }
}
