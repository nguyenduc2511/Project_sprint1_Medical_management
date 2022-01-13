import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {EmployeeRoutingModule} from './employee-routing.module';
import {ListComponent} from './list/list.component';
import {CreateComponent} from './create/create.component';
import {EditComponent} from './edit/edit.component';
import {DetailComponent} from './detail/detail.component';
import {ChangePasswordComponent} from './change-password/change-password.component';
import {DetailEditComponent} from './detail-edit/detail-edit.component';
import {SharedModule} from '../shared/shared.module';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { TruncateTextPipe } from './truncate-text.pipe';


@NgModule({
  declarations: [ListComponent, CreateComponent, EditComponent, DetailComponent,
    ChangePasswordComponent, DetailEditComponent, TruncateTextPipe],
    imports: [
        CommonModule,
        EmployeeRoutingModule,
        SharedModule,
        ReactiveFormsModule,
        FormsModule
    ]
})
export class EmployeeModule {
}
