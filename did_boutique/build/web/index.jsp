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
            String headerJSP = (String) session.getAttribute("headerJSP");
            if (headerJSP == null) {
                headerJSP = "/vues/mesInclusions/adminHeader.jsp"; // En cas de problème avec l'attribut de session
            }
        %>

        <jsp:include page="<%=headerJSP%>" />

        <div class="main-content">
            <%
                Mutilisateur userConnect = null;
                userConnect = (Mutilisateur) session.getAttribute("userConnect");
                if (userConnect != null) {
                    
                }

            %>
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
    </body>
</html>
