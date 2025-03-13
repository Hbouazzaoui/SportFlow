package com.example.sportflow.controllers;

import com.example.sportflow.DAO.AdminDAO;
import com.example.sportflow.Model.Member;
import com.example.sportflow.Model.Coach;
import com.example.sportflow.Model.Session;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
    private AdminDAO adminDAO;

    public void init() {
        adminDAO = new AdminDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();

        // Handle null action (default to dashboard)
        if (action == null || action.equals("/")) {
            getDashboard(request, response);
            return;
        }

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
                addMemberForm(request, response);
                break;
            case "/member/edit-member":
                editMemberForm(request, response);
                break;
            case "/member/delete-member":
                deleteMember(request, response);
                break;

            // Session func
            case "/session":
                getAllSession(request, response);
                break;
            case "/session/add-form":
                addSessionForm(request, response);
                break;
            case "/session/edit-form":
                editSessionForm(request, response);
                break;
            case "/session/delete-session":
                deleteSession(request, response);
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
            case "/coach/delete-coach":
                deleteCoach(request, response);
                break;

            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void editSessionForm(HttpServletRequest request, HttpServletResponse response) {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();

        if (action == null || action.equals("/")) {
            response.sendRedirect(request.getContextPath() + "/AdminServlet/dashboard");
            return;
        }

        switch (action) {
            case "/member/add-member":
                addMember(request, response);
                break;
            case "/member/edit-member":
                updateMember(request, response);
                break;

            case "/session/add-form":
                addSession(request, response);
                break;
            case "/session/edit-form":
                updateSession(request, response);
                break;

            case "/coach/add-form":
                addCoach(request, response);
                break;
            case "/coach/edit-form":
                updateCoach(request, response);
                break;

            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void addSession(HttpServletRequest request, HttpServletResponse response) {
    }

    private void updateSession(HttpServletRequest request, HttpServletResponse response) {
    }

    // Dashboard
    private void getDashboard(HttpServletRequest request, HttpServletResponse response) {
        try {
            long memberCount = adminDAO.getAdminCountByRole("member");
            long coachCount = adminDAO.getAdminCountByRole("coach");
            long sessionCount = adminDAO.getSessionCount();

            request.setAttribute("memberCount", memberCount);
            request.setAttribute("coachCount", coachCount);
            request.setAttribute("sessionCount", sessionCount);
            request.getRequestDispatcher("/WEB-INF/views/admin/dashboard.jsp").forward(request, response);
        } catch (Exception e) {
            throw new RuntimeException("Error loading dashboard: " + e.getMessage(), e);
        }
    }

    // Profile
    private void getProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/profile/profile.jsp").forward(request, response);
    }

    // Member Management
    private void getAllMember(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<Member> members = adminDAO.getAllMembers();
            request.setAttribute("members", members);
            request.getRequestDispatcher("/WEB-INF/views/member/list.jsp").forward(request, response);
        } catch (Exception e) {
            throw new RuntimeException("Error fetching members: " + e.getMessage(), e);
        }
    }

    private void addMemberForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/member/add.jsp").forward(request, response);
    }

    private void addMember(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }

    private void editMemberForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void updateMember(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }

    private void deleteMember(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }

    // Session Management
    private void getAllSession(HttpServletRequest request, HttpServletResponse response) {

    }

    private void addSessionForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/session/add.jsp").forward(request, response);
    }



    private void deleteSession(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }

    // Coach Management
    private void getAllCoach(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<Coach> coaches = adminDAO.getAllCoaches();
            request.setAttribute("coaches", coaches);
            request.getRequestDispatcher("/WEB-INF/views/coach/list.jsp").forward(request, response);
        } catch (Exception e) {
            throw new RuntimeException("Error fetching coaches: " + e.getMessage(), e);
        }
    }

    private void addCoachForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/coach/add.jsp").forward(request, response);
    }

    private void addCoach(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String speciality = request.getParameter("speciality");

        Coach coach = new Coach(name, speciality, speciality);
        adminDAO.addCoach(coach);

        response.sendRedirect(request.getContextPath() + "/AdminServlet/coach");
    }

    private void editCoachForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Coach coach = adminDAO.getCoachById(id);
        request.setAttribute("coach", coach);
        request.getRequestDispatcher("/WEB-INF/views/coach/edit.jsp").forward(request, response);
    }

    private void updateCoach(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String speciality = request.getParameter("speciality");

        Coach coach = new Coach(id, name, speciality);
        adminDAO.updateCoach(coach);

        response.sendRedirect(request.getContextPath() + "/AdminServlet/coach");
    }

    private void deleteCoach(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        adminDAO.deleteCoach(id);
        response.sendRedirect(request.getContextPath() + "/AdminServlet/coach");
    }
}