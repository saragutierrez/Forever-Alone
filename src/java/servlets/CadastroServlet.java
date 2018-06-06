package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.util.StringUtils;

import beans.Cliente;
import facades.ClienteFacade;
import utils.DataUtil;
import utils.SenhaUtil;

/**
 * Servlet implementation class CadastroServlet
 */
@WebServlet(name = "CadastroServlet", urlPatterns = {"/CadastroServlet"})
public class CadastroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CadastroServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException, ParseException, InstantiationException {
        Cliente c = fillCliente(request);
		try {
			ClienteFacade.insert(c);
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		response.sendRedirect("index.jsp");
    }
    
    private Cliente fillCliente(HttpServletRequest request) {
    	try {
    		//Pegando dados do formulário
    		Cliente c = new Cliente();
    		//Informações obrigatórios
    		c.setNome(request.getParameter("nome"));
    		c.setEmail(request.getParameter("email"));
    		c.setCpf(request.getParameter("cpf"));
    		c.getEndereco().setRua(request.getParameter("rua"));
    		c.getEndereco().setCep(Integer.parseInt(request.getParameter("cep")));
    		c.getEndereco().getCidade().setIdCidade(Integer.parseInt(request.getParameter("idCidade")));
    		c.getPerfil().setDataNasc(DataUtil.formataDataTelaParaBean(request.getParameter("dataNasc")));
    		c.getPerfil().setGenero(request.getParameter("genero"));
    		c.getPerfil().setEscolaridade(request.getParameter("escolaridade"));
    		c.getPerfil().setCorCabelo(request.getParameter("corCabelo"));
    		c.getPerfil().setCorPele(request.getParameter("corPele"));
    		c.getPerfil().setDescricao(request.getParameter("descricao"));
    		
    		//Verificando igualdade de senhas
    		String senha1 = request.getParameter("senha1");
    		String senha2 = request.getParameter("senha2");
    		if(SenhaUtil.compararSenha(senha1, senha2))
    			c.setSenha(SenhaUtil.md5(senha1));    			
    		else
    			throw new Exception("Senhas diferentes");
    		
    		//Informações opcionais
    		String preferenciaGenero = request.getParameter("preferenciaGenero");
    		String preferenciaEscolaridade = request.getParameter("preferenciaEscolaridade");
    		String preferenciaCorCabelo = request.getParameter("preferenciaCorCabelo");
    		String preferenciaCorPele = request.getParameter("preferenciaCorPele");
    		String preferenciaIdadeMinima = request.getParameter("preferenciaIdadeMin");
    		String preferenciaIdadeMaxima = request.getParameter("preferenciaIdadeMax");
    		
    		if(!StringUtils.isNullOrEmpty(preferenciaGenero))
    			c.getPreferencias().setGenero(preferenciaGenero);
			if(!StringUtils.isNullOrEmpty(preferenciaEscolaridade))
				c.getPreferencias().setEscolaridade(preferenciaEscolaridade);
			if(!StringUtils.isNullOrEmpty(preferenciaCorCabelo))
				c.getPreferencias().setCorCabelo(preferenciaCorCabelo);
			if(!StringUtils.isNullOrEmpty(preferenciaCorPele))
				c.getPreferencias().setCorPele(preferenciaCorPele);
			if(!StringUtils.isNullOrEmpty(preferenciaIdadeMinima))
				c.getPreferencias().setIdadeInicial(Integer.parseInt(preferenciaIdadeMinima));
			if(!StringUtils.isNullOrEmpty(preferenciaIdadeMaxima))
				c.getPreferencias().setIdadeFinal(Integer.parseInt(preferenciaIdadeMaxima));
					
			return c;
    	}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			processRequest(request, response);
		} catch (ClassNotFoundException | InstantiationException | SQLException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			processRequest(request, response);
		} catch (ClassNotFoundException | InstantiationException | SQLException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
