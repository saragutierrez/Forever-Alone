/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import com.mysql.cj.util.StringUtils;

import beans.Funcionario;
import facades.FuncionarioFacade;
import utils.SenhaUtil;

import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author luck
 */
@WebServlet(name = "AdminServlet", urlPatterns = {"/AdminServlet"})
public class AdminServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     * @throws java.text.ParseException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException, ParseException, InstantiationException {
        
    HttpSession session = request.getSession();

    if (session == null || session.getAttribute("user") == null) {

        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        request.setAttribute("msg", "Usuário deve se autenticar para acessar o sistema.");
        rd.forward(request, response);
        
    }else {
    
        //Inicio Controller
        
    	//Variaveis de controle
    	String home = "/admin/homeAdmin.jsp";
    	RequestDispatcher rd = request.getRequestDispatcher(home);
    	List<Funcionario> lista = null;
        
    	String action = request.getParameter("action");

    	//Identificacao da action
        if(!StringUtils.isNullOrEmpty(action)){
        	int id = 0;
        	Funcionario f = null;
            switch(action){
            
        		/* INICIO - MANTER FUNCIONARIOS */
                //Listar funcionarios do banco de dados
                case "list":
					try {
						lista = FuncionarioFacade.searchAll();
					} catch (InstantiationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
						request.setAttribute("menu", "list");
						request.setAttribute("lista", lista);
	                    rd.forward(request, response);                
                    break;
                    
                //Ir para tela de alteracao de nome, email e senha de um funcionario
                case "formUpdate":
                    //Busca id do funcionario a ser alterado
                    id = Integer.parseInt(request.getParameter("id"));
                    if( id > 0){
                        f = FuncionarioFacade.search(id);
						request.setAttribute("menu", "form");
						request.setAttribute("funcionario", f);
						rd.forward(request, response);                    
                    }
                    break;

                //Atualizar
                case "update":
                    //Preencher dados do funcionario no enviados pelo formulario
                    f = fillFuncionario(request);
                    FuncionarioFacade.update(f);
					response.sendRedirect("AdminServlet");
					break;

                //Ir para tela de insercao de um novo funcionario
                case "formNew":
					request.setAttribute("menu", "form");
					rd.forward(request, response);
                    break;
                    
                //Inserir um novo cliente
                case "new":
                    //Preencher dados do cliente no enviados pelo formulÃ¡rio
                    f = fillFuncionario(request);
                    FuncionarioFacade.insert(f);
					response.sendRedirect("AdminServlet");
					break;

                //Remover um funcionario
                case "remove":
                	//Busca id do funcionario a ser removido no parametro da pÃ¡gina
                	id = parseInt((String)request.getParameter("id"));
                	if( id > 0){
                		try {
                			FuncionarioFacade.delete(id);
                		} catch (InstantiationException e) {
                			// TODO Auto-generated catch block
                			e.printStackTrace();
                		} catch (IllegalAccessException e) {
                			// TODO Auto-generated catch block
                			e.printStackTrace();
                		}
    				}
                	response.sendRedirect("AdminServlet");
                	break;
                	/* FIM - MANTER FUNCIONARIOS */
                	
                	/* INICIO RELATÓRIOS */
                case "reports":
                	request.setAttribute("menu", "reports");
                	response.sendRedirect("AdminServlet");
					break;
                	/* FIM RELATÓRIOS */
                	
                default:
    				try {
    					lista = FuncionarioFacade.searchAll();
    				} catch (InstantiationException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				} catch (IllegalAccessException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
    					request.setAttribute("menu", "list");
                        request.setAttribute("lista", lista);
                        rd.forward(request, response);                
                        break;
            }            
        }else{
            //Por default, a controller lista os funcionarios
			try {
				lista = FuncionarioFacade.searchAll();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
                rd = request.getRequestDispatcher(home);
				request.setAttribute("menu", "list");
				request.setAttribute("lista", lista);
                rd.forward(request, response);
                }        
        //Fim Controller
        
        }
    
    }

    private Funcionario fillFuncionario(HttpServletRequest request) {
    	try {
    		//Pegando dados do formulário
    		//Preenchedo bean
    		Funcionario f = new Funcionario();
    		String id = request.getParameter("idUsuario");
    		if(!StringUtils.isNullOrEmpty(id)) {
    			 f.setIdUsuario(Integer.parseInt(id));
    		}
    		f.setNome(request.getParameter("nome"));
    		f.setEmail(request.getParameter("email"));

    		//Verificando igualdade de senhas
    		String senha1 = request.getParameter("senha1");
    		String senha2 = request.getParameter("senha2");
    		if(SenhaUtil.compararSenha(senha1, senha2)) {
    			f.setSenha(senha1);    			
    		}else
    			throw new Exception("Senhas diferentes");
    		
    		return f;
    	}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
        	processRequest(request, response);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
