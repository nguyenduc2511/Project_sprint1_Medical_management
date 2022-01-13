import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HomeRoutingModule } from './home-routing.module';
import { HomepageComponent } from './homepage/homepage.component';
import { ListSuppliesComponent } from './list-supplies/list-supplies.component';
import {ReactiveFormsModule} from '@angular/forms';
import { DetailSuppliesComponent } from './detail-supplies/detail-supplies.component';
import { IntroduceComponent } from './introduce/introduce.component';
import { CertificateComponent } from './certificate/certificate.component';
import { ActivityComponent } from './activity/activity.component';
import { ContactComponent } from './contact/contact.component';
import { CoporateComponent } from './coporate/coporate.component';


@NgModule({
  declarations: [HomepageComponent, ListSuppliesComponent, DetailSuppliesComponent,
    IntroduceComponent, CertificateComponent, ActivityComponent, ContactComponent, CoporateComponent],
  imports: [
    CommonModule,
    HomeRoutingModule,
    ReactiveFormsModule
  ]
})
export class HomeModule { }
