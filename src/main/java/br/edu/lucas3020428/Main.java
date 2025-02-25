package br.edu.lucas3020428;

import br.edu.lucas3020428.DAO.AlunoDAO.AlunoDAO;
import br.edu.lucas3020428.Utils.InputUtils;
import br.edu.lucas3020428.aluno.Aluno;
import jakarta.persistence.EntityManager;

import java.math.BigDecimal;
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
                    excluirAluno();
                    break;
                case 3:
                    alterarAluno();
                    break;
                case 4:
                    buscarAluno();
                    break;
                case 5:
                    listarAlunos();
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
        System.out.println("----------------------------------------------------------------");
        System.out.println(" ");
    }


    private static void excluirAluno() {
        System.out.println("EXCLUIR ALUNO: ");


        System.out.println("----------------------------------------------------------------");
        System.out.println(" ");
    }

    private static void alterarAluno() {
        System.out.println("** ALTERAR ALUNO **");

        System.out.println("----------------------------------------------------------------");
        System.out.println(" ");
    }

    private static void buscarAluno() {
        System.out.println("** BUSCAR ALUNO **");

        System.out.println("----------------------------------------------------------------");
        System.out.println(" ");
    }

    private static void listarAlunos() {
        System.out.println("** LISTAR ALUNOS **");

        System.out.println("----------------------------------------------------------------");
        System.out.println(" ");
    }


}

