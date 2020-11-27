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
import objects.o_combobox;

/**
 *
 * @author luis
 */
public class m_cuentas {

    cl_conectar c_conectar = new cl_conectar();

    private void listar_combobox(String query, JComboBox combobox) {
        try {

            combobox.removeAllItems();
            Statement st = c_conectar.conexion();
            ResultSet rs = c_conectar.consulta(st, query);

            while (rs.next()) {
                combobox.addItem(new o_combobox(rs.getInt("id_cuenta_contable"), rs.getString("id_cuenta_contable") + " | " + rs.getString("nombre")));
            }

            c_conectar.cerrar(st);
            c_conectar.cerrar(rs);
        } catch (SQLException ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public void cbx_ventas_70(JComboBox combobox) {
        String query = "select * from cuentas_contables "
                + "where id_cuenta_contable like '70%' and LENGTH(id_cuenta_contable) > 3 "
                + "order by id_cuenta_contable asc";
        listar_combobox(query, combobox);
    }

    public void cbx_ventas_12(JComboBox combobox) {
        String query = "select * from cuentas_contables "
                + "where id_cuenta_contable like '1_12%' and LENGTH(id_cuenta_contable) > 3 "
                + "order by id_cuenta_contable asc";
        listar_combobox(query, combobox);
    }
    
    public void cbx_cuentas_orden(JComboBox combobox, String inicial) {
        String query = "select id_cuenta_contable, nombre from cuentas_contables "
                + "where id_cuenta_contable like '"+inicial+"%' and LENGTH(id_cuenta_contable) = 2 "
                + "order by id_cuenta_contable asc";
        listar_combobox(query, combobox);
    }
    
    public void cbx_cuentas_final(JComboBox combobox, String inicial, int tope) {
        String query = "select id_cuenta_contable, nombre from cuentas_contables "
                + "where id_cuenta_contable like '"+inicial+"%' and (LENGTH(id_cuenta_contable) > 2 and LENGTH(id_cuenta_contable) <= '"+tope+"') "
                + "order by id_cuenta_contable asc";
        System.out.println(query);
        listar_combobox(query, combobox);
    }

    public void listar_libros(JComboBox combobox) {

        combobox.removeAllItems();
        combobox.addItem(new o_combobox(0, "0 | APERTURA"));
        combobox.addItem(new o_combobox(5, "5 | VENTAS"));
        combobox.addItem(new o_combobox(8, "8 | COMPRAS"));

    }

}
