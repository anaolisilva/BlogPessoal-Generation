import { Postagem } from "./Postagem"

export class User {
  public id: number
  public nome: string
// usuario, email, foto, tipo, senha --> conferur com back.
  public postagem: Postagem[]
}

