package view;

import java.util.List;

import javax.swing.JFrame;

import model.master.Jogador;
import model.militares.Militar;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SelecionarMilitar {

	public JFrame frame;
	private String stringLabel;
	public boolean flag = false;
	public boolean cancelado = false;
	private List<Militar> listaMilitar;
	private JComboBox comboBox;
	private Jogador jogador;
	private int unidadesSelecionadas = -1;
	
	public SelecionarMilitar(List<Militar> listaMilitar , String stringLabel , Jogador jogador) {
		this.stringLabel = stringLabel;
		this.jogador = jogador;
		this.listaMilitar = listaMilitar;
		initialize();
		this.preencheComboBox();
		frame.setVisible(true);
	}
	
	public SelecionarMilitar(List<Militar> listaMilitar , String stringLabel , Jogador jogador , int unidadeSelecionadas) {
		this.unidadesSelecionadas = unidadeSelecionadas;
		this.stringLabel = stringLabel;
		this.jogador = jogador;
		this.listaMilitar = listaMilitar;
		initialize();
		this.preencheComboBox();
		frame.setVisible(true);
	}
	
	private void preencheComboBox() {
		for (Militar militar : listaMilitar) {
			comboBox.addItem(militar);
		}
	}
	
	public Militar getMilitarEscolhido() {
		return (Militar) comboBox.getSelectedItem();
	}
	
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Turno do Jogador: " + this.jogador.getNome());
		frame.setBounds(100, 100, 714, 140);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel(stringLabel);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.BOLD, 25));
		label.setBounds(10, 11, 700, 31);
		frame.getContentPane().add(label);
		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 25));
		comboBox.setBounds(20, 53, 220, 36);
		frame.getContentPane().add(comboBox);
		
		JButton button = new JButton("Selecionar");
		button.setFont(new Font("Tahoma", Font.PLAIN, 25));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				flag = true;
			
			}
		});
		button.setBounds(451, 53, 227, 36);
		button.setForeground(this.jogador.getCorComplementar());
		button.setBackground(this.jogador.getCor());
		frame.getContentPane().add(button);
		
		JButton buttonCancel = new JButton("Cancelar");
		buttonCancel.setFont(new Font("Tahoma", Font.ITALIC, 25));
		buttonCancel.setBounds(250, 53, 191, 36);
		buttonCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				flag = true;
				cancelado = true;
			
			}
		});
		frame.getContentPane().add(buttonCancel);
		
		if (this.unidadesSelecionadas == -1) {
			buttonCancel.setVisible(false);
		} else {
			buttonCancel.setText("Atacar (" + this.unidadesSelecionadas + ")");
		}
	}

}
