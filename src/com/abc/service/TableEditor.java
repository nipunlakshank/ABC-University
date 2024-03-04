/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.abc.service;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;

/**
 *
 * @author nipun
 */
public class TableEditor {

    public static void setTableHeaders(JTable table) {
        Font font = new Font("Comic Sans MS", Font.BOLD, 13);
        table.getTableHeader().setFont(font);
        table.getTableHeader().setBackground(new Color(30, 135, 111));
        table.getTableHeader().setForeground(Color.WHITE);
    }

    public static void setTableHeaders(JTable table, Color foreground, Color background) {
        Font font = new Font("Comic Sans MS", Font.BOLD, 13);
        table.getTableHeader().setFont(font);
        table.getTableHeader().setBackground(background);
        table.getTableHeader().setForeground(foreground);
    }

    public static void setTableHeaders(JTable table, Color foreground, Color background, Font font) {
        table.getTableHeader().setFont(font);
        table.getTableHeader().setBackground(background);
        table.getTableHeader().setForeground(foreground);
    }

    public static void setCellsRenderer(JTable table, int alignment) {

        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(alignment);

        TableModel tableModel = table.getModel();

        for (int columnIndex = 0; columnIndex < tableModel.getColumnCount(); columnIndex++) {
            table.getColumnModel().getColumn(columnIndex).setCellRenderer(cellRenderer);
        }

    }

    public static void setColumnRenderer(JTable table, int alignment, int columnIndex, Color color) {

        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setForeground(color);
        cellRenderer.setHorizontalAlignment(alignment);

        table.getColumnModel().getColumn(columnIndex).setCellRenderer(cellRenderer);

    }

    public static void setColumnRenderer(JTable table, int alignment, int columnIndex) {

        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(alignment);

        table.getColumnModel().getColumn(columnIndex).setCellRenderer(cellRenderer);

    }
    
    public static void setMinColumnWidth(JTable table, int columnIndex, int minWidth){
        table.getColumnModel().getColumn(columnIndex).setMinWidth(minWidth);
    }
    public static void setMaxColumnWidth(JTable table, int columnIndex, int maxWidth){
        table.getColumnModel().getColumn(columnIndex).setMaxWidth(maxWidth);
    }
    public static void setPreferredColumnWidth(JTable table, int columnIndex, int preferredWidth){
        table.getColumnModel().getColumn(columnIndex).setPreferredWidth(preferredWidth);
    }
}
