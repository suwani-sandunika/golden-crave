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
public class CustomerOrderService extends javax.swing.JFrame {
private static String customer_id;
    HashMap<String, String> discountMap = new HashMap<>();

    private static int order_id;
    private static int editQty;

    /**
     * Creates new form Order_Service
     */
    public CustomerOrderService(String customer_id) {
        initComponents();
        this.customer_id = customer_id;
        loadStock();
        loadDiscount();
        loadOrder();

    }

    private void loadStock() {

        try {
            //table
            DefaultTableModel model = (DefaultTableModel) jTable4.getModel();
            model.setRowCount(0);
            //combo box 
            Vector<String> vector2 = new Vector<>();
            vector2.add("Select");

            ResultSet resultset = MySQL.execute("SELECT * FROM `stock` INNER JOIN `product` ON `product`.`id`= `stock`.`product_id` INNER JOIN `size` ON `size`.`id`=`stock`.`size_id` ");
            while (resultset.next()) {
                //quality check
                if (Integer.parseInt(resultset.getString("quality_id")) > 7) {

                    Vector vector = new Vector();
                    vector.add(resultset.getString("id"));
                    vector.add(resultset.getString("product.name"));
                    vector.add(resultset.getString("price"));
                    vector.add(resultset.getString("avb_qty"));
                    vector.add(resultset.getString("size.name"));

                    model.addRow(vector);

                    //combo box
                    vector2.add(resultset.getString("id"));

                }
            }

            jComboBox3.setModel(new DefaultComboBoxModel<>(vector2));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loadDiscount() {

        try {

            ResultSet resultset = MySQL.execute("SELECT * FROM `discount`");

            Vector<String> vector = new Vector<>();
            vector.add("Select");

            while (resultset.next()) {
                vector.add(resultset.getString("discount_size"));
                discountMap.put(resultset.getString("discount_size"), resultset.getString("id"));

            }

            jComboBox4.setModel(new DefaultComboBoxModel<>(vector));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loadOrder() {

        try {

            DefaultTableModel model = (DefaultTableModel) jTable3.getModel();
            model.setRowCount(0);

            ResultSet resultset = MySQL.execute("SELECT * FROM `order` INNER JOIN `order_status` ON `order_status`.`id`=`order`.`order_status_id` WHERE `customer_id`='"+customer_id+"'");
            while (resultset.next()) {

                Vector vector = new Vector();
                vector.add(resultset.getString("id"));
                vector.add(resultset.getString("stock_id"));
                vector.add(resultset.getString("total"));
                vector.add(resultset.getString("order_status.type"));
                vector.add(resultset.getString("qty"));
                vector.add(resultset.getString("date"));

                model.addRow(vector);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void reset() {

        jComboBox3.setEnabled(true);
        jComboBox4.setEnabled(true);
        jButton1.setEnabled(true);
        jTable1.clearSelection();
        jFormattedTextField1.setEditable(true);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jComboBox4 = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTable2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel4.setFont(new java.awt.Font("Microsoft YaHei", 1, 24)); // NOI18N
        jLabel4.setText("Order Service");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Stock :");

        jTable3.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Order Id", "Stock", "Total", "Status", "Quantity", "Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
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
        jButton1.setText("Create Order");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Delete Order");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Generate Invoice");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("<Back");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText("Available Stock:");

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Stock_id", "Product", "Price", "Qty", "Size"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
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

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setText("Discount:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setText("Quantity");

        jFormattedTextField1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane3)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(204, 204, 204))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 699, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGap(6, 6, 6)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(jComboBox4, 0, 184, Short.MAX_VALUE)
                                                        .addComponent(jFormattedTextField1)))
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))))))))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(269, 269, 269)
                .addComponent(jLabel4)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jFormattedTextField1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTable4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable4MouseClicked

//        if (evt.getClickCount() == 2) {
//            int row = jTable1.getSelectedRow();
//
//            jComboBox1.setEnabled(false);
//            jComboBox2.setEnabled(false);
//            jFormattedTextField1.setEnabled(false);
//            jButton6.setEnabled(true);
//
//            String product = String.valueOf(jTable1.getValueAt(row, 1));
//            jComboBox1.setSelectedItem(product);
//
//            String size = String.valueOf(jTable1.getValueAt(row, 4));
//            jComboBox2.setSelectedItem(size);
//
//            String price = String.valueOf(jTable1.getValueAt(row, 2));
//            jFormattedTextField1.setText(price);
//
//            String qty = String.valueOf(jTable1.getValueAt(row, 3));
//            jFormattedTextField2.setText(qty);
//
//            String id = String.valueOf(jTable1.getValueAt(row, 0));
//            stock_id = Integer.parseInt(id);
//
//        }
    }//GEN-LAST:event_jTable4MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        String stock = String.valueOf(jComboBox3.getSelectedItem());
        String discount = String.valueOf(jComboBox4.getSelectedItem());
        String qty = jFormattedTextField1.getText();

        if (stock.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please Select a Stock", "Warning", JOptionPane.WARNING_MESSAGE);

        } else if (discount.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please Select a Discount", "Warning", JOptionPane.WARNING_MESSAGE);

        } else if (qty.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please add a Quantity ", "Warning", JOptionPane.WARNING_MESSAGE);

        } else {

            try {
                //current date
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                LocalDateTime now = LocalDateTime.now();
                String date = dtf.format(now);

                ResultSet resultset = MySQL.execute("SELECT * FROM `stock` WHERE `id`='" + stock + "'");

                if (resultset.next()) {
                    if (Integer.parseInt(resultset.getString("avb_qty")) > Integer.parseInt(qty)) {

                        double price = Double.parseDouble(resultset.getString("price"));
                        String old_qty = resultset.getString("avb_qty");
                        Integer oqty = Integer.parseInt(old_qty);
                        Integer nqty = Integer.parseInt(qty);
                        int newQty = oqty - nqty;

                        //total calculation
                        int total = Integer.parseInt(qty) * (int) Math.round(price);//Double price into integer
                        //set total to lable

                        MySQL.execute("UPDATE `stock` SET `avb_qty`='" + newQty + "' WHERE `id`='" + resultset.getString("id") + "'");

                        MySQL.execute("INSERT INTO `order`(`total`,`qty`,`customer_id`,`order_status_id`,`discount_id`,`date`,`stock_id`) VALUES('" + String.valueOf(total) + "','" + qty + "','"+customer_id+"','1','" + discountMap.get(discount) + "','" + date + "','" + stock + "')");

                        loadStock();
                        loadOrder();
                        JOptionPane.showMessageDialog(this, "Stock Added", "Warning", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "Insufficent Stock!", "Warning", JOptionPane.INFORMATION_MESSAGE);
                    }

                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked

        if (evt.getClickCount() == 2) {
            int row = jTable3.getSelectedRow();

            jComboBox3.setEnabled(false);
            jComboBox4.setEnabled(false);
            jFormattedTextField1.setEnabled(false);
            jButton1.setEnabled(false);

            String stock = String.valueOf(jTable3.getValueAt(row, 1));
            jComboBox3.setSelectedItem(stock);

            String qty = String.valueOf(jTable3.getValueAt(row, 4));
            jFormattedTextField1.setText(qty);
            editQty = Integer.parseInt(qty);

            String id = String.valueOf(jTable3.getValueAt(row, 0));
            order_id = Integer.parseInt(id);

        }
    }//GEN-LAST:event_jTable3MouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        try {

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            LocalDateTime now = LocalDateTime.now();
//                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            HashMap<String, Object> map = new HashMap<>();
            map.put("user", customer_id);
            map.put("reportDate", dtf.format(now));

//            Date date = new Date();
//            Date d = sdf.parse("21/12/2012");
            String path = "src//Resources//CustomerOrdrerService.jasper";

            JRDataSource datasource = new JRTableModelDataSource(jTable3.getModel());

            JasperPrint jasperprint = JasperFillManager.fillReport(path, map, datasource);
            JasperViewer.viewReport(jasperprint, false);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        String qty1 = jFormattedTextField1.getText();
        int qty = editQty;

        try {
            ResultSet resultset = MySQL.execute("SELECT * FROM `order` INNER JOIN `stock` ON `stock`.`id`=`order`.`stock_id` WHERE `order`.`id`='" + order_id + "'");

            if (resultset.next()) {

               

                String old_qty = resultset.getString("stock.avb_qty");
                Integer oqty = Integer.parseInt(old_qty);
                Integer nqty = Integer.parseInt(qty1);
                int newQty = oqty + nqty;

                MySQL.execute("UPDATE `stock` SET `avb_qty`='" + newQty + "' WHERE `id`='" + resultset.getString("stock_id") + "'");

                MySQL.execute("DELETE FROM `order` WHERE `id`='" + order_id + "'");

                JOptionPane.showMessageDialog(this, "Order Deleted!", "Warning", JOptionPane.INFORMATION_MESSAGE);
                loadStock();
                loadOrder();
                reset();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        CustomerDashboard dash = new CustomerDashboard(this.customer_id);
        dash.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        FlatMaterialDarkerContrastIJTheme.setup();

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
             //   new CustomerOrderService().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    // End of variables declaration//GEN-END:variables
}
