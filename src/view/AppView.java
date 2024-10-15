package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

import api.Aluno;
import api.ControlePresenca;
import api.Exercicio;
import api.Instrutor;
import api.Plano;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;

import org.json.simple.*;
import org.json.simple.parser.*;

public class AppView extends JFrame {

	@SuppressWarnings("unchecked")
	private List<Aluno> listaAlunos = (List<Aluno>) desserializarListaAlunos();
	@SuppressWarnings("unchecked")
	private List<Instrutor> listaInstrutores = (List<Instrutor>) desserializarListaInstrutores();
	@SuppressWarnings("unchecked")
	private List<Plano> listaPlanos = (List<Plano>) desserializarListaPlanos();
	@SuppressWarnings("unchecked")
	private List<ControlePresenca> listaControlePresenca = (List<ControlePresenca>) desserializarListaControlesPresenca();

	private JFrame frame;
	private JTextField txtBuscaPessoas;
	private JList<String> jListPessoas;
	private JRadioButton rdbAlunos;
	private JRadioButton rdbInstrutores;
	private JTextField txtBuscaPlanos;
	private JList<String> jListPlanos;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JList<String> jListControlesPresenca;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppView window = new AppView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void serializarListaAlunos() {
		try {
			// Salva os dados da lista de alunos
			File arquivo = new File("listaAlunos.ser");
			FileOutputStream file = new FileOutputStream(arquivo);
			ObjectOutputStream out = new ObjectOutputStream(file);

			out.writeObject(listaAlunos);

			out.close();
			file.close();

			System.out.println("Objeto foi serializado.");

		} catch (IOException e) {
			System.out.println("Ocorreu um erro ao serializar o objeto: " + e);

			JOptionPane.showMessageDialog(null, "Ocorreu um erro ao cadastrar o aluno");
		}

	}

	public List<?> desserializarListaAlunos() {
		try {
			// Busca o arquivo que foi serializado acima
			File arquivo = new File("listaAlunos.ser");

			FileInputStream file = new FileInputStream(arquivo);
			ObjectInputStream input = new ObjectInputStream(file);

			// Lê o arquivo e converte para uma lista que não tem tipo
			List<?> pessoasDesserializadas = (List<?>) input.readObject();

			// PRINT DE TODAS AS PESSOAS DESSERIALIZADAS
			for (Object pessoa : pessoasDesserializadas) {
				System.out.println("DESSERIALIZADO: " + pessoa);
			}

			input.close();
			file.close();

			return pessoasDesserializadas;

		} catch (IOException e) {
			System.out.println("Ocorreu um erro ao desserializar o objeto: " + e);
			return new ArrayList<>(); // Retorna uma lista vazia no caso de erro
		} catch (ClassNotFoundException e) {
			System.out.println("Classe do objeto serializado não encontrada: " + e);
			return new ArrayList<>(); // Retorna uma lista vazia no caso de erro
		}
	}
	
	public void serializarListaInstrutores() {
		try {
			// Salva os dados da lista de Instrutores
			File arquivo = new File("listaInstrutores.ser");
			FileOutputStream file = new FileOutputStream(arquivo);
			ObjectOutputStream out = new ObjectOutputStream(file);

			out.writeObject(listaInstrutores);

			out.close();
			file.close();

			System.out.println("Objeto foi serializado.");

		} catch (IOException e) {
			System.out.println("Ocorreu um erro ao serializar o objeto: " + e);
			
			JOptionPane.showMessageDialog(null, "Ocorreu um erro ao cadastrar o instrutor");
		}
	}

	public List<?> desserializarListaInstrutores() {
		try {
			// Busca o arquivo que foi serializado acima
			File arquivo = new File("listaInstrutores.ser");

			FileInputStream file = new FileInputStream(arquivo);
			ObjectInputStream input = new ObjectInputStream(file);

			// Lê o arquivo e converte para uma lista que não tem tipo
			List<?> pessoasDesserializadas = (List<?>) input.readObject();

			// PRINT DE TODAS AS PESSOAS DESSERIALIZADAS
			for (Object pessoa : pessoasDesserializadas) {
				System.out.println("DESSERIALIZADO: " + pessoa);
			}

			input.close();
			file.close();

			return pessoasDesserializadas;

		} catch (IOException e) {
			System.out.println("Ocorreu um erro ao desserializar o objeto: " + e);
			return new ArrayList<>(); // Retorna uma lista vazia no caso de erro
		} catch (ClassNotFoundException e) {
			System.out.println("Classe do objeto serializado não encontrada: " + e);
			return new ArrayList<>(); // Retorna uma lista vazia no caso de erro
		}
	}

	public List<?> desserializarListaPlanos() {
		try {
			// Busca o arquivo que foi serializado acima
			File arquivo = new File("listaPlanos.ser");

			FileInputStream file = new FileInputStream(arquivo);
			ObjectInputStream input = new ObjectInputStream(file);

			// Lê o arquivo e converte para uma lista que não tem tipo
			List<?> planosDesserializados = (List<?>) input.readObject();

			// PRINT DE TODAS AS PESSOAS DESSERIALIZADAS
			for (Object plano : planosDesserializados) {
				System.out.println("DESSERIALIZADO: " + plano);
			}

			input.close();
			file.close();

			return planosDesserializados;

		} catch (IOException e) {
			System.out.println("Ocorreu um erro ao desserializar o objeto: " + e);
			return new ArrayList<>(); // Retorna uma lista vazia no caso de erro
		} catch (ClassNotFoundException e) {
			System.out.println("Classe do objeto serializado não encontrada: " + e);
			return new ArrayList<>(); // Retorna uma lista vazia no caso de erro
		}
	}

	public List<?> desserializarListaControlesPresenca() {
		try {
			// Busca o arquivo que foi serializado acima
			File arquivo = new File("listaControlesPresenca.ser");

			FileInputStream file = new FileInputStream(arquivo);
			ObjectInputStream input = new ObjectInputStream(file);

			// Lê o arquivo e converte para uma lista que não tem tipo
			List<?> controlesPresencaDesserializados = (List<?>) input.readObject();

			// PRINT DE TODAS AS PESSOAS DESSERIALIZADAS
			for (Object controlePresenca : controlesPresencaDesserializados) {
				System.out.println("DESSERIALIZADO: " + controlePresenca);
			}

			input.close();
			file.close();

			return controlesPresencaDesserializados;

		} catch (IOException e) {
			System.out.println("Ocorreu um erro ao desserializar o objeto: " + e);
			return new ArrayList<>(); // Retorna uma lista vazia no caso de erro
		} catch (ClassNotFoundException e) {
			System.out.println("Classe do objeto serializado não encontrada: " + e);
			return new ArrayList<>(); // Retorna uma lista vazia no caso de erro
		}
	}

	public void importarPessoasJSON(File arquivoSelecionado) {
		JSONParser parser = new JSONParser();
		try {
			FileReader reader = new FileReader(arquivoSelecionado);
			Object object = parser.parse(reader);
			JSONObject jsonObject = (JSONObject) object;
			
			// Adiciona os alunos importados
			JSONArray arrayAlunos = (JSONArray) jsonObject.get("alunos");
			for (Object item : arrayAlunos) {
				JSONObject itemConvertido = (JSONObject) item;
				
				String nome = (String) itemConvertido.get("nome");
				String dataNascimento = (String) itemConvertido.get("dataNascimento");
				String cpf = (String) itemConvertido.get("cpf");
				String email = (String) itemConvertido.get("email");
				String nrTelefone = (String) itemConvertido.get("nrTelefone");
				double peso = Double.parseDouble((String) itemConvertido.get("peso"));
				double altura = Double.parseDouble((String) itemConvertido.get("altura"));
				String objetivoTreinamento = (String) itemConvertido.get("objetivoTreinamento");
				
				String planoSelecionado = (String) itemConvertido.get("plano");
				Plano planoAluno = listaPlanos.stream().filter(e -> e.getNomePlano().equals(planoSelecionado)).findFirst().get();

				Aluno novoAluno = new Aluno(nome, dataNascimento, cpf, email, nrTelefone, peso, altura,
						objetivoTreinamento, planoAluno);
				
				// Adiciona o novo aluno na lista e chama o metodo p salvar a lista
				listaAlunos.add(novoAluno);
			}
			
			// Adiciona os instrutores importados
			JSONArray arrayInstrutores = (JSONArray) jsonObject.get("instrutores");
			for (Object item : arrayInstrutores) {
				JSONObject itemConvertido = (JSONObject) item;
				
				String nome = (String) itemConvertido.get("nome");
				String dataNascimento = (String) itemConvertido.get("dataNascimento");
				String cpf = (String) itemConvertido.get("cpf");
				String email = (String) itemConvertido.get("email");
				String nrTelefone = (String) itemConvertido.get("nrTelefone");
				String areaAtuacao = (String) itemConvertido.get("areaAtuacao");


				Instrutor novoInstrutor = new Instrutor(nome, dataNascimento, cpf, email, nrTelefone, areaAtuacao);
				
				// Adiciona o novo instrutor na lista e chama o metodo p salvar a lista
				listaInstrutores.add(novoInstrutor);
			}
			
			serializarListaAlunos();
			serializarListaInstrutores();
			
		} catch (IOException e) {
			System.out.println("Ocorreu um erro ao ler o arquivo: " + e);
		} catch (ParseException  e) {
			System.out.println("Erro ao importar o arquivo JSON: " + e);
		}
	}

	public void criarModeloListaAlunosOuInstrutores(char tipoLista, String filtroBusca) {
		List<String> values = tipoLista == 'A' ? listaAlunos.stream().map(e -> e.getNome()).collect(Collectors.toList())
				: listaInstrutores.stream().map(e -> e.getNome()).collect(Collectors.toList());

		DefaultListModel<String> listModel = new DefaultListModel<>();
		listModel.addAll(values);

		if (!filtroBusca.isEmpty()) {
			for (int i = 0; i < listModel.getSize(); i++) {
				String item = listModel.elementAt(i);
				if (!item.contains(filtroBusca)) {
					listModel.remove(i);
					i--;
				}
			}
		}

		jListPessoas.setModel(listModel);
	}

	public void criarModeloListaPlanos(String filtroBusca) {
		List<String> values = listaPlanos.stream().map(e -> e.getNomePlano()).collect(Collectors.toList());

		DefaultListModel<String> listModel = new DefaultListModel<>();
		listModel.addAll(values);

		if (!filtroBusca.isEmpty()) {
			for (int i = 0; i < listModel.getSize(); i++) {
				String item = listModel.elementAt(i);
				if (!item.contains(filtroBusca)) {
					listModel.remove(i);
					i--;
				}
			}
		}

		jListPlanos.setModel(listModel);
	}

	public void criarModeloListaControlesPresenca() {
		List<String> values = listaControlePresenca.stream().map(e -> e.getDataRegistro()).collect(Collectors.toList());

		DefaultListModel<String> listModel = new DefaultListModel<>();
		listModel.addAll(values);
		jListControlesPresenca.setModel(listModel);
	}

	public void listarInformacoesPessoa() {
		List<String> pessoaSelecionada = jListPessoas.getSelectedValuesList();
		String construtorStringInformacoes;
		// Faz as validacoes de erro na selecao
		if (pessoaSelecionada.size() == 0) {
			JOptionPane.showMessageDialog(null, "Nenhuma pessoa selecionada");
			return;
		} else if (pessoaSelecionada.size() > 1) {
			JOptionPane.showMessageDialog(null, "Selecione apenas uma pessoa.");
			return;
		}

		if (rdbAlunos.isSelected()) {
			Aluno alunoSelecionado = listaAlunos.stream().filter(e -> e.getNome().equals(pessoaSelecionada.get(0)))
					.findFirst().get();
			construtorStringInformacoes = "<html>" + "Informações do Aluno:<br>" + "<br>Nome: "
					+ alunoSelecionado.getNome() + "<br>Data de nascimento: "
					+ alunoSelecionado.getDataNascimento().toString() + "<br>Cpf: " + alunoSelecionado.getCpf()
					+ "<br>Email: " + alunoSelecionado.getEmail() + "<br>Número de telefone: "
					+ alunoSelecionado.getNrTelefone() + "<br>Peso: " + alunoSelecionado.getPeso() + "kg"
					+ "<br>Altura: " + alunoSelecionado.getAltura() + "m" + "<br>Objetivo do treinamento: "
					+ alunoSelecionado.getObjetivoTreinamento() + "</html>";
		} else {
			Instrutor instrutorSelecionado = listaInstrutores.stream()
					.filter(e -> e.getNome().equals(pessoaSelecionada.get(0))).findFirst().get();
			construtorStringInformacoes = "<html>" + "Informações do Instrutor:<br>" + "<br>Nome: "
					+ instrutorSelecionado.getNome() + "<br>Data de nascimento: "
					+ instrutorSelecionado.getDataNascimento().toString() + "<br>Cpf: " + instrutorSelecionado.getCpf()
					+ "<br>Email: " + instrutorSelecionado.getEmail() + "<br>Número de telefone: "
					+ instrutorSelecionado.getNrTelefone() + "<br>Área de atuação: "
					+ instrutorSelecionado.getAreaDeAtuacao() + "</html>";
		}

		JOptionPane.showMessageDialog(null, construtorStringInformacoes);
	}

	public void listarInformacoesPlano() {
		List<String> planoSelecionadoList = jListPlanos.getSelectedValuesList();
		// Faz as validacoes de erro na selecao
		if (planoSelecionadoList.size() == 0) {
			JOptionPane.showMessageDialog(null, "Nenhum plano selecionado");
			return;
		} else if (planoSelecionadoList.size() > 1) {
			JOptionPane.showMessageDialog(null, "Selecione apenas um plano.");
			return;
		}

		Plano planoSelecionado = listaPlanos.stream().filter(e -> e.getNomePlano().equals(planoSelecionadoList.get(0)))
				.findFirst().get();

		String construtorStringExercicios = "";

		for (Exercicio exercicio : planoSelecionado.getExercicios()) {
			construtorStringExercicios = construtorStringExercicios.concat("<br>=> " + exercicio.getNomeExercicio()
					+ "<br>---- Quantidade de repetições: " + exercicio.getQntRepeticoes()
					+ "<br>---- Número de séries: " + exercicio.getNrSeries() + "<br>");
		}

		String construtorStringInformacoes = "<html>" + "Informações do Plano:<br>" + "<br>Nome: "
				+ planoSelecionado.getNomePlano() + "<br>Preço: R$" + planoSelecionado.getPrecoPlano()
				+ "<br>Descrição: " + planoSelecionado.getDescricao() + "<br><br>Exercícios:"
				+ construtorStringExercicios + "</html>";

		JOptionPane.showMessageDialog(null, construtorStringInformacoes);
	}

	public void listarInformacoesControlePresenca() {
		List<String> controlePresencaSelecionadoList = jListControlesPresenca.getSelectedValuesList();
		// Faz as validacoes de erro na selecao
		if (controlePresencaSelecionadoList.size() == 0) {
			JOptionPane.showMessageDialog(null, "Nenhum controle de presença selecionado");
			return;
		} else if (controlePresencaSelecionadoList.size() > 1) {
			JOptionPane.showMessageDialog(null, "Selecione apenas um controle de presença.");
			return;
		}

		ControlePresenca controlePresencaSelecionado = listaControlePresenca.stream()
				.filter(e -> e.getDataRegistro().equals(controlePresencaSelecionadoList.get(0))).findFirst().get();

		String construtorStringAlunosPresentes = "";

		for (String alunoPresente : controlePresencaSelecionado.getNomesAlunosPresentes()) {
			construtorStringAlunosPresentes = construtorStringAlunosPresentes.concat("<br>- " + alunoPresente);
		}

		String construtorStringInformacoes = "<html>" + "Controle de Presença:<br>" + "<br>Dia: "
				+ controlePresencaSelecionado.getDataRegistro() + "<br>Alunos presentes: "
				+ construtorStringAlunosPresentes + "</html>";

		JOptionPane.showMessageDialog(null, construtorStringInformacoes);
	}

	/**
	 * Create the application.
	 */
	public AppView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 442, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[grow]", "[grow]"));

		JTabbedPane tabbedPaneGeral = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPaneGeral, "cell 0 0,grow");

		JPanel panelPessoa = new JPanel();
		tabbedPaneGeral.addTab("Pessoa", null, panelPessoa, null);
		panelPessoa.setLayout(new MigLayout("", "[335.00,grow][][grow][10.00][][344.00,grow][grow][27.00]",
				"[8.00][][][][][21.00,grow][26.00,grow][][][][8]"));

		JLabel lblNewLabel = new JLabel("Digite o nome do usu\u00E1rio...");
		panelPessoa.add(lblNewLabel, "cell 0 1");

		// Cria a lista com os dados de aluno default
		jListPessoas = new JList<String>();
		criarModeloListaAlunosOuInstrutores('A', "");

		jListPessoas.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelPessoa.add(jListPessoas, "cell 5 1 1 9,grow");

		txtBuscaPessoas = new JTextField();
		panelPessoa.add(txtBuscaPessoas, "flowx,cell 0 2 2 1,growx");
		txtBuscaPessoas.setColumns(10);

		rdbAlunos = new JRadioButton("Alunos");
		// Muda a lista pra alunos quanod seleciona o radio button
		rdbAlunos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Lista mudada para Alunos");
				criarModeloListaAlunosOuInstrutores('A', "");
			}
		});
		buttonGroup.add(rdbAlunos);
		rdbAlunos.setSelected(true);
		panelPessoa.add(rdbAlunos, "cell 0 3");

		rdbInstrutores = new JRadioButton("Instrutores");
		// Muda a lista pra instrutores quando seleciona o radio button
		rdbInstrutores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Lista mudada para Instrutores");
				criarModeloListaAlunosOuInstrutores('I', "");
			}
		});
		buttonGroup.add(rdbInstrutores);
		panelPessoa.add(rdbInstrutores, "cell 0 4");

		// Botão de cadastro de Pessoa/Aluno
		JButton btnCadastrarAluno = new JButton("Cadastrar aluno");
		btnCadastrarAluno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Abre a tela de cadastro
				CadastroAlunoView viewCadastroPessoa = new CadastroAlunoView();
				viewCadastroPessoa.setVisible(true);
			}
		});
		panelPessoa.add(btnCadastrarAluno, "flowx,cell 0 6,alignx left,aligny top");

		// Botão de cadastro de instrutor
		JButton btnCadastrarInstrutor = new JButton("Cadastrar instrutor");
		btnCadastrarInstrutor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Abre tela de cadastro
				CadastroInstrutorView viewCadastroInstrutor = new CadastroInstrutorView();
				viewCadastroInstrutor.setVisible(true);
			}
		});
		panelPessoa.add(btnCadastrarInstrutor, "cell 0 6,aligny top");

		JButton btnInformacoesPessoa = new JButton("Informa\u00E7\u00F5es");
		btnInformacoesPessoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listarInformacoesPessoa();
			}
		});
		panelPessoa.add(btnInformacoesPessoa, "flowx,cell 0 7,alignx left,aligny top");

		JButton btnBuscaPessoas = new JButton("🔎");
		btnBuscaPessoas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String buscaLista = txtBuscaPessoas.getText();
				char tipoLista = rdbAlunos.isSelected() ? 'A' : 'I';
				criarModeloListaAlunosOuInstrutores(tipoLista, buscaLista);
			}
		});
		panelPessoa.add(btnBuscaPessoas, "cell 0 2");
		
				JButton btnImportarPessoas = new JButton("Importar");
				btnImportarPessoas.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JFileChooser fileChooser = new JFileChooser();
						int resultadoAcao = fileChooser.showOpenDialog(AppView.this);
						if (resultadoAcao == JFileChooser.APPROVE_OPTION) {
							File arquivoSelecionado = fileChooser.getSelectedFile();
							importarPessoasJSON(arquivoSelecionado);
						}
					}
				});
				panelPessoa.add(btnImportarPessoas, "cell 0 7");

		JPanel panelPlano = new JPanel();
		tabbedPaneGeral.addTab("Plano", null, panelPlano, null);
		panelPlano.setLayout(new MigLayout("", "[grow][][][][][][][6.00][][grow][][][48.00][][grow]",
				"[10.00][-1.00,grow][][][][][][][][grow]"));

		jListPlanos = new JList<String>();
		criarModeloListaPlanos("");

		jListPlanos.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelPlano.add(jListPlanos, "cell 5 1 9 8,grow");

		JLabel label = new JLabel("Selecione um plano...");
		panelPlano.add(label, "cell 0 2");

		txtBuscaPlanos = new JTextField();
		panelPlano.add(txtBuscaPlanos, "flowx,cell 0 3,growx");
		txtBuscaPlanos.setColumns(10);

		JButton btnCadastrarPlano = new JButton("Cadastrar");
		btnCadastrarPlano.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Abre tela de cadastro
				CadastroPlanoView viewCadastroPlano = new CadastroPlanoView();
				viewCadastroPlano.setVisible(true);
			}
		});
		panelPlano.add(btnCadastrarPlano, "flowx,cell 0 6");

		JButton btnInformacoesPlano = new JButton("Informações");
		btnInformacoesPlano.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listarInformacoesPlano();
			}
		});
		panelPlano.add(btnInformacoesPlano, "cell 0 6");

		JButton btnBuscaPlanos = new JButton("🔎");
		btnBuscaPlanos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String buscaLista = txtBuscaPlanos.getText();
				criarModeloListaPlanos(buscaLista);
			}
		});
		panelPlano.add(btnBuscaPlanos, "cell 1 3");

		JPanel panelControlePresenca = new JPanel();
		tabbedPaneGeral.addTab("Controle de Presenca", null, panelControlePresenca, null);
		panelControlePresenca
				.setLayout(new MigLayout("", "[16.00][][][][][][][][][151.00][grow]", "[][][grow][][][][]"));

		JLabel lblNewLabel_1 = new JLabel(
				"<html>\r\nSelecione uma data ou crie uma<br>\r\nnova para registrar as presenças\r\n</html>");
		panelControlePresenca.add(lblNewLabel_1, "cell 1 1 7 1");

		jListControlesPresenca = new JList();
		criarModeloListaControlesPresenca();

		jListControlesPresenca.setBorder(new LineBorder(new Color(192, 192, 192)));
		panelControlePresenca.add(jListControlesPresenca, "cell 9 1 1 5,grow");

		JButton btnNovoControlePresenca = new JButton("Novo registro");
		btnNovoControlePresenca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Abre a tela de cadastro
				CadastroControlePresencaView viewControlePresenca = new CadastroControlePresencaView();
				viewControlePresenca.setVisible(true);
			}
		});
		panelControlePresenca.add(btnNovoControlePresenca, "cell 1 3");

		JButton btnInformacoesControlePresenca = new JButton("Informações");
		btnInformacoesControlePresenca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listarInformacoesControlePresenca();
			}
		});
		panelControlePresenca.add(btnInformacoesControlePresenca, "cell 1 4");

	}

}
