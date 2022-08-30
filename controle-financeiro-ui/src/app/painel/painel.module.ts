import { CommonModule, registerLocaleData } from '@angular/common';
import ptBr from '@angular/common/locales/pt';
import { LOCALE_ID, NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatDividerModule } from '@angular/material/divider';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { NgChartsModule } from 'ng2-charts';
import { NgxSpinnerModule } from 'ngx-spinner';

import { PainelRoutingModule } from './painel-routing.module';
import { PainelComponent } from './painel.component';

registerLocaleData(ptBr); //Outra maneira de formatar dinheiro no HTML

@NgModule({
  declarations: [
    PainelComponent
  ],
  imports: [
    CommonModule,
    MatButtonModule,
    MatCardModule,
    MatDividerModule,
    MatIconModule,
    MatInputModule,
    MatSelectModule,
    NgxSpinnerModule,
    NgChartsModule,
    MatDatepickerModule,
    ReactiveFormsModule,
    FormsModule,
    PainelRoutingModule
  ],
  exports: [
    PainelComponent
  ],
  providers: [
    { provide: LOCALE_ID, useValue: 'pt' },
  ],
})
export class PainelModule { }
