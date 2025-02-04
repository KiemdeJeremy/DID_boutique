<%-- 
    Document   : credit
    Created on : 23 janv. 2025, 20:13:48
    Author     : USER
--%>

<%@page import="models.Mcredit"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
            String messageCredit = (String) session.getAttribute("messageCredit");
            if (messageCredit != null) {
                session.removeAttribute("messageCredit");
            }
        %>

        <div class="main-content">
            <div class="row text-center mb-4">
                <div class="col-2">

                </div>
                <div class="col-8">
                    <img src="${pageContext.request.contextPath}/images/DID.jpg" alt="logo de DID" class="mt-3 mr-4 " style="width: 180px; height: 150px;" />
                </div>        
            </div>
            <div class="row text-center mb-2">
                <div class="col-2">

                </div>
                <div class="col-8">
                    <p class="h1"><strong>Historique des crédits</strong></p>
                </div>        
            </div>
            <div class="row">

                <div class="col-1">

                </div>
                <div class="col-10">
                    <table id="creditTable" class="table table-bordered table-striped table-hover">
                        <thead class="table-dark">
                            <tr>
                                <th scope="col">#</th>
                                <th scope="col">Date de Crédit</th>
                                <th scope="col">Montant</th>
                                <th scope="col">Date de Règlement</th>
                                <th scope="col">Statut</th>
                                <th scope="col">Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%            List<Mcredit> listCredits = (List<Mcredit>) session.getAttribute("listCredits");
                                if (listCredits != null) {
                                    for (Mcredit credit : listCredits) {
                            %>
                            <tr>
                                <td><%= credit.getIdCredit()%></td>
                                <td><%= credit.getDateCredit()%></td>
                                <td><%= credit.getMontantCredit()%></td>
                                <td><%= credit.getDateReglement()%></td>
                                <td>
                                    <i class="<%= credit.getStatut().equals("paye") ? "fa fa-thumbs-up text-success" : "fa fa-times-circle text-danger"%>"></i>
                                    <%= credit.getStatut()%>
                                </td>
                                <td>
                                    <a href="#" class="view" title="Voir" data-toggle="modal" data-target="#viewModal<%= credit.getIdCredit()%>">
                                        <i class="fa fa-eye mr-2 fa-lg"></i>
                                    </a>
                                    <a href="#" class="edit" title="Modifier" data-toggle="modal" data-target="#updateModal<%= credit.getIdCredit()%>">
                                        <i class="fa fa-pencil-alt text-success mr-2 fa-lg"></i>
                                    </a>
                                    <a href="#" class="delete" title="Supprimer" data-toggle="modal" data-target="#deleteModal<%= credit.getIdCredit()%>">
                                        <i class="fa fa-trash text-danger mr-2 fa-lg"></i>
                                    </a>
                                </td>
                            </tr>

                            <!-- Modal Voir -->
                        <div class="modal fade" id="viewModal<%= credit.getIdCredit()%>" tabindex="-1" role="dialog">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title">Détails du Crédit</h5>
                                        <button type="button" class="close" data-dismiss="modal">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <p><strong>Date de Crédit:</strong> <%= credit.getDateCredit()%></p>
                                        <p><strong>Montant:</strong> <%= credit.getMontantCredit()%></p>
                                        <p><strong>Date de Règlement:</strong> <%= credit.getDateReglement()%></p>
                                        <p><strong>Statut:</strong> <%= credit.getStatut()%></p>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Fermer</button>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- Modal Modifier -->
                        <div class="modal fade" id="updateModal<%= credit.getIdCredit()%>" tabindex="-1" role="dialog">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title">Modifier le Crédit</h5>
                                        <button type="button" class="close" data-dismiss="modal">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <form action="${pageContext.request.contextPath}/creditModification" method="post">
                                            <input type="hidden" name="idCredit" value="<%= credit.getIdCredit()%>">
                                            <div class="form-group">
                                                <label for="dateCredit">Date de Crédit</label>
                                                <input type="date" class="form-control" name="dateCredit" value="<%= credit.getDateCredit()%>" readonly required>
                                            </div>
                                            <div class="form-group">
                                                <label for="montantCredit">Montant</label>
                                                <input type="number" class="form-control" name="montantCredit" value="<%= credit.getMontantCredit()%>" readonly required>
                                            </div>
                                            <div class="form-group">
                                                <label for="dateReglement">Date de Règlement</label>
                                                <input type="date" class="form-control" name="dateReglement" value="<%= credit.getDateReglement()%>" >
                                            </div>
                                            <div class="form-group">
                                                <label for="statut">Statut</label>
                                                <select id="statut" name="statut" class="form-control" required>
                                                    <option value="">choisir</option>
                                                    <option value="paye" <%= credit.getStatut().equals("paye") ? "selected" : ""%> >Paye</option>
                                                    <option value="non paye" <%= credit.getStatut().equals("non paye") ? "selected" : ""%>>Non Paye</option>
                                                </select>
                                            </div>
                                            <div class="form-group">
                                                <label for="idAchat">Id Achat</label>
                                                <input type="text" class="form-control" name="idAchat" value="<%= credit.getIdAchat()%>" readonly>
                                            </div>
                                            <button type="submit" class="btn btn-primary">Sauvegarder</button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- Modal Supprimer -->
                        <div class="modal fade" id="deleteModal<%= credit.getIdCredit()%>" tabindex="-1" role="dialog">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title">Confirmation de Suppression</h5>
                                        <button type="button" class="close" data-dismiss="modal">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        Êtes-vous sûr de vouloir supprimer le crédit d'un montant de <%= credit.getMontantCredit()%> ?
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Non</button>
                                        <a href="${pageContext.request.contextPath}/creditSuppression?idCredit=<%= credit.getIdCredit()%>" class="btn btn-danger">Oui</a>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- Modal de notification message -->
                        <div class="modal fade" id="messageCreditModal" tabindex="-1" role="dialog">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title"><i class="fa-solid fa-thumbs-up text-success fa-2x"></i> Notification</h5>
                                        <button type="button" class="close" data-dismiss="modal">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <%= messageCredit != null ? messageCredit : ""%>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-primary" data-dismiss="modal">Fermer</button>
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
                $('#creditTable').DataTable({
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

        <script>
            $(document).ready(function () {
            <% if (messageCredit != null) { %>
                $('#messageCreditModal').modal('show');
            <% }%>
            });
        </script>
    </body>
</html>
