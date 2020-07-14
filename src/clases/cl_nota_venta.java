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
 * @author luis
 */
public class cl_nota_venta {

    cl_conectar c_conectar = new cl_conectar();
    private int id_nota;
    private int id_empresa_nota;
    private String periodo_nota;
    private int id_venta;
    private String periodo_venta;
    private int id_empresa_venta;

    public cl_nota_venta() {
    }

    public int getId_nota() {
        return id_nota;
    }

    public void setId_nota(int id_nota) {
        this.id_nota = id_nota;
    }

    public int getId_empresa_nota() {
        return id_empresa_nota;
    }

    public void setId_empresa_nota(int id_empresa_nota) {
        this.id_empresa_nota = id_empresa_nota;
    }

    public String getPeriodo_nota() {
        return periodo_nota;
    }

    public void setPeriodo_nota(String periodo_nota) {
        this.periodo_nota = periodo_nota;
    }

    public int getId_venta() {
        return id_venta;
    }

    public void setId_venta(int id_venta) {
        this.id_venta = id_venta;
    }

    public String getPeriodo_venta() {
        return periodo_venta;
    }

    public void setPeriodo_venta(String periodo_venta) {
        this.periodo_venta = periodo_venta;
    }

    public int getId_empresa_venta() {
        return id_empresa_venta;
    }

    public void setId_empresa_venta(int id_empresa_venta) {
        this.id_empresa_venta = id_empresa_venta;
    }
    
    public boolean insertar() {
        boolean registrado = false;

        Statement st = c_conectar.conexion();
        String query = "insert into notas_venta "
                + "values ('" + id_nota + "', '" + periodo_nota + "', '" + id_empresa_nota + "', '" + id_venta + "', '" + periodo_venta + "', "
                + "'" + id_empresa_venta + "')";
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
        String query = "delete from notas_venta "
                + "where id_ventas = '" + id_nota + "' and periodo = '" + periodo_nota + "' and id_empresa = '" + id_empresa_nota + "'";
        int resultado = c_conectar.actualiza(st, query);
        if (resultado > -1) {
            registrado = true;
        }
        c_conectar.cerrar(st);

        return registrado;
    }
    
    public boolean obtener_datos() {
        boolean existe = false;
        try {
            Statement st = c_conectar.conexion();
            String query = "select * from notas_venta "
                    + "where id_ventas = '" + id_nota + "' and periodo = '" + periodo_nota + "' and id_empresa = '" + id_empresa_nota + "' ";
            System.out.println(query);
            ResultSet rs = c_conectar.consulta(st, query);
            if (rs.next()) {
                existe = true;
                this.id_venta = rs.getInt("id_ventas_ref");
                this.periodo_venta = rs.getString("periodo_ref");
                this.id_empresa_venta = rs.getInt("id_empresa_ref");
            }
            c_conectar.cerrar(rs);
            c_conectar.cerrar(st);
        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
        return existe;
    }

}
