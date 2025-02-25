package br.edu.lucas3020428;

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
//                            cadastrarAluno( scanner);
                            break;
                        case 2:
//                            excluirAluno( scanner);
                            break;
                        case 3:
//                            alterarAluno( scanner);
                            break;
                        case 4:
//                            buscarAluno( scanner);
                            break;
                        case 5:
//                            listarAlunos(em);
                            break;
                        case 6:
                            System.out.println("Saindo...");
                            break;
                        default:
                            System.out.println("Opção inválida!");
                    }
                } while (option != 6);
            }

            private static void cadastrarAluno() {}

            private static void excluirAluno() {}

            private static void alterarAluno() {}

            private static void buscarAluno() {}

            private static void listarAlunos() {}


        }

