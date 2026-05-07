/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package corridassql;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author flixz
 */
public class Connection {
    private java.sql.Connection con;
    public java.sql.Connection getCon(){
        con = conecta();
        return con;
    }
    
   
    //########################FUNCAO DE CONEXAO COM BANCO DE DADOS##################
	public java.sql.Connection conecta()
	{
	//	String url="jdbc:odbc:banco";
		String url="jdbc:mysql://localhost:3306/corridas";
		java.sql.Connection con;

		try{
		//	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(url,"root","12345");
                        System.out.println("Conexão bem sucedida");
			return con;
			}
			catch(ClassNotFoundException cnf){
				System.out.println("Houve um erro no DRIVER: classnotfoundexcepition-"+cnf);
				return null;
			}
			catch(SQLException sql){
						System.out.println("Houve um erro no SQL:sqlexception sql-"+sql);
						return null;
			}
                        catch(Exception e){
                            System.out.println(e.getMessage());
                            return null;
                        }
	}
        
    
}
