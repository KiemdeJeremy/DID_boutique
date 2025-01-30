<%@page import="java.util.ArrayList"%>
<%@page import="models.Mclient"%>
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
        <%
            String message = (String) session.getAttribute("message");
            List<String> erreurs = (List<String>) session.getAttribute("erreurs");
            if (message != null) {
                session.removeAttribute("message");
            }
            if (erreurs == null) {
                erreurs = new ArrayList<>();
            } else {
                session.removeAttribute("erreurs");
            }

            Mclient clientErreur = (Mclient) session.getAttribute("client");
            if (clientErreur == null) {
                clientErreur = new Mclient(); // Initialiser pour éviter NullPointerException
            }

            Mclient clientAchat = (Mclient) session.getAttribute("clientAchat");
            if (clientAchat == null) {
                clientAchat = new Mclient();
            }
        %>

        <div class="main-content">
            <div class="container-fluid mt-5">
                <div class="row">
                    <div class="col-12 text-right mb-3">
                        <button class="btn btn-primary" data-toggle="modal" data-target="#addClientModal">Ajouter Client</button>
                        <button class="btn btn-secondary" data-toggle="modal" data-target="#viewClientsModal">Consulter Clients</button>
                    </div>
                </div>

                <!-- Modal Ajouter Client -->
                <div class="modal fade" id="addClientModal" tabindex="-1" role="dialog">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title">Ajouter un Client</h5>
                                <button type="button" class="close" data-dismiss="modal">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <form action="${pageContext.request.contextPath}/clientEnregistrement" method="post">
                                    <div class="form-group">
                                        <label for="nom">Nom</label>
                                        <input type="text" class="form-control" name="nom" required  value="<%= clientErreur.getNom() != null ? clientErreur.getNom() : ""%>">
                                    </div>
                                    <div class="form-group">
                                        <label for="telephone">Téléphone</label>
                                        <input type="tel" class="form-control" name="telephone" required value="<%= clientErreur.getTelephone() != null ? clientErreur.getTelephone() : ""%>">
                                    </div>
                                    <button type="submit" class="btn btn-primary">Enregistrer</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Modal Voir Clients -->
                <div class="modal fade" id="viewClientsModal" tabindex="-1" role="dialog">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title">Liste des Clients</h5>
                                <button type="button" class="close" data-dismiss="modal">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <div class="table-responsive">
                                    <table id="clientTable" class="table table-bordered table-striped table-hover">
                                        <thead class="table-dark">
                                            <tr>
                                                <th scope="col">#</th>
                                                <th scope="col">Nom</th>
                                                <th scope="col">Téléphone</th>
                                                <th scope="col">Actions</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%
                                                List<Mclient> listClients = (List<Mclient>) request.getAttribute("listClients");
                                                if (listClients != null) {
                                                    for (Mclient client : listClients) {
                                            %>
                                            <tr>
                                                <td><%= client.getIdClient()%></td>
                                                <td><%= client.getNom()%></td>
                                                <td><%= client.getTelephone()%></td>
                                                <td>
                                                    <a href="#" class="view" data-toggle="modal" data-target="#viewModal<%= client.getIdClient()%>">
                                                        <i class="fa fa-eye mr-2"></i>
                                                    </a>
                                                    <a href="#" class="edit" data-toggle="modal" data-target="#updateModal<%= client.getIdClient()%>">
                                                        <i class="fa fa-pencil-alt text-success mr-2"></i>
                                                    </a>
                                                    <a href="#" class="delete" data-toggle="modal" data-target="#deleteModal<%= client.getIdClient()%>">
                                                        <i class="fa fa-trash text-danger mr-2"></i>
                                                    </a>
                                                </td>
                                            </tr>

                                            <!-- Modal Voir -->
                                        <div class="modal fade" id="viewModal<%= client.getIdClient()%>" tabindex="-1" role="dialog">
                                            <div class="modal-dialog" role="document">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <h5 class="modal-title">Détails du Client</h5>
                                                        <button type="button" class="close" data-dismiss="modal">
                                                            <span aria-hidden="true">&times;</span>
                                                        </button>
                                                    </div>
                                                    <div class="modal-body">
                                                        <p><strong>Nom:</strong> <%= client.getNom()%></p>
                                                        <p><strong>Téléphone:</strong> <%= client.getTelephone()%></p>
                                                    </div>
                                                    <div class="modal-footer">
                                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Fermer</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <!-- Modal Modifier -->
                                        <div class="modal fade" id="updateModal<%= client.getIdClient()%>" tabindex="-1" role="dialog">
                                            <div class="modal-dialog" role="document">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <h5 class="modal-title">Modifier le Client</h5>
                                                        <button type="button" class="close" data-dismiss="modal">
                                                            <span aria-hidden="true">&times;</span>
                                                        </button>
                                                    </div>
                                                    <div class="modal-body">
                                                        <form action="${pageContext.request.contextPath}/clientModification" method="post">
                                                            <input type="hidden" name="idClient" value="<%= client.getIdClient()%>">
                                                            <div class="form-group">
                                                                <label for="nom">Nom</label>
                                                                <input type="text" class="form-control" name="nom" value="<%= client.getNom()%>" required>
                                                            </div>
                                                            <div class="form-group">
                                                                <label for="telephone">Téléphone</label>
                                                                <input type="tel" class="form-control" name="telephone" value="<%= client.getTelephone()%>" required>
                                                            </div>
                                                            <button type="submit" class="btn btn-primary">Sauvegarder</button>
                                                        </form>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <!-- Modal Supprimer -->
                                        <div class="modal fade" id="deleteModal<%= client.getIdClient()%>" tabindex="-1" role="dialog">
                                            <div class="modal-dialog" role="document">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <h5 class="modal-title">Confirmation de Suppression</h5>
                                                        <button type="button" class="close" data-dismiss="modal">
                                                            <span aria-hidden="true">&times;</span>
                                                        </button>
                                                    </div>
                                                    <div class="modal-body">
                                                        Êtes-vous sûr de vouloir supprimer le client <%= client.getNom()%> ?
                                                    </div>
                                                    <div class="modal-footer">
                                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Non</button>
                                                        <a href="${pageContext.request.contextPath}/clientSuppression?idClient=<%= client.getIdClient()%>" class="btn btn-danger">Oui</a>
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
                            </div>
                        </div>
                    </div>
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

                <!-- jQuery and Bootstrap JS -->
                <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
                <script src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.min.js"></script>
                <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

                <script>
                    $(document).ready(function () {
                        $('#clientTable').DataTable();

                        // Gestion des modales de notification
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
            </div>
        </div>
    </body>
</html>