import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SystemRoutingModule } from './system-routing.module';
import { SystemPageComponent } from './system-page/system-page.component';
import {SharedModule} from '../shared/shared.module';
import { MaintenanceComponent } from './maintenance/maintenance.component';


@NgModule({
  declarations: [SystemPageComponent, MaintenanceComponent],
  imports: [
    CommonModule,
    SystemRoutingModule,
    SharedModule
  ]
})
export class SystemModule { }
