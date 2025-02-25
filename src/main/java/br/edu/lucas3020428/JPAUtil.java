package br.edu.lucas3020428;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {

    private static final EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("alunos");


    public static EntityManager getEntityManager() {
        return FACTORY.createEntityManager();
    }


}
