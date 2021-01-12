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
 * @author Mariela
 */
public class libros_ventas {

    cl_conectar c_conectar = new cl_conectar();
    cl_varios c_varios = new cl_varios();
    cl_nota_venta c_nota = new cl_nota_venta();
    cl_venta c_venta = new cl_venta();
    cl_documentos_sunat c_tido = new cl_documentos_sunat();
    
    private int id_empresa;

    public libros_ventas() {
    }

    public int getId_empresa() {
        return id_empresa;
    }

    public void setId_empresa(int id_empresa) {
        this.id_empresa = id_empresa;
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
                String tipo_doc = "";
                String serie_ref = "";
                String nro_ref = "";
                String fecha_ref ="";
                if (rs.getInt("id_tido") == 4 || rs.getInt("id_tido") == 5) {
                    System.out.println("encontre nota" + id_empresa);
                    c_nota.setId_nota(rs.getInt("id_ventas"));
                    c_nota.setPeriodo_nota(rs.getString("periodo"));
                    c_nota.setId_empresa_nota(id_empresa);
                    c_nota.obtener_datos();
                    
                    c_venta.setId_venta(c_nota.getId_venta());
                    c_venta.setPeriodo(c_nota.getPeriodo_venta());
                    c_venta.setId_empresa(c_nota.getId_empresa_venta());
                    c_venta.obtener_datos();
                    
                    c_tido.setId_tido(c_venta.getId_tido());
                    c_tido.obtener_datos();
                    
                    tipo_doc = c_tido.getCod_sunat();
                    serie_ref = c_venta.getSerie();
                    nro_ref = c_venta.getNumero() + "";
                    fecha_ref = c_varios.formato_fecha_vista(c_venta.getFecha());
                }
                String tipo_cliente = "0";
                if (rs.getString("doc_cliente").length() == 11) {
                    tipo_cliente = "6";
                }
                
                if (rs.getString("doc_cliente").length() == 8) {
                    tipo_cliente = "1";
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
                        + "|0.00"
                        + "|0.00"
                        + "|0.00"
                        + "|0.00"
                        + "|0.00"
                        + "|0.00"
                        + "|0.00"
                        + "|0.00"
                        + "|" + c_varios.formato_numero(rs.getDouble("total"))
                        + "|" + rs.getString("moneda")
                        + "|" + c_varios.formato_tc(rs.getDouble("tc"))
                        + "|" + fecha_ref
                        + "|" + tipo_doc
                        + "|" + serie_ref
                        + "|" + nro_ref
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
