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
    
    /*
    public QuizDao() {
        pagingInfo = new PagingInfo();
        converter = new QuizConverter();
    }
    private Quiz quiz = null;
    private List<Quiz> quizItems = null;
    private QuizFacade jpaController = null;
    private QuizConverter converter = null;
    private PagingInfo pagingInfo = null;

    @Resource
    private UserTransaction utx = null;
    @PersistenceUnit(unitName = "quizskills_PU")
    private EntityManagerFactory emf = null;

    public PagingInfo getPagingInfo() {
        if (pagingInfo.getItemCount() == -1) {
            pagingInfo.setItemCount(getJpaController().count());
        }
        return pagingInfo;
    }

    public QuizFacade getJpaController() {
        if (jpaController == null) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            jpaController = (QuizFacade) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "skillsetQuizJpa");
        }
        return jpaController;
    }

    public SelectItem[] getSkillsetQuizItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), false);
    }

    public SelectItem[] getSkillsetQuizItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), true);
    }

    public Quiz getSkillsetQuiz() {
        if (quiz == null) {
            quiz = (Quiz) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentSkillsetQuiz", converter, null);
        }
        if (quiz == null) {
            quiz = new Quiz();
        }
        return quiz;
    }

    public String listSetup() {
        reset(true);
        return "skillsetQuiz_list";
    }

    public String createSetup() {
        reset(false);
        quiz = new Quiz();
        return "skillsetQuiz_create";
    }

    public String create() {
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().create(quiz);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("SkillsetQuiz was successfully created.");
            } else {
                JsfUtil.ensureAddErrorMessage(transactionException, "A persistence error occurred.");
            }
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (Exception ex) {
            }
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return listSetup();
    }

    public String detailSetup() {
        return scalarSetup("skillsetQuiz_detail");
    }

    public String editSetup() {
        return scalarSetup("skillsetQuiz_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        quiz = (Quiz) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentSkillsetQuiz", converter, null);
        if (quiz == null) {
            String requestSkillsetQuizString = JsfUtil.getRequestParameter("jsfcrud.currentSkillsetQuiz");
            JsfUtil.addErrorMessage("The skillsetQuiz with id " + requestSkillsetQuizString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        String skillsetQuizString = converter.getAsString(FacesContext.getCurrentInstance(), null, quiz);
        String currentSkillsetQuizString = JsfUtil.getRequestParameter("jsfcrud.currentSkillsetQuiz");
        if (skillsetQuizString == null || skillsetQuizString.length() == 0 || !skillsetQuizString.equals(currentSkillsetQuizString)) {
            String outcome = editSetup();
            if ("skillsetQuiz_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit skillsetQuiz. Try again.");
            }
            return outcome;
        }
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().edit(quiz);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("SkillsetQuiz was successfully updated.");
            } else {
                JsfUtil.ensureAddErrorMessage(transactionException, "A persistence error occurred.");
            }
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (Exception ex) {
            }
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return detailSetup();
    }

    public String remove() {
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentSkillsetQuiz");
        Integer id = new Integer(idAsString);
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().remove(getJpaController().find(id));
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("SkillsetQuiz was successfully deleted.");
            } else {
                JsfUtil.ensureAddErrorMessage(transactionException, "A persistence error occurred.");
            }
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (Exception ex) {
            }
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return relatedOrListOutcome();
    }

    private String relatedOrListOutcome() {
        String relatedControllerOutcome = relatedControllerOutcome();
        if (relatedControllerOutcome != null) {
            return relatedControllerOutcome;
        }
        return listSetup();
    }

    public List<Quiz> getSkillsetQuizItems() {
        if (quizItems == null) {
            getPagingInfo();
            quizItems = getJpaController().findRange(new int[]{pagingInfo.getFirstItem(), pagingInfo.getFirstItem() + pagingInfo.getBatchSize()});
        }
        return quizItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "skillsetQuiz_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "skillsetQuiz_list";
    }

    private String relatedControllerOutcome() {
        String relatedControllerString = JsfUtil.getRequestParameter("jsfcrud.relatedController");
        String relatedControllerTypeString = JsfUtil.getRequestParameter("jsfcrud.relatedControllerType");
        if (relatedControllerString != null && relatedControllerTypeString != null) {
            FacesContext context = FacesContext.getCurrentInstance();
            Object relatedController = context.getApplication().getELResolver().getValue(context.getELContext(), null, relatedControllerString);
            try {
                Class<?> relatedControllerType = Class.forName(relatedControllerTypeString);
                Method detailSetupMethod = relatedControllerType.getMethod("detailSetup");
                return (String) detailSetupMethod.invoke(relatedController);
            } catch (ClassNotFoundException e) {
                throw new FacesException(e);
            } catch (NoSuchMethodException e) {
                throw new FacesException(e);
            } catch (IllegalAccessException e) {
                throw new FacesException(e);
            } catch (InvocationTargetException e) {
                throw new FacesException(e);
            }
        }
        return null;
    }

    private void reset(boolean resetFirstItem) {
        quiz = null;
        quizItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        Quiz quiz = new Quiz();
        String newQuizString = converter.getAsString(FacesContext.getCurrentInstance(), null, quiz);
        String quizString = converter.getAsString(FacesContext.getCurrentInstance(), null, quiz);
        if (!newQuizString.equals(quizString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }
    */
}