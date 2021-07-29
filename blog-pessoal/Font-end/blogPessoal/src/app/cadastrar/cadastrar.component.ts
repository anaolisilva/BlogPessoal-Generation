import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../model/User';
import { AuthService } from '../service/auth.service';

@Component({
  selector: 'app-cadastrar',
  templateUrl: './cadastrar.component.html',
  styleUrls: ['./cadastrar.component.css']
})
export class CadastrarComponent implements OnInit {

  usuario: User = new User
  confSenha: string
  tipoUsuario: string

  constructor(
    private authService: AuthService,
    private router: Router
    ) { } //Injeção de dependências

  ngOnInit() {
    window.scroll(0,0)
  }

  confirmarSenha(event: any) {
    this.confSenha = event.target.value

  }

  //any é um tipo do typescript?
  defineTipoUsuario(event: any) {
    this.tipoUsuario = event.target.value
  }

  cadastrar() {
    this.usuario.tipo = this.tipoUsuario

    if (this.usuario.senha == this.confSenha) {
      this.authService.cadastrar(this.usuario).subscribe((resp: User) => {
        this.usuario = resp
        this.router.navigate(['/logar']) //Redireciona o usuário para a rota de entrar.
        alert('Usuário cadastrado com sucesso!')
      })
      //subscribe transforma objeto typescript num json, para a requisição entender.
    }else {
      alert('Senhas não batem')
    }

  }

}
