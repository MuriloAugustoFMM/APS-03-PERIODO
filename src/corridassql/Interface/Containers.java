/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package corridassql.Interface;

import java.awt.Color;
import java.awt.Panel;
import java.util.ArrayList;

/**
 *
 * @author flixz
 */
public class Containers {
    private ArrayList<Panel> containers =  new ArrayList<>();
    
    public Containers(){
        this.generateContainers();
    }
    
    public void generateContainers(){
        for(int i = 0; i < 2; i++){
            containers.add(new Panel());
            containers.get(i).setBounds(0,40,500, 150 + 300 * i);
            containers.get(i).setLayout(null);
            containers.get(i).setBackground(new Color(155,155,55 + i * 100));
        }
    }
    
    public ArrayList getContainers(){
        return this.containers;
    }
    
}
