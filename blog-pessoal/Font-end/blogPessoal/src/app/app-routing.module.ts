import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CadastrarComponent } from './cadastrar/cadastrar.component';
import { InicioComponent } from './inicio/inicio.component';
import { LogarComponent } from './logar/logar.component';

const routes: Routes = [
  {path:'', redirectTo:'logar', pathMatch:'full'},
  {path:'logar', component: LogarComponent},
  {path:'cadastrar', component: CadastrarComponent},
  {path:'home', component: InicioComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
