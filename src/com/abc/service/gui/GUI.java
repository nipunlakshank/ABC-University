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
class GUI {
    private MainPanel content;

    protected GUIState createState() {
        return new GUIState(content);
    }

    protected void restore(GUIState state) {
        content = state.getContent();
    }

    protected MainPanel getContent() {
        return content;
    }

    protected void setContent(MainPanel content) {
        this.content = content;
    }
}
