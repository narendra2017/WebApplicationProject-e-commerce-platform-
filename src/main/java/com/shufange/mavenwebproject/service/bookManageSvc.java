/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.shufange.mavenwebproject.service;

import com.shufange.mavenwebproject.model.Book;
import com.shufange.mavenwebproject.util.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.*;


public class bookManageSvc {
    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    
    public void deleteBook(int isbn){
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction(); 
            String query = "where isbn = " + "'" + Integer.toString(isbn) + "'";
            List<Book> books = (List<Book>) session.createQuery("from Book"+query).list();
            
            for(Book book : books){
                session.delete(book);
            }
            
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
       }finally {
          session.close();
       }
        
    }
    
    public List<Book> showBook(){
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        List<Book> books = new ArrayList();
        try{
            tx = session.beginTransaction();            
            books = (List<Book>) session.createQuery("from Book").list();       
            tx.commit();         
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
       }finally {      
          session.close();
          return books;         
       }
    }
}
