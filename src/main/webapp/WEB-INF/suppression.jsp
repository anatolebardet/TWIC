<%@ page import="com.eseo.twic.beans.VilleFrance" %><%--
  Created by IntelliJ IDEA.
  User: bardetanatole
  Date: 30/04/2022
  Time: 11:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Suppression Ville</title>
</head>
<body>
<h2> Informations de la ville </h2>
<form method="post" action="suppression">
    <p>
        <%--@declare id="long"--%><%--@declare id="lat"--%><%--@declare id="ligne"--%><%--@declare id="libelle"--%>
        <%--@declare id="cp"--%><%--@declare id="code"--%><%--@declare id="nom"--%>

        <label for="nom">Nom commune</label>
        <input type="text" name="nom" value="${ville.getNomCommune()}" readonly/><br />

        <label for="code">Code commune</label>
        <input type="text" name="code" value="${ville.getId()}" readonly/><br />

        <label for="cp">Code postal</label>
        <input type="text" name="cp" value="${ville.getCodePostal()}" readonly/><br />

        <label for="libelle">Libellé acheminement</label>
        <input type="text" name="libelle" value="${ville.getLibelleAcheminement()}" readonly /><br />

        <label for="ligne">Ligne 5</label>
        <input type="text" name="ligne" value="${ville.getLigne5()}" readonly/><br />

        <label for="lat">Latitude</label>
        <input type="text" name="lat" value="${ville.getLatitude()}" readonly/> <br />

        <label for="long">Longitude</label>
        <input type="text" name="long" value="${ville.getLongitude()}" readonly/> <br />
    </p>
    <input type="submit" name="supp" value="Supprimer" />
    <a href="villes">Annuler</a>
</form>


</body>
</html>
