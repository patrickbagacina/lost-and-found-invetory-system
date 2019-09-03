import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './login.component';
import { Routes, RouterModule } from '@angular/router';
import { UserService } from 'src/app/services/user.service';
import { shared_modules } from 'src/app/shared/shared-module';
import { StorageService } from 'src/app/services/storage.service';

const routes: Routes = [
  {
    path: '',
    component: LoginComponent
  }
];

@NgModule({
  declarations: [LoginComponent],
  imports: [
    CommonModule,
    RouterModule.forChild(routes),
    ...shared_modules
  ],
  providers: [
    UserService,
    StorageService
  ]
})
export class LoginModule { }
