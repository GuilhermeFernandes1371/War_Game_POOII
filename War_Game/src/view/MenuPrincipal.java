package view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import control.ControladorDeJogo;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuPrincipal extends JFrame {

	private JPanel contentPane;

	public MenuPrincipal() {

		this.setResizable(false);
		this.setTitle("Menu Principal - War Game");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 356, 285);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setContentPane(contentPane);
		this.contentPane.setLayout(null);
		this.setVisible(true);

		JButton NovoJogo = new JButton("Novo Jogo");
		NovoJogo.setFont(new Font("Tahoma", Font.BOLD, 18));
		NovoJogo.setBounds(5, 11, 335, 69);
		contentPane.add(NovoJogo);

		JButton Sair = new JButton("Sair");
		Sair.setFont(new Font("Tahoma", Font.BOLD, 18));
		Sair.setBounds(228, 171, 112, 69);
		this.contentPane.add(Sair);

		JButton CarregarJogo = new JButton("Carregar Jogo");
		CarregarJogo.setFont(new Font("Tahoma", Font.BOLD, 18));
		CarregarJogo.setBounds(5, 91, 335, 69);
		this.contentPane.add(CarregarJogo);

		JButton Desenvolvedores = new JButton("Desenvolvedores");
		Desenvolvedores.setFont(new Font("Tahoma", Font.BOLD, 18));
		Desenvolvedores.setBounds(5, 171, 213, 69);
		this.contentPane.add(Desenvolvedores);

		NovoJogo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread() {
					@Override
					public void run() {
						
						ControladorDeJogo jogo = new ControladorDeJogo();
						dispose();
						jogo.start();
						
					}
				}.start();

			}
		});

		Sair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				System.exit(0);

			}
		});

		CarregarJogo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				CarregarJogo jogo = new CarregarJogo();
				dispose();

			}
		});

		Desenvolvedores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StringBuilder sb = new StringBuilder(
						"<html><body><b>Universidade de Itauna - UIT</b> <br>"
								+ "Gabriel Mesquita Teixeira CIU: 74.782           <br>"
								+ "Guilherme Henrique Fernandes de Oliveira CIU: 74.800");

				JOptionPane.showMessageDialog(null, sb.toString());

			}
		});
	}
}
