import { Category } from './../../../models/category';
import { Component, OnInit } from '@angular/core';
import { ItemService } from 'src/app/services/item.service';
import { ChartOptions, ChartType, ChartDataSets } from 'chart.js';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {
  allItems: any;
  redeemedItems: any;

  public barChartData: ChartDataSets[] = [];
  public barChartOptions: ChartOptions = {
    responsive: true,
    scales: { xAxes: [{}], yAxes: [{}] },
    plugins: {
      datalabels: {
        anchor: 'end',
        align: 'end',
      }
    }
  };
  public barChartLabels = ['Categories'];
  public barChartType: ChartType = 'bar';
  public barChartLegend = true;

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

      Object.keys(Category).forEach(cat => {
        this.barChartData.push(
          {
            data: [this.allItems.filter(i => i.category === Category[cat]).length],
            label: Category[cat]
          }
        );
      });
    });
  }
}
