/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.abc.gui.user.admin;

import com.abc.gui.ImagePanel;
import com.abc.gui.MainPanel;
import com.abc.gui.Welcome;
import com.abc.model.ScaledImage;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author nipun
 */
public class AdminHome extends MainPanel {

    private JPanel bgPanel;
    private JPanel floatingPanel;

    /**
     * Creates new form AdminHome
     */
    public AdminHome() {
        super(AdminHome.class);
        initComponents();
        this.setLayout(new BorderLayout());
        setBackgroundPanel();
        setFloatingPanel();
    }

    private void setFloatingPanel() {

        floatingPanel = new AdminHomeContent();
        floatingPanel.setOpaque(false);
        bgPanel.setLayout(new GridBagLayout());
        bgPanel.add(floatingPanel, new GridBagConstraints());
    }

    private void setBackgroundPanel() {
        BufferedImage bImg = null;
        try {
            bImg = ImageIO.read(Welcome.class.getResourceAsStream("../assets/img/admin-bg.jpeg"));
        } catch (IOException e) {
        }

        ScaledImage img = new ScaledImage("admin-bg", bImg);

        bgPanel = new ImagePanel(this, img);
        this.add(bgPanel, BorderLayout.CENTER);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1200, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 750, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
