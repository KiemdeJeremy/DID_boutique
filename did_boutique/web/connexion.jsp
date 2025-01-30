<%-- 
    Document   : connexion
    Created on : 29 janv. 2025, 14:56:18
    Author     : USER
--%>

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
        <link rel="stylesheet" type="text/css" href="css/style.css" />
    </head>
    <body>

        <div class="container d-flex justify-content-center align-items-center "style="margin-top: 10%">

            <%
                String error = null;
                error = (String) session.getAttribute("error");
                if (error != null) {
                    session.removeAttribute("message");
                }
            %>

            <div class="p-4 shadow" style="width: 100%; max-width: 600px; background-color: #F5F5F5;">

                <!-- Image centrée -->
                <div class="text-center mb-4">
                    <img src="${pageContext.request.contextPath}/images/DID.jpg" alt="logo de DID" class="mt-3 mr-4 " style="width: 180px; height: 150px;" />
                </div>
                <!-- Formulaire de connexion -->
                <form action="${pageContext.request.contextPath}/connexionServlet" method="post">
                    <div class="form-group mb-3">
                        <label for="login">Matricule</label>
                        <input type="text" id="matricule" name="matricule" class="form-control" placeholder="Entrez votre matricule" required>
                    </div>
                    <div class="form-group mb-4">
                        <label for="password">Mot de passe</label>
                        <input type="password" id="password" name="password" class="form-control" placeholder="Entrez votre mot de passe" required>
                    </div>
                    <%
                        if (error != null) {
                    %>
                    <p class="text-danger">Nom d'utilisateur ou mot de passe incorrect</p>
                    <%
                        }
                    %>
                    <!-- Boutons -->
                    <div class=" text-right">
                        <!-- Connexion -->
                        <button type="submit" class="btn btn-primary">Connexion</button>
                    </div>
                    <br>
                </form>
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
            document.addEventListener('DOMContentLoaded', function () {
                var errorElement = document.querySelector('.text-danger');
                if (errorElement) {
                    setTimeout(function () {
                        errorElement.style.display = 'none';
                    }, 2000); // 2000ms = 2s
                }
            });
        </script>
    </body>
</html>
