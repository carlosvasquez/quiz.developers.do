package org.devdom.quiz.model.dto;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.eclipse.persistence.annotations.Direction;
import org.eclipse.persistence.annotations.NamedStoredProcedureQueries;
import org.eclipse.persistence.annotations.NamedStoredProcedureQuery;
import org.eclipse.persistence.annotations.StoredProcedureParameter;

/**
 *
 * @author Carlos Vasquez Polanco
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
                                ),
    @NamedStoredProcedureQuery( name="Developer.updAuthorizationByUidAndAuthorizationCode", 
                                procedureName="updAuthorizationByUidAndAuthorizationCode",
                                returnsResultSet=true,
                                resultClass=Developer.class,
                                parameters={@StoredProcedureParameter(queryParameter="fb_id",
                                                                      name="fb_id",
                                                                      direction=Direction.IN,
                                                                      type=Long.class),
                                            @StoredProcedureParameter(queryParameter="authorization_code",
                                                                      name="authorization_code",
                                                                      direction=Direction.IN,
                                                                      type=String.class)}
                                )
})
public class Quiz implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "quiz_id")
    private Integer id;
    @Size(max = 300)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "owner")
    private long owner;
    @Column(name = "user_id")
    private int userId;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "status_id")
    private int statusId;
    @Column(name = "status")
    private String status;

    public Quiz() {
    }

    public Quiz(Integer quizId) {
        this.id = quizId;
    }

    public Quiz(Integer quizId, Date creationDate, int owner) {
        this.id = quizId;
        this.creationDate = creationDate;
        this.owner = owner;
    }

        /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * @return the fullName
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * @param fullName the fullName to set
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * @return the statusId
     */
    public int getStatusId() {
        return statusId;
    }

    /**
     * @param statusId the statusId to set
     */
    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }
    
    public Integer getQuizId() {
        return getId();
    }

    public void setQuizId(Integer quizId) {
        this.setId(quizId);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public long getOwner() {
        return owner;
    }

    public void setOwner(long owner) {
        this.owner = owner;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Quiz)) {
            return false;
        }
        Quiz other = (Quiz) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.devdom.quiz.model.dto.Quiz[ Id=" + getId() + " ]";
    }
    
}