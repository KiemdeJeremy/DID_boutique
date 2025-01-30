package org.apache.jsp.vues;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class connexion_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta charset=\"UTF-8\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css\" />\n");
      out.write("        <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css\" />\n");
      out.write("        <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css\" />\n");
      out.write("        <link href=\"https://unpkg.com/boxicons@2.1.1/css/boxicons.min.css\" rel=\"stylesheet\" />\n");
      out.write("        <link rel=\"stylesheet\" href=\"https://cdn.datatables.net/1.11.5/css/jquery.dataTables.min.css\">\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\" />\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("\n");
      out.write("        <div class=\"container d-flex justify-content-center align-items-center\">\n");
      out.write("            <div class=\"p-4 shadow\" style=\"width: 100%; max-width: 600px; background-color: #F5F5F5;\">\n");
      out.write("\n");
      out.write("                ");

                    if (request.getAttribute("alertMessage") != null) {
                
      out.write("\n");
      out.write("                <div class=\"text-center\" id=\"inscriptionValide\">\n");
      out.write("                    <i class=\"fa-solid fa-check text-success fa-2x\"></i>\n");
      out.write("                    <span class=\"text-success h4\">");
      out.print( request.getAttribute("alertMessage"));
      out.write("</span>\n");
      out.write("                </div>\n");
      out.write("                ");
 }
      out.write("\n");
      out.write("\n");
      out.write("                <!-- Image centrée -->\n");
      out.write("                <div class=\"text-center mb-4\">\n");
      out.write("                    <img src=\"/image/img2.jpeg\" alt=\"image d'une opération\" class=\"mt-3 mr-4 rounded-circle\" style=\"width: 180px; height: 150px;\" />\n");
      out.write("                </div>\n");
      out.write("                <!-- Formulaire de connexion -->\n");
      out.write("                <form action=\"");
      out.print( request.getContextPath());
      out.write("/login\" method=\"post\">\n");
      out.write("                    <div class=\"form-group mb-3\">\n");
      out.write("                        <label for=\"login\">Nom d'utilisateur</label>\n");
      out.write("                        <input type=\"text\" id=\"login\" name=\"username\" class=\"form-control\" placeholder=\"Entrez votre login\" required>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"form-group mb-4\">\n");
      out.write("                        <label for=\"password\">Mot de passe</label>\n");
      out.write("                        <input type=\"password\" id=\"password\" name=\"password\" class=\"form-control\" placeholder=\"Entrez votre mot de passe\" required>\n");
      out.write("                    </div>\n");
      out.write("                    ");

                        if (request.getParameter("error") != null) {
                    
      out.write("\n");
      out.write("                    <p class=\"text-danger\">Nom d'utilisateur ou mot de passe incorrect</p>\n");
      out.write("                    ");

                        }
                    
      out.write("\n");
      out.write("                    <!-- Boutons -->\n");
      out.write("                    <div class=\"d-flex justify-content-between\">\n");
      out.write("                        <!-- Connexion -->\n");
      out.write("                        <button type=\"submit\" class=\"btn btn-primary\">Connexion</button>\n");
      out.write("                        <!-- Signaler -->\n");
      out.write("                        <button type=\"button\" class=\"btn btn-danger\" data-toggle=\"modal\" data-target=\"#signalerModal\">\n");
      out.write("                            Signaler\n");
      out.write("                        </button>\n");
      out.write("                        <!-- Inscription -->\n");
      out.write("                        <a href=\"");
      out.print( request.getContextPath());
      out.write("/inscription\" class=\"btn btn-secondary\">Inscription</a>\n");
      out.write("                    </div>\n");
      out.write("                    <br>\n");
      out.write("                </form>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("        <!-- Bootstrap & jQuery -->\n");
      out.write("        <!-- jQuery -->\n");
      out.write("        <script src=\"https://code.jquery.com/jquery-3.6.0.min.js\"></script>\n");
      out.write("        <!-- DataTables -->\n");
      out.write("        <script src=\"https://cdn.datatables.net/1.11.5/js/jquery.dataTables.min.js\"></script>\n");
      out.write("        <!-- Bootstrap (ajoutez uniquement si nécessaire après DataTables) -->\n");
      out.write("        <script src=\"https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js\"></script>\n");
      out.write("        <script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js\"></script>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
