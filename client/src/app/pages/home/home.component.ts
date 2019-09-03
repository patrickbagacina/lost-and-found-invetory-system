import { Component, OnInit } from '@angular/core';
import { StorageService, Storage } from 'src/app/services/storage.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  constructor(
    private router: Router,
    private storageService: StorageService
  ) { }

  ngOnInit() {
  }

  logout() {
    this.storageService.removeItem(Storage.CURRENT_USER);
    this.router.navigateByUrl('login');
  }

  goTo(path) {
    this.router.navigate([path]);
  }
}
