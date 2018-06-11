package model.bean.mundo;

import java.util.ArrayList;
import java.util.List;

public class Mundo {
	
	private List<Continente> listaContinentes = new ArrayList<>();
	private List<Territorio> listaTerritorios = new ArrayList<>();
	
	public Mundo (List<Continente> listaContinentes , List<Territorio> listaTerritorios) {
		this.setListaContinentes(listaContinentes);
		this.setListaTerritorios(listaTerritorios);
	}

	public List<Continente> getListaContinentes() {
		return listaContinentes;
	}

	public void setListaContinentes(List<Continente> listaContinentes) {
		this.listaContinentes = listaContinentes;
	}

	public List<Territorio> getListaTerritorios() {
		return listaTerritorios;
	}

	public void setListaTerritorios(List<Territorio> listaTerritorios) {
		this.listaTerritorios = listaTerritorios;
	}
	
}
