package com.eseo.twic.servlets;

import com.eseo.twic.forms.GestionVille;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



@WebServlet(name = "ModificationVille", value = "/modification")
public class ModificationVille extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        GestionVille gv = new GestionVille();
        request.setAttribute("ville", gv.getVilleById(id));
        this.getServletContext().getRequestDispatcher("/WEB-INF/modification.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nom = request.getParameter("nom");
        String code = request.getParameter("code");
        String cp = request.getParameter("cp");
        String libelle = request.getParameter("libelle");
        String ligne = request.getParameter("ligne");
        String latitude = request.getParameter("lat");
        String longitude = request.getParameter("long");
        String query = "Code_commune_INSEE="+code+"&Nom_commune="+nom+"&Code_postal="+cp+"&Libelle_acheminement="
                + libelle+"&Ligne_5="+ligne+"&Latitude="+latitude+"&Longitude="+longitude;
        GestionVille.put(query);
        response.sendRedirect("/villes");
    }
}
