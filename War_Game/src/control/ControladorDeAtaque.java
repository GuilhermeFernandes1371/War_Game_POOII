package control;

import model.bean.master.Jogador;
import model.bean.militares.Militar;
import model.bean.mundo.Territorio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JOptionPane;

public class ControladorDeAtaque {
	private List<Integer> listaDadoAtacante = new ArrayList<>();
	private List<Integer> listaDadoAtacado  = new ArrayList<>();
	private List<Militar> listaMilitaresAtacantePerdido = new ArrayList<>();
	private List<Militar> listaMilitaresAtacadoPerdido  = new ArrayList<>();
	private List<Militar> listaMilitaresAtacante;
	private List<Militar> listaMilitaresAtacado;
	private Jogador jogadorAtacante;
	private Jogador jogadorAtacado;
	private Territorio territorioAtacante;
	private Territorio territorioAtacado;

	private int quantidadeDadosAtacante = 0;
	private int quantidadeDadosAtacado  = 0;
	
	private boolean conquista = false;

	private int atacanteDado1 = 0;
	private int atacanteDado2 = 0;
	private int atacanteDado3 = 0;
	private int atacadoDado1  = 0;
	private int atacadoDado2  = 0;
	private int atacadoDado3  = 0;

	// ResultadoDado == 0 significa que o atacante ganhou naquele dado
	// ResultadoDado == 1 significa que o atacado ganhou naquele dado
	// ResultadoDado == -1 significa erro naquele dado
	private int resultadoDadoMaior = -1;
	private int resultadoDadoMedio = -1;
	private int resultadoDadoMenor = -1;

	public ControladorDeAtaque(Territorio territorioAtacante, Territorio territorioAtacado, List<Militar> listaMilitaresAtacante, List<Militar> listaMilitaresDefesa) {
		this.territorioAtacado = territorioAtacado;
		this.territorioAtacante = territorioAtacante;
		this.jogadorAtacado = territorioAtacado.getJogador();
		this.jogadorAtacante = territorioAtacante.getJogador();
		this.listaMilitaresAtacante = listaMilitaresAtacante;
		this.listaMilitaresAtacado = listaMilitaresDefesa;
		this.quantidadeDadosAtacante = listaMilitaresAtacante.size();
		this.quantidadeDadosAtacado = listaMilitaresDefesa.size();
		this.iniciaAtaque();
	}

	private void iniciaAtaque() {
		// Lanca dados do Atacante
		if (this.quantidadeDadosAtacante >= 1) {
			this.atacanteDado1 = this.listaMilitaresAtacante.get(0).valorAtaque();
		}
		if (this.quantidadeDadosAtacante >= 2) {
			this.atacanteDado2 = this.listaMilitaresAtacante.get(1).valorAtaque();
		}
		if (this.quantidadeDadosAtacante >= 3) {
			this.atacanteDado3 = this.listaMilitaresAtacante.get(2).valorAtaque();
		}

		// Lanca dados do Atacado
		if (this.quantidadeDadosAtacado >= 1) {
			this.atacadoDado1 = this.listaMilitaresAtacado.get(0).valorAtaque();
		}
		if (this.quantidadeDadosAtacado >= 2) {
			this.atacadoDado2 = this.listaMilitaresAtacado.get(1).valorAtaque();
		}
		if (this.quantidadeDadosAtacado >= 3) {
			this.atacadoDado3 = this.listaMilitaresAtacado.get(2).valorAtaque();
		}

		this.listaDadoAtacante.add(this.atacanteDado1);
		this.listaDadoAtacante.add(this.atacanteDado2);
		this.listaDadoAtacante.add(this.atacanteDado3);
		Collections.sort(this.listaDadoAtacante, Collections.reverseOrder()); // Ordena a lista inversamente do Atacante

		this.listaDadoAtacado.add(this.atacadoDado1);
		this.listaDadoAtacado.add(this.atacadoDado2);
		this.listaDadoAtacado.add(this.atacadoDado3);
		Collections.sort(this.listaDadoAtacado, Collections.reverseOrder()); // Ordena a lista inversamente do Atacado
		
		this.verificaVencedor();
	}

	private void verificaVencedor() {
		// Resultado == 0 significa que o atacante ganhou naquele dado, caso resultado
		// == 1, o atacado ganhou, caso -1 erro
		if (this.quantidadeDadosAtacado >= 1 && this.quantidadeDadosAtacante >= 1) {
			if (this.listaDadoAtacante.get(0) > this.listaDadoAtacado.get(0)) {
				this.resultadoDadoMaior = 0;
				this.listaMilitaresAtacadoPerdido.add(this.listaMilitaresAtacado.get(0));
			} else {
				resultadoDadoMaior = 1;
				this.listaMilitaresAtacantePerdido.add(this.listaMilitaresAtacante.get(0));
			}
		}
		if (this.quantidadeDadosAtacado >= 2 && this.quantidadeDadosAtacante >= 2) {
			if (this.listaDadoAtacante.get(1) > this.listaDadoAtacado.get(1)) {
				this.resultadoDadoMedio = 0;
				this.listaMilitaresAtacadoPerdido.add(this.listaMilitaresAtacado.get(1));
			} else {
				this.resultadoDadoMedio = 1;
				this.listaMilitaresAtacantePerdido.add(this.listaMilitaresAtacante.get(1));
			}
		}
		if (this.quantidadeDadosAtacado >= 3 && this.quantidadeDadosAtacante >= 3) {
			if (this.listaDadoAtacante.get(2) > this.listaDadoAtacado.get(2)) {
				this.resultadoDadoMenor = 0;
				this.listaMilitaresAtacadoPerdido.add(this.listaMilitaresAtacado.get(2));
			} else {
				resultadoDadoMenor = 1;
				this.listaMilitaresAtacantePerdido.add(this.listaMilitaresAtacante.get(2));
			}
		}
		
		this.removeMilitaresMortos();
		
		if (this.territorioAtacado.getListaMilitares().isEmpty()) {
			this.conquista();
		}
	}
	
	private void removeMilitaresMortos() {
		for (int i=0 ; i< this.listaMilitaresAtacantePerdido.size() ; i++) {
			this.territorioAtacante.getListaMilitares().remove(this.listaMilitaresAtacantePerdido.get(i));
		}
		for (int i=0 ; i< this.listaMilitaresAtacadoPerdido.size() ; i++) {
			this.territorioAtacado.getListaMilitares().remove(this.listaMilitaresAtacadoPerdido.get(i));
		}
	}
	
	private void conquista() {
		this.conquista = true;
		
		this.jogadorAtacado .getListaTerritorios().remove(territorioAtacado);
		this.jogadorAtacante.getListaTerritorios().add(territorioAtacado);
		
		Militar militarTransferidoNovoTerritorio = this.territorioAtacante.getListaMilitares().get(0);
		this.territorioAtacante.getListaMilitares().remove(militarTransferidoNovoTerritorio);
		this.territorioAtacado .getListaMilitares().add   (militarTransferidoNovoTerritorio);
	}

	public List<Integer> getListaDadoAtacante() {
		return listaDadoAtacante;
	}

	public List<Integer> getListaDadoAtacado() {
		return listaDadoAtacado;
	}

	public List<Militar> getListaMilitaresAtacantePerdido() {
		return listaMilitaresAtacantePerdido;
	}

	public List<Militar> getListaMilitaresAtacadoPerdido() {
		return listaMilitaresAtacadoPerdido;
	}

	public List<Militar> getListaMilitaresAtacante() {
		return listaMilitaresAtacante;
	}

	public List<Militar> getListaMilitaresAtacado() {
		return listaMilitaresAtacado;
	}

	public Jogador getJogadorAtacante() {
		return jogadorAtacante;
	}

	public Jogador getJogadorAtacado() {
		return jogadorAtacado;
	}

	public Territorio getTerritorioAtacante() {
		return territorioAtacante;
	}

	public Territorio getTerritorioAtacado() {
		return territorioAtacado;
	}

	public int getQuantidadeDadosAtacante() {
		return quantidadeDadosAtacante;
	}

	public int getQuantidadeDadosAtacado() {
		return quantidadeDadosAtacado;
	}

	public boolean isConquista() {
		return conquista;
	}

	public int getAtacanteDado1() {
		return atacanteDado1;
	}

	public int getAtacanteDado2() {
		return atacanteDado2;
	}

	public int getAtacanteDado3() {
		return atacanteDado3;
	}

	public int getAtacadoDado1() {
		return atacadoDado1;
	}

	public int getAtacadoDado2() {
		return atacadoDado2;
	}

	public int getAtacadoDado3() {
		return atacadoDado3;
	}

	public int getResultadoDadoMaior() {
		return resultadoDadoMaior;
	}

	public int getResultadoDadoMedio() {
		return resultadoDadoMedio;
	}

	public int getResultadoDadoMenor() {
		return resultadoDadoMenor;
	}
}
