package business.server.configurazioneauto;

import business.server.configurazione.Configurazione;

public class ConfigurazioneAuto {
	
	private Integer id;
	private Configurazione configurazione;
	
	public ConfigurazioneAuto(Configurazione config){
		configurazione = config;
	}
	
	public ConfigurazioneAuto(Configurazione config, Integer id){
		configurazione = config;
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Configurazione getConfigurazione() {
		return configurazione;
	}

}