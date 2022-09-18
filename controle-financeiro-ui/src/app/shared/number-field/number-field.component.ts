import { Component, forwardRef, Input, OnInit } from '@angular/core';
import { AbstractControl, FormControl, FormGroupDirective, NG_VALUE_ACCESSOR, NgForm } from '@angular/forms';
import { ErrorStateMatcher } from '@angular/material/core';

const NUMBER_FIELD_VALUE_ACCESSOR: any = {
  provide: NG_VALUE_ACCESSOR,
  useExisting: forwardRef(() => NumberFieldComponent),
  multi: true
};

@Component({
  selector: 'number-field',
  templateUrl: './number-field.component.html',
  styleUrls: ['./number-field.component.scss'],
  providers: [NUMBER_FIELD_VALUE_ACCESSOR]
})
export class NumberFieldComponent implements OnInit {

  @Input() label!: string;
  @Input() id!: string;
  @Input() control!: FormControl | AbstractControl | null | undefined;
  @Input() required = true;
  @Input() mensagemRequired!: string;
  @Input() mensagemValorZero!: string;
  @Input() valorMinimo = 1;

  private innerValue: any;
  matcher: any;
  iniciarMatcher = true;

  get errorMessage() {
    if (this.control?.hasError('required')) {
      return this.mensagemRequired;
    }
    if (this.control?.hasError('valorInvalido') || this.control?.hasError('limiteInvalido')) {
      if (this.iniciarMatcher) {
        this.iniciarMatcher = false;
        this.matcher = new MyErrorStateMatcher(this.control);
      }
      return this.mensagemValorZero;
    }
    return null;
  }

  constructor() { }

  ngOnInit(): void { }

  get value() {
    return this.innerValue;
  }

  set value(v: any) {
    if (v !== this.innerValue) {
      this.innerValue = v;
      this.onChangeCb(v);
    }
  }

  onChangeCb: (_: any) => void = () => {};
  onTouchedCb: (_: any) => void = () => {};

  writeValue(v: any): void {
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

export class MyErrorStateMatcher implements ErrorStateMatcher {

  controle: FormControl | AbstractControl | null | undefined

  constructor(controle: FormControl | AbstractControl | null | undefined) {
    this.controle = controle;
  }

  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    return !! (this.controle?.hasError('valorInvalido') || this.controle?.hasError('limiteInvalido') || this.controle?.hasError('required')) ;
  }

}
