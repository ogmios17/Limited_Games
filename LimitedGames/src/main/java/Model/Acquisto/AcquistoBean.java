package Model.Acquisto;

public class AcquistoBean {

	private int IDAcquisto, IDOrdine, IDGioco, iva, quantita;
	private float prezzo;
	private String immagine;
	public int getIDAcquisto() {
		return IDAcquisto;
	}
	public int getQuantita() {
		return quantita;
	}
	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}
	public void setIDAcquisto(int iDAcquisto) {
		IDAcquisto = iDAcquisto;
	}
	public int getIDOrdine() {
		return IDOrdine;
	}
	public void setIDOrdine(int iDOrdine) {
		IDOrdine = iDOrdine;
	}
	public int getIDGioco() {
		return IDGioco;
	}
	public void setIDGioco(int iDGioco) {
		IDGioco = iDGioco;
	}
	public int getIva() {
		return iva;
	}
	public void setIva(int iva) {
		this.iva = iva;
	}
	public float getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}
	public String getImmagine() {
		return immagine;
	}
	public void setImmagine(String immagine) {
		this.immagine = immagine;
	}
	@Override
	public String toString() {
		return "AcquistoBean [IDAcquisto=" + IDAcquisto + ", IDOrdine=" + IDOrdine + ", IDGioco=" + IDGioco + ", iva="
				+ iva + ", prezzo=" + prezzo + ", immagine=" + immagine + "]";
	}
	
	
}
