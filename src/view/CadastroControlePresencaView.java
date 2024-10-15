package view;

import java.awt.EventQueue;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

import api.Aluno;
import api.ControlePresenca;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CadastroControlePresencaView extends JFrame {

	@SuppressWarnings("unchecked")
	private List<ControlePresenca> listaControlesPresenca = (List<ControlePresenca>) desserializarListaControlesPresenca();
	@SuppressWarnings("unchecked")
	private List<Aluno> listaAlunos = (List<Aluno>) desserializarListaAlunos();

	private JPanel contentPane;
	private JTextField txtDataRegistro;
	private JList<String> jListAlunos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroControlePresencaView frame = new CadastroControlePresencaView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void serializarListaControlesPresenca() {
		try {
			// Salva os dados da lista de alunos
			File arquivo = new File("listaControlesPresenca.ser");
			FileOutputStream file = new FileOutputStream(arquivo);
			ObjectOutputStream out = new ObjectOutputStream(file);

			out.writeObject(listaControlesPresenca);

			out.close();
			file.close();

			System.out.println("Objeto foi serializado.");

		} catch (IOException e) {
			System.out.println("Ocorreu um erro ao serializar o objeto: " + e);

			JOptionPane.showMessageDialog(null, "Ocorreu um erro ao cadastrar o controle de presenca");
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

	public void adicionarListaPresenca() {
		try {
			String dataRegistro = txtDataRegistro.getText();
			List<String> alunosSelecionados = jListAlunos.getSelectedValuesList();

			List<Aluno> alunosPresentes = listaAlunos.stream().filter(e -> alunosSelecionados.contains(e.getNome()))
					.collect(Collectors.toList());
			
			ControlePresenca novoControlePresenca = new ControlePresenca(dataRegistro, alunosPresentes);
			
			listaControlesPresenca.add(novoControlePresenca);
			
			serializarListaControlesPresenca();
			
			dispose();
		} catch (Exception e) {
			System.out.println("Ocorreu um erro ao criar o objeto ControlePresenca: " + e);
			JOptionPane.showMessageDialog(null,
					"Ocorreu um erro ao cadastrar o Controle de Presenca: " + e.getMessage());
		}
	}
	
	public void criarModeloListaAlunos() {
		List<String> values = listaAlunos.stream().map(e -> e.getNome()).collect(Collectors.toList());

		DefaultListModel<String> listModel = new DefaultListModel<>();
		listModel.addAll(values);
		jListAlunos.setModel(listModel);
	}

	/**
	 * Create the frame.
	 */
	public CadastroControlePresencaView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 352, 392);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[18.00][270.00,grow][grow]", "[][][][13.00][204.00][grow]"));

		JLabel lblNewLabel = new JLabel("Data do registro (dd/mm/aaaa)");
		contentPane.add(lblNewLabel, "cell 1 1");

		txtDataRegistro = new JTextField();
		contentPane.add(txtDataRegistro, "cell 1 2,growx");
		txtDataRegistro.setColumns(10);

		jListAlunos = new JList();
		jListAlunos.setBorder(new LineBorder(new Color(192, 192, 192)));
		contentPane.add(jListAlunos, "cell 1 4,grow");
		criarModeloListaAlunos();

		JButton btnSalvarControlePresenca = new JButton("Salvar");
		btnSalvarControlePresenca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarListaPresenca();
			}
		});
		contentPane.add(btnSalvarControlePresenca, "cell 1 5,alignx right");
	}

}
