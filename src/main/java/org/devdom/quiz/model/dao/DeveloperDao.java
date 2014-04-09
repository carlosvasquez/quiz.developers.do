package org.devdom.quiz.model.dao;

import javax.persistence.EntityManager;
import org.devdom.quiz.model.dto.Developer;
import org.devdom.quiz.util.EntityManagerFactory;

/**
 *
 * @author Carlos Vasquez Polanco
 */
public class DeveloperDao {

    private final EntityManagerFactory emf;
    
    public DeveloperDao(){
        emf = new EntityManagerFactory();
    }

    public EntityManager getEntityManager(){
        return emf.getEntityManager();
    }
    
    public Developer findProfileAuthorizationByFBId(Long uid) throws Exception, ClassCastException{
        EntityManager em = getEntityManager();
        try{
            return (Developer) em.createNamedQuery("Developer.findProfileAuthorizationByFBId")
                    .setParameter("fb_id", uid)
                    .getSingleResult();
        }finally{
            if (em != null) {
                em.close();
            }
        }
    }
    
    public Developer updAuthorizationByUidAndAuthorizationCode(long uid, String authorizationCode) throws Exception{
        EntityManager em = getEntityManager();
        try{
            return (Developer) em.createNamedQuery("Developer.updAuthorizationByUidAndAuthorizationCode")
                    .setParameter("fb_id", uid)
                    .setParameter("authorization_code", authorizationCode)
                    .getSingleResult();
        }finally{
            if(em != null){
                em.close();
            }
        }
    }
}