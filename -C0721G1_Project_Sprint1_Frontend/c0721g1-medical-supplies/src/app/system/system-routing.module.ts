import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {SystemPageComponent} from './system-page/system-page.component';
import {AuthGuard} from '../security/auth.guard';
import {MaintenanceComponent} from './maintenance/maintenance.component';


const routes: Routes = [
  {
    path: '',
    component: SystemPageComponent,
    canActivate: [AuthGuard],
    data: {expectedRole: ['ROLE_USER']}
  },
  {
    path: 'maintenance',
    component: MaintenanceComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SystemRoutingModule { }
