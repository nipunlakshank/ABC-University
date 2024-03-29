/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.abc.gui.user.admin;

import com.abc.exceptions.DataInsertException;
import static com.abc.gui.Splash.APP;
import com.abc.model.dao.LecturerSubjectDAO;
import com.abc.model.dao.LecturerViewDAO;
import com.abc.model.dto.LecturerViewDTO;
import com.abc.service.TableEditor;
import java.awt.Color;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nipun
 */
public class AssignLecturerToSubject extends javax.swing.JDialog {

    
    private final LecturerViewDAO lecViewDAO;
    
    /**
     * Creates new form AddLecturerToSubject
     * @param parent
     * @param data
     * @param modal
     */
    public AssignLecturerToSubject(java.awt.Frame parent, Map<String, String> data, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(parent);
        lecViewDAO = new LecturerViewDAO();
        formatTables();
        setLabels(data);
        loadTable();
    }
    
    private void setLabels(Map<String, String> data){
        lblDegId.setText(data.get("degId"));
        lblDegTitle.setText(data.get("degTitle"));
        lblSubId.setText(data.get("subId"));
        lblSubCode.setText(data.get("subCode"));
        lblSubTitle.setText(data.get("subTitle"));
    }
    
    private void clearFields(){
        txtSearchName.setText("");
        txtSearchMobile.setText("");
        txtSearchEmail.setText("");
        filter();
    }
    
    private void addLecturer(){
        int rows = table.getSelectedRowCount();
        if(rows != 1){
            return;
        }
        int row = table.getSelectedRow();
        int lecId = (int) table.getValueAt(row, 0);
        int subId = Integer.parseInt(lblSubId.getText());
        
        LecturerSubjectDAO lecSubDAO = new LecturerSubjectDAO();
        try {
            if(lecSubDAO.isExist(lecId, subId)){
                APP.showAlert(this, "Selected lecturer is already assigned to this subject.", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            int id = lecSubDAO.save(lecId, subId);
            if(id < 1){
                throw new DataInsertException();
            }
            APP.showAlert(this, "Selected lecturer has successfully assigned to this subject.", "Success", JOptionPane.INFORMATION_MESSAGE);
            filter();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void loadTable() {
        try {
            DefaultTableModel dtm = (DefaultTableModel) table.getModel();
            dtm.setRowCount(0);
            List<LecturerViewDTO> views = lecViewDAO.getViews();

            for (LecturerViewDTO view : views) {
                dtm.addRow(new Object[]{view.getId(), view.getName(), view.getMobile(), view.getEmail()});
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void filter() {
        try {
            DefaultTableModel dtm = (DefaultTableModel) table.getModel();
            dtm.setRowCount(0);
            List<LecturerViewDTO> views = lecViewDAO.filter(txtSearchName.getText(), txtSearchMobile.getText(), txtSearchEmail.getText());

            for (LecturerViewDTO view : views) {
                dtm.addRow(new Object[]{view.getId(), view.getName(), view.getMobile(), view.getEmail()});
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void formatTables() {
        TableEditor.setTableHeaders(table, Color.WHITE, new Color(255, 102, 0));

        TableEditor.setColumnRenderer(table, SwingConstants.LEFT, 0);
        TableEditor.setColumnRenderer(table, SwingConstants.CENTER, 1);
        TableEditor.setColumnRenderer(table, SwingConstants.CENTER, 2);
        TableEditor.setColumnRenderer(table, SwingConstants.CENTER, 3);

        TableEditor.setMaxColumnWidth(table, 0, 50);
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
        topPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblDegId = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lblDegTitle = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lblSubId = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        lblSubCode = new javax.swing.JLabel();
        btnClose = new javax.swing.JButton();
        btnConfirm = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        lblSubTitle = new javax.swing.JLabel();
        txtSearchName = new javax.swing.JTextField();
        txtSearchMobile = new javax.swing.JTextField();
        txtSearchEmail = new javax.swing.JTextField();
        btnClear = new javax.swing.JButton();
        midPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setLayout(new java.awt.BorderLayout());

        topPanel.setPreferredSize(new java.awt.Dimension(583, 180));

        jLabel1.setText("Lecturer Name:");

        jLabel3.setText("Lecturer Mobile:");

        jLabel5.setText("Lecturer Email:");

        jLabel9.setText("Degree Id:");

        lblDegId.setText("Not Selected");

        jLabel11.setText("Degree Title:");

        lblDegTitle.setText("Not Selected");

        jLabel13.setText("Subject Id:");

        lblSubId.setText("Not Selected");

        jLabel15.setText("Subject Code:");

        lblSubCode.setText("Not Selected");

        btnClose.setBackground(new java.awt.Color(255, 102, 0));
        btnClose.setFont(new java.awt.Font("Comic Sans MS", 0, 13)); // NOI18N
        btnClose.setForeground(new java.awt.Color(255, 255, 255));
        btnClose.setText("Close");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        btnConfirm.setBackground(new java.awt.Color(0, 153, 153));
        btnConfirm.setFont(new java.awt.Font("Comic Sans MS", 0, 13)); // NOI18N
        btnConfirm.setForeground(new java.awt.Color(255, 255, 255));
        btnConfirm.setText("Confirm");
        btnConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmActionPerformed(evt);
            }
        });

        jLabel17.setText("Subject Title:");

        lblSubTitle.setText("Not Selected");

        txtSearchName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchNameKeyReleased(evt);
            }
        });

        txtSearchMobile.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchMobileKeyReleased(evt);
            }
        });

        txtSearchEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchEmailKeyReleased(evt);
            }
        });

        btnClear.setBackground(new java.awt.Color(51, 51, 51));
        btnClear.setFont(new java.awt.Font("Comic Sans MS", 0, 13)); // NOI18N
        btnClear.setForeground(new java.awt.Color(255, 255, 255));
        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout topPanelLayout = new javax.swing.GroupLayout(topPanel);
        topPanel.setLayout(topPanelLayout);
        topPanelLayout.setHorizontalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(topPanelLayout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtSearchEmail))
                        .addGroup(topPanelLayout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtSearchName, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(topPanelLayout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtSearchMobile)))
                    .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, topPanelLayout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblDegTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                            .addComponent(lblDegId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(topPanelLayout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblSubTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(topPanelLayout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblSubId, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE))
                            .addGroup(topPanelLayout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblSubCode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, topPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnClose)))
                .addContainerGap())
        );

        topPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel1, jLabel3, jLabel5});

        topPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel11, jLabel9});

        topPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel13, jLabel15});

        topPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnClose, btnConfirm});

        topPanelLayout.setVerticalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanelLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel9)
                    .addComponent(lblDegId)
                    .addComponent(jLabel13)
                    .addComponent(lblSubId)
                    .addComponent(txtSearchName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel11)
                    .addComponent(lblDegTitle)
                    .addComponent(jLabel15)
                    .addComponent(lblSubCode)
                    .addComponent(txtSearchMobile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel17)
                    .addComponent(lblSubTitle)
                    .addComponent(txtSearchEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnClose)
                    .addComponent(btnConfirm)
                    .addComponent(btnClear))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jPanel1.add(topPanel, java.awt.BorderLayout.PAGE_START);

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Name", "Mobile", "Email"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class
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
        jScrollPane1.setViewportView(table);

        javax.swing.GroupLayout midPanelLayout = new javax.swing.GroupLayout(midPanel);
        midPanel.setLayout(midPanelLayout);
        midPanelLayout.setHorizontalGroup(
            midPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(midPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1006, Short.MAX_VALUE)
                .addContainerGap())
        );
        midPanelLayout.setVerticalGroup(
            midPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, midPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(midPanel, java.awt.BorderLayout.CENTER);

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
    }// </editor-fold>//GEN-END:initComponents

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCloseActionPerformed

    private void btnConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmActionPerformed
        addLecturer();
    }//GEN-LAST:event_btnConfirmActionPerformed

    private void txtSearchNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchNameKeyReleased
        filter();
    }//GEN-LAST:event_txtSearchNameKeyReleased

    private void txtSearchMobileKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchMobileKeyReleased
        filter();
    }//GEN-LAST:event_txtSearchMobileKeyReleased

    private void txtSearchEmailKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchEmailKeyReleased
        filter();
    }//GEN-LAST:event_txtSearchEmailKeyReleased

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        clearFields();
    }//GEN-LAST:event_btnClearActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnConfirm;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDegId;
    private javax.swing.JLabel lblDegTitle;
    private javax.swing.JLabel lblSubCode;
    private javax.swing.JLabel lblSubId;
    private javax.swing.JLabel lblSubTitle;
    private javax.swing.JPanel midPanel;
    private javax.swing.JTable table;
    private javax.swing.JPanel topPanel;
    private javax.swing.JTextField txtSearchEmail;
    private javax.swing.JTextField txtSearchMobile;
    private javax.swing.JTextField txtSearchName;
    // End of variables declaration//GEN-END:variables
}
