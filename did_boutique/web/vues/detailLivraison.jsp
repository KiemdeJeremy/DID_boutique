<%@page import="models.Mutilisateur"%>
<%@page import="models.Mlivraison"%>
<%@page import="models.Mproduit"%>
<%@page import="java.util.List"%>
<%@page import="models.MdetailLivraison"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <%
        Mutilisateur userConnect = (Mutilisateur) session.getAttribute("userConnect");
        if(userConnect==null){
            request.getRequestDispatcher("/connexion.jsp").forward(request, response);
        }
        String headerJSP = (String) session.getAttribute("headerJSP");
        if (headerJSP == null) {
            headerJSP = "/vues/mesInclusions/adminHeader.jsp"; // En cas de problème avec l'attribut de session
        }
    %>

    <jsp:include page="<%=headerJSP%>" />

    <body>
        <div class="main-content">
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

            <div class="container-fluid mt-5">
                <div class="row">
                    <div class="col-4">
                        <div class="" style="background-color: blue">
                            <h2 class="mb-4 text-warning text-center"><u>Enregistrer un Détail de Livraison</u></h2>

                            <form class="w-75 text-warning ml-5" action="${pageContext.request.contextPath}/detailLivraisonEnregistrement" method="post">
                                <div class="form-group">
                                    <label for="quantite">Quantité</label>
                                    <input type="number" id="quantite" name="quantite" oninput="updateCoutTotal()" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label for="prixUnitaire">Prix Unitaire</label>
                                    <input type="number" id="prixUnitaire" name="prixUnitaire" oninput="updateCoutTotal()" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label for="coutTotal">Coût Total</label>
                                    <input type="number" id="coutTotal" name="coutTotal" class="form-control" readonly required>
                                </div>
                                <div class="form-group">
                                    <label for="idLivraison">ID Livraison</label>
                                    <select id="idLivraison" name="idLivraison" class="form-control" required>
                                        <option value="">Choisir une livraison</option>
                                        <%
                                            List<Mlivraison> listLivraisons = (List<Mlivraison>) request.getAttribute("listLivraisons");
                                            if (listLivraisons != null) {
                                                for (Mlivraison livraison : listLivraisons) {
                                        %>
                                        <option value="<%= livraison.getIdLivraison()%>"><%= livraison.getIdLivraison() %></option>
                                        <%
                                                }
                                            }
                                        %>
                                    </select>
                                </div>

                                <div class="form-group">
                                    <label for="idProduit">ID Produit</label>
                                    <select id="idProduit" name="idProduit" class="form-control" required>
                                        <option value="">Choisir un produit</option>
                                        <%
                                            List<Mproduit> listProduits = (List<Mproduit>) request.getAttribute("listProduits");
                                            if (listProduits != null) {
                                                for (Mproduit produit : listProduits) {
                                        %>
                                        <option value="<%= produit.getIdProduit()%>"><%= produit.getIdProduit() + " " + produit.getNom()%></option>
                                        <%
                                                }
                                            }
                                        %>
                                    </select>
                                </div>
                                <button type="submit" class="btn btn-primary mb-3 w-50">Enregistrer</button>
                            </form>
                        </div>
                    </div>
                    <div class="col-8">
                        <div class="table-responsive" style="max-height:750px;">
                            <table id="detailLivraisonTable" class="table table-bordered table-striped table-hover">
                                <thead class="table-dark">
                                    <tr>
                                        <th scope="col">#</th>
                                        <th scope="col">Quantité</th>
                                        <th scope="col">Prix Unitaire</th>
                                        <th scope="col">Coût Total</th>
                                        <th scope="col">ID Livraison</th>
                                        <th scope="col">ID Produit</th>
                                        <th scope="col">Actions</th>
                                    </tr>
                                </thead>
                                <tbody style="background-color: #d6d6d6">
                                    <%
                                        List<MdetailLivraison> listDetailsLivraisons = (List<MdetailLivraison>) request.getAttribute("listDetailsLivraisons");
                                        if (listDetailsLivraisons != null) {
                                            for (MdetailLivraison detailLivraison : listDetailsLivraisons) {
                                    %>
                                    <tr>
                                        <td><%= detailLivraison.getIdDetailLivraison()%></td>
                                        <td><%= detailLivraison.getQuantite()%></td>
                                        <td><%= detailLivraison.getPrixUnitaire()%></td>
                                        <td><%= detailLivraison.getCoutTotal()%></td>
                                        <td><%= detailLivraison.getIdLivraison()%></td>
                                        <td><%= detailLivraison.getIdProduit()%></td>
                                        <td>
                                            <a href="#" class="view" title="Voir" data-toggle="modal" data-target="#fenetre<%= detailLivraison.getIdDetailLivraison()%>">
                                                <i class="fa fa-eye mr-2 fa-lg"></i>
                                            </a>
                                            <a href="" class="edit" title="Modifier" data-toggle="modal" data-target="#updateModal<%= detailLivraison.getIdDetailLivraison()%>">
                                                <i class="fa fa-pencil-alt text-success mr-2 fa-lg"></i>
                                            </a>
                                            <a href="#" class="delete" title="Supprimer" data-toggle="modal" data-target="#deleteModal<%= detailLivraison.getIdDetailLivraison()%>">
                                                <i class="fa fa-trash text-danger mr-2 fa-lg"></i>
                                            </a>
                                        </td>
                                    </tr>

                                    <!-- Modal Supprimer -->
                                    <div class="modal fade" id="deleteModal<%= detailLivraison.getIdDetailLivraison()%>" tabindex="-1" role="dialog" aria-labelledby="deleteModalLabel<%= detailLivraison.getIdDetailLivraison()%>" aria-hidden="true">
                                        <div class="modal-dialog" role="document">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title" id="deleteModalLabel<%= detailLivraison.getIdDetailLivraison()%>">Confirmation de suppression</h5>
                                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                        <span aria-hidden="true">&times;</span>
                                                    </button>
                                                </div>
                                                <div class="modal-body">
                                                    Êtes-vous sûr de vouloir supprimer ce détail de livraison ?
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Non</button>
                                                    <a href="${pageContext.request.contextPath}/detailLivraisonSuppression?idDetailLivraison=<%= detailLivraison.getIdDetailLivraison()%>" class="btn btn-danger">Oui</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <!-- Modal Voir -->
                                    <div class="modal fade" id="fenetre<%= detailLivraison.getIdDetailLivraison()%>" role="dialog" 
                                         aria-labelledby="titreFenetre<%= detailLivraison.getIdDetailLivraison()%>" aria-hidden="true">
                                        <div class="modal-dialog modal-lg" role="document">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title" id="titreFenetre<%= detailLivraison.getIdDetailLivraison()%>">
                                                        Détails du Détail de Livraison #<%= detailLivraison.getIdDetailLivraison()%>
                                                    </h5>
                                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                        <span aria-hidden="true">&times;</span>
                                                    </button>
                                                </div>
                                                <div class="modal-body">
                                                    <p><strong>Quantité:</strong> <%= detailLivraison.getQuantite()%></p>
                                                    <p><strong>Prix Unitaire:</strong> <%= detailLivraison.getPrixUnitaire()%></p>
                                                    <p><strong>Coût Total:</strong> <%= detailLivraison.getCoutTotal()%></p>
                                                    <p><strong>ID Livraison:</strong> <%= detailLivraison.getIdLivraison()%></p>
                                                    <p><strong>ID Produit:</strong> <%= detailLivraison.getIdProduit()%></p>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-danger" data-dismiss="modal">Fermer</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <!-- Modal Modifier -->
                                    <div class="modal fade" id="updateModal<%= detailLivraison.getIdDetailLivraison()%>" tabindex="-1" role="dialog" aria-labelledby="updateModalLabel<%= detailLivraison.getIdDetailLivraison()%>" aria-hidden="true">
                                        <div class="modal-dialog" role="document">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title" id="updateModalLabel<%= detailLivraison.getIdDetailLivraison()%>">Modifier Détail de Livraison</h5>
                                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                        <span aria-hidden="true">&times;</span>
                                                    </button>
                                                </div>
                                                <div class="modal-body">
                                                    <form action="${pageContext.request.contextPath}/detailLivraisonModification" method="post">
                                                        <input type="hidden" name="idDetailLivraison" value="<%= detailLivraison.getIdDetailLivraison()%>">
                                                        <div class="form-group">
                                                            <label for="quantite<%= detailLivraison.getIdDetailLivraison()%>">Quantité</label>
                                                            <input type="number" id="quantiteUpdate" name="quantite" class="form-control" oninput="updateCoutTotalUpdate()" required value="<%= detailLivraison.getQuantite()%>">
                                                        </div>
                                                        <div class="form-group">
                                                            <label for="prixUnitaire<%= detailLivraison.getIdDetailLivraison()%>">Prix Unitaire</label>
                                                            <input type="number" id="prixUnitaireUpdate" name="prixUnitaire" class="form-control" oninput="updateCoutTotalUpdate()" required value="<%= detailLivraison.getPrixUnitaire()%>">
                                                        </div>
                                                        <div class="form-group">
                                                            <label for="coutTotal<%= detailLivraison.getIdDetailLivraison()%>">Coût Total</label>
                                                            <input type="number" id="coutTotalUpdate" name="coutTotal" class="form-control" readonly value="<%= detailLivraison.getCoutTotal()%>">
                                                        </div>
                                                        <div class="form-group">
                                                            <label for="idLivraison<%= detailLivraison.getIdDetailLivraison()%>">ID Livraison</label>
                                                            <select id="idLivraison" name="idLivraison" class="form-control" required>
                                                                <option value="">Choisir une livraison</option>
                                                                <%
                                                                    // Remplir la liste des livraisons
                                                                    listLivraisons = (List<Mlivraison>) request.getAttribute("listLivraisons");
                                                                    if (listLivraisons != null) {
                                                                        for (Mlivraison livraison : listLivraisons) {
                                                                %>
                                                                <option value="<%= livraison.getIdLivraison()%>" <%= detailLivraison.getIdLivraison() == livraison.getIdLivraison() ? "selected" : ""%>><%= livraison.getIdLivraison() %></option>
                                                                <%
                                                                        }
                                                                    }
                                                                %>
                                                            </select>
                                                        </div>
                                                        <div class="form-group">
                                                            <label for="idProduit<%= detailLivraison.getIdDetailLivraison()%>">ID Produit</label>
                                                            <select id="idProduit" name="idProduit" class="form-control" required>
                                                                <option value="">Choisir un produit</option>
                                                                <%
                                                                    // Remplir la liste des produits
                                                                    listProduits = (List<Mproduit>) request.getAttribute("listProduits");
                                                                    if (listProduits != null) {
                                                                        for (Mproduit produit : listProduits) {
                                                                %>
                                                                <option value="<%= produit.getIdProduit()%>" <%= detailLivraison.getIdProduit() == produit.getIdProduit() ? "selected" : ""%>><%= produit.getIdProduit() + " " + produit.getNom()%></option>
                                                                <%
                                                                        }
                                                                    }
                                                                %>
                                                            </select>
                                                        </div>
                                                        <button type="submit" class="btn btn-primary">Sauvegarder</button>
                                                    </form>
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

            <!-- Modals pour messages -->
            <div class="modal fade" id="messageModal" tabindex="-1" role="dialog" aria-labelledby="messageModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="messageModalLabel">Notification</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
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

            <!-- Modal de notification erreur-->
            <div class="modal fade" id="erreurModal" tabindex="-1" role="dialog" aria-labelledby="erreurModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="erreurModalLabel"> <i class="fa-solid fa-circle-exclamation text-danger fa-2x"></i> Notification d'erreur</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
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
                    $('#detailLivraisonTable').DataTable({
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
                    // Afficher le modal de message si nécessaire
                    var message = "<%= message%>";
                    if (message && message !== "null" && message !== "") {
                        $('#messageModal').modal('show');
                    }
                });
            </script>

            <script>
                $(document).ready(function () {
                <% if (erreurs != null && !erreurs.isEmpty()) { %>
                    $('#erreurModal').modal('show');
                <% }%>
                });
            </script>

            <script>
                function updateCoutTotal() {
                    // Récupérer les valeurs des champs
                    var prixUnitaire = parseFloat(document.getElementById("prixUnitaire").value) || 0;
                    var quantite = parseFloat(document.getElementById("quantite").value) || 0;

                    // Calculer le coût total
                    var coutTotal = prixUnitaire * quantite;

                    // Mettre à jour le champ de coût total
                    document.getElementById("coutTotal").value = coutTotal.toFixed(2); // Limiter à 2 décimales
                }
            </script>

            <script>
                function updateCoutTotalUpdate() {
                    // Récupérer les valeurs des champs
                    var prixUnitaireUpdate = parseFloat(document.getElementById("prixUnitaireUpdate").value) || 0;
                    var quantiteUpdate = parseFloat(document.getElementById("quantiteUpdate").value) || 0;

                    // Calculer le coût total
                    var coutTotalUpdate = prixUnitaireUpdate * quantiteUpdate;

                    // Mettre à jour le champ de coût total
                    document.getElementById("coutTotalUpdate").value = coutTotalUpdate.toFixed(2); // Limiter à 2 décimales
                }
            </script>
        </div>
    </body>
</html>