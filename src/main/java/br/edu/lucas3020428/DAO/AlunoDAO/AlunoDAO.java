package br.edu.lucas3020428.DAO.AlunoDAO;

import br.edu.lucas3020428.Excepetions.NotFoundException;

import br.edu.lucas3020428.aluno.Aluno;
import jakarta.persistence.EntityManager;

import java.util.List;

public class AlunoDAO {

    private final EntityManager em;

    public AlunoDAO(EntityManager em) {
        this.em = em;
    }

    public void saveStudent(Aluno aluno) {
        em.getTransaction().begin();
        em.persist(aluno);
        em.getTransaction().commit();
    }

    public void deleteStudentByName(String name) throws NotFoundException {
        Aluno alunoToDelete = findStudentByName(name);

        try {
            em.getTransaction().begin();
            em.remove(alunoToDelete);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new NotFoundException("Alnuno não encontrado");
        }
    }


    public Aluno findStudentByName(String name) throws NotFoundException {
        String query = "SELECT a FROM Aluno a WHERE a.nome = :nome";

        try {
            return em.createQuery(query, Aluno.class)
                    .setParameter("nome", name)
                    .getResultList()
                    .stream()
                    .findFirst()
                    .orElse(null);
        }catch (Exception e ) {
            throw new NotFoundException("Aluno não encontrado");
        }

    }

    public List<Aluno> findAllStudents() throws Exception {
        String query = "SELECT a FROM Aluno a ORDER BY a.nome";

        try{
            return em.createQuery(query, Aluno.class).getResultList();

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateStudent(Aluno aluno) {
        em.getTransaction().begin();
        em.merge(aluno);
        em.getTransaction().commit();
    }

}
