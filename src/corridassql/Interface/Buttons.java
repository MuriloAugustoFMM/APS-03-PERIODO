/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package corridassql.Interface;

import java.awt.Button;
import java.util.ArrayList;

/**
 *
 * @author flixz
 */
public class Buttons {
    private ArrayList<Button> menuButtons = new ArrayList<>();
    private Button actionButton;
    
    
    public Buttons(){
       this.generateBodyButtons();
       this.generateActionButton();
    }
    
    public ArrayList getMenuButtons(){
        return this.menuButtons;
    }
    
    public Button getActionButton(){
        return this.actionButton;
    }
    
    public void generateBodyButtons(){
        for(int i = 0; i < 4; i++ ){
            menuButtons.add(new Button());
            menuButtons.get(i).setBounds(140 + 80 * i, 10,70,20);
            menuButtons.get(i).setLabel("BOTAO");
        }
        
    }
    
    public void generateActionButton(){
            actionButton = new Button();
            actionButton.setBounds(210, 110,70,20);
            actionButton.setLabel("Pesquisar");
    }
    
}
