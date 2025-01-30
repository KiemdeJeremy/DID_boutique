<%-- 
    Document   : livreurHeader
    Created on : 30 janv. 2025, 05:36:43
    Author     : USER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
       <div class="sidebar">
            <header>DID Boutique</header>
            <ul>
                <li><a href="${pageContext.request.contextPath}/index.jsp"><i class="fa-solid fa-house"></i>Acceuil</a></li>
                <li><a href="${pageContext.request.contextPath}/listLivraison"><i class="fa-solid fa-car-side"></i>Livraison</a></li>
                <li><a href="${pageContext.request.contextPath}/listDetailLivraison"><i class="fa-solid fa-paste"></i>Detail_Livraison</a></li>
            </ul>
        </div>
    </body>
</html>
