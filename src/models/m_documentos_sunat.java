/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import clases.cl_conectar;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import minicontador.frm_menu;
import objects.o_combobox;

/**
 *
 * @author luis
 */
public class m_documentos_sunat {
    cl_conectar c_conectar = new cl_conectar();
    
    public void listar_combobox(JComboBox combobox) {
        try {
            
            combobox.removeAllItems();
            Statement st = c_conectar.conexion();
            String query = "select id_tido, abreviatura, nombre "
                    + "from documentos_sunat "
                    + "order by nombre asc ";
            ResultSet rs = c_conectar.consulta(st, query);
            
            while (rs.next()) {
                combobox.addItem(new o_combobox(rs.getInt("id_tido"), rs.getString("abreviatura") + " | " + rs.getString("nombre")));
            }
            
            c_conectar.cerrar(st);
            c_conectar.cerrar(rs);
        } catch (SQLException ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(null, ex);
        }
    }
}
