package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

import DAO.ProfessorDAO;
import DAO.DisciplinaDAO;

import models.Aluno;
import models.Disciplina;
import models.Professor;
import models.Secretaria;
import models.abstracts.Usuario;
import models.enums.Status;

public class ProfessorController {

    private ProfessorDAO professorDAO = ProfessorDAO.getInstance();
    private DisciplinaDAO disciplinaDAO = DisciplinaDAO.getInstance();
    private static final AtomicInteger contadorProfessor = new AtomicInteger(1);
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");

    public static void setContadorProfessor(int valor) {
        contadorProfessor.set(valor);
    }

    public void menu() {

    }

    public void listarDisciplinas(Object professor) {
        List<Disciplina> disciplinas = new ArrayList<>();
        disciplinas = ((Professor) professor).getDisciplinasLecionadas();
        if (disciplinas.isEmpty()) {
            System.out.println(
                    "O professor " + ((Usuario) professor).getNome() + " não está lecionando nenhuma disciplina");
        } else {
            System.out.println("Disciplinas lecionadas por " + ((Usuario) professor).getNome() + ":");
            for (Disciplina d : disciplinas) {
                System.out.println("- " + d.getNome() + " (" + d.getCodigo() + ")");
            }
        }
    }

    // pode ser que dê erro
    // public void listarAlunos(String codigo) {
    //     Disciplina disciplina;
    //     try {
    //         disciplina = disciplinaDAO.buscarPorCodigo(codigo);
    //     } catch (Exception e) {
    //         System.out.println("Disciplina não encontrada!");
    //         return;
    //     }
    //     List<Aluno> alunos = new ArrayList<>();
    //     alunos = disciplina.getAlunosMatriculados();
    //     if (alunos.isEmpty()) {
    //         System.out.println("Nenhum aluno matriculado na disciplina " + disciplina.getNome());
    //     } else {
    //         System.out.println("Alunos matriculados na disciplina " + disciplina.getNome() + ":");
    //         for (Aluno a : alunos) {
    //             System.out.println("- " + a.getNome() + " (" + a.getMatricula() + ")");
    //         }
    //     }
    // }

    
    public void listarAlunos(String codigo) {
        Optional<Disciplina> disciplinaOpt = disciplinaDAO.buscarPorCodigo(codigo);

        if (disciplinaOpt.isEmpty()) {
            System.out.println("❌ Disciplina não encontrada!");
            return;
        }

        Disciplina disciplina = disciplinaOpt.get();
        List<Aluno> alunos = disciplina.getAlunosMatriculados();

        if (alunos.isEmpty()) {
            System.out.println("📌 Nenhum aluno matriculado na disciplina " + disciplina.getNome());
        } else {
            System.out.println("\n📌 Alunos matriculados na disciplina " + disciplina.getNome() + ":");
            for (Aluno aluno : alunos) {
                System.out.println("- " + aluno.getNome() + " (" + aluno.getMatricula() + ")");
            }
        }
    }
    
    // ============================
    public boolean adicionarProfessor(String nome, String email, String senha) {
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            System.out.println("❌ Erro: E-mail inválido.");
            return false;
        }

        if (professorDAO.buscarPorEmail(email).isPresent()) {
            System.out.println("❌ Erro: Já existe um professor com esse e-mail.");
            return false;
        }

        String id = gerarId();
        Professor novoProfessor = new Professor(id, nome, email, senha);
        professorDAO.adicionarProfessor(novoProfessor);
        return true;
    }

    private String gerarId() {
        return "PRO-" + String.format("%04d", contadorProfessor.getAndIncrement());
    }

    public boolean editarProfessor(String id, String novoNome, String novoEmail, String novaSenha) {
        Optional<Professor> professorOpt = professorDAO.buscarPorId(id);
        if (professorOpt.isPresent()) {
            Professor professor = professorOpt.get();
    
            // Validar e-mail apenas se ele for alterado
            if (!novoEmail.isEmpty()) {
                if (!EMAIL_PATTERN.matcher(novoEmail).matches()) {
                    System.out.println("❌ Erro: E-mail inválido.");
                    return false;
                }
                professor.setEmail(novoEmail);
            }
    
            if (!novoNome.isEmpty()) professor.setNome(novoNome);
            if (!novaSenha.isEmpty()) professor.setSenha(novaSenha);
    
            professorDAO.atualizarProfessor(professor);
            return true;
        }
        return false;
    }

    public boolean alterarStatusProfessor(int index) {
        List<Professor> professores = professorDAO.listarProfessores();
        if (index < 1 || index > professores.size()) {
            System.out.println("❌ Erro: Índice inválido.");
            return false;
        }

        Professor professor = professores.get(index - 1);
        professor.setStatus(professor.getStatus() == Status.ATIVO ? Status.INATIVO : Status.ATIVO);
        professorDAO.atualizarProfessor(professor);
        return true;
    }

    public List<Professor> listarProfessoresAtivos() {
        return professorDAO.listarProfessores().stream()
                .filter(professor -> professor.getStatus() == Status.ATIVO)
                .toList();
    }

    public void listarProfessores() {
        List<Professor> professores = professorDAO.listarProfessores();
        if (professores.isEmpty()) {
            System.out.println("📌 Nenhum professor cadastrado.");
        } else {
            System.out.println("\n=== Lista de Professores ===");
            System.out.println(" Nº | ID        | Nome                | E-mail               | Status ");
            System.out.println("---------------------------------------------------------------");
            int i = 1;
            for (Professor professor : professores) {
                System.out.printf(" %2d | %-8s | %-20s | %-20s | %s \n",
                        i, professor.getId(), professor.getNome(), professor.getEmail(), professor.getStatus());
                i++;
            }
        }
    }

    public Professor selecionarProfessor(int index) {
        List<Professor> professores = professorDAO.listarProfessores();
        if (index < 1 || index > professores.size()) {
            return null;
        }
        return professores.get(index - 1);
    }

    public void visualizarProfessor(String id) {
        Optional<Professor> professorOpt = professorDAO.buscarPorId(id);
        if (professorOpt.isPresent()) {
            Professor professor = professorOpt.get();
            System.out.println("\n=== Detalhes do Professor ===");
            System.out.println("ID: " + professor.getId());
            System.out.println("Nome: " + professor.getNome());
            System.out.println("E-mail: " + professor.getEmail());
            System.out.println("Status: " + professor.getStatus());

            List<Disciplina> disciplinas = disciplinaDAO.listarDisciplinas();
            List<Disciplina> disciplinasDoProfessor = disciplinas.stream()
                    .filter(d -> d.getProfessor() != null && d.getProfessor().getId().equals(id))
                    .toList();

            if (disciplinasDoProfessor.isEmpty()) {
                System.out.println("📌 Este professor não leciona nenhuma disciplina.");
            } else {
                System.out.println("📌 Disciplinas Lecionadas:");
                disciplinasDoProfessor
                        .forEach(d -> System.out.println(" - " + d.getNome() + " (" + d.getCodigo() + ")"));
            }
        } else {
            System.out.println("❌ Professor não encontrado.");
        }
    }
}
