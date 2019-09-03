import { Component, OnInit } from '@angular/core';
import { ItemService } from 'src/app/services/item.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-redeemed-items-index',
  templateUrl: './redeemed-items-index.component.html',
  styleUrls: ['./redeemed-items-index.component.scss']
})
export class RedeemedItemsIndexComponent implements OnInit {
  items: any[] = [];

  constructor(
    private itemService: ItemService,
    private router: Router
  ) { }

  ngOnInit() {
    this.listItems();
  }

  listItems() {
    this.itemService.listRedeemedItems().then((res: any) => {
      this.items = res.data.items.data;
    });
  }

  viewItem(row) {
    this.router.navigateByUrl(`/redeemed/${row.id}`);
  }
}
