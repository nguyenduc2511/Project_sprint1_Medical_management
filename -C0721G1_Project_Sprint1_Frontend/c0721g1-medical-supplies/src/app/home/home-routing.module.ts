import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {HomepageComponent} from './homepage/homepage.component';
import {ListSuppliesComponent} from './list-supplies/list-supplies.component';
import {DetailSuppliesComponent} from './detail-supplies/detail-supplies.component';


const routes: Routes = [
  {
    path: '',
    component: HomepageComponent
  },
  {
    path: 'list',
    component: ListSuppliesComponent

  },
  {
    path: 'detail/:id',
    component: DetailSuppliesComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class HomeRoutingModule { }
