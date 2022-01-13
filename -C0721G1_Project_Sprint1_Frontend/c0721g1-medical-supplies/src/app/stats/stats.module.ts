import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { StatsRoutingModule } from './stats-routing.module';
import { FinancialComponent } from './financial/financial.component';
import { PotentialCustomerComponent } from './potential-customer/potential-customer.component';
import { SuppliesComponent } from './supplies/supplies.component';
import { TrendingSuppliesComponent } from './trending-supplies/trending-supplies.component';
import {FormsModule} from '@angular/forms';
import {SharedModule} from '../shared/shared.module';
import {BsDatepickerModule} from 'ngx-bootstrap/datepicker';
import {NgxPaginationModule} from 'ngx-pagination';


@NgModule({
  declarations: [FinancialComponent, PotentialCustomerComponent, SuppliesComponent, TrendingSuppliesComponent],
  imports: [
    CommonModule,
    StatsRoutingModule,
    FormsModule,
    SharedModule,
    BsDatepickerModule,
    NgxPaginationModule
  ]
})
export class StatsModule { }
