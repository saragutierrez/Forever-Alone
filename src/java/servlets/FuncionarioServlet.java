/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import com.mysql.cj.util.StringUtils;

import beans.Cliente;
import beans.Festa;
import facades.ClienteFacade;
import facades.FestaFacade;
import utils.DataUtil;
import utils.SenhaUtil;

import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
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
@WebServlet(name = "FuncionarioServlet", urlPatterns = {"/FuncionarioServlet"})
public class FuncionarioServlet extends HttpServlet {

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
    	String home = "/funcionario/homeFuncionario.jsp";
    	RequestDispatcher rd = request.getRequestDispatcher(home);
    	List<Cliente> lista = null;
    	
    	String action = request.getParameter("action");

    	//Identificacao da action
        if(!StringUtils.isNullOrEmpty(action)){
        	int id = 0;
        	Cliente c = null;
        	Festa ft = null;
        	List<Festa> festas = null;
            
        	switch(action){

        		/* INÍCIO - MANTER CLIENTES */
        		//Listar clientes do banco de dados
                case "list":
				try {
					lista = ClienteFacade.searchAll();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					request.setAttribute("menu", "listar");
					request.setAttribute("lista", lista);
                    rd.forward(request, response);                
                    break;
                    
                //Ir para tela de alteracao de dados de um cliente
                case "formUpdate":
                    //Busca id do cliente a ser alterado
                    id = Integer.parseInt(request.getParameter("id"));
                    if( id > 0){
                        c = ClienteFacade.search(id);
						request.setAttribute("menu", "alterar");
						request.setAttribute("cliente", c);
						rd.forward(request, response);                    
                    }
                    break;

                //Atualizar
                case "update":
                    //Preencher dados do cliente no enviados pelo formulario
                    c = fillCliente(request);
                    ClienteFacade.update(c);
					response.sendRedirect(home);
					break;

                //Ir para tela de insercao de um novo cliente
                case "formNew":
					request.setAttribute("menu", "novo");
					rd.forward(request, response);
                    break;
                    

                //INserir um novo cliente
                case "new":
                    //Preencher dados do cliente no enviados pelo formulario
                    c = fillCliente(request);
					try {
						ClienteFacade.insert(c);
					} catch (IllegalAccessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					response.sendRedirect(home);
					break;

                //Remover um cliente
                case "remove":
                	//Busca id do cliente a ser removido no parametro da pagina
                	id = parseInt((String)request.getParameter("id"));
                	if( id > 0){
                		try {
                			ClienteFacade.delete(id);
                		} catch (InstantiationException e) {
                			// TODO Auto-generated catch block
                			e.printStackTrace();
                		} catch (IllegalAccessException e) {
                			// TODO Auto-generated catch block
                			e.printStackTrace();
                		}
    					response.sendRedirect(home);                	}
                	break;
            		/* FIM - MANTER CLIENTES */
                	
                	/* INICIO - PROMOVER FESTAS */
                case "festas":
                		festas = FestaFacade.searchAll();
                        request.setAttribute("festas", festas);
                        rd.forward(request, response);                
                        break;
                
                case "formFesta":
					try {
						lista = ClienteFacade.searchAll();
					} catch (IllegalAccessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                    request.setAttribute("clientes", lista);
                    rd.forward(request, response);                
                    break;
                    
                case "promoverFesta":
					ft = fillFesta(request);
					FestaFacade.insert(ft);
					response.sendRedirect(home);
                    break;
                    /* FIM - PROMOVER FESTAS */
                    
                default:
    				try {
    					lista = ClienteFacade.searchAll();
    				} catch (InstantiationException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				} catch (IllegalAccessException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
                        request.setAttribute("lista", lista);
                        rd.forward(request, response);                
                        break;
            }            
        }else{
            //Por default, a controller lista os clientes
			try {
				lista = ClienteFacade.searchAll();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
                rd = request.getRequestDispatcher(home);
                request.setAttribute("lista", lista);
                rd.forward(request, response);
                }        
        //Fim Controller
        
        }
    
    }

    private Cliente fillCliente(HttpServletRequest request) {
    	try {
    		//Pegando dados do formulário
    		int idCliente = Integer.parseInt(request.getParameter("idUsuario"));
    		String nome = request.getParameter("nome");
    		String email = request.getParameter("email");
    		String senha1 = request.getParameter("senha1");
    		String senha2 = request.getParameter("senha2");
    		
    		//Verificando igualdade de senhas
    		String senha = "";
    		if(SenhaUtil.compararSenha(senha1, senha2))
    			senha = senha1;
    		else
    			throw new Exception("Senhas diferentes");
    		
    		//Preenchedo bean
    		Cliente c = new Cliente();
    		c.setIdUsuario(idCliente);
    		c.setNome(nome);
    		c.setEmail(email);
    		c.setSenha(senha);
    		return c;
    	}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
    
    private Festa fillFesta(HttpServletRequest request) {
    	try {
    		//Pegando dados do formulário
    		String nomeFesta = request.getParameter("nomeFesta");
    		String temaFesta = request.getParameter("temaFesta");
    		String localFesta = request.getParameter("localFesta");
    		Date dataFesta = DataUtil.formataDataHoraTelaParaBean(request.getParameter("dataFesta"));

    		//Preenchedo bean
    		Festa ft = new Festa();
    		ft.setNomeFesta(nomeFesta);
    		ft.setTemaFesta(temaFesta);
    		ft.setLocalFesta(localFesta);
    		ft.setDataFesta(dataFesta);
    		return ft;
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
