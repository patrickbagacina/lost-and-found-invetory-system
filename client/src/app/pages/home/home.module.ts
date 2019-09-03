import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomeComponent } from './home.component';
import { Routes, RouterModule } from '@angular/router';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatSidenavModule } from '@angular/material/sidenav';
import { shared_modules } from 'src/app/shared/shared-module';
import { DashboardComponent } from './dashboard/dashboard.component';
import { ItemsIndexComponent } from './items-index/items-index.component';
import { ItemsCreateComponent } from './items-create/items-create.component';
import { ItemsViewComponent } from './items-view/items-view.component';
import { RedeemedItemsIndexComponent } from './redeemed-items-index/redeemed-items-index.component';
import { RedeemedItemsViewComponent } from './redeemed-items-view/redeemed-items-view.component';
import { ItemService } from 'src/app/services/item.service';
import { TableComponent } from 'src/app/shared/table/table.component';
import { SharedComponentsModule } from 'src/app/shared/shared-components.module';
import { ItemsRedeemComponent } from './items-redeem/items-redeem.component';

const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
    children: [
      {
        path: '',
        redirectTo: 'dashboard',
        pathMatch: 'full'
      },
      {
        path: 'dashboard',
        component: DashboardComponent
      },
      {
        path: 'items/create',
        component: ItemsCreateComponent
      },
      {
        path: 'items/:id',
        component: ItemsViewComponent
      },
      {
        path: 'items',
        component: ItemsIndexComponent
      },
      {
        path: 'redeemed/:id',
        component: RedeemedItemsViewComponent
      },
      {
        path: 'redeemed',
        component: RedeemedItemsIndexComponent
      }
    ]
  }
];

@NgModule({
  declarations: [
    HomeComponent,
    DashboardComponent,
    ItemsIndexComponent,
    ItemsCreateComponent,
    ItemsViewComponent,
    RedeemedItemsIndexComponent,
    RedeemedItemsViewComponent,
    ItemsRedeemComponent
  ],
  imports: [
    CommonModule,
    RouterModule.forChild(routes),
    MatSidenavModule,
    MatToolbarModule,
    SharedComponentsModule,
    ...shared_modules
  ],
  providers: [
    ItemService
  ],
  entryComponents: [
    ItemsRedeemComponent
  ]
})
export class HomeModule { }
