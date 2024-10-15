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

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import api.Instrutor;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CadastroInstrutorView extends JFrame {
	
	@SuppressWarnings("unchecked")
	private List<Instrutor>listaInstrutores = (List<Instrutor>) desserializarListaInstrutores();

	private JPanel contentPane;
	private JTextField txtNomeInstrutor;
	private JTextField txtDataNascimentoInstrutor;
	private JTextField txtNroTelefoneInstrutor;
	private JTextField txtCpfInstrutor;
	private JTextField txtAreaAtuacao;
	private JButton btnSalvarInstrutor;
	private JTextField txtEmailInstrutor;
	private JLabel lblNewLabel_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroInstrutorView frame = new CadastroInstrutorView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void serializarListaInstrutores(List<Instrutor> novaLista) {
		try {
			// Salva os dados da lista de Instrutores
			File arquivo = new File("listaInstrutores.ser");
			FileOutputStream file = new FileOutputStream(arquivo);
			ObjectOutputStream out = new ObjectOutputStream(file);

			out.writeObject(novaLista);

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
            //Busca o arquivo que foi serializado acima
            File arquivo = new File("listaInstrutores.ser");

            FileInputStream file = new FileInputStream(arquivo);
            ObjectInputStream input = new ObjectInputStream(file);

            //Lê o arquivo e converte para uma lista que não tem tipo
            List<?> pessoasDesserializadas = (List<?>) input.readObject();

            //PRINT DE TODAS AS PESSOAS DESSERIALIZADAS
            for (Object pessoa : pessoasDesserializadas) {
                System.out.println("DESSERIALIZADO: "+pessoa);
            }

            input.close();
            file.close();
            
        	return pessoasDesserializadas;

        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao desserializar o objeto: "+e);
            return new ArrayList<>(); // Retorna uma lista vazia no caso de erro
        } catch (ClassNotFoundException e){
            System.out.println("Classe do objeto serializado não encontrada: "+e);
            return new ArrayList<>(); // Retorna uma lista vazia no caso de erro
        }
	}

	/**
	 * Create the frame.
	 */
	public CadastroInstrutorView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 328);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[35.00][37.00,grow][147.00,grow][159.00,grow][71.00,grow]", "[][][][][][][][][][][][][][][][][][][][][][][][][][][]"));
		
		JLabel lblNewLabel = new JLabel("Nome");
		contentPane.add(lblNewLabel, "cell 2 1");
		
		txtNomeInstrutor = new JTextField();
		contentPane.add(txtNomeInstrutor, "cell 2 2 2 1,growx");
		txtNomeInstrutor.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("<html>\r\nData de Nascimento<br>\r\r\n(dd/mm/aaaa)\r\n</html>");
		contentPane.add(lblNewLabel_1, "cell 2 4");
		
		JLabel lblNewLabel_1_1 = new JLabel("Cpf");
		contentPane.add(lblNewLabel_1_1, "cell 3 4");
		
		txtDataNascimentoInstrutor = new JTextField();
		txtDataNascimentoInstrutor.setColumns(10);
		contentPane.add(txtDataNascimentoInstrutor, "cell 2 5,growx");
		
		txtCpfInstrutor = new JTextField();
		txtCpfInstrutor.setColumns(10);
		contentPane.add(txtCpfInstrutor, "cell 3 5,growx");
		
		JLabel lblNewLabel_2 = new JLabel("Nro. de Telefone");
		contentPane.add(lblNewLabel_2, "cell 2 7");
		
		JLabel lblNewLabel_4 = new JLabel("Área de Atuação");
		contentPane.add(lblNewLabel_4, "cell 3 7");
		
		txtNroTelefoneInstrutor = new JTextField();
		txtNroTelefoneInstrutor.setColumns(10);
		contentPane.add(txtNroTelefoneInstrutor, "cell 2 8,growx");
		
		txtAreaAtuacao = new JTextField();
		txtAreaAtuacao.setColumns(10);
		contentPane.add(txtAreaAtuacao, "cell 3 8,growx");
		
		lblNewLabel_3 = new JLabel("Email");
		contentPane.add(lblNewLabel_3, "cell 2 10");
		
		txtEmailInstrutor = new JTextField();
		contentPane.add(txtEmailInstrutor, "cell 2 11 2 1,growx");
		txtEmailInstrutor.setColumns(10);
		
		btnSalvarInstrutor = new JButton("Salvar");
		btnSalvarInstrutor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Pega as infos das caixas de texto e cria o objeto
				String nome = txtNomeInstrutor.getText();
				String dataNascimento = txtDataNascimentoInstrutor.getText();
				String cpf = txtCpfInstrutor.getText();
				String email = txtEmailInstrutor.getText();
				String nrTelefone = txtNroTelefoneInstrutor.getText();
				String areaAtuacao = txtAreaAtuacao.getText();

				Instrutor novoInstrutor = new Instrutor(nome, dataNascimento, cpf, email, nrTelefone, areaAtuacao);
				
				// Adiciona o novo instrutor na lista e chama o metodo p salvar a lista
				listaInstrutores.add(novoInstrutor);

				serializarListaInstrutores(listaInstrutores);
				
				// Fecha a tela depois de cadastrar
				dispose();
			}
		});
		contentPane.add(btnSalvarInstrutor, "cell 4 19,alignx right");
	}

}
