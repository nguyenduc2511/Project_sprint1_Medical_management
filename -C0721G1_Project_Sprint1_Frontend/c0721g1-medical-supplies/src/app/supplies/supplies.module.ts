import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SuppliesRoutingModule } from './supplies-routing.module';
import { ListComponent } from './list/list.component';
import { EditComponent } from './edit/edit.component';
import { CreateComponent } from './create/create.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {SharedModule} from '../shared/shared.module';
import { TruncateTextPipe } from './truncate-text.pipe';

@NgModule({
  declarations: [ListComponent, EditComponent, CreateComponent, TruncateTextPipe],
    imports: [
        CommonModule,
        SuppliesRoutingModule,
        ReactiveFormsModule,
        FormsModule,
        SharedModule
    ]
})
export class SuppliesModule { }
