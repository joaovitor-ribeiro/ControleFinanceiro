import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

import { UsuarioFormModule } from './usuario-form/usuario-form.module';
import { UsuarioRoutingModule } from './usuario-routing.module';

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    UsuarioFormModule,
    UsuarioRoutingModule
  ]
})
export class UsuarioModule { }
