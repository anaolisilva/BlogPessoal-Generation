import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.prod';
import { Tema } from '../model/Tema';
import { TemaService } from '../service/tema.service';

@Component({
  selector: 'app-tema',
  templateUrl: './tema.component.html',
  styleUrls: ['./tema.component.css']
})
export class TemaComponent implements OnInit {

  tema: Tema = new Tema()
  listaTemas: Tema[]
  temaEdit: Tema = new Tema()
  temaDelete: Tema = new Tema()


  constructor(
    private router: Router,
    private temaService: TemaService,
    private route: ActivatedRoute
  ) { }

  ngOnInit() {
    if (environment.token == '') {
      this.router.navigate(['/logar'])
    }

    this.getAllTemas()
    // pega todos os temas sempre que iniciar a página.
  }

  getAllTemas() {
    this.temaService.getAllTema().subscribe((resp: Tema[]) => {
      this.listaTemas = resp
      console.log(this.listaTemas)
    })
  }

  findByIdTemaEdit(id:number){
    this.temaService.getByIdTema(id).subscribe((resp: Tema) => {
      this.temaEdit = resp
    })
  }

  novoTema() {
    this.temaService.postTema(this.tema).subscribe((resp: Tema)=> {
      this.tema = resp
      // Aqui o objeto tema recebe o resp porque o resp se tornou Json com o subscribe. O subscribe faz tanto o Json -> TypeScript quando o inverso, dependendo do que estamos fazendo na própria função.
      // A função arrow retorna para o início dela mesma (no caso acima, está no subscribe.)
      console.log(this.tema)
      alert('Novo tema cadastrado com sucesso.')
      this.getAllTemas()
      this.tema = new Tema()
      // reseta o tema para outro cadastro.
    })
  }

  atualizarTema(id:number){
    this.findByIdTemaEdit(id)
    this.temaService.putTema(this.temaEdit).subscribe((resp: Tema)=> {
      this.temaEdit = resp
      console.log(this.temaEdit)
      alert('Tema alterado com sucesso.')
      this.getAllTemas()
      this.temaEdit = new Tema()
    })
  }

  apagarTema(id:number){
    this.temaService.deleteTema(id).subscribe(()=>{
      // resposta vazia porque o delete não tem um objeto.
      alert('Tema apagado com sucesso!')
      this.router.navigate(['/tema'])
    })
  }

}
