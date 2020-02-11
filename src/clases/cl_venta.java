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
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class cl_venta {

    cl_conectar c_conectar = new cl_conectar();
    cl_varios c_varios = new cl_varios();

    private String periodo;
    private int id_venta;
    private int id_empresa;
    private String fecha;
    private int id_tido;
    private String serie;
    private int numero;
    private int id_cliente;
    private int id_moneda;
    private double tc;
    private double subtotal;
    private double igv;
    private double total;
    private int tipo_venta;
    private int id_usuario;
    private int estado;

    public cl_venta() {
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public int getId_venta() {
        return id_venta;
    }

    public void setId_venta(int id_venta) {
        this.id_venta = id_venta;
    }

    public int getId_empresa() {
        return id_empresa;
    }

    public void setId_empresa(int id_empresa) {
        this.id_empresa = id_empresa;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
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
        this.serie = serie.toUpperCase();
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
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

    public int getTipo_venta() {
        return tipo_venta;
    }

    public void setTipo_venta(int tipo_venta) {
        this.tipo_venta = tipo_venta;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public void obtener_id() {
        try {
            Statement st = c_conectar.conexion();
            String query = "select ifnull(max(id_ventas) + 1, 1) as codigo "
                    + "from ventas "
                    + "where periodo = '" + periodo + "' and id_empresa = '" + id_empresa + "'";
            ResultSet rs = c_conectar.consulta(st, query);

            if (rs.next()) {
                this.id_venta = rs.getInt("codigo");
            }
            c_conectar.cerrar(rs);
            c_conectar.cerrar(st);
        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
        }

    }

    public boolean insertar() {
        boolean registrado = false;

        Statement st = c_conectar.conexion();
        String query = "insert into ventas "
                + "values ('" + id_venta + "', '" + periodo + "', '" + id_empresa + "', '" + fecha + "', '" + id_tido + "', "
                + "'" + serie + "', '" + numero + "', '" + id_cliente + "', '" + id_moneda + "', '" + tc + "', '" + subtotal + "', "
                + "'" + igv + "', '" + total + "', '" + tipo_venta + "', '" + estado + "', '" + id_usuario + "')";
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
        String query = "delete from ventas "
                + "where id_venta = '" + id_venta + "' and periodo = '" + periodo + "' and id_empresa = '" + id_empresa + "'";
        int resultado = c_conectar.actualiza(st, query);
        if (resultado > -1) {
            registrado = true;
        }
        c_conectar.cerrar(st);

        return registrado;
    }

    public boolean validar_documento() {
        boolean existe = false;
        try {
            Statement st = c_conectar.conexion();
            String query = "select id_ventas, periodo from ventas "
                    + "where id_tido = '" + this.id_tido + "' and serie = '" + this.serie + "' and numero = '" + this.numero + "'";
            ResultSet rs = c_conectar.consulta(st, query);
            if (rs.next()) {
                existe = true;
                this.id_venta = rs.getInt("id_venta");
                this.periodo = rs.getString("periodo");
            }
            c_conectar.cerrar(rs);
            c_conectar.cerrar(st);
        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
        return existe;
    }

    public boolean obtener_datos() {
        boolean existe = false;
        try {
            Statement st = c_conectar.conexion();
            String query = "select * from ventas "
                    + "where periodo = '" + this.periodo + "' and id_ventas = '" + this.id_venta + "' and id_empresa = '" + this.id_empresa + "'";
            ResultSet rs = c_conectar.consulta(st, query);
            if (rs.next()) {
                existe = true;
                this.fecha = rs.getString("fecha_emision");
                this.id_tido = rs.getInt("id_tido");
                this.serie = rs.getString("serie");
                this.numero = rs.getInt("numero");
                this.id_cliente = rs.getInt("id_entidad");
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

    public double ver_ventas(JTable tabla, String query) {
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
            mostrar.addColumn("Cliente");
            mostrar.addColumn("Moneda");
            mostrar.addColumn("Total Venta");
            mostrar.addColumn("Sub Total S/");
            mostrar.addColumn("IGV S/");
            mostrar.addColumn("Total S/");
            mostrar.addColumn("Estado");
            mostrar.addColumn("Periodo");
            mostrar.addColumn("Id Venta");
            //Creando las filas para el JTable
            while (rs.next()) {
                total = total + rs.getDouble("total");
                double total_venta = rs.getDouble("total");
                double tipo_cambio = rs.getDouble("tc");
                double total_soles = total_venta * tipo_cambio;
                double subtotal_soles = total_soles / 1.18;
                double igv_soles = total_soles / 1.18 * 0.18;
                int itipo_venta = rs.getInt("tipo_venta");
                if (itipo_venta == 2) {
                    subtotal_soles = total_soles;
                    igv_soles = 0;
                }

                int iestado = rs.getInt("estado");
                String valor_cliente = "";
                String valor_estado = "";
                if (iestado == 1) {
                    valor_estado = "ACTIVO";
                    valor_cliente = rs.getString("doc_cliente") + " | " + rs.getString("nombre_cliente");
                }
                if (iestado == 2) {
                    valor_estado = "ANULADO";
                    valor_cliente = "**** ANULADO ****";
                }

                Object[] fila = new Object[12];
                fila[0] = rs.getString("periodo") + c_varios.ceros_izquieda_numero(3, rs.getInt("id_ventas"));
                fila[1] = c_varios.formato_fecha_vista(rs.getString("fecha_emision"));
                fila[2] = rs.getString("doc_venta") + " / " + c_varios.ceros_izquieda_letras(4, rs.getString("serie")) + " - " + c_varios.ceros_izquieda_numero(7, rs.getInt("numero"));
                fila[3] = valor_cliente;
                fila[4] = rs.getString("moneda");
                fila[5] = c_varios.formato_totales(total_venta);
                fila[6] = c_varios.formato_numero(subtotal_soles);
                fila[7] = c_varios.formato_numero(igv_soles);
                fila[8] = c_varios.formato_numero(total_soles);
                fila[9] = valor_estado;
                fila[10] = rs.getString("periodo");
                fila[11] = rs.getInt("id_ventas");
                mostrar.addRow(fila);
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
            //tabla.setDefaultRenderer(Object.class, new render_tables.render_ventas());
        } catch (SQLException e) {
            System.out.print(e);
        }
        return total;
    }

    public void generar_le(String query, String ruc, String speriodo) {
        String nombre_libro = "LE" + ruc + speriodo + "00140100001" + "1" + "11.txt";

        String sdirectorio = c_varios.obtenerDireccionCarpeta() + File.separator + "libros_electronicos" + File.separator + ruc + File.separator + speriodo;
        File directorio = new File(sdirectorio);
        if (!directorio.exists()) {
            directorio.mkdirs();
        }

        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter(directorio + File.separator + nombre_libro);
            pw = new PrintWriter(fichero);

            Statement st = c_conectar.conexion();
            ResultSet rs = c_conectar.consulta(st, query);
            while (rs.next()) {
                String tipo_cliente = "1";
                if (rs.getString("doc_cliente").length() == 11) {
                    tipo_cliente = "6";
                }

                String sestado = "1";
                if (rs.getInt("estado") == 2) {
                    sestado = "2";
                }

                String linea = rs.getString("periodo") + "00"
                        + "|" + rs.getString("periodo") + c_varios.ceros_izquieda_numero(3, rs.getInt("id_ventas"))
                        + "|M" + rs.getString("id_ventas")
                        + "|" + c_varios.formato_fecha_vista(rs.getString("fecha_emision"))
                        + "|"
                        + "|" + rs.getString("cod_sunat")
                        + "|" + c_varios.ceros_izquieda_letras(4, rs.getString("serie"))
                        + "|" + rs.getString("numero")
                        + "|"
                        + "|" + tipo_cliente
                        + "|" + rs.getString("doc_cliente")
                        + "|" + rs.getString("nombre_cliente")
                        + "|0"
                        + "|" + c_varios.formato_numero(rs.getDouble("subtotal"))
                        + "|0"
                        + "|" + c_varios.formato_numero(rs.getDouble("igv"))
                        + "|0"
                        + "|0"
                        + "|0"
                        + "|0"
                        + "|0"
                        + "|0"
                        + "|0"
                        + "|" + c_varios.formato_numero(rs.getDouble("total"))
                        + "|" + rs.getString("moneda")
                        + "|" + c_varios.formato_tc(rs.getDouble("tc"))
                        + "|"
                        + "|"
                        + "|"
                        + "|"
                        + "|"
                        + "|"
                        + "|"
                        + "|" + sestado
                        + "|";
                pw.println(linea);
            }
            c_conectar.cerrar(rs);
            c_conectar.cerrar(st);

            JOptionPane.showMessageDialog(null, "LIBRO ELECTRONICO GENERADO CORRECTAMENTE");
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                // Nuevamente aprovechamos el finally para 
                // asegurarnos que se cierra el fichero.
                if (null != fichero) {
                    fichero.close();
                }
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }

}
