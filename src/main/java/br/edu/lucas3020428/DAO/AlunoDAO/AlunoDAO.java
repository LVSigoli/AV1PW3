package br.edu.lucas3020428.DAO.AlunoDAO;

import br.edu.lucas3020428.JPAUtil;
import br.edu.lucas3020428.aluno.Aluno;
import jakarta.persistence.EntityManager;

public class AlunoDAO {

    private final  EntityManager em = JPAUtil.getEntityManager() ;


    public void saveStudent(Aluno aluno) {

        em.getTransaction().begin();
        em.persist(aluno);
        em.getTransaction().commit();
        em.close();

    }
}
