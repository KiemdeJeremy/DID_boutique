<%@page import="models.Mutilisateur"%>
<%@page import="models.Mproduit"%>
<%@page import="java.util.List"%>
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

        <%
            String messageProduit = (String) session.getAttribute("messageProduit");
            if (messageProduit != null) {
                session.removeAttribute("messageProduit");
            }
        %>

        <div class="main-content">
            <div class="row text-center mb-4">
                <div class="col-2">

                </div>
                <div class="col-8">
                    <img src="${pageContext.request.contextPath}/images/produit.jpg" alt="image d'un produits" class="mt-3 mr-4 " style="width: 380px; height: 250px;" />
                </div>        
            </div>
            <div class="container-fluid">
                <div class="row">
                    <div class="col-5">
                        <div style="background-color: blue">
                            <h3 class="text-center text-warning">Enregistrer un Nouveau Produit</h3>
                            <form class="ml-4 text-warning w-75" action="${pageContext.request.contextPath}/produitEnregistrement" method="post" enctype="multipart/form-data">
                                <div class="form-group">
                                    <label for="nom">Nom du produit</label>
                                    <input type="text" id="nom" name="nom" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label for="prix">Prix du produit</label>
                                    <input type="number" id="prix" name="prix" class="form-control" step="0.01" required>
                                </div>
                                <div class="form-group">
                                    <label for="quantite">Quantité en stock</label>
                                    <input type="number" id="quantite" name="quantite" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label for="datePeremption">Date de péremption</label>
                                    <input type="date" id="datePeremption" name="datePeremption" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label for="image">Image du produit</label>
                                    <input type="file" id="image" name="image" class="form-control-file" required>
                                </div>
                                <button type="submit" class="btn btn-primary mb-4">Enregistrer</button>
                            </form>
                        </div>
                    </div>
                    <div class="col-7">
                        <div class="table-responsive" style="max-height:750px;">
                            <table id="produitsTable" class="table table-bordered table-striped table-hover">
                                <thead class="table-dark">
                                    <tr>
                                        <th scope="col">#</th>
                                        <th scope="col">Image</th>
                                        <th scope="col">Nom</th>
                                        <th scope="col">Prix</th>
                                        <th scope="col">Quantité</th>
                                        <th scope="col">Date de péremption</th>
                                        <th scope="col">Actions</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        List<Mproduit> listProduit = (List<Mproduit>) request.getAttribute("listProduits");
                                        if (listProduit != null) {
                                            for (Mproduit produit : listProduit) {
                                                String cheminComplet = produit.getImage();
                                                String nomImage = cheminComplet.substring(cheminComplet.lastIndexOf('/') + 1);
                                                // Vérification de la quantité pour appliquer une classe CSS
                                                String rowClass = produit.getQuantite() <= 5 ? "table-danger" : "";
                                    %>
                                    <tr class="<%= rowClass%>">
                                        <td><%= produit.getIdProduit()%></td>
                                        <td>
                                            <img style="width: 35px; height: 35px; object-fit: cover;" 
                                                 src="${pageContext.request.contextPath}/uploads/<%= nomImage%>" 
                                                 class="small-img" 
                                                 alt="Image du produit">
                                        </td>
                                        <td><%= produit.getNom()%></td>
                                        <td><%= produit.getPrix()%> FCFA</td>
                                        <td><%= produit.getQuantite()%></td>
                                        <td><%= produit.getDatePeremption()%></td>
                                        <td>
                                            <a href="#" class="view" title="Voir" data-toggle="modal" data-target="#fenetre<%= produit.getIdProduit()%>"><i class="fa fa-eye fa-lg"></i></a>
                                            <a href="#" class="edit" title="Modifier" data-toggle="modal" data-target="#updateModal<%= produit.getIdProduit()%>"><i class="fa fa-pencil-alt text-success fa-lg"></i></a>
                                            <a href="#" class="delete" title="Supprimer" data-toggle="modal" data-target="#deleteModal<%= produit.getIdProduit()%>"><i class="fa fa-trash text-danger fa-lg"></i></a>
                                        </td>
                                    </tr>

                                    <!-- Modal pour voir le produit -->
                                <div class="modal fade" id="fenetre<%= produit.getIdProduit()%>" role="dialog">
                                    <div class="modal-dialog modal-lg" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title">Informations sur <%= produit.getNom()%></h5>
                                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                            </div>
                                            <div class="modal-body">
                                                <div class="container-fluid">
                                                    <div class="row">
                                                        <div class="col-md-6">
                                                            <img src="${pageContext.request.contextPath}/uploads/<%= nomImage%>" class="img-fluid" alt="<%= produit.getNom()%>">
                                                        </div>
                                                        <div class="col-md-6">
                                                            <h1 class="text-success"><u><%= produit.getNom()%></u></h1>
                                                            <p><strong class="text-warning">Prix:</strong> <%= produit.getPrix()%> FCFA</p>
                                                            <p><strong class="text-warning">Quantité:</strong> <%= produit.getQuantite()%> pièces</p>
                                                            <p><strong class="text-warning">Date de péremption:</strong> <%= produit.getDatePeremption()%></p>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-danger" data-dismiss="modal">Fermer</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <!-- Modal de modification -->
                                <div class="modal fade" id="updateModal<%= produit.getIdProduit()%>" tabindex="-1" role="dialog">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title">Modifier le produit</h5>
                                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                            </div>
                                            <div class="modal-body">
                                                <form action="${pageContext.request.contextPath}/produitModification" method="post" enctype="multipart/form-data">
                                                    <input type="hidden" name="idProduit" value="<%= produit.getIdProduit()%>">
                                                    <input type="hidden" name="imageActuelle" value="<%= produit.getImage()%>">

                                                    <div class="form-group">
                                                        <label for="nom<%= produit.getIdProduit()%>">Nom du produit</label>
                                                        <input type="text" id="nom<%= produit.getIdProduit()%>" name="nom" class="form-control" value="<%= produit.getNom()%>" required>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="prix<%= produit.getIdProduit()%>">Prix du produit</label>
                                                        <input type="number" id="prix<%= produit.getIdProduit()%>" name="prix" class="form-control" value="<%= produit.getPrix()%>" required>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="quantite<%= produit.getIdProduit()%>">Quantité en stock</label>
                                                        <input type="number" id="quantite<%= produit.getIdProduit()%>" name="quantite" class="form-control" value="<%= produit.getQuantite()%>" required>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="datePeremption">Date de péremption</label>
                                                        <input type="date" id="datePeremption" name="datePeremption" class="form-control" value="<%= produit.getDatePeremption()%>" required>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="image<%= produit.getIdProduit()%>">Image du produit</label>
                                                        <input type="file" id="image<%= produit.getIdProduit()%>" name="image" class="form-control-file">
                                                        <img style="width: 50px;height: 50px;object-fit: cover;" src="${pageContext.request.contextPath}/uploads/<%= nomImage%>" alt="Image actuelle">
                                                    </div>
                                                    <button type="submit" class="btn btn-primary">Sauvegarder</button>
                                                </form>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Fermer</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <!-- Modal de confirmation de suppression -->
                                <div class="modal fade" id="deleteModal<%= produit.getIdProduit()%>" tabindex="-1" role="dialog">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title">Confirmation de la suppression</h5>
                                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                            </div>
                                            <div class="modal-body">
                                                Êtes-vous sûr de vouloir supprimer le produit <strong><%= produit.getNom()%></strong> ?
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Non</button>
                                                <a href="${pageContext.request.contextPath}/produitSuppression?idProduit=<%= produit.getIdProduit()%>" class="btn btn-danger">Oui</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <!-- Modal de notification message -->
                                <div class="modal fade" id="messageProduitModal" tabindex="-1" role="dialog">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title"><i class="fa-solid fa-thumbs-up text-success fa-2x"></i> Notification</h5>
                                                <button type="button" class="close" data-dismiss="modal">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                            </div>
                                            <div class="modal-body">
                                                <%= messageProduit != null ? messageProduit : ""%>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-primary" data-dismiss="modal">Fermer</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <%
                                        } // Fin de la boucle for
                                    } // Fin de la condition if
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
                        $('#produitsTable').DataTable({
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
                    <% if (messageProduit != null) { %>
                        $('#messageProduitModal').modal('show');
                    <% }%>
                    });
                </script>
            </div>
    </body>
</html>