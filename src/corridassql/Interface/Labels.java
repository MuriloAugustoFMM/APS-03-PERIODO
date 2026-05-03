/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package corridassql.Interface;

import java.awt.Label;
import java.util.ArrayList;

/**
 *
 * @author flixz
 */
public class Labels {
    private ArrayList<Label> bodyLabels = new ArrayList<>();
    private ArrayList<Label> menuLabels = new ArrayList<>();
    
    public Labels(){
        this.generateBodyLabels();
        this.generateMenuLabels();
    }
    
    public ArrayList getBodyLabels(){
        return this.bodyLabels;
    }
    
    public ArrayList getMenuLabels(){
        return this.menuLabels;
    }
    
    public void generateBodyLabels(){
        for(int i = 0; i < 8; i ++){
            bodyLabels.add(new Label());
            bodyLabels.get(i).setBounds(10,(160 + 20 * i),120,20);
            bodyLabels.get(i).setText("TESTE");
        }
     
    }
    
    public void generateMenuLabels(){
        for(int i = 0; i < 2; i ++){
            menuLabels.add(new Label());
            menuLabels.get(i).setBounds(10,(10 + 40 * i),130,20);
            menuLabels.get(i).setText("TESTE");
        }
     
    }
    
  
    
}
