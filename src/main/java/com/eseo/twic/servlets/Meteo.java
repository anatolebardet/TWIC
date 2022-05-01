package com.eseo.twic.servlets;

import com.eseo.twic.beans.VilleFrance;
import com.eseo.twic.forms.GestionVille;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Meteo", value = "/meteo")
public class Meteo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        GestionVille gv = new GestionVille();
        VilleFrance ville = gv.getVilleById(id);
        String lat = ville.getLatitude();
        String lon = ville.getLongitude();
        request.setAttribute("temperature",GestionVille.getTemperature(lat,lon));
        request.setAttribute("ville", gv.getVilleById(id));
        this.getServletContext().getRequestDispatcher("/WEB-INF/meteo.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
