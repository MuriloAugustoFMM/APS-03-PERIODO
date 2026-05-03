/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package corridassql.Interface;

import java.awt.*;

/**
 *
 * @author flixz
 */
public class MainFrame extends Frame{
      
    public MainFrame(){   
	this.setTitle("Cronometro");
        this.setSize(500,500);
	this.setBackground(new Color(64,128,128));
	this.setLayout(null);
        this.setLocationRelativeTo(null);
	this.addWindowListener(new java.awt.event.WindowAdapter() {    
            @Override
            public void windowClosing(java.awt.event.WindowEvent e){
                System.exit(0);
            }
          
        });
        
        
    }
    
  
}
