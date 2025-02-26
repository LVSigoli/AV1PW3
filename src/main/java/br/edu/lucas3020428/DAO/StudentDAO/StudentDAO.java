package br.edu.lucas3020428.DAO.StudentDAO;

import br.edu.lucas3020428.Excepetions.NotFoundException;
import br.edu.lucas3020428.student.Student;
import jakarta.persistence.EntityManager;

import java.util.List;

public class StudentDAO {

    private final EntityManager em;

    public StudentDAO(EntityManager em) {
        this.em = em;
    }

    public void saveStudent(Student student) {
        em.getTransaction().begin();
        em.persist(student);
        em.getTransaction().commit();
    }

    public void deleteStudentByName(String name) throws NotFoundException {
        Student studentToDelete = findStudentByName(name);

        try {
            em.getTransaction().begin();
            em.remove(studentToDelete);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new NotFoundException("Aluno não encontrado");
        }
    }

    public Student findStudentByName(String name) throws NotFoundException {
        String query = "SELECT s FROM Student s WHERE s.name = :name";

        try {
            return em.createQuery(query, Student.class)
                    .setParameter("name", name)
                    .getResultList()
                    .stream()
                    .findFirst()
                    .orElse(null);
        } catch (Exception e) {
            throw new NotFoundException("Aluno não encontrado");
        }
    }

    public List<Student> findAllStudents() throws Exception {
        String query = "SELECT s FROM Student s ORDER BY s.name";

        try {
            return em.createQuery(query, Student.class).getResultList();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateStudent(Student student) {
        em.getTransaction().begin();
        em.merge(student);
        em.getTransaction().commit();
    }
}
