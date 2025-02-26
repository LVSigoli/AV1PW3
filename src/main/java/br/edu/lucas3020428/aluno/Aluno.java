package br.edu.lucas3020428.aluno;

// External Libraries

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
@Table(name = "alunos")

public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String ra;
    private String email;
    private BigDecimal nota1;
    private BigDecimal nota2;
    private BigDecimal nota3;


    public Aluno(String nome, String ra, String email, BigDecimal nota1, BigDecimal nota2, BigDecimal nota3) {
        this.nome = nome;
        this.ra = ra;
        this.email = email;
        this.nota1 = nota1;
        this.nota2 = nota2;
        this.nota3 = nota3;
    }

    public Aluno() {
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRa() {
        return ra;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getNota1() {
        return nota1;
    }

    public void setNota1(BigDecimal nota1) {
        this.nota1 = nota1;
    }

    public BigDecimal getNota2() {
        return nota2;
    }

    public void setNota2(BigDecimal nota2) {
        this.nota2 = nota2;
    }

    public BigDecimal getNota3() {
        return nota3;
    }

    public void setNota3(BigDecimal nota3) {
        this.nota3 = nota3;
    }

    @Override
    public String toString() {
        return "Dados do aluno: \n" +
                "Nome: " + nome + "\n" +
                "RA: " + ra + "\n" +
                "Email: " + email + "\n" +
                "Notas: " + nota1 + " - " + nota2 + " - " + nota3 + "\n";
    }

    public String toStringWithStatus() {
        BigDecimal media = getMedia();
        String status = getStatus(media);

        return "Nome: " + nome + "\n" +
                "RA: " + ra + "\n" +
                "Email: " + email + "\n" +
                "Notas: " + nota1 + " - " + nota2 + " - " + nota3 + "\n" +
                "Média: " + media + "\n" +
                "Status: " + status;
    }

    private BigDecimal getMedia() {
        BigDecimal sum = nota1.add(nota2.add(nota3));
        return sum.divide(BigDecimal.valueOf(3), 2, RoundingMode.HALF_UP);
    }

    private String getStatus(BigDecimal media) {
        if (media.compareTo(BigDecimal.valueOf(7)) >= 0) return "Aprovado";

        if (media.compareTo(BigDecimal.valueOf(4)) >= 0) return "Recuperação";

        return "Reprovado";
    }
}
