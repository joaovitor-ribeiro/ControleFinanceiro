import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';
import { environment } from 'src/environments/environment';

import { Despesa, FiltroDespesa, PageDespesa, Paginacao } from './despesa.model';

@Injectable({
  providedIn: 'root'
})
export class DespesaService {

  private url = `${environment.url}despesa/`

  constructor(
    private http: HttpClient
  ) { }

  inserir(despesa: Despesa) {
    return this.http.post<void>(this.url + 'inserir', despesa);
  }

  listar(filtro?: FiltroDespesa, paginacao?: Paginacao) {
    let params: any = {
      descricao: filtro?.descricao || '',
      categorias: filtro?.categorias ||  '',
      page: paginacao?.page || '',
      size: paginacao?.size || '',
    };

    if (filtro?.dataInicial) {
      params = {dataInicial: filtro.dataInicial, ...params};
    }

    if (filtro?.dataFinal) {
      params = {dataFinal: filtro.dataFinal, ...params};
    }

    return this.http.get<PageDespesa>(this.url + 'listar', {params}).pipe(map(pageDespesa => {
      pageDespesa.content.forEach(despesa => {
        despesa.valor = Number(despesa.valor).toLocaleString('pt-br', {style: 'currency', currency: 'BRL'});
        switch (despesa.cartao.numero.length) {
          case 16:
            despesa.cartao.numero = despesa.cartao.numero.replace(/(\d{4})?(\d{4})?(\d{4})?(\d{4})/, '$1 $2 $3 $4');
            break;
          case 15:
            despesa.cartao.numero = despesa.cartao.numero.replace(/(\d{4})?(\d{6})?(\d{5})/, '$1 $2 $3');
            break;
          case 14:
            despesa.cartao.numero = despesa.cartao.numero.replace(/(\d{4})?(\d{6})?(\d{4})/, '$1 $2 $3');
            break;
        }
      });
      return pageDespesa;
    }));
  }

  editar(id: number, despesa: Despesa) {
    return this.http.put<void>(this.url + `editar/${id}`, despesa);
  }

  excluir(id: number) {
    return this.http.delete<void>(this.url + `excluir/${id}`);
  }

  retornarDespesaId(id: number) {
    return this.http.get<Despesa>(this.url + id);
  }

}
