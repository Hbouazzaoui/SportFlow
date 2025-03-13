
package com.example.sportflow.controllers;

import com.example.sportflow.DAO.AdminDAO;
import com.example.sportflow.Model.Admin;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
    private AdminDAO adminDAO ;
    public void init(){
        adminDAO = new AdminDAO();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Admin> adminList = AdminDAO.list();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        String action = request.getPathInfo();
        switch (action) {
            case "/dashboard":
                getDashboard(request, response);
                break;

            case "/profile":
                getProfile(request, response);
                break;

            // Member func
            case "/member":
                getAllMember(request, response);
                break;
            case "/member/add-member":
                addMember(request, response);
                break;
            case "/member/edit-member":
                editMemberForm(request, response);
                break;

            // Session func
            case "/session":
                getAllSession(request, response);
                break;
            case "/session/add-form":
                addSession(request, response);
                break;
            case "/employee/edit-form":
                editSessionForm(request, response);
                break;

            // Coach func
            case "/coach":
                getAllCoach(request, response);
                break;
            case "/coach/add-form":
                addCoachForm(request, response);
                break;
            case "/coach/edit-form":
                editCoachForm(request, response);
                break;

            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
    private void getDashboard(HttpServletRequest request, HttpServletResponse response) {
        try {
            long memberCount = adminDAO.getAdminCountByRole(Admin.class);
            long coachCount = adminDAO.getAdminCountByRole(Admin.class);
            long sessionCount = adminDAO.getSessionCount();

            request.setAttribute("memberCount",memberCount);
            request.setAttribute("coachCount",coachCount);
            request.setAttribute("sessionCount",sessionCount);
            request.getRequestDispatcher("/WEB-INF/views/admin/dashboard.jsp").forward(request,response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void getProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/profile/profile.jsp").forward(request,response);

    }



    private void addCoachForm(HttpServletRequest request, HttpServletResponse response) {

    }

    private void editCoachForm(HttpServletRequest request, HttpServletResponse response) {


    }

    private void getAllCoach(HttpServletRequest request, HttpServletResponse response) {

    }





    private void editMemberForm(HttpServletRequest request, HttpServletResponse response) {
    }

    private void addMember(HttpServletRequest request, HttpServletResponse response) {

    }

    private void getAllMember(HttpServletRequest request, HttpServletResponse response) {

    }

    private void editSessionForm(HttpServletRequest request, HttpServletResponse response) {

    }

    private void addSession(HttpServletRequest request, HttpServletResponse response) {

    }

    private void getAllSession(HttpServletRequest request, HttpServletResponse response) {
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     doGet(request,response);

    }
}
