/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.abc.gui.user.admin;

import com.abc.gui.GlassPane;
import static com.abc.gui.Splash.APP;
import java.awt.Color;

/**
 *
 * @author nipun
 */
public class AdminHomeContent extends GlassPane {

    /**
     * Creates new form AdminHomeContent
     */
    public AdminHomeContent() {
        initComponents();
        setBackground(new Color(0, 0, 0, 150));
        titlePanel.setBackground(new Color(89, 243, 253, 60));
        midPanel.setOpaque(false);
        btnPanel.setOpaque(false);
        topPanel.setOpaque(false);
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

        topPanel = new javax.swing.JPanel();
        titlePanel = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        midPanel = new javax.swing.JPanel();
        btnPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        btnDegress = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btnLecturers = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        btnStudents = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        topPanel.setOpaque(false);
        topPanel.setLayout(new java.awt.GridBagLayout());

        lblTitle.setFont(new java.awt.Font("Comic Sans MS", 1, 20)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("Home | Admin");

        javax.swing.GroupLayout titlePanelLayout = new javax.swing.GroupLayout(titlePanel);
        titlePanel.setLayout(titlePanelLayout);
        titlePanelLayout.setHorizontalGroup(
            titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titlePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 513, Short.MAX_VALUE)
                .addContainerGap())
        );
        titlePanelLayout.setVerticalGroup(
            titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, titlePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                .addContainerGap())
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 375;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(28, 30, 28, 30);
        topPanel.add(titlePanel, gridBagConstraints);

        add(topPanel, java.awt.BorderLayout.PAGE_START);

        midPanel.setLayout(new java.awt.BorderLayout());

        jPanel1.setOpaque(false);
        jPanel1.setLayout(new java.awt.GridBagLayout());

        btnDegress.setBackground(new java.awt.Color(247, 93, 59));
        btnDegress.setFont(new java.awt.Font("Comic Sans MS", 1, 16)); // NOI18N
        btnDegress.setForeground(new java.awt.Color(255, 255, 255));
        btnDegress.setText("Manage Degrees");
        btnDegress.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(247, 93, 59), 1, true));
        btnDegress.setPreferredSize(new java.awt.Dimension(135, 25));
        btnDegress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDegressActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 119;
        gridBagConstraints.ipady = 27;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 162, 0, 163);
        jPanel1.add(btnDegress, gridBagConstraints);

        jPanel2.setOpaque(false);
        jPanel2.setLayout(new java.awt.GridBagLayout());

        btnLecturers.setBackground(new java.awt.Color(247, 93, 59));
        btnLecturers.setFont(new java.awt.Font("Comic Sans MS", 1, 16)); // NOI18N
        btnLecturers.setForeground(new java.awt.Color(255, 255, 255));
        btnLecturers.setText("Manage Lecturers");
        btnLecturers.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 0, true));
        btnLecturers.setPreferredSize(new java.awt.Dimension(135, 25));
        btnLecturers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLecturersActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 109;
        gridBagConstraints.ipady = 29;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 161, 0, 170);
        jPanel2.add(btnLecturers, gridBagConstraints);

        jPanel3.setOpaque(false);
        jPanel3.setLayout(new java.awt.GridBagLayout());

        btnStudents.setBackground(new java.awt.Color(247, 93, 59));
        btnStudents.setFont(new java.awt.Font("Comic Sans MS", 1, 16)); // NOI18N
        btnStudents.setForeground(new java.awt.Color(255, 255, 255));
        btnStudents.setText("Manage Students");
        btnStudents.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 0, true));
        btnStudents.setPreferredSize(new java.awt.Dimension(135, 25));
        btnStudents.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStudentsActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 116;
        gridBagConstraints.ipady = 29;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 160, 0, 164);
        jPanel3.add(btnStudents, gridBagConstraints);

        javax.swing.GroupLayout btnPanelLayout = new javax.swing.GroupLayout(btnPanel);
        btnPanel.setLayout(btnPanelLayout);
        btnPanelLayout.setHorizontalGroup(
            btnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(btnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(btnPanelLayout.createSequentialGroup()
                        .addGroup(btnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 573, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 573, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        btnPanelLayout.setVerticalGroup(
            btnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnPanelLayout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(73, Short.MAX_VALUE))
        );

        midPanel.add(btnPanel, java.awt.BorderLayout.CENTER);

        add(midPanel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnDegressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDegressActionPerformed
        APP.guiManager.setContent(new ManageDegrees());
    }//GEN-LAST:event_btnDegressActionPerformed

    private void btnLecturersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLecturersActionPerformed
        APP.guiManager.setContent(new ManageLecturers());
    }//GEN-LAST:event_btnLecturersActionPerformed

    private void btnStudentsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStudentsActionPerformed
        APP.guiManager.setContent(new ManageStudents());
    }//GEN-LAST:event_btnStudentsActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDegress;
    private javax.swing.JButton btnLecturers;
    private javax.swing.JPanel btnPanel;
    private javax.swing.JButton btnStudents;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel midPanel;
    private javax.swing.JPanel titlePanel;
    private javax.swing.JPanel topPanel;
    // End of variables declaration//GEN-END:variables
}
