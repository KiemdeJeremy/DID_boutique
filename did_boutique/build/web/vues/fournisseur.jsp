<%@page import="models.Mfournisseur"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" />
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
            <div class="container-fluid mt-5">
                <div class="row">   
                    <div class="col-4">
                        <div class="bg-light p-4 mb-4">
                            <h3 class="text-center">Enregistrer un Fournisseur</h3>
                            <%
                                // Récupération du fournisseur de la session
                                Mfournisseur fournisseurTransmis = (Mfournisseur) session.getAttribute("fournisseur");
                                // On le supprime immédiatement de la session
                                if (fournisseurTransmis != null) {
                                    session.removeAttribute("fournisseur");
                                }
                            %>
                            <form action="${pageContext.request.contextPath}/fournisseurEnregistrement" method="post">
                                <div class="form-group">
                                    <label for="nom">Nom</label>
                                    <input type="text" class="form-control" name="nom" required 
                                           value="<%= fournisseurTransmis != null ? fournisseurTransmis.getNom() : ""%>">
                                </div>
                                <div class="form-group">
                                    <label for="prenom">Prénom</label>
                                    <input type="text" class="form-control" name="prenom" required 
                                           value="<%= fournisseurTransmis != null ? fournisseurTransmis.getPrenom() : ""%>">
                                </div>
                                <div class="form-group">
                                    <label for="telephone1">Téléphone 1</label>
                                    <input type="tel" class="form-control" name="telephone1" required 
                                           value="<%= fournisseurTransmis != null ? fournisseurTransmis.getTelephone1() : ""%>">
                                </div>
                                <div class="form-group">
                                    <label for="telephone2">Téléphone 2</label>
                                    <input type="tel" class="form-control" name="telephone2" 
                                           value="<%= fournisseurTransmis != null ? fournisseurTransmis.getTelephone2() : ""%>">
                                </div>
                                <div class="form-group">
                                    <label for="adresse">Adresse</label>
                                    <input type="text" class="form-control" name="adresse" required 
                                           value="<%= fournisseurTransmis != null ? fournisseurTransmis.getAdresse() : ""%>">
                                </div>
                                <button type="submit" class="btn btn-primary">Enregistrer</button>
                            </form>
                        </div>
                    </div>

                    <div class="col-8">
                        <h2 class="text-center">Liste des Fournisseurs</h2>

                        <%
                            String message = (String) session.getAttribute("message");
                            List<String> erreurs = (List<String>) session.getAttribute("erreurs");
                            if (message != null) {
                                session.removeAttribute("message");
                            }
                            if (erreurs != null) {
                                session.removeAttribute("erreurs");
                            }
                        %>

                        <div class="table-responsive">
                            <table id="fournisseurTable" class="table table-bordered table-striped table-hover">
                                <thead class="table-dark">
                                    <tr>
                                        <th scope="col">#</th>
                                        <th scope="col">Nom</th>
                                        <th scope="col">Prénom</th>
                                        <th scope="col">Téléphone 1</th>
                                        <th scope="col">Téléphone 2</th>
                                        <th scope="col">Adresse</th>
                                        <th scope="col">Actions</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        List<Mfournisseur> listFournisseurs = (List<Mfournisseur>) request.getAttribute("listFournisseurs");
                                        if (listFournisseurs != null) {
                                            for (Mfournisseur fournisseur : listFournisseurs) {
                                    %>
                                    <tr>
                                        <td><%= fournisseur.getIdFournisseur()%></td>
                                        <td><%= fournisseur.getNom()%></td>
                                        <td><%= fournisseur.getPrenom()%></td>
                                        <td><%= fournisseur.getTelephone1()%></td>
                                        <td><%= fournisseur.getTelephone2()%></td>
                                        <td><%= fournisseur.getAdresse()%></td>
                                        <td>
                                            <a href="#" class="view" title="Voir" data-toggle="modal" data-target="#viewModal<%= fournisseur.getIdFournisseur()%>">
                                                <i class="fa fa-eye mr-2 fa-lg"></i>
                                            </a>
                                            <a href="#" class="edit" title="Modifier" data-toggle="modal" data-target="#updateModal<%= fournisseur.getIdFournisseur()%>">
                                                <i class="fa fa-pencil-alt text-success mr-2 fa-lg"></i>
                                            </a>
                                            <a href="#" class="delete" title="Supprimer" data-toggle="modal" data-target="#deleteModal<%= fournisseur.getIdFournisseur()%>">
                                                <i class="fa fa-trash text-danger mr-2 fa-lg"></i>
                                            </a>
                                        </td>
                                    </tr>

                                    <!-- Modal Voir -->
                                <div class="modal fade" id="viewModal<%= fournisseur.getIdFournisseur()%>" tabindex="-1" role="dialog">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title">Détails du Fournisseur</h5>
                                                <button type="button" class="close" data-dismiss="modal">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                            </div>
                                            <div class="modal-body">
                                                <p><strong>Nom:</strong> <%= fournisseur.getNom()%></p>
                                                <p><strong>Prénom:</strong> <%= fournisseur.getPrenom()%></p>
                                                <p><strong>Téléphone 1:</strong> <%= fournisseur.getTelephone1()%></p>
                                                <p><strong>Téléphone 2:</strong> <%= fournisseur.getTelephone2()%></p>
                                                <p><strong>Adresse:</strong> <%= fournisseur.getAdresse()%></p>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Fermer</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <!-- Modal Modifier -->
                                <div class="modal fade" id="updateModal<%= fournisseur.getIdFournisseur()%>" tabindex="-1" role="dialog">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title">Modifier le Fournisseur</h5>
                                                <button type="button" class="close" data-dismiss="modal">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                            </div>
                                            <div class="modal-body">
                                                <form action="${pageContext.request.contextPath}/fournisseurModification" method="post">
                                                    <input type="hidden" name="idFournisseur" value="<%= fournisseur.getIdFournisseur()%>">
                                                    <div class="form-group">
                                                        <label for="nom">Nom</label>
                                                        <input type="text" class="form-control" name="nom" value="<%= fournisseur.getNom()%>" required>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="prenom">Prénom</label>
                                                        <input type="text" class="form-control" name="prenom" value="<%= fournisseur.getPrenom()%>" required>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="telephone1">Téléphone 1</label>
                                                        <input type="tel" class="form-control" name="telephone1" value="<%= fournisseur.getTelephone1()%>" required>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="telephone2">Téléphone 2</label>
                                                        <input type="tel" class="form-control" name="telephone2" value="<%= fournisseur.getTelephone2()%>">
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="adresse">Adresse</label>
                                                        <input type="text" class="form-control" name="adresse" value="<%= fournisseur.getAdresse()%>" required>
                                                    </div>
                                                    <button type="submit" class="btn btn-primary">Sauvegarder</button>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <!-- Modal Supprimer -->
                                <div class="modal fade" id="deleteModal<%= fournisseur.getIdFournisseur()%>" tabindex="-1" role="dialog">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title">Confirmation de Suppression</h5>
                                                <button type="button" class="close" data-dismiss="modal">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                            </div>
                                            <div class="modal-body">
                                                Êtes-vous sûr de vouloir supprimer le fournisseur <%= fournisseur.getNom()%> <%= fournisseur.getPrenom()%> ?
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Non</button>
                                                <a href="${pageContext.request.contextPath}/fournisseurSuppression?idFournisseur=<%= fournisseur.getIdFournisseur()%>" class="btn btn-danger">Oui</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <%
                                        }
                                    }
                                %>
                                </tbody>
                            </table>
                        </div>

                        <!-- Modal de notification message -->
                        <div class="modal fade" id="messageModal" tabindex="-1" role="dialog">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title"><i class="fa-solid fa-thumbs-up text-success fa-2x"></i> Notification</h5>
                                        <button type="button" class="close" data-dismiss="modal">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <%= message != null ? message : ""%>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-primary" data-dismiss="modal">Fermer</button>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- Modal de notification erreur -->
                        <div class="modal fade" id="erreurModal" tabindex="-1" role="dialog">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title"><i class="fa-solid fa-circle-exclamation text-danger fa-2x"></i> Notification d'erreur</h5>
                                        <button type="button" class="close" data-dismiss="modal">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <% if (erreurs != null && !erreurs.isEmpty()) { %>
                                        <ul class="list-unstyled">
                                            <% for (String erreur : erreurs) {%>
                                            <li class="d-flex align-items-center mb-2">
                                                <i class="fa-solid fa-times-circle mr-2 text-danger"></i>
                                                <span><%= erreur%></span>
                                            </li>
                                            <% } %>
                                        </ul>
                                        <% }%>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-primary" data-dismiss="modal">Fermer</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>

            <!-- jQuery and Bootstrap JS -->
            <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
            <script src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.min.js"></script>
            <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
            <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

            <script>
                $(document).ready(function () {
                    $('#fournisseurTable').DataTable({
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

                    var message = "<%= message%>";
                    if (message && message !== "null" && message !== "") {
                        $('#messageModal').modal('show');
                        setTimeout(function () {
                            $('#messageModal').modal('hide');
                        }, 2000);
                    }

                <% if (erreurs != null && !erreurs.isEmpty()) { %>
                    $('#erreurModal').modal('show');
                <% }%>
                });
            </script>
    </body>
</html>