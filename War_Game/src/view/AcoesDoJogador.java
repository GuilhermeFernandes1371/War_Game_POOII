package view;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;

import model.bean.master.Jogador;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;

import java.awt.GridLayout;

public class AcoesDoJogador {

	public JFrame frame;
	public boolean flag = false;
	private int op = 0;
	private Jogador jogador;
	
	public AcoesDoJogador(Jogador jogador) {
		this.jogador = jogador;
		initialize();
		this.frame.setVisible(true);
	}

	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Turno do Jogador " + jogador.getNome());
		frame.setBounds(100, 100, 578, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		JLabel label = new JLabel("Escolha um a\u00E7\u00E3o para fazer");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.BOLD, 25));
		label.setBounds(20, 11, 532, 54);
		frame.getContentPane().add(label);
		
		JPanel panel = new JPanel();
		panel.setBounds(20, 68, 532, 182);
		frame.getContentPane().add(panel);
		panel.setLayout(new GridLayout(0, 3, 0, 0));
		
		JButton button_Atacar = new JButton("Atacar");
		button_Atacar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				op = 1;
				flag = true;
				
			}
		});
		button_Atacar.setFont(new Font("Tahoma", Font.PLAIN, 25));
		panel.add(button_Atacar);
		
		JButton button_Remanejar = new JButton("Remanejar");
		button_Remanejar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				op = 2;
				flag = true;
			
			}
		});
		button_Remanejar.setFont(new Font("Tahoma", Font.PLAIN, 25));
		panel.add(button_Remanejar);
		
		JButton button_PassarAVez = new JButton("Passar a Vez");
		panel.add(button_PassarAVez);
		button_PassarAVez.setFont(new Font("Tahoma", Font.PLAIN, 25));
		button_PassarAVez.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				op = 3;
				flag = true;
			
			}
		});
	}

	public int getOp() {
		return op;
	}
}
