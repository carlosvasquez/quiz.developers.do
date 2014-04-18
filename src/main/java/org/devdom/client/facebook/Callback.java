package org.devdom.client.facebook;

import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.internal.org.json.JSONArray;
import facebook4j.internal.org.json.JSONException;
import facebook4j.internal.org.json.JSONObject;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.devdom.quiz.model.bean.FacebookController;
import org.devdom.quiz.model.dao.DeveloperDao;
import org.devdom.quiz.model.dto.Developer;
import org.devdom.quiz.model.dto.FacebookProfile;

/**
 *
 * @author Carlos Vasquez Polanco
 */
public class Callback extends HttpServlet{
    
    private static final long serialVersionUID = 6305643034487441839L;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Facebook facebook = (Facebook) request.getSession().getAttribute("facebook");
        String oauthCode = request.getParameter("code");
        
        try {
            facebook.getOAuthAccessToken(oauthCode);
            setProfile(request,response,facebook);
        } catch (FacebookException e) {
            throw new ServletException(e);
        }
        response.sendRedirect(request.getContextPath() + "/");
    }
    
    private void setProfile(HttpServletRequest request, HttpServletResponse response, Facebook facebook){
        FacebookProfile profile = new FacebookProfile();
        try {
            String query = "SELECT uid, first_name, last_name, name, birthday_date, email, pic_big, sex FROM user WHERE uid = me() ";
            JSONArray jsonArray = facebook.executeFQL(query);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject;
                try {
                    jsonObject = jsonArray.getJSONObject(i);
                    profile.setUid(jsonObject.getLong("uid"));
                    profile.setFirstName(jsonObject.getString("first_name"));
                    profile.setLastName(jsonObject.getString("last_name"));
                    profile.setEmail(jsonObject.getString("email"));
                    profile.setBirthday_date(jsonObject.getString("birthday_date"));
                    profile.setPic_with_logo(jsonObject.getString("pic_big"));
                    profile.setSex(jsonObject.getString("sex"));
                    request.getSession().setAttribute("profile", profile);
                } catch (JSONException ex) {
                    Logger.getLogger(FacebookController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            DeveloperDao developerDao = new DeveloperDao();
            Developer developer = developerDao.findProfileAuthorizationByFBId(profile.getUid());
            HttpSession session = request.getSession();
            
            if(developer==null){
                try {
                    request.getSession().invalidate();
                    response.sendRedirect("index.xhtml");
                } catch (IOException ex) {
                    Logger.getLogger(Callback.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                if(developer.getUid()!=0){                
                    session.setAttribute("devdo_member",true);
                    session.setAttribute("quiz_authorized", developer.isQuizAuthorized());
                    session.setAttribute("quiz_auth_code", developer.getAuthorizationCode());
                    session.setAttribute("user_roles", developer.getRoles());
                }else{
                    session.setAttribute("devdo_member",false);
                }
            }
        } catch (FacebookException ex) {
            Logger.getLogger(FacebookController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}