/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package siopet;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author nico flassy
 */
public class PanelClikLisneter implements MouseListener{

    private item_notif panel;
    
    public PanelClikLisneter(item_notif panel) {
        this.panel = panel;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        panel.setBackground(Color.RED);
    }

    @Override
    public void mousePressed(MouseEvent e) {
       
    }

    @Override
    public void mouseReleased(MouseEvent e) {
      
    }

    @Override
    public void mouseEntered(MouseEvent e) {
      
    }

    @Override
    public void mouseExited(MouseEvent e) {
       
    }
    
}
