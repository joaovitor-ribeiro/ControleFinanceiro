import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

import { LoginFormModule } from './login-form/login-form.module';
import { LoginRoutingModule } from './login-routing.module';

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    LoginRoutingModule,
    LoginFormModule
  ],
  exports: []
})
export class LoginModule { }
