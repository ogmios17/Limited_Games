package Model.Gioco;

import java.sql.Date;

public class GiocoBean {
	
	private int id, iva;
	private float prezzo;
	private String descrizione, edizione,titolo,immagine;
	private Date ReleaseDate;
	
	public int getId() {
		return id;
	}
	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public String getImmagine() {
		return immagine;
	}
	public void setImmagine(String immagine) {
		this.immagine = immagine;
	}
	public Date getReleaseDate() {
		return ReleaseDate;
	}
	public void setReleaseDate(Date releaseDate) {
		ReleaseDate = releaseDate;
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
