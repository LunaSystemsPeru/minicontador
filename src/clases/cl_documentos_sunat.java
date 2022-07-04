/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author CALIDAD
 */
public class cl_documentos_sunat {

    cl_conectar c_conectar = new cl_conectar();
    
    private int id_tido;
    private String nombre;
    private String abreviado;
    private String cod_sunat;

    public cl_documentos_sunat() {
    }

    public int getId_tido() {
        return id_tido;
    }

    public void setId_tido(int id_tido) {
        this.id_tido = id_tido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAbreviado() {
        return abreviado;
    }

    public void setAbreviado(String abreviado) {
        this.abreviado = abreviado;
    }

    public String getCod_sunat() {
        return cod_sunat;
    }

    public void setCod_sunat(String cod_sunat) {
        this.cod_sunat = cod_sunat;
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
                this.nombre = rs.getString("nombre");
                this.abreviado = rs.getString("abreviatura");
                this.cod_sunat = rs.getString("cod_sunat");
            }
            c_conectar.cerrar(rs);
            c_conectar.cerrar(st);
        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
        return existe;
    }

}
