/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.shufange.mavenwebproject.controller;

import com.shufange.mavenwebproject.model.Book;
import com.shufange.mavenwebproject.service.bookManageSvc;
import com.shufange.mavenwebproject.service.userManageSvc;
import com.shufange.mavenwebproject.util.HibernateUtil;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.SessionFactory;

/**
 *
 * @author shufange
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/showBook/"})
public class LoginServlet extends HttpServlet {

    private static final userManageSvc userManageSvc = new userManageSvc();
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(username);
        if(!userManageSvc.userLogin(username, password)){
            String feedback = "Incorrect username or password";
            request.setAttribute("loginFeedback", feedback);
        }
        else response.sendRedirect("/MavenWebProject/showBook/");
        
} 

    

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
