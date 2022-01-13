import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {ListComponent} from './list/list.component';
import {AuthAdminGuard} from '../security/auth.admin.guard';
import {CreateComponent} from './create/create.component';
import {EditComponent} from './edit/edit.component';
import {DetailComponent} from './detail/detail.component';
import {AuthGuard} from '../security/auth.guard';
import {ChangePasswordComponent} from './change-password/change-password.component';
import {DetailEditComponent} from './detail-edit/detail-edit.component';


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
  },
  {
    path: 'detail/:id',
    component: DetailComponent,
    canActivate: [AuthGuard],
    data: {expectedRole: ['ROLE_USER']}
  },
  {
    path: 'change-password/:id',
    component: ChangePasswordComponent,
    canActivate: [AuthGuard],
    data: {expectedRole: ['ROLE_USER']}
  },
  {
    path: 'detail-edit/:id',
    component: DetailEditComponent,
    canActivate: [AuthGuard],
    data: {expectedRole: ['ROLE_USER']}
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EmployeeRoutingModule { }
