package org.devdom.quiz.model.bean;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Carlos Vasquez Polanco
 */
@ManagedBean
@SessionScoped
public class ValidationCode  implements Serializable{
    
    private String code;

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }
    
    public void check(ActionEvent actionEvent) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        
        facesContext.addMessage(null, new FacesMessage("codigo " + code + " verificado!"));
    }
    
    public void display(){
    
    }
    
    public void panel(){
    
    }
    
}
