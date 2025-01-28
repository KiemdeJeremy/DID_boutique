<%-- 
    Document   : utilisateur
    Created on : 23 janv. 2025, 04:05:55
    Author     : USER
--%>

<%@page import="java.util.List"%>
<%@page import="models.Mutilisateur"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" />
        <link href="https://unpkg.com/boxicons@2.1.1/css/boxicons.min.css" rel="stylesheet" />
        <link rel="stylesheet" href="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    </head>
    <body>
        <input type="checkbox" id="check">
        <label for="check">
            <i class="fas fa-bars" id="btn"></i>
            <i class="fas fa-times" id="cancel"></i>
        </label>
        <div class="sidebar">
            <header>DID Boutique</header>
            <ul>
                <li><a href="${pageContext.request.contextPath}/index.jsp"><i class="fa-solid fa-house"></i>Acceuil</a></li>
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
                        <div class=" bg-dark">
                            <h2 class="mb-4 text-warning text-center"><u>Enregistrer un utilisateur</u></h2>
                            <form class="w-75 text-warning ml-5" action="${pageContext.request.contextPath}/utilisateurEnregistrement" method="post">
                                <div class="form-group">
                                    <label for="nom">Nom de l'utilisateur</label>
                                    <input type="text" id="nom" name="nom" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label for="prenom">Prenom de l'utilisateur</label>
                                    <input type="text" id="prenom" name="prenom" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label for="sexe">Sexe de l'utilisateur</label>
                                    <select id="sexe" name="sexe" class="form-control" required>
                                        <option value="">choisir</option>
                                        <option value="masculin">masculin</option>
                                        <option value="feminin">feminin</option>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label for="dateNaissance">Date de naissance</label>
                                    <input type="date" id="dateNaissance" name="dateNaissance" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label for="matricule">Matricule de l'utilisateur</label>
                                    <input type="text" id="matricule" name="matricule" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label for="password">Password de l'utilisateur</label>
                                    <input type="password" id="password" name="password" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label for="role">Role de l'utilisateur</label>
                                    <select id="role" name="role" class="form-control" required>
                                        <option value="">choisir</option>
                                        <option value="livreur">Livreur</option>
                                        <option value="magasinier">Magasinier</option>
                                        <option value="caissier">Caissier</option>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label for="telephone">Téléphone de l'utilisateur</label>
                                    <input type="tel" id="telephone" name="telephone" class="form-control" required>
                                </div>

                                <button type="submit" class="btn btn-primary mb-3 w-50">Enregistrer</button>
                            </form>
                        </div>
                    </div>
                    <div class="col-8">
                        <div class="table-responsive" style="max-height:750px;">
                            <table id="utilisateurTable" class="table table-bordered table-striped table-hover">
                                <thead class="table-dark">
                                    <tr>
                                        <th scope="col">#</th>
                                        <th scope="col">Nom</th>
                                        <th scope="col">Prénom</th>
                                        <th scope="col">Sexe</th>
                                        <th scope="col">Date Naissance</th>
                                        <th scope="col">Role</th>
                                        <th scope="col">Téléphone</th>
                                        <th scope="col">Actions</th>
                                    </tr>
                                </thead>
                                <tbody style="background-color: #d6d6d6">
                                    <%
                                        List<Mutilisateur> listUtilisateurs = (List<Mutilisateur>) request.getAttribute("listUtilisateurs");
                                        if (listUtilisateurs != null) {
                                            for (Mutilisateur utilisateur : listUtilisateurs) {
                                    %>
                                    <tr>
                                        <td><%= utilisateur.getIdUtilisateur()%></td>
                                        <td><%= utilisateur.getNom()%></td>
                                        <td><%= utilisateur.getPrenom()%></td>
                                        <td><%= utilisateur.getSexe()%></td>
                                        <td><%= utilisateur.getDateNaissance()%></td>
                                        <td><%= utilisateur.getRole()%></td>
                                        <td><%= utilisateur.getTelephone()%></td>
                                        <td>
                                            <!-- Boutons d'action -->
                                            <a href="#" class="view" title="Voir" data-toggle="modal" data-target="#fenetre<%= utilisateur.getIdUtilisateur()%>">
                                                <i class="fa fa-eye mr-2 fa-lg"></i>
                                            </a>
                                            <a href="#" class="edit" title="Modifier" data-toggle="modal" data-target="#updateModal<%= utilisateur.getIdUtilisateur()%>">
                                                <i class="fa fa-pencil-alt text-success mr-2 fa-lg"></i>
                                            </a>
                                            <a href="#" class="delete" title="Supprimer" data-toggle="modal" data-target="#deleteModal<%= utilisateur.getIdUtilisateur()%>">
                                                <i class="fa fa-trash text-danger mr-2 fa-lg"></i>
                                            </a>
                                        </td>
                                    </tr>

                                    <!-- Modal Voir -->
                                <div class="modal fade" id="fenetre<%= utilisateur.getIdUtilisateur()%>" role="dialog" 
                                     aria-labelledby="titreFenetre<%= utilisateur.getIdUtilisateur()%>" aria-hidden="true">
                                    <div class="modal-dialog modal-lg" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="titreFenetre<%= utilisateur.getIdUtilisateur()%>">
                                                    Détails de l'utilisateur <%= utilisateur.getNom()%> <%= utilisateur.getPrenom()%>
                                                </h5>
                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                            </div>
                                            <div class="modal-body">
                                                <p><strong>Nom:</strong> <%= utilisateur.getNom()%></p>
                                                <p><strong>Prénom:</strong> <%= utilisateur.getPrenom()%></p>
                                                <p><strong>Sexe:</strong> <%= utilisateur.getSexe()%></p>
                                                <p><strong>Date de naissance:</strong> <%= utilisateur.getDateNaissance()%></p>
                                                <p><strong>Matricule:</strong> <%= utilisateur.getMatricule()%></p>
                                                <p><strong>Rôle:</strong> <%= utilisateur.getRole()%></p>
                                                <p><strong>Téléphone:</strong> <%= utilisateur.getTelephone()%></p>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-danger" data-dismiss="modal">Fermer</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <!-- Modal Modifier -->
                                <div class="modal fade" id="updateModal<%= utilisateur.getIdUtilisateur()%>" tabindex="-1" role="dialog"
                                     aria-labelledby="updateModalLabel<%= utilisateur.getIdUtilisateur()%>" aria-hidden="true">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="updateModalLabel<%= utilisateur.getIdUtilisateur()%>">
                                                    Modifier l'utilisateur
                                                </h5>
                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                            </div>
                                            <div class="modal-body">
                                                <form action="${pageContext.request.contextPath}/utilisateurModification" method="post">
                                                    <input type="hidden" name="idUtilisateur" value="<%= utilisateur.getIdUtilisateur()%>">
                                                    <div class="form-group">
                                                        <label for="nom<%= utilisateur.getIdUtilisateur()%>">Nom</label>
                                                        <input type="text" class="form-control" id="nom<%= utilisateur.getIdUtilisateur()%>" 
                                                               name="nom" value="<%= utilisateur.getNom()%>" required>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="prenom<%= utilisateur.getIdUtilisateur()%>">Prénom</label>
                                                        <input type="text" class="form-control" id="prenom<%= utilisateur.getIdUtilisateur()%>" 
                                                               name="prenom" value="<%= utilisateur.getPrenom()%>" required>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="sexe<%= utilisateur.getIdUtilisateur()%>">Sexe</label>
                                                        <select class="form-control" id="sexe<%= utilisateur.getIdUtilisateur()%>" name="sexe" required>
                                                            <option value="masculin" <%= utilisateur.getSexe().equals("masculin") ? "selected" : ""%>>Masculin</option>
                                                            <option value="feminin" <%= utilisateur.getSexe().equals("feminin") ? "selected" : ""%>>Féminin</option>
                                                        </select>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="dateNaissance<%= utilisateur.getIdUtilisateur()%>">Date de naissance</label>
                                                        <input type="date" class="form-control" id="dateNaissance<%= utilisateur.getIdUtilisateur()%>" 
                                                               name="dateNaissance" value="<%= utilisateur.getDateNaissance()%>" required>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="role<%= utilisateur.getIdUtilisateur()%>">Rôle</label>
                                                        <select class="form-control" id="role<%= utilisateur.getIdUtilisateur()%>" name="role" required>
                                                            <option value="livreur" <%= utilisateur.getRole().equals("livreur") ? "selected" : ""%>>Livreur</option>
                                                            <option value="magasinier" <%= utilisateur.getRole().equals("magasinier") ? "selected" : ""%>>Magasinier</option>
                                                            <option value="caissier" <%= utilisateur.getRole().equals("caissier") ? "selected" : ""%>>Caissier</option>
                                                        </select>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="matricule<%= utilisateur.getIdUtilisateur()%>">Matricule</label>
                                                        <input type="text" class="form-control" id="matricule<%= utilisateur.getIdUtilisateur()%>" 
                                                               name="matricule" value="<%= utilisateur.getMatricule()%>" required>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="password<%= utilisateur.getIdUtilisateur()%>">Password</label>
                                                        <input type="text" class="form-control" id="password<%= utilisateur.getIdUtilisateur()%>" 
                                                               name="password" value="<%= utilisateur.getPassword()%>" required>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="telephone<%= utilisateur.getIdUtilisateur()%>">Téléphone</label>
                                                        <input type="tel" class="form-control" id="telephone<%= utilisateur.getIdUtilisateur()%>" 
                                                               name="telephone" value="<%= utilisateur.getTelephone()%>" required>
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

                                <!-- Modal Supprimer -->
                                <div class="modal fade" id="deleteModal<%= utilisateur.getIdUtilisateur()%>" tabindex="-1" role="dialog"
                                     aria-labelledby="deleteModalLabel<%= utilisateur.getIdUtilisateur()%>" aria-hidden="true">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="deleteModalLabel<%= utilisateur.getIdUtilisateur()%>">
                                                    Confirmation de suppression
                                                </h5>
                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                            </div>
                                            <div class="modal-body">
                                                Êtes-vous sûr de vouloir supprimer l'utilisateur <%= utilisateur.getNom()%> <%= utilisateur.getPrenom()%> ?
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Non</button>
                                                <a href="${pageContext.request.contextPath}/utilisateurSuppression?idUtilisateur=<%= utilisateur.getIdUtilisateur()%>" 
                                                   class="btn btn-danger">Oui</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <!-- Modal de notification -->
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
                                                <% if (erreurs != null && !erreurs.isEmpty()) { %>
                                                <ul>
                                                    <% for (String erreur : erreurs) {%>
                                                    <li><%= erreur%></li>
                                                        <% } %>
                                                </ul>
                                                <% } %>
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
                $('#utilisateurTable').DataTable({
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

        <!-- Script pour le modal -->
        <script>
            $(document).ready(function () {
                var message = "<%= message%>";
                var erreurs = "<%= erreurs%>";

                if (message && message !== "null" && message !== "") {
                    $('#messageModal').modal('show');

                    // Masque le modal après 2 secondes
                    setTimeout(function () {
                        $('#messageModal').modal('hide');
                    }, 2000);
                }

                if (erreurs && erreurs !== "null" && erreurs !== "") {
                    $('#messageModal').modal('show');
                }
            });
        </script>
    </body>
</html>
