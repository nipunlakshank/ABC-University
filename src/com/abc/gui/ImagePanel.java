/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.abc.gui;

import com.abc.model.ScaledImage;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.JPanel;

/**
 *
 * @author nipun
 */
public class ImagePanel extends JPanel {

    private final Image img;
    private final JPanel parent;

    public ImagePanel(JPanel parent, ScaledImage img) {
        this.parent = parent;
        this.img = img.getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        int x = (parent.getWidth() - img.getWidth(null)) / 2;
        int y = (parent.getHeight() - img.getHeight(null)) / 2;
        g2d.drawImage(img, x, y, null);
    }

}
