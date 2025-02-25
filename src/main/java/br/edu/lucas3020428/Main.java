package br.edu.lucas3020428;

import br.edu.lucas3020428.aluno.Aluno;

import java.math.BigDecimal;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
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
                    cadastrarAluno(scanner);
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

    private static void cadastrarAluno(Scanner scanner) {
        System.out.println(" CADASTRO DE ALUNO");

        System.out.print("Nome: ");
        String nome = scanner.next();

        System.out.print("RA: ");
        String ra = scanner.next();

        System.out.print("E-mail: ");
        String email = scanner.next();

        BigDecimal nota1 = normalizeInputToBigDecimal(scanner, "Nota 1: ");
        BigDecimal nota2 = normalizeInputToBigDecimal(scanner, "Nota 2: ");
        BigDecimal nota3 = normalizeInputToBigDecimal(scanner, "Nota 3: ");

        Aluno aluno = new Aluno(nome, ra, email, nota1, nota2, nota3);

        System.out.println("Aluno cadastrado com sucesso!");
        System.out.println("//////////////////////////////////");
    }


    private static BigDecimal normalizeInputToBigDecimal(Scanner scanner, String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                String entrada = scanner.next().replace(",", ".");

                return new BigDecimal(entrada);

            } catch (NumberFormatException e) {
                System.out.println("Erro: Digite um número válido!");
            }
        }
    }

    private static void excluirAluno() {
        System.out.println("** EXCLUIR ALUNO **");

        System.out.println("//////////////////////////////////");

    }

    private static void alterarAluno() {
        System.out.println("** ALTERAR ALUNO **");

        System.out.println("//////////////////////////////////");

    }

    private static void buscarAluno() {
        System.out.println("** BUSCAR ALUNO **");

        System.out.println("//////////////////////////////////");

    }

    private static void listarAlunos() {
        System.out.println("** LISTAR ALUNOS **");

        System.out.println("//////////////////////////////////");

    }


}

