package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import model.bean.master.Jogador;
import model.bean.mundo.Territorio;

public class PlayerInfo {

	public JFrame frame;
	
	private JPanel panelPrincipal;
	private JPanel panelP1;
	private JPanel panelP2;
	private JPanel panelP3;
	private JPanel panelP4;
	private JPanel panelP1Bot;
	private JPanel panelP2Bot;
	private JPanel panelP3Bot;
	private JPanel panelP4Bot;
	private JPanel panelP1Top;
	private JPanel panelP2Top;
	private JPanel panelP3Top;
	private JPanel panelP4Top;
	private List<Jogador> listaJogadores;
	private List<JLabel> listaTerritorioJ1 = new ArrayList<>();
	private List<JLabel> listaTerritorioJ2 = new ArrayList<>();
	private List<JLabel> listaTerritorioJ3 = new ArrayList<>();
	private List<JLabel> listaTerritorioJ4 = new ArrayList<>();
	
	public PlayerInfo(List<Jogador> listaJogadores) {
		this.listaJogadores = listaJogadores;
		initialize();
		this.frame.setVisible(true);
		this.atualiza(this.listaJogadores);
	}
	
	public void atualiza(List<Jogador> listaJogadores) {
		this.listaJogadores = listaJogadores;
		
		if (this.listaJogadores.size() >= 2) {
			for (Territorio territorio : this.listaJogadores.get(0).getListaTerritorios()) {
								
				JLabel labelTerritorio = new JLabel(territorio.getNome() + " (" + territorio.getListaMilitares().size() + ")");
				labelTerritorio.setHorizontalAlignment(SwingConstants.CENTER);
				labelTerritorio.setForeground(this.listaJogadores.get(0).getCorComplementar());
				this.panelP1Bot.add(labelTerritorio);
				
				listaTerritorioJ1.add(labelTerritorio);
				
			}
			
			for (JLabel label : listaTerritorioJ2) {
				this.panelP2Bot.remove(label);
			}
			
			for (Territorio territorio : this.listaJogadores.get(1).getListaTerritorios()) {
				JLabel labelTerritorio = new JLabel(territorio.getNome() + " (" + territorio.getListaMilitares().size() + ")");
				labelTerritorio.setHorizontalAlignment(SwingConstants.CENTER);
				labelTerritorio.setForeground(this.listaJogadores.get(1).getCorComplementar());
				this.panelP2Bot.add(labelTerritorio);
				
				listaTerritorioJ2.add(labelTerritorio);
			}
			
		}
		if (this.listaJogadores.size() >= 3) {
			for (JLabel label : listaTerritorioJ3) {
				this.panelP3Bot.remove(label);
			}
			
			for (Territorio territorio : this.listaJogadores.get(2).getListaTerritorios()) {
				JLabel labelTerritorio = new JLabel(territorio.getNome() + " (" + territorio.getListaMilitares().size() + ")");
				labelTerritorio.setHorizontalAlignment(SwingConstants.CENTER);
				labelTerritorio.setForeground(this.listaJogadores.get(2).getCorComplementar());
				this.panelP3Bot.add(labelTerritorio);
				
				listaTerritorioJ3.add(labelTerritorio);
			}
			
		}
		if (listaJogadores.size() >= 4) {
			for (JLabel label : listaTerritorioJ4) {
				this.panelP4Bot.remove(label);
			}
			
			for (Territorio territorio : this.listaJogadores.get(3).getListaTerritorios()) {
				JLabel labelTerritorio = new JLabel(territorio.getNome() + " (" + territorio.getListaMilitares().size() + ")");
				labelTerritorio.setHorizontalAlignment(SwingConstants.CENTER);
				labelTerritorio.setForeground(this.listaJogadores.get(3).getCorComplementar());
				this.panelP4Bot.add(labelTerritorio);
				
				listaTerritorioJ4.add(labelTerritorio);
			}
			
		}
		
	}

	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Interface Jogadores");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(1,1));
		
		int x=2,y=2;
		if (listaJogadores.size() == 2) {
			x = 2;
			y = 1;
		} else if (listaJogadores.size() == 3) {
			x = 1;
			y = 3;
		} else if (listaJogadores.size() == 4) {
			x = 2;
			y = 2;
		}
		
		panelPrincipal = new JPanel();
		panelPrincipal.setBounds(10, 11, 414, 239);
		panelPrincipal.setLayout(new GridLayout(x,y));
		frame.getContentPane().add(panelPrincipal);
		
		panelP1 = new JPanel();
		panelP1.setLayout(new BorderLayout());
		panelP2 = new JPanel();
		panelP2.setLayout(new BorderLayout());
		panelP3 = new JPanel();
		panelP3.setLayout(new BorderLayout());
		panelP4 = new JPanel();
		panelP4.setLayout(new BorderLayout());
		
		panelP1Bot = new JPanel();
		panelP1Bot.setLayout(new FlowLayout());
		panelP2Bot = new JPanel();
		panelP2Bot.setLayout(new FlowLayout());
		panelP3Bot = new JPanel();
		panelP3Bot.setLayout(new FlowLayout());
		panelP4Bot = new JPanel();
		panelP4Bot.setLayout(new FlowLayout());
		
		panelP1Top = new JPanel();
		panelP1Top.setLayout(new GridLayout());
		panelP2Top = new JPanel();
		panelP2Top.setLayout(new GridLayout());
		panelP3Top = new JPanel();
		panelP3Top.setLayout(new GridLayout());
		panelP4Top = new JPanel();
		panelP4Top.setLayout(new GridLayout());
		
		if (listaJogadores.size() >= 1) {
			panelPrincipal.add(panelP1);
			
			JLabel nomeJogador1 = new JLabel("Jogador 1: " + this.listaJogadores.get(0).getNome());
			nomeJogador1.setHorizontalAlignment(SwingConstants.CENTER);
			nomeJogador1.setForeground(this.listaJogadores.get(0).getCorComplementar());
			panelP1Top.add(nomeJogador1);
			panelP1Top.setBackground(this.listaJogadores.get(0).getCor());
			panelP1Bot.setBackground(this.listaJogadores.get(0).getCor());
			
			panelP1.add(panelP1Top , BorderLayout.NORTH);
			panelP1.add(panelP1Bot , BorderLayout.CENTER);
			
		}
		if (listaJogadores.size() >= 2) {
			panelPrincipal.add(panelP2);
			
			JLabel nomeJogador2 = new JLabel("Jogador 2: " + this.listaJogadores.get(1).getNome());
			nomeJogador2.setHorizontalAlignment(SwingConstants.CENTER);
			nomeJogador2.setForeground(this.listaJogadores.get(1).getCorComplementar());
			panelP2Top.add(nomeJogador2);
			panelP2Top.setBackground(this.listaJogadores.get(1).getCor());
			panelP2Bot.setBackground(this.listaJogadores.get(1).getCor());
			
			panelP2.add(panelP2Top , BorderLayout.NORTH);
			panelP2.add(panelP2Bot , BorderLayout.CENTER);
			
		}
		if (listaJogadores.size() >= 3) {
			panelPrincipal.add(panelP3);
			
			JLabel nomeJogador3 = new JLabel("Jogador 3: " + this.listaJogadores.get(2).getNome());
			nomeJogador3.setHorizontalAlignment(SwingConstants.CENTER);
			nomeJogador3.setForeground(this.listaJogadores.get(2).getCorComplementar());
			panelP3Top.add(nomeJogador3);
			panelP3Top.setBackground(this.listaJogadores.get(2).getCor());
			panelP3Bot.setBackground(this.listaJogadores.get(2).getCor());
			
			panelP3.add(panelP3Top , BorderLayout.NORTH);
			panelP3.add(panelP3Bot , BorderLayout.CENTER);
		}
		if (listaJogadores.size() >= 4) {
			panelPrincipal.add(panelP4);
			
			JLabel nomeJogador4 = new JLabel("Jogador 4: " + this.listaJogadores.get(3).getNome());
			nomeJogador4.setHorizontalAlignment(SwingConstants.CENTER);
			nomeJogador4.setForeground(this.listaJogadores.get(3).getCorComplementar());
			panelP4Top.add(nomeJogador4);
			panelP4Top.setBackground(this.listaJogadores.get(3).getCor());
			panelP4Bot.setBackground(this.listaJogadores.get(3).getCor());
			
			panelP4.add(panelP4Top , BorderLayout.NORTH);
			panelP4.add(panelP4Bot , BorderLayout.CENTER);
		}
		
	}
}
