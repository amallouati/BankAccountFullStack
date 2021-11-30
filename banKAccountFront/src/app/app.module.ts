import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';

import { AllAccountComponent } from './components/views/account-bank/account-bank.component';
import { AppRoutingModule } from './app-routing.module';
import { GestionOperationsModule } from './components/views/operations/operations.module';
import { ReactiveFormsModule } from '@angular/forms';
import { BalanceComponent } from './components/shared/balance.component';


@NgModule({
  declarations: [
    AppComponent,
    BalanceComponent,
    AllAccountComponent,

    
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    ReactiveFormsModule,
    
    GestionOperationsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
