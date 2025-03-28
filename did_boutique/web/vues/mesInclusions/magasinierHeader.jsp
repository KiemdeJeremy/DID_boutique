<%-- 
    Document   : magasinierHeader
    Created on : 30 janv. 2025, 05:40:35
    Author     : USER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <input type="checkbox" id="check">
        <label for="check">
            <i class="fas fa-bars" id="btn"></i>
            <i class="fas fa-times" id="cancel"></i>
        </label>
    <head>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" />
        <link rel="stylesheet" href="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.min.css">
        <link rel="stylesheet" type="text/css" href="css/style.css" />
    </head>
    <body>
        <div class="sidebar">
            <header>DID Boutique</header>
            <ul>
                <li><a href="${pageContext.request.contextPath}/accueilController"><i class="fa-solid fa-house"></i>Accueil</a></li>
                <li><a href="${pageContext.request.contextPath}/listProduit"><i class="fa-solid fa-gift"></i>Produit</a></li>
                <li><a href="${pageContext.request.contextPath}/listLivraison"><i class="fa-solid fa-car-side"></i>Livraison</a></li>
                <li><a href="${pageContext.request.contextPath}/listDetailLivraison"><i class="fa-solid fa-paste"></i>Detail_Livraison</a></li>
                <li><a href="${pageContext.request.contextPath}/connexionServlet" class="text-danger"><i class="fa-solid fa-right-from-bracket text-danger"></i>Déconnexion</a></li>
            </ul>
        </div>
    </body>
</html>
