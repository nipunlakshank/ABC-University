/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.abc.gui;

import javax.swing.JPanel;

/**
 *
 * @author nipun
 */
public class MainPanel extends JPanel{
    private final Class contentClass;
    
    public MainPanel(Class contentClass){
        this.contentClass = contentClass;
    }
    
    public Class getContentClass(){
        return contentClass;
    }
}
