/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package corridassql.Interface;

import java.awt.TextField;
import java.util.ArrayList;

/**
 *
 * @author flixz
 */
public class TextFields {
    private ArrayList<TextField> textFields = new ArrayList<>();
    
    public TextFields(){
        generateTextFields();   
    }
    
    public void generateTextFields(){
        for(int i = 0; i < 8; i++){
            this.textFields.add(new TextField());
            this.textFields.get(i).setBounds(130, (160 + 20 * i),230,20);
        }
    }
    
    public ArrayList getTextFields(){
        return this.textFields;
    }
    
    
}
