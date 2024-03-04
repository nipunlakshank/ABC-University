/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.abc.gui.user.admin;

import com.abc.gui.MainPanel;
import static com.abc.gui.Splash.APP;
import com.abc.model.dao.DegreeDAO;
import com.abc.model.dao.DegreeSubjectDAO;
import com.abc.model.dto.DegreeViewDTO;
import com.abc.model.dto.SubjectDTO;
import com.abc.service.TableEditor;
import java.awt.Color;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nipun
 */
public class ManageDegrees extends MainPanel {

    private final DegreeSubjectDAO degSubDAO;
    private final DegreeDAO degDAO;

    /**
     * Creates new form ManageDegrees
     */
    public ManageDegrees() {
        super(ManageDegrees.class);
        initComponents();
        degSubDAO = new DegreeSubjectDAO();
        degDAO = new DegreeDAO();
        formatTables();
        loadDegrees();
    }
    
    private void showAddLecturer(){
        int subs = tblSubjects.getSelectedRowCount();
        int degs = tblDegrees.getSelectedRowCount();
        if(subs > 1 || degs > 1){
            APP.showAlert(this, "Please select only one degree and a subject at a time.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int subRow = tblSubjects.getSelectedRow();
        int degRow = tblDegrees.getSelectedRow();
        if(subRow == -1 || degRow == -1){
            APP.showAlert(this, "Please select the degree and subject to add a lecturer.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Map<String, String> data = new HashMap<>();
        data.put("degId", tblDegrees.getValueAt(degRow, 0) + "");
        data.put("degTitle", tblDegrees.getValueAt(degRow, 1) + "");
        data.put("subId", tblSubjects.getValueAt(subRow, 0) + "");
        data.put("subCode", tblSubjects.getValueAt(subRow, 1) + "");
        data.put("subTitle", tblSubjects.getValueAt(subRow, 2) + "");
        AssignLecturerToSubject addLecToSub = new AssignLecturerToSubject(APP, data, true);
        addLecToSub.setVisible(true);
    }
    
    private void loadSubjects(int degreeId) {
        try {
            List<SubjectDTO> subjects = degSubDAO.getSubjectViews(degreeId);
            DefaultTableModel dtm = (DefaultTableModel) tblSubjects.getModel();
            dtm.setRowCount(0);

            for (SubjectDTO subject : subjects) {
                dtm.addRow(new Object[]{subject.getId(), subject.getCode(), subject.getTitle(), subject.getCredits(), subject.getSemester(), subject.getState()});
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void loadDegrees() {
        try {
            clearSubjects();
            DefaultTableModel dtm = (DefaultTableModel) tblDegrees.getModel();
            dtm.setRowCount(0);
            List<DegreeViewDTO> degViews = degSubDAO.getDegreeViews();

            for (DegreeViewDTO degView : degViews) {
                dtm.addRow(new Object[]{degView.getId(), degView.getTitle(), degView.getCredits(), degView.getIsActive() ? "Active" : "Inactive"});
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void filterDeg(String inTitle) {
        try {
            DefaultTableModel dtm = (DefaultTableModel) tblDegrees.getModel();
            dtm.setRowCount(0);
            List<DegreeViewDTO> degViews = degSubDAO.filterDegrees(inTitle);

            for (DegreeViewDTO degView : degViews) {
                dtm.addRow(new Object[]{degView.getId(), degView.getTitle(), degView.getCredits(), degView.getIsActive() ? "Active" : "Inactive"});
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void filterSub(int degId, String inCode, String inTitle) {
        try {
            DefaultTableModel dtm = (DefaultTableModel) tblSubjects.getModel();
            dtm.setRowCount(0);
            List<SubjectDTO> subViews = degSubDAO.filterSubjects(degId, inCode, inTitle);

            for (SubjectDTO subView : subViews) {
                dtm.addRow(new Object[]{subView.getId(), subView.getCode(), subView.getTitle(), subView.getCredits(), subView.getSemester(), subView.getState()});
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void clearSubjects() {
        DefaultTableModel dtm = (DefaultTableModel) tblSubjects.getModel();
        dtm.setRowCount(0);
    }

    private void clearFields() {
        txtDegTitle.setText("");
        txtSubTitle.setText("");
        loadDegrees();
    }
    
    private void refreshDegrees(){
        filterDeg(txtDegTitle.getText());
        clearSubjects();
    }

    private void changeDegStatus(int row) {
        int degId = (int) tblDegrees.getValueAt(row, 0);
        String title = tblDegrees.getValueAt(row, 1) + "";
        Boolean isActive = !(tblDegrees.getValueAt(row, 3) + "").equals("Active");
        try {
            degDAO.update(degId, title, isActive);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        refreshDegrees();
    }

    private void formatTables() {
        TableEditor.setTableHeaders(tblDegrees, Color.WHITE, new Color(255, 102, 0));
        TableEditor.setTableHeaders(tblSubjects, Color.WHITE, new Color(255, 102, 0));

        TableEditor.setColumnRenderer(tblDegrees, SwingConstants.LEFT, 0);
        TableEditor.setColumnRenderer(tblDegrees, SwingConstants.CENTER, 1);
        TableEditor.setColumnRenderer(tblDegrees, SwingConstants.CENTER, 2);
        TableEditor.setColumnRenderer(tblDegrees, SwingConstants.CENTER, 3);

        TableEditor.setColumnRenderer(tblSubjects, SwingConstants.LEFT, 0);
        TableEditor.setColumnRenderer(tblSubjects, SwingConstants.CENTER, 1);
        TableEditor.setColumnRenderer(tblSubjects, SwingConstants.CENTER, 2);
        TableEditor.setColumnRenderer(tblSubjects, SwingConstants.CENTER, 3);
        TableEditor.setColumnRenderer(tblSubjects, SwingConstants.CENTER, 4);

        TableEditor.setMaxColumnWidth(tblDegrees, 0, 50);
        TableEditor.setMinColumnWidth(tblDegrees, 1, 150);

        TableEditor.setMaxColumnWidth(tblSubjects, 0, 50);
        TableEditor.setMinColumnWidth(tblSubjects, 2, 150);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        basePanel = new javax.swing.JPanel();
        topPanel = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        btnChangeStatus = new javax.swing.JButton();
        txtDegTitle = new javax.swing.JTextField();
        btnClearDegTitle = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        txtSubTitle = new javax.swing.JTextField();
        txtSubCode = new javax.swing.JTextField();
        btnClearSubTitle = new javax.swing.JButton();
        btnClearSubCode = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        midPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDegrees = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSubjects = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();

        basePanel.setLayout(new java.awt.BorderLayout());

        topPanel.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setOpaque(false);

        jLabel3.setFont(new java.awt.Font("Comic Sans MS", 1, 13)); // NOI18N
        jLabel3.setText("Degree Title: ");

        jButton1.setBackground(new java.awt.Color(0, 153, 102));
        jButton1.setFont(new java.awt.Font("Comic Sans MS", 0, 13)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Add New Degree");

        btnChangeStatus.setBackground(new java.awt.Color(255, 153, 0));
        btnChangeStatus.setFont(new java.awt.Font("Comic Sans MS", 0, 13)); // NOI18N
        btnChangeStatus.setForeground(new java.awt.Color(255, 255, 255));
        btnChangeStatus.setText("Change Status");
        btnChangeStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangeStatusActionPerformed(evt);
            }
        });

        txtDegTitle.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDegTitleKeyReleased(evt);
            }
        });

        btnClearDegTitle.setBackground(new java.awt.Color(51, 51, 51));
        btnClearDegTitle.setForeground(new java.awt.Color(255, 255, 255));
        btnClearDegTitle.setText("Clear");
        btnClearDegTitle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearDegTitleActionPerformed(evt);
            }
        });

        btnRefresh.setBackground(new java.awt.Color(0, 153, 204));
        btnRefresh.setFont(new java.awt.Font("Comic Sans MS", 0, 13)); // NOI18N
        btnRefresh.setForeground(new java.awt.Color(255, 255, 255));
        btnRefresh.setText("Refresh");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnChangeStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnRefresh))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDegTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnClearDegTitle)))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnChangeStatus, btnRefresh, jButton1});

        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDegTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClearDegTitle))
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnChangeStatus)
                        .addComponent(btnRefresh))
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnChangeStatus, btnRefresh, jButton1});

        jPanel4.setOpaque(false);

        jLabel7.setFont(new java.awt.Font("Comic Sans MS", 1, 13)); // NOI18N
        jLabel7.setText("Subject Title: ");

        jLabel8.setFont(new java.awt.Font("Comic Sans MS", 1, 13)); // NOI18N
        jLabel8.setText("Subject Code: ");

        jButton3.setBackground(new java.awt.Color(0, 153, 102));
        jButton3.setFont(new java.awt.Font("Comic Sans MS", 0, 13)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Add Subject");

        jButton4.setBackground(new java.awt.Color(255, 102, 0));
        jButton4.setFont(new java.awt.Font("Comic Sans MS", 0, 13)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Delete Subject");

        txtSubTitle.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSubTitleKeyReleased(evt);
            }
        });

        txtSubCode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSubCodeKeyReleased(evt);
            }
        });

        btnClearSubTitle.setBackground(new java.awt.Color(51, 51, 51));
        btnClearSubTitle.setForeground(new java.awt.Color(255, 255, 255));
        btnClearSubTitle.setText("Clear");
        btnClearSubTitle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearSubTitleActionPerformed(evt);
            }
        });

        btnClearSubCode.setBackground(new java.awt.Color(51, 51, 51));
        btnClearSubCode.setForeground(new java.awt.Color(255, 255, 255));
        btnClearSubCode.setText("Clear");
        btnClearSubCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearSubCodeActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(0, 153, 153));
        jButton2.setFont(new java.awt.Font("Comic Sans MS", 0, 13)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Assign Lecturer");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtSubTitle)
                            .addComponent(txtSubCode, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnClearSubTitle)
                            .addComponent(btnClearSubCode))))
                .addContainerGap())
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton2, jButton3, jButton4});

        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtSubTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClearSubTitle))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtSubCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClearSubCode))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                    .addComponent(jButton2))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton2, jButton3, jButton4});

        javax.swing.GroupLayout topPanelLayout = new javax.swing.GroupLayout(topPanel);
        topPanel.setLayout(topPanelLayout);
        topPanelLayout.setHorizontalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        topPanelLayout.setVerticalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        topPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jPanel3, jPanel4});

        basePanel.add(topPanel, java.awt.BorderLayout.PAGE_START);

        midPanel.setBackground(new java.awt.Color(204, 204, 255));

        tblDegrees.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Title", "Total Credits", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
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
        tblDegrees.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDegreesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDegrees);

        tblSubjects.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Code", "Title", "Credits", "Semester", "State"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblSubjects);

        jPanel1.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 102, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Degrees");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setOpaque(false);

        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 1, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 102, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Subjects");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout midPanelLayout = new javax.swing.GroupLayout(midPanel);
        midPanel.setLayout(midPanelLayout);
        midPanelLayout.setHorizontalGroup(
            midPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(midPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(midPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 545, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(midPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 545, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(8, 8, 8))
        );
        midPanelLayout.setVerticalGroup(
            midPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, midPanelLayout.createSequentialGroup()
                .addGroup(midPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(midPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );

        basePanel.add(midPanel, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(basePanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(basePanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblDegreesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDegreesMouseClicked
        int rowCount = tblDegrees.getSelectedRowCount();
        if (rowCount != 1) {
            return;
        }
        int row = tblDegrees.getSelectedRow();

        int degId = (int) tblDegrees.getValueAt(row, 0);
        filterSub(degId, txtSubCode.getText(), txtSubTitle.getText());
    }//GEN-LAST:event_tblDegreesMouseClicked

    private void btnClearDegTitleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearDegTitleActionPerformed
        txtDegTitle.setText("");
        loadDegrees();
    }//GEN-LAST:event_btnClearDegTitleActionPerformed

    private void btnClearSubTitleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearSubTitleActionPerformed
        txtSubTitle.setText("");
        int rows = tblDegrees.getSelectedRowCount();
        int row = tblDegrees.getSelectedRow();
        if(rows > 1 || row == -1){
            clearSubjects();
            return;
        }
        int degId = (int) tblDegrees.getValueAt(row, 0);
        loadSubjects(degId);
    }//GEN-LAST:event_btnClearSubTitleActionPerformed

    private void btnClearSubCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearSubCodeActionPerformed
        txtSubCode.setText("");
        int rows = tblDegrees.getSelectedRowCount();
        int row = tblDegrees.getSelectedRow();
        if(rows > 1 || row == -1){
            clearSubjects();
            return;
        }
        int degId = (int) tblDegrees.getValueAt(row, 0);
        loadSubjects(degId);
    }//GEN-LAST:event_btnClearSubCodeActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        refreshDegrees();
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void btnChangeStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangeStatusActionPerformed
        int rows = tblDegrees.getSelectedRowCount();
        if (rows > 1) {
            APP.showAlert(this, "Please select only one degree at a time." ,"Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int row = tblDegrees.getSelectedRow();
        if(row == -1){
            APP.showAlert(this, "Please select a degree to change status." ,"Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
         changeDegStatus(row);
    }//GEN-LAST:event_btnChangeStatusActionPerformed

    private void txtDegTitleKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDegTitleKeyReleased
        filterDeg(txtDegTitle.getText());
        int row = tblDegrees.getSelectedRow();
        if(row == -1){
            clearSubjects();
        }
    }//GEN-LAST:event_txtDegTitleKeyReleased

    private void txtSubTitleKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSubTitleKeyReleased
        int degId = (int) tblDegrees.getValueAt(tblDegrees.getSelectedRow(), 0);
        filterSub(degId, txtSubCode.getText(), txtSubTitle.getText());
    }//GEN-LAST:event_txtSubTitleKeyReleased

    private void txtSubCodeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSubCodeKeyReleased
        int degId = (int) tblDegrees.getValueAt(tblDegrees.getSelectedRow(), 0);
        filterSub(degId, txtSubCode.getText(), txtSubTitle.getText());
    }//GEN-LAST:event_txtSubCodeKeyReleased

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        showAddLecturer();
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel basePanel;
    private javax.swing.JButton btnChangeStatus;
    private javax.swing.JButton btnClearDegTitle;
    private javax.swing.JButton btnClearSubCode;
    private javax.swing.JButton btnClearSubTitle;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel midPanel;
    private javax.swing.JTable tblDegrees;
    private javax.swing.JTable tblSubjects;
    private javax.swing.JPanel topPanel;
    private javax.swing.JTextField txtDegTitle;
    private javax.swing.JTextField txtSubCode;
    private javax.swing.JTextField txtSubTitle;
    // End of variables declaration//GEN-END:variables
}
