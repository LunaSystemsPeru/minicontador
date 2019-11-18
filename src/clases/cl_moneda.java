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
public class cl_moneda {

    cl_conectar c_conectar = new cl_conectar();
    private int id_moneda;
    private String nombre;
    private String abreviado;

    public cl_moneda() {
    }

    public int getId_moneda() {
        return id_moneda;
    }

    public void setId_moneda(int id_moneda) {
        this.id_moneda = id_moneda;
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
    
    public boolean obtener_datos() {
        boolean existe = false;
        try {
            Statement st = c_conectar.conexion();
            String query = "select * from moneda "
                    + "where id_moneda = '" + this.id_moneda + "' ";
            System.out.println(query);
            ResultSet rs = c_conectar.consulta(st, query);
            if (rs.next()) {
                existe = true;
                this.nombre = rs.getString("nombre");
                this.abreviado = rs.getString("abreviatura");
            }
            c_conectar.cerrar(rs);
            c_conectar.cerrar(st);
        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
        return existe;
    }

}
