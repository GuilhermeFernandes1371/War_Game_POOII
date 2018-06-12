package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JButton;

import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JProgressBar;

import control.ControladorDeCarregamento;
import control.ControladorDeJogo;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CarregarJogo extends JFrame {

	private JPanel contentPane;
	private JTable tabela;
	private JTextField gameId;
	private JProgressBar barra;
	private JLabel labelBarra;

	public CarregarJogo() {
		
		this.setTitle("Tela de Carregamento");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 712, 370);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setContentPane(contentPane);
		this.contentPane.setLayout(null);
		
		JPanel painelDireitaTabela = new JPanel();
		painelDireitaTabela.setBounds(442, 11, 254, 320);
		this.contentPane.add(painelDireitaTabela);
		painelDireitaTabela.setLayout(null);
		
		this.tabela = new JTable();
		this.tabela.setBounds(10, 11, 234, 298);
		painelDireitaTabela.add(tabela);
		
		JPanel painelCimaEsquerdo = new JPanel();
		painelCimaEsquerdo.setBounds(10, 11, 422, 55);
		this.contentPane.add(painelCimaEsquerdo);
		painelCimaEsquerdo.setLayout(null);
		
		JPanel painelBaixoEsquerdo = new JPanel();
		painelBaixoEsquerdo.setBounds(10, 77, 422, 254);
		this.contentPane.add(painelBaixoEsquerdo);
		painelBaixoEsquerdo.setLayout(null);
		
        this.barra = new JProgressBar();
        this.barra.setBounds(10, 11, 402, 29);
        this.barra.setVisible(false);
		painelBaixoEsquerdo.add(this.barra);
		
		this.labelBarra = new JLabel("Carregando Jogo...");
		this.labelBarra.setFont(new Font("Tahoma", Font.BOLD, 18));
		this.labelBarra.setHorizontalAlignment(SwingConstants.CENTER);
		this.labelBarra.setBounds(10, 51, 402, 29);
		painelBaixoEsquerdo.add(this.labelBarra);
		
		JButton load = new JButton("Carregar Jogo");
		load.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int id = Integer.parseInt(gameId.getText());
					carregaJogo(id);
				}catch(NumberFormatException ex) {
					reinicia();
					labelBarra.setText("ERRO - GameID digitado errado");
				}
			}
		});
		load.setFont(new Font("Tahoma", Font.BOLD, 20));
		load.setBounds(208, 11, 204, 28);
		painelCimaEsquerdo.add(load);
		
		this.gameId = new JTextField();
		this.gameId.setFont(new Font("Tahoma", Font.PLAIN, 18));
		this.gameId.setBounds(112, 11, 86, 28);
		painelCimaEsquerdo.add(gameId);
		this.gameId.setColumns(10);
		
		JLabel labelGameId = new JLabel("GameID:");
		labelGameId.setHorizontalAlignment(SwingConstants.CENTER);
		labelGameId.setFont(new Font("Tahoma", Font.BOLD, 18));
		labelGameId.setBounds(10, 11, 95, 28);
		painelCimaEsquerdo.add(labelGameId);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 91, 402, 152);
		painelBaixoEsquerdo.add(panel);
		this.reinicia();
		this.setVisible(true);
		
	}

	private void reinicia() {
		this.barra.setVisible(false);
		this.barra.setValue(0);
		this.barra.setMaximum(1);
		this.barra.setMinimum(0);
		this.barra.setStringPainted(false);
		this.labelBarra.setText("Digite um GameID");
	}
	
	private void carregaJogo(int id) {
		new Thread() {
			@Override
			public void run() {
				
				carregar(id);
				
			}
		}.start();
	}

	private void carregar(int id) {
		
		ControladorDeJogo jogo = new ControladorDeJogo();
		
		ControladorDeCarregamento loadGame = new ControladorDeCarregamento();
		this.labelBarra.setText("Carregando Jogo");
		this.barra.setVisible(true);
		this.barra.setMinimum(0);
		this.barra.setMaximum(loadGame.getNumeroTotalDeOperacoes());
		this.barra.setValue  (loadGame.getNumeroAtualDeOperacoes());
		this.barra.setStringPainted(true);
		
		Thread carregaJogo = new Thread() {
			@Override
			public void run() {
				try {
					loadGame.start(id);
					Thread.currentThread().interrupt();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex);
					System.exit(0);
				}
			}
		};
		
		carregaJogo.start();
		while(carregaJogo.isAlive()) {
			this.barra.setValue(loadGame.getNumeroAtualDeOperacoes());
		}
		
		JOptionPane.showMessageDialog(null, "Jogo carregado com sucesso");

		dispose();
		jogo.start();
	}
}
