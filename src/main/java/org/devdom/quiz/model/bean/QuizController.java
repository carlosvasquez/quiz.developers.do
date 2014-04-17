package org.devdom.quiz.model.bean;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.devdom.quiz.model.dao.QuizDao;
import org.devdom.quiz.model.dto.Quiz;

/**
 *
 * @author Carlos Vasquez Polanco
 */
@ManagedBean
@SessionScoped
public class QuizController implements Serializable{
    
    private static final long serialVersionUID = 1L;
    private final FacesContext facesContext = FacesContext.getCurrentInstance();
    private final HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
    private final FacebookController facebook = new FacebookController();
    private static final Logger LOG = Logger.getLogger(QuizController.class.getName());
    
    private Quiz quiz;
    private final QuizDao quizDao = new QuizDao();

    private void reset(){
        quiz = null;
    }
    
    public List<Quiz> getAllQuiz(){
        try {
            return quizDao.findAllQuiz();
        } catch (Exception ex) {
            Logger.getLogger(QuizController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public List<Quiz> getAllQuizByUserId(){
        long facebookID = facebook.getFacebookID();
        try {
            if(facebookID==0){
                return null;
            }
            return quizDao.findAllQuizByUserId(facebookID);
        } catch (Exception ex) {
            Logger.getLogger(QuizController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}