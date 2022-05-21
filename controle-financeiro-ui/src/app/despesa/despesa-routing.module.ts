import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { DespesaFormComponent } from './despesa-form/despesa-form.component';
import { DespesaListaComponent } from './despesa-lista/despesa-lista.component';

const despesasRoutes: Routes = [
  {path: 'inserir', component: DespesaFormComponent},
  {path: 'listar',  component: DespesaListaComponent},
  {path: ':id',     component: DespesaFormComponent},
];

@NgModule({
  imports: [RouterModule.forChild(despesasRoutes)],
  exports: [RouterModule]
})
export class DespesaRoutingModule { }
