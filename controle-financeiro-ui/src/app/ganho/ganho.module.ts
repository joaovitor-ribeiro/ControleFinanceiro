import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { MAT_DATE_FORMATS } from '@angular/material/core';

import { GanhoFormModule } from './ganho-form/ganho-form.module';
import { GanhoListaModule } from './ganho-lista/ganho-lista.module';
import { GanhoRoutingModule } from './ganho-routing.module';

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    GanhoFormModule,
    GanhoListaModule,
    GanhoRoutingModule
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
export class GanhoModule { }
