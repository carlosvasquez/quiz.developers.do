package org.devdom.quiz.model.bean;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

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
    
    public void check(){
        System.out.println();
    }
    
    public void display(){
    
    }
    
    public void panel(){
    
    }
    
}
