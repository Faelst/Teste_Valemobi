/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste_valemobi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author thiag
 */
public class Controle {

    public void insert(String cpf_cnpj, String nm_customer, boolean is_active, double vl_total) throws SQLException {

        // Conexação com o Banco.
        Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Rafaelnba");

        // Script SQL , para ser executada.
        String sql = "insert into tb_customer_account(cpf_cnpj , nm_customer, is_active, vl_total) values(?,?,?,?)";
        // Preparação para execução do Script.
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, cpf_cnpj);
        stmt.setString(2, nm_customer);
        stmt.setBoolean(3, is_active);
        stmt.setDouble(4, vl_total);
        // Execução do script
        stmt.execute();
        // Saida de Dados.
        System.out.println(nm_customer);
        System.out.println(cpf_cnpj);
        System.out.println(is_active);
        System.out.println("R$" + vl_total);

    }

    // Metodo para Localizar dados no Banco.
    public void selectMedia() throws SQLException {
        
        // Conexação com o Banco.
        Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Rafaelnba");
        // Script SQL , para ser executada.
        String sql = "select id_customer, nm_customer , vl_total from tb_customer_account where (vl_total>560 and (id_customer>1 and id_customer<100));";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        String r = "R$";
        int count = 1;
        
        String display = "";

      
        List<String> nomeCliente = new ArrayList<String>();
        List<Double> saldoCliente = new ArrayList<Double>();
        nomeCliente.add("Nome do Cliente");
        
        
        System.out.printf("%30s%25s \n\n", "NOME DO CLIENTE", "SALDO DO CLIENTE");
        // Estrutura de repitição para ler todos os dados resultado do SELECT.
        while (rs.next()) {
            //Saida de dados.
            nomeCliente.add(rs.getString("nm_customer"));
            saldoCliente.add(rs.getDouble("vl_total"));
            System.out.printf("%30s ", rs.getString("nm_customer"));
            System.out.printf("%10s%8s  \n", r, rs.getDouble("vl_total"));
           
            
        }
      
        sql = "select avg(vl_total) from tb_customer_account where (vl_total>560 and (id_customer>1 and id_customer<100));";
        stmt = conn.prepareStatement(sql);
        rs = stmt.executeQuery();
        rs.next();
        
        
   String output = "";
for(int i = 0; i<saldoCliente.size(); i++){
    String everything = nomeCliente.get(i);
    String everything2 = saldoCliente.get(i).toString();

    output += everything +"\nSaldo: R$ "+ everything2 +  "\n\n";       
}
JOptionPane.showMessageDialog(null, output+" Media Salarial: R$ "+rs.getDouble(1));
         

        
    }
    
}
