import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CartaoListaComponent } from './cartao-lista.component';

describe('CartaoListaComponent', () => {
  let component: CartaoListaComponent;
  let fixture: ComponentFixture<CartaoListaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CartaoListaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CartaoListaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
