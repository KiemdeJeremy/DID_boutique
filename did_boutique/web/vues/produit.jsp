<%-- 
    Document   : produit
    Created on : 23 janv. 2025, 20:04:37
    Author     : USER
--%>

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
        
    </body>
</html>
