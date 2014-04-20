package org.devdom.quiz.model.dto;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Carlos Vásquez Polanco
 */
@Entity
@XmlRootElement
public class Questions implements Serializable{
    
    /*
    @Column(name = "uid")
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "question")
    private String question;
    @Column(name = "creation_date")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date creationDate;
    @Column(name = "skill_id")
    private Integer skillId;
    @Column(name = "owner")
    private Integer owner;
    @Column(name = "quiz_id")
    private Integer quizId;
    @Column(name = "status_id")
    private int statusId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the question
     */
    public String getQuestion() {
        return question;
    }

    /**
     * @param question the question to set
     */
    public void setQuestion(String question) {
        this.question = question;
    }

    /**
     * @return the creationDate
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * @param creationDate the creationDate to set
     */
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * @return the skillId
     */
    public Integer getSkillId() {
        return skillId;
    }

    /**
     * @param skillId the skillId to set
     */
    public void setSkillId(Integer skillId) {
        this.skillId = skillId;
    }

    /**
     * @return the owner
     */
    public Integer getOwner() {
        return owner;
    }

    /**
     * @param owner the owner to set
     */
    public void setOwner(Integer owner) {
        this.owner = owner;
    }

    /**
     * @return the quizId
     */
    public Integer getQuizId() {
        return quizId;
    }

    /**
     * @param quizId the quizId to set
     */
    public void setQuizId(Integer quizId) {
        this.quizId = quizId;
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
    
    
}
