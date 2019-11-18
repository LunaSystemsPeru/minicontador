/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author CALIDAD
 */
public class cl_empresas {

    cl_conectar c_conectar = new cl_conectar();

    private int id_empresa;
    private int id_entidad;
    private int id_usuario;
    private String fecha_creado;
    private int estado;

    public cl_empresas() {
    }

    public int getId_empresa() {
        return id_empresa;
    }

    public void setId_empresa(int id_empresa) {
        this.id_empresa = id_empresa;
    }

    public int getId_entidad() {
        return id_entidad;
    }

    public void setId_entidad(int id_entidad) {
        this.id_entidad = id_entidad;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getFecha_creado() {
        return fecha_creado;
    }

    public void setFecha_creado(String fecha_creado) {
        this.fecha_creado = fecha_creado;
    }

    public void obtener_id() {
        try {
            Statement st = c_conectar.conexion();
            String query = "select ifnull(max(id_empresa) + 1, 1) as codigo "
                    + "from empresas ";
            ResultSet rs = c_conectar.consulta(st, query);

            if (rs.next()) {
                this.id_empresa = rs.getInt("codigo");
            }
            c_conectar.cerrar(rs);
            c_conectar.cerrar(st);
        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
        }

    }

    public boolean obtener_datos() {
        boolean existe = false;
        try {
            Statement st = c_conectar.conexion();
            String query = "select * from empresas "
                    + "where id_usuario = '" + this.id_usuario + "' and id_empresa = '" + this.id_empresa + "'";
            ResultSet rs = c_conectar.consulta(st, query);
            if (rs.next()) {
                existe = true;
                this.id_entidad = rs.getInt("id_entidad");
                this.estado = rs.getInt("estado");
                this.fecha_creado = rs.getString("fecha_registro");
            }
            c_conectar.cerrar(rs);
            c_conectar.cerrar(st);
        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
        return existe;
    }

    public boolean validar_entidad() {
        boolean existe = false;
        try {
            Statement st = c_conectar.conexion();
            String query = "select id_empresa "
                    + "from empresas "
                    + "where id_entidad = '" + this.id_entidad + "' and id_usuario = '" + this.id_usuario + "'";
            ResultSet rs = c_conectar.consulta(st, query);

            if (rs.next()) {
                existe = true;
                this.id_empresa = rs.getInt("id_empresa");
            }
            c_conectar.cerrar(rs);
            c_conectar.cerrar(st);
        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
        return existe;
    }

    public boolean insertar() {
        boolean registrado = false;

        Statement st = c_conectar.conexion();
        String query = "insert into empresas "
                + "values ('" + id_empresa + "', '" + id_entidad + "', '1', now(), '" + id_usuario + "')";
        int resultado = c_conectar.actualiza(st, query);
        if (resultado > -1) {
            registrado = true;
        }
        c_conectar.cerrar(st);

        return registrado;
    }

    public void ver_empresas(JTable tabla, String query) {
        try {
            DefaultTableModel mostrar = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int fila, int columna) {
                    return false;
                }
            };
            Statement st = c_conectar.conexion();
            ResultSet rs = c_conectar.consulta(st, query);

            RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(mostrar);
            tabla.setRowSorter(sorter);

            mostrar.addColumn("Nro RUC");
            mostrar.addColumn("Razon Social");
            mostrar.addColumn("Fec. Registro");
            mostrar.addColumn("Estado");
            mostrar.addColumn("id_empresa");

            while (rs.next()) {

                Object fila[] = new Object[5];

                fila[0] = rs.getString("documento");
                fila[1] = rs.getString("nombre");
                fila[2] = rs.getString("fecha_registro");
                int iestado = rs.getInt("estado");
                String valor_estado = "";
                if (iestado == 1) {
                    valor_estado = "ACTIVO";
                }
                if (iestado == 2) {
                    valor_estado = "--";
                }
                fila[3] = valor_estado;
                fila[4] = rs.getInt("id_empresa");
                mostrar.addRow(fila);
            }

            c_conectar.cerrar(st);
            c_conectar.cerrar(rs);
            tabla.setModel(mostrar);
            tabla.getColumnModel().getColumn(0).setPreferredWidth(100);
            tabla.getColumnModel().getColumn(1).setPreferredWidth(500);
            tabla.getColumnModel().getColumn(2).setPreferredWidth(150);
            tabla.getColumnModel().getColumn(3).setPreferredWidth(80);
            tabla.getColumnModel().getColumn(4).setMinWidth(0);
            tabla.getColumnModel().getColumn(4).setMaxWidth(0);
            tabla.getColumnModel().getColumn(4).setPreferredWidth(0);
            //tabla.setDefaultRenderer(Object.class, new render_tables.render_clientes());
        } catch (SQLException ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(null, ex);
        }
    }

}
