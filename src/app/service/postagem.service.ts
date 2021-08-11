import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.prod';
import { Postagem } from '../model/Postagem';

@Injectable({
  providedIn: 'root'
})
export class PostagemService {

  constructor(private http: HttpClient) { }

  token = {
    headers: new HttpHeaders().set('Authorization',environment.token)
  }

  getAllPostages(): Observable<Postagem[]>{
    return this.http.get<Postagem[]>('https://bgp-anaolisilva.herokuapp.com/postagens', this.token)
  }

  getPostagensById(id: number): Observable<Postagem>{
  return this.http.get<Postagem>(`https://bgp-anaolisilva.herokuapp.com/postagens/${id}`, this.token)
  }

  getPostagensByTitulo(titulo: string): Observable<Postagem[]> {
    return this.http.get<Postagem[]>(`https://bgp-anaolisilva.herokuapp.com/postagens/titulo/${titulo}`, this.token)
  }

  postPostagem(postagem: Postagem): Observable<Postagem> {
    return this.http.post<Postagem>('https://bgp-anaolisilva.herokuapp.com/postagens', postagem, this.token)
  }

  putPostagem(postagem: Postagem): Observable<Postagem> {
    return this.http.put<Postagem>('https://bgp-anaolisilva.herokuapp.com/postagens', postagem, this.token)
  }

  curtir(id:number): Observable<Postagem> {
    return this.http.put<Postagem>(`https://bgp-anaolisilva.herokuapp.com/postagens/curtir/${id}`, this.token)
  }

  descurtir(id:number): Observable<Postagem> {
    return this.http.put<Postagem>(`https://bgp-anaolisilva.herokuapp.com/postagens/descurtir/${id}`, this.token)
  }

  deletePostagem(id: number) {
    return this.http.delete(`https://bgp-anaolisilva.herokuapp.com/postagens/${id}`, this.token)
  }

}
