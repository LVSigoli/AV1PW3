package br.edu.lucas3020428;

import br.edu.lucas3020428.DAO.AlunoDAO.AlunoDAO;
import br.edu.lucas3020428.Excepetions.NotFoundException;
import br.edu.lucas3020428.Utils.InputUtils;
import br.edu.lucas3020428.aluno.Aluno;
import jakarta.persistence.EntityManager;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EntityManager em = JPAUtil.getEntityManager();

        int option;

        do {
            System.out.println("** CADASTRO DE ALUNOS **");

            System.out.println("1 - Cadastrar aluno");

            System.out.println("2 - Excluir aluno");

            System.out.println("3 - Alterar aluno");

            System.out.println("4 - Buscar aluno pelo nome");

            System.out.println("5 - Listar alunos (com status aprovação)");

            System.out.println("6 - FIM");

            System.out.print("Escolha uma opção: ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    cadastrarAluno(scanner, em);
                    break;
                case 2:
                    excluirAluno(scanner, em);
                    break;
                case 3:
                    alterarAluno(scanner, em);
                    break;
                case 4:
                    buscarAluno(scanner, em);
                    break;
                case 5:
                    listarAlunos(scanner, em);
                    break;
                case 6:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (option != 6);
    }

    private static void cadastrarAluno(Scanner scanner, EntityManager em) {
        System.out.println(" CADASTRO DE ALUNO");
        scanner.nextLine();

        System.out.println("Nome: ");
        String nome = scanner.nextLine();

        System.out.println("RA: ");
        String ra = scanner.nextLine();

        System.out.println("E-mail: ");
        String email = scanner.nextLine();

        BigDecimal nota1 = InputUtils.normalizeInputToBigDecimal(scanner, "Nota 1: ");
        BigDecimal nota2 = InputUtils.normalizeInputToBigDecimal(scanner, "Nota 2: ");
        BigDecimal nota3 = InputUtils.normalizeInputToBigDecimal(scanner, "Nota 3: ");

        Aluno aluno = new Aluno(nome, ra, email, nota1, nota2, nota3);


        AlunoDAO alunoDAO = new AlunoDAO(em);
        alunoDAO.saveStudent(aluno);

        System.out.println("Aluno cadastrado com sucesso!");

        System.out.println(" ");
    }

    private static void excluirAluno(Scanner scanner, EntityManager em) {
        System.out.println("EXCLUIR ALUNO: ");
        scanner.nextLine();

        System.out.println("Informe o nome do aluno: ");
        String nome = scanner.nextLine();
        AlunoDAO alunoDAO = new AlunoDAO(em);
        try {
            alunoDAO.deleteStudentByName(nome);

            System.out.println("Aluno removido com sucesso!");
        } catch (NotFoundException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (RuntimeException e) {
            System.out.println("Erro ao excluir aluno");
        }

        System.out.println(" ");
    }

    private static void alterarAluno(Scanner scanner, EntityManager em) {
        System.out.println("ALTERAR ALUNO: ");
        scanner.nextLine();

        System.out.println("Informe o nome do aluno: ");
        String nome = scanner.nextLine().trim();

        AlunoDAO alunoDAO = new AlunoDAO(em);

        try {
            Aluno alunoFound = alunoDAO.findStudentByName(nome);

            if (alunoFound == null) {
                System.out.println("Aluno não encontrado com o nome: " + nome);
                return;
            }

            System.out.println("Novos dados (deixe em branco para manter o atual)");

            System.out.println("Novo nome do aluno: ");
            String novoNome = scanner.nextLine().trim();

            if (!novoNome.isEmpty()) alunoFound.setNome(novoNome);

            System.out.println("Novo email: ");
            String novoEmail = scanner.nextLine().trim();

            if (!novoEmail.isEmpty()) alunoFound.setEmail(novoEmail);

            System.out.println("Novo RA: ");
            String novoRa = scanner.nextLine().trim();

            if (!novoRa.isEmpty()) alunoFound.setRa(novoRa);

            System.out.println("Nova Nota 1: ");
            String novaNota1 = scanner.nextLine().trim();

            if (!novaNota1.isEmpty()) alunoFound.setNota1(new BigDecimal(novaNota1));

            System.out.println("Nova Nota 2: ");
            String novaNota2 = scanner.nextLine().trim();

            if (!novaNota2.isEmpty()) alunoFound.setNota2(new BigDecimal(novaNota2));

            System.out.println("Nova Nota 3: ");
            String novaNota3 = scanner.nextLine().trim();

            if (!novaNota3.isEmpty()) alunoFound.setNota3(new BigDecimal(novaNota3));

            alunoDAO.updateStudent(alunoFound);

            System.out.println("Aluno atualizado com sucesso!");

        } catch (NotFoundException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro inesperado ao atualizar aluno ");
        }

        System.out.println(" ");
    }

    private static void buscarAluno(Scanner scanner, EntityManager em) {
        System.out.println(" ");
        System.out.println("BUSCAR ALUNO: ");
        scanner.nextLine();

        System.out.println("Informe o nome do aluno: ");
        String nome = scanner.nextLine().trim();

        try {
            AlunoDAO alunoDAO = new AlunoDAO(em);

            Aluno alunoFound = alunoDAO.findStudentByName(nome);

            if (alunoFound != null) System.out.println(alunoFound);

            else System.out.println("Aluno não encontrado com o nome: " + nome);

        } catch (NotFoundException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (RuntimeException e) {
            System.out.println("Erro inesperado ao buscar aluno");
        }

        System.out.println(" ");
    }

    private static void listarAlunos(Scanner scanner, EntityManager em) {
        try {
            AlunoDAO alunoDAO = new AlunoDAO(em);
            List<Aluno> students = alunoDAO.findAllStudents();

            if (students.isEmpty()) {
                System.out.println(" ");
                System.out.println("Nenhum aluno cadastrado");
                System.out.println(" ");
                return;
            }

            System.out.println(" ");
            System.out.println(" Exibindo todos os Alunos: ");
            System.out.println(" ");

            students.forEach(
                    student -> {
                        System.out.println(student);
                        System.out.println(" ");
                    }
            );

        } catch (RuntimeException e) {
            System.out.println("Não foi possível realizar a listagem de alunos");
        } catch (Exception e) {
            System.out.println("Não foi possível realizar a listagem de alunos");
        }

        System.out.println(" ");
    }

}

