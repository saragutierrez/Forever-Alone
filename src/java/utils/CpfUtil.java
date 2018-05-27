package utils;

import java.io.Serializable;

public class CpfUtil implements Serializable{

	public static String formataCpfTela(String cpf) {
		return (cpf.substring(0, 3)+"."+cpf.substring(3, 6)+"."+cpf.substring(6, 9)+"-"+cpf.substring(9, 11));
	}
	
}
