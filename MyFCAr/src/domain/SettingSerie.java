package domain;

public class SettingSerie extends Setting {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private NomeSerie nome;

	public SettingSerie(Integer id, Float v, NomeSerie n) {
		super(id, v);
		nome = n;
		// TODO Auto-generated constructor stub
	}

	public String getNome() {
		return nome.toString();
	}

}
