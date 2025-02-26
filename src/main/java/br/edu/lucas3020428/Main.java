package br.edu.lucas3020428;

import br.edu.lucas3020428.DAO.StudentDAO.StudentDAO;
import br.edu.lucas3020428.Excepetions.NotFoundException;
import br.edu.lucas3020428.Utils.InputUtils;
import br.edu.lucas3020428.student.Student;
import jakarta.persistence.EntityManager;

import java.math.BigDecimal;
import java.util.InputMismatchException;
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
            option = getOption(scanner);

            switch (option) {
                case 1:
                    registerStudent(scanner, em);
                    break;
                case 2:
                    deleteStudent(scanner, em);
                    break;
                case 3:
                    updateStudent(scanner, em);
                    break;
                case 4:
                    searchStudent(scanner, em);
                    break;
                case 5:
                    listStudents(scanner, em);
                    break;
                case 6:
                    closeTransaction(em);
                    System.out.println("Saindo...");
                    break;
                default:
                    break;
            }
        } while (option != 6);
    }

    private static int getOption(Scanner scanner) {
        int option;
        while (true) {
            try {
                option = scanner.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Por favor, informe um valor inteiro válido.");
                scanner.nextLine();
            }
        }
        return option;
    }

    private static void registerStudent(Scanner scanner, EntityManager em) {
        System.out.println(" CADASTRO DE ALUNO");
        scanner.nextLine();

        System.out.println("Nome: ");
        String name = scanner.nextLine();

        System.out.println("RA: ");
        String ra = scanner.nextLine();

        System.out.println("E-mail: ");
        String email = scanner.nextLine();

        BigDecimal grade1 = InputUtils.normalizeInputToBigDecimal(scanner, "Nota 1: ");
        BigDecimal grade2 = InputUtils.normalizeInputToBigDecimal(scanner, "Nota 2: ");
        BigDecimal grade3 = InputUtils.normalizeInputToBigDecimal(scanner, "Nota 3: ");

        Student student = new Student(name, ra, email, grade1, grade2, grade3);

        StudentDAO studentDAO = new StudentDAO(em);
        studentDAO.saveStudent(student);

        System.out.println("Aluno cadastrado com sucesso!");
        System.out.println(" ");
    }

    private static void deleteStudent(Scanner scanner, EntityManager em) {
        System.out.println("EXCLUIR ALUNO: ");
        scanner.nextLine();

        System.out.println("Informe o nome do aluno: ");
        String name = scanner.nextLine();
        StudentDAO studentDAO = new StudentDAO(em);
        try {
            studentDAO.deleteStudentByName(name);
            System.out.println("Aluno removido com sucesso!");
        } catch (NotFoundException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (RuntimeException e) {
            System.out.println("Erro ao excluir aluno");
        }
        System.out.println(" ");
    }

    private static void updateStudent(Scanner scanner, EntityManager em) {
        System.out.println("ALTERAR ALUNO: ");
        scanner.nextLine();

        System.out.println("Informe o nome do aluno: ");
        String name = scanner.nextLine().trim();

        StudentDAO studentDAO = new StudentDAO(em);

        try {
            Student foundStudent = studentDAO.findStudentByName(name);

            if (foundStudent == null) {
                System.out.println("Aluno não encontrado com o nome: " + name);
                return;
            }

            System.out.println("Novos dados (deixe em branco para manter o atual)");

            System.out.println("Novo nome do aluno: ");
            String newName = scanner.nextLine().trim();
            if (!newName.isEmpty()) foundStudent.setName(newName);

            System.out.println("Novo email: ");
            String newEmail = scanner.nextLine().trim();
            if (!newEmail.isEmpty()) foundStudent.setEmail(newEmail);

            System.out.println("Novo RA: ");
            String newRa = scanner.nextLine().trim();
            if (!newRa.isEmpty()) foundStudent.setRegistrationNumber(newRa);

            System.out.println("Nova Nota 1: ");
            String newGrade1 = scanner.nextLine().trim();
            if (!newGrade1.isEmpty()) foundStudent.setGrade1(new BigDecimal(newGrade1));

            System.out.println("Nova Nota 2: ");
            String newGrade2 = scanner.nextLine().trim();
            if (!newGrade2.isEmpty()) foundStudent.setGrade2(new BigDecimal(newGrade2));

            System.out.println("Nova Nota 3: ");
            String newGrade3 = scanner.nextLine().trim();
            if (!newGrade3.isEmpty()) foundStudent.setGrade3(new BigDecimal(newGrade3));

            studentDAO.updateStudent(foundStudent);

            System.out.println("Aluno atualizado com sucesso!");

        } catch (NotFoundException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro inesperado ao atualizar aluno "+ e.getMessage());
        }
        System.out.println(" ");
    }

    private static void searchStudent(Scanner scanner, EntityManager em) {
        System.out.println(" ");
        System.out.println("BUSCAR ALUNO: ");
        scanner.nextLine();

        System.out.println("Informe o nome do aluno: ");
        String name = scanner.nextLine().trim();

        try {
            StudentDAO studentDAO = new StudentDAO(em);
            Student foundStudent = studentDAO.findStudentByName(name);

            if (foundStudent != null) System.out.println(foundStudent);
            else System.out.println("Aluno não encontrado com o nome: " + name);

        } catch (NotFoundException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (RuntimeException e) {
            System.out.println("Erro inesperado ao buscar aluno");
        }
        System.out.println(" ");
    }

    private static void listStudents(Scanner scanner, EntityManager em) {
        try {
            StudentDAO studentDAO = new StudentDAO(em);
            List<Student> students = studentDAO.findAllStudents();

            if (students.isEmpty()) {
                System.out.println(" ");
                System.out.println("Nenhum aluno cadastrado");
                System.out.println(" ");
                return;
            }

            System.out.println(" ");
            System.out.println(" Exibindo todos os Alunos: ");
            System.out.println(" ");

            students.forEach(student -> {
                System.out.println(student.toStringWithStatus());
                System.out.println(" ");
            });
        } catch (Exception e) {
            System.out.println("Não foi possível realizar a listagem de alunos");
        }
        System.out.println(" ");
    }

    private static void closeTransaction( EntityManager em) {
        em.close();
    }
}
