package utils;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DataUtil implements Serializable{

//	//converter data do java para string
//	public static String formataDataBeanParaTela(java.util.Date data);
	
    //converter data de string para data java
	public static java.util.Date formataDataTelaParaBean(String data) throws ParseException{
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = formato.parse(data);
        return date;
	}
	
	//converter java date para sql date
	public static java.sql.Date formataDataBeanParaSql(java.util.Date data){
		java.sql.Date sqlDate = new java.sql.Date(data.getTime());
		return sqlDate;
	}
	
    //converter sql date para java date
	public static java.util.Date formataDataSqlParaBean(java.sql.Date data){
		java.sql.Date sqlDate = data;
        java.util.Date utilDate = new java.util.Date(sqlDate.getTime());
        return utilDate;
	}
	
	//converter data e hora de string para data e hora java
	public static java.util.Date formataDataHoraTelaParaBean(String data) throws ParseException{
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        java.util.Date date = formato.parse(data);
        return date;
	}
	
	//converter data e hora java para data e hora string para exibir na tela
	public static String formataDataHoraBeanParaTela(java.util.Date data) throws ParseException{
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String date = formato.format(data);
        return date;
	}
	
	//converter sql timestamp com data e hora para java date com data e hora
	public static java.util.Date formataDataHoraSqlParaBean(java.sql.Timestamp dataHora){
        java.util.Date utilDate = new java.util.Date(dataHora.getTime());
        return utilDate;
	}

	//converter java date com data e hora para sql timestamp com data e hora
	public static java.sql.Timestamp formataDataHoraBeanParaSql(java.util.Date dataHora){
        java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(dataHora.getTime());
        return sqlTimestamp ;
	}
}
