package view;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EscolherQuantidadeJogadores {

	public JFrame frame;
	private int quantidadeDeJogadores = 0;
	public boolean flag = false;
	
	public EscolherQuantidadeJogadores() {
		initialize();
		this.frame.setVisible(true);
	}
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton button2p = new JButton("2 Players");
		button2p.setFont(new Font("Tahoma", Font.PLAIN, 22));
		button2p.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				quantidadeDeJogadores = 2;
				flag = true;
				
			}
		});
		frame.getContentPane().add(button2p, BorderLayout.WEST);
		
		JLabel label = new JLabel("Escolha a quantidade de jogadores:");
		label.setFont(new Font("Times New Roman", Font.BOLD, 23));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(label, BorderLayout.NORTH);
		
		JButton button3p = new JButton("3 Players");
		button3p.setFont(new Font("Tahoma", Font.PLAIN, 22));
		button3p.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				quantidadeDeJogadores = 3;
				flag = true;
				
			}
		});
		frame.getContentPane().add(button3p, BorderLayout.CENTER);
		
		JButton button4p = new JButton("4 Players");
		button4p.setFont(new Font("Tahoma", Font.PLAIN, 22));
		button4p.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				quantidadeDeJogadores = 4;
				flag = true;
				
			}
		});
		frame.getContentPane().add(button4p, BorderLayout.EAST);
	}

	public int getQuantidadeDeJogadores() {
		return quantidadeDeJogadores;
	}

}
