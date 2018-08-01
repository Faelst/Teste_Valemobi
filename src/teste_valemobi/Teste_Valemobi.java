/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste_valemobi;

import java.sql.SQLException;
import java.util.Scanner;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author thiag
 */
public class Teste_Valemobi {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {

        Scanner scan = new Scanner(System.in);

        //**conexao com o banco.
        ConexaoDB banco = new ConexaoDB();

        //*Criaçao do objeto Tb_customer_account
        Tb_customer_account tb = new Tb_customer_account();
        Controle Ctlr = new Controle();
        JPanel panel = new JPanel();
       
        banco.Conectar("jdbc:postgresql://localhost:5432/db_teste", "postgres", "Rafaelnba");

        String[] values = {"Cadasrar Cliente", "Localizar Cliente", "Fechar"};

        Object selected = JOptionPane.showInputDialog(null, " Selecione uma das opções", "Selection", JOptionPane.DEFAULT_OPTION, null, values, "0");
        if (selected != null) {//null if the user cancels. 
            String selectedString = selected.toString();
            //do something
        } else {
            System.out.println("Selecione uma das opções");
        }

       
        // Entrada de dados.
        if (selected == "Cadasrar Cliente") {

            int n_clientes = 0;
            n_clientes = Integer.parseInt(EntradaDados(" Infome numero de Clientes a serem Cadastrados: "));
            n_clientes++;

            for (int i = 1; i != n_clientes; i++) {

                System.out.println(n_clientes);

                tb.setNm_customer(EntradaDados("Informe o NOME do(a) " + i + " Cliente "));
                tb.setCpf_cnpj(EntradaDados("Informe o CFP ou CNPJ do(a) '" + tb.getNm_customer() + "' :"));
                tb.setIs_active(Boolean.TRUE);
                tb.setVl_total(Double.parseDouble(EntradaDados(" Informe o SALDO do " + tb.getNm_customer() + " Cliente")));

                if (tb.getVl_total() <= 0 || tb.getIs_active() == false) {
                    System.out.print(" Cliente esta inativo");
                } else {
                    Ctlr.insert(tb.getCpf_cnpj(), tb.getNm_customer(), true, tb.getVl_total());
                }
            }
        } else if (selected == "Localizar Cliente") {
            Ctlr.selectMedia();
        }

        // Desconectar o Banco.
        banco.Desconectar();

        // TODO code application logic here
    }

    public static String EntradaDados(String desc) {

        Object[] options1 = {"Proximo"};
        
        String seg = desc;

        JPanel panel = new JPanel();

        int dialogButton = JOptionPane.YES_NO_OPTION;

        panel.add(new JLabel(desc));
        JTextField textField = new JTextField(10);
        panel.add(textField);
        String entradaDados = "Nao foi Difitadado nenhum valor";

        int result = JOptionPane.showOptionDialog(null, panel, "Cadastro Cliente",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options1, null);
        if (result == JOptionPane.YES_OPTION) {
            entradaDados = textField.getText();

        }
        
        return entradaDados;

    }

}
