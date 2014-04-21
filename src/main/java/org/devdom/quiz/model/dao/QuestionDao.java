package org.devdom.quiz.model.dao;

import java.util.List;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import org.devdom.quiz.model.dto.Questions;
import org.devdom.quiz.util.EntityManagerFactory;

/**
 *
 * @author Carlos Vásquez Polanco
 */
public class QuestionDao{
    
    private static final long serialVersionUID = 1L;
    private final EntityManagerFactory emf;

    private static final Logger LOG = Logger.getLogger(QuizDao.class.getName());
    
    public QuestionDao(){
        emf = new EntityManagerFactory();
    }
    
    public EntityManager getEntityManager(){
        return emf.getEntityManager();
    }
    
    public List<Questions> findAllQuestionsByQuizId(int quizId) throws Exception, ClassCastException{
        EntityManager em = getEntityManager();
        try{
            return em.createNamedQuery("Questions.findAllQuestionsByQuizId")
                     .setParameter("quiz_id",quizId)
                     .getResultList();
        }finally{
            if(em!=null|em.isOpen())
                em.close();
        }
    }

}
