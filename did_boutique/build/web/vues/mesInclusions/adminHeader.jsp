<%-- 
    Document   : adminHeader
    Created on : 30 janv. 2025, 04:40:32
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
                <li><a href="${pageContext.request.contextPath}/listUtilisateur"><i class="fa-solid fa-house"></i>Utilisateur</a></li>
                <li><a href="${pageContext.request.contextPath}/listFournisseur"><i class="fa-brands fa-ubuntu"></i>Fournisseur</a></li>
                <li><a href="${pageContext.request.contextPath}/listAchat"><i class="fa-solid fa-cart-shopping"></i>Achat</a></li>
                <li><a href="${pageContext.request.contextPath}/listClient"><i class="fa-solid fa-id-card"></i>Client</a></li>
                <li><a href="${pageContext.request.contextPath}/listLivraison"><i class="fa-solid fa-car-side"></i>Livraison</a></li>
                <li><a href="${pageContext.request.contextPath}/listDetailLivraison"><i class="fa-solid fa-paste"></i>Detail_Livraison</a></li>
                <li><a href="${pageContext.request.contextPath}/listDetailAchat"><i class="fa-solid fa-cash-register"></i>Detail_Achat</a></li>
                <li><a href="${pageContext.request.contextPath}/listCredit"><i class="fa-solid fa-circle-dollar-to-slot"></i>Credit</a></li>
                <li><a href="${pageContext.request.contextPath}/listProduit"><i class="fa-solid fa-gift"></i>Produit</a></li>
            </ul>
        </div>
    </body>
</html>
