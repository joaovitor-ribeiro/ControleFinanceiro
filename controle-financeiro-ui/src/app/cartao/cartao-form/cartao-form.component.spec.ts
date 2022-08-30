import { LocationStrategy } from '@angular/common';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { MockLocationStrategy } from '@angular/common/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormControl } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterTestingModule } from '@angular/router/testing';

import { Bandeira } from '../cartao.model';
import { CartaoFormComponent } from './cartao-form.component';
import { CartaoFormModule } from './cartao-form.module';

describe(CartaoFormComponent.name, () => {
  let component: CartaoFormComponent;
  let fixture: ComponentFixture<CartaoFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        CartaoFormModule,
        HttpClientTestingModule,
        RouterTestingModule,
        BrowserAnimationsModule,
      ],
      providers: [{provide: LocationStrategy, useClass: MockLocationStrategy}],
    }).compileComponents();

    fixture = TestBed.createComponent(CartaoFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('#validarNumero deve retornar false quando o número é inválido', () => {
    const numerosInvalidos = ['5117555768291171', '0123456789123456', 'aknjdnsgjngsg', '!#$%¨&*(?', '340111123151020'];
    numerosInvalidos.forEach(numero => {
      const invalido = component.validarNumero(numero);
      expect(invalido).toBeFalse();
    });
  });

  it('#validarNumero deve retornar true quando o número é válido', () => {
    const numerosValidos = ['4929058595075605', '5431723688840383', '340722823151020'];
    numerosValidos.forEach(numero => {
      const valido = component.validarNumero(numero);
      expect(valido).toBeTrue();
    });
  });

  it('#validarCartao deve retornar o erro: cartaoInvalido quando o número é inválido', async () => {
    const numerosInvalidos = ['5117555768291171', '0123456789123456', 'aknjdnsgjngsg', '!#$%¨&*(?', '340111123151020'];
    numerosInvalidos.forEach(async numero => {
      const erro = await component.validarCartao( new FormControl(numero) );
      expect(erro).toEqual({ cartaoInvalido: true });
    });
  });

  it('#validarCartao deve retornar null quando o número é válido', async () => {
    const numerosValidos = ['4929058595075605', '5431723688840383', '340722823151020'];
    numerosValidos.forEach(async numero => {
      const erro = await component.validarCartao( new FormControl(numero) );
      expect(erro).toEqual(null);
    });
  });

  it('#validarNumeroCorrespondenteABandeira deve retornar true quando o número e a bandeira forem compatíveis', () => {
    const numerosValidos = ['4539063642617856', '5481132071163156', '347099436377810', '3596788135012213', '30289539153372', '6062824301637513'];
    const bandeirasValidas = [Bandeira.visa, Bandeira.mastercard, Bandeira.americanExpress, Bandeira.jcb, Bandeira.disnerClub, Bandeira.hipercard];
    numerosValidos.forEach((numero, i) => {
      component.cartaoFormulario.get('bandeira')?.setValue(bandeirasValidas[i]);
      component.cartaoFormulario.get('numero')?.setValue(numero);
      const bandeiraCorrespodeAoNumero = component.validarNumeroCorrespondenteABandeira();
      expect(bandeiraCorrespodeAoNumero).toBeTrue();
    });
  });

  it('#validarNumeroCorrespondenteABandeira deve retornar false quando o número e a bandeira não forem compatíveis', () => {
    const numerosValidos = ['5481132071163156', '4539063642617856', '3596788135012213', '347099436377810', '6062824301637513', '30289539153372'];
    const bandeirasValidas = [Bandeira.visa, Bandeira.mastercard, Bandeira.americanExpress, Bandeira.jcb, Bandeira.disnerClub, Bandeira.hipercard];
    numerosValidos.forEach((numero, i) => {
      component.cartaoFormulario.get('bandeira')?.setValue(bandeirasValidas[i]);
      component.cartaoFormulario.get('numero')?.setValue(numero);
      const bandeiraNaoCorrespodeAoNumero = component.validarNumeroCorrespondenteABandeira();
      expect(bandeiraNaoCorrespodeAoNumero).toBeFalse();
    });
  });

  it('#validarNumeroCorrespondeABandeira deve retorna o erro: numeroNaoCorrespondeABandeira quando o número e a bandeira não forem compatíveis', async () => {
    const numerosValidos = ['5481132071163156', '4539063642617856', '3596788135012213', '347099436377810', '6062824301637513', '30289539153372'];
    const bandeirasValidas = [Bandeira.visa, Bandeira.mastercard, Bandeira.americanExpress, Bandeira.jcb, Bandeira.disnerClub, Bandeira.hipercard];
    component.cartaoFormulario.get('bandeira')?.setValue(Bandeira.visa);
    component.cartaoFormulario.get('numero')?.setValue('5481132071163156');
    numerosValidos.forEach(async (numero, i) => {
      component.cartaoFormulario.get('bandeira')?.setValue(bandeirasValidas[i]);
      component.cartaoFormulario.get('numero')?.setValue(numero);
      const erro = await component.validarNumeroCorrespondeABandeira(new FormControl());
      expect(erro).toEqual({ numeroNaoCorrespondeABandeira: true });
    });
  });

  it('#validarNumeroCorrespondeABandeira deve retorna null quando o número e a bandeira forem compatíveis', async () => {
    const numerosValidos = ['4539063642617856', '5481132071163156', '347099436377810', '3596788135012213', '30289539153372', '6062824301637513'];
    const bandeirasValidas = [Bandeira.visa, Bandeira.mastercard, Bandeira.americanExpress, Bandeira.jcb, Bandeira.disnerClub, Bandeira.hipercard];
    component.cartaoFormulario.get('bandeira')?.setValue(Bandeira.visa);
    component.cartaoFormulario.get('numero')?.setValue('5481132071163156');
    numerosValidos.forEach(async (numero, i) => {
      component.cartaoFormulario.get('bandeira')?.setValue(bandeirasValidas[i]);
      component.cartaoFormulario.get('numero')?.setValue(numero);
      const erro = await component.validarNumeroCorrespondeABandeira(new FormControl());
      expect(erro).toEqual(null);
    });
  });

});
