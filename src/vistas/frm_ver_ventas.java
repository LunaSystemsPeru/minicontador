/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import clases.cl_varios;
import clases.cl_venta;
import forms.frm_reg_venta;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import minicontador.frm_menu;

/**
 *
 * @author CALIDAD
 */
public class frm_ver_ventas extends javax.swing.JInternalFrame {

    cl_varios c_varios = new cl_varios();
    cl_venta c_venta = new cl_venta();

    int id_empresa = frm_menu.c_empresa.getId_empresa();
    int id_usuario = frm_menu.c_usuario.getId_usuario();

    String query;
    String periodo;

    int fila_seleccionada;

    /**
     * Creates new form frm_ver_ventas
     */
    public frm_ver_ventas() {
        initComponents();
        periodo = c_varios.obtener_periodo();
        query = "select v.id_ventas, v.periodo, v.fecha_emision, ds.cod_sunat, ds.abreviatura as doc_venta, v.serie, v.numero, en.documento as doc_cliente, en.nombre as nombre_cliente, m.abreviatura as moneda, v.tc, v.tipo_venta, v.subtotal, v.igv, v.total, v.estado "
                + "from ventas as v "
                + "inner join entidad as en on en.id_entidad = v.id_entidad and en.id_usuario = v.id_usuario "
                + "inner join documentos_sunat as ds on v.id_tido = ds.id_tido "
                + "inner join moneda as m on m.id_moneda = v.id_moneda "
                + "where v.periodo = '" + periodo + "' and v.id_empresa = '" + id_empresa + "' "
                + "order by v.fecha_emision asc, v.numero asc";
        c_venta.ver_ventas(t_ventas, query);
    }

    private void activar_botones() {
        btn_eliminar.setEnabled(true);
    }

    private void desactivar_botones() {
        btn_eliminar.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        jButton1 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        btn_modificar = new javax.swing.JButton();
        btn_eliminar = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        jButton3 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        jButton5 = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        jButton4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txt_buscar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        t_ventas = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        cbx_buscar = new javax.swing.JComboBox<>();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();

        setTitle("Ver Documentos de Venta");

        jToolBar1.setFloatable(false);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/application_add.png"))); // NOI18N
        jButton1.setText("Agregar");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton1);

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/error.png"))); // NOI18N
        jButton6.setText("Doc. Anulado");
        jButton6.setFocusable(false);
        jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton6);

        btn_modificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/application_edit.png"))); // NOI18N
        btn_modificar.setText("Modificar");
        btn_modificar.setEnabled(false);
        btn_modificar.setFocusable(false);
        btn_modificar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_modificar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btn_modificar);

        btn_eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/delete.png"))); // NOI18N
        btn_eliminar.setText("Eliminar");
        btn_eliminar.setEnabled(false);
        btn_eliminar.setFocusable(false);
        btn_eliminar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_eliminar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminarActionPerformed(evt);
            }
        });
        jToolBar1.add(btn_eliminar);
        jToolBar1.add(jSeparator2);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/clipboard_text.png"))); // NOI18N
        jButton3.setText("ver Asiento");
        jButton3.setEnabled(false);
        jButton3.setFocusable(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton3);
        jToolBar1.add(jSeparator1);

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/computer.png"))); // NOI18N
        jButton5.setText("Generar L.E.");
        jButton5.setFocusable(false);
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton5);
        jToolBar1.add(jSeparator3);

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/cross.png"))); // NOI18N
        jButton4.setText("Salir");
        jButton4.setFocusable(false);
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton4);

        jLabel1.setText("Buscar");

        txt_buscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_buscarKeyPressed(evt);
            }
        });

        t_ventas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        t_ventas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                t_ventasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(t_ventas);

        jLabel2.setText("Seleccionar Año:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "201801", "201802", "201803", "201804", "201805" }));

        cbx_buscar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "POR DOCUMENTO", "POR CLIENTE", "POR PERIODO" }));
        cbx_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbx_buscarActionPerformed(evt);
            }
        });

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2018" }));

        jLabel3.setText("Periodo:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbx_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbx_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void cbx_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbx_buscarActionPerformed
        txt_buscar.setText("");
        txt_buscar.requestFocus();
    }//GEN-LAST:event_cbx_buscarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
        Frame f = JOptionPane.getRootFrame();
        frm_reg_venta.origen = "doc_activo";
        frm_reg_venta dialog = new frm_reg_venta(f, true);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        this.dispose();
        Frame f = JOptionPane.getRootFrame();
        frm_reg_venta.origen = "doc_anulado";
        frm_reg_venta dialog = new frm_reg_venta(f, true);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        query = "select v.id_ventas, v.periodo, v.fecha_emision, ds.cod_sunat, ds.abreviatura as doc_venta, v.serie, v.numero, en.documento as doc_cliente, en.nombre as nombre_cliente, m.abreviatura as moneda, v.tc, v.tipo_venta, v.subtotal, v.igv, v.total, v.estado "
                + "from ventas as v "
                + "inner join entidad as en on en.id_entidad = v.id_entidad and en.id_usuario = v.id_usuario "
                + "inner join documentos_sunat as ds on v.id_tido = ds.id_tido "
                + "inner join moneda as m on m.id_moneda = v.id_moneda "
                + "where v.periodo = '" + periodo + "' and v.id_empresa = '" + id_empresa + "' "
                + "order by v.fecha_emision asc, v.numero asc";
        c_venta.generar_le(query, frm_menu.c_entidad.getDocumento(), periodo);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void txt_buscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_buscarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            int tipo_busqueda = cbx_buscar.getSelectedIndex();
            String busqueda = txt_buscar.getText();

            if (tipo_busqueda == 2) {
                periodo = busqueda;
                query = "select v.id_ventas, v.periodo, v.fecha_emision, ds.cod_sunat, ds.abreviatura as doc_venta, v.serie, v.numero, en.documento as doc_cliente, en.nombre as nombre_cliente, m.abreviatura as moneda, v.tc, v.tipo_venta, v.subtotal, v.igv, v.total, v.estado "
                        + "from ventas as v "
                        + "inner join entidad as en on en.id_entidad = v.id_entidad and en.id_usuario = v.id_usuario "
                        + "inner join documentos_sunat as ds on v.id_tido = ds.id_tido "
                        + "inner join moneda as m on m.id_moneda = v.id_moneda "
                        + "where v.periodo = '" + periodo + "' and v.id_empresa = '" + id_empresa + "' "
                        + "order by v.fecha_emision asc, v.numero asc";
            }

            c_venta.ver_ventas(t_ventas, query);
        }
    }//GEN-LAST:event_txt_buscarKeyPressed

    private void t_ventasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t_ventasMouseClicked
        if (evt.getClickCount() == 2) {
            fila_seleccionada = t_ventas.getSelectedRow();
            if (fila_seleccionada > -1) {
                c_venta.setId_venta(Integer.parseInt(t_ventas.getValueAt(fila_seleccionada, 11).toString()));
                c_venta.setPeriodo(t_ventas.getValueAt(fila_seleccionada, 10).toString());
                c_venta.setId_empresa(id_empresa);
                activar_botones();
            }
        }
    }//GEN-LAST:event_t_ventasMouseClicked

    private void btn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarActionPerformed
        desactivar_botones();
        if (fila_seleccionada > -1) {
            int confirmado = JOptionPane.showConfirmDialog(null, "¿Esta Seguro de Eliminar el Documento de Venta?");
            if (JOptionPane.OK_OPTION == confirmado) {
                c_venta.eliminar();
                c_venta.ver_ventas(t_ventas, query);
            }
        }
    }//GEN-LAST:event_btn_eliminarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_eliminar;
    private javax.swing.JButton btn_modificar;
    private javax.swing.JComboBox<String> cbx_buscar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTable t_ventas;
    private javax.swing.JTextField txt_buscar;
    // End of variables declaration//GEN-END:variables
}
