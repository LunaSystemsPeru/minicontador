/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
public class cl_compra {

    cl_conectar c_conectar = new cl_conectar();
    cl_varios c_varios = new cl_varios();

    private int periodo;
    private int id_compra;
    private int id_empresa;
    private String fecha_emision;
    private String fecha_vcto;
    private int id_tido;
    private String serie;
    private int numero;
    private int id_proveedor;
    private int id_moneda;
    private double tc;
    private double subtotal;
    private double igv;
    private double total;
    private String tipo_compra;
    private int id_usuario;

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getTipo_compra() {
        return tipo_compra;
    }

    public void setTipo_compra(String tipo_compra) {
        this.tipo_compra = tipo_compra;
    }

    public cl_compra() {
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }

    public int getId_compra() {
        return id_compra;
    }

    public void setId_compra(int id_compra) {
        this.id_compra = id_compra;
    }

    public int getId_empresa() {
        return id_empresa;
    }

    public void setId_empresa(int id_empresa) {
        this.id_empresa = id_empresa;
    }

    public String getFecha_emision() {
        return fecha_emision;
    }

    public void setFecha_emision(String fecha_emision) {
        this.fecha_emision = fecha_emision;
    }

    public String getFecha_vcto() {
        return fecha_vcto;
    }

    public void setFecha_vcto(String fecha_vcto) {
        this.fecha_vcto = fecha_vcto;
    }

    public int getId_tido() {
        return id_tido;
    }

    public void setId_tido(int id_tido) {
        this.id_tido = id_tido;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public int getId_moneda() {
        return id_moneda;
    }

    public void setId_moneda(int id_moneda) {
        this.id_moneda = id_moneda;
    }

    public double getTc() {
        return tc;
    }

    public void setTc(double tc) {
        this.tc = tc;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getIgv() {
        return igv;
    }

    public void setIgv(double igv) {
        this.igv = igv;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public boolean insertar() {
        boolean registrado = false;

        Statement st = c_conectar.conexion();
        String query = "INSERT INTO compras\n"
                + "VALUES ('" + id_compra + "',\n"
                + "        '" + periodo + "',\n"
                + "        '" + id_empresa + "',\n"
                + "        '" + fecha_emision + "',\n"
                + "        '" + fecha_vcto + "',\n"
                + "        '" + id_tido + "',\n"
                + "        '" + serie + "',\n"
                + "        '" + numero + "',\n"
                + "        '" + id_proveedor + "',\n"
                + "        '" + id_moneda + "',\n"
                + "        '" + tc + "',\n"
                + "        '" + subtotal + "',\n"
                + "        '" + igv + "',\n"
                + "        '" + total + "',\n"
                + "        '" + id_usuario + "',\n"
                + "        '" + tipo_compra + "');";
        int resultado = c_conectar.actualiza(st, query);
        if (resultado > -1) {
            registrado = true;
        }
        c_conectar.cerrar(st);

        return registrado;
    }

    public boolean eliminar() {
        boolean registrado = false;

        Statement st = c_conectar.conexion();
        String query = "delete from compras "
                + "where periodo = '" + periodo + "' and id_empresa = '" + id_empresa + "' and id_compras = '" + id_compra + "'";
        int resultado = c_conectar.actualiza(st, query);
        if (resultado > -1) {
            registrado = true;
        }
        c_conectar.cerrar(st);

        return registrado;
    }

    public void obtener_id() {
        try {
            Statement st = c_conectar.conexion();
            String query = "select ifnull(max(id_compras) + 1, 1) as codigo "
                    + "from compras "
                    + "where periodo = '" + periodo + "' and id_empresa = '" + id_empresa + "'";
            ResultSet rs = c_conectar.consulta(st, query);

            if (rs.next()) {
                this.id_compra = rs.getInt("codigo");
            }
            c_conectar.cerrar(rs);
            c_conectar.cerrar(st);
        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
        }

    }

    public double ver_compras(JTable tabla, String query) {
        double total = 0;
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

            mostrar.addColumn("Codigo"); //periodo + id
            mostrar.addColumn("Fecha");
            mostrar.addColumn("Documento");
            mostrar.addColumn("Proveedor");
            mostrar.addColumn("Moneda");
            mostrar.addColumn("Total Compra");
            mostrar.addColumn("Sub Total S/");
            mostrar.addColumn("IGV S/");
            mostrar.addColumn("Exonerado S/");
            mostrar.addColumn("Total S/");
            mostrar.addColumn("");
            mostrar.addColumn("");
            //Creando las filas para el JTable
            int contar = 0;
            while (rs.next()) {
                contar++;
                total = total + rs.getDouble("total");
                double total_venta = rs.getDouble("total");
                double tipo_cambio = rs.getDouble("tc");
                double total_soles = total_venta * tipo_cambio;
                double subtotal_soles = rs.getDouble("subtotal");
                double igv_soles = rs.getDouble("igv");
                double exonerado_soles = total_soles - subtotal_soles - igv_soles;
                int itipo_venta = rs.getInt("tipo_compra");
                if (itipo_venta == 2) {
                    subtotal_soles = total_soles;
                    igv_soles = 0;
                }

                String valor_cliente = "";
                String valor_estado = "";

                Object[] fila = new Object[12];
                fila[0] = rs.getString("periodo") + c_varios.ceros_izquieda_numero(3, rs.getInt("id_compras"));
                fila[1] = c_varios.formato_fecha_vista(rs.getString("fecha_emision"));
                fila[2] = rs.getString("doc_compra") + " / " + c_varios.ceros_izquieda_letras(4, rs.getString("serie")) + " - " + c_varios.ceros_izquieda_numero(7, rs.getInt("numero"));
                fila[3] = rs.getString("doc_cliente") + " | " + rs.getString("nombre_cliente");;
                fila[4] = rs.getString("moneda");
                fila[5] = c_varios.formato_numero(total_venta);
                fila[6] = c_varios.formato_numero(subtotal_soles);
                fila[7] = c_varios.formato_numero(igv_soles);
                fila[8] = c_varios.formato_numero(exonerado_soles);
                fila[9] = c_varios.formato_numero(total_soles);
                fila[10] = rs.getInt("periodo");
                fila[11] = rs.getInt("id_compras");

                mostrar.addRow(fila);
            }
            if (contar == 0) {
                JOptionPane.showMessageDialog(null, "NO SE HAN ENCONTRADO DATOS");
            }
            c_conectar.cerrar(st);
            c_conectar.cerrar(rs);
            tabla.setModel(mostrar);
            tabla.getColumnModel().getColumn(0).setPreferredWidth(80);
            tabla.getColumnModel().getColumn(1).setPreferredWidth(80);
            tabla.getColumnModel().getColumn(2).setPreferredWidth(150);
            tabla.getColumnModel().getColumn(3).setPreferredWidth(400);
            tabla.getColumnModel().getColumn(4).setPreferredWidth(50);
            tabla.getColumnModel().getColumn(5).setPreferredWidth(80);
            tabla.getColumnModel().getColumn(6).setPreferredWidth(80);
            tabla.getColumnModel().getColumn(7).setPreferredWidth(80);
            tabla.getColumnModel().getColumn(8).setPreferredWidth(80);
            tabla.getColumnModel().getColumn(9).setPreferredWidth(80);
            tabla.getColumnModel().getColumn(10).setPreferredWidth(0);
            tabla.getColumnModel().getColumn(11).setPreferredWidth(0);
            c_varios.centrar_celda(tabla, 0);
            c_varios.centrar_celda(tabla, 1);
            c_varios.centrar_celda(tabla, 2);
            c_varios.centrar_celda(tabla, 4);
            c_varios.derecha_celda(tabla, 5);
            c_varios.derecha_celda(tabla, 6);
            c_varios.derecha_celda(tabla, 7);
            c_varios.derecha_celda(tabla, 8);
            //tabla.setDefaultRenderer(Object.class, new render_tables.render_ventas());
        } catch (SQLException e) {
            System.out.print(e);
        }
        return total;
    }

    public boolean obtener_datos() {
        boolean existe = false;
        try {
            Statement st = c_conectar.conexion();
            String query = "select * from compras "
                    + "where periodo = '" + this.periodo + "' and id_compras = '" + this.id_compra + "' and id_empresa = '" + this.id_empresa + "'";
            ResultSet rs = c_conectar.consulta(st, query);
            if (rs.next()) {
                existe = true;
                this.fecha_emision = rs.getString("fecha_emision");
                this.fecha_vcto = rs.getString("fecha_vcto");
                this.id_tido = rs.getInt("id_tido");
                this.serie = rs.getString("serie");
                this.numero = rs.getInt("numero");
                this.id_proveedor = rs.getInt("id_entidad");
                this.id_moneda = rs.getInt("id_moneda");
                this.tc = rs.getDouble("tc");
                this.subtotal = rs.getDouble("subtotal");
                this.igv = rs.getDouble("igv");
                this.total = rs.getDouble("total");

            }
            c_conectar.cerrar(rs);
            c_conectar.cerrar(st);
        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
        return existe;
    }

    public boolean validarDocumento() {
        boolean existe = false;
        try {
            Statement st = c_conectar.conexion();
            String query = "select * from compras "
                    + "where id_entidad = '" + this.id_proveedor + "' and id_tido= '" + this.id_tido + "' "
                    + "and serie = '" + this.serie + "' and numero = '" + this.numero + "' and id_empresa = '" + this.id_empresa + "'";
            ResultSet rs = c_conectar.consulta(st, query);
            if (rs.next()) {
                existe = true;
                this.id_compra = rs.getInt("id_compras");
                this.fecha_emision = rs.getString("fecha_emision");
                this.fecha_vcto = rs.getString("fecha_vcto");
                this.id_tido = rs.getInt("id_tido");
                this.serie = rs.getString("serie");
                this.numero = rs.getInt("numero");
                this.id_proveedor = rs.getInt("id_entidad");
                this.id_moneda = rs.getInt("id_moneda");
                this.tc = rs.getDouble("tc");
                this.subtotal = rs.getDouble("subtotal");
                this.igv = rs.getDouble("igv");
                this.total = rs.getDouble("total");
                this.periodo = rs.getInt("periodo");

            }
            c_conectar.cerrar(rs);
            c_conectar.cerrar(st);
        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
        return existe;
    }

}
