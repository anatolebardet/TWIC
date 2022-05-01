<!DOCTYPE html>
<%@ page pageEncoding="UTF-8" %>
<html>
<head>
    <title>Villes</title>
</head>
<body>
<form method="post" action="suppression">
<table class="v">
    <tr>
        <ul>
            <th>Nom des villes</th>
            <th>Météo</th>
            <th>Modification</th>
            <th>Suppression</th>
        </ul>
    </tr>
    <c:forEach items="${listeVilles}" var="ville">
        <tr>
            <td>${ville.getNomCommune()}</td>
            <td><a href="meteo?id=${ville.getId()}">Météo</a></td>
            <td><a href="modification?id=${ville.getId()}">Modifier</a></td>
            <td><a href="suppression?id=${ville.getId()}">Supprimer</a></td>
        </tr>
    </c:forEach>
</table>
</form>

<c:if test="${currentPage != 1}">
    <h3><a href="villes?page=${currentPage - 1}">Page précédente</a></h3>
</c:if>

<table class="pages">
    <tr>
        <c:forEach begin="1" end="${nbPages}" var="i">
            <c:choose>
                <c:when test="${currentPage eq i}">
                    <td>${i}</td>
                </c:when>
                <c:otherwise>
                    <td><a href="villes?page=${i}">${i}</a></td>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </tr>
</table>

<c:if test="${currentPage lt nbPages}">
    <h3><a href="villes?page=${currentPage + 1}">Page suivante</a></h3>
</c:if>
</body>
</html>
