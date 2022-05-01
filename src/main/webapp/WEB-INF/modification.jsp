<!DOCTYPE html>
<%@ page pageEncoding="UTF-8" %>
<html>
<head>
    <title>Ville</title>
</head>
<body>
    <form method="post" action="modification">
    <p>
        <%--@declare id="long"--%><%--@declare id="lat"--%><%--@declare id="ligne"--%><%--@declare id="libelle"--%>
        <%--@declare id="cp"--%><%--@declare id="code"--%><%--@declare id="nom"--%>

        <label for="nom">Nom commune</label>
        <input type="text" name="nom" value="${ville.getNomCommune()}" /><br />

        <label for="code">Code commune</label>
        <input type="text" name="code" value="${ville.getId()}" /><br />

        <label for="cp">Code postal</label>
        <input type="text" name="cp" value="${ville.getCodePostal()}" /><br />

        <label for="libelle">Libellé acheminement</label>
        <input type="text" name="libelle" value="${ville.getLibelleAcheminement()}" /><br />

        <label for="ligne">Ligne 5</label>
        <input type="text" name="ligne" value="${ville.getLigne5()}" /><br />

        <label for="lat">Latitude</label>
        <input type="text" name="lat" value="${ville.getLatitude()}" /> <br />

        <label for="long">Longitude</label>
        <input type="text" name="long" value="${ville.getLongitude()}" /> <br />
    </p>
    <input type="submit" name="maj" value="Mettre à jour" />
        <a href="villes">Annuler</a>
    </form>


</body>
</html>
