/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.sql.Statement;

/**
 *
 * @author luis
 */
public class cl_diario_venta {
    cl_conectar c_conectar = new cl_conectar();
    
    private int id_venta;
    private String periodo_venta;
    private int id_empresa_venta;
    private String periodo_diario;
    private int subdiario;
    private int id_diario;
    private int id_empresa_diario;

    public cl_diario_venta() {
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

    public String getPeriodo_diario() {
        return periodo_diario;
    }

    public void setPeriodo_diario(String periodo_diario) {
        this.periodo_diario = periodo_diario;
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

    public int getId_empresa_diario() {
        return id_empresa_diario;
    }

    public void setId_empresa_diario(int id_empresa_diario) {
        this.id_empresa_diario = id_empresa_diario;
    }
    
    public boolean insertar() {
        boolean registrado = false;
        Statement st = c_conectar.conexion();
        String query = "insert into ventas_libro_diario "
                + "values ('" + this.id_venta + "', '" + this.periodo_venta + "', '" + this.id_empresa_venta + "', '" + this.periodo_diario + "', '" + this.id_diario + "', "
                + "'" + this.id_empresa_diario + "', '" + this.subdiario + "')";
        int resultado = c_conectar.actualiza(st, query);
        if (resultado > -1) {
            registrado = true;
        }
        return registrado;
    }
}
