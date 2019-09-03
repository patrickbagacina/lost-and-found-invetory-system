import { Model } from './model';

export const emailRegex = '^(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$';

export class User extends Model {
  static login(username, password) {
    return this.api().users().login(username, password);
  }
}

export interface UserModel {
  id: string;
  username: string;
  firstName: string;
  lastName: string;
  email: string;
  mobileNumber: string;
  typeOfId: string;
  idNumber: string;
}
