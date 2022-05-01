<!DOCTYPE html>
<%@ page pageEncoding="UTF-8" %>

<html lang="en">
<head>
    <title>Distance entre 2 villes</title>
</head>
    <h2>Calcul de distance entre 2 villes : </h2>

    <form method="post" >
        <p>
            <label for="v1">Ville 1 : </label>
            <select name="v1">
                <c:forEach var="v1" items="${listeVilles}">
                    <option id="v1"><c:out value="${v1.getNomCommune()}"></c:out></option>
                </c:forEach>
            </select>
        </p>
        <p>
            <label for="v2">Ville 2 : </label>
            <select name="v2">
                <c:forEach var="v2" items="${listeVilles}">
                    <option id="v2"><c:out value="${v2.getNomCommune()}"></c:out></option>
                </c:forEach>
            </select>
        </p>
        <input type="submit" value="Calculer la distance"/>
    </form>

    <c:if test="${distance != null}">
        <p>La distance entre ${v1} et ${v2} est de ${distance} km.</p>
    </c:if>

</html>