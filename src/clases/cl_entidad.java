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
public class cl_entidad {

    cl_conectar c_conectar = new cl_conectar();

    private int id_entidad;
    private int id_usuario;
    private String documento;
    private String nombre;
    private String direccion;

    public cl_entidad() {
    }

    public int getId_entidad() {
        return id_entidad;
    }

    public void setId_entidad(int id_entidad) {
        this.id_entidad = id_entidad;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }
    
    public boolean validar_documento() {
        boolean existe = false;
        try {
            Statement st = c_conectar.conexion();
            String query = "select id_entidad from entidad "
                    + "where id_usuario = '" + this.id_usuario + "' and documento = '" + this.documento + "'";
            ResultSet rs = c_conectar.consulta(st, query);
            if (rs.next()) {
                existe = true;
                this.id_entidad = rs.getInt("id_entidad");
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
            String query = "select * from entidad "
                    + "where id_usuario = '" + this.id_usuario + "' and id_entidad = '" + this.id_entidad + "'";
            ResultSet rs = c_conectar.consulta(st, query);
            if (rs.next()) {
                existe = true;
                this.nombre = rs.getString("nombre");
                this.documento = rs.getString("documento");
                this.direccion = rs.getString("direccion");
            }
            c_conectar.cerrar(rs);
            c_conectar.cerrar(st);
        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
        return existe;
    }

    public void obtener_id() {
        try {
            Statement st = c_conectar.conexion();
            String query = "select ifnull(max(id_entidad) + 1, 1) as codigo "
                    + "from entidad "
                    + "where id_usuario = '" + this.id_usuario + "'";
            ResultSet rs = c_conectar.consulta(st, query);

            if (rs.next()) {
                this.id_entidad = rs.getInt("codigo");
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
        String query = "insert into entidad "
                + "values ('" + id_entidad + "', '" + id_usuario + "', '" + documento + "', '" + nombre + "', '" + direccion + "')";
        int resultado = c_conectar.actualiza(st, query);
        if (resultado > -1) {
            registrado = true;
        }
        c_conectar.cerrar(st);

        return registrado;
    }

}
