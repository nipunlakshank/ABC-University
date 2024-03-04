/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.abc.gui.user.lecturer;

import static com.abc.gui.Splash.APP;
import com.abc.model.dao.ExamStudentViewDAO;
import com.abc.model.dto.ExamStudentViewDTO;
import com.abc.model.dto.ResultsViewDTO;
import com.abc.service.MySQL;
import com.abc.service.TableEditor;
import com.abc.util.MyPatterns;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nipun
 */
public class AddMarks extends javax.swing.JDialog {

    private final ExamStudentViewDAO examStuViewDAO;
    private final MySQL db;
    private Boolean isConnCreated;
    private List<ExamStudentViewDTO> views;

    /**
     * Creates new form AddMarks
     *
     * @param parent
     * @param data
     * @param modal
     */
    public AddMarks(java.awt.Frame parent, Map<String, String> data, boolean modal) {
        super(parent, modal);
        initComponents();
        formatTables();
        examStuViewDAO = new ExamStudentViewDAO();
        setLabels(data);
        db = new MySQL();
        isConnCreated = false;
        loadTable();
    }

    private void setLabels(Map<String, String> data) {
        lblExamId.setText(data.get("examId"));
        lblExamCode.setText(data.get("examCode"));
        lblSubject.setText(data.get("subject"));
    }

    private void loadTable() {
        int examId = Integer.parseInt(lblExamId.getText());
        try {
            views = examStuViewDAO.getViews(examId);

            DefaultTableModel dtm = (DefaultTableModel) table.getModel();
            dtm.setRowCount(0);

            for (ExamStudentViewDTO view : views) {
                dtm.addRow(new Object[]{view.getId(), view.getSno(), view.getName(), view.getEmail(), view.getMarks()});
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void filter() {

        String inSno = txtSno.getText();
        String inName = txtName.getText();
        String inEmail = txtEmail.getText();

        List<ExamStudentViewDTO> filteredViews = views.parallelStream()
                .filter(view -> view.getSno().toLowerCase().contains(inSno.toLowerCase()) && view.getName().toLowerCase().contains(inName.toLowerCase()) && view.getEmail().toLowerCase().contains(inEmail.toLowerCase()))
                .sorted(Comparator.comparingInt(ExamStudentViewDTO::getId))
                .collect(Collectors.toList());

        DefaultTableModel dtm = (DefaultTableModel) table.getModel();
        dtm.setRowCount(0);

        filteredViews.forEach(view -> dtm.addRow(new Object[]{view.getId(), view.getSno(), view.getName(), view.getEmail(), view.getMarks()}));

    }

    private void addMarks() {

        int rows = table.getSelectedRowCount();
        if (rows > 1) {
            APP.showAlert(this, "Please select one row at a time.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int row = table.getSelectedRow();
        if (row == -1) {
            APP.showAlert(this, "Please select a student to add marks.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!isConnCreated) {
            try {
                db.createConnection();
                isConnCreated = true;
            } catch (SQLException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }

        if (!MyPatterns.MARKS.matcher(txtMarks.getText()).matches()) {
            APP.showAlert(this, "Invalid input", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int examId = Integer.parseInt(lblExamId.getText());
        int studentId = (int) table.getValueAt(row, 0);
        double marks = Double.parseDouble(txtMarks.getText());

        try {
            db.createIUDStatement("UPDATE `student_has_exam` SET `marks`=? WHERE `student_id`=? AND `exam_id`=?")
                    .setDouble(1, marks)
                    .setInt(2, studentId)
                    .setInt(3, examId)
                    .executeUpdate();

            table.setValueAt(marks, row, 4);
            txtMarks.setText("");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    private void commit() {
        try {
            db.commit();
            isConnCreated = false;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void closeOperation() {
        if (!isConnCreated) {
            try {
                db.close();
            } catch (Exception ex) {
            }
            this.dispose();
            return;
        }

        int option = JOptionPane.showConfirmDialog(this, "Closing this window will ignore any unsaved changes.\nDo you wish to save changes before closing?", "Warning!", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
        try {
            switch (option) {
                case JOptionPane.YES_OPTION -> {
                    try (db) {
                        db.commit();
                        this.dispose();
                    }
                }
                case JOptionPane.NO_OPTION -> {
                    try (db) {
                        db.rollback();
                        this.dispose();
                    }
                }
                default -> {
                    return;
                }
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
        TableEditor.setColumnRenderer(table, SwingConstants.CENTER, 4);

        TableEditor.setMaxColumnWidth(table, 0, 50);
        TableEditor.setMaxColumnWidth(table, 1, 150);
        TableEditor.setPreferredColumnWidth(table, 2, 200);
        TableEditor.setPreferredColumnWidth(table, 4, 100);
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
        jLabel2 = new javax.swing.JLabel();
        lblExamId = new javax.swing.JLabel();
        lblExamCode = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblSubject = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtSno = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        btnCommit = new javax.swing.JButton();
        btnClose = new javax.swing.JButton();
        btnClearSno = new javax.swing.JButton();
        btnClearName = new javax.swing.JButton();
        btnClearEmail = new javax.swing.JButton();
        txtMarks = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Exam Id:");

        jLabel2.setText("Exam Code:");

        lblExamId.setText("null");

        lblExamCode.setText("null");

        jLabel5.setText("Subject:");

        lblSubject.setText("null");

        jLabel7.setText("Student No:");

        txtSno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSnoKeyReleased(evt);
            }
        });

        jLabel8.setText("Name:");

        txtName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNameKeyReleased(evt);
            }
        });

        jLabel9.setText("Email:");

        txtEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEmailKeyReleased(evt);
            }
        });

        btnCommit.setBackground(new java.awt.Color(0, 153, 153));
        btnCommit.setFont(new java.awt.Font("Comic Sans MS", 0, 13)); // NOI18N
        btnCommit.setForeground(new java.awt.Color(255, 255, 255));
        btnCommit.setText("Commit Changes");
        btnCommit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCommitActionPerformed(evt);
            }
        });

        btnClose.setBackground(new java.awt.Color(255, 102, 0));
        btnClose.setFont(new java.awt.Font("Comic Sans MS", 0, 13)); // NOI18N
        btnClose.setForeground(new java.awt.Color(255, 255, 255));
        btnClose.setText("Close");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        btnClearSno.setBackground(new java.awt.Color(51, 51, 51));
        btnClearSno.setForeground(new java.awt.Color(255, 255, 255));
        btnClearSno.setText("Clear");

        btnClearName.setBackground(new java.awt.Color(51, 51, 51));
        btnClearName.setForeground(new java.awt.Color(255, 255, 255));
        btnClearName.setText("Clear");

        btnClearEmail.setBackground(new java.awt.Color(51, 51, 51));
        btnClearEmail.setForeground(new java.awt.Color(255, 255, 255));
        btnClearEmail.setText("Clear");

        txtMarks.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMarksKeyReleased(evt);
            }
        });

        jLabel3.setText("Marks:");

        btnAdd.setBackground(new java.awt.Color(0, 102, 204));
        btnAdd.setFont(new java.awt.Font("Comic Sans MS", 0, 13)); // NOI18N
        btnAdd.setForeground(new java.awt.Color(255, 255, 255));
        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout topPanelLayout = new javax.swing.GroupLayout(topPanel);
        topPanel.setLayout(topPanelLayout);
        topPanelLayout.setHorizontalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(topPanelLayout.createSequentialGroup()
                        .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(topPanelLayout.createSequentialGroup()
                                .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblExamId, javax.swing.GroupLayout.PREFERRED_SIZE, 528, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblExamCode, javax.swing.GroupLayout.PREFERRED_SIZE, 526, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(topPanelLayout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtSno))
                                    .addGroup(topPanelLayout.createSequentialGroup()
                                        .addGap(31, 31, 31)
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtName, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE))))
                            .addGroup(topPanelLayout.createSequentialGroup()
                                .addComponent(lblSubject, javax.swing.GroupLayout.PREFERRED_SIZE, 561, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtEmail)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnClearSno)
                            .addComponent(btnClearName)
                            .addComponent(btnClearEmail)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, topPanelLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMarks, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAdd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCommit, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        topPanelLayout.setVerticalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanelLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1)
                        .addComponent(lblExamId, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(txtSno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnClearSno)))
                .addGap(18, 18, 18)
                .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, topPanelLayout.createSequentialGroup()
                        .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnClearName)
                            .addComponent(jLabel2)
                            .addComponent(lblExamCode, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnClearEmail)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(lblSubject, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnClose)
                    .addComponent(btnCommit)
                    .addComponent(txtMarks, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(btnAdd))
                .addContainerGap())
        );

        topPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {lblExamCode, lblExamId, lblSubject});

        topPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnAdd, btnCommit, jLabel3, txtMarks});

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Id", "Student No.", "Name", "Email", "Marks"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addComponent(topPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(topPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE)
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
    }// </editor-fold>//GEN-END:initComponents

    private void txtSnoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSnoKeyReleased
        filter();
    }//GEN-LAST:event_txtSnoKeyReleased

    private void txtNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNameKeyReleased
        filter();
    }//GEN-LAST:event_txtNameKeyReleased

    private void txtEmailKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailKeyReleased
        filter();
    }//GEN-LAST:event_txtEmailKeyReleased

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        closeOperation();
    }//GEN-LAST:event_btnCloseActionPerformed

    private void btnCommitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCommitActionPerformed
        commit();
    }//GEN-LAST:event_btnCommitActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        addMarks();
    }//GEN-LAST:event_btnAddActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        if (table.getSelectedRowCount() == 1 && table.getSelectedRow() != -1) {
            txtMarks.setText(table.getValueAt(table.getSelectedRow(), 4) + "");
        }
    }//GEN-LAST:event_tableMouseClicked

    private void txtMarksKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMarksKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            addMarks();
        }
    }//GEN-LAST:event_txtMarksKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnClearEmail;
    private javax.swing.JButton btnClearName;
    private javax.swing.JButton btnClearSno;
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnCommit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblExamCode;
    private javax.swing.JLabel lblExamId;
    private javax.swing.JLabel lblSubject;
    private javax.swing.JTable table;
    private javax.swing.JPanel topPanel;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtMarks;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtSno;
    // End of variables declaration//GEN-END:variables
}
