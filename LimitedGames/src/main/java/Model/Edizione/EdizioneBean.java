package Model.Edizione;

public class EdizioneBean {

	private int IDGioco, quantita;
	private String piattaforma;
	public int getIDGioco() {
		return IDGioco;
	}
	public void setIDGioco(int iDGioco) {
		IDGioco = iDGioco;
	}
	public int getQuantita() {
		return quantita;
	}
	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}
	public String getPiattaforma() {
		return piattaforma;
	}
	public void setPiattaforma(String piattaforma) {
		this.piattaforma = piattaforma;
	}
	@Override
	public String toString() {
		return "EdizioneBean [IDGioco=" + IDGioco + ", quantita=" + quantita + ", piattaforma=" + piattaforma + "]";
	}
	
	
}
