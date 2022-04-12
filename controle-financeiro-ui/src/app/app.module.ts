import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { CartaoModule } from './cartao/cartao.module';
import { HttpClientModule } from '@angular/common/http';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatIconModule } from '@angular/material/icon';
import { MatDividerModule } from '@angular/material/divider';
import { MatListModule}  from '@angular/material/list';
import { CategoriaModule } from './categoria/categoria.module';
import { DespesaModule } from './despesa/despesa.module';


@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    CartaoModule,
    CategoriaModule,
    MatSidenavModule,
    MatIconModule,
    MatDividerModule,
    MatListModule,
    DespesaModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
