import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Usuario } from './usuario.model';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  private url = `${environment.url}usuario/`

  constructor(
    private http: HttpClient)
  { }

  inserir(usuario: Usuario) {
    return this.http.post<number>(this.url + 'inserir', usuario);
  }

  inserirFoto(foto: File, id: number) {
    const file = new FormData();
    file.append('file', foto, foto.name);
    return this.http.post<void>(this.url + `inserir/${id}`, file);
  }

  editar(id: number, usuario: Usuario) {
    return this.http.put<void>(this.url + `editar/${id}`, usuario);
  }

  retornarUsuarioId(id: number) {
    return this.http.get<Usuario>(this.url + `${id}`);
  }
}
