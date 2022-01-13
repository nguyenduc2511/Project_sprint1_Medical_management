import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SharedRoutingModule } from './shared-routing.module';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { LeftSideBarComponent } from './left-side-bar/left-side-bar.component';
import { RightSideBarComponent } from './right-side-bar/right-side-bar.component';
import {ReactiveFormsModule} from '@angular/forms';
import {MatDialogModule, MatDialogRef} from '@angular/material/dialog';


@NgModule({
  declarations: [HeaderComponent, FooterComponent, LeftSideBarComponent, RightSideBarComponent],
  imports: [
    CommonModule,
    SharedRoutingModule,
    ReactiveFormsModule,
    MatDialogModule
  ],
  exports: [HeaderComponent, FooterComponent, LeftSideBarComponent, RightSideBarComponent],
  providers: [{provide: MatDialogRef, useValue: {}}]
})
export class SharedModule { }
