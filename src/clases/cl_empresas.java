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
    cl_varios c_varios = new cl_varios();

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

    public void ver_meses(JTable tabla, int anio) {
        try {
            DefaultTableModel mostrar = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int fila, int columna) {
                    return false;
                }
            };
            Statement st = c_conectar.conexion();
            String query = "select m.nombre, "
                    + "(select ifnull(sum(v.igv*v.tc),0) from ventas as v where v.id_empresa = '" + this.id_empresa + "' and v.periodo like concat(" + anio + ", lpad(m.id, 2, '0')) ) as igv_venta, "
                    + "(select ifnull(sum(c.igv*c.tc),0) from compras as c where c.id_empresa = '" + this.id_empresa + "' and c.periodo like concat(" + anio + ", lpad(m.id, 2, '0')) ) as igv_compra "
                    + "from mes as m "
                    + "order by m.id asc";
            ResultSet rs = c_conectar.consulta(st, query);

            mostrar.addColumn("Mes");
            mostrar.addColumn("IGV Venta");
            mostrar.addColumn("IGV Compra");
            mostrar.addColumn("Diferencia");
            mostrar.addColumn("Acumulado");

            double acumulado = 0;
            double diferencia_igv = 0;
            while (rs.next()) {
                double igv_venta = rs.getDouble("igv_venta");
                double igv_compra = rs.getDouble("igv_compra");

                if (igv_compra > igv_venta) {
                    diferencia_igv = igv_venta - igv_compra;
                } else {
                    diferencia_igv = igv_venta - igv_compra;
                }
                acumulado += diferencia_igv;

                Object fila[] = new Object[5];

                fila[0] = rs.getString("nombre");
                fila[1] = c_varios.formato_totales(igv_venta);
                fila[2] = c_varios.formato_totales(igv_compra);
                fila[3] = c_varios.formato_totales(igv_venta - igv_compra);
                fila[4] = c_varios.formato_totales(acumulado);
                mostrar.addRow(fila);
            }

            c_conectar.cerrar(st);
            c_conectar.cerrar(rs);
            tabla.setModel(mostrar);
            tabla.getColumnModel().getColumn(0).setPreferredWidth(100);
            tabla.getColumnModel().getColumn(1).setPreferredWidth(100);
            tabla.getColumnModel().getColumn(2).setPreferredWidth(100);
            tabla.getColumnModel().getColumn(3).setPreferredWidth(100);
            tabla.getColumnModel().getColumn(4).setPreferredWidth(100);
            c_varios.derecha_celda(tabla, 1);
            c_varios.derecha_celda(tabla, 2);
            c_varios.derecha_celda(tabla, 3);
            c_varios.derecha_celda(tabla, 4);
            //tabla.setDefaultRenderer(Object.class, new render_tables.render_clientes());
        } catch (SQLException ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(null, ex);
        }
    }

}
