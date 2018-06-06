package dao;

import java.io.IOException;
import java.util.Date;

import beans.Cliente;
import facades.ClienteFacade;
import utils.SenhaUtil;

public class TesteDAO {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Cliente c = new Cliente();
		//Informações obrigatórios
		c.setNome("nome");
		c.setEmail("email@email.com");
		c.setCpf("12345678910");
		c.getEndereco().setRua("rua");
		c.getEndereco().setCep(12345678);
		c.getEndereco().getCidade().setIdCidade(1234);
		c.getPerfil().setDataNasc(new Date());
		c.getPerfil().setGenero("M");
		c.getPerfil().setEscolaridade("escolaridade");
		c.getPerfil().setCorCabelo("corCabelo");
		c.getPerfil().setCorPele("corPele");
		c.getPerfil().setDescricao("descricao");
		c.setSenha(SenhaUtil.md5("teste"));    			
		
		//Informações opcionais
		c.getPreferencias().setGenero("M");
		c.getPreferencias().setCorCabelo("preferenciaCorCabelo");
		c.getPreferencias().setCorPele("preferenciaCorPele");
		c.getPreferencias().setIdadeInicial(18);
		c.getPreferencias().setIdadeFinal(22);
		
		try {
			ClienteFacade.insert(c);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Cliente inserido");
	}

}
