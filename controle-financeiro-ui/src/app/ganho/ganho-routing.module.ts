import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { GanhoFormComponent } from './ganho-form/ganho-form.component';
import { GanhoListaComponent } from './ganho-lista/ganho-lista.component';

const ganhoRoutes: Routes = [
  {path: 'inserir', component: GanhoFormComponent},
  {path: 'listar',  component: GanhoListaComponent},
  {path: ':id',     component: GanhoFormComponent},
];

@NgModule({
  imports: [ RouterModule.forChild(ganhoRoutes)],
  exports: [RouterModule]
})
export class GanhoRoutingModule { }
