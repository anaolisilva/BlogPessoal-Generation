import { Component, OnInit } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { environment } from 'src/environments/environment.prod';
import { Postagem } from '../model/Postagem';
import { Tema } from '../model/Tema';
import { User } from '../model/User';
import { AuthService } from '../service/auth.service';
import { PostagemService } from '../service/postagem.service';
import { TemaService } from '../service/tema.service';

@Component({
  selector: 'app-inicio',
  templateUrl: './inicio.component.html',
  styleUrls: ['./inicio.component.css']
})
export class InicioComponent implements OnInit {

  postagem: Postagem = new Postagem()
  listaTemas: Tema[]
  idTema: number
  tema: Tema = new Tema()
  usuario: User = new User()
  usuarioId = environment.id
  todasPostagens: Postagem[]
  postagemEdit: Postagem = new Postagem()

  constructor(
    private router: Router,
    private postagemService: PostagemService,
    private temaService: TemaService,
    private authService: AuthService
  ) { }

  ngOnInit() {
    window.scroll(0,0)

    if (environment.token == '') {
      this.router.navigate(['/logar'])
    }

    this.getAllTemas()
    this.getAllPostagens()
    this.findUsuarioById()
  }

  // Métodos de tema
  getAllTemas() {
    this.temaService.getAllTema().subscribe((resp: Tema[]) => {
      this.listaTemas = resp
      console.log(this.listaTemas)
    })
  }

  findByIdTema() {
    this.temaService.getByIdTema(this.idTema).subscribe((resp: Tema) => {
      this.tema = resp
    })
  }

  //Métodos usuário
  findUsuarioById() {
    this.authService.getUsuarioById(this.usuarioId).subscribe((resp: User) => {
      this.usuario = resp
    })
  }


  // Métodos de postagem
  getAllPostagens() {
    this.postagemService.getAllPostages().subscribe((resp: Postagem[]) => {
      this.todasPostagens = resp
      console.log(this.todasPostagens)
    })
  }

  getPostagemById(id: number) {
    this.postagemService.getPostagensById(id).subscribe((resp: Postagem) => {
      this.postagemEdit = resp
    })
  }

  publicar() {
    this.tema.id = this.idTema
    this.postagem.tema = this.tema //Coloca o tema selecionado dentro da postagem a ser postada.

    this.usuario.id = this.usuarioId
    this.postagem.usuario = this.usuario //Coloca o usuário logado (environment) na postagem

    this.postagemService.postPostagem(this.postagem).subscribe((resp: Postagem) => {
      this.postagem = resp
      alert('Postado com sucesso!')
      this.getAllPostagens()
      this.postagem = new Postagem()
    })
  }

  editarPostagem(id: number) {
    this.getPostagemById(id)
    this.postagemService.putPostagem(this.postagemEdit).subscribe((resp: Postagem) => {
      this.postagemEdit = resp
      console.log(this.postagemEdit)
      alert('Postagem alterada com sucesso.')
      this.getAllPostagens()
      this.findUsuarioById()
      this.postagemEdit = new Postagem()
    })
  }

}
