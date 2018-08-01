/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste_valemobi;
import java.sql.DriverManager;
import java.sql.*;

import javax.swing.JOptionPane;

/**
 *
 * @author thiag
 */
public class ConexaoDB {
    
    private Connection con = null;
    
    public Statement stmt;
    
    public ResultSet rs;
    
    private String end;
    
    private String user;
    
    private String pass;
    
    public void Conectar(String strEnd, String strUsuario, String strSenha) {
    	/** Recebendo o endereco,usuario e senha do usuario e repassando para a variavel global */
        end = strEnd; 
        user = strUsuario;
        pass = strSenha;
        
        try {
        	/** Pasando o nome do Driver do PostgreSQL */
            
            /** Obtendo a conexao com o banco de dados*/
            con = DriverManager.getConnection(end, user , pass);
            /** Criando o Statement */
            stmt = con.createStatement();
            
            Class.forName("org.postgresql.Driver");
        /** Retorna um erro caso nao encontre o driver, ou alguma informacao sobre o mesmo
         * esteja errada */
             JOptionPane.showMessageDialog(null, "Conexão realizada com Sucesso");

        } catch (ClassNotFoundException cnfe) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar o driver");
            cnfe.printStackTrace();
         /** Retorna um erro caso exista erro de query SQL */   
        } catch (SQLException sqlex) {
            JOptionPane.showMessageDialog(null, "erro na query");
            sqlex.printStackTrace();
        }
    }
    /** Esse metodo quando invocado, realiza a desconexao com o banco */
    public void Desconectar() {
        try {
            con.close();
        /** Retorna um erro caso nao consiga desconectar */    
        } catch (SQLException onConClose) {
            JOptionPane.showMessageDialog(null, "Erro ao desconectar o banco");
            onConClose.printStackTrace();
        }
    }

    PreparedStatement prepareStatement(String sql) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
       
}
