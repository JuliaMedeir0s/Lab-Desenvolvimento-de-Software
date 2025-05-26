export interface LoginFormData {
  email: string;
  password: string;
}

export interface StudentRegistrationData {
  nome: string;
  email: string;
  senha: string;
  cpf: string;
  rg: string;
  instituicao: string;
  rua: string;
  numero: string;
  cidade: string;
  uf: string;
  cep: string;
}

export interface PartnerRegistrationData {
  nome: string;
  email: string;
  senha: string;
  cnpj?: string;
}

export interface Reward {
  id: string;
  name: string;
  description: string;
  coinValue: number;
  imageUrl?: string;
}

export interface Student {
  id: string;
  name: string;
  email: string;
  course: string;
}

export interface Transaction {
  id: string;
  date: string;
  studentName: string;
  amount: number;
  reason: string;
}