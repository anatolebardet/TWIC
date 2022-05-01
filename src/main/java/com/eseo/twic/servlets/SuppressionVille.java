package com.eseo.twic.servlets;

import com.eseo.twic.forms.GestionVille;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



@WebServlet(name = "SuppressionVille", value = "/suppression")
public class SuppressionVille extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(("get supp"));
        String id;
        id = request.getParameter("id");
        GestionVille gv = new GestionVille();
        request.setAttribute("ville", gv.getVilleById(id));
        this.getServletContext().getRequestDispatcher("/WEB-INF/suppression.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        System.out.println("code supp : "+code);
        GestionVille.delete(code);
        response.sendRedirect("/villes");
    }
}