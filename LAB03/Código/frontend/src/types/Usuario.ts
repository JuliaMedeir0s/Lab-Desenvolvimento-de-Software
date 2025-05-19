export type TipoUsuario = "aluno" | "professor" | "parceiro";

export interface Usuario {
  id: number;
  nome: string;
  email: string;
  tipoUsuario: TipoUsuario;
}
