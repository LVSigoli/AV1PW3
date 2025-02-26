package br.edu.lucas3020428.student;

// External Libraries

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String registrationNumber;
    private String email;
    private BigDecimal grade1;
    private BigDecimal grade2;
    private BigDecimal grade3;

    public Student(String name, String registrationNumber, String email, BigDecimal grade1, BigDecimal grade2, BigDecimal grade3) {
        this.name = name;
        this.registrationNumber = registrationNumber;
        this.email = email;
        this.grade1 = grade1;
        this.grade2 = grade2;
        this.grade3 = grade3;
    }

    public Student() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getGrade1() {
        return grade1;
    }

    public void setGrade1(BigDecimal grade1) {
        this.grade1 = grade1;
    }

    public BigDecimal getGrade2() {
        return grade2;
    }

    public void setGrade2(BigDecimal grade2) {
        this.grade2 = grade2;
    }

    public BigDecimal getGrade3() {
        return grade3;
    }

    public void setGrade3(BigDecimal grade3) {
        this.grade3 = grade3;
    }

    @Override
    public String toString() {
        return "Dados do Aluno: \n" +
                "Nome: " + name + "\n" +
                "RA: " + registrationNumber + "\n" +
                "E-mail: " + email + "\n" +
                "Notas: " + grade1 + " - " + grade2 + " - " + grade3 + "\n";
    }

    public String toStringWithStatus() {
        BigDecimal average = getAverage();
        String status = getStatus(average);

        return "Nome: " + name + "\n" +
                "RA: " + registrationNumber + "\n" +
                "E-mail: " + email + "\n" +
                "Notas: " + grade1 + " - " + grade2 + " - " + grade3 + "\n" +
                "Media: " + average + "\n" +
                "Situação: " + status;
    }

    private BigDecimal getAverage() {
        BigDecimal sum = grade1.add(grade2.add(grade3));
        return sum.divide(BigDecimal.valueOf(3), 2, RoundingMode.HALF_UP);
    }

    private String getStatus(BigDecimal media) {
        if (media.compareTo(BigDecimal.valueOf(7)) >= 0) return "Aprovado";

        if (media.compareTo(BigDecimal.valueOf(4)) >= 0) return "Recuperação";

        return "Reprovado";
    }
}
