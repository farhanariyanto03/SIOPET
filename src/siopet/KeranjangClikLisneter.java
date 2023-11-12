package siopet;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 *
 * @author nico flassy
 */
public class KeranjangClikLisneter implements MouseListener{

    private item_keranjang panel1;
    
    public KeranjangClikLisneter(item_keranjang panel) {
        this.panel1 = panel;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        panel1.cart_harga.setForeground(Color.red);
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
