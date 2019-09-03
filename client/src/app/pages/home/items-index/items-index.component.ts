import { Component, OnInit } from '@angular/core';
import { ItemService } from 'src/app/services/item.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-items-index',
  templateUrl: './items-index.component.html',
  styleUrls: ['./items-index.component.scss']
})
export class ItemsIndexComponent implements OnInit {
  items: any[] = [];

  constructor(
    private itemService: ItemService,
    private router: Router
  ) { }

  ngOnInit() {
    this.listItems();
  }

  addItem() {
    this.router.navigateByUrl('/items/create');
  }

  listItems() {
    this.itemService.listItemsInStorage().then((res: any) => {
      this.items = res.data.items.data;
    });
  }

  viewItem(row) {
    this.router.navigateByUrl(`/items/${row.id}`);
  }
}
