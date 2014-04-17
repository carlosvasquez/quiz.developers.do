package org.devdom.quiz.model.dao;

import java.util.List;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import org.devdom.quiz.model.dto.Quiz;
import org.devdom.quiz.util.EntityManagerFactory;

/**
 *
 * @author Carlos Vasquez Polanco
 */
public class QuizDao {

    private static final long serialVersionUID = 1L;
    private final EntityManagerFactory emf;

    private static final Logger LOG = Logger.getLogger(QuizDao.class.getName());
    
    public QuizDao(){
        emf = new EntityManagerFactory();
    }
    
    public EntityManager getEntityManager(){
        return emf.getEntityManager();
    }

    public List<Quiz> getAllQuiz() throws Exception, ClassCastException{
        EntityManager em = getEntityManager();
        try{
            return (List<Quiz>) (Quiz) em.createNamedQuery("Quiz.findAllQuiz")
                    .getResultList();
        }finally{
            if (em != null) {
                em.close();
            }
        }
    }
    
    public List<Quiz> getAllQuizByUserId(long userId) throws Exception, ClassCastException{
        EntityManager em = getEntityManager();
        try{
            return (List<Quiz>) (Quiz) em.createNamedQuery("Quiz.findAllQuizByUserId")
                    .setParameter("fb_id", userId)
                    .getResultList();
        }finally{
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Quiz> getList(){

        List<Quiz> list = null;
        EntityManager em = null;

        try{
            em = emf.getEntityManager();
            list = em.createNamedQuery("Quiz.findAll").getResultList();
        }finally{
            if(em!=null|em.isOpen())
                em.close();
        }
        return list;
    }
    
    public void create(Quiz quiz) {
        EntityManager em = null;
        try {
            em = emf.getEntityManager();
            em.getTransaction().begin();
            em.persist(quiz);
            em.getTransaction().commit();
        }finally {
            if(em!=null|em.isOpen())
                em.close();
        }
    }

}