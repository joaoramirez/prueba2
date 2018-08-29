/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author juan
 */
@Named(value = "loginBean")
@Stateless
public class LoginBean implements Serializable {

   private String username;
   private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
   
   
    public LoginBean() {
    }
    
    public void login() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        request.getSession().setMaxInactiveInterval(900);
        try{
        request.login(username.toUpperCase(), password);
        
        FacesContext.getCurrentInstance().getExternalContext().redirect("welcome.xhtml");
        }
            catch(Exception se) {
        FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error: ","Usuario o contraseña incorrecta"));
        }
       
    }
    
    public void logout() {
        try {
            System.gc();
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            if (request.getSession() != null) {
                request.getSession().invalidate();
            }
            request.logout();
            FacesContext.getCurrentInstance().getExternalContext().redirect("/Login/");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: ", e.getMessage()));
        }
    }
    
}
