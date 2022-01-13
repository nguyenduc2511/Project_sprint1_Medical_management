import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {authInterceptorProviders} from './helper/auth.interceptor';
import {HttpClientModule} from '@angular/common/http';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {ToastrModule} from 'ngx-toastr';
import {MatButtonModule} from '@angular/material/button';
import {SharedModule} from './shared/shared.module';
import {AngularFireModule} from '@angular/fire';
import {environment} from '../environments/environment';
import {BsDatepickerModule} from 'ngx-bootstrap/datepicker';
import {CookieService} from 'ngx-cookie-service';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    SharedModule,
    HttpClientModule,
    BrowserAnimationsModule,
    AngularFireModule.initializeApp(environment.firebaseConfig),
    ToastrModule.forRoot({
      timeOut: 2000,
      closeButton: true,
      progressBar: true,
      progressAnimation: 'increasing'
    }),
    MatButtonModule,
    BsDatepickerModule.forRoot()
  ],
  providers: [authInterceptorProviders, CookieService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
