/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServletUsuario;

import DAO.UsuarioDAO;
import Model.Usuario;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author daniel.mbarbosa1
 */
@WebServlet(name = "UsuarioExcluirVariosServlet", urlPatterns = {"/ti/excluir_usuarios"})
public class UsuarioExcluirVariosServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String[] usuariosSelecionados = request.getParameterValues("selected");

        boolean httpOK = UsuarioDAO.excluirUsuarios(usuariosSelecionados);

        if (httpOK) {
            ArrayList<Usuario> usuarios = UsuarioDAO.getUsuarios();
            request.setAttribute("listaUsuarios", usuarios);

            request.setAttribute("varMsg", true);
            request.setAttribute("msg", "Usuarios excluidos com sucesso.");
            
        } else {
            ArrayList<Usuario> usuarios = UsuarioDAO.getUsuarios();
            request.setAttribute("listaUsuarios", usuarios);

            request.setAttribute("varMsgErro", true);
            request.setAttribute("msg", "Erro ao excluir usuarios.");
        }
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/ti/listagem_usuarios.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
