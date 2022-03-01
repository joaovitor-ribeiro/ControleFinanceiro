import { environment } from './../../environments/environment';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Cartao, FiltroCartao } from './cartao.model';
import { map } from 'rxjs/operators';
import { ErrorModalService } from '../shared/error-modal/error-modal.service';

@Injectable({
  providedIn: 'root'
})
export class CartaoService {

  private url = `${environment.url}cartao`

  constructor(
    private http: HttpClient,
    private erroService: ErrorModalService,
  ) { }

  inserir(cartao: Cartao) {
    return this.http.post<void>(this.url + '/inserir', cartao);
  }

  listar(filtro: FiltroCartao) {

    const params = {
      nome: filtro?.nome || '',
      bandeiras: filtro?.bandeiras ||  ''
    };

    return this.http.get<Cartao[]>(this.url + '/listar', {params}).pipe(map((result: Cartao[]) => {
    result.forEach(cartao => {
        cartao.limite = Number(cartao.limite).toLocaleString('pt-br', {style: 'currency', currency: 'BRL'});
        switch (cartao.numero.length) {
          case 16:
            cartao.numero = cartao.numero.replace(/(\d{4})?(\d{4})?(\d{4})?(\d{4})/, '$1 $2 $3 $4');
            break;
          case 15:
            cartao.numero = cartao.numero.replace(/(\d{4})?(\d{6})?(\d{5})/, '$1 $2 $3');
            break;
          case 14:
            cartao.numero = cartao.numero.replace(/(\d{4})?(\d{6})?(\d{4})/, '$1 $2 $3');
            break;
        }
      });
      return result;
    }));
  }

  retornarCartaoId(id: number) {
    return this.http.get<Cartao>(this.url + `/${id}`);
  }

  editar(id: number, cartao: Cartao) {
    return this.http.put<void>(this.url + `/editar/${id}`, cartao);
  }

  excluir(id: number) {
    return this.http.delete<void>(this.url + `/excluir/${id}`);
  }

}
