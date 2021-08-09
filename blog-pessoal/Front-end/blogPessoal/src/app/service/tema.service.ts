import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.prod';
import { Tema } from '../model/Tema';

@Injectable({
  providedIn: 'root'
})
export class TemaService {

  constructor(private http: HttpClient) { }

// Pra criar essa variável, chamamos outra função. Dessa forma, o token já é criado "com endereço". Ele já é definido como uma variável que vai no cabeçalho, na parte de Authorization.


  tokenvalid = {
    headers: new HttpHeaders().set('Authorization',environment.token)
  }

// traz um array de objetos tema, pois é um getAll.
  getAllTema(): Observable<Tema[]> {
    return this.http.get<Tema[]>('https://bgp-anaolisilva.herokuapp.com/temas', this.tokenvalid)
  }

  getByIdTema(id: number): Observable<Tema> {
    return this.http.get<Tema>(`https://bgp-anaolisilva.herokuapp.com/temas/${id}`, this.tokenvalid)
  }

  postTema(tema: Tema): Observable<Tema> {
    return this.http.post<Tema>('https://bgp-anaolisilva.herokuapp.com/temas/novo', tema, this.tokenvalid)
  }

  putTema(tema: Tema): Observable<Tema> {
    return this.http.put<Tema>('https://bgp-anaolisilva.herokuapp.com/temas', tema, this.tokenvalid)
  }

  deleteTema(id: number){
    return this.http.delete(`https://bgp-anaolisilva.herokuapp.com/temas/${id}`, this.tokenvalid)
  }

}
