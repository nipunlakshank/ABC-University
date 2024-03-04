/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.abc.gui.user.lecturer;

import com.abc.exceptions.DataInsertException;
import com.abc.gui.MainPanel;
import static com.abc.gui.Splash.APP;
import com.abc.model.dao.ExamDAO;
import com.abc.model.dao.ExamViewDAO;
import com.abc.model.dao.SubjectDAO;
import com.abc.model.dto.ExamDTO;
import com.abc.model.dto.ExamViewDTO;
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
public class ManageExams extends MainPanel {

    private final ExamViewDAO examViewDAO;
    private final SubjectDAO subDAO;
    private final ExamDAO examDAO;

    /**
     * Creates new form ManageExams
     */
    public ManageExams() {
        super(ManageExams.class);
        initComponents();
        examViewDAO = new ExamViewDAO();
        subDAO = new SubjectDAO();
        examDAO = new ExamDAO();
        formatTables();
        loadSubjects();
        loadExams();
    }

    private void showAddMarks() {
        int rows = tblExams.getSelectedRowCount();
        if (rows > 1) {
            APP.showAlert(this, "Please select only one exam at a time.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int row = tblExams.getSelectedRow();
        if (row == -1) {
            APP.showAlert(this, "Please select a exam to add marks.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Map<String, String> data = new HashMap<>();
        data.put("examId", tblExams.getValueAt(row, 0) + "");
        data.put("examCode", tblExams.getValueAt(row, 2) + "");
        data.put("subject", tblExams.getValueAt(row, 3) + "");

        AddMarks addMarks = new AddMarks(APP, data, true);
        addMarks.setVisible(true);
    }

    private void changeStatus() {
        int rows = tblExams.getSelectedRowCount();
        if (rows > 1) {
            APP.showAlert(this, "Please select only one exam at a time.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int row = tblExams.getSelectedRow();
        if (row == -1) {
            APP.showAlert(this, "Please select a exam to change status.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String statusString = tblExams.getValueAt(row, 4) + "";
        Boolean status;
        status = !statusString.equalsIgnoreCase("active");

        try {
            examDAO.update((int) tblExams.getValueAt(row, 0), status);
            filterExams();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void addExam() {
        int rows = tblSubjects.getSelectedRowCount();
        if (rows > 1) {
            APP.showAlert(this, "Please select only one subject at a time.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int row = tblSubjects.getSelectedRow();
        if (row == -1) {
            APP.showAlert(this, "Please select a subject to add an exam.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int subId = (int) tblSubjects.getValueAt(row, 0);

        ExamDTO exam = new ExamDTO(0, subId, "", false);
        try {
            int examId = examDAO.save(exam);
            if (examId < 1) {
                throw new DataInsertException();
            }

            APP.showAlert(this, "New Exam added.", "Success", JOptionPane.INFORMATION_MESSAGE);
            filterExams();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void loadSubjects() {
        try {
            List<SubjectDTO> subjects = subDAO.getAll();
            DefaultTableModel dtm = (DefaultTableModel) tblSubjects.getModel();
            dtm.setRowCount(0);

            for (SubjectDTO subject : subjects) {
                dtm.addRow(new Object[]{subject.getId(), subject.getCode(), subject.getTitle(), subject.getSemester()});
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void loadExams() {
        try {
            List<ExamViewDTO> examViews = examViewDAO.getViews();
            DefaultTableModel dtm = (DefaultTableModel) tblExams.getModel();
            dtm.setRowCount(0);

            for (ExamViewDTO exam : examViews) {
                dtm.addRow(new Object[]{exam.getId(), exam.getSubjectId(), exam.getCode(), exam.getSubject(), exam.getSemester(), exam.getStatus()});
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void filterExams() {
        try {
            String inCode = txtExSearchExCode.getText();
            String inSubjectId = txtExSearchSubId.getText();
            String inTitle = txtExSearchSubTitle.getText();
            String status = cbStatus.getSelectedIndex() == 0 ? "" : cbStatus.getItemAt(cbStatus.getSelectedIndex());

            List<ExamViewDTO> examViews = examViewDAO.filter(inCode, inSubjectId, inTitle, status);

//            List<ExamViewDTO> filteredViews = examViews.parallelStream()
//                    .filter(view -> view.getCode().toLowerCase().contains(inCode.toLowerCase()) && (view.getSubjectId() + "").contains(inSubjectId) && view.getSubject().toLowerCase().contains(inTitle.toLowerCase()))
//                    .sorted(Comparator.comparingInt(ExamViewDTO::getId))
//                    .collect(Collectors.toList());

            DefaultTableModel dtm = (DefaultTableModel) tblExams.getModel();
            dtm.setRowCount(0);

            examViews.forEach(exam -> dtm.addRow(new Object[]{exam.getId(), exam.getSubjectId(), exam.getCode(), exam.getSubject(), exam.getSemester(), exam.getStatus()}));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void filterSubjects() {
        String inCode = txtSubSearchSubCode.getText();
        String inTitle = txtSubSearchSubTitle.getText();

        try {

            List<SubjectDTO> subjects = subDAO.filter(inCode, inTitle);
            DefaultTableModel dtm = (DefaultTableModel) tblSubjects.getModel();
            dtm.setRowCount(0);

            for (SubjectDTO subject : subjects) {
                dtm.addRow(new Object[]{subject.getId(), subject.getCode(), subject.getTitle(), subject.getSemester()});
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void clearFields() {
        txtExSearchExCode.setText("");
        txtExSearchSubId.setText("");
        txtExSearchSubTitle.setText("");
    }

    private void formatTables() {
        TableEditor.setTableHeaders(tblSubjects, Color.WHITE, new Color(0, 153, 153));

        TableEditor.setColumnRenderer(tblSubjects, SwingConstants.LEFT, 0);
        TableEditor.setColumnRenderer(tblSubjects, SwingConstants.CENTER, 1);
        TableEditor.setColumnRenderer(tblSubjects, SwingConstants.CENTER, 2);
        TableEditor.setColumnRenderer(tblSubjects, SwingConstants.CENTER, 3);

        TableEditor.setMaxColumnWidth(tblSubjects, 0, 50);
        TableEditor.setMaxColumnWidth(tblSubjects, 1, 100);
        TableEditor.setMaxColumnWidth(tblSubjects, 3, 150);
        TableEditor.setPreferredColumnWidth(tblSubjects, 2, 200);

        /////////////////////////////
        TableEditor.setTableHeaders(tblExams, Color.WHITE, new Color(255, 102, 0));

        TableEditor.setColumnRenderer(tblExams, SwingConstants.LEFT, 0);
        TableEditor.setColumnRenderer(tblExams, SwingConstants.CENTER, 1);
        TableEditor.setColumnRenderer(tblExams, SwingConstants.CENTER, 2);
        TableEditor.setColumnRenderer(tblExams, SwingConstants.CENTER, 3);
        TableEditor.setColumnRenderer(tblExams, SwingConstants.CENTER, 4);
        TableEditor.setColumnRenderer(tblExams, SwingConstants.CENTER, 5);

        TableEditor.setMaxColumnWidth(tblExams, 0, 50);
        TableEditor.setMaxColumnWidth(tblExams, 1, 300);
        TableEditor.setPreferredColumnWidth(tblSubjects, 2, 250);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        leftPanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSubjects = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtSubSearchSubCode = new javax.swing.JTextField();
        txtSubSearchSubTitle = new javax.swing.JTextField();
        btnPanel = new javax.swing.JPanel();
        btnAdd = new javax.swing.JButton();
        rightPanel = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtExSearchExCode = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtExSearchSubId = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtExSearchSubTitle = new javax.swing.JTextField();
        btnChangeStatus = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        btnAddMarks = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        cbStatus = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblExams = new javax.swing.JTable();

        leftPanel.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 102, 0));

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Add Exam");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 1, 13)); // NOI18N
        jLabel2.setText("Select Subject:");

        tblSubjects.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Code", "Title", "Semester"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblSubjects);

        jLabel3.setText("Subject Code:");

        jLabel4.setText("Subject Title:");

        txtSubSearchSubCode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSubSearchSubCodeKeyReleased(evt);
            }
        });

        txtSubSearchSubTitle.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSubSearchSubTitleKeyReleased(evt);
            }
        });

        btnPanel.setOpaque(false);
        btnPanel.setLayout(new java.awt.GridBagLayout());

        btnAdd.setBackground(new java.awt.Color(0, 153, 153));
        btnAdd.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        btnAdd.setForeground(new java.awt.Color(255, 255, 255));
        btnAdd.setText("Add");
        btnAdd.setPreferredSize(new java.awt.Dimension(100, 32));
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(36, 196, 63, 202);
        btnPanel.add(btnAdd, gridBagConstraints);

        javax.swing.GroupLayout leftPanelLayout = new javax.swing.GroupLayout(leftPanel);
        leftPanel.setLayout(leftPanelLayout);
        leftPanelLayout.setHorizontalGroup(
            leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, leftPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, leftPanelLayout.createSequentialGroup()
                        .addGroup(leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSubSearchSubCode)
                            .addComponent(txtSubSearchSubTitle, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );
        leftPanelLayout.setVerticalGroup(
            leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtSubSearchSubCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtSubSearchSubTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel5.setText("Exam Code:");

        txtExSearchExCode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtExSearchExCodeKeyReleased(evt);
            }
        });

        jLabel6.setText("Subject Id:");

        txtExSearchSubId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtExSearchSubIdKeyReleased(evt);
            }
        });

        jLabel7.setText("Subject Title:");

        txtExSearchSubTitle.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtExSearchSubTitleKeyReleased(evt);
            }
        });

        btnChangeStatus.setBackground(new java.awt.Color(255, 153, 0));
        btnChangeStatus.setFont(new java.awt.Font("Comic Sans MS", 0, 13)); // NOI18N
        btnChangeStatus.setForeground(new java.awt.Color(255, 255, 255));
        btnChangeStatus.setText("Change Status");
        btnChangeStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangeStatusActionPerformed(evt);
            }
        });

        btnClear.setBackground(new java.awt.Color(51, 51, 51));
        btnClear.setFont(new java.awt.Font("Comic Sans MS", 0, 13)); // NOI18N
        btnClear.setForeground(new java.awt.Color(255, 255, 255));
        btnClear.setText("Clear Fields");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btnAddMarks.setBackground(new java.awt.Color(0, 153, 204));
        btnAddMarks.setFont(new java.awt.Font("Comic Sans MS", 0, 13)); // NOI18N
        btnAddMarks.setForeground(new java.awt.Color(255, 255, 255));
        btnAddMarks.setText("Add Marks");
        btnAddMarks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddMarksActionPerformed(evt);
            }
        });

        jLabel8.setText("Marking Status:");

        cbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Active", "Closed" }));
        cbStatus.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbStatusItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnClear)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                        .addComponent(btnAddMarks, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnChangeStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtExSearchSubTitle)
                            .addComponent(txtExSearchExCode))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtExSearchSubId)
                            .addComponent(cbStatus, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(10, 10, 10)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtExSearchExCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txtExSearchSubId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtExSearchSubTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(cbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnChangeStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClear)
                    .addComponent(btnAddMarks, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnChangeStatus, btnClear});

        tblExams.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Subject Id", "Code", "Subject", "Semester", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblExams);

        javax.swing.GroupLayout rightPanelLayout = new javax.swing.GroupLayout(rightPanel);
        rightPanel.setLayout(rightPanelLayout);
        rightPanelLayout.setHorizontalGroup(
            rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rightPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jScrollPane2)
        );
        rightPanelLayout.setVerticalGroup(
            rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rightPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(leftPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rightPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(rightPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(leftPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnChangeStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangeStatusActionPerformed
        changeStatus();
    }//GEN-LAST:event_btnChangeStatusActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        addExam();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        clearFields();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnAddMarksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddMarksActionPerformed
        showAddMarks();
    }//GEN-LAST:event_btnAddMarksActionPerformed

    private void txtSubSearchSubCodeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSubSearchSubCodeKeyReleased
        filterSubjects();
    }//GEN-LAST:event_txtSubSearchSubCodeKeyReleased

    private void txtSubSearchSubTitleKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSubSearchSubTitleKeyReleased
        filterSubjects();
    }//GEN-LAST:event_txtSubSearchSubTitleKeyReleased

    private void txtExSearchExCodeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtExSearchExCodeKeyReleased
        filterExams();
    }//GEN-LAST:event_txtExSearchExCodeKeyReleased

    private void txtExSearchSubIdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtExSearchSubIdKeyReleased
        filterExams();
    }//GEN-LAST:event_txtExSearchSubIdKeyReleased

    private void txtExSearchSubTitleKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtExSearchSubTitleKeyReleased
        filterExams();
    }//GEN-LAST:event_txtExSearchSubTitleKeyReleased

    private void cbStatusItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbStatusItemStateChanged
        filterExams();
    }//GEN-LAST:event_cbStatusItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAddMarks;
    private javax.swing.JButton btnChangeStatus;
    private javax.swing.JButton btnClear;
    private javax.swing.JPanel btnPanel;
    private javax.swing.JComboBox<String> cbStatus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel leftPanel;
    private javax.swing.JPanel rightPanel;
    private javax.swing.JTable tblExams;
    private javax.swing.JTable tblSubjects;
    private javax.swing.JTextField txtExSearchExCode;
    private javax.swing.JTextField txtExSearchSubId;
    private javax.swing.JTextField txtExSearchSubTitle;
    private javax.swing.JTextField txtSubSearchSubCode;
    private javax.swing.JTextField txtSubSearchSubTitle;
    // End of variables declaration//GEN-END:variables
}
