package org.devdom.quiz.model.dao;

import javax.persistence.EntityManager;
import org.devdom.quiz.util.EntityManagerFactory;

/**
 *
 * @author Carlos Vásquez Polanco
 */
public class FacebookDao {
    
    private final EntityManagerFactory emf;
    
    public FacebookDao(){
        emf = new EntityManagerFactory();
    }

    public EntityManager getEntityManager(){
        return emf.getEntityManager();
    }
}
