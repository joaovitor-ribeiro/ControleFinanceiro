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
import { AlertModalModule } from 'src/app/shared/alert-modal/alert-modal.module';

import { GanhoFormComponent } from './ganho-form.component';

@NgModule({
  declarations: [
    GanhoFormComponent
  ],
  imports: [
    CommonModule,
    MatInputModule,
    FormsModule,
    ReactiveFormsModule,
    MatIconModule,
    MatButtonModule,
    MatCardModule,
    MatDividerModule,
    AlertModalModule,
    NgxSpinnerModule,
    MatSelectModule,
    NgxMaskModule,
    NgxMaskModule.forRoot(),
    MatDatepickerModule,
    MatNativeDateModule
  ]
})
export class GanhoFormModule { }
