/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import clases.cl_documentos_sunat;
import clases.cl_libro_diario;
import clases.cl_moneda;
import clases.cl_plan_contable;
import clases.cl_varios;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import minicontador.frm_menu;
import models.m_cuentas;
import models.m_documentos_sunat;
import models.m_moneda;
import objects.o_combobox;
import vistas.frm_ver_diario;

/**
 *
 * @author luis
 */
public class frm_reg_diario extends javax.swing.JDialog {

    cl_varios c_varios = new cl_varios();

    cl_plan_contable c_cuentas = new cl_plan_contable();
    cl_libro_diario c_libro = new cl_libro_diario();
    cl_documentos_sunat c_tido = new cl_documentos_sunat();
    cl_moneda c_moneda = new cl_moneda();

    DefaultTableModel m_caja = null;

    m_documentos_sunat m_documentos = new m_documentos_sunat();
    m_moneda m_moneda = new m_moneda();
    m_cuentas m_cuentas = new m_cuentas();

    int id_usuario = frm_menu.c_usuario.getId_usuario();
    int id_empresa = frm_menu.c_empresa.getId_empresa();

    /**
     * Creates new form frm_reg_diario
     */
    public frm_reg_diario(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        cargar_tabla();

        txt_fecha.setText(c_varios.formato_fecha_vista(c_varios.getFechaActual()));
        m_documentos.listar_combobox(cbx_tipo_doc);

        m_moneda.listar_monedas(cbx_moneda);
        m_cuentas.listar_libros(cbx_libro);
    }

    public final void cargar_tabla() {
        m_caja = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };
        m_caja.addColumn("Tipo");
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
        m_caja.addColumn("id_tido");
        m_caja.addColumn("serie_doc");
        m_caja.addColumn("numero_doc");
        t_cuentas_venta.setModel(m_caja);
        t_cuentas_venta.getColumnModel().getColumn(0).setPreferredWidth(30);
        t_cuentas_venta.getColumnModel().getColumn(1).setPreferredWidth(20);
        t_cuentas_venta.getColumnModel().getColumn(2).setPreferredWidth(50);
        t_cuentas_venta.getColumnModel().getColumn(3).setPreferredWidth(50);
        t_cuentas_venta.getColumnModel().getColumn(4).setPreferredWidth(80);
        t_cuentas_venta.getColumnModel().getColumn(5).setPreferredWidth(120);
        t_cuentas_venta.getColumnModel().getColumn(6).setPreferredWidth(80);
        t_cuentas_venta.getColumnModel().getColumn(7).setPreferredWidth(80);
        t_cuentas_venta.getColumnModel().getColumn(8).setPreferredWidth(50);
        t_cuentas_venta.getColumnModel().getColumn(9).setPreferredWidth(80);
        t_cuentas_venta.getColumnModel().getColumn(10).setPreferredWidth(80);
        t_cuentas_venta.getColumnModel().getColumn(11).setPreferredWidth(350);
        t_cuentas_venta.getColumnModel().getColumn(12).setPreferredWidth(0);
        t_cuentas_venta.getColumnModel().getColumn(13).setPreferredWidth(0);
        t_cuentas_venta.getColumnModel().getColumn(14).setPreferredWidth(0);
        c_varios.centrar_celda(t_cuentas_venta, 0);
        c_varios.centrar_celda(t_cuentas_venta, 1);
        c_varios.centrar_celda(t_cuentas_venta, 2);
        c_varios.centrar_celda(t_cuentas_venta, 3);
        c_varios.centrar_celda(t_cuentas_venta, 4);
        c_varios.centrar_celda(t_cuentas_venta, 5);
        c_varios.centrar_celda(t_cuentas_venta, 6);
        c_varios.centrar_celda(t_cuentas_venta, 7);
        c_varios.derecha_celda(t_cuentas_venta, 8);
        c_varios.derecha_celda(t_cuentas_venta, 9);
        c_varios.derecha_celda(t_cuentas_venta, 10);
    }

    private void calcular_totales() {
        double debe = 0;
        double haber = 0;
        double diferencia = 0;
        int contar_filas = t_cuentas_venta.getRowCount();
        for (int i = 0; i < contar_filas; i++) {
            debe = debe + Double.parseDouble(t_cuentas_venta.getValueAt(i, 9).toString());
            haber = haber + Double.parseDouble(t_cuentas_venta.getValueAt(i, 10).toString());
        }
        diferencia = debe - haber;
        txt_tot_debe.setText(c_varios.formato_totales(debe));
        txt_tot_haber.setText(c_varios.formato_totales(haber));
        txt_tot_diferencia.setText(c_varios.formato_totales(diferencia));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cbx_libro = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        txt_periodo = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txt_tot_debe = new javax.swing.JTextField();
        txt_tot_haber = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cbx_moneda = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        txt_tot_diferencia = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cbx_tipo = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txt_fecha1 = new javax.swing.JFormattedTextField();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        t_cuentas_venta = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        txt_cod_cuenta = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        txt_nom_cuenta = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txt_debe = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txt_haber = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txt_glosa = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        cbx_tipo_doc = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        txt_serie = new javax.swing.JTextField();
        txt_numero = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txt_doc_emisor = new javax.swing.JTextField();
        txt_fecha = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        jToolBar2 = new javax.swing.JToolBar();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Reg. Movimiento Diario");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos Contable"));

        jLabel1.setText("Libro:");

        cbx_libro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "APERTURA", "CIERRE", "VENTAS", "COMPRAS", "CAJAS Y BANCOS" }));
        cbx_libro.setEnabled(false);
        cbx_libro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbx_libroKeyPressed(evt);
            }
        });

        jLabel4.setText("Periodo:");

        txt_periodo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_periodo.setText("201903");
        txt_periodo.setEnabled(false);
        txt_periodo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_periodoKeyPressed(evt);
            }
        });

        jLabel18.setText("Debe:");

        jLabel19.setText("Haber:");

        txt_tot_debe.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_tot_debe.setText("0.00");
        txt_tot_debe.setEnabled(false);

        txt_tot_haber.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_tot_haber.setText("0.00");
        txt_tot_haber.setEnabled(false);

        jLabel6.setText("Moneda:");

        cbx_moneda.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SOLES", "DOLAR AMERICANO" }));
        cbx_moneda.setEnabled(false);
        cbx_moneda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbx_monedaKeyPressed(evt);
            }
        });

        jLabel7.setText("Diferencia:");

        txt_tot_diferencia.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_tot_diferencia.setText("0.00");
        txt_tot_diferencia.setEnabled(false);

        jLabel3.setText("Tipo:");

        cbx_tipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A - APERTURA", "M - MOVIMIENTO", "C - CIERRE" }));
        cbx_tipo.setEnabled(false);
        cbx_tipo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbx_tipoKeyPressed(evt);
            }
        });

        jLabel11.setText("Tipo Cambio");

        jLabel12.setText("Fecha:");

        try {
            txt_fecha1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txt_fecha1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_fecha1.setText("02/03/2019");
        txt_fecha1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_fecha1KeyPressed(evt);
            }
        });

        jTextField1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField1.setText("1.000");
        jTextField1.setEnabled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_fecha1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_periodo, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbx_moneda, 0, 290, Short.MAX_VALUE)
                            .addComponent(cbx_libro, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbx_tipo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel18)
                        .addComponent(jLabel19))
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_tot_haber, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                    .addComponent(txt_tot_debe)
                    .addComponent(txt_tot_diferencia)
                    .addComponent(jTextField1))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_tot_debe, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_fecha1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_periodo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_tot_haber, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_tot_diferencia, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(cbx_tipo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbx_libro, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbx_moneda, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        t_cuentas_venta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"1", "601101", "20532059250", "VR | E001 - 0002154", "06/03/2019", "PEN", "2000.00", null, "COMPRA XXX"}
            },
            new String [] {
                "Item", "Cuenta C.", "Emisor", "Documento", "Fec. Doc.", "Mon.", "Debe ", "Haber", "Glosa"
            }
        ));
        t_cuentas_venta.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane1.setViewportView(t_cuentas_venta);
        if (t_cuentas_venta.getColumnModel().getColumnCount() > 0) {
            t_cuentas_venta.getColumnModel().getColumn(0).setPreferredWidth(10);
            t_cuentas_venta.getColumnModel().getColumn(1).setPreferredWidth(60);
            t_cuentas_venta.getColumnModel().getColumn(2).setPreferredWidth(70);
            t_cuentas_venta.getColumnModel().getColumn(3).setPreferredWidth(100);
            t_cuentas_venta.getColumnModel().getColumn(4).setPreferredWidth(150);
            t_cuentas_venta.getColumnModel().getColumn(5).setPreferredWidth(80);
            t_cuentas_venta.getColumnModel().getColumn(6).setPreferredWidth(80);
            t_cuentas_venta.getColumnModel().getColumn(7).setPreferredWidth(30);
            t_cuentas_venta.getColumnModel().getColumn(8).setPreferredWidth(80);
            t_cuentas_venta.getColumnModel().getColumn(9).setPreferredWidth(80);
            t_cuentas_venta.getColumnModel().getColumn(10).setPreferredWidth(150);
        }

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Asignacion Contable"));

        jLabel13.setText("Cuenta Contable:");

        txt_cod_cuenta.setEnabled(false);
        txt_cod_cuenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_cod_cuentaKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_cod_cuentaKeyPressed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/find.png"))); // NOI18N
        jButton1.setEnabled(false);

        txt_nom_cuenta.setEnabled(false);

        jLabel16.setText("Debe:");

        txt_debe.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_debe.setText("0.00");
        txt_debe.setEnabled(false);
        txt_debe.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_debeKeyPressed(evt);
            }
        });

        jLabel17.setText("Haber:");

        txt_haber.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_haber.setText("0.00");
        txt_haber.setEnabled(false);
        txt_haber.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_haberKeyPressed(evt);
            }
        });

        jLabel2.setText("Glosa Documento:");

        txt_glosa.setEnabled(false);
        txt_glosa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_glosaKeyPressed(evt);
            }
        });

        jLabel8.setText("Tipo Documento:");

        cbx_tipo_doc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "FACTURA", "BOLETA" }));
        cbx_tipo_doc.setEnabled(false);
        cbx_tipo_doc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbx_tipo_docKeyPressed(evt);
            }
        });

        jLabel9.setText("Serie - Numero");

        txt_serie.setEnabled(false);
        txt_serie.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_serieKeyPressed(evt);
            }
        });

        txt_numero.setEnabled(false);
        txt_numero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_numeroKeyPressed(evt);
            }
        });

        jLabel10.setText("Doc. Emisor:");

        txt_doc_emisor.setEnabled(false);
        txt_doc_emisor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_doc_emisorKeyPressed(evt);
            }
        });

        try {
            txt_fecha.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txt_fecha.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_fecha.setText("02/03/2019");
        txt_fecha.setEnabled(false);
        txt_fecha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_fechaKeyPressed(evt);
            }
        });

        jLabel5.setText("Fecha Comprobante:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbx_tipo_doc, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txt_serie, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_numero, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txt_doc_emisor, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(57, 57, 57))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_haber)
                            .addComponent(txt_debe)
                            .addComponent(txt_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txt_cod_cuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_nom_cuenta)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txt_glosa)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_cod_cuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_nom_cuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_doc_emisor, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbx_tipo_doc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_serie, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_numero, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(4, 4, 4)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_debe, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_haber, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_glosa, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jToolBar2.setFloatable(false);
        jToolBar2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/add.png"))); // NOI18N
        jButton2.setText("Añadir");
        jButton2.setFocusable(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setMaximumSize(new java.awt.Dimension(50, 41));
        jButton2.setMinimumSize(new java.awt.Dimension(50, 41));
        jButton2.setPreferredSize(new java.awt.Dimension(50, 41));
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar2.add(jButton2);

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/application_edit.png"))); // NOI18N
        jButton4.setText("Modificar");
        jButton4.setFocusable(false);
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setMaximumSize(new java.awt.Dimension(50, 41));
        jButton4.setMinimumSize(new java.awt.Dimension(50, 41));
        jButton4.setPreferredSize(new java.awt.Dimension(50, 41));
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar2.add(jButton4);

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/cross.png"))); // NOI18N
        jButton5.setText("Eliminar");
        jButton5.setFocusable(false);
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton5.setMaximumSize(new java.awt.Dimension(50, 41));
        jButton5.setMinimumSize(new java.awt.Dimension(50, 41));
        jButton5.setPreferredSize(new java.awt.Dimension(50, 41));
        jButton5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar2.add(jButton5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 664, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jToolBar2, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_periodoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_periodoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txt_periodo.getText().length() == 6) {
                cbx_tipo.setEnabled(true);
                cbx_tipo.requestFocus();
            }
        }
    }//GEN-LAST:event_txt_periodoKeyPressed

    private void cbx_tipoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbx_tipoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cbx_libro.setEnabled(true);
            cbx_libro.requestFocus();
        }
    }//GEN-LAST:event_cbx_tipoKeyPressed

    private void cbx_libroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbx_libroKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cbx_moneda.setEnabled(true);
            cbx_moneda.requestFocus();
        }
    }//GEN-LAST:event_cbx_libroKeyPressed

    private void cbx_monedaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbx_monedaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txt_cod_cuenta.setEnabled(true);
            txt_cod_cuenta.requestFocus();
        }
    }//GEN-LAST:event_cbx_monedaKeyPressed

    private void txt_cod_cuentaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cod_cuentaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txt_cod_cuenta.getText().length() > 1) {
                //buscar cuenta contable
                String texto = txt_cod_cuenta.getText().trim();
                c_cuentas.setId_cuenta(texto);
                if (c_cuentas.obtener_datos()) {
                    txt_nom_cuenta.setText(c_cuentas.getNombre());

                    txt_doc_emisor.setEnabled(true);
                    txt_doc_emisor.requestFocus();
                } else {
                    JOptionPane.showMessageDialog(null, "ESTA CUENTA NO EXISTE");
                    txt_cod_cuenta.setEnabled(true);
                    txt_cod_cuenta.selectAll();
                    txt_cod_cuenta.requestFocus();
                }
            }
        }
    }//GEN-LAST:event_txt_cod_cuentaKeyPressed

    private void txt_doc_emisorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_doc_emisorKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txt_doc_emisor.getText().length() > 0) {
                cbx_tipo_doc.setEnabled(true);
                cbx_tipo_doc.requestFocus();
            }
        }
    }//GEN-LAST:event_txt_doc_emisorKeyPressed

    private void cbx_tipo_docKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbx_tipo_docKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txt_serie.setEnabled(true);
            txt_serie.requestFocus();
        }
    }//GEN-LAST:event_cbx_tipo_docKeyPressed

    private void txt_serieKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_serieKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txt_serie.getText().length() > 0) {
                txt_numero.setEnabled(true);
                txt_numero.requestFocus();
            }
        }
    }//GEN-LAST:event_txt_serieKeyPressed

    private void txt_numeroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_numeroKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txt_numero.getText().length() > 0) {
//                cbx_costo.setEnabled(true);
//                cbx_costo.requestFocus();
            }
        }
    }//GEN-LAST:event_txt_numeroKeyPressed

    private void txt_fechaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_fechaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txt_fecha.getText().length() == 10) {
                txt_debe.setEnabled(true);
                txt_debe.selectAll();
                txt_debe.requestFocus();
            }
        }
    }//GEN-LAST:event_txt_fechaKeyPressed

    private void txt_debeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_debeKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String texto = txt_debe.getText();
            if (c_varios.esDecimal(texto)) {
                //si es decimal tiene q ser mayor a 0 sino pasamos al haber
                double debe = Double.parseDouble(texto);
                if (debe > 0) {
                    txt_glosa.setEnabled(true);
                    txt_glosa.requestFocus();
                } else {
                    txt_haber.setEnabled(true);
                    txt_haber.selectAll();
                    txt_haber.requestFocus();
                }
            } else {
                JOptionPane.showMessageDialog(null, "POR FAVOR INGRESE UN NUMERO CORRECTO");
                txt_debe.selectAll();
                txt_debe.requestFocus();
            }

        }
    }//GEN-LAST:event_txt_debeKeyPressed

    private void txt_haberKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_haberKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String texto = txt_haber.getText();
            if (c_varios.esDecimal(texto)) {
                double haber = Double.parseDouble(texto);
                if (haber > 0) {
                    txt_glosa.setEnabled(true);
                    txt_glosa.requestFocus();
                } else {
                    txt_haber.setEnabled(true);
                    txt_haber.selectAll();
                    txt_haber.requestFocus();
                }
            } else {
                JOptionPane.showMessageDialog(null, "POR FAVOR INGRESE UN NUMERO CORRECTO");
                txt_haber.selectAll();
                txt_haber.requestFocus();
            }

        }
    }//GEN-LAST:event_txt_haberKeyPressed

    private void txt_glosaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_glosaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Object fila[] = new Object[15];
            int tipo_asiento = cbx_tipo.getSelectedIndex();
            String masiento = "";
            if (tipo_asiento == 0) {
                masiento = "A";
            }
            if (tipo_asiento == 1) {
                masiento = "M";
            }
            if (tipo_asiento == 2) {
                masiento = "C";
            }

            o_combobox o_libros = (o_combobox) cbx_libro.getSelectedItem();

            fila[0] = masiento + o_libros.getId();
            fila[1] = t_cuentas_venta.getRowCount() + 1;
            fila[2] = txt_cod_cuenta.getText();
            fila[3] = "";  //cbx_costo.getSelectedIndex();
            fila[4] = txt_doc_emisor.getText();
            o_combobox o_tido = (o_combobox) cbx_tipo_doc.getSelectedItem();
            c_tido.setId_tido(o_tido.getId());
            c_tido.obtener_datos();
            fila[5] = c_tido.getAbreviado() + " | " + txt_serie.getText() + " - " + txt_numero.getText();
            fila[6] = txt_fecha.getText();
            fila[7] = txt_fecha.getText();
            o_combobox o_moneda = (o_combobox) cbx_moneda.getSelectedItem();
            c_moneda.setId_moneda(o_moneda.getId());
            c_moneda.obtener_datos();
            fila[8] = c_moneda.getAbreviado();
            fila[9] = c_varios.formato_numero(Double.parseDouble(txt_debe.getText()));
            fila[10] = c_varios.formato_numero(Double.parseDouble(txt_haber.getText()));
            fila[11] = txt_glosa.getText().toUpperCase();
            fila[12] = o_tido.getId();
            fila[13] = txt_serie.getText();
            fila[14] = txt_numero.getText();
            m_caja.addRow(fila);

            calcular_totales();
            //limpiar cajas
            txt_cod_cuenta.setText("");
            txt_nom_cuenta.setText("");
            txt_doc_emisor.setText("");
            txt_serie.setText("");
            txt_numero.setText("");
            txt_fecha.setText(c_varios.formato_fecha_vista(c_varios.getFechaActual()));
            txt_debe.setText("0.00");
            txt_haber.setText("0.00");
            //txt_glosa.setText("");
            txt_cod_cuenta.requestFocus();
        }
    }//GEN-LAST:event_txt_glosaKeyPressed

    private void txt_cod_cuentaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cod_cuentaKeyTyped
        c_varios.solo_numeros(evt);
        c_varios.limitar_caracteres(evt, txt_cod_cuenta, 5);
    }//GEN-LAST:event_txt_cod_cuentaKeyTyped

    private void txt_fecha1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_fecha1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_fecha1KeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frm_reg_diario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_reg_diario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_reg_diario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_reg_diario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                frm_reg_diario dialog = new frm_reg_diario(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbx_libro;
    private javax.swing.JComboBox<String> cbx_moneda;
    private javax.swing.JComboBox<String> cbx_tipo;
    private javax.swing.JComboBox<String> cbx_tipo_doc;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JTable t_cuentas_venta;
    private javax.swing.JTextField txt_cod_cuenta;
    private javax.swing.JTextField txt_debe;
    private javax.swing.JTextField txt_doc_emisor;
    private javax.swing.JFormattedTextField txt_fecha;
    private javax.swing.JFormattedTextField txt_fecha1;
    private javax.swing.JTextField txt_glosa;
    private javax.swing.JTextField txt_haber;
    private javax.swing.JTextField txt_nom_cuenta;
    private javax.swing.JTextField txt_numero;
    private javax.swing.JTextField txt_periodo;
    private javax.swing.JTextField txt_serie;
    private javax.swing.JTextField txt_tot_debe;
    private javax.swing.JTextField txt_tot_diferencia;
    private javax.swing.JTextField txt_tot_haber;
    // End of variables declaration//GEN-END:variables
}
