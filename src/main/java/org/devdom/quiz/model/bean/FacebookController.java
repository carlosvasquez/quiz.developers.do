package org.devdom.quiz.model.bean;

import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.internal.org.json.JSONArray;
import facebook4j.internal.org.json.JSONException;
import facebook4j.internal.org.json.JSONObject;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.devdom.quiz.model.dto.FacebookProfile;
import org.devdom.quiz.model.dto.Questions;
import org.devdom.quiz.util.Configuration;

/**
 *
 * @author Carlos Vasquez
 */

@ManagedBean
@SessionScoped
public class FacebookController implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private FacesContext facesContext = FacesContext.getCurrentInstance();
    private HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
    private List<Questions> questions = null;

    public String getAuthorizationCode(){
        facesContext = FacesContext.getCurrentInstance();
        session = (HttpSession) facesContext.getExternalContext().getSession(true);
        return session.getAttribute("quiz_auth_code").toString();
    }

    public String[] getRoles(){
        facesContext = FacesContext.getCurrentInstance();
        session = (HttpSession) facesContext.getExternalContext().getSession(true);
        if(session.getAttribute("user_roles")==null){
            return new String[] {"1;ver"};
        }
        String roles = session.getAttribute("user_roles").toString();
        return roles.split("\\,");
    }
    
    public boolean roleBuilder(int active){
        String[] roles = getRoles();
        for(String role : roles){
            if(Integer.parseInt(role.split("\\;")[0])==active)
                return true;
        }
        return false;
    }
    
    public boolean isViewer(){
        return roleBuilder(Configuration.ROLE_VIEWER);
    }
    
    public boolean isCompetitor(){
        return roleBuilder(Configuration.ROLE_COMPETITOR);
    }
    
    public boolean isEditor(){
        return roleBuilder(Configuration.ROLE_EDITOR);
    }
    
    public boolean isCreator(){
        return roleBuilder(Configuration.ROLE_CREATOR);
    }
    
    public boolean isOwner(){
        return roleBuilder(Configuration.ROLE_OWNER);
    }
    
    public boolean isAdministrator(){
        return roleBuilder(Configuration.ROLE_ADMINISTRATOR);
    }

    public boolean isDevdoMember(){
        facesContext = FacesContext.getCurrentInstance();
        session = (HttpSession) facesContext.getExternalContext().getSession(true);
        return (session.getAttribute("devdo_member") == null)?false: (boolean) session.getAttribute("devdo_member");
    }
    
    public void setAuthorized(){
        facesContext = FacesContext.getCurrentInstance();
        session = (HttpSession) facesContext.getExternalContext().getSession(true);
        session.setAttribute("quiz_authorized", true);
    }
    
    public boolean isAuthorized(){
        facesContext = FacesContext.getCurrentInstance();
        session = (HttpSession) facesContext.getExternalContext().getSession(true);
        return (session.getAttribute("quiz_authorized") == null)?false: (boolean) session.getAttribute("quiz_authorized");
    }

    public boolean isLogged(){
        facesContext = FacesContext.getCurrentInstance();
        session = (HttpSession) facesContext.getExternalContext().getSession(true);
        return session.getAttribute("facebook") != null;
    }
    
    public String getProfilePicture(){
        /*
        session = (HttpSession) facesContext.getExternalContext().getSession(true);
        FacebookProfile profile = (FacebookProfile) session.getAttribute("profile");
        if(profile==null)
            return "";
        if(profile.getPic_with_logo()==null)
            return "";
        return profile.getPic_with_logo();*/
        return "https://graph.facebook.com/710605599/picture?height=420&amp;width=420";
    }
    
    public String getLoggedName(){
        session = (HttpSession) facesContext.getExternalContext().getSession(true);
        FacebookProfile profile = (FacebookProfile) session.getAttribute("profile");
        if(profile==null)
            return "";
        if(profile.getFirstName()==null || profile.getLastName()==null)
            return "";
        return profile.getFirstName() + " " + profile.getLastName();
    }

    public long getFacebookID(){
        session = (HttpSession) facesContext.getExternalContext().getSession(true);
        FacebookProfile profile = (FacebookProfile) session.getAttribute("profile");
        if(profile==null)
            return 0;
        return profile.getUid();
    }

    public String connect(){

        facesContext = FacesContext.getCurrentInstance();
        session = (HttpSession) facesContext.getExternalContext().getSession(true);
        session.getAttribute("facebook");

        Facebook facebook = (Facebook) session.getAttribute("facebook");
        FacebookProfile profile = new FacebookProfile();
        
        try {
            String query = "SELECT uid, first_name, last_name, name, birthday_date, email, pic_big, sex FROM user WHERE uid = me() ";
            JSONArray jsonArray = facebook.executeFQL(query);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject;
                try {
                    jsonObject = jsonArray.getJSONObject(i);                    
                    profile.setFirstName(jsonObject.getString("first_name"));
                    profile.setLastName(jsonObject.getString("last_name"));
                    profile.setEmail(jsonObject.getString("email"));
                    profile.setBirthday_date(jsonObject.getString("birthday_date"));
                    profile.setPic_with_logo(jsonObject.getString("pic_big"));
                    profile.setSex(jsonObject.getString("sex"));
                    session.setAttribute("profile", profile);
                } catch (JSONException ex) {
                    Logger.getLogger(FacebookController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return "Nombre del usuario " + profile.getFirstName();
        } catch (FacebookException ex) {
            Logger.getLogger(FacebookController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
}
