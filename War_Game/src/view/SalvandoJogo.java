package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import control.ControladorDeJogo;
import control.ControladorDeSalvamento;

import java.awt.Font;
import java.awt.Point;

public class SalvandoJogo extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	private JProgressBar barra;
	private JLabel label;
	private ControladorDeSalvamento save;
	
	public boolean flag = false;
	
	public SalvandoJogo() {
		
		this.setAlwaysOnTop(true);
		this.setEnabled(false);
		this.setResizable(false);
		this.setTitle("Salvando Jogo");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 448, 171);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.contentPane.setLayout(new BorderLayout(0, 0));
		this.setContentPane(contentPane);
		this.setVisible(true);
		
		this.panel = new JPanel();
		this.contentPane.add(panel, BorderLayout.CENTER);
		this.panel.setLayout(null);
		
		this.barra = new JProgressBar();
		this.barra.setIndeterminate(true);
		this.barra.setStringPainted(true);
		this.barra.setToolTipText("");
		this.barra.setBounds(10, 81, 404, 34);
		this.panel.add(barra);
		
		this.label = new JLabel("Salvando Jogo");
		this.label.setBounds(10, 11, 404, 59);
		this.label.setFont(new Font("Tahoma", Font.BOLD, 28));
		this.label.setHorizontalAlignment(SwingConstants.CENTER);
		this.panel.add(label);
		
		this.iniciandoSalvamento();
	}
	
	private void iniciandoSalvamento() {
		try {
			this.save = new ControladorDeSalvamento();
			
			this.barra.setMinimum(0);
			this.barra.setMaximum(save.getNumeroTotalDeOperacoes());
			this.barra.setValue(save.getNumeroAtualDeOperacoes());
			this.barra.setIndeterminate(false);
			
			Thread saveGame = new Thread() {
				@Override
				public void run() {
					try {
						save.salvaGame();
						Thread.currentThread().interrupt();
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, ex);
						System.exit(0);
					}
				}
			};
			saveGame.start();
			
			while(saveGame.isAlive()) {
				// Atualiza a barra de salvamento do jogo
				barra.setValue(save.getNumeroAtualDeOperacoes());
				System.out.println("Barra atualizado");
			}
			
			JOptionPane.showMessageDialog(null, "Jogo salvo com sucesso !!! ID(" + ControladorDeJogo.id + ")");
			this.flag = true;
			
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex);
			System.exit(0);
		}
	}
	
}
