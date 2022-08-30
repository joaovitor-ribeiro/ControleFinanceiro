import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AuthGuard } from '../guard/auth.guard';
import { CategoriaFormComponent } from './categoria-form/categoria-form.component';
import { CategoriaListaComponent } from './categoria-lista/categoria-lista.component';

const categoriaRoutes: Routes = [
  {path: 'inserir', component: CategoriaFormComponent,  canActivate: [AuthGuard]},
  {path: 'listar',  component: CategoriaListaComponent, canActivate: [AuthGuard]},
  {path: ':id',     component: CategoriaFormComponent,  canActivate: [AuthGuard]},
];

@NgModule({
  imports: [RouterModule.forChild(categoriaRoutes)],
  exports: [RouterModule]
})
export class CategoriaRoutingModule { }
