export type TipoTransacao = "envio" | "resgate";

export interface Transacao {
  id: number;
  tipo: TipoTransacao;
  valor: number;
  mensagem?: string;
  codigo?: string;
  data: string;
  alunoId: number;
  professorId?: number;
  vantagemId?: number;
}
