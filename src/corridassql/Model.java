/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package corridassql;

import com.mysql.jdbc.ResultSet;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
/**
 *
 * @author flixz
 */
public class Model {
    private java.sql.Connection connection = new Connection().getCon();
    
    public void stareBD(){
         try{
                String sql = "SELECT * FROM pilots;";
                PreparedStatement pst = connection.prepareStatement(sql);
                pst.execute();
                
        }catch(SQLException ex){
            ex.printStackTrace();
                 
            }
    }
    
    public Model(){

    }
    
    public void createRace(String name, String city, String date){
        System.out.println("CHAMANDO FUNCAO CREATE RACE");
        //FORMATANDO A DATA
        DateTimeFormatter brFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        LocalDate formatedDate = LocalDate.parse(date, brFormat);
        
        java.sql.Date dateSql = java.sql.Date.valueOf(formatedDate);
        
        try{
                String sql = "INSERT INTO race (Race_name,Race_city,Race_date) VALUES (?,?,?)";
                PreparedStatement pst = connection.prepareStatement(sql);
                pst.setString(1, name);
                pst.setString(2, city);
                pst.setDate(3, dateSql);
                pst.executeUpdate();
                System.out.println("REGISTRADO COM SUCESSO");
        }catch(SQLException ex){
                ex.printStackTrace();
                 
        }
        
        
    }

    public ArrayList<String> searchRace(int id){
        String sql = "SELECT * FROM race WHERE Race_id = ?";
        ArrayList<String> answer = new ArrayList<>();
        try {
            PreparedStatement pst = connection.prepareStatement(sql);

            // 2. Define o ID que veio por parâmetro no primeiro '?'
            pst.setInt(1, id);

            // 3. Executa a busca
            ResultSet rs = (ResultSet) pst.executeQuery();

            // 4. Verifica se encontrou algum resultado
            if (rs.next()) {
                // Coleta os dados das colunas (ajuste os nomes conforme seu banco)
                String nome = rs.getString("Race_name");
                answer.add(nome);
                String local = rs.getString("Race_city");
                answer.add(local);
                java.sql.Date dataDb = rs.getDate("Race_date");
                if(dataDb != null) answer.add(dataDb.toString());
                else answer.add("SEM DATA");
                System.out.println("--- Corrida Encontrada ---");
                System.out.println("ID: " + id);
                System.out.println("Nome: " + nome);
                System.out.println("Local: " + local);
                System.out.println("Data: " + dataDb);
            } else {
                System.out.println("Nenhuma corrida encontrada com o ID: " + id);
            }

        } catch (SQLException ex) {
            System.out.println("Erro ao buscar corrida: " + ex.getMessage());
        }
        return answer;
    }
    
    public ArrayList<Integer> searchRace() {
    // 1. Criamos a lista que armazenará os IDs
        ArrayList<Integer> ids = new ArrayList<>();
        String sql = "SELECT Race_id FROM race";

        try {
            PreparedStatement pst = connection.prepareStatement(sql);

            // 2. Executa a query
            ResultSet rs = (ResultSet) pst.executeQuery();

            // 3. Percorre todos os resultados e adiciona na lista
            while (rs.next()) {
                ids.add(rs.getInt("Race_id"));
            }

            System.out.println("Total de IDs coletados: " + ids.size());

        } catch (SQLException ex) {
            System.out.println("Erro ao listar IDs: " + ex.getMessage());
        }

        // 4. Retorna a lista preenchida (ou vazia se der erro)
        return ids;
    }
    
    public void updateRace(int id, String novoNome, String novaCidade, String novaData) {
    // O SQL usa o WHERE para garantir que você não atualize a tabela inteira por engano!
    String sql = "UPDATE race SET Race_name = ?, Race_city = ?, Race_date = ? WHERE Race_id = ?";

    try {
        PreparedStatement pst = connection.prepareStatement(sql);
        
        // Preenchendo os novos valores
        pst.setString(1, novoNome);
        pst.setString(2, novaCidade);
        
        // Usando a lógica de conversão de data que vimos antes
        java.time.LocalDate data = java.time.LocalDate.parse(novaData, java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        pst.setObject(3, data);
        
        // O quarto '?' é o ID no WHERE
        pst.setInt(4, id);

        int rows = pst.executeUpdate();
        if (rows > 0) {
            System.out.println("Corrida atualizada com sucesso!");
        }
    } catch (SQLException e) {
        System.out.println("Erro ao atualizar: " + e.getMessage());
    }
}
    
    public void deleteRace(int id) {
    String sql = "DELETE FROM race WHERE Race_id = ?";

    try {
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setInt(1, id);

        int rows = pst.executeUpdate();
        if (rows > 0) {
            System.out.println("Corrida excluída com sucesso!");
        } else {
            System.out.println("Nenhum registro encontrado com esse ID.");
        }
    } catch (SQLException e) {
        // Se houver registros vinculados na tabela Races_link, o MySQL pode impedir a exclusão
        System.out.println("Erro ao excluir: " + e.getMessage());
    }
}
    
public void createTeam(String name) {
    String sql = "INSERT INTO teams (Team_name) VALUES (?)";
    try {
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setString(1, name);
        pst.executeUpdate();
        System.out.println("Time registrado!");
    } catch (SQLException ex) { ex.printStackTrace(); }
}

public void updateTeam(int id, String novoNome) {
    String sql = "UPDATE teams SET Team_name = ? WHERE Team_id = ?";
    try {
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setString(1, novoNome);
        pst.setInt(2, id);
        pst.executeUpdate();
    } catch (SQLException ex) { ex.printStackTrace(); }
}

public ArrayList<Integer> searchTeam(){
    ArrayList<Integer> ids = new ArrayList<>();
        String sql = "SELECT Team_id FROM teams";

        try {
            PreparedStatement pst = connection.prepareStatement(sql);

            // 2. Executa a query
            ResultSet rs = (ResultSet) pst.executeQuery();

            // 3. Percorre todos os resultados e adiciona na lista
            while (rs.next()) {
                ids.add(rs.getInt("Team_id"));
            }

            System.out.println("Total de IDs coletados: " + ids.size());

        } catch (SQLException ex) {
            System.out.println("Erro ao listar IDs: " + ex.getMessage());
        }

        // 4. Retorna a lista preenchida (ou vazia se der erro)
        return ids;
    }

public ArrayList<String> searchTeam(int id){
    ArrayList<String> answer = new ArrayList<>();
        String sql = "SELECT * FROM teams WHERE Team_id = ?";

        try {
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setInt(1, id);
            // 2. Executa a query
            ResultSet rs = (ResultSet) pst.executeQuery();
            if (rs.next()) {
                // Coleta os dados das colunas (ajuste os nomes conforme seu banco)
                String teamName = rs.getString("Team_name");
                answer.add(teamName);
            } else {
                System.out.println("Nenhuma corrida encontrada com o ID: " + id);
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao listar IDs: " + ex.getMessage());
        }

        // 4. Retorna a lista preenchida (ou vazia se der erro)
        return answer;
    }


public void deleteTeam(int id) {
    String sql = "DELETE FROM teams WHERE Team_id = ?";
    try {
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setInt(1, id);
        pst.executeUpdate();
    } catch (SQLException ex) { ex.printStackTrace(); }
}

public void createVehicle(String model, String brand, int power, int teamId) {
    String sql = "INSERT INTO vehicles (Vehicle_model, Vehicle_brand, Vehicle_power, Vehicle_team) VALUES (?,?,?,?)";
    try {
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setString(1, model);
        pst.setString(2, brand);
        pst.setInt(3, power);
        pst.setInt(4, teamId); // FK para o time
        pst.executeUpdate();
    } catch (SQLException ex) { ex.printStackTrace(); }
}

public void updateVehicle(int id, String model, String brand, int power) {
    String sql = "UPDATE vehicles SET Vehicle_model = ?, Vehicle_brand = ?, Vehicle_power = ? WHERE Vehicle_id = ?";
    try {
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setString(1, model);
        pst.setString(2, brand);
        pst.setInt(3, power);
        pst.setInt(4, id);
        pst.executeUpdate();
    } catch (SQLException ex) { ex.printStackTrace(); }
}

public void deleteVehicle(int id) {
    String sql = "DELETE FROM vehicles WHERE Vehicle_id = ?";
    try {
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setInt(1, id);
        pst.executeUpdate();
    } catch (SQLException ex) { ex.printStackTrace(); }
}
    
}
