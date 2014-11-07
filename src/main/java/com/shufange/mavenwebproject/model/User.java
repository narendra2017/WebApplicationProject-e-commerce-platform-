/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.shufange.mavenwebproject.model;


import java.util.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.*;
import javax.sql.DataSource;

@Entity
@Table(name = "BOOKUSER")
public class User {
    
    @Id
    private int userId;
    
    @Column (name="USER_NAME")        
    private String userName;
    
    @Column (name="CREATED_DATE") 
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date createdDate;
    
    @Column (name="PASSWORD")
    private String password;
    
    @ManyToMany    
    @JoinTable(name="BOOKUSER_GROUP",
            joinColumns=
                    @JoinColumn(name="USER_NAME")
            )
    private Group group;
    
    public User() {
    }

    public User(int userId, String userName, Date createdDate) {
        this.userId = userId;
        this.userName = userName;
        this.createdDate = createdDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    private DataSource getUser() throws NamingException {
        Context c = new InitialContext();
        return (DataSource) c.lookup("java:comp/env/user");
    }

    private DataSource getUSERGROUP() throws NamingException {
        Context c = new InitialContext();
        return (DataSource) c.lookup("java:comp/env/USERGROUP");
    }
    
    

    
}
