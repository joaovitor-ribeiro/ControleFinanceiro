import { HttpClient } from '@angular/common/http';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TestBed, tick } from '@angular/core/testing';
import { environment } from 'src/environments/environment';
import { MockLocationStrategy } from '@angular/common/testing';

import { Bandeira, Cartao, FiltroCartao } from './cartao.model';
import { CartaoService } from './cartao.service';


describe(CartaoService.name, () => {
  let service: CartaoService;
  let httpMock: HttpTestingController;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        HttpClientTestingModule,
      ],
      providers: [{CartaoService, useClass: MockLocationStrategy}],
    }).compileComponents();

    service = TestBed.inject(CartaoService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });


  it('deve buscar uma lista de cartões', () => {
    service.listar().subscribe(cartoes => {
      expect(cartoes).toBeTruthy();
      expect(cartoes.length).toBeGreaterThanOrEqual(1);

      const cartao = cartoes[0];
      expect(cartao.nome).toEqual('Nubank');
      expect(cartao.bandeira).toEqual('Mastercard');
      expect(cartao.numero).toEqual('5173863405996183');
      expect(cartao.limite).toEqual('5000.0');
    });

    const httpRequest = httpMock.expectOne(`${environment.url}cartao/listar?nome=&bandeiras=`);
    expect(httpRequest.request.method).toBe('GET');
    expect(httpRequest.request.url).toBe('http://localhost:8080/cartao/listar');
    expect(httpRequest.request.responseType).toBe('json');

    httpMock.verify();
  });

  it('deve cadastrar um novo cartão', () => {
    const cartao = {
      nome: 'Lixo',
      bandeira: Bandeira.visa,
      numero: '4556262750556199',
      limite: '1900',
    } as Cartao;

    service.inserir(cartao).subscribe();

    const httpRequest = httpMock.expectOne(`${environment.url}cartao/inserir`);
    expect(httpRequest.request.method).toBe('POST');
    expect(httpRequest.request.url).toBe('http://localhost:8080/cartao/inserir');
    expect(httpRequest.request.body).toEqual(cartao);

    httpRequest.flush(null, {
      status: 201,
      statusText: 'Created'
    });

    httpMock.verify();
  });

  it('deve buscar um cartão', () => {
    service.retornarCartaoId(1).subscribe();

    const httpRequest = httpMock.expectOne(`${environment.url}cartao/1`);
    expect(httpRequest.request.method).toBe('GET');
    expect(httpRequest.request.url).toBe('http://localhost:8080/cartao/1');
    expect(httpRequest.request.responseType).toBe('json');

    httpMock.verify();
  });

  it('deve editar um cartão', () => {
    const cartao = {
      nome: 'Lixo',
      bandeira: Bandeira.visa,
      numero: '4556262750556199',
      limite: '1900',
    } as Cartao;

    service.editar(1, cartao).subscribe();

    const httpRequest = httpMock.expectOne(`${environment.url}cartao/editar/1`);
    expect(httpRequest.request.method).toBe('PUT');
    expect(httpRequest.request.url).toBe('http://localhost:8080/cartao/editar/1');
    expect(httpRequest.request.body).toEqual(cartao);

    httpMock.verify();
  });

  it('deve excluir um cartão', () => {
    service.excluir(1).subscribe();

    const httpRequest = httpMock.expectOne(`${environment.url}cartao/excluir/1`);
    expect(httpRequest.request.method).toBe('DELETE');
    expect(httpRequest.request.url).toBe('http://localhost:8080/cartao/excluir/1');

    httpMock.verify();
  });


});
