/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package corridassql.Interface;
/**
 *
 * @author flixz
 */
import corridassql.Interface.Containers;
import corridassql.Interface.Labels;
import corridassql.Interface.MainFrame;
import corridassql.Interface.TextFields;
import corridassql.Interface.Buttons;
import corridassql.Interface.DropDown;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.function.Consumer;
import corridassql.Model;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


        
public class MainInterface {
    
private Frame window = new MainFrame();
private ArrayList<Label> bodyLabels = new Labels().getBodyLabels();
private ArrayList<Label> menuLabels = new Labels().getMenuLabels();
private ArrayList<Panel> containers = new Containers().getContainers();
private ArrayList<TextField> textFields = new TextFields().getTextFields();
private ArrayList<Button> menuButtons = new Buttons().getMenuButtons();
private ArrayList<Choice> bodyDD = new DropDown().getBodyDD();
private Button actionButton = new Buttons().getActionButton();
private Button bCreate, bResearch, bUpdate, bDelete;
private Choice menuDD;
private Panel pMenu, pMain;
private Boolean activeRace, activeTeam, activePilot, activeVehicle, activeCreate, activeUp,activeRes, activeQry, activeDel;
private Model model;
private int[] idTest = {1,2,3,4,5,6,7,8,9,10}; 
public MainInterface(Model model){
    pMenu = this.containers.get(0);
    pMain = this.containers.get(1);
        
    this.model = model;
    
    this.renderLabels();
    this.renderTextFields();
    this.renderButtons();
    this.renderDropDown();
    
    this.renderPanels();
    this.window.setVisible(true);
    this.clearScreen();
    this.clearMode();
    
        
}



public Frame getInterface(){
    return window;
}

public ArrayList getbodyLabels(){
    return this.bodyLabels;
}

public ArrayList getbodyDD(){
    return this.bodyDD;
}

public ArrayList getContainers(){
    return this.containers;
}

public ArrayList getTextFields(){
    return this.textFields;
}

public ArrayList getMenuLabels(){
    return this.menuLabels;
}

public Button getCreateButton(){
    return this.bCreate;
}

public Button getDeleteButton(){
    return this.bDelete;
}

public Button getResearcButton(){
    return this.bResearch;
}

public Button getUpdateButton(){
    return this.bUpdate;
}


public void renderLabels(){
        for(Label l : this.bodyLabels){
            this.pMain.add(l);
        }
        Label op = this.menuLabels.get(0);
        op.setText("Selecione a operação: ");
        
        this.pMenu.add(op);
        
        Label table = this.menuLabels.get(1);
        table.setText("Selecione a tabela: ");

        this.pMenu.add(table);
        
        
}

public void setDefaultMode(){
    this.setResearchMode();
}

public void renderTextFields(){
        for(TextField t : this.textFields){
           this.pMain.add(t);
        }

        this.textFields.get(2).addKeyListener(new KeyAdapter(){
            public void keyTyped(KeyEvent e){

                char c = e.getKeyChar();
                String s = MainInterface.this.textFields.get(2).getText();
                MainInterface.this.keyboardControl(e,c,s);
            }
        });


        this.textFields.get(3).addKeyListener(new KeyAdapter(){
            public void keyTyped(KeyEvent e){
                     
            char c = e.getKeyChar();
            String s = MainInterface.this.textFields.get(3).getText();
            MainInterface.this.keyboardControl(e,c,s);
            }
        });


}

public void renderPanels(){
    for(Panel p : this.containers){
        this.window.add(p);
    }
}

public void renderButtons(){
    bCreate = this.menuButtons.get(0);
    bCreate.setLabel("Criar");
    bCreate.addActionListener( e ->{
          this.setCreateMode();
          
    });
    this.pMenu.add(bCreate);
    
    bResearch = this.menuButtons.get(1);
    bResearch.setLabel("Pesquisar");
    bResearch.addActionListener( e ->{
        this.setResearchMode();
    });
    this.pMenu.add(bResearch);
    
    bUpdate = this.menuButtons.get(2);
    bUpdate.setLabel("Atualizar");
    bUpdate.addActionListener( e ->{
        this.setUpdateMode();
    });
    this.pMenu.add(bUpdate);
    
    bDelete = this.menuButtons.get(3);
    bDelete.setLabel("Deletar");
    bDelete.addActionListener( e ->{
        this.setDeleteMode();
    });
    this.pMenu.add(bDelete);
    
    this.actionButton.addActionListener(e ->{
        this.executeAction();
    });
  
    
    this.pMenu.add(this.actionButton);

}

public void renderDropDown(){
    this.menuDD = new DropDown().getMenuDD();
    this.menuDD.addItemListener(new ItemListener(){
        @Override
        public void itemStateChanged(ItemEvent e){
            String item = MainInterface.this.menuDD.getSelectedItem();
            MainInterface.this.changeScreen(item);
            System.out.println(item);
        }
                
    });
    this.pMenu.add(this.menuDD);
    this.bodyDD = new DropDown().getBodyDD();
    for(int i =0; i < 8; i++){
        this.pMain.add(this.bodyDD.get(i));
    }
    
}

public void printMode(){
    System.out.println("MODES: ");
    System.out.println(String.format("CREATE: %b",this.activeCreate));
    System.out.println(String.format("QUERY: %b",this.activeQry));
    System.out.println(String.format("UPDATE: %b",this.activeUp));
    System.out.println(String.format("DELETE: %b",this.activeDel));
}

public void printScreen(){
    //
}

public void clearScreen(){
  for(int i = 0; i < 8; i++){
      this.textFields.get(i).setVisible(false);
      this.textFields.get(i).setText("");
      this.bodyLabels.get(i).setVisible(false);
      this.bodyDD.get(i).setVisible(false);
  }
    this.activePilot = false;
    this.activeRace = false;
    this.activeTeam = false;
    this.activeVehicle = false;
    this.activeRes = false;
} 

public void clearMode(){
    this.activeCreate = false;
    this.activeDel = false;
    this.activeQry = false;
    this.activeUp = false;
    
}

public void setRaceScreen(String mode){
    // definindo as labels
    this.bodyLabels.get(0).setText("Id:");
    this.bodyLabels.get(0).setVisible(true);
    
    this.bodyLabels.get(1).setText("Nome:");
    this.bodyLabels.get(1).setVisible(true);
    
    this.bodyLabels.get(2).setText("Cidade:");
    this.bodyLabels.get(2).setVisible(true);
    
    this.bodyLabels.get(3).setText("Data:");
    this.bodyLabels.get(3).setVisible(true);
    
    this.textFields.get(0).setVisible(true);
        
    this.textFields.get(1).setVisible(true);
    
    this.textFields.get(2).setVisible(true);
    
    this.textFields.get(3).setVisible(true);
    
    
    
    if(this.activeQry || this.activeDel){
        
        this.textFields.get(0).setVisible(false);
        
        this.loadIds(this.model.searchRace(),this.bodyDD.get(0));
        this.bodyDD.get(0).setVisible(true);
        
        
        this.textFields.get(1).setEnabled(false);
    
        this.textFields.get(2).setEnabled(false);
    
        this.textFields.get(3).setEnabled(false);
    }else{
        
        this.textFields.get(0).setEnabled(false);
        
        this.textFields.get(1).setEnabled(true);
    
        this.textFields.get(2).setEnabled(true);
    
        this.textFields.get(3).setEnabled(true);
        
        if(this.activeUp){
            this.textFields.get(0).setVisible(false);
            this.textFields.get(0).setEnabled(false);
            
            this.loadIds(this.model.searchRace(),this.bodyDD.get(0));
            this.bodyDD.get(0).setVisible(true);
            
        }
    }
    
        
    
    
    this.activeRace = true;
    
}

public void setTeamsScreen(String mode){
    
    this.bodyLabels.get(0).setText("Id:");
    this.bodyLabels.get(0).setVisible(true);

    this.bodyLabels.get(1).setText("Nome do time:");
    this.bodyLabels.get(1).setVisible(true);
    
    this.textFields.get(0).setVisible(true);
    

    this.textFields.get(1).setVisible(true);
    
    if(this.activeQry || this.activeDel){
        
        this.textFields.get(0).setVisible(false);
        
        this.loadIds(this.model.searchTeam(), this.bodyDD.get(0));
        this.bodyDD.get(0).setVisible(true);
        
        this.textFields.get(1).setEnabled(false);
    
    }else{
        this.textFields.get(0).setEnabled(false);
        this.textFields.get(1).setEnabled(true);
        if(this.activeUp){
            this.textFields.get(0).setEnabled(false);
            this.textFields.get(0).setVisible(false);

            this.loadIds(this.model.searchTeam(), this.bodyDD.get(0));
            this.bodyDD.get(0).setVisible(true);
        }
    }
    
    
    
    this.activeTeam = true;
    
}

public void setPilotScreen(String mode){
    
    this.bodyLabels.get(0).setText("Id:");
    this.bodyLabels.get(0).setVisible(true);

    this.bodyLabels.get(1).setText("Nome:");
    this.bodyLabels.get(1).setVisible(true);
    
    this.bodyLabels.get(2).setText("Idade:");
    this.bodyLabels.get(2).setVisible(true);
    
    this.bodyLabels.get(3).setText("Time:");
    this.bodyLabels.get(3).setVisible(true);
    
    this.textFields.get(0).setVisible(true);
    
    this.textFields.get(1).setVisible(true);
    
    this.textFields.get(2).setVisible(true);
    
    this.textFields.get(3).setVisible(true);
    
    if(this.activeQry || this.activeDel){
        
        this.textFields.get(0).setVisible(false);
        
        this.loadIds(this.model.searchPilot(),this.bodyDD.get(0));
        this.bodyDD.get(0).setVisible(true);
        
        this.textFields.get(1).setEnabled(false);
        this.textFields.get(2).setEnabled(false);
        this.textFields.get(3).setEnabled(false);
        this.textFields.get(3).setEnabled(false);
        
        
    }else{
        this.textFields.get(0).setEnabled(false);
        this.textFields.get(1).setEnabled(true);
        this.textFields.get(2).setEnabled(true);
        this.textFields.get(3).setVisible(false);

        this.loadIds(this.model.searchTeam(), this.bodyDD.get(3));
        this.bodyDD.get(3).setVisible(true);
        
        if(this.activeUp){
            this.textFields.get(0).setEnabled(false);
            this.textFields.get(0).setVisible(false);

            //this.loadIds();
            this.bodyDD.get(0).setVisible(true);
        }
        
        
    }
    
    
    
   
    
    this.activePilot = true;
    
}

public void setVehicleScreen(String mode){
    
    this.bodyLabels.get(0).setText("Id:");
    this.bodyLabels.get(0).setVisible(true);

    this.bodyLabels.get(1).setText("Modelo:");
    this.bodyLabels.get(1).setVisible(true);
    
    this.bodyLabels.get(2).setText("Marca:");
    this.bodyLabels.get(2).setVisible(true);
    
    this.bodyLabels.get(3).setText("Potencia:");
    this.bodyLabels.get(3).setVisible(true);
    
    this.bodyLabels.get(4).setText("Time:");
    this.bodyLabels.get(4).setVisible(true);
    
    this.textFields.get(0).setVisible(true);
    
    this.textFields.get(1).setVisible(true);
    
    this.textFields.get(2).setVisible(true);
    
    this.textFields.get(3).setVisible(true);
    
    this.textFields.get(4).setVisible(true);
    
    if(this.activeQry || this.activeDel){
        this.textFields.get(0).setVisible(false);
        this.loadIds(this.model.searchVehicle(), this.bodyDD.get(0));
        this.bodyDD.get(0).setVisible(true);
        
        this.textFields.get(1).setEnabled(false);
        this.textFields.get(2).setEnabled(false);
        this.textFields.get(3).setEnabled(false);
        this.textFields.get(4).setEnabled(false);
    }else{
        this.textFields.get(0).setEnabled(false);
        this.textFields.get(1).setEnabled(true);
        this.textFields.get(2).setEnabled(true);
        this.textFields.get(3).setEnabled(true);
        this.textFields.get(4).setVisible(false);
        
        this.loadIds(this.model.searchTeam(),this.bodyDD.get(4));
        this.bodyDD.get(4).setVisible(true);
        if(this.activeUp){
            this.textFields.get(0).setVisible(false);
            this.loadIds(this.model.searchVehicle(), this.bodyDD.get(0));
            this.bodyDD.get(0).setVisible(true);
        
        }
        
    }
    
    this.activeVehicle = true;
    
}

public void setResultsScreen(String mode){
    
    this.bodyLabels.get(0).setText("Id:");
    this.bodyLabels.get(0).setVisible(true);

    this.bodyLabels.get(1).setText("Corrida:");
    this.bodyLabels.get(1).setVisible(true);
    
    this.bodyLabels.get(2).setText("Piloto:");
    this.bodyLabels.get(2).setVisible(true);
    
    this.bodyLabels.get(3).setText("Time:");
    this.bodyLabels.get(3).setVisible(true);
    
    this.bodyLabels.get(4).setText("Veiculo:");
    this.bodyLabels.get(4).setVisible(true);
    
    this.bodyLabels.get(5).setText("Primeira volta:");
    this.bodyLabels.get(5).setVisible(true);
    
    this.bodyLabels.get(6).setText("Segunda volta:");
    this.bodyLabels.get(6).setVisible(true);
    
    this.bodyLabels.get(7).setText("Tempo total:");
    this.bodyLabels.get(7).setVisible(true);
    
    this.textFields.get(0).setVisible(true);
    
    this.textFields.get(1).setVisible(true);
    
    this.textFields.get(2).setVisible(true);
    
    this.textFields.get(3).setVisible(true);
    
    this.textFields.get(4).setVisible(true);
   
    this.textFields.get(5).setVisible(true);
    
    this.textFields.get(6).setVisible(true);
    
    this.textFields.get(7).setVisible(true);
    
    
    if(this.activeQry || this.activeDel){
        this.textFields.get(0).setVisible(false);
       // this.loadIds();
        this.bodyDD.get(0).setVisible(true);
        this.textFields.get(1).setEnabled(false);
        this.textFields.get(2).setEnabled(false);
        this.textFields.get(3).setEnabled(false);
        this.textFields.get(4).setEnabled(false);
        this.textFields.get(5).setEnabled(false);
        this.textFields.get(6).setEnabled(false);
        this.textFields.get(7).setEnabled(false);
    }else{
        this.textFields.get(0).setEnabled(false);
        
        this.textFields.get(1).setVisible(false);
       // this.loadIds();
        this.bodyDD.get(1).setVisible(true);
        this.textFields.get(2).setVisible(false);
       // this.loadIds();
        this.bodyDD.get(2).setVisible(true);
        this.textFields.get(3).setVisible(false);
       // this.loadIds();
        this.bodyDD.get(3).setVisible(true);
        this.textFields.get(4).setVisible(false);
       // this.loadIds();
        this.bodyDD.get(4).setVisible(true);
        this.textFields.get(5).setEnabled(true);
        this.textFields.get(6).setEnabled(true);
        this.textFields.get(7).setEnabled(true);
        if(this.activeUp){
            this.textFields.get(0).setVisible(false);
          //  this.loadIds();
            this.bodyDD.get(0).setVisible(true);
        }
    }
    
    this.activeRes = true;
    
}

public void setCreateMode(){
    this.clearMode();
    this.activeCreate = true;
    this.bCreate.setEnabled(false);
    this.bDelete.setEnabled(true);
    this.bResearch.setEnabled(true);
    this.bUpdate.setEnabled(true);
    
    this.actionButton.setLabel("Criar");
    
    this.changeScreen(this.verifyScreen());
    
    this.printMode();
}

public void setResearchMode(){
    this.clearMode();
    this.activeQry = true;
    this.bCreate.setEnabled(true);
    this.bDelete.setEnabled(true);
    this.bResearch.setEnabled(false);
    this.bUpdate.setEnabled(true);
    
    this.actionButton.setLabel("Pesquisar");
    
    this.changeScreen(this.verifyScreen());
    
    this.printMode();
}

public void setUpdateMode(){
    this.clearMode();
    this.activeUp = true;
    this.bCreate.setEnabled(true);
    this.bDelete.setEnabled(true);
    this.bResearch.setEnabled(true);
    this.bUpdate.setEnabled(false);
    
    this.actionButton.setLabel("Atualizar");
    
    this.changeScreen(this.verifyScreen());
    
    this.printMode();
}

public void setDeleteMode(){
    this.clearMode();
    this.activeDel = true;
    this.bCreate.setEnabled(true);
    this.bDelete.setEnabled(false);
    this.bResearch.setEnabled(true);
    this.bUpdate.setEnabled(true);
    
    this.actionButton.setLabel("Deletar");
    
    this.changeScreen(this.verifyScreen());
    
    this.printMode();
}


public String verifyMode(){
    if(this.activeCreate) return "CREATE";
    if(this.activeDel) return "DELETE";
    if(this.activeQry) return "SELECT";
    if(this.activeUp) return "UPDATE";
    return "";
}

public String verifyScreen(){
    if(this.activeRace) return "Corridas";
    if(this.activePilot) return "Pilotos";
    if(this.activeTeam) return "Times";
    if(this.activeRes) return "Resultados";
    if(this.activeVehicle) return "Veiculos";
    return "";
}


public void changeScreen(String screen) {
    // LIMPA A TELA
    this.clearScreen();

    // COLOCA O MODO DEFAULT CASO NAO TENHA NADA SELECIONADO
    if (this.verifyMode() == "") this.setDefaultMode();

    //VERIFICA QUAL TELA ESTÁ SELECIONADA NO MENU DROP-DOWN
    switch (screen) {

        case "Corridas":
            System.out.println("Corrida");
            this.setRaceScreen(this.verifyMode());
            break;

        case "Times":
            System.out.println("Time");
            this.setTeamsScreen(this.verifyMode());

            break;

        case "Pilotos":
            System.out.println("Pilot");
            this.setPilotScreen(this.verifyMode());
            break;

        case "Veiculos":
            System.out.println("Veiculo");
            this.setVehicleScreen(this.verifyMode());
            break;

        case "Resultados":
            this.setResultsScreen(this.verifyMode());
            break;
        default:
            System.out.println("Tela nao encontrada");
            break;
    }


}

public void keyboardControl(KeyEvent e, char c, String s){

    if(this.activeRace && e.getSource() == this.textFields.get(3)){
        this.verifyDate(e,c,s);
    }else if(this.activePilot && e.getSource() == this.textFields.get(2)){
        this.verifyAge(e,c,s);
    }else if(this.activeVehicle && e.getSource() == this.textFields.get(3)){
        this.verifyPower(e,c,s);
    }
}

public void verifyPower(KeyEvent e, char c, String s){
    if(c != KeyEvent.VK_BACK_SPACE && !Character.isDigit(c)) {
        System.out.println("Caractere nao numerico detectado");
        e.consume();
    }else if (s.length() + 1 > 3 ) {
        System.out.println("Potenca invalida, escopo permitido (0-999)");
        e.consume();
    }


}

public void verifyAge(KeyEvent e, char c, String s){
    if(c != KeyEvent.VK_BACK_SPACE && !Character.isDigit(c)){
        System.out.println("Caractere nao numerico detectado");
        e.consume();
    }else if (s.length() + 1 >= 3 ) {
        System.out.println("Idade inválida, escopo permitido (0-99)");
        e.consume();
    }

}

public void verifyDate(KeyEvent e, char c, String s){
    if(c != KeyEvent.VK_BACK_SPACE && !Character.isDigit(c)){
        System.out.println("Caractere nao numerico detectado");
        e.consume(); // CONSOME O EVENTO, OU SEJA, NÃO ACONTECE            
    }else if(c != KeyEvent.VK_BACK_SPACE && (s.length() == 2 || s.length() == 5)){
            e.consume();
            this.textFields.get(3).setText(s + "/" + c);
            // desloca o carret, barra vertical, para o final da string apos modificala
            this.textFields.get(3).setCaretPosition(this.textFields.get(3).getText().length());
    }else if(s.length() + 1 > 10){
        e.consume();
    }
}

public void  executeAction(){
    switch(this.verifyMode()){
        case "CREATE":
            this.createRegister();
            break;
        case "DELETE":
            this.deleteRegister();
            break;
        case "SELECT":
            this.researchRegister();
            break;
        case "UPDATE":
            this.updateRegister();
            break;
}
}

public void createRegister(){
    switch(this.verifyScreen()){
        case "Corridas":
            String raceName, cityName, raceDate;
            raceName = this.textFields.get(1).getText();
            cityName = this.textFields.get(2).getText();
            raceDate = this.textFields.get(3).getText();

            this.model.createRace(raceName, cityName, raceDate);
            break;
        case "Times":
            String teamName = this.textFields.get(1).getText();
            this.model.createTeam(teamName);
            break;
        case "Pilotos":
            int age = Integer.parseInt(this.textFields.get(2).getText());
            if(age <= 17){
                System.out.println("Idade inválida, necessário 18 anos");
                break;
            }
            if(this.bodyDD.get(3).getSelectedItem().equals("")) break;
            int teamId = Integer.parseInt(this.bodyDD.get(3).getSelectedItem());

            String pilotName = this.textFields.get(1).getText();

            this.model.createPilot(pilotName,age,teamId);
            break;
        case "Veiculos":
            String model, brand;
            model = this.textFields.get(1).getText();
            brand = this.textFields.get(2).getText();
            int power, vehicleTeamId;
            power = Integer.parseInt(this.textFields.get(3).getText());
            if(power <= 0 ){
                System.out.println("Potencia invalida");
                break;
            }
            vehicleTeamId = Integer.parseInt(this.bodyDD.get(4).getSelectedItem());
            this.model.createVehicle(model,brand,power,vehicleTeamId);
            
            
            break;
        case "Resultados":
            break;
        default:
            System.out.println("Ação nao encontrada");
            break;
            
        
    }
}

public void updateRegister(){
    switch(this.verifyScreen()){
        case "Corridas":
            if(this.bodyDD.get(0).getSelectedItem() != ""){
                int id = Integer.parseInt(this.bodyDD.get(0).getSelectedItem());
                String name = this.textFields.get(1).getText();
                String city = this.textFields.get(2).getText();
                String date = this.textFields.get(3).getText();
                this.model.updateRace(id, name, city, date);
                this.changeScreen(this.verifyScreen());
            }
            break;
        case "Times":
            if(this.bodyDD.get(0).getSelectedItem() != ""){
                int id = Integer.parseInt(this.bodyDD.get(0).getSelectedItem());
                 String teamName = this.textFields.get(1).getText();
                this.model.updateTeam(id, teamName);
                this.changeScreen(this.verifyScreen());
            }   
            break;
        case "Pilotos":
            if(Integer.parseInt(this.textFields.get(2).getText()) <= 17){
                System.out.println("Idade inválida, necessário 18 anos");
                break;
            }
            if(this.bodyDD.get(0).getSelectedItem() != ""){
                int id = Integer.parseInt(this.bodyDD.get(0).getSelectedItem());
                String novoNome = this.textFields.get(1).getText();
                int pilotAge = Integer.parseInt(this.textFields.get(2).getText());
                int teamId = Integer.parseInt(this.bodyDD.get(3).getSelectedItem());
                this.model.updatePilot(id,novoNome,pilotAge,teamId);
                this.changeScreen(this.verifyScreen());
            }
            break;
        case "Veiculos":
            if(this.bodyDD.get(0).getSelectedItem() != "") {
                int id = Integer.parseInt(this.bodyDD.get(0).getSelectedItem());
                String newModel, newBrand;
                newModel = this.textFields.get(1).getText();
                newBrand = this.textFields.get(2).getText();
                int newPower, vehicleTeamId;
                newPower = Integer.parseInt(this.textFields.get(3).getText());
                if(newPower <= 0 ){
                    System.out.println("Potencia invalida");
                    break;
                }
                vehicleTeamId = Integer.parseInt(this.bodyDD.get(4).getSelectedItem());
                this.model.updateVehicle(id,newModel,newBrand,newPower,vehicleTeamId);
                this.changeScreen(this.verifyScreen());
            }
            break;
        case "Resultados":
            break;
        default:
            System.out.println("Ação nao encontrada");
            break;
            
        
    }
}

public void deleteRegister(){
    switch(this.verifyScreen()){
        case "Corridas":
            if(this.bodyDD.get(0).getSelectedItem() != ""){
                int id = Integer.parseInt(this.bodyDD.get(0).getSelectedItem());
                
                this.model.deleteRace(id);
                this.changeScreen(this.verifyScreen());
            }
            break;
        case "Times":
            if(this.bodyDD.get(0).getSelectedItem() != ""){
                int id = Integer.parseInt(this.bodyDD.get(0).getSelectedItem());
                this.model.deleteTeam(id);
                this.changeScreen(this.verifyScreen());
            }
            break;
        case "Pilotos":
            if(this.bodyDD.get(0).getSelectedItem() != "") {
                int id = Integer.parseInt(this.bodyDD.get(0).getSelectedItem());
                this.model.deletePilot(id);
                this.changeScreen(this.verifyScreen());
            }
            break;
        case "Veiculos":
            if(this.bodyDD.get(0).getSelectedItem() != "") {


            }
            break;
        case "Resultados":
            break;
        default:
            System.out.println("Ação nao encontrada");
            break;
            
        
    }
}

public void researchRegister(){
    switch(this.verifyScreen()){
        case "Corridas":
            if(this.bodyDD.get(0).getSelectedItem() != ""){
                int id = Integer.parseInt(this.bodyDD.get(0).getSelectedItem());
                ArrayList<String> answer = this.model.searchRace(id);
                this.textFields.get(1).setText(answer.get(0));
                this.textFields.get(2).setText(answer.get(1));
                this.textFields.get(3).setText(answer.get(2));
            }
            break;
        case "Times":
            if(this.bodyDD.get(0).getSelectedItem() != ""){
                int id = Integer.parseInt(this.bodyDD.get(0).getSelectedItem());
                ArrayList<String> answer = this.model.searchTeam(id);
                this.textFields.get(1).setText(answer.get(0));
            }  
            break;
        case "Pilotos":
                if(this.bodyDD.get(0).getSelectedItem() != ""){
                    int id = Integer.parseInt(this.bodyDD.get(0).getSelectedItem());
                    ArrayList<String> answer = this.model.searchPilot(id);
                    this.textFields.get(1).setText(answer.get(0));
                    this.textFields.get(2).setText(answer.get(1));
                    this.textFields.get(3).setText(answer.get(2));
                }

            break;
        case "Veiculos":
            if(this.bodyDD.get(0).getSelectedItem() != ""){
                int id = Integer.parseInt(this.bodyDD.get(0).getSelectedItem());
                ArrayList<String> answer = this.model.searchVehicle(id);
                this.textFields.get(1).setText(answer.get(0));
                this.textFields.get(2).setText(answer.get(1));
                this.textFields.get(3).setText(answer.get(2));
                this.textFields.get(4).setText(answer.get(3));
            }
            break;
        case "Resultados":
            break;
        default:
            System.out.println("Ação nao encontrada");
            break;
            
        
    }
}

public void loadIds(ArrayList<Integer> ids, Choice c){
    c.removeAll();
    c.add("");
    for(int i : ids){
        System.out.println(i);
        c.add(Integer.toString(i));
    } 
}



}
