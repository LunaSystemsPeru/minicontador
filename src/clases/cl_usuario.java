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

/**
 *
 * @author CALIDAD
 */
public class cl_usuario {

    cl_conectar c_conectar = new cl_conectar();

    private int id_usuario;
    private String nombres;
    private String email;
    private String fecha_registro;
    private String fecha_ingreso;
    private String celular;
    private String password;
    private int estado;

    public cl_usuario() {
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(String fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public String getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(String fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public void obtener_id() {
        try {
            Statement st = c_conectar.conexion();
            String query = "select ifnull(max(id_usuario) + 1, 1) as codigo "
                    + "from usuarios";
            ResultSet rs = c_conectar.consulta(st, query);

            if (rs.next()) {
                this.id_usuario = rs.getInt("codigo");
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
        String query = "insert into usuarios "
                + "values ('" + id_usuario + "', '" + nombres + "', '" + email + "', now(), '" + password + "', '" + celular + "', '1', current_time())";
        int resultado = c_conectar.actualiza(st, query);
        if (resultado > -1) {
            registrado = true;
        }
        c_conectar.cerrar(st);

        return registrado;
    }
    
    public boolean validar_email() {
        boolean existe = false;
        try {
            Statement st = c_conectar.conexion();
            String query = "select * "
                    + "from usuarios "
                    + "where email = '" + this.email + "' or celular = '"+this.celular+"'";
            ResultSet rs = c_conectar.consulta(st, query);

            if (rs.next()) {
                existe = true;
                this.id_usuario = rs.getInt("id_usuario");
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
            String query = "select * "
                    + "from usuarios "
                    + "where id_usuario = '" + this.id_usuario + "'";
            ResultSet rs = c_conectar.consulta(st, query);

            if (rs.next()) {
                existe = true;
                this.celular = rs.getString("celular");
                this.nombres = rs.getString("nombres");
                this.email = rs.getString("email");
                this.fecha_registro = rs.getString("fecha_registro");
                this.password = rs.getString("password");
                this.estado = rs.getInt("estado");
                this.fecha_ingreso = rs.getString("ultimo_ingreso");
            }
            c_conectar.cerrar(rs);
            c_conectar.cerrar(st);
        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
        return existe;
    }
    
    public void autoconectar() {
        boolean existe = false;
        try {
            Statement st = c_conectar.conexion();
            String query = "select 1 ";
            ResultSet rs = c_conectar.consulta(st, query);
            if (rs.next()) {
                existe = true;
            }
            c_conectar.cerrar(rs);
            c_conectar.cerrar(st);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "se perdio la conexion \n"+ex.getLocalizedMessage());
            c_conectar.conectar();
        }
        //return existe;
    }
}
