/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.abc.service.gui;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nipun
 */
class GUIHistory {

    private final List<GUIState> states;
    private int currentIndex;
    private final int maxHistorySize;

    protected GUIHistory() {
        states = new ArrayList<>();
        currentIndex = -1;
        maxHistorySize = 5;
    }

    public void push(GUIState state) {

        while (currentIndex < states.size() - 1) {
            states.remove(states.size() - 1);
        }

        states.add(state);

        if (states.size() > maxHistorySize) {
            states.remove(0);
        }
        currentIndex = states.size() - 1;
    }

    public GUIState previous() throws IndexOutOfBoundsException{
        if (currentIndex == 0) {
            throw new IndexOutOfBoundsException();
        }
        return states.get(--currentIndex);
    }
    
    public GUIState next() throws IndexOutOfBoundsException{
        if(currentIndex == states.size() - 1){
            throw new IndexOutOfBoundsException();
        }
        return states.get(++currentIndex);
    }
    
    public int size(){
        return states.size();
    }
    
    public void clear(){
        states.clear();
    }

}
