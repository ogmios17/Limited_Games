package Model.Gioco;

public class GiocoBean {
	
	private int id, iva;
	private float prezzo;
	private String descrizione, edizione;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getEdizione() {
		return edizione;
	}
	public void setEdizione(String edizione) {
		this.edizione = edizione;
	}
	@Override
	public String toString() {
		return "GiocoBean [id=" + id + ", iva=" + iva + ", prezzo=" + prezzo + ", descrizione=" + descrizione
				+ ", edizione=" + edizione + "]";
	}
	
	
}
