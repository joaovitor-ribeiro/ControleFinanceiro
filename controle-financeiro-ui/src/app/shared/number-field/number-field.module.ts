import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { NgxMaskModule } from 'ngx-mask';

import { NumberFieldComponent } from './number-field.component';

@NgModule({
  declarations: [NumberFieldComponent],
  imports: [
    CommonModule,
    MatInputModule,
    FormsModule,
    ReactiveFormsModule,
    MatIconModule,
    MatButtonModule,
    NgxMaskModule,
    NgxMaskModule.forRoot(),
  ],
  exports: [NumberFieldComponent]
})
export class NumberFieldModule { }
