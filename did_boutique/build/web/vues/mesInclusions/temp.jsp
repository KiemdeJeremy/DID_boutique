<%@page import="models.Mclient"%>
<%@page import="models.Mutilisateur"%>
<%@page import="models.Machat"%>
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
            <div class="container-fluid mt-5">
                <div class="row">   
                    <div class="col-4">
                        <div class="bg-light p-4 mb-4">
                            <h3 class="text-center">Enregistrer un Achat</h3>
                            <%
                                // Récupération de l'achat de la session
                                Machat achatTransmis = (Machat) session.getAttribute("achat");
                                List<String> erreursAchat = (List<String>) session.getAttribute("erreursAchat");
                                String messageAchat = (String) session.getAttribute("messageAchat");

                                // Suppression des attributs de session après récupération
                                session.removeAttribute("achat");
                                session.removeAttribute("erreursAchat");
                                session.removeAttribute("messageAchat");

                                Mutilisateur userConnect = null;
                                userConnect = (Mutilisateur) session.getAttribute("userConnect");
                                Mclient clientAchat = (Mclient) session.getAttribute("clientAchat");

                            %>
                            <form action="${pageContext.request.contextPath}/achatEnregistrement" method="post">
                                <div class="form-group">
                                    <label for="dateAchat">Date d'Achat</label>
                                    <input type="date" class="form-control" name="dateAchat" required 
                                           value="<%= achatTransmis != null ? achatTransmis.getDateAchat() : ""%>">
                                </div>
                                <div class="form-group">
                                    <label for="montant">Montant</label>
                                    <input type="number" class="form-control" name="montant" step="0.01" required 
                                           value="<%= achatTransmis != null ? achatTransmis.getMontant() : ""%>">
                                </div>
                                <div class="form-group">
                                    <label for="sommeEncaisse">Somme Encaissée</label>
                                    <input type="number" class="form-control" name="sommeEncaisse" step="0.01" required 
                                           value="<%= achatTransmis != null ? achatTransmis.getSommeEncaisse() : ""%>">
                                </div>
                                <div class="form-group">
                                    <label for="remise">Remise</label>
                                    <input type="number" class="form-control" name="remise" step="0.01" 
                                           value="<%= achatTransmis != null ? achatTransmis.getRemise() : ""%>">
                                </div>
                                <div class="form-group">
                                    <label for="idUtilisateur">ID Utilisateur</label>
                                    <input type="text" class="form-control" name="idUtilisateur" required 
                                           value="<%= userConnect != null ? userConnect.getIdUtilisateur() + "-" + userConnect.getNom() : ""%>" 
                                           readonly>
                                </div>
                                <div class="form-group">
                                    <label for="idClient">ID Client</label>
                                    <input type="text" class="form-control" name="idClient" required 
                                           value="<%= clientAchat != null ? clientAchat.getIdClient() + "-" + clientAchat.getNom() : ""%>" 
                                           readonly>
                                </div>
                                <button type="submit" class="btn btn-primary">Enregistrer</button>
                            </form>
                        </div>
                    </div>

                    <div class="col-8">
                        <h2 class="text-center">Liste des Achats</h2>
                        <div class="table-responsive">
                            <table id="achatTable" class="table table-bordered table-striped table-hover">
                                <thead class="table-dark">
                                    <tr>
                                        <th scope="col">#</th>
                                        <th scope="col">Date d'Achat</th>
                                        <th scope="col">Montant</th>
                                        <th scope="col">Somme Encaissée</th>
                                        <th scope="col">Remise</th>
                                        <th scope="col">ID Utilisateur</th>
                                        <th scope="col">ID Client</th>
                                        <th scope="col">Actions</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        List<Machat> listAchats = (List<Machat>) request.getAttribute("listAchats");
                                        if (listAchats != null) {
                                            for (Machat achat : listAchats) {
                                    %>
                                    <tr>
                                        <td><%= achat.getIdAchat()%></td>
                                        <td><%= achat.getDateAchat()%></td>
                                        <td><%= achat.getMontant()%></td>
                                        <td><%= achat.getSommeEncaisse()%></td>
                                        <td><%= achat.getRemise()%></td>
                                        <td><%= achat.getIdUtilisateur()%></td>
                                        <td><%= achat.getIdClient()%></td>
                                        <td>
                                            <a href="#" class="edit" title="Modifier" data-toggle="modal" data-target="#updateModal<%= achat.getIdAchat()%>">
                                                <i class="fa fa-pencil-alt text-success mr-2 fa-lg"></i>
                                            </a>
                                            <a href="#" class="delete" title="Supprimer" data-toggle="modal" data-target="#deleteModal<%= achat.getIdAchat()%>">
                                                <i class="fa fa-trash text-danger mr-2 fa-lg"></i>
                                            </a>
                                        </td>
                                    </tr>

                                    <!-- Modal Modifier -->
                                <div class="modal fade" id="updateModal<%= achat.getIdAchat()%>" tabindex="-1" role="dialog">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title">Modifier l'Achat</h5>
                                                <button type="button" class="close" data-dismiss="modal">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                            </div>
                                            <div class="modal-body">
                                                <form action="${pageContext.request.contextPath}/achatModification" method="post">
                                                    <input type="hidden" name="idAchat" value="<%= achat.getIdAchat()%>">
                                                    <div class="form-group">
                                                        <label for="dateAchat">Date d'Achat</label>
                                                        <input type="date" class="form-control" name="dateAchat" value="<%= achat.getDateAchat()%>" required>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="montant">Montant</label>
                                                        <input type="number" class="form-control" name="montant" value="<%= achat.getMontant()%>" step="0.01" required>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="sommeEncaisse">Somme Encaissée</label>
                                                        <input type="number" class="form-control" name="sommeEncaisse" value="<%= achat.getSommeEncaisse()%>" step="0.01" required>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="remise">Remise</label>
                                                        <input type="number" class="form-control" name="remise" value="<%= achat.getRemise()%>" step="0.01">
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="idUtilisateur">ID Utilisateur</label>
                                                        <input type="text" class="form-control" name="idUtilisateur" required 
                                                               value="<%= userConnect != null ? userConnect.getIdUtilisateur() + "-" + userConnect.getNom() : ""%>" 
                                                               readonly>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="idClient">ID Client</label>
                                                        <input type="text" class="form-control" name="idClient" required 
                                                               value="<%= clientAchat != null ? clientAchat.getIdClient() + "-" + clientAchat.getNom() : ""%>" 
                                                               readonly>
                                                    </div>
                                                    <button type="submit" class="btn btn-primary">Sauvegarder</button>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <!-- Modal Supprimer -->
                                <div class="modal fade" id="deleteModal<%= achat.getIdAchat()%>" tabindex="-1" role="dialog">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title">Confirmation de Suppression</h5>
                                                <button type="button" class="close" data-dismiss="modal">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                            </div>
                                            <div class="modal-body">
                                                Êtes-vous sûr de vouloir supprimer cet achat ?
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Non</button>
                                                <a href="${pageContext.request.contextPath}/achatSuppression?idAchat=<%= achat.getIdAchat()%>" class="btn btn-danger">Oui</a>
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
                        <div class="modal fade" id="messageAchatModal" tabindex="-1" role="dialog">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title"><i class="fa-solid fa-thumbs-up text-success fa-2x"></i> Notification</h5>
                                        <button type="button" class="close" data-dismiss="modal">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <%= messageAchat != null ? messageAchat : ""%>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-primary" data-dismiss="modal">Fermer</button>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- Modal de notification erreur -->
                        <div class="modal fade" id="erreursAchatModal" tabindex="-1" role="dialog">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title"><i class="fa-solid fa-circle-exclamation text-danger fa-2x"></i> Notification d'erreur</h5>
                                        <button type="button" class="close" data-dismiss="modal">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <% if (erreursAchat != null && !erreursAchat.isEmpty()) { %>
                                        <ul class="list-unstyled">
                                            <% for (String error : erreursAchat) {%>
                                            <li class="d-flex align-items-center mb-2">
                                                <i class="fa-solid fa-times-circle mr-2 text-danger"></i>
                                                <span><%= error%></span>
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
                    $('#achatTable').DataTable({
                        "pagingType": "full_numbers",
                        "language": {
                            "url": "//cdn.datatables.net/plug-ins/1.11.5/i18n/fr_fr.json",
                            "search": "Recherche :",
                            "lengthMenu": "Afficher _MENU_ entrées par page",
                            "info": "Affichage de _START_ à _END_ sur _TOTAL_ entrées",
                            "paginate": {
                                "first": "<<",
                                "last": ">>",
                                "next": ">",
                                "previous": "<"
                            }
                        }
                    });

                    var messageAchat = "<%= messageAchat%>";
                    if (messageAchat && messageAchat !== "null" && messageAchat !== "") {
                        $('#messageAchatModal').modal('show');
                        setTimeout(function () {
                            $('#messageAchatModal').modal('hide');
                        }, 2000);
                    }

                <% if (erreursAchat != null && !erreursAchat.isEmpty()) { %>
                    $('#erreursAchatModal').modal('show');
                <% }%>
                });
            </script>
    </body>
</html>