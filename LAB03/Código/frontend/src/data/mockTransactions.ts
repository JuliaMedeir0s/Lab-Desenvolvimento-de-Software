import { Transaction } from "../types";

export const mockTransactions: Transaction[] = [
  {
    id: "1",
    date: "2025-05-22T14:30:00",
    studentName: "Ana Silva",
    amount: 20,
    reason:
      "Participação excelente na apresentação do projeto final de Desenvolvimento Web",
  },
  {
    id: "2",
    date: "2025-05-20T10:15:00",
    studentName: "João Oliveira",
    amount: 15,
    reason: "Resolução criativa do problema proposto em sala de aula",
  },
  {
    id: "3",
    date: "2025-05-18T09:45:00",
    studentName: "Mariana Costa",
    amount: 10,
    reason: "Ajuda aos colegas durante a atividade em grupo",
  },
  {
    id: "4",
    date: "2025-05-15T13:20:00",
    studentName: "Pedro Santos",
    amount: 25,
    reason: "Nota máxima na avaliação de Algoritmos e Estruturas de Dados",
  },
  {
    id: "5",
    date: "2025-05-10T15:40:00",
    studentName: "Camila Rodrigues",
    amount: 15,
    reason: "Participação ativa durante a aula de Inteligência Artificial",
  },
];
