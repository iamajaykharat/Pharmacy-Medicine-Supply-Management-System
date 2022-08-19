import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { NgxPaginationModule } from 'ngx-pagination';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SigninComponent } from './component/signin/signin.component';
import { SignUpComponent } from './component/sign-up/sign-up.component';
import { HomeComponent } from './component/home/home.component';
import { AuthService } from './service/auth-service/auth.service';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AuthGuard } from './guard/auth.guard';
import { ErrorPageComponent } from './component/error-page/error-page.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { MrScheduleComponent } from './component/mr-schedule/mr-schedule.component';
import { CheckStockComponent } from './component/check-stock/check-stock.component';
import { PlaceOrderComponent } from './component/place-order/place-order.component';
import { OrdersComponent } from './component/orders/orders.component';
import { DemandComponent } from './component/demand/demand.component';
import { CheckStockService } from './service/check-stock/check-stock.service';
import { TokenInterceptorService } from './service/interceptor/token-interceptor.service';
import { MrScheduleService } from './service/mr-schedule/mr-schedule.service';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToastrModule } from 'ngx-toastr';
import { NotificationService } from './service/notification-service/notification.service';
import { FilterSearchPipe } from './pipe/filter-search.pipe';
import { DemandFilterPipe } from './pipe/demand-filter.pipe';
import { StockfilterPipe } from './pipe/stockfilter.pipe';

@NgModule({
  declarations: [
    AppComponent,
    SigninComponent,
    SignUpComponent,
    HomeComponent,
    ErrorPageComponent,
    MrScheduleComponent,
    CheckStockComponent,
    PlaceOrderComponent,
    OrdersComponent,
    DemandComponent,
    FilterSearchPipe,
    DemandFilterPipe,
    StockfilterPipe
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    FontAwesomeModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot(),
    NgxPaginationModule,
    ReactiveFormsModule
  ],
  providers: [
    AuthService,
    AuthGuard,
    MrScheduleService,
    NotificationService,
    CheckStockService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptorService,
      multi:true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
