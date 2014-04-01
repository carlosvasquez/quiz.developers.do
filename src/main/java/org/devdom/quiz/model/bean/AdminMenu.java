package org.devdom.quiz.model.bean;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

/**
 *
 * @author Carlos Vasquez
 */

@ManagedBean
@SessionScoped
public class AdminMenu implements Serializable{
    
    private static final long serialVersionUID = 1L;
    private FacesContext facesContext = null;
    private UIViewRoot uiRoot = null;
    private String viewId = "";
    
    public int getActiveIndex(){
        facesContext = FacesContext.getCurrentInstance();
        uiRoot = facesContext.getViewRoot();
        viewId = uiRoot.getViewId();

        switch(viewId){
            case "/index.xhtml" : return 0;
            case "/profile.xhtml" : return 1;
            case "/admin.xhtml" : return 2;
            default : return 0;
        }
    }
    
    
}
