package corridassql;

import corridassql.Interface.MainInterface;
import corridassql.Model;

/*
            O QUE FAZER?
-> testar
-> Criar modelo DER.
-> Criar as entidades: Times, Carros, Corredores, Corridas e tabela de vinculo
-> Conectar banco de dados 'corridas' com o java.
-> Criação do menu top principal
-> Criação da tela principal genérica
-> Criação do método para verificar mudança no menu do topo
-> Criação de uma função para limpar tudo
-> Criação da lógica para alteração das telas
-> Criação da lógica de alteração utilizando os botões de busca e registro
-> Criação das telas de registro e busca
-> Criar funções para coletar os dados do textfield
-> Criar funções para validar os dados dos textfields
-> Criar função de criação no banco de dados
-> Criar botao de atualizacao
-> Criar funcao de atualizacao
-> criar botao de exclusao
-> Criar funcao de exclusao
-> Vincular as solicitacoes com o bd
-> Criar tela para salvar resultados.

*/

public class  Corrida{
	    
    private Model model = new Model();
              
        
   
        // metodo principal
    public static void main(String[] args) {
            Corrida corrida = new Corrida();
		
                
            MainInterface main = new MainInterface(corrida.model);

    }
}

