package br.edu.lucas3020428.DAO.AlunoDAO;

import br.edu.lucas3020428.JPAUtil;
import br.edu.lucas3020428.aluno.Aluno;
import jakarta.persistence.EntityManager;

public class AlunoDAO {

    private final  EntityManager em ;

    public AlunoDAO(EntityManager em) {
        this.em = em;
    }

    public void saveStudent(Aluno aluno) {
        em.getTransaction().begin();
        em.persist(aluno);
        em.getTransaction().commit();

    }
}
