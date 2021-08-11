import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment.prod';
import { UserLogin } from '../model/UserLogin';
import { AuthService } from '../service/auth.service';

@Component({
  selector: 'app-logar',
  templateUrl: './logar.component.html',
  styleUrls: ['./logar.component.css']
})
export class LogarComponent implements OnInit {

  usuarioLogin: UserLogin = new UserLogin()

  constructor(
    private auth: AuthService,
    private router: Router
  ) { }

  ngOnInit() {
    window.scroll(0,0)
  }

  logar() {
    this.auth.logar(this.usuarioLogin).subscribe((resp: UserLogin) => {
      this.usuarioLogin = resp
      environment.token = this.usuarioLogin.token
      environment.nome = this.usuarioLogin.nome
      environment.foto = this.usuarioLogin.foto
      environment.id = this.usuarioLogin.id
      environment.usuario = this.usuarioLogin.usuario
      environment.tipo = this.usuarioLogin.tipo


      this.router.navigate(['/home'])

    }, erro => {
      if(erro.status == 500) {
        alert('Usuário ou senha incorretos!')
      }
    })
  }

}
