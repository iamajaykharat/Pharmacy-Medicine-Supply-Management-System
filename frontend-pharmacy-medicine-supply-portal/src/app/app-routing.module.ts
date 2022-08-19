import { NgModule } from '@angular/core';
import { ExtraOptions, RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { CheckStockComponent } from './component/check-stock/check-stock.component';
import { DemandComponent } from './component/demand/demand.component';
import { ErrorPageComponent } from './component/error-page/error-page.component';
import { HomeComponent } from './component/home/home.component';
import { MrScheduleComponent } from './component/mr-schedule/mr-schedule.component';
import { OrdersComponent } from './component/orders/orders.component';
import { PlaceOrderComponent } from './component/place-order/place-order.component';
import { SignUpComponent } from './component/sign-up/sign-up.component';
import { SigninComponent } from './component/signin/signin.component';
import { AuthGuard } from './guard/auth.guard';

const routerOptions: ExtraOptions = {
  onSameUrlNavigation:'reload',
  scrollPositionRestoration: 'enabled',
  anchorScrolling: 'enabled',
  scrollOffset: [0, 64],
};
const routes: Routes = [
  {
    path:'',
    pathMatch:'full',
    component: SigninComponent
  },
  {
    path:"signin",
    component:SigninComponent
  }, 
  {
    path:"home",
    canActivate :[AuthGuard],
    component:HomeComponent,
  },
  {
    path:"error",
    component:ErrorPageComponent
  },
  {
    path:"signup",
    component:SignUpComponent
  },
  {
    path:"mr-schedule",
    canActivate :[AuthGuard],
    component: MrScheduleComponent
  },
  {
    path:"check-stock",    
    canActivate :[AuthGuard],
    component: CheckStockComponent
  },
  {
    path:"place-order",    
    canActivate :[AuthGuard],
    component: PlaceOrderComponent
  },
  {
    path:"demand",    
    canActivate :[AuthGuard],
    component: DemandComponent
  },
  {
    path:"orders",    
    canActivate :[AuthGuard],
    component: OrdersComponent
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes,routerOptions)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
