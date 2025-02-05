<%@page import="models.Mlivraison"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
    <%
        String headerJSP = (String) session.getAttribute("headerJSP");
        if (headerJSP == null) {
            headerJSP = "/vues/mesInclusions/adminHeader.jsp"; // En cas de problème avec l'attribut de session
        }
    %>

    <jsp:include page="<%=headerJSP%>" />
    <body>
        <div class="main-content">
            <div class="container-fluid">

                <div class="row text-center mb-4">
                    <div class="col-2">

                    </div>
                    <div class="col-8">
                        <img src="${pageContext.request.contextPath}/images/livraison1.jpeg" alt="image camion" class="mt-3 mr-4 " style="width: 210px; height: 180px;" />
                    </div> <br> <br> <br>   <br> <br> <br>   
                </div>
                <div class="row">   
                    <div class="col-4">
                        <div class="btn-primary p-4 mb-4">
                            <h3 class="text-center">Enregistrer une Livraison</h3>
                            <%
                                // Récupération de la livraison de la session
                                Mlivraison livraisonTransmise = (Mlivraison) session.getAttribute("livraison");
                                if (livraisonTransmise != null) {
                                    session.removeAttribute("livraison");
                                }
                            %>
                            <form action="${pageContext.request.contextPath}/livraisonEnregistrement" method="post">
                                <div class="form-group">
                                    <label for="dateLivraison">Date de Livraison</label>
                                    <input type="date" class="form-control" name="dateLivraison" required 
                                           value="<%= livraisonTransmise != null ? livraisonTransmise.getDateLivraison() : ""%>">
                                </div>
                                <div class="form-group">
                                    <label for="libelle">Libellé</label>
                                    <input type="text" class="form-control" name="libelle" required 
                                           value="<%= livraisonTransmise != null ? livraisonTransmise.getLibelle() : ""%>">
                                </div>
                                <div class="form-group">
                                    <label for="montantLivraison">Montant</label>
                                    <input type="number" class="form-control" name="montantLivraison" required 
                                           value="<%= livraisonTransmise != null ? livraisonTransmise.getMontantLivraison() : ""%>">
                                </div>
                                <button type="submit" class="btn btn-warning">Enregistrer</button>
                            </form>
                        </div>
                    </div>

                    <div class="col-8">
                        <h2 class="text-center">Liste des Livraisons</h2>

                        <%
                            String messageLivraison = (String) session.getAttribute("messageLivraison");
                            List<String> erreursLivraison = (List<String>) session.getAttribute("erreursLivraison");
                            if (messageLivraison != null) {
                                session.removeAttribute("message");
                            }
                            if (erreursLivraison != null) {
                                session.removeAttribute("erreursLivraison");
                            }
                        %>

                        <div class="table-responsive">
                            <table id="livraisonTable" class="table table-bordered table-striped table-hover">
                                <thead class="table-dark">
                                    <tr>
                                        <th>#</th>
                                        <th>Date de Livraison</th>
                                        <th>Libellé</th>
                                        <th>Montant</th>
                                        <th>Actions</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        List<Mlivraison> listLivraisons = (List<Mlivraison>) request.getAttribute("listLivraisons");
                                        if (listLivraisons != null) {
                                            for (Mlivraison livraison : listLivraisons) {
                                    %>
                                    <tr>
                                        <td><%= livraison.getIdLivraison()%></td>
                                        <td><%= livraison.getDateLivraison()%></td>
                                        <td><%= livraison.getLibelle()%></td>
                                        <td><%= livraison.getMontantLivraison()%></td>
                                        <td>
                                            <a href="#" class="view" data-toggle="modal" data-target="#viewModal<%= livraison.getIdLivraison()%>"> <i class="fa fa-eye mr-2 fa-lg"></i></a>
                                            <a href="#" class="edit" data-toggle="modal" data-target="#updateModal<%= livraison.getIdLivraison()%>"><i class="fa fa-pencil-alt text-success mr-2 fa-lg"></i></a>
                                            <a href="#" class="delete" data-toggle="modal" data-target="#deleteModal<%= livraison.getIdLivraison()%>"><i class="fa fa-trash text-danger mr-2 fa-lg"></i></a>
                                        </td>
                                    </tr>

                                    <!-- Modal Voir -->
                                <div class="modal fade" id="viewModal<%= livraison.getIdLivraison()%>" tabindex="-1" role="dialog">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title">Détails de la Livraison</h5>
                                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                            </div>
                                            <div class="modal-body">
                                                <p><strong>Date de Livraison:</strong> <%= livraison.getDateLivraison()%></p>
                                                <p><strong>Libellé:</strong> <%= livraison.getLibelle()%></p>
                                                <p><strong>Montant:</strong> <%= livraison.getMontantLivraison()%></p>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Fermer</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <!-- Modal Modifier -->
                                <div class="modal fade" id="updateModal<%= livraison.getIdLivraison()%>" tabindex="-1" role="dialog">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title">Modifier la Livraison</h5>
                                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                            </div>
                                            <div class="modal-body">
                                                <form action="${pageContext.request.contextPath}/livraisonModification" method="post">
                                                    <input type="hidden" name="idLivraison" value="<%= livraison.getIdLivraison()%>">
                                                    <div class="form-group">
                                                        <label>Date de Livraison</label>
                                                        <input type="date" class="form-control" name="dateLivraison" value="<%= livraison.getDateLivraison()%>" required>
                                                    </div>
                                                    <div class="form-group">
                                                        <label>Libellé</label>
                                                        <input type="text" class="form-control" name="libelle" value="<%= livraison.getLibelle()%>" required>
                                                    </div>
                                                    <div class="form-group">
                                                        <label>Montant</label>
                                                        <input type="number" class="form-control" name="montantLivraison" value="<%= livraison.getMontantLivraison()%>" required>
                                                    </div>
                                                    <button type="submit" class="btn btn-primary">Sauvegarder</button>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <!-- Modal Supprimer -->
                                <div class="modal fade" id="deleteModal<%= livraison.getIdLivraison()%>" tabindex="-1" role="dialog">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title">Confirmation de Suppression</h5>
                                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                            </div>
                                            <div class="modal-body">
                                                Êtes-vous sûr de vouloir supprimer la livraison <%= livraison.getLibelle()%> ?
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Non</button>
                                                <a href="${pageContext.request.contextPath}/livraisonSuppression?idLivraison=<%= livraison.getIdLivraison()%>" class="btn btn-danger">Oui</a>
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

                        <!-- Modals de notification -->
                        <div class="modal fade" id="messageModal" tabindex="-1" role="dialog">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title">Notification</h5>
                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                    </div>
                                    <div class="modal-body">
                                        <%= messageLivraison != null ? messageLivraison : ""%>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-primary" data-dismiss="modal">Fermer</button>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="modal fade" id="erreurModal" tabindex="-1" role="dialog">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title">Notification d'erreur</h5>
                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                    </div>
                                    <div class="modal-body">
                                        <% if (erreursLivraison != null && !erreursLivraison.isEmpty()) { %>
                                        <ul class="list-unstyled">
                                            <% for (String erreur : erreursLivraison) {%>
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
                    $('#livraisonTable').DataTable({
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

                    var messageLivraison = "<%= messageLivraison%>";
                    if (messageLivraison && messageLivraison !== "null" && messageLivraison !== "") {
                        $('#messageModal').modal('show');
                        setTimeout(function () {
                            $('#messageModal').modal('hide');
                        }, 2000);
                    }

                <% if (erreursLivraison != null && !erreursLivraison.isEmpty()) { %>
                    $('#erreurModal').modal('show');
                <% }%>
                });
            </script>
    </body>
</html>