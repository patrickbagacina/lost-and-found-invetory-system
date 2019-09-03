import { User } from './../models/user';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  login(username, password) {
    return User.login(username, password);
  }
}
