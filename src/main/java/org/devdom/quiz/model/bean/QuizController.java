package org.devdom.quiz.model.bean;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.devdom.quiz.model.dao.QuestionDao;
import org.devdom.quiz.model.dao.QuizDao;
import org.devdom.quiz.model.dto.Questions;
import org.devdom.quiz.model.dto.Quiz;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author Carlos Vásquez Polanco
 */
@ManagedBean
@SessionScoped
public class QuizController implements SelectableDataModel<Quiz>{
    
    private static final long serialVersionUID = 1L;
    private final FacesContext facesContext = FacesContext.getCurrentInstance();
    private final HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
    private final FacebookController facebook = new FacebookController();
    private static final Logger LOG = Logger.getLogger(QuizController.class.getName());
    
    private Quiz quiz;
    private final QuizDao quizDao = new QuizDao();
    private Quiz selectedQuiz;
    private List<Questions> questions;
    
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
    
    public void onRowSelect(SelectEvent event) {
        System.out.println("select!");
        System.out.println("nombre: "+ ((Quiz) event.getObject()).getId());

        Integer quizId = ((Quiz) event.getObject()).getId();
        try {
            questions = (List<Questions>) (Questions) (new QuestionDao()).findAllQuestionsByQuizId(quizId);
            System.out.println("cantidad de preguntas " + questions.size());
        } catch (Exception ex) {
            Logger.getLogger(QuizController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void onRowUnselect(UnselectEvent event) {
        System.out.println("unselect!");
    }
    
    public Quiz getSelectedQuiz(){
        return selectedQuiz;
    }

    public void setSelectedQuiz(Quiz quiz) {
        this.selectedQuiz = quiz;
    }

    public List<Quiz> getList(){
        return null;
    }

    @Override
    public Object getRowKey(Quiz t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Quiz getRowData(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return the questions
     */
    public List<Questions> getQuestions() {
        return questions;
    }

    /**
     * @param questions the questions to set
     */
    public void setQuestions(List<Questions> questions) {
        this.questions = questions;
    }

}