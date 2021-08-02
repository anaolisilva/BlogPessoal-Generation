import { Component } from '@angular/core';
import { AuthService } from './service/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'blogPessoal';

  constructor(
    public auth: AuthService
  ){}
  //Injeta a dependência Auth pra conseguirmos chamá-la lá no app.components.html. Cria um objeto auth do tipo AuthService, que vai puxar os métodos que construímos em auth.service.ts
}
