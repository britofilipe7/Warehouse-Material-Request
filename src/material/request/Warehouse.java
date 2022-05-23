package material.request;


import java.awt.HeadlessException;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;  
import java.util.Date;  
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.*;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Filipe Brito
 */
public class Warehouse extends javax.swing.JFrame {
    
   Connection conn = null;
   PreparedStatement pst = null;
   ResultSet rs = null;
   DefaultTableModel model = new DefaultTableModel();

    /**
     * Creates new form LQ_Request_Armazem
     */
    public Warehouse() {
        initComponents();
        Object col[] = {"ID", "Work Center", "Material", "Date"};
        
        model.setColumnIdentifiers(col);
        jTable1.setModel(model);
        conn = Admin.ConnectDB();
        
        jTable1.getColumnModel().getColumn(0).setMinWidth(0);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(0).setWidth(0);
        
        updateTable();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    
    public static Connection ConnectDB(){
        try{
           Class.forName("org.sqlite.JDBC");
           Connection conn = DriverManager.getConnection("jdbc:sqlite:Material_Request_DB.db");
           return conn;
        }
        
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }

    public void updateTable(){
        Timer timer = new Timer();
        int begin = 0;
        int timeInterval = 60000;
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
        
            public void run() {
                int rowscountbefore = jTable1.getRowCount();
                DefaultTableModel mod = (DefaultTableModel) jTable1.getModel();
                mod.setRowCount(0);

                conn = Admin.ConnectDB();
                if (conn !=null){
                    String sql ="SELECT ID, workcenter, material, date FROM material WHERE pleased IS NULL";

                    try{
                        pst = conn.prepareStatement(sql);
                        rs = pst.executeQuery();
                        Object[] columnData = new Object[4];

                        while (rs.next()){
                            columnData[0] = rs.getString("ID");
                            columnData[1] = rs.getString("workcenter");
                            columnData[2] = rs.getString("material");
                            columnData[3] = rs.getString("date");
                            
                            model.addRow(columnData);
                        }
                    }catch(Exception e){
                        JOptionPane.showMessageDialog(null, e);
                    } 
                }

                int rowscountafter = jTable1.getRowCount();
                if (rowscountafter > rowscountbefore && rowscountbefore!=0) {
                    try{
                        //Obtain only one instance of the SystemTray object
                        SystemTray tray = SystemTray.getSystemTray();

                        // If you want to create an icon in the system tray to preview
                        Image image = Toolkit.getDefaultToolkit().createImage("some-icon.png");
                        //Alternative (if the icon is on the classpath):
                        //Image image = Toolkit.getDefaultToolkit().createImage(getClass().getResource("icon.png"));

                        TrayIcon trayIcon = new TrayIcon(image, "Java AWT Tray Demo");
                        //Let the system resize the image if needed
                        trayIcon.setImageAutoSize(true);
                        //Set tooltip text for the tray icon
                        trayIcon.setToolTip("System tray icon demo");
                        tray.add(trayIcon);

                        // Display info notification:
                        trayIcon.displayMessage("Requisição de Matéria-Prima", "Novo(s) Pedido(s)!", TrayIcon.MessageType.INFO);
                        // Error:
                        // trayIcon.displayMessage("Hello, World", "Java Notification Demo", MessageType.ERROR);
                        // Warning:
                        // trayIcon.displayMessage("Hello, World", "Java Notification Demo", MessageType.WARNING);
                    }catch(Exception ex){
                        System.err.print(ex);
                    }
                }
            }
        }, begin, timeInterval);
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jlblAbastecer = new javax.swing.JLabel();
        jbtnAbastecer = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jbtnHistory = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Solicitação de Matéria Prima - Armazém");
        setResizable(false);

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jlblAbastecer.setText("Observações:");

        jbtnAbastecer.setText("Abastecer");
        jbtnAbastecer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnAbastecerActionPerformed(evt);
            }
        });

        jLabel2.setText("Armazém");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jlblAbastecer)
                        .addGap(27, 27, 27)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(76, 76, 76))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(125, 125, 125)))
                .addComponent(jbtnAbastecer)
                .addGap(37, 37, 37))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblAbastecer, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnAbastecer)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Centro de Trabalho", "MateriaPrima", "Data"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jbtnHistory.setText("Histórico");
        jbtnHistory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnHistoryActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(138, 138, 138)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 780, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jbtnHistory)
                        .addGap(376, 376, 376))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jbtnHistory)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnAbastecerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnAbastecerActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        if (jTable1.getSelectedRow() == -1){
            if (jTable1.getRowCount() == 0){
                JOptionPane.showMessageDialog(null, "No data!", "Material Request", JOptionPane.OK_OPTION);
            }else{
                JOptionPane.showMessageDialog(null, "Select a row!", "Material Request", JOptionPane.OK_OPTION);
            }
        }else{
            int row = jTable1.getSelectedRow();
            String ID = model.getValueAt(row, 0).toString();
            
            Date date = new Date();  
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yy HH:mm:ss");  
            String strDate = formatter.format(date);  
            DefaultTableModel mod = (DefaultTableModel) jTable1.getModel();
            mod.setRowCount(0);
        
            try {
                Statement st = conn.createStatement();
                String sql1 ="UPDATE material SET pleased=?, dateofsupply=?, observations=? WHERE ID=?";
                pst = conn.prepareStatement(sql1);
                pst.setString(1, "X");
                pst.setString(2, strDate);
                pst.setString(3, jTextField1.getText());
                pst.setString(4, ID);

                pst.execute();
                rs.close();
                pst.close();
            }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
            updateTable();
            DefaultTableModel iModel = (DefaultTableModel) jTable1.getModel();
            jTextField1.setText("");
        }        

    }//GEN-LAST:event_jbtnAbastecerActionPerformed

    private void jbtnHistoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnHistoryActionPerformed
        new History().setVisible(true);
    }//GEN-LAST:event_jbtnHistoryActionPerformed

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
            java.util.logging.Logger.getLogger(Warehouse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Warehouse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Warehouse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Warehouse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Warehouse().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JButton jbtnAbastecer;
    private javax.swing.JButton jbtnHistory;
    private javax.swing.JLabel jlblAbastecer;
    // End of variables declaration//GEN-END:variables
}
