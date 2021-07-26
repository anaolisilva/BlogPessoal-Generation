import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-menu', //Esse é o nome do meu menu pro angular. Dá pra chamar ele como seletor em qualquer parte do código. Também pode ser utilizada como tag.
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

}
