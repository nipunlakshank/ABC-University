/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.abc.service.gui;

import com.abc.gui.MainPanel;

/**
 *
 * @author nipun
 */
public class GUIState {
    private final MainPanel content;

    public GUIState(MainPanel content) {
        this.content = content;
    }

    public MainPanel getContent() {
        return content;
    }
}
