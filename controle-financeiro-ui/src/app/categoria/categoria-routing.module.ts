import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { CategoriaFormComponent } from './categoria-form/categoria-form.component';
import { CategoriaListaComponent } from './categoria-lista/categoria-lista.component';

const categoriaRoutes: Routes = [
  {path: 'inserir', component: CategoriaFormComponent},
  {path: 'listar',  component: CategoriaListaComponent},
  {path: ':id',     component: CategoriaFormComponent},
];

@NgModule({
  imports: [RouterModule.forChild(categoriaRoutes)],
  exports: [RouterModule]
})
export class CategoriaRoutingModule { }
