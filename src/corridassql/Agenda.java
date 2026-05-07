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
	private Label lCodigo, lPiloto, lTime, lVolta01, lVolta02, lCronometro, lSoma;
	private TextField tCodigo, tNome, tTime, tVolta01, tVolta02, tSoma;
	private TextArea tObs;
	private Button bNovo, bSalva,bConsulta,bAltera,bExclui,bCrono;
	private long tempVolta01, tempVolta02;
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
		m1 = new Menu("Corrida");
		mi11 = new MenuItem("Crud");
		mi12 = new MenuItem("Sair");
		m1.add(mi11);
		m1.addSeparator();
		m1.add(mi12);
		mb.add(m1);
		mi11.addActionListener(this);
		mi12.addActionListener(this);

		// Cria Rótulos(Label) do painel Endereço
		lSoma = new Label("Soma:");
		lCodigo = new Label("Codigo:");
		lPiloto = new Label("Piloto:");
		lTime = new Label("Time:");
		lVolta01 = new Label("Volta01:");

		lVolta02 = new Label("Volta02:");

		lCronometro = new Label("000s");
		lCronometro.setFont(new Font(Font.SERIF,1,25));
		//Cria os campos de texto (TextField) do painel Endereço
		tSoma = new TextField();
		tSoma.addFocusListener(this);
			tSoma.setEnabled(false);

		tCodigo = new TextField(10);
		tCodigo.addFocusListener(this);
                tCodigo.setEnabled(false);
                
		tNome = new TextField(45);
		// Define um Listener(escutador) para controlar o foco do campo nome
		tNome.addFocusListener(this);
                tNome.setEnabled(false);
                
		tTime = new TextField(60);
                tTime.setEnabled(false);
                
		tVolta01 = new TextField(8);
                tVolta01.setEnabled(false);

                
		tVolta02 = new TextField(9);
                tVolta02.setEnabled(false);


		// Posiciona e define tamanhos para
		// Rótulos e campos de texto do painel Endereço
		lCodigo.setBounds(10,15,50,13);
		tCodigo.setBounds(70,12,50,19);
		lPiloto.setBounds(10,37,50,13);
		tNome.setBounds(70,34,240,19);
		lTime.setBounds(10,59,60,13);
		tTime.setBounds(70,56,270,19);
		lVolta01.setBounds(10,140,50,20);
		tVolta01.setBounds(60,140,86,19);
		lVolta02.setBounds(10,170,50,20);
		tVolta02.setBounds(60,170,86,19);
		lSoma.setBounds(10,200,50,20);
		tSoma.setBounds(60,200,86,19);
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
		painelEndereco.add(tTime);
		painelEndereco.add(lVolta01);
		painelEndereco.add(tVolta01);
		painelEndereco.add(lVolta02);
		painelEndereco.add(tVolta02);
		painelEndereco.add(lCronometro);
		painelEndereco.add(tObs);
		painelEndereco.add(masculino);
		painelEndereco.add(feminino);
		painelEndereco.add(tSoma);
		painelEndereco.add(lSoma);

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
		bCrono.addActionListener(this);
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
	public void setTime(String endereco){
		tTime.setText(endereco);
	}
	public void setVolta01(String fone){
		tVolta01.setText(fone);
	}
	public void setVolta02(String celular){
		tVolta02.setText(celular);
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
	public void displayCron() {
		new Thread(() -> {
			long inicio = System.currentTimeMillis() / 1000;

			while (!this.bCrono.getLabel().equals("Somar")) {
				long agora = System.currentTimeMillis() / 1000;
				long tempo = agora - inicio;

				// Atualiza a Label
				this.lCronometro.setText(tempo + " s");

				try {
					// Faz a thread "dormir" por 1 segundo para não fritar o processador
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					break;
				}
			}
		}).start(); // Inicia a thread separada
	}

	public boolean cron(){

		if(this.bCrono.getLabel().equals("START")) {
			displayCron();
			this.tempVolta01 = System.currentTimeMillis();
			this.bCrono.setLabel("Volta 01");
			return false;
		}

		if(this.bCrono.getLabel().equals("Volta 01")) {
			this.tempVolta02 = System.currentTimeMillis();
			this.tempVolta01 = System.currentTimeMillis() - this.tempVolta01;
			tVolta01.setText(Long.toString(this.tempVolta01 / 1000)+" s");
			this.bCrono.setLabel("Volta 02");
			return false;
		}
		if(this.bCrono.getLabel().equals("Volta 02")) {
			this.tempVolta02 = System.currentTimeMillis() - this.tempVolta02;
			tVolta02.setText(Long.toString(this.tempVolta02 / 1000)+" s");
			this.bCrono.setLabel("Somar");
			return false;
		}
		if(this.bCrono.getLabel().equals("Somar")){
			tSoma.setText(Long.toString((this.tempVolta01 + this.tempVolta02)/1000)+" s");
			return false;
		}

		return false;
	}
	public void setMenuBar(MenuBar mb)	{
		janela.setMenuBar(mb);
	}

	//METODOS MUTADORES (Permitem receber o conteúdo dos campos)
	public int getCodigo(){
		if(!tCodigo.getText().equals(""))
			return Integer.parseInt(tCodigo.getText());
		return 0;
	}
	public String getNome(){
		return tNome.getText();
	}
	public String getTime(){
		return tTime.getText();
	}
	public String getVolta01(){
		return tVolta01.getText();
	}
	public String getVolta02(){
		return tVolta02.getText();
	}
	public String getObs(){
		return tObs.getText();
	}
	public String getSexo() {
		if (masculino.getState() == true) return "M";
		else if (feminino.getState() == true) return "F";
		else return " ";
	}
	public String getSoma() {return tSoma.getText();}
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
		else if (b==bCrono)	  this.cron();
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

		java.sql.Connection con=conecta();
		//con=conecta();
		try
		{
			//Cria um Stantement para acesso ao banco
			Statement st=con.createStatement();

                        //executa um cmd SQL para inserir os dados na tabela
			int resultado=st.executeUpdate("insert into resultados (piloto, time, prim_volta, seg_volta, soma) values('"+getNome()+"','"+ getTime()+"','"+ getVolta01()+"','"+ getVolta02()+"','"+getSoma()+"')");
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
			int cod=getCodigo();
			Statement st=con.createStatement();
			int resultado=st.executeUpdate("Delete from resultados where id='"+cod+"';");
			con.close();
			this.limpaDados();
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


                
                tCodigo.setEnabled(true);
                tNome.setEnabled(true);
                tTime.setEnabled(true);
                //tVolta01.setEnabled(true);
                //tVolta02.setEnabled(true);
                tObs.setEnabled(true);
                masculino.setEnabled(true);
                feminino.setEnabled(true);
				lCronometro.setText("000s");
                
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
                        
			int cod=getCodigo();
                        int id=getCodigo();
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("Select * from resultados where id ='"+cod+"';");
                        //ResultSet rs=st.executeQuery("Select * from Agenda where Nome like '%"+nome+"%';");

			while(rs.next())
			{
				this.setCodigo(Integer.toString(rs.getInt(1)));
				this.setNome(rs.getString(2));
				this.setTime(rs.getString(3));
				this.setVolta01(rs.getString(4));
				this.setVolta02(rs.getString(5));
				tSoma.setText(rs.getString(6));
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
                        int resultado=st.executeUpdate("Update resultados set nome='"+getNome()+"',time='"+ getTime()+"',prim_volta='"+ getVolta01()+"',seg_volta='"+ getVolta02()+"',soma='"+tSoma.getText()+"' where id='"+getCodigo()+"';");
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
		this.setTime("");
		this.setVolta01("");
		this.setVolta02("");
		this.setObs("");
		tSoma.setText("");
		lCronometro.setText("0s");
	}

	//Obtem os dados do objeto contato e mostra-os nos seus respectivos
	//componentes visuais.
	public void obterDadosContatos(int pos) {
		//cria um objeto para receber o conteudo na posicao do vetor
		Contato contatoAtual=(Contato)vContatos.elementAt(pos);
		//Utiliza o metodo getCodigo do objeto e devolve para o método setCodigo do componente
		this.setCodigo(contatoAtual.getCodigo());
		this.setNome(contatoAtual.getNome());
		this.setTime(contatoAtual.getEndereco());
		this.setVolta01(contatoAtual.getFone());
		this.setVolta02(contatoAtual.getCelular());
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

