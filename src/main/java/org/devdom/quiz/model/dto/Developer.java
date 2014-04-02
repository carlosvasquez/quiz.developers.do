package org.devdom.quiz.model.dto;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;
import org.eclipse.persistence.annotations.Direction;
import org.eclipse.persistence.annotations.NamedStoredProcedureQueries;
import org.eclipse.persistence.annotations.NamedStoredProcedureQuery;
import org.eclipse.persistence.annotations.StoredProcedureParameter;

/**
 *
 * @author Carlos Vásquez Polanco
 */
@Entity
@XmlRootElement
@NamedStoredProcedureQueries({
    @NamedStoredProcedureQuery( name="Developer.findProfileAuthorizationByFBId", 
                                procedureName="findProfileAuthorizationByFBId",
                                returnsResultSet=true,
                                resultClass=Developer.class,
                                parameters={@StoredProcedureParameter(queryParameter="fb_id",
                                                                      name="fb_id",
                                                                      direction=Direction.IN,
                                                                      type=String.class)}
                                )
})
public class Developer implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "uid")
    private Long uid;
    
    @Column(name = "user_id")
    private Long userId;
    
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;
    
    @Column(name = "quiz_authorized")
    private boolean quizAuthorized;
    
    @Column(name = "authorization_code")
    private String authorizationCode;
    
    public Long getId() {
        return getUid();
    }

    public void setId(Long id) {
        this.setUid(id);
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the uid
     */
    public Long getUid() {
        return uid;
    }

    /**
     * @param uid the uid to set
     */
    public void setUid(Long uid) {
        this.uid = uid;
    }

    /**
     * @return the userId
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * @return the quizAuthorized
     */
    public boolean isQuizAuthorized() {
        return quizAuthorized;
    }

    /**
     * @param quizAuthorized the quizAuthorized to set
     */
    public void setQuizAuthorized(boolean quizAuthorized) {
        this.quizAuthorized = quizAuthorized;
    }

    /**
     * @return the authorizationCode
     */
    public String getAuthorizationCode() {
        return authorizationCode;
    }

    /**
     * @param authorizationCode the authorizationCode to set
     */
    public void setAuthorizationCode(String authorizationCode) {
        this.authorizationCode = authorizationCode;
    }
    
    
}
