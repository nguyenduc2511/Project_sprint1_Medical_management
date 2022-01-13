import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {FinancialComponent} from './financial/financial.component';
import {PotentialCustomerComponent} from './potential-customer/potential-customer.component';
import {SuppliesComponent} from './supplies/supplies.component';
import {AuthGuard} from '../security/auth.guard';


const routes: Routes = [
  {
    path: 'financial',
    component: FinancialComponent,
    canActivate: [AuthGuard],
    data: {expectedRole: ['ROLE_USER']}
  },
  {
    path: 'potential-customer',
    component: PotentialCustomerComponent,
    canActivate: [AuthGuard],
    data: {expectedRole: ['ROLE_USER']}
  },
  {
    path: 'supplies-stats',
    component: SuppliesComponent,
    canActivate: [AuthGuard],
    data: {expectedRole: ['ROLE_USER']}
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class StatsRoutingModule { }
