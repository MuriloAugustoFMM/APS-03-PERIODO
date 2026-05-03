/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package corridassql.Interface;

import java.awt.Choice;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;


/**
 *
 * @author flixz
 */
public class DropDown{
    
    private ArrayList<Choice> bodyDD = new ArrayList<>();
    private Choice menuDD;
    public DropDown(){
        this.generateMenuDD();
        this.generateBodyDD();
    }
    
    public void generateMenuDD(){
        this.menuDD = new Choice();
        this.menuDD.add("");
        this.menuDD.add("Corridas");
        this.menuDD.add("Times");
        this.menuDD.add("Pilotos");
        this.menuDD.add("Veiculos");
        this.menuDD.add("Resultados");
        this.menuDD.setBounds(140,50,100,100);
        
    }
    
    public void generateBodyDD(){
        for(int i = 0; i < 8; i++){
            this.bodyDD.add(new Choice());
            this.bodyDD.get(i).setBounds(130, (160 + 20 * i),230,20);
            this.bodyDD.get(i).setVisible(false);
            this.bodyDD.get(i).add("TESTE");
        }
    }
    
    public ArrayList getBodyDD(){
        return this.bodyDD;
    }
    
    public Choice getMenuDD(){
        return this.menuDD;
    }
    
   
}
