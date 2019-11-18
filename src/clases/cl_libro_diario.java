/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

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
 * @author luis
 */
public class cl_libro_diario {

    cl_conectar c_conectar = new cl_conectar();
    cl_varios c_varios = new cl_varios();

    private String periodo;
    private int subdiario;
    private int id_diario;
    private int id_empresa;
    private int id_usuario;
    private String tipo_asiento;
    private String id_cuenta;
    private String id_ccosto;
    private int id_moneda;
    private String doc_entidad;
    private int id_tido;
    private String serie;
    private int numero;
    private String fecha_cble;
    private String fecha_vcto;
    private String glosa;
    private double debe;
    private double haber;
    private String cod_libro;
    private int estado;

    public cl_libro_diario() {
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public int getSubdiario() {
        return subdiario;
    }

    public void setSubdiario(int subdiario) {
        this.subdiario = subdiario;
    }

    public int getId_diario() {
        return id_diario;
    }

    public void setId_diario(int id_diario) {
        this.id_diario = id_diario;
    }

    public int getId_empresa() {
        return id_empresa;
    }

    public void setId_empresa(int id_empresa) {
        this.id_empresa = id_empresa;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getTipo_asiento() {
        return tipo_asiento;
    }

    public void setTipo_asiento(String tipo_asiento) {
        this.tipo_asiento = tipo_asiento;
    }

    public String getId_cuenta() {
        return id_cuenta;
    }

    public void setId_cuenta(String id_cuenta) {
        this.id_cuenta = id_cuenta;
    }

    public String getId_ccosto() {
        return id_ccosto;
    }

    public void setId_ccosto(String id_ccosto) {
        this.id_ccosto = id_ccosto;
    }

    public int getId_moneda() {
        return id_moneda;
    }

    public void setId_moneda(int id_moneda) {
        this.id_moneda = id_moneda;
    }

    public String getDoc_entidad() {
        return doc_entidad;
    }

    public void setDoc_entidad(String doc_entidad) {
        this.doc_entidad = doc_entidad;
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

    public String getFecha_cble() {
        return fecha_cble;
    }

    public void setFecha_cble(String fecha_cble) {
        this.fecha_cble = fecha_cble;
    }

    public String getFecha_vcto() {
        return fecha_vcto;
    }

    public void setFecha_vcto(String fecha_vcto) {
        this.fecha_vcto = fecha_vcto;
    }

    public String getGlosa() {
        return glosa;
    }

    public void setGlosa(String glosa) {
        this.glosa = glosa;
    }

    public double getDebe() {
        return debe;
    }

    public void setDebe(double debe) {
        this.debe = debe;
    }

    public double getHaber() {
        return haber;
    }

    public void setHaber(double haber) {
        this.haber = haber;
    }

    public String getCod_libro() {
        return cod_libro;
    }

    public void setCod_libro(String cod_libro) {
        this.cod_libro = cod_libro;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public void obtener_subdiario() {
        try {
            Statement st = c_conectar.conexion();
            String query = "select ifnull(max(subdiario) + 1, 1) as codigo "
                    + "from libro_diario "
                    + "where periodo = '" + periodo + "' and id_empresa = '" + id_empresa + "'";
            ResultSet rs = c_conectar.consulta(st, query);

            if (rs.next()) {
                this.subdiario = rs.getInt("codigo");
            }
            c_conectar.cerrar(rs);
            c_conectar.cerrar(st);
        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
        }

    }

    public void obtener_id() {
        try {
            Statement st = c_conectar.conexion();
            String query = "select ifnull(max(id_diario) + 1, 1) as codigo "
                    + "from libro_diario "
                    + "where periodo = '" + periodo + "' and id_empresa = '" + id_empresa + "' and subdiario = '" + subdiario + "'";
            ResultSet rs = c_conectar.consulta(st, query);

            if (rs.next()) {
                this.id_diario = rs.getInt("codigo");
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
            String query = "select * from documentos_sunat "
                    + "where id_tido = '" + this.id_tido + "' ";
            ResultSet rs = c_conectar.consulta(st, query);
            if (rs.next()) {
                existe = true;
//                this.nombre = rs.getString("nombre");
//                this.abreviado = rs.getString("abreviatura");
//                this.cod_sunat = rs.getString("cod_sunat");
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
        String query = "insert into libro_diario "
                + "values ('" + this.periodo + "', '" + this.subdiario + "', '" + this.id_diario + "', '" + this.id_empresa + "', '" + this.id_usuario + "', "
                + "'" + this.tipo_asiento + "', '" + this.id_cuenta + "', '" + this.id_ccosto + "', '" + this.id_moneda + "', '" + this.doc_entidad + "', "
                + "'" + this.id_tido + "', '" + this.serie + "', '" + this.numero + "', '" + this.fecha_cble + "', '" + this.fecha_vcto + "', '" + this.glosa + "', "
                + "'" + this.debe + "', '" + this.haber + "', '" + this.cod_libro + "', '" + this.estado + "')";
        int resultado = c_conectar.actualiza(st, query);
        if (resultado > -1) {
            registrado = true;
        }
        return registrado;
    }

    public double ver_diario(JTable tabla, String query) {
        double total = 0;
        try {
            DefaultTableModel m_caja = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int fila, int columna) {
                    return false;
                }
            };
            Statement st = c_conectar.conexion();
            ResultSet rs = c_conectar.consulta(st, query);

            RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(m_caja);
            tabla.setRowSorter(sorter);

            m_caja.addColumn("Item");
            m_caja.addColumn("Cuenta C.");
            m_caja.addColumn("c. Costo");
            m_caja.addColumn("Emisor");
            m_caja.addColumn("Doc. Ref.");
            m_caja.addColumn("Fec. Doc.");
            m_caja.addColumn("Fec. Vcto.");
            m_caja.addColumn("Moneda");
            m_caja.addColumn("Debe");
            m_caja.addColumn("Haber");
            m_caja.addColumn("Glosa");
            //Creando las filas para el JTable
            while (rs.next()) {
                Object[] fila = new Object[11];
                fila[0] = rs.getString("tipo_asiento") + rs.getString("subdiario") + rs.getString("id_diario");
                fila[1] = rs.getString("id_cuenta_contable");
                fila[2] = rs.getString("id_centro_costo");
                fila[3] = rs.getString("doc_entidad");
                fila[4] = rs.getString("abreviatura") + " | " + rs.getString("serie_doc") + " - " + rs.getString("numero_doc");
                fila[5] = rs.getString("fecha_cble");
                fila[6] = rs.getString("fecha_vcto");
                fila[7] = rs.getString("cod_sunat");
                fila[8] = c_varios.formato_totales(rs.getDouble("debe"));
                fila[9] = c_varios.formato_totales(rs.getDouble("haber"));
                fila[10] = rs.getString("glosa");
                m_caja.addRow(fila);
            }
            c_conectar.cerrar(st);
            c_conectar.cerrar(rs);
            tabla.setModel(m_caja);
            tabla.getColumnModel().getColumn(0).setPreferredWidth(70);
            tabla.getColumnModel().getColumn(1).setPreferredWidth(50);
            tabla.getColumnModel().getColumn(2).setPreferredWidth(50);
            tabla.getColumnModel().getColumn(3).setPreferredWidth(100);
            tabla.getColumnModel().getColumn(4).setPreferredWidth(120);
            tabla.getColumnModel().getColumn(5).setPreferredWidth(80);
            tabla.getColumnModel().getColumn(6).setPreferredWidth(80);
            tabla.getColumnModel().getColumn(7).setPreferredWidth(50);
            tabla.getColumnModel().getColumn(8).setPreferredWidth(80);
            tabla.getColumnModel().getColumn(9).setPreferredWidth(80);
            tabla.getColumnModel().getColumn(10).setPreferredWidth(350);
            c_varios.centrar_celda(tabla, 0);
            c_varios.centrar_celda(tabla, 1);
            c_varios.centrar_celda(tabla, 2);
            c_varios.centrar_celda(tabla, 3);
            c_varios.centrar_celda(tabla, 4);
            c_varios.centrar_celda(tabla, 5);
            c_varios.centrar_celda(tabla, 6);
            c_varios.derecha_celda(tabla, 7);
            c_varios.derecha_celda(tabla, 8);
            c_varios.derecha_celda(tabla, 9);
            //tabla.setDefaultRenderer(Object.class, new render_tables.render_ventas());
        } catch (SQLException e) {
            System.out.print(e);
        }
        return total;
    }

    public void generar_le(String empresa) {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            String nombre = "LE" + empresa + this.periodo + "00005020000" + "1111";
            fichero = new FileWriter("libros_electronicos/" + nombre + ".txt");
            pw = new PrintWriter(fichero);

            String query = "select ld.id_diario, ld.tipo_asiento, ld.id_cuenta_contable, ld.id_centro_costo, ld.doc_entidad, ds.abreviatura, ds.cod_sunat as s_tipo_doc, "
                    + "ld.serie_doc, ld.numero_doc, ld.fecha_cble, ld.fecha_vcto, m.cod_sunat, ld.debe, ld.haber, ld.glosa, ld.periodo, ld.subdiario, ld.codigo_libro, ld.estado_operacion "
                    + "from libro_diario as ld "
                    + "inner join documentos_sunat as ds on ds.id_tido = ld.id_tido "
                    + "inner join moneda as m on m.id_moneda = ld.id_moneda "
                    + "order by ld.subdiario , ld.id_diario asc";
            Statement st = c_conectar.conexion();
            ResultSet rs = c_conectar.consulta(st, query);
            while (rs.next()) {
                String doc_emisor = rs.getString("doc_entidad");
                int tipo_doc_entidad = 0;
                if (doc_emisor.length() == 11) {
                    tipo_doc_entidad = 6;
                }
                if (doc_emisor.length() == 8) {
                    tipo_doc_entidad = 3;
                }
                String linea = rs.getString("periodo")
                        + "|" + rs.getString("id_diario")
                        + "|" + rs.getString("tipo_asiento") + rs.getString("subdiario")
                        + "|" + rs.getString("id_cuenta_contable")
                        + "|"
                        + "|" + rs.getString("id_centro_costo")
                        + "|" + tipo_doc_entidad
                        + "|" + doc_emisor
                        + "|" + c_varios.ceros_izquieda_letras(2, rs.getString("s_tipo_doc"))
                        + "|" + rs.getString("serie_doc")
                        + "|" + rs.getString("numero_doc")
                        + "|" + c_varios.formato_fecha_vista(rs.getString("fecha_cble"))
                        + "|" + c_varios.formato_fecha_vista(rs.getString("fecha_vcto"))
                        + "|" + c_varios.formato_fecha_vista(rs.getString("fecha_cble"))
                        + "|" + rs.getString("glosa")
                        + "|"
                        + "|" + c_varios.formato_numero(rs.getDouble("debe"))
                        + "|" + c_varios.formato_numero(rs.getDouble("haber"))
                        + "|" + rs.getString("codigo_libro")
                        + "|" + rs.getString("estado_operacion");
                pw.println(linea);
            }
            c_conectar.cerrar(rs);
            c_conectar.cerrar(st);
            
            JOptionPane.showMessageDialog(null, "LIBRO ELECTRONICO CREADO, revise la carpeta por favor");

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
