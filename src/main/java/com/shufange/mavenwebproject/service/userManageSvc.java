/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.shufange.mavenwebproject.service;

import com.shufange.mavenwebproject.model.User;
import com.shufange.mavenwebproject.util.HibernateUtil;
import java.util.*;
import org.hibernate.*;

/**
 *
 * @author shufange
 */
public class userManageSvc {
    
    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private int isbn;
    
    public boolean userLogin(String username, String password){
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        boolean res = false;
        try{
            tx = session.beginTransaction(); 
            List<User> users = (List<User>) session.createQuery("from User order by userName").list();
            res = matchSearch(username,password,users);               
            tx.commit();
        }catch(HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
       }finally {
          session.close();
          return res;
       }
    }
    
    public boolean matchSearch(String u, String p, List<User> userList){
        User user = userIsMatch(u,userList);
        if(user==null)
            return false;
        else if(user.getPassword()!=p){
            return false;
        }else return true;
    }
    
    public User userIsMatch(String str, List<User> userList ){
        int start=0; int end=userList.size()-1;
        while(start+1<end){
            int mid = start+(end-start)/2;
            User theuser = userList.get(mid);
            if(theuser.getUserName()==str){
                return theuser;
            }else if(theuser.getUserName().compareTo(str)>0){
                end = mid-1;
            }else{
                start = mid+1;
            }
        }
        
        if(userList.get(start).getUserName()==str) return userList.get(start);
        if(userList.get(end).getUserName()==str) return userList.get(end);
 
        return null;
    }
}
