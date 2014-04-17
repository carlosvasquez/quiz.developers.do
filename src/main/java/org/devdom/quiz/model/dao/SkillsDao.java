package org.devdom.quiz.model.dao;

import java.util.List;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import org.devdom.quiz.model.dto.Skills;
import org.devdom.quiz.util.EntityManagerFactory;

/**
 *
 * @author Carlos Vasquez Polanco
 */
public class SkillsDao {
    
    private static final long serialVersionUID = 1L;
    private final EntityManagerFactory emf;
    private static final Logger LOG = Logger.getLogger(QuizDao.class.getName());
    
    public SkillsDao(){
        emf = new EntityManagerFactory();
    }
    
    public EntityManager getEntityManager(){
        return emf.getEntityManager();
    }
    
    public List<Skills> findAllSkills(){
        List<Skills> list = null;
        
        EntityManager em = null;
        try{
            em = emf.getEntityManager();
            list = (List<Skills>) em.createNamedQuery("Skills.findAllSkills")
                    .getResultList();
        }finally{
            if(em!=null|em.isOpen())
                em.close();
        }
        return list;
    }
}
