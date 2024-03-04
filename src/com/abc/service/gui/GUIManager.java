/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.abc.service.gui;

import com.abc.gui.MainPanel;
import static com.abc.gui.Splash.APP;
import com.abc.gui.user.admin.AdminHome;
import com.abc.gui.user.lecturer.LecturerHome;
import com.abc.gui.user.student.StudentHome;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Container;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author nipun
 */
public class GUIManager {

    private final GUI gui;
    private final GUIHistory history;
    private static GUIManager manager;
    private final Container container;
    private final JPanel basePanel;
    private final Set<Class> homeClasses;

    private GUIManager(Container container, JPanel basePanel) {
        this.container = container;
        this.basePanel = basePanel;
        gui = new GUI();
        history = new GUIHistory();
        homeClasses = new HashSet<>();
        homeClasses.add(AdminHome.class);
        homeClasses.add(LecturerHome.class);
        homeClasses.add(StudentHome.class);
    }

    public static GUIManager getManager(Container container, JPanel basePanel) {

        if (manager == null) {
            manager = new GUIManager(container, basePanel);
        }
        return manager;
    }

    public static GUIManager getManager() throws InstantiationError {
        if (manager == null) {
            throw new InstantiationError();
        }
        return manager;
    }

    private void update(MainPanel panel) {
        container.remove(basePanel);
        basePanel.removeAll();
        basePanel.setLayout(new BorderLayout());
        basePanel.add(panel, BorderLayout.CENTER);
        container.add(basePanel, BorderLayout.CENTER);
        container.validate();
        container.repaint();
        APP.currentContent = panel.getContentClass();
    }

    public void setContent(MainPanel panel) {
        gui.setContent(panel);
        if (homeClasses.contains(panel.getClass())) {
            history.clear();
        }
        history.push(gui.createState());
        update(panel);
    }

    public void previous() {
        try {
            gui.restore(history.previous());
            update(gui.getContent());
        } catch (IndexOutOfBoundsException e) {
        }
    }

    public void clearHistory() {
        history.clear();
    }

    public void next() {
        try {
            gui.restore(history.next());
            update(gui.getContent());
        } catch (IndexOutOfBoundsException e) {
        }
    }
}
