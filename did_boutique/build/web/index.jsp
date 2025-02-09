<%@page import="java.util.List"%>
<%@page import="models.Mproduit"%>
<%@page import="models.Mutilisateur"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" />
        <link href="https://unpkg.com/boxicons@2.1.1/css/boxicons.min.css" rel="stylesheet" />
        <link rel="stylesheet" href="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.min.css">
        <link rel="stylesheet" type="text/css" href="css/style.css" />
    </head>
    <body>
        <input type="checkbox" id="check">
        <label for="check">
            <i class="fas fa-bars" id="btn"></i>
            <i class="fas fa-times" id="cancel"></i>
        </label>

        <%
            Mutilisateur userConnect = (Mutilisateur) session.getAttribute("userConnect");
            if (userConnect == null) {
                request.getRequestDispatcher("/connexion.jsp").forward(request, response);
            }
            String headerJSP = (String) session.getAttribute("headerJSP");
            if (headerJSP == null) {
                headerJSP = "/vues/mesInclusions/adminHeader.jsp"; // En cas de problème avec l'attribut de session
            }
        %>

        <jsp:include page="<%=headerJSP%>" />

        <div class="main-content">
            <div class="container-fluid">
                <div class="row d-flex justify-content-center align-items-center" style="height: 100px;">
                    <div>
                        <p class="h1">Bienvenue sur <mark>DID Boutique </mark> !</p>
                        <i class="h4">Votre satisfaction notre priorité !</i>
                    </div>
                </div><br><br>
                <div class="row">

                    <%
                        List<Mproduit> listDesProduits = (List<Mproduit>) session.getAttribute("listDesProduits");
                        for (Mproduit produit : listDesProduits) {
                            String cheminComplet = produit.getImage(); // Par exemple: C:/Users/USER/Documents/NetBeansProjects/produits/src/java/uploads/image1.jpg
                            String nomImage = cheminComplet.substring(cheminComplet.lastIndexOf('/') + 1);
                    %>
                    <div class="col-xs-6 col-lg-6 col-xl-4 mb-3">
                        <div class="card d-flex flex-column" style="width: 18rem;">
                            <img src="uploads/<%= nomImage%>" class="card-img-top" alt="Image du produit">
                            <div class="card-body d-flex flex-column">
                                <h5 class="card-title"><%= produit.getNom()%></h5>
                                <p class="card-text"><strong>Prix : <%= produit.getPrix()%> FCFA</strong></p>
                                <!-- Alignement des boutons sur une même ligne
                                <div class="d-flex">
                                    <a href="#" class="btn btn-success btn-sm" 
                                       
                                        <i class="fas fa-plus"></i> Ajouter
                                    </a>
                                    <a href="#" class="btn btn-warning btn-sm ml-2" data-toggle="modal" data-target="#fenetre">
                                        <i class="fas fa-eye"></i> Voir
                                    </a>
                                </div> -->
                            </div>
                        </div>
                    </div>
                    <%
                        }
                    %>
                </div>
            </div>

            <!-- Bootstrap & jQuery -->
            <!-- jQuery -->
            <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
            <!-- DataTables -->
            <script src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.min.js"></script>
            <!-- Bootstrap (ajoutez uniquement si nécessaire après DataTables) -->
            <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
            <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

            <script>
                $(document).ready(function () {
                    $('#citoyensTable').DataTable({
                        "pagingType": "full_numbers", // Type de pagination (simple, full, simple_numbers, full_numbers)
                        "language": {
                            "url": "//cdn.datatables.net/plug-ins/1.11.5/i18n/fr_fr.json",
                            "search": "Recherche :", // Texte du label de recherche
                            "lengthMenu": "Afficher _MENU_ entrées par page", // Texte pour le menu déroulant des entrées
                            "info": "Affichage de _START_ à _END_ sur _TOTAL_ entrées", // Texte d'information sous le tableau
                            "paginate": {
                                "first": "<<", // Texte pour le bouton 'Premier'
                                "last": ">>", // Texte pour le bouton 'Dernier'
                                "next": ">", // Texte pour le bouton 'Suivant'
                                "previous": "<" // Texte pour le bouton 'Précédent'
                            }
                        }
                    });
                });

            </script>
        </div>
    </body>
</html>
