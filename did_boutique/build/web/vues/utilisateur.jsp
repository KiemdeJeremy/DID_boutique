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

    <body>
        <div class="main-content"> 
            <div class="row text-center mb-4">
                <div class="col-2">

                </div>
                <div class="col-8">
                    <img src="${pageContext.request.contextPath}/images/user.jpg" alt="image camion" class="mt-3 mr-4 " style="width: 310px; height: 280px;" />
                </div> <br> <br> <br>   <br> <br> <br>   
            </div>
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
                                    <%
                                        // Récupération de l'utilisateur de la session
                                        Mutilisateur user = (Mutilisateur) session.getAttribute("utilisateur");
                                        // On le supprime immédiatement de la session
                                        if (user != null) {
                                            session.removeAttribute("utilisateur");
                                        }
                                    %>

                            <form class="w-75 text-warning ml-5" action="${pageContext.request.contextPath}/utilisateurEnregistrement" method="post">
                                <div class="form-group">
                                    <label for="nom">Nom de l'utilisateur</label>
                                    <input type="text" id="nom" name="nom" class="form-control" required 
                                           value="<%= user != null ? user.getNom() : ""%>">
                                </div>
                                <div class="form-group">
                                    <label for="prenom">Prenom de l'utilisateur</label>
                                    <input type="text" id="prenom" name="prenom" class="form-control" required 
                                           value="<%= user != null ? user.getPrenom() : ""%>">
                                </div>
                                <div class="form-group">
                                    <label for="sexe">Sexe de l'utilisateur</label>
                                    <select id="sexe" name="sexe" class="form-control" required>
                                        <option value="">choisir</option>
                                        <option value="masculin" <%= user != null && "masculin".equals(user.getSexe()) ? "selected" : ""%>>masculin</option>
                                        <option value="feminin" <%= user != null && "feminin".equals(user.getSexe()) ? "selected" : ""%>>feminin</option>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label for="dateNaissance">Date de naissance</label>
                                    <input type="date" id="dateNaissance" name="dateNaissance" class="form-control" required
                                           value="<%= user != null && user.getDateNaissance() != null ? user.getDateNaissance().toString() : ""%>">
                                </div>
                                <div class="form-group">
                                    <label for="matricule">Matricule de l'utilisateur</label>
                                    <input type="text" id="matricule" name="matricule" class="form-control" required
                                           value="<%= user != null ? user.getMatricule() : ""%>">
                                </div>
                                <div class="form-group">
                                    <label for="password">Password de l'utilisateur</label>
                                    <input type="password" id="password" name="password" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label for="role">Role de l'utilisateur</label>
                                    <select id="role" name="role" class="form-control" required>
                                        <option value="">choisir</option>
                                        <option value="magasinier" <%= user != null && "magasinier".equals(user.getRole()) ? "selected" : ""%>>Magasinier</option>
                                        <option value="caissier" <%= user != null && "caissier".equals(user.getRole()) ? "selected" : ""%>>Caissier</option>
                                        <option value="administrateur" <%= user != null && "administrateur".equals(user.getRole()) ? "selected" : ""%>>Administrateur</option>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label for="telephone">Téléphone de l'utilisateur</label>
                                    <input type="tel" id="telephone" name="telephone" class="form-control" required
                                           value="<%= user != null ? user.getTelephone() : ""%>">
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
                                                <p><strong>Password:</strong> <%= utilisateur.getPassword()%></p>
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
                                                            <option value="administrateur" <%= utilisateur.getRole().equals("administrateur") ? "selected" : ""%>>Administrateur</option>
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

                                <!-- Modal de notification message-->
                                <div class="modal fade" id="messageModal" tabindex="-1" role="dialog" aria-labelledby="messageModalLabel" aria-hidden="true">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="messageModalLabel"> <i class="fa-solid fa-thumbs-up text-success fa-2x"></i> Notification</h5>
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
                                <%
                                        }
                                    }
                                %>

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

        <!-- Script pour le modal du message -->
        <script>
            $(document).ready(function () {
                var message = "<%= message%>";
                if (message && message !== "null" && message !== "") {
                    $('#messageModal').modal('show');

                    // Masque le modal après 2 secondes
                    setTimeout(function () {
                        $('#messageModal').modal('hide');
                    }, 2000);
                }
            });
        </script>

        <!-- Script pour le modal des erreurs -->
        <script>
            $(document).ready(function () {
            <% if (erreurs != null && !erreurs.isEmpty()) { %>
                $('#erreurModal').modal('show');
            <% }%>
            });
        </script>
    </body>
</html>
