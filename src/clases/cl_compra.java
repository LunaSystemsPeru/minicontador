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
                double subtotal_soles = total_soles / 1.18;
                double igv_soles = total_soles / 1.18 * 0.18;
                int itipo_venta = rs.getInt("tipo_compra");
                if (itipo_venta == 2) {
                    subtotal_soles = total_soles;
                    igv_soles = 0;
                }

                String valor_cliente = "";
                String valor_estado = "";

                Object[] fila = new Object[11];
                fila[0] = rs.getString("periodo") + c_varios.ceros_izquieda_numero(3, rs.getInt("id_compras"));
                fila[1] = c_varios.formato_fecha_vista(rs.getString("fecha_emision"));
                fila[2] = rs.getString("doc_compra") + " / " + c_varios.ceros_izquieda_letras(4, rs.getString("serie")) + " - " + c_varios.ceros_izquieda_numero(7, rs.getInt("numero"));
                fila[3] = rs.getString("doc_cliente") + " | " + rs.getString("nombre_cliente");;
                fila[4] = rs.getString("moneda");
                fila[5] = c_varios.formato_numero(total_venta);
                fila[6] = c_varios.formato_numero(subtotal_soles);
                fila[7] = c_varios.formato_numero(igv_soles);
                fila[8] = c_varios.formato_numero(total_soles);
                fila[9] = rs.getInt("periodo");
                fila[10] = rs.getInt("id_compras");

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
            tabla.getColumnModel().getColumn(9).setPreferredWidth(0);
            tabla.getColumnModel().getColumn(10).setPreferredWidth(0);
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

    public void generar_le(String query, String ruc, String speriodo) {
        System.out.println(query);
        String nombre_libro = "LE" + ruc + speriodo + "00080100001" + "1" + "11.txt";

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
                if (!c_varios.formato_periodo(rs.getString("fecha_emision")).equals(speriodo)) {
                    System.out.println("periodo fecha" + c_varios.formato_periodo(rs.getString("fecha_emision")));
                    System.out.println("\nperiodo libro" + speriodo);
                    sestado = "6";
                }

                String linea = rs.getString("periodo") + "00"
                        + "|" + rs.getString("periodo") + c_varios.ceros_izquieda_numero(3, rs.getInt("id_compras"))
                        + "|M" + rs.getString("id_compras")
                        + "|" + c_varios.formato_fecha_vista(rs.getString("fecha_emision"))
                        + "|" + c_varios.formato_fecha_vista(rs.getString("fecha_emision"))
                        + "|" + rs.getString("cod_sunat")
                        + "|" + c_varios.ceros_izquieda_letras(4, rs.getString("serie"))
                        + "|"
                        + "|" + rs.getString("numero")
                        + "|"
                        + "|" + tipo_cliente
                        + "|" + rs.getString("doc_cliente")
                        + "|" + rs.getString("nombre_cliente")
                        + "|" + c_varios.formato_numero(rs.getDouble("subtotal"))
                        + "|" + c_varios.formato_numero(rs.getDouble("igv"))
                        + "|0"
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
                        + "|"
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

    public void generar_le_domiciliado(String query, String ruc, String speriodo) {
        String nombre_libro = "LE" + ruc + speriodo + "00080200001" + "0" + "11.txt";

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

            //pw.println("");
            JOptionPane.showMessageDialog(null, "LIBRO ELECTRONICO GENERADO CORRECTAMENTE");
        } catch (IOException e) {
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
