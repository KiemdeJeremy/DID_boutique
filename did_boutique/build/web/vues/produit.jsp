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
    <div class="main-content">
        <div class="container-fluid mt-5">
            <div class="row">
                <div class="col-12">
                    <div class="bg-light p-4 mb-4">
                        <h3 class="text-center">Enregistrer un Nouveau Produit</h3>
                        <form class="w-75 mx-auto" action="${pageContext.request.contextPath}/produitEnregistrement" method="post" enctype="multipart/form-data">
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
                            <button type="submit" class="btn btn-primary">Enregistrer</button>
                        </form>
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
                var message = "<%= request.getAttribute("message") != null ? request.getAttribute("message") : "" %>";
                if (message) {
                    alert(message); // Affiche un message d'alerte si nécessaire
                }
            });
        </script>
    </div>
</body>
</html>