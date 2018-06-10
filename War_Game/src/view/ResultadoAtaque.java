package view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingConstants;

import control.ControladorDeAtaque;
import model.militares.Militar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ResultadoAtaque {

	public JFrame frame;
	public boolean flag = false;
	private ControladorDeAtaque control;
	private static final int sleep = 1000;
	
	private JButton button_Continuar;
	private JLabel label_ResultadoDado3Atacado;
	private JLabel label_ResultadoDado2Atacado;
	private JLabel label_ResultadoDado1Atacado;
	private JLabel label_ResultadoDado3Atacante;
	private JLabel label_ResultadoDado2Atacante;
	private JLabel label_ResultadoDado1Atacante;
	private JLabel label_Atacante;
	private JLabel label_Atacado;

	public ResultadoAtaque(ControladorDeAtaque control) {
		this.control = control;
		initialize();
		this.frame.setVisible(true);
		this.ataqueShow();
	}

	private void initialize() {
		List<Militar> listaMilitaresAtacante = new ArrayList<>();
		for (Militar militar : this.control.getListaMilitaresAtacante()) {
			listaMilitaresAtacante.add(militar);
		}
		for (Militar militar : this.control.getListaMilitaresAtacantePerdido()) {
			listaMilitaresAtacante.add(militar);
		}
		List<Militar> listaMilitaresAtacado = new ArrayList<>();
		for (Militar militar : this.control.getListaMilitaresAtacado()) {
			listaMilitaresAtacado.add(militar);
		}
		for (Militar militar : this.control.getListaMilitaresAtacadoPerdido()) {
			listaMilitaresAtacado.add(militar);
		}
		
		frame = new JFrame();
		frame.setTitle("Tela de Ataque");
		frame.setBounds(100, 100, 745, 296);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		label_Atacante = new JLabel(control.getTerritorioAtacante().getNome());
		label_Atacante.setHorizontalAlignment(SwingConstants.CENTER);
		label_Atacante.setFont(new Font("Tahoma", Font.BOLD, 30));
		label_Atacante.setBounds(10, 11, 250, 55);
		label_Atacante.setForeground(control.getTerritorioAtacante().getJogador().getCor());
		frame.getContentPane().add(label_Atacante);
		
		label_Atacado = new JLabel(control.getTerritorioAtacado().getNome());
		label_Atacado.setHorizontalAlignment(SwingConstants.CENTER);
		label_Atacado.setFont(new Font("Tahoma", Font.BOLD, 30));
		label_Atacado.setBounds(469, 11, 250, 55);
		label_Atacante.setForeground(control.getTerritorioAtacado().getJogador().getCor());
		frame.getContentPane().add(label_Atacado);
		
		JLabel label_VS = new JLabel("VS");
		label_VS.setHorizontalAlignment(SwingConstants.CENTER);
		label_VS.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 35));
		label_VS.setBounds(289, 9, 131, 55);
		frame.getContentPane().add(label_VS);
		
		JLabel label_Dado1Atacante = new JLabel("Dado 1:");
		label_Dado1Atacante.setFont(new Font("Tahoma", Font.BOLD, 25));
		label_Dado1Atacante.setBounds(20, 77, 103, 31);
		frame.getContentPane().add(label_Dado1Atacante);
		
		JLabel label_Dado2Atacante = new JLabel("Dado 2:");
		label_Dado2Atacante.setFont(new Font("Tahoma", Font.BOLD, 25));
		label_Dado2Atacante.setBounds(20, 117, 103, 31);
		frame.getContentPane().add(label_Dado2Atacante);
		
		JLabel label_Dado3Atacante = new JLabel("Dado 3:");
		label_Dado3Atacante.setFont(new Font("Tahoma", Font.BOLD, 25));
		label_Dado3Atacante.setBounds(20, 159, 103, 31);
		frame.getContentPane().add(label_Dado3Atacante);
		
		label_ResultadoDado1Atacante = new JLabel("-");
		label_ResultadoDado1Atacante.setHorizontalAlignment(SwingConstants.CENTER);
		label_ResultadoDado1Atacante.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));
		label_ResultadoDado1Atacante.setBounds(133, 77, 37, 31);
		frame.getContentPane().add(label_ResultadoDado1Atacante);
		
		label_ResultadoDado2Atacante = new JLabel("-");
		label_ResultadoDado2Atacante.setHorizontalAlignment(SwingConstants.CENTER);
		label_ResultadoDado2Atacante.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));
		label_ResultadoDado2Atacante.setBounds(133, 117, 37, 31);
		frame.getContentPane().add(label_ResultadoDado2Atacante);
		
		label_ResultadoDado3Atacante = new JLabel("-");
		label_ResultadoDado3Atacante.setHorizontalAlignment(SwingConstants.CENTER);
		label_ResultadoDado3Atacante.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));
		label_ResultadoDado3Atacante.setBounds(133, 159, 37, 31);
		frame.getContentPane().add(label_ResultadoDado3Atacante);
		
		JLabel label_Dado1Atacado = new JLabel("Dado 1:");
		label_Dado1Atacado.setFont(new Font("Tahoma", Font.BOLD, 25));
		label_Dado1Atacado.setBounds(569, 77, 103, 31);
		frame.getContentPane().add(label_Dado1Atacado);
		
		JLabel label_Dado2Atacado = new JLabel("Dado 2:");
		label_Dado2Atacado.setFont(new Font("Tahoma", Font.BOLD, 25));
		label_Dado2Atacado.setBounds(569, 117, 103, 31);
		frame.getContentPane().add(label_Dado2Atacado);
		
		JLabel label_Dado3Atacado = new JLabel("Dado 3:");
		label_Dado3Atacado.setFont(new Font("Tahoma", Font.BOLD, 25));
		label_Dado3Atacado.setBounds(569, 159, 103, 31);
		frame.getContentPane().add(label_Dado3Atacado);
		
		label_ResultadoDado1Atacado = new JLabel("-");
		label_ResultadoDado1Atacado.setHorizontalAlignment(SwingConstants.CENTER);
		label_ResultadoDado1Atacado.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));
		label_ResultadoDado1Atacado.setBounds(682, 77, 37, 31);
		frame.getContentPane().add(label_ResultadoDado1Atacado);
		
		label_ResultadoDado2Atacado = new JLabel("-");
		label_ResultadoDado2Atacado.setHorizontalAlignment(SwingConstants.CENTER);
		label_ResultadoDado2Atacado.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));
		label_ResultadoDado2Atacado.setBounds(682, 117, 37, 31);
		frame.getContentPane().add(label_ResultadoDado2Atacado);
		
		label_ResultadoDado3Atacado = new JLabel("-");
		label_ResultadoDado3Atacado.setHorizontalAlignment(SwingConstants.CENTER);
		label_ResultadoDado3Atacado.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));
		label_ResultadoDado3Atacado.setBounds(682, 159, 37, 31);
		frame.getContentPane().add(label_ResultadoDado3Atacado);
		
		JLabel label_MilitarAtacante1 = new JLabel("-");
		label_MilitarAtacante1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_MilitarAtacante1.setHorizontalAlignment(SwingConstants.CENTER);
		label_MilitarAtacante1.setBounds(180, 77, 80, 31);
		frame.getContentPane().add(label_MilitarAtacante1);
		
		JLabel label_MilitarAtacante2 = new JLabel("-");
		label_MilitarAtacante2.setHorizontalAlignment(SwingConstants.CENTER);
		label_MilitarAtacante2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_MilitarAtacante2.setBounds(180, 117, 80, 31);
		frame.getContentPane().add(label_MilitarAtacante2);
		
		JLabel label_MilitarAtacante3 = new JLabel("-");
		label_MilitarAtacante3.setHorizontalAlignment(SwingConstants.CENTER);
		label_MilitarAtacante3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_MilitarAtacante3.setBounds(180, 159, 80, 31);
		frame.getContentPane().add(label_MilitarAtacante3);
		
		JLabel label_MilitarAtacado1 = new JLabel("-");
		label_MilitarAtacado1.setHorizontalAlignment(SwingConstants.CENTER);
		label_MilitarAtacado1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_MilitarAtacado1.setBounds(469, 77, 80, 31);
		frame.getContentPane().add(label_MilitarAtacado1);
		
		JLabel label_MilitarAtacado2 = new JLabel("-");
		label_MilitarAtacado2.setHorizontalAlignment(SwingConstants.CENTER);
		label_MilitarAtacado2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_MilitarAtacado2.setBounds(469, 117, 80, 31);
		frame.getContentPane().add(label_MilitarAtacado2);
		
		JLabel label_MilitarAtacado3 = new JLabel("-");
		label_MilitarAtacado3.setHorizontalAlignment(SwingConstants.CENTER);
		label_MilitarAtacado3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_MilitarAtacado3.setBounds(469, 159, 80, 31);
		frame.getContentPane().add(label_MilitarAtacado3);
		
		button_Continuar = new JButton("Continuar");
		button_Continuar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				flag = true;
				
			}
		});
		button_Continuar.setFont(new Font("Tahoma", Font.BOLD, 34));
		button_Continuar.setBounds(263, 201, 222, 43);
		button_Continuar.setVisible(false);
		button_Continuar.setEnabled(false);
		frame.getContentPane().add(button_Continuar);
		
		
		if (listaMilitaresAtacante.size() >= 1) {
			label_MilitarAtacante1.setText(listaMilitaresAtacante.get(0).toString());
		}
		if (listaMilitaresAtacante.size() >= 2) {
			label_MilitarAtacante2.setText(listaMilitaresAtacante.get(1).toString());
		}
		if (listaMilitaresAtacante.size() >= 3) {
			label_MilitarAtacante3.setText(listaMilitaresAtacante.get(2).toString());
		}
		
		if (listaMilitaresAtacado.size() >= 1) {
			label_MilitarAtacado1.setText(listaMilitaresAtacado.get(0).toString());
		}
		if (listaMilitaresAtacado.size() >= 2) {
			label_MilitarAtacado2.setText(listaMilitaresAtacado.get(1).toString());
		}
		if (listaMilitaresAtacado.size() >= 3) {
			label_MilitarAtacado3.setText(listaMilitaresAtacado.get(2).toString());
		}
	}
	
	private void ataqueShow() {
		try {
			
			if (this.control.getQuantidadeDadosAtacante() >= 1) {
				Thread.currentThread().sleep(this.sleep);
				this.label_ResultadoDado1Atacante.setText(this.control.getAtacanteDado1() + "");
			} else {
				this.label_ResultadoDado1Atacante.setText("0");
				this.label_ResultadoDado2Atacante.setText("0");
				this.label_ResultadoDado3Atacante.setText("0");
			}
			
			if (this.control.getQuantidadeDadosAtacante() >= 2) {
				Thread.currentThread().sleep(this.sleep);
				this.label_ResultadoDado2Atacante.setText(this.control.getAtacanteDado2() + "");
			} else {
				this.label_ResultadoDado2Atacante.setText("0");
				this.label_ResultadoDado3Atacante.setText("0");
			}
			if (this.control.getQuantidadeDadosAtacante() >= 3) {
				Thread.currentThread().sleep(this.sleep);
				this.label_ResultadoDado3Atacante.setText(this.control.getAtacanteDado3() + "");
			}else{
				this.label_ResultadoDado3Atacante.setText("0");
			}

			if (this.control.getQuantidadeDadosAtacado() >= 1) {
				Thread.currentThread().sleep(this.sleep);
				this.label_ResultadoDado1Atacado.setText(this.control.getAtacadoDado1() + "");
			} else {
				this.label_ResultadoDado1Atacado.setText("0");
				this.label_ResultadoDado2Atacado.setText("0");
				this.label_ResultadoDado3Atacado.setText("0");
			}
			
			if (this.control.getQuantidadeDadosAtacado() >= 2) {
				Thread.currentThread().sleep(this.sleep);
				this.label_ResultadoDado2Atacado.setText(this.control.getAtacadoDado2() + "");
			} else {
				this.label_ResultadoDado2Atacado.setText("0");
				this.label_ResultadoDado3Atacado.setText("0");
			}
			if (this.control.getQuantidadeDadosAtacado() >= 3) {
				Thread.currentThread().sleep(this.sleep);
				this.label_ResultadoDado3Atacado.setText(this.control.getAtacadoDado3() + "");
			}else{
				this.label_ResultadoDado3Atacado.setText("0");
			}
			
			Thread.currentThread().sleep(this.sleep/2);
			
			this.label_ResultadoDado1Atacante.setText(this.control.getListaDadoAtacante().get(0) + "");
			this.label_ResultadoDado2Atacante.setText(this.control.getListaDadoAtacante().get(1) + "");
			this.label_ResultadoDado3Atacante.setText(this.control.getListaDadoAtacante().get(2) + "");

			this.label_ResultadoDado1Atacado.setText(this.control.getListaDadoAtacado().get(0) + "");
			this.label_ResultadoDado2Atacado.setText(this.control.getListaDadoAtacado().get(1) + "");
			this.label_ResultadoDado3Atacado.setText(this.control.getListaDadoAtacado().get(2) + "");
			
			Thread.currentThread().sleep(this.sleep/2);
			
			if (this.control.getResultadoDadoMaior() == 1 && this.control.getQuantidadeDadosAtacado() >= 1 && this.control.getQuantidadeDadosAtacante() >= 1) {
				this.label_ResultadoDado1Atacado.setForeground(Color.GREEN);
				this.label_ResultadoDado1Atacante.setForeground(Color.RED);
			} else {
				this.label_ResultadoDado1Atacado.setForeground(Color.RED);
				this.label_ResultadoDado1Atacante.setForeground(Color.GREEN);
			}
			if (this.control.getResultadoDadoMedio() == 1 && this.control.getQuantidadeDadosAtacado() >= 2 && this.control.getQuantidadeDadosAtacante() >= 2) {
				this.label_ResultadoDado2Atacado.setForeground(Color.GREEN);
				this.label_ResultadoDado2Atacante.setForeground(Color.RED);
			} else {
				this.label_ResultadoDado2Atacado.setForeground(Color.RED);
				this.label_ResultadoDado2Atacante.setForeground(Color.GREEN);
			}
			if (this.control.getResultadoDadoMenor() == 1 && this.control.getQuantidadeDadosAtacado() >= 3 && this.control.getQuantidadeDadosAtacante() >= 3) {
				this.label_ResultadoDado3Atacado.setForeground(Color.GREEN);
				this.label_ResultadoDado3Atacante.setForeground(Color.RED);
			} else {
				this.label_ResultadoDado3Atacado.setForeground(Color.RED);
				this.label_ResultadoDado3Atacante.setForeground(Color.GREEN);
			}
			
			if (this.control.isConquista()) {
				this.label_Atacado.setForeground(Color.RED);
				this.label_Atacante.setForeground(Color.GREEN);
			}
			
			button_Continuar.setVisible(true);
			Thread.currentThread().sleep(this.sleep*2);
			button_Continuar.setEnabled(true);
			
			if (control.isConquista()) {
				JOptionPane.showMessageDialog(null, "Territorio Conquistado");
			}
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
