package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import api.Aluno;
import api.Plano;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JComboBox;

public class CadastroAlunoView extends JFrame {

	@SuppressWarnings("unchecked")
	private List<Aluno> listaAlunos = (List<Aluno>) desserializarListaAlunos();
	@SuppressWarnings("unchecked")
	private List<Plano> listaPlanos = (List<Plano>) desserializarListaPlanos();

	private JPanel contentPane;
	private JTextField txtNomeAluno;
	private JTextField txtDataNascimentoAluno;
	private JTextField txtNroTelefoneAluno;
	private JTextField txtObjetivoTreino;
	private JTextField txtCpfAluno;
	private JTextField txtPeso;
	private JTextField txtAltura;
	private JButton btnSalvarAluno;
	private JTextField txtEmailAluno;
	private JLabel lblEmail;
	private JComboBox<String> cmbPlanoAluno;
	private JLabel lblNewLabel_5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroAlunoView frame = new CadastroAlunoView();
					frame.setVisible(true);
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
	
	public void criarModeloComboBoxPlanos() {
		List<String> opcoesComboBoxPlano = listaPlanos.stream().map(e -> e.getNomePlano()).collect(Collectors.toList());
		
		for (String item : opcoesComboBoxPlano) {
			cmbPlanoAluno.addItem(item);
		}
	}

	public void adicionarAluno() {
		try {
			// Pega as infos das caixas de texto e cria o objeto
			String nome = txtNomeAluno.getText();
			String dataNascimento = txtDataNascimentoAluno.getText();
			String cpf = txtCpfAluno.getText();
			String email = txtEmailAluno.getText();
			String nrTelefone = txtNroTelefoneAluno.getText();
			double peso = Double.parseDouble(txtPeso.getText());
			double altura = Double.parseDouble(txtAltura.getText());
			String objetivoTreinamento = txtObjetivoTreino.getText();
			
			String planoSelecionado = (String) cmbPlanoAluno.getSelectedItem();
			Plano planoAluno = listaPlanos.stream().filter(e -> e.getNomePlano().equals(planoSelecionado)).findFirst().get();

			Aluno novoAluno = new Aluno(nome, dataNascimento, cpf, email, nrTelefone, peso, altura,
					objetivoTreinamento, planoAluno);
			
			// Adiciona o novo aluno na lista e chama o metodo p salvar a lista
			listaAlunos.add(novoAluno);

			serializarListaAlunos();
			
			// Fecha a tela depois de cadastrar
			dispose();
		} catch (Exception e) {
			System.out.println("Ocorreu um erro ao criar o objeto Aluno: " + e);
			JOptionPane.showMessageDialog(null, "Ocorreu um erro ao cadastrar o aluno: " + e.getMessage());
		}
	}

	/**
	 * Create the frame.
	 */
	public CadastroAlunoView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 403);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[35.00][37.00,grow][147.00,grow][159.00,grow][71.00,grow]", "[][][][][][][][][][][][][][][][][][][][][][][][][][][][]"));

		JLabel lblNewLabel = new JLabel("Nome");
		contentPane.add(lblNewLabel, "cell 2 1");

		txtNomeAluno = new JTextField();
		contentPane.add(txtNomeAluno, "cell 2 2 2 1,growx");
		txtNomeAluno.setColumns(10);

		lblEmail = new JLabel("Email");
		contentPane.add(lblEmail, "cell 2 4");

		txtEmailAluno = new JTextField();
		txtEmailAluno.setColumns(10);
		contentPane.add(txtEmailAluno, "cell 2 5 2 1,growx");

		JLabel lblNewLabel_1 = new JLabel("<html>\r\nData de Nascimento<br>\r\r\n(dd/mm/aaaa)\r\n</html>");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(lblNewLabel_1, "cell 2 7");

		JLabel lblNewLabel_1_1 = new JLabel("Cpf");
		contentPane.add(lblNewLabel_1_1, "cell 3 7");

		txtDataNascimentoAluno = new JTextField();
		txtDataNascimentoAluno.setColumns(10);
		contentPane.add(txtDataNascimentoAluno, "cell 2 8,growx");

		txtCpfAluno = new JTextField();
		txtCpfAluno.setColumns(10);
		contentPane.add(txtCpfAluno, "cell 3 8,growx");

		JLabel lblNewLabel_2 = new JLabel("Nro. de Telefone");
		contentPane.add(lblNewLabel_2, "cell 2 10");
		
		lblNewLabel_5 = new JLabel("Plano");
		contentPane.add(lblNewLabel_5, "cell 3 10");

		txtNroTelefoneAluno = new JTextField();
		txtNroTelefoneAluno.setColumns(10);
		contentPane.add(txtNroTelefoneAluno, "cell 2 11,growx");
		
		cmbPlanoAluno = new JComboBox();
		contentPane.add(cmbPlanoAluno, "cell 3 11,growx");
		criarModeloComboBoxPlanos();

		JLabel lblNewLabel_4 = new JLabel("Peso");
		contentPane.add(lblNewLabel_4, "cell 2 13");

		JLabel lblNewLabel_4_1 = new JLabel("Altura");
		contentPane.add(lblNewLabel_4_1, "cell 3 13");

		txtPeso = new JTextField();
		txtPeso.setColumns(10);
		contentPane.add(txtPeso, "cell 2 14,growx");

		txtAltura = new JTextField();
		txtAltura.setColumns(10);
		contentPane.add(txtAltura, "cell 3 14,growx");

		JLabel lblNewLabel_3 = new JLabel("Qual o objetivo do treinamento?");
		contentPane.add(lblNewLabel_3, "cell 2 15 2 1");

		txtObjetivoTreino = new JTextField();
		txtObjetivoTreino.setColumns(10);
		contentPane.add(txtObjetivoTreino, "cell 2 16 2 8,grow");

		// Botão de salvamento do cadastro de aluno
		btnSalvarAluno = new JButton("Salvar");
		btnSalvarAluno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarAluno();
			}
		});
		contentPane.add(btnSalvarAluno, "cell 3 26 2 1,alignx right");
	}

}
