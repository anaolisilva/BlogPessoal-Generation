import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from 'src/app/model/User';
import { AuthService } from 'src/app/service/auth.service';
import { environment } from 'src/environments/environment.prod';

@Component({
  selector: 'app-user-edit',
  templateUrl: './user-edit.component.html',
  styleUrls: ['./user-edit.component.css']
})
export class UserEditComponent implements OnInit {

  usuario: User = new User()
  idUser: number
  confSenha: string
  tipoUsuario: string

  constructor(
    private authService: AuthService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit() {

    window.scroll(0, 0)

    if (environment.token == '') {
      this.router.navigate(['/logar'])
    }

    this.idUser = this.route.snapshot.params['id']
    this.findByIdUsuario(this.idUser)

  }

  confirmarSenha(event: any) {
    this.confSenha = event.target.value

  }

  defineTipoUsuario(event: any) {
    this.tipoUsuario = event.target.value
  }

  atualizar() {
    this.usuario.tipo = this.tipoUsuario
    if (this.usuario.senha == this.confSenha) {
      this.authService.atualizarUsuario(this.usuario).subscribe((resp: User) => {
        this.usuario = resp
        alert('Usuário atualizado com sucesso. Faça login novamente.')
        environment.token = ''
        environment.nome = ''
        environment.foto = ''
        environment.id = 0
        environment.usuario = ''
        environment.tipo = ''
        this.router.navigate(['/logar'])
      })

    }
    else {
      alert('Senhas não batem!')
    }

  }

  findByIdUsuario(id: number) {
    this.authService.getUsuarioById(id).subscribe((resp: User) => {
      this.usuario = resp
    })
  }

}
