import { Component, OnInit } from '@angular/core';
import { ItemService } from 'src/app/services/item.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {
  allItems: any;
  redeemedItems: any;

  constructor(
    private itemService: ItemService
  ) { }

  ngOnInit() {
    this.listAllItems();
  }

  listAllItems() {
    this.itemService.listAllItems().then((res: any) => {
      this.allItems = res.data.allItems.data;
      this.redeemedItems = this.allItems.filter((i) => i.isRedeemed);
    });
  }
}
