import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CadastrarComponent } from './cadastrar/cadastrar.component';
import { PostagemDeleteComponent } from './delete/postagem-delete/postagem-delete.component';
import { TemaDeleteComponent } from './delete/tema-delete/tema-delete.component';
import { UserEditComponent } from './edit/user-edit/user-edit.component';
import { InicioComponent } from './inicio/inicio.component';
import { LogarComponent } from './logar/logar.component';
import { TemaComponent } from './tema/tema.component';

const routes: Routes = [
  {path:'', redirectTo:'logar', pathMatch:'full'},
  {path:'logar', component: LogarComponent},
  {path:'cadastrar', component: CadastrarComponent},
  {path:'home', component: InicioComponent},
  {path: 'tema', component: TemaComponent},
  {path: 'tema-del/:id', component: TemaDeleteComponent},
  {path: 'postagem-del/:id', component: PostagemDeleteComponent},
  {path: 'user-edit/:id', component: UserEditComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
