package view;

import java.util.List;

import javax.swing.JFrame;

import model.bean.master.Jogador;
import model.bean.mundo.Territorio;

import javax.swing.JLabel;

import java.awt.BorderLayout;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SelecionarTerritorio {

	public JFrame frame;
	public boolean flag = false;
	List<Territorio> listaTerritorios;
	private String stringLabel;
	private JComboBox comboBox_Territorio;
	private Jogador jogador;
	
	public SelecionarTerritorio(List<Territorio> listaTerritorios , String stringLabel , Jogador jogador) {
		this.jogador = jogador;
		this.stringLabel = stringLabel;
		this.listaTerritorios = listaTerritorios;
		initialize();
		preencheComboBox();
		frame.setVisible(true);
		frame.setResizable(false);
	}
	
	private void preencheComboBox() {
		for (Territorio territorio : this.listaTerritorios) {
			comboBox_Territorio.addItem(territorio);
		}
	}
	
	public Territorio getTerritorioEscolhido() {
		return (Territorio) comboBox_Territorio.getSelectedItem();
	}
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 795, 193);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Turno do Jogador: " + jogador.getNome());
		
		JLabel label = new JLabel(stringLabel);
		label.setBounds(10, 21, 759, 31);
		label.setFont(new Font("Tahoma", Font.BOLD, 25));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(label);
		
		JButton button_Selecionar = new JButton("Selecionar");
		button_Selecionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				flag = true;
			
			}
		});
		button_Selecionar.setBounds(481, 84, 288, 46);
		button_Selecionar.setFont(new Font("Tahoma", Font.BOLD, 25));
		button_Selecionar.setForeground(this.jogador.getCorComplementar());
		button_Selecionar.setBackground(this.jogador.getCor());
		frame.getContentPane().add(button_Selecionar);
		
		comboBox_Territorio = new JComboBox();
		comboBox_Territorio.setFont(new Font("Tahoma", Font.PLAIN, 25));
		comboBox_Territorio.setBounds(10, 84, 288, 46);
		frame.getContentPane().add(comboBox_Territorio);
	}

}
