/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import clases.cl_conectar;
import clases.cl_moneda;
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
public class m_empresas {
    
    cl_conectar c_conectar = new cl_conectar();
    
    private final int id_usuario = frm_menu.c_usuario.getId_usuario();
    
    public void listar_empresas(JComboBox combobox) {
        try {
            
            combobox.removeAllItems();
            Statement st = c_conectar.conexion();
            String query = "select e.id_empresa, en.documento, en.nombre "
                    + "from empresas as e "
                    + "inner join entidad as en on en.id_entidad = e.id_entidad and en.id_usuario = e.id_usuario "
                    + "where e.id_usuario = '" + this.id_usuario + "' "
                    + "order by nombre asc";
            ResultSet rs = c_conectar.consulta(st, query);
            
            while (rs.next()) {
                combobox.addItem(new o_combobox(rs.getInt("id_empresa"), rs.getString("documento") + " | " + rs.getString("nombre")));
            }
            
            c_conectar.cerrar(st);
            c_conectar.cerrar(rs);
        } catch (SQLException ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(null, ex);
        }
    }
}
