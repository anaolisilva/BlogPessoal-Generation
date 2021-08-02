import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment.prod';

@Component({
  selector: 'app-menu', //Esse é o nome do meu menu pro angular. Dá pra chamar ele como seletor em qualquer parte do código. Também pode ser utilizada como tag.
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  nome = environment.nome
  foto = environment.foto
  token = environment.token


  constructor(
    private router: Router

  ) { }

  ngOnInit(): void {
  }

  sair() {
    environment.token = ''
    environment.nome = ''
    environment.foto = ''
    environment.id = 0
    environment.usuario = ''
    environment.tipo = ''
    this.router.navigate(['/logar'])

  }
}
