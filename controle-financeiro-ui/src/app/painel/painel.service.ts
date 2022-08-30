import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

import { Painel } from './painel.model';

@Injectable({
  providedIn: 'root'
})
export class PainelService {

  private url = `${environment.url}painel/`

  constructor(
    private http: HttpClient,
  ) { }

  painelControleFinanceiro(data?: string) {
    const params = {
      data: data || ''
    };
    return this.http.get<Painel>(this.url, {params});
  }
}
