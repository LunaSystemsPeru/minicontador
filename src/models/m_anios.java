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
public class m_anios {

    cl_conectar c_conectar = new cl_conectar();

    int id_empresa = frm_menu.c_empresa.getId_empresa();
    int id_usuario = frm_menu.c_usuario.getId_usuario();

    public void listar_anios_ventas(JComboBox combobox) {
        try {

            combobox.removeAllItems();
            Statement st = c_conectar.conexion();
            String query = "select distinct(year(fecha_emision)) as anios "
                    + "from ventas "
                    + "where id_empresa = '" + id_empresa + "' "
                    + "order by year(fecha_emision) asc ";
            ResultSet rs = c_conectar.consulta(st, query);

            int encontrado = 0;
            while (rs.next()) {
                encontrado++;
                combobox.addItem(new o_combobox(rs.getInt("anios"), rs.getString("anios")));
            }
            if (encontrado == 0) {
                combobox.addItem(new o_combobox(0, "NO HAY DATOS"));
            }

            c_conectar.cerrar(st);
            c_conectar.cerrar(rs);
        } catch (SQLException ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public void listar_periodo_ventas(int anio, JComboBox combobox) {
        try {

            combobox.removeAllItems();
            Statement st = c_conectar.conexion();
            String query = "select distinct(periodo) as periodo "
                    + "from ventas "
                    + "where id_empresa = '" + id_empresa + "' and periodo like '" + anio + "%' "
                    + "order by periodo asc";
            ResultSet rs = c_conectar.consulta(st, query);

            int encontrado = 0;
            while (rs.next()) {
                encontrado++;
                combobox.addItem(new o_combobox(rs.getInt("periodo"), rs.getString("periodo")));
            }

            if (encontrado == 0) {
                combobox.addItem(new o_combobox(0, "NO HAY DATOS"));
            }

            c_conectar.cerrar(st);
            c_conectar.cerrar(rs);
        } catch (SQLException ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public void listar_periodo_compras(int anio, JComboBox combobox) {
        try {

            combobox.removeAllItems();
            Statement st = c_conectar.conexion();
            String query = "select distinct(periodo) as periodo "
                    + "from compras "
                    + "where id_empresa = '" + id_empresa + "' and periodo like '" + anio + "%' "
                    + "order by periodo asc";
            ResultSet rs = c_conectar.consulta(st, query);

            int encontrado = 0;
            while (rs.next()) {
                encontrado++;
                combobox.addItem(new o_combobox(rs.getInt("periodo"), rs.getString("periodo")));
            }

            if (encontrado == 0) {
                combobox.addItem(new o_combobox(0, "NO HAY DATOS"));
            }

            c_conectar.cerrar(st);
            c_conectar.cerrar(rs);
        } catch (SQLException ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public void listar_anios_compras(JComboBox combobox) {
        try {

            combobox.removeAllItems();
            Statement st = c_conectar.conexion();
            String query = "select distinct(year(fecha_emision)) as anios "
                    + "from compras "
                    + "where id_empresa = '" + id_empresa + "' "
                    + "order by year(fecha_emision) asc ";
            ResultSet rs = c_conectar.consulta(st, query);

            int encontrado = 0;
            while (rs.next()) {
                encontrado++;
                combobox.addItem(new o_combobox(rs.getInt("anios"), rs.getString("anios")));
            }
            if (encontrado == 0) {
                combobox.addItem(new o_combobox(0, "NO HAY DATOS"));
            }

            c_conectar.cerrar(st);
            c_conectar.cerrar(rs);
        } catch (SQLException ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(null, ex);
        }
    }
}
