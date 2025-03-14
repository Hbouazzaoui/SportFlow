package com.example.sportflow.authe;

import com.example.sportflow.DAO.LginDAO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/loginReg")
public class Login extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public  Login(){
        super();
    }
    protected void doPost(HttpServletRequest request , HttpServletResponse response)
    {
//        LginDAO cd = new
//        String userName =request.getParameter("userName");
//        String password =request.getParameter("password");
//        String sumbitType =request.getParameter("sumbit");
//
//        if (sumbitType.equals("login")) {
//
//        }else if (sumbitType.equals("register")){
//
//        }
    }
}
