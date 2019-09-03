import { Storage } from './../../services/storage.service';
import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { StorageService } from 'src/app/services/storage.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  invalidCredentials = false;

  constructor(
    private userService: UserService,
    private builder: FormBuilder,
    private router: Router,
    private storageService: StorageService
  ) { }

  ngOnInit() {
    this.buildForm();
  }

  buildForm() {
    this.loginForm = this.builder.group({
      username: ['', [
        Validators.required
      ]],
      password: ['', [
        Validators.required
      ]]
    });
  }

  login() {
    this.invalidCredentials = false;

    if (this.username.valid && this.password.valid) {
      this.userService.login(this.username.value, this.password.value)
        .then((res: any) => {
          this.storageService.setItem(
            Storage.CURRENT_USER,
            JSON.stringify(res.data.login)
          );
          this.router.navigateByUrl('');
        })
        .catch((error: any) => {
          this.username.setValue(null);
          this.password.setValue(null);
          this.invalidCredentials = true;
        });
    }
  }

  get username() {
    return this.loginForm.get('username');
  }

  get password() {
    return this.loginForm.get('password');
  }
}
