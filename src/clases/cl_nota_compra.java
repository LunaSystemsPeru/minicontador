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
 * @author luisoyanguren
 */
public class cl_nota_compra {
    cl_conectar c_conectar = new cl_conectar();
    
    private int idcompra;
    private int periodo_compra;
    private int empresa_compra;
    private int idnota;
    private int periodo_nota;
    private int empresa_nota;

    public cl_nota_compra() {
    }

    public int getIdcompra() {
        return idcompra;
    }

    public void setIdcompra(int idcompra) {
        this.idcompra = idcompra;
    }

    public int getPeriodo_compra() {
        return periodo_compra;
    }

    public void setPeriodo_compra(int periodo_compra) {
        this.periodo_compra = periodo_compra;
    }

    public int getEmpresa_compra() {
        return empresa_compra;
    }

    public void setEmpresa_compra(int empresa_compra) {
        this.empresa_compra = empresa_compra;
    }

    public int getIdnota() {
        return idnota;
    }

    public void setIdnota(int idnota) {
        this.idnota = idnota;
    }

    public int getPeriodo_nota() {
        return periodo_nota;
    }

    public void setPeriodo_nota(int periodo_nota) {
        this.periodo_nota = periodo_nota;
    }

    public int getEmpresa_nota() {
        return empresa_nota;
    }

    public void setEmpresa_nota(int empresa_nota) {
        this.empresa_nota = empresa_nota;
    }
    
    public boolean insertar() {
        boolean registrado = false;

        Statement st = c_conectar.conexion();
        String query = "insert into notas_compras "
                + "values ('" + this.idnota + "', '" + this.periodo_nota + "', '" + this.empresa_nota + "', '" + this.idcompra + "', '" + this.periodo_compra + "', "
                + "'" + this.empresa_compra + "')";
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
        String query = "delete from notas_compras "
                + "where id_compras = '" + idnota + "' and periodo = '" + periodo_nota + "' and id_empresa = '" + this.empresa_nota + "' ";
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
            String query = "select * from notas_compras "
                    + "where id_compras = '" + idnota + "' and periodo = '" + periodo_nota + "' and id_empresa = '" + this.empresa_nota + "' ";
            //System.out.println(query);
            ResultSet rs = c_conectar.consulta(st, query);
            if (rs.next()) {
                existe = true;
                this.idcompra = rs.getInt("id_compras_ref");
                this.periodo_compra = rs.getInt("periodo_ref");
                this.empresa_compra = rs.getInt("id_empresa_ref");
            }
            c_conectar.cerrar(rs);
            c_conectar.cerrar(st);
        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
        return existe;
    }

    
}
