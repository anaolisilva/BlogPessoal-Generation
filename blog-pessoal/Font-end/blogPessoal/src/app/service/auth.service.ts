import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.prod';
import { User } from '../model/User';
import { UserLogin } from '../model/UserLogin';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  tokenUser = {
    headers: new HttpHeaders().set('Authorization',environment.token)
  }



  cadastrar(user: User): Observable<User> {
    return this.http.post<User>('https://bgp-anaolisilva.herokuapp.com/usuarios/cadastrar', user)
  }

  logar (usuario: UserLogin): Observable<UserLogin> {
    return this.http.post<UserLogin>('https://bgp-anaolisilva.herokuapp.com/usuarios/logar', usuario)

  }
// : --> a classe em maiúscula define o tipo do objeto. O observable é pra conferir se o objeto tem todos os requisitos preenchidos e não mandar requisição pro back-end à toa. No post, colocamos <User> para indicar que é o objeto observável.


getUsuarioById(id: number): Observable<User>{
  return this.http.get<User>(`https://bgp-anaolisilva.herokuapp.com/usuarios/${id}`, this.tokenUser)

}

  logado() {
    let ok: boolean = false
    if(environment.token != '') {
      ok = true
    }
    return ok
  }

}
