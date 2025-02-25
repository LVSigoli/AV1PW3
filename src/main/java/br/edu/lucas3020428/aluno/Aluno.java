package br.edu.lucas3020428.aluno;

// External Libraries
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name="alunos")
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
}
