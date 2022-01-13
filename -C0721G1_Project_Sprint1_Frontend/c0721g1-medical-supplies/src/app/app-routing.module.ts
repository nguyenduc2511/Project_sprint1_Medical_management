import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {IntroduceComponent} from './home/introduce/introduce.component';
import {CertificateComponent} from './home/certificate/certificate.component';
import {ActivityComponent} from './home/activity/activity.component';
import {ContactComponent} from './home/contact/contact.component';
import {CoporateComponent} from './home/coporate/coporate.component';


const routes: Routes = [
  {
    path: 'auth',
    loadChildren: () => import('./security/security.module').then(module => module.SecurityModule)
  },
  {
    path: 'home',
    loadChildren: () => import('./home/home.module').then(module => module.HomeModule)
  },
  {
    path: '',
    redirectTo: 'home',
    pathMatch: 'full'
  },
  {
    path: 'system',
    loadChildren: () => import('./system/system.module').then(module => module.SystemModule)
  },
  {
    path: 'employee',
    loadChildren: () => import('./employee/employee.module').then(module => module.EmployeeModule)
  },
  {
    path: 'home',
    loadChildren: () => import('./home/home.module').then(module => module.HomeModule)
  },
  {
    path: 'cart',
    loadChildren: () => import('./cart/cart.module').then(module => module.CartModule)
  },
  {
    path: 'payment/:total',
    loadChildren: () => import('./payment/payment.module').then(module => module.PaymentModule)
  },
  {
    path: 'supplies',
    loadChildren: () => import('./supplies/supplies.module').then(module => module.SuppliesModule)
  },
  {
    path: 'stats',
    loadChildren: () => import('./stats/stats.module').then(module => module.StatsModule)
  },
  {
    path: 'introduce',
    component: IntroduceComponent
  },
  {
    path: 'certificate',
    component: CertificateComponent
  },
  {
    path: 'activity',
    component: ActivityComponent
  },
  {
    path: 'contact',
    component: ContactComponent
  },
  {
    path: 'coporate',
    component: CoporateComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
