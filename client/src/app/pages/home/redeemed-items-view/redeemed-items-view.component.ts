import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ItemService } from 'src/app/services/item.service';

@Component({
  selector: 'app-redeemed-items-view',
  templateUrl: './redeemed-items-view.component.html',
  styleUrls: ['./redeemed-items-view.component.scss']
})
export class RedeemedItemsViewComponent implements OnInit {
  item: any;

  constructor(
    private aRoute: ActivatedRoute,
    private router: Router,
    private itemService: ItemService
  ) { }

  ngOnInit() {
    this.getItem();
  }

  getItem() {
    this.aRoute.params.subscribe((data: any) => {
      this.itemService.get(data.id).then((res: any) => {
        this.item = res.data.item;
      });
    });
  }

  goTo(path) {
    this.router.navigate([path]);
  }
}
