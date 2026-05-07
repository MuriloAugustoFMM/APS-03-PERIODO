/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package corridassql;

/**
 *
 * @author Nelson Batista
 */
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.util.Vector;
import java.sql.*;


public class Agenda extends WindowAdapter implements ActionListener, FocusListener{
	// Declara os componentes gráficos utilizados na janela
	private Frame janela;
	private Panel painelEndereco,painelBotoes;
	private Label lCodigo, lPiloto, lTime, lFone, lCelular, lCronometro;
	private TextField tCodigo, tNome, tEndereco, tFone, tCelular;
	private TextArea tObs;
	private Button bNovo, bSalva,bConsulta,bAltera,bExclui,bCrono;
	private MenuBar mb;
	private Menu m1;
	private MenuItem mi11, mi12;
	private CheckboxGroup cbgSexo;
	private Checkbox masculino, feminino;
	// Declara o vetor para armazenar os dados
	private Vector vContatos;
	// Declara uma variavel para controlar a movimentação no vetor
	private int posicao;

  	// INICIO DO METODO CONSTRUTOR DA CLASSE
  	// Quando a classe Agenda for instanciada(criando um objeto) este
  	// método será executado
	public Agenda(){
		// Cria o objeto VContatos para armazenar os dados
		vContatos=new Vector();
		// Cria a janela Agenda
		janela = new Frame();
		janela.setTitle("Agenda");
		janela.setSize(370,414);
		janela.setBackground(new Color(160,160,160));
		janela.setLayout(null);
		// Define um Listener(escutador) para a janela da Agenda
	 	janela.addWindowListener(this);

		// Cria o painel Endereço
		painelEndereco = new Panel();
		painelEndereco.setBackground(new Color(128,128,128));
		painelEndereco.setLayout(null);

		// Cria o painel Botões
		painelBotoes = new Panel();
		painelBotoes.setBackground(new Color(64,128,128));
		painelBotoes.setLayout(null);

		// Cria menu para a janela Agenda
		mb = new MenuBar();
		m1 = new Menu("Agenda");
		mi11 = new MenuItem("Cadastro");
		mi12 = new MenuItem("Sair");
		m1.add(mi11);
		m1.addSeparator();
		m1.add(mi12);
		mb.add(m1);
		mi11.addActionListener(this);
		mi12.addActionListener(this);

		// Cria Rótulos(Label) do painel Endereço
		lCodigo = new Label("Codigo:");
		lPiloto = new Label("Piloto:");
		lTime = new Label("Time:");
		lFone = new Label("Fone:");
		lFone.setVisible(false);
		lCelular = new Label("Celular:");
		lCelular.setVisible(false);
		lCronometro = new Label("999s");
		lCronometro.setFont(new Font(Font.SERIF,1,25));
		//Cria os campos de texto (TextField) do painel Endereço
		tCodigo = new TextField(10);
		tCodigo.addFocusListener(this);
                tCodigo.setEnabled(false);
                
		tNome = new TextField(45);
		// Define um Listener(escutador) para controlar o foco do campo nome
		tNome.addFocusListener(this);
                tNome.setEnabled(false);
                
		tEndereco = new TextField(60);
                tEndereco.setEnabled(false);
                
		tFone = new TextField(8);
                tFone.setEnabled(false);
				tFone.setVisible(false);
                
		tCelular = new TextField(9);
                tCelular.setEnabled(false);
				tCelular.setVisible(false);

		// Posiciona e define tamanhos para
		// Rótulos e campos de texto do painel Endereço
		lCodigo.setBounds(10,15,50,13);
		tCodigo.setBounds(70,12,50,19);
		lPiloto.setBounds(10,37,50,13);
		tNome.setBounds(70,34,240,19);
		lTime.setBounds(10,59,60,13);
		tEndereco.setBounds(70,56,270,19);
		lFone.setBounds(10,83,60,13);
		tFone.setBounds(70,80,86,19);
		lCelular.setBounds(180,83,50,13);
		tCelular.setBounds(240,80,80,19);
		lCronometro.setBounds(10,105,100,25);

		// Cria Checkboxes para sexo no painel Endereço
		cbgSexo = new CheckboxGroup();

		masculino = new Checkbox("Masculino",false,cbgSexo);
                masculino.setEnabled(false);
				masculino.setVisible(false);
		feminino = new Checkbox("Feminino",false,cbgSexo);
                feminino.setEnabled(false);
				feminino.setVisible(false);

		// Posiciona e define tamanhos para
		// Checkboxes do painel Endereço
		masculino.setBounds(70,102,90,19);
		feminino.setBounds(160,102,90,19);

		// Cria, define tamanho e posição de uma área de texto para observações
		tObs = new TextArea("",15,90,TextArea.SCROLLBARS_BOTH);
		tObs.setBounds(10,132,330,90);
                tObs.setEnabled(false);
				tObs.setVisible(false);

		// Adiciona Labels e campos de texto ao painel Endereco
		painelEndereco.add(lCodigo);
		painelEndereco.add(tCodigo);
		painelEndereco.add(lPiloto);
		painelEndereco.add(tNome);
		painelEndereco.add(lTime);
		painelEndereco.add(tEndereco);
		painelEndereco.add(lFone);
		painelEndereco.add(tFone);
		painelEndereco.add(lCelular);
		painelEndereco.add(tCelular);
		painelEndereco.add(lCronometro);
		painelEndereco.add(tObs);
		painelEndereco.add(masculino);
		painelEndereco.add(feminino);

		// Cria botões
		bNovo = new Button("Novo/Limpar");
		// Define um Listener(escutador) para cada botao
		bNovo.addActionListener(this);
		bSalva = new Button("Salva");
		bSalva.addActionListener(this);
		bConsulta = new Button("Consulta");
		bConsulta.addActionListener(this);
		bAltera=new Button("Altera");
		bAltera.addActionListener(this);
		bExclui=new Button("Exclui");
		bExclui.addActionListener(this);
		bCrono = new Button();
		bCrono.setBounds(150,100,70,25);
		bCrono.setLabel("START");
		painelEndereco.add(bCrono);

		// Define o gerenciador de layout para o painel botões
		painelBotoes.setLayout(new FlowLayout());

		// Adiciona botões ao painel botões
		painelBotoes.add(bNovo);
		painelBotoes.add(bSalva);
		painelBotoes.add(bConsulta);
		painelBotoes.add(bAltera);
		painelBotoes.add(bExclui);

    	// Adiciona os paineis e o menu à janela Agenda
		janela.add(painelEndereco);
		janela.add(painelBotoes);
		janela.setMenuBar(mb);

		// Desabilita botões XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
		/*bNovo.setEnabled(false);
		bSalva.setEnabled(false);
		bConsulta.setEnabled(false);
		bExclui.setEnabled(false);
		bAltera.setEnabled(false);*/
	}//FINAL DO MÉTODO CONSTRUTOR

	//METODOS ACESSORES (Permitem adicionar valores para os campos)
	public void setCodigo(String codigo){
		tCodigo.setText(codigo);
	}
	public void setNome(String nome){
		tNome.setText(nome);
	}
	public void setEndereco(String endereco){
		tEndereco.setText(endereco);
	}
	public void setFone(String fone){
		tFone.setText(fone);
	}
	public void setCelular(String celular){
		tCelular.setText(celular);
	}
	public void setObs(String Obs){
		tObs.setText(Obs);
	}
	public void setSexo(String sexo) {
		if (sexo != null)	{
			if (sexo.equals("F")) feminino.setState(true);
			else if (sexo.equals("M")) masculino.setState(true);
		}
	}
	public void setMenuBar(MenuBar mb)	{
		janela.setMenuBar(mb);
	}

	//METODOS MUTADORES (Permitem receber o conteúdo dos campos)
	public String getCodigo(){
		return tCodigo.getText();
	}
	public String getNome(){
		return tNome.getText();
	}
	public String getEndereco(){
		return tEndereco.getText();
	}
	public String getFone(){
		return tFone.getText();
	}
	public String getCelular(){
		return tCelular.getText();
	}
	public String getObs(){
		return tObs.getText();
	}
	public String getSexo() {
		if (masculino.getState() == true) return "M";
		else if (feminino.getState() == true) return "F";
		else return " ";
	}
	public MenuBar getMenuBar()	{
		return janela.getMenuBar();
	}

	// Método para tornar visíveis os paineis Endereco e Botoes
	public void mostraPainel()	{
		painelEndereco.setSize(350,234);  // Tamanho do painel
		painelEndereco.setLocation(10,80);  // Posição do painel
		painelBotoes.setSize(350,34);
		painelBotoes.setLocation(10,344);
		janela.show();   // Reorganiza a janela Agenda
	}

	// Método para detectar se determinada estrutura recebeu o foco
	public void focusGained(FocusEvent e){

	}

	// Método para detectar se determinada estrutura perdeu o foco
	public void focusLost(FocusEvent e)	{
		/* if(getCodigo().length()==0)	// Verifica se o campo codigo está em branco
			tCodigo.requestFocus(); // Devolve o foco para o campo tCodigo
		else	{
			// Habilita botões
			bNovo.setEnabled(true);
			if(getNome().length()>0)
				bSalva.setEnabled(true);
		}*/
	}

	// Método para detectar que ações foram executadas
	public void actionPerformed(ActionEvent e)	{
		if (e.getSource().equals(mi11))  // Se a ação foi clicar em 'Cadastro'
		{                                // no Menu Agenda
			this.mostraPainel();
			return;
		}

		if (e.getSource().equals(mi12))  // Se a ação foi clicar em 'Sair' no
		{                                // Menu Agenda
			System.exit(0);
		}

		// Determina a ação correspondente a cada botão quando clicado.
		Button b=(Button)e.getSource();
		if (b==bNovo)          this.botaoNovo();
		else if (b==bSalva)	   this.botaoSalva();
		else if (b==bConsulta)   this.botaoConsulta();
		else if (b==bExclui) this.botaoExclui();
		else if (b==bAltera)  this.botaoAltera();
	}

//----------------------Seção de acesso oa banco de dados ----------------
	java.sql.Connection conecta()
	{
	//	String url="jdbc:odbc:banco";
		String url="jdbc:mysql://localhost/corridas";
		java.sql.Connection con;

		try{
		//	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(url,"root","");
			return con;
			}
			catch(ClassNotFoundException cnf){
				System.out.println("Houve um erro no DRIVER: classnotfoundexcepition-"+cnf);
				return null;
			}
			catch(SQLException sql){
						System.out.println("Houve um erro no SQL:sqlexception sql-"+sql);
						return null;
			}
	}

	//Método do botão Salva
	public void botaoSalva()
	{
                tCodigo.setEnabled(true);
		java.sql.Connection con=conecta();
		//con=conecta();
		try
		{
			//Cria um Stantement para acesso ao banco
			Statement st=con.createStatement();

                        //executa um cmd SQL para inserir os dados na tabela
			int resultado=st.executeUpdate("insert into agenda (Nome, Endereco, Fone, Celular, Sexo, Obs) values('"+getNome()+"','"+getEndereco()+"','"+getFone()+"','"+getCelular()+"','"+getSexo()+"','"+getObs()+"')");
			//System.out.println("insert into Agenda values('"+getCodigo()+"','"+getNome()+"','"+getEndereco()+"','"+getFone()+"','"+getCelular()+"','"+getSexo()+"','"+getObs()+"');");
			//fechar cnx

			st.close();
			//rs.close();
			con.close();

			System.out.println("Registro salvo.");
                        this.limpaDados();
		}

		catch(SQLException sql){

			System.out.println("Nao salvo.");
		}
	}
	
	//Método do botão Exclui.
	void botaoExclui() {

		java.sql.Connection con=conecta();
		con=conecta();
		try
		{
			String cod=getCodigo();
			Statement st=con.createStatement();
			int resultado=st.executeUpdate("Delete from Agenda where codigo='"+cod+"';");
			con.close();
			this.limpaDados();
			tCodigo.requestFocus();
			System.out.println("registro Excluido");
		}
		catch(SQLException sql)
		{
			System.out.println("registro Nao Excluido");
		}		
	}        

	//Método do botão Novo
	void botaoNovo() {
		this.limpaDados();
                
		bNovo.setEnabled(true);
		bSalva.setEnabled(true);
		bConsulta.setEnabled(true);
		bExclui.setEnabled(true);
		bAltera.setEnabled(true);
                
                tCodigo.setEnabled(false);
                tNome.setEnabled(true);
                tEndereco.setEnabled(true);
                tFone.setEnabled(true);
                tCelular.setEnabled(true);
                tObs.setEnabled(true);
                masculino.setEnabled(true);
                feminino.setEnabled(true);
                
		tNome.requestFocus();
	}
/////////////////////////////////////////////////////////
	//Método do botão Consulta
	void botaoConsulta()
	{

		java.sql.Connection con=conecta();
		con=conecta();
             
               
		try
		{
                        
			String cod=getCodigo();
                        String nome=getNome();
			Statement st=con.createStatement();
			//ResultSet rs=st.executeQuery("Select * from Agenda where codigo ='"+cod+"';");
                        ResultSet rs=st.executeQuery("Select * from Agenda where Nome like '%"+nome+"%';");

			while(rs.next())
			{
				this.setCodigo(rs.getString(1));
				this.setNome(rs.getString(2));
				this.setEndereco(rs.getString(3));
				this.setFone(rs.getString(4));
				this.setCelular(rs.getString(5));
				this.setSexo(rs.getString(6));
				this.setObs(rs.getString(7));
				System.out.println("Registro  encontrado");
			}
			con.close();
		}
		catch(SQLException sql)
		{
			System.out.println("Registro não encontrado");
		}
	}
        
        
	//Código do botão próximo.
	void botaoAltera()
	{
		Connection con=conecta();

		try
		{
                    try (Statement st = con.createStatement()) {
                        int resultado=st.executeUpdate("Update Agenda set nome='"+getNome()+"',endereco='"+getEndereco()+"',fone='"+getFone()+"',celular='"+getCelular()+"',sexo='"+getSexo()+"',obs='"+getObs()+"'where codigo='"+getCodigo()+"';");
                    }
			con.close();
			System.out.println("Registro Alterado");
			this.limpaDados();
			tNome.requestFocus();
			bSalva.setEnabled(false);
		}
		catch(SQLException sql)
		{
			System.out.println("Registro Não Alterado");
		}
	}     
        
	//Método para limpar o conteúdo do campos
	public void limpaDados()	{
		this.setCodigo("");   // move vazio para os campos de texto
		this.setNome("");
		this.setEndereco("");
		this.setFone("");
		this.setCelular("");
		this.setObs("");
	}

	//Obtem os dados do objeto contato e mostra-os nos seus respectivos
	//componentes visuais.
	public void obterDadosContatos(int pos) {
		//cria um objeto para receber o conteudo na posicao do vetor
		Contato contatoAtual=(Contato)vContatos.elementAt(pos);
		//Utiliza o metodo getCodigo do objeto e devolve para o método setCodigo do componente
		this.setCodigo(contatoAtual.getCodigo());
		this.setNome(contatoAtual.getNome());
		this.setEndereco(contatoAtual.getEndereco());
		this.setFone(contatoAtual.getFone());
		this.setCelular(contatoAtual.getCelular());
		this.setSexo(contatoAtual.getSexo());
		this.setObs(contatoAtual.getObs());
	}

    // Define ações a serem executadas quando o for pressionado o botão
    // fechar da janela Agenda
	public void windowClosing(WindowEvent e) {
		System.exit(0); // Sai do sistema
	}

	// Cria método para tornar a janela Agenda visível
	public void mostraAgenda(){
		janela.setVisible(true);
	}    
    public static void main(String[] args) {
                Agenda agenda = new Agenda();
		agenda.mostraAgenda(); }}

