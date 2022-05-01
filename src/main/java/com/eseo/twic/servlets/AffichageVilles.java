package com.eseo.twic.servlets;

import com.eseo.twic.forms.GestionVille;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AffichageVilles", value = "/villes")
public class AffichageVilles extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int page = 1;
        int resultPerPage = 50;
        GestionVille gv = new GestionVille();
        int nbPages = (gv.readAll().size()/resultPerPage)+1;
        if(request.getParameter("page") != null){
            page = Integer.parseInt(request.getParameter("page"));
        }
        request.setAttribute("listeVilles", gv.readPage(page,resultPerPage));
        request.setAttribute("nbPages", nbPages);
        request.setAttribute("currentPage", page);
        this.getServletContext().getRequestDispatcher("/WEB-INF/villes.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
