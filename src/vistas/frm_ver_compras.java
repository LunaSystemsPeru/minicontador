/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import clases.cl_compra;
import clases.cl_varios;
import forms.frm_reg_compra;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import minicontador.frm_menu;

/**
 *
 * @author CALIDAD
 */
public class frm_ver_compras extends javax.swing.JInternalFrame {

    cl_compra c_compra = new cl_compra();
    cl_varios c_varios = new cl_varios();

    int id_empresa = frm_menu.c_empresa.getId_empresa();
    int id_usuario = frm_menu.c_usuario.getId_usuario();

    int fila_seleccionada;
    String periodo;
    String query;

    /**
     * Creates new form frm_ver_ventas
     */
    public frm_ver_compras() {
        initComponents();
        periodo = c_varios.obtener_periodo();
        query = "SELECT c.id_compras, c.periodo, c.fecha_emision, ds.cod_sunat, ds.abreviatura "
                + "AS doc_compra, c.serie, c.numero, en.documento AS doc_cliente, "
                + "en.nombre AS nombre_cliente, m.abreviatura AS moneda, c.tc, c.tipo_compra, c.subtotal, c.igv, c.total "
                + "FROM compras AS c "
                + "INNER JOIN entidad AS en ON en.id_entidad = c.id_entidad AND en.id_usuario = c.id_usuario "
                + "INNER JOIN documentos_sunat AS ds ON c.id_tido = ds.id_tido "
                + "INNER JOIN moneda AS m ON m.id_moneda = c.id_moneda "
                + "where c.periodo = '" + periodo + "' and c.id_empresa = '" + id_empresa + "' "
                + "order by c.fecha_emision asc, c.numero asc";
        // System.out.println(query);
        c_compra.ver_compras(t_compras, query);
    }

    private void desactivar_botones() {
        btn_eliminar.setEnabled(false);
        btn_modificar.setEnabled(false);
        btn_ver_detalles.setEnabled(false);
    }

    private void activar_botones() {
        btn_eliminar.setEnabled(true);
        btn_modificar.setEnabled(true);
        btn_ver_detalles.setEnabled(true);
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
        btn_modificar = new javax.swing.JButton();
        btn_eliminar = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        btn_ver_detalles = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        jButton5 = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        jButton4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txt_busqueda = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        t_compras = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        cbx_tipo_busqueda = new javax.swing.JComboBox<>();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();

        setTitle("Ver Documentos de Compras");

        jToolBar1.setFloatable(false);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/add.png"))); // NOI18N
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

        btn_modificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/application_edit.png"))); // NOI18N
        btn_modificar.setText("Modificar");
        btn_modificar.setEnabled(false);
        btn_modificar.setFocusable(false);
        btn_modificar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_modificar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btn_modificar);

        btn_eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/trash_16.png"))); // NOI18N
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

        btn_ver_detalles.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/clipboard_text.png"))); // NOI18N
        btn_ver_detalles.setText("Ver Detalles");
        btn_ver_detalles.setEnabled(false);
        btn_ver_detalles.setFocusable(false);
        btn_ver_detalles.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_ver_detalles.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btn_ver_detalles);
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

        txt_busqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_busquedaKeyPressed(evt);
            }
        });

        t_compras.setModel(new javax.swing.table.DefaultTableModel(
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
        t_compras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                t_comprasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(t_compras);

        jLabel2.setText("Seleccionar Año:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "201801", "201802", "201803", "201804", "201805" }));

        cbx_tipo_busqueda.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "POR DOCUMENTO", "POR PROVEEDOR", "POR PERIODO" }));
        cbx_tipo_busqueda.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbx_tipo_busquedaItemStateChanged(evt);
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
                        .addComponent(cbx_tipo_busqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_busqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(txt_busqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbx_tipo_busqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
        Frame f = JOptionPane.getRootFrame();
        frm_reg_compra dialog = new frm_reg_compra(f, true);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        String ruc = frm_menu.c_entidad.getDocumento();
        query = "SELECT c.id_compras, c.periodo, c.fecha_emision, ds.cod_sunat, ds.abreviatura "
                + "AS doc_compra, c.serie, c.numero, en.documento AS doc_cliente, "
                + "en.nombre AS nombre_cliente, m.abreviatura AS moneda, c.tc, c.tipo_compra, c.subtotal, c.igv, c.total "
                + "FROM compras AS c "
                + "INNER JOIN entidad AS en ON en.id_entidad = c.id_entidad AND en.id_usuario = c.id_usuario "
                + "INNER JOIN documentos_sunat AS ds ON c.id_tido = ds.id_tido "
                + "INNER JOIN moneda AS m ON m.id_moneda = c.id_moneda "
                + "where c.periodo = '" + periodo + "' and c.id_empresa = '" + id_empresa + "' "
                + "order by c.fecha_emision asc, c.numero asc";

        c_compra.generar_le_domiciliado(query, ruc, periodo);
        c_compra.generar_le(query, ruc, periodo);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void btn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarActionPerformed
        //preguntar  
        desactivar_botones();
        if (fila_seleccionada > -1) {
            int confirmado = JOptionPane.showConfirmDialog(null, "¿Esta Seguro de Eliminar el Documento de Ingreso de Mercaderia?");
            if (JOptionPane.OK_OPTION == confirmado) {
                c_compra.eliminar();
                c_compra.ver_compras(t_compras, query);
            }
        }
    }//GEN-LAST:event_btn_eliminarActionPerformed

    private void t_comprasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t_comprasMouseClicked
        if (evt.getClickCount() == 2) {
            fila_seleccionada = t_compras.getSelectedRow();
            c_compra.setPeriodo(Integer.parseInt(t_compras.getValueAt(fila_seleccionada, 9).toString()));
            c_compra.setId_compra(Integer.parseInt(t_compras.getValueAt(fila_seleccionada, 10).toString()));
            c_compra.setId_empresa(id_empresa);
            activar_botones();
        }
    }//GEN-LAST:event_t_comprasMouseClicked

    private void txt_busquedaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_busquedaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            int tipo_busqueda = cbx_tipo_busqueda.getSelectedIndex();
            String busqueda = txt_busqueda.getText();

            if (tipo_busqueda == 2) {
                periodo = busqueda;
                query = "SELECT c.id_compras, c.periodo, c.fecha_emision, ds.cod_sunat, ds.abreviatura "
                        + "AS doc_compra, c.serie, c.numero, en.documento AS doc_cliente, "
                        + "en.nombre AS nombre_cliente, m.abreviatura AS moneda, c.tc, c.tipo_compra, c.subtotal, c.igv, c.total "
                        + "FROM compras AS c "
                        + "INNER JOIN entidad AS en ON en.id_entidad = c.id_entidad AND en.id_usuario = c.id_usuario "
                        + "INNER JOIN documentos_sunat AS ds ON c.id_tido = ds.id_tido "
                        + "INNER JOIN moneda AS m ON m.id_moneda = c.id_moneda "
                        + "where c.periodo = '" + periodo + "' and c.id_empresa = '" + id_empresa + "' "
                        + "order by c.fecha_emision asc, c.numero asc";
            }

            c_compra.ver_compras(t_compras, query);
        }
    }//GEN-LAST:event_txt_busquedaKeyPressed

    private void cbx_tipo_busquedaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbx_tipo_busquedaItemStateChanged
        txt_busqueda.selectAll();
        txt_busqueda.requestFocus();
    }//GEN-LAST:event_cbx_tipo_busquedaItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_eliminar;
    private javax.swing.JButton btn_modificar;
    private javax.swing.JButton btn_ver_detalles;
    private javax.swing.JComboBox<String> cbx_tipo_busqueda;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
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
    private javax.swing.JTable t_compras;
    private javax.swing.JTextField txt_busqueda;
    // End of variables declaration//GEN-END:variables
}
