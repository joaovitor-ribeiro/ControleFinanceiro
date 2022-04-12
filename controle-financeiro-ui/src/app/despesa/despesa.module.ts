import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { MAT_DATE_FORMATS } from '@angular/material/core';

import { DespesaFormModule } from './despesa-form/despesa-form.module';
import { DespesaListaModule } from './despesa-lista/despesa-lista.module';
import { DespesaRoutingModule } from './despesa-routing.module';

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    DespesaFormModule,
    DespesaListaModule,
    DespesaRoutingModule
  ],
  providers: [
    {
      provide: MAT_DATE_FORMATS,
      useValue: {
        parse: {
          dateInput: ['l', 'LL'],
        },
        display: {
          dateInput: 'L',
          monthYearLabel: 'MMM YYYY',
          dateA11yLabel: 'LL',
          monthYearA11yLabel: 'MMMM YYYY',
        },
      },
    }
  ]
})
export class DespesaModule { }
