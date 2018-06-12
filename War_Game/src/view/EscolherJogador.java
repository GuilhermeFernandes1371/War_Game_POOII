package view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EscolherJogador {

	public JFrame frame;
	private JTextField textField_Nome;
	private JComboBox comboBox_Cor;
	public boolean flag = false;
	private Color cor;
	private String nome;
	private int numeroDeJogadoresEscolhidos;
	
	public EscolherJogador(int numeroDeJogadoresEscolhidos) {
		this.numeroDeJogadoresEscolhidos = numeroDeJogadoresEscolhidos;
		initialize();
		this.inicializaCores();
		this.frame.setVisible(true);
		this.frame.setResizable(false);
		System.out.println("Gerado interface");
	}
	
	private void inicializaCores() {
		comboBox_Cor.addItem("PRETO");
		comboBox_Cor.addItem("AZUL");
		comboBox_Cor.addItem("AMARELO");
		comboBox_Cor.addItem("BRANCO");
	}
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 139);
		frame.setTitle("Escolhendo jogador " + (this.numeroDeJogadoresEscolhidos + 1));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label_Nome = new JLabel("Nome:");
		label_Nome.setFont(new Font("Tahoma", Font.BOLD, 25));
		label_Nome.setBounds(10, 11, 90, 31);
		frame.getContentPane().add(label_Nome);
		
		textField_Nome = new JTextField();
		textField_Nome.setFont(new Font("Tahoma", Font.PLAIN, 25));
		textField_Nome.setBounds(110, 11, 314, 31);
		frame.getContentPane().add(textField_Nome);
		textField_Nome.setColumns(10);
		
		JLabel label_Cor = new JLabel("Cor:");
		label_Cor.setFont(new Font("Tahoma", Font.BOLD, 25));
		label_Cor.setBounds(10, 53, 90, 31);
		frame.getContentPane().add(label_Cor);
		
		comboBox_Cor = new JComboBox();
		comboBox_Cor.setBounds(110, 53, 113, 31);
		frame.getContentPane().add(comboBox_Cor);
		
		JButton button_Continuar = new JButton("CONTINUAR");
		button_Continuar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				cor = descobreCor( (String) comboBox_Cor.getSelectedItem());
				nome = textField_Nome.getText();
				flag = true;
				
			}
		});
		button_Continuar.setBounds(233, 53, 191, 31);
		frame.getContentPane().add(button_Continuar);
	}
	
	private Color descobreCor(String corString) {
		switch (corString) {
		case "PRETO":
			return Color.BLACK;
		case "BRANCO":
			return Color.WHITE;
		case "AZUL":
			return Color.BLUE;
		case "AMARELO":
			return Color.YELLOW;
		default:
			JOptionPane.showMessageDialog(null, "ERRO");
			return null;
		}
	}

	public Color getCor() {
		return cor;
	}

	public String getNome() {
		return nome;
	}
}
