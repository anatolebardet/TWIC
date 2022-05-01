package com.eseo.twic.servlets;

import com.eseo.twic.beans.VilleFrance;
import com.eseo.twic.forms.GestionVille;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DistanceVilles", value = "/distance")
public class DistanceVilles extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GestionVille gv = new GestionVille();
        request.setAttribute("listeVilles", gv.readAll());
        this.getServletContext().getRequestDispatcher("/WEB-INF/distance.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nomV1 = request.getParameter("v1");
        String nomV2 = request.getParameter("v2");
        GestionVille gv = new GestionVille();
        VilleFrance ville1 = gv.getVilleByName(nomV1);
        VilleFrance ville2 = gv.getVilleByName(nomV2);
        String dist = GestionVille.distance(Double.parseDouble(ville1.getLatitude()), Double.parseDouble(ville1.getLongitude()),
                Double.parseDouble(ville2.getLatitude()), Double.parseDouble(ville2.getLongitude()));
        request.setAttribute("distance", dist);
        this.doGet(request,response);
    }
}
