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

/**
 *
 * @author luisoyanguren
 */
public class libros_compras {

    cl_conectar c_conectar = new cl_conectar();
    cl_nota_compra c_nota = new cl_nota_compra();
    cl_compra c_compra = new cl_compra();
    cl_documentos_sunat c_tido = new cl_documentos_sunat();
    cl_varios c_varios = new cl_varios();

    private int id_empresa;

    public libros_compras() {
    }

    public int getId_empresa() {
        return id_empresa;
    }

    public void setId_empresa(int id_empresa) {
        this.id_empresa = id_empresa;
    }

    public void generar_le(String query, String ruc, String speriodo) {
        // System.out.println(query);
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
                String tipo_doc = "";
                String serie_ref = "";
                String nro_ref = "";
                String fecha_ref = "";
                if (rs.getInt("id_tido") == 4 || rs.getInt("id_tido") == 5) {
                    System.out.println("encontre nota" + id_empresa);
                    c_nota.setIdnota(rs.getInt("id_compras"));
                    c_nota.setPeriodo_nota(rs.getInt("periodo"));
                    c_nota.setEmpresa_nota(id_empresa);
                    c_nota.obtener_datos();

                    c_compra.setId_compra(c_nota.getIdcompra());
                    c_compra.setPeriodo(c_nota.getPeriodo_compra());
                    c_compra.setId_empresa(c_nota.getEmpresa_compra());
                    c_compra.obtener_datos();

                    c_tido.setId_tido(c_compra.getId_tido());
                    c_tido.obtener_datos();

                    tipo_doc = c_tido.getCod_sunat();
                    serie_ref = c_compra.getSerie();
                    nro_ref = c_compra.getNumero() + "";
                    fecha_ref = c_varios.formato_fecha_vista(c_compra.getFecha_emision());
                }

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
                
                // #AA84D3@9C60FE*

                double subtotal_compra = rs.getDouble("subtotal");
                double igv_compra = rs.getDouble("igv");
                double tc_compra = rs.getDouble("tc");
                double total_compra = rs.getDouble("total");

                double subtotal_soles = subtotal_compra * tc_compra;
                double igv_soles = igv_compra * tc_compra;
                double total_soles = total_compra * tc_compra;
                double exonerado_soles = total_soles - igv_soles - subtotal_soles;

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
                        + "|" + c_varios.formato_numero(subtotal_soles)
                        + "|" + c_varios.formato_numero(igv_soles)
                        + "|0.00"
                        + "|0.00"
                        + "|0.00"
                        + "|0.00"
                        + "|"  + c_varios.formato_numero(exonerado_soles)
                        + "|0.00"
                        + "|0.00"
                        + "|0.00"
                        + "|" + c_varios.formato_numero(total_soles)
                        + "|" + rs.getString("moneda")
                        + "|" + c_varios.formato_tc(rs.getDouble("tc"))
                        + "|" + fecha_ref
                        + "|" + tipo_doc
                        + "|" + serie_ref
                        + "|"
                        + "|" + nro_ref
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
