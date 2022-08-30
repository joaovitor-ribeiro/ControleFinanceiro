import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { MatDividerModule } from '@angular/material/divider';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { MatMenuModule } from '@angular/material/menu';
import { MatSidenavModule } from '@angular/material/sidenav';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CartaoModule } from './cartao/cartao.module';
import { CategoriaModule } from './categoria/categoria.module';
import { DespesaModule } from './despesa/despesa.module';
import { GanhoModule } from './ganho/ganho.module';
import { PainelModule } from './painel/painel.module';
import { LoginModule } from './login/login.module';
import { InterceptService } from './shared/intercept/intercept.service';
import { UsuarioModule } from './usuario/usuario.module';

@NgModule({
  declarations: [
    AppComponent
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
    GanhoModule,
    UsuarioModule,
    PainelModule,
    LoginModule,
    MatMenuModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: InterceptService,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
