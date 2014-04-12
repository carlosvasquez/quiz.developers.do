package org.devdom.quiz.model.bean;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.devdom.quiz.model.dao.DeveloperDao;
import org.devdom.quiz.model.dto.Developer;

/**
 *
 * @author Carlos Vasquez
 */
@ManagedBean
@SessionScoped
public class ValidationCode  implements Serializable{
    
    private String code;
    private FacebookController fbController = null;

    public ValidationCode(){
        
    }
    /**
     * @return the code
     */
    public String getCode(){
        return code;
    }
    /**
     * @param code the code to set
     */
    public void setCode(String code){
        this.code = code;
    }

    public void validate(FacesContext facesContext, UIComponent component, Object value){
        
    }

    public void check(ActionEvent actionEvent) {
        fbController = new FacebookController();
        FacesContext facesContext = FacesContext.getCurrentInstance(); 
        long facebookID = fbController.getFacebookID();

        try{
            if(facebookID!=0){
                if(getCode().equals(fbController.getAuthorizationCode())){
                    DeveloperDao developerDao = new DeveloperDao();
                    Developer developer = developerDao.updAuthorizationByUidAndAuthorizationCode(facebookID, getCode());
                    boolean authorized = developer.isQuizAuthorized();
                    fbController.setAuthorized();

                    facesContext.addMessage("display", new FacesMessage("codigo autorizado!"));
                    facesContext.getViewRoot().setViewId("viewID");
                    /*
                    if(authorized){
                        facesContext.addMessage("display", new FacesMessage("codigo autorizado!"));
                        fbController.setAuthorized();
                    }else{
                        facesContext.addMessage("display", new FacesMessage(FacesMessage.SEVERITY_ERROR, "codigo "+code+" es invàlido!", ""));
                    }
                    */
                }else{
                    facesContext.addMessage("display", new FacesMessage(FacesMessage.SEVERITY_ERROR, "codigo "+code+" es invàlido2", " +2"));
                }
            }else{
                facesContext.addMessage("display", new FacesMessage(FacesMessage.SEVERITY_ERROR, "sesion expirada! refresca la ventana", ""));
            }
        }catch(Exception ex){
            facesContext.addMessage("display", new FacesMessage(FacesMessage.SEVERITY_ERROR, "error: "+ ex.getMessage(), " +2"));
        }
    }
}
