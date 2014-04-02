package org.devdom.quiz.util;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author Carlos Vasquez Polanco
 */
public class EntityManagerFactory{
    
    private final String PERSISTENCE_UNIT = "quiz_pu";
    private final javax.persistence.EntityManagerFactory emf;

    public EntityManagerFactory(){
        emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT, Configuration.JPAConfig());
    }

    public EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
}
