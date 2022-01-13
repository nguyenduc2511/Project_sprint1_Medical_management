import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {ListComponent} from './list/list.component';
import {AuthAdminGuard} from '../security/auth.admin.guard';
import {CreateComponent} from './create/create.component';
import {EditComponent} from './edit/edit.component';


const routes: Routes = [
  {
    path: 'list',
    component: ListComponent,
    canActivate: [AuthAdminGuard],
    data: {expectedRole: ['ROLE_ADMIN']}
  },
  {
    path: 'create',
    component: CreateComponent,
    canActivate: [AuthAdminGuard],
    data: {expectedRole: ['ROLE_ADMIN']}
  },
  {
    path: 'edit/:id',
    component: EditComponent,
    canActivate: [AuthAdminGuard],
    data: {expectedRole: ['ROLE_ADMIN']}
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SuppliesRoutingModule { }
