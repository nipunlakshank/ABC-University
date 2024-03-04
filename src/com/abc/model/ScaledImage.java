/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.abc.model;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author nipun
 */
public class ScaledImage {

    private static final int WIDTH;
    private static final int HEIGHT;
    private final String key;
    private final Image image;
    private static final Map<String, Image> scaledImages;

    static {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        WIDTH = (int) screenSize.getWidth();
        HEIGHT = (int) screenSize.getHeight();
        scaledImages = new HashMap<>();
    }

    public ScaledImage(String key, BufferedImage bImg) {
        this.key = key;
        
        if (scaledImages.containsKey(key)) {
            image = scaledImages.get(key);
        } else {
            image = bImg.getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
            scaledImages.put(key, image);
        }
    }
    
    public ScaledImage(String key, BufferedImage bImg, int width, int height) {
        this.key = key;
        
        if (scaledImages.containsKey(key)) {
            image = scaledImages.get(key);
        } else {
            image = bImg.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            scaledImages.put(key, image);
        }
    }

    public String getKey() {
        return key;
    }

    public Image getImage() {
        return image;
    }

}
