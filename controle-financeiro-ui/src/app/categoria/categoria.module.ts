import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

import { CategoriaFormModule } from './categoria-form/categoria-form.module';
import { CategoriaListaModule } from './categoria-lista/categoria-lista.module';
import { CategoriaRoutingModule } from './categoria-routing.module';

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    CategoriaFormModule,
    CategoriaListaModule,
    CategoriaRoutingModule
  ]
})
export class CategoriaModule { }
