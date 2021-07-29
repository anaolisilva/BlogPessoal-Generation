import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../model/User';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  cadastrar(user: User): Observable<User> {
    return this.http.post<User>('https://bgp-anaolisilva.herokuapp.com/usuarios/cadastrar', user)
  }
// : --> a classe em maiúscula define o tipo do objeto. O observable é pra conferir se o objeto tem todos os requisitos preenchidos e não mandar requisição pro back-end à toa. No post, colocamos <User> para indicar que é o objeto observável.

}
