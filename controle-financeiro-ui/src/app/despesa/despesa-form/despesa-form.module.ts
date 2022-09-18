import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatNativeDateModule } from '@angular/material/core';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatDividerModule } from '@angular/material/divider';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { NgxMaskModule } from 'ngx-mask';
import { NgxSpinnerModule } from 'ngx-spinner';
import { InputFieldModule } from 'src/app/shared/input-field/input-field.module';
import { NumberFieldModule } from 'src/app/shared/number-field/number-field.module';

import { AlertModalModule } from './../../shared/alert-modal/alert-modal.module';
import { DespesaFormComponent } from './despesa-form.component';


@NgModule({
  declarations: [DespesaFormComponent],
  imports: [
    CommonModule,
    MatInputModule,
    FormsModule,
    ReactiveFormsModule,
    MatIconModule,
    MatButtonModule,
    MatCardModule,
    MatDividerModule,
    NgxMaskModule,
    NgxMaskModule.forRoot(),
    AlertModalModule,
    NgxSpinnerModule,
    MatSelectModule,
    MatDatepickerModule,
    MatNativeDateModule,
    InputFieldModule,
    NumberFieldModule
  ]
})
export class DespesaFormModule { }
