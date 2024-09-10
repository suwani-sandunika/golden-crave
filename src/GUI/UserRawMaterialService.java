/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import Model.MySQL;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMaterialDarkerContrastIJTheme;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author vihan
 */
public class UserRawMaterialService extends javax.swing.JFrame {

    private static int grnItem_id;
    private static int grntable_id = 0;
    private static String total;
    private static String supplierName;
    private static String userid;

    /**
     * Creates new form Order_Service
     */
    public UserRawMaterialService(String userid) {
        initComponents();
        this.userid = userid;
        calculate();
        loadSupplier();
        loadGRN();
        loadGRNItem();
        jButton4.setEnabled(false);
        

    }

    private void loadSupplier() {

        try {

            ResultSet resultset = MySQL.execute("SELECT * FROM `supplier`");

            Vector<String> vector = new Vector<>();
            vector.add("Select");

            while (resultset.next()) {
                vector.add(resultset.getString("mobile"));

            }

            jComboBox2.setModel(new DefaultComboBoxModel<>(vector));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loadGRN() {

        try {

            DefaultTableModel model = (DefaultTableModel) jTable4.getModel();
            model.setRowCount(0);

            ResultSet resultset = MySQL.execute("SELECT * FROM `grn` INNER JOIN `supplier` ON `supplier`.`mobile`=`grn`.`supplier_mobile` WHERE `total` != '0.00'");
            while (resultset.next()) {

                Vector vector = new Vector();
                vector.add(resultset.getString("id"));
                vector.add(resultset.getString("total"));
                vector.add(resultset.getString("supplier.mobile"));
                vector.add(resultset.getString("date"));

                model.addRow(vector);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loadGRNItem() {

        try {

            ResultSet resultset1;
            if (grntable_id == 0) {
                //if grn table is not selected
                 resultset1 = MySQL.execute("SELECT * FROM `grn_item` INNER JOIN `grn` ON `grn`.`id`=`grn_item`.`grn_id`"); 
            }else{
                //if grn table is selected
              resultset1 = MySQL.execute("SELECT * FROM `grn_item` INNER JOIN `grn` ON `grn`.`id`=`grn_item`.`grn_id` WHERE `grn_item`.`grn_id`='"+grntable_id+"'");
            
            }
            DefaultTableModel model = (DefaultTableModel) jTable3.getModel();
            model.setRowCount(0);

            ResultSet resultset = resultset1;
            while (resultset.next()) {

                Vector vector = new Vector();
                vector.add(resultset.getString("grn_item.id"));
                vector.add(resultset.getString("grn.id"));
                vector.add(resultset.getString("item_name"));
                vector.add(resultset.getString("buying_price"));
                vector.add(resultset.getString("qty"));

                model.addRow(vector);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void calculate() {

        try {

            ResultSet resultset = MySQL.execute("SELECT * FROM `grn`");

            
            String grnID = "";

            while (resultset.next()) { //grn table loop
                int total1 = 0;
                grnID = resultset.getString("grn.id");

                ResultSet resultset2 = MySQL.execute("SELECT * FROM `grn_item` WHERE `grn_id`='" + grnID + "'");
                while (resultset2.next()) { //grn item table loop

                    String qty1 = resultset2.getString("qty");
                    Double price1 = resultset2.getDouble("buying_price");

                    int total2 = Integer.parseInt(qty1) * (int) Math.round(price1);

                    total1 = total1 + total2;
                }

                MySQL.execute("UPDATE `grn` SET `total`='" + String.valueOf(total1) + "' WHERE `grn`.`id`='" + grnID + "'");

            }

            loadGRN();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void reset() {

        jComboBox2.setEnabled(true);
        jTextField2.setEditable(true);
        jTextField3.setEditable(true);
        jFormattedTextField1.setEnabled(true);
        jFormattedTextField2.setEnabled(true);
        jButton2.setEnabled(true);

        jTable3.clearSelection();
        jTable4.clearSelection();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jComboBox2 = new javax.swing.JComboBox<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        jFormattedTextField2 = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel4.setFont(new java.awt.Font("Microsoft YaHei", 1, 24)); // NOI18N
        jLabel4.setText("             Raw Material Service");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Item name:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Supplier:");

        jTable3.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Item Id", "GRN_ID", "Item Name", "Buying Price", "Quantity"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable3);

        jButton1.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("<Back");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Add GRN");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Delete GRN Item");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("View GRN Report");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jTable4.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Id", "Total", "Supplier", "Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable4MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTable4);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Qty:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Buying Price:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("GRN");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("GRN_id");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("GRN Item");

        jButton5.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Clear Selected");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(108, 108, 108)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jFormattedTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 595, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel9)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 543, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 595, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel7))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(248, 248, 248)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jFormattedTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                .addGap(2, 2, 2)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        String supplier = String.valueOf(jComboBox2.getSelectedItem());
        String itemName = jTextField2.getText();
        String grnId = jTextField3.getText();
        String qty = String.valueOf(jFormattedTextField1.getText());
        String price = String.valueOf(jFormattedTextField2.getText());

        if (itemName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Select a Item name", "Warning", JOptionPane.WARNING_MESSAGE);

        } else if (qty.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Select a Quantity", "Warning", JOptionPane.WARNING_MESSAGE);

        } else if (price.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please add a Price ", "Warning", JOptionPane.WARNING_MESSAGE);

        } else if (supplier.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please add a Supplier ", "Warning", JOptionPane.WARNING_MESSAGE);

        } else if (grnId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please add a GRN Number ", "Warning", JOptionPane.WARNING_MESSAGE);

        } else {

            try {
                //current date
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                LocalDateTime now = LocalDateTime.now();
                String date = dtf.format(now);

                //total calculation
                int total1 = Integer.parseInt(qty) * Integer.parseInt(price);

                ResultSet resultset2 = MySQL.execute("SELECT * FROM `grn` WHERE `id`='" + grnId + "'");

                if (resultset2.next()) {
                    //if have a grn add to grn_item
                    MySQL.execute("INSERT INTO `grn_item`(`grn_id`,`buying_price`,`qty`,`item_name`) VALUES('" + resultset2.getString("id") + "','" + price + "','" + qty + "','" + itemName + "')");
                    JOptionPane.showMessageDialog(this, "GRN Item Added", "Warning", JOptionPane.WARNING_MESSAGE);
                    calculate();
                    loadGRN();
                    loadGRNItem();

                } else {
                    //when dont have a Grn. add a new grn and grn_item
                    MySQL.execute("INSERT INTO `grn`(`id`,`date`,`total`,`supplier_mobile`) VALUES('" + grnId + "','" + date + "','" + total1 + "','" + supplier + "')");
                    JOptionPane.showMessageDialog(this, "GRN Updated", "Warning", JOptionPane.WARNING_MESSAGE);
                    MySQL.execute("INSERT INTO `grn_item`(`grn_id`,`buying_price`,`qty`,`item_name`) VALUES('" + grnId + "','" + price + "','" + qty + "','" + itemName + "')");
                    JOptionPane.showMessageDialog(this, "GRN Item Added", "Warning", JOptionPane.WARNING_MESSAGE);
                    calculate();
                    loadGRN();
                    loadGRNItem();

                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        try {

            MySQL.execute("DELETE FROM `grn_item` WHERE `id`='" + grnItem_id + "'");
            calculate();
            loadGRN();
            loadGRNItem();
            reset();

            JOptionPane.showMessageDialog(this, "GRN item Deleted!", "Warning", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked

        if (evt.getClickCount() == 2) {
            int row = jTable3.getSelectedRow();

            jComboBox2.setEnabled(false);
            jTextField2.setEditable(false);
            jTextField3.setEditable(false);
            jFormattedTextField1.setEnabled(false);
            jFormattedTextField2.setEnabled(false);
            jButton2.setEnabled(false);

            String id = String.valueOf(jTable3.getValueAt(row, 0));
            grnItem_id = Integer.parseInt(id);

        }
    }//GEN-LAST:event_jTable3MouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
    
        try {

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            LocalDateTime now = LocalDateTime.now();
//                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            HashMap<String, Object> map = new HashMap<>();
            map.put("supplier", supplierName);
            map.put("reportDate", dtf.format(now));
            map.put("total", total);

//            Date date = new Date();
//            Date d = sdf.parse("21/12/2012");
            String path = "src//Resources//UserRawMaterialService.jasper";

            JRDataSource datasource = new JRTableModelDataSource(jTable3.getModel());

            JasperPrint jasperprint = JasperFillManager.fillReport(path, map, datasource);
            JasperViewer.viewReport(jasperprint, false);

        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTable4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable4MouseClicked
       if (evt.getClickCount() == 2) {
            int row = jTable4.getSelectedRow();

            jComboBox2.setEnabled(false);
            jTextField2.setEditable(false);
            jTextField3.setEditable(false);
            jFormattedTextField1.setEnabled(false);
            jFormattedTextField2.setEnabled(false);
            jButton2.setEnabled(false);
            jButton4.setEnabled(true);

            String id = String.valueOf(jTable4.getValueAt(row, 0));
            grntable_id = Integer.parseInt(id);
            
             total = String.valueOf(jTable4.getValueAt(row, 1));
             supplierName = String.valueOf(jTable4.getValueAt(row, 2));
//            String total1 = String.valueOf(jTable4.getValueAt(row, 1));
                     
           // total = Integer.parseInt(total1);           
            //total = Integer.parseInt(total1);
            
            loadGRNItem();
            
            

        }
    }//GEN-LAST:event_jTable4MouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        reset();
        loadGRNItem();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        UserDashboard d = new UserDashboard(userid);
        d.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        FlatMaterialDarkerContrastIJTheme.setup();

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               // new UserRawMaterialService().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JFormattedTextField jFormattedTextField2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}
