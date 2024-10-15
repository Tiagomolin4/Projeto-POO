package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

import api.Aluno;
import api.Exercicio;
import api.Plano;

import java.awt.Color;
import java.awt.Font;

public class CadastroPlanoView extends JFrame {

	@SuppressWarnings("unchecked")
	private List<Plano> listaPlanos = (List<Plano>) desserializarListaPlanos();
	private List<Exercicio> listaExercicios = new ArrayList<>();
	

	private JPanel contentPane;
	private JTextField txtNomePlano;
	private JTextField txtPrecoPlano;
	private JTextField txtDescricaoPlano;
	private JList<String> jListExercicios;
	private JLabel lblNewLabel_3;
	private JTextField txtQntRepeticoes;
	private JLabel lblNewLabel_4;
	private JTextField txtNroSeries;
	private JLabel lblNewLabel_5;
	private JButton btnNewButton;
	private JLabel lblNewLabel_6;
	private JButton btnAdicionarExercicio;
	private JComboBox cmbTipoExercicio;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroPlanoView frame = new CadastroPlanoView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void serializarListaPlanos() {
		try {
			// Salva os dados da lista de planos
			File arquivo = new File("listaPlanos.ser");
			FileOutputStream file = new FileOutputStream(arquivo);
			ObjectOutputStream out = new ObjectOutputStream(file);

			out.writeObject(listaPlanos);

			out.close();
			file.close();

			System.out.println("Objeto foi serializado.");

		} catch (IOException e) {
			System.out.println("Ocorreu um erro ao serializar o objeto: " + e);
			
			JOptionPane.showMessageDialog(null, "Ocorreu um erro ao cadastrar o plano");
		}

	}
	
	public List<?> desserializarListaPlanos() {
		try {
            //Busca o arquivo que foi serializado acima
            File arquivo = new File("listaPlanos.ser");

            FileInputStream file = new FileInputStream(arquivo);
            ObjectInputStream input = new ObjectInputStream(file);

            //Lê o arquivo e converte para uma lista que não tem tipo
            List<?> planosDesserializados = (List<?>) input.readObject();

            //PRINT DE TODAS AS PESSOAS DESSERIALIZADAS
            for (Object plano : planosDesserializados) {
                System.out.println("DESSERIALIZADO: "+plano);
            }

            input.close();
            file.close();
            
        	return planosDesserializados;

        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao desserializar o objeto: "+e);
            return new ArrayList<>(); // Retorna uma lista vazia no caso de erro
        } catch (ClassNotFoundException e){
            System.out.println("Classe do objeto serializado não encontrada: "+e);
            return new ArrayList<>(); // Retorna uma lista vazia no caso de erro
        }
	}

	public void atualizarModeloListaExercicios() {
		List<String> values = listaExercicios.stream().map(e -> e.getNomeExercicio()).collect(Collectors.toList());

		DefaultListModel<String> listModel = new DefaultListModel<>();
		listModel.addAll(values);
		jListExercicios.setModel(listModel);
	}
	
	

	/**
	 * Create the frame.
	 */
	public CadastroPlanoView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 421, 471);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[35.00][37.00,grow][147.00,grow][][159.00,grow][71.00,grow]",
				"[][][][][][][][][][][][][][][][grow][][][][][][][][][][][][][][][][][][][][][][][][][][][]"));

		JLabel lblNewLabel = new JLabel("Título");
		contentPane.add(lblNewLabel, "cell 2 1");

		txtNomePlano = new JTextField();
		contentPane.add(txtNomePlano, "cell 2 2 3 1,growx");
		txtNomePlano.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Preço do plano");
		contentPane.add(lblNewLabel_1, "cell 2 4");

		txtPrecoPlano = new JTextField();
		txtPrecoPlano.setColumns(10);
		contentPane.add(txtPrecoPlano, "cell 2 5,growx");

		JLabel lblNewLabel_2 = new JLabel("Descrição");
		contentPane.add(lblNewLabel_2, "cell 2 7");

		txtDescricaoPlano = new JTextField();
		txtDescricaoPlano.setColumns(10);
		contentPane.add(txtDescricaoPlano, "cell 2 8 3 4,grow");

		lblNewLabel_6 = new JLabel("Adicionar Exercício");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblNewLabel_6, "cell 2 16");

		jListExercicios = new JList();
		jListExercicios.setBorder(new LineBorder(new Color(192, 192, 192)));
		contentPane.add(jListExercicios, "cell 4 17 1 11,grow");

		lblNewLabel_3 = new JLabel("Tipo de exercício");
		contentPane.add(lblNewLabel_3, "flowy,cell 2 19");

		lblNewLabel_4 = new JLabel("Qnt. repetições");
		contentPane.add(lblNewLabel_4, "flowy,cell 2 22");

		txtQntRepeticoes = new JTextField();
		txtQntRepeticoes.setToolTipText("");
		txtQntRepeticoes.setColumns(10);
		contentPane.add(txtQntRepeticoes, "cell 2 22");

		lblNewLabel_5 = new JLabel("Nro. séries");
		contentPane.add(lblNewLabel_5, "flowy,cell 2 25");

		txtNroSeries = new JTextField();
		txtNroSeries.setToolTipText("");
		txtNroSeries.setColumns(10);
		contentPane.add(txtNroSeries, "cell 2 25");

		btnAdicionarExercicio = new JButton(">>>");
		btnAdicionarExercicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Pega as infos do cadastro de exercicio, cria o objeto e adiciona na lista
				String tipoExercicio = (String) cmbTipoExercicio.getSelectedItem();
				int qntRepeticoes = Integer.parseInt(txtQntRepeticoes.getText());
				int nroSeries = Integer.parseInt(txtNroSeries.getText());

				Exercicio novoExercicio = new Exercicio(tipoExercicio, qntRepeticoes, nroSeries);
				listaExercicios.add(novoExercicio);
				atualizarModeloListaExercicios();
			}
		});
		contentPane.add(btnAdicionarExercicio, "cell 2 27");

		btnNewButton = new JButton("Salvar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Pega as informacoes do formulario e cadastra um Plano
				String nomePlano = txtNomePlano.getText();
				double precoPlano = Double.parseDouble(txtPrecoPlano.getText());
				String descricaoPlano = txtDescricaoPlano.getText();

				//Cria o plano, adiciona na lista e serializa ela de novo
				Plano novoPlano = new Plano(nomePlano, precoPlano, descricaoPlano, listaExercicios);
				listaPlanos.add(novoPlano);
				serializarListaPlanos();
				
				//Fecha a tela
				dispose();
			}
		});
		contentPane.add(btnNewButton, "cell 4 28,alignx right");

		cmbTipoExercicio = new JComboBox();
		cmbTipoExercicio.setModel(
				new DefaultComboBoxModel(new String[] { "Agachamento", "Levantamento terra", "Supino", "Remada",
						"Flexões", "Rosca direta", "Tríceps mergulho", "Avanço", "Elevação de panturrilha", "Abdominal",
						"Prancha", "Corrida na esteira", "Bike ergométrica", "Remo ergométrico", "Alongamentos" }));
		contentPane.add(cmbTipoExercicio, "cell 2 19,growx");
	}

}
