import { Component, forwardRef, Input, OnInit } from '@angular/core';
import { AbstractControl, FormControl, NG_VALUE_ACCESSOR } from '@angular/forms';

const INPUT_FIELD_VALUE_ACCESSOR: any = {
  provide: NG_VALUE_ACCESSOR,
  useExisting: forwardRef(() => InputFieldComponent),
  multi: true
};

@Component({
  selector: 'input-field',
  templateUrl: './input-field.component.html',
  styleUrls: ['./input-field.component.scss'],
  providers: [INPUT_FIELD_VALUE_ACCESSOR]
})
export class InputFieldComponent implements OnInit {

  @Input() label!: string;
  @Input() foco!: boolean;
  @Input() control!: FormControl | AbstractControl | null | undefined;
  @Input() maximo = 20;
  @Input() minimo = 3;
  @Input() required = true;
  @Input() mensagemRequired!: string;
  @Input() mensagemMin!: string;
  @Input() type = 'text';

  private innerValue: any;

  get errorMessage() {
    if (this.control?.hasError('required')) {
      return this.mensagemRequired;
    }
    if (this.control?.hasError('minlength')) {
      return this.mensagemMin;
    }
    return null;
  }

  constructor() { }

  ngOnInit(): void {
    if (this.foco) {
      setTimeout(() => {
        document.getElementById('inputField')?.focus();
      }, 250);
    }
  }

  get value() {
    return this.innerValue;
  }

  set value(v: string) {
    if (v !== this.innerValue) {
      this.innerValue = v;
      this.onChangeCb(v);
    }
  }

  onChangeCb: (_: any) => void = () => {};
  onTouchedCb: (_: any) => void = () => {};

  writeValue(v: string): void {
    this.value = v ;
  }

  registerOnChange(fn: any): void {
    this.onChangeCb = fn;
  }

  registerOnTouched(fn: any): void {
    this.onTouchedCb = fn;
  }

  limparCampo() {
    this.value = '';
  }

}
