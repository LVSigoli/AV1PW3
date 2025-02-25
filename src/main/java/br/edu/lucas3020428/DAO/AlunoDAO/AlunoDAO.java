package br.edu.lucas3020428.DAO.AlunoDAO;

import br.edu.lucas3020428.Excepetions.NotFoundException;

import br.edu.lucas3020428.aluno.Aluno;
import jakarta.persistence.EntityManager;

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
            System.out.println("Aluno removido com sucesso!");
        } catch (Exception e) {
            throw new NotFoundException("Alnuno não encontrado");
        }
    }


    private Aluno findStudentByName(String name) throws NotFoundException {
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

}
