package corridassql;/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Nelson Batista
 */
public class Contato{
                private String codigo;	
                private String nome;	
                private String endereco;
                private String fone;
		private String celular;	
                private String sexo;
                private String obs;

		public Contato( 
                        String codigo,
                        String nome, 
                        String endereco, 
                        String fone, 
                        String celular, 
                        String sexo,
                        String obs)
                    {
                        this.codigo=codigo;
                        this.nome = nome;
                        this.endereco = endereco;
                        this.fone = fone; 
                        this.celular = celular;
                        this.sexo=sexo;
                    this.obs=obs;
                    }
		public String getCodigo(){
			return this.codigo;}
		public String getNome(){
			return this.nome;}
		public String getEndereco(){
			return this.endereco;}
		public String getFone(){
			return this.fone;}
		public String getCelular(){
			return this.celular;}
		public String getSexo(){
			return this.sexo;}
		public String getObs() {
			return this.obs;}
}
