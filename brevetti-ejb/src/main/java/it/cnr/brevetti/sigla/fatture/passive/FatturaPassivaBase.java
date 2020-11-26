package it.cnr.brevetti.sigla.fatture.passive;

import java.io.Serializable;
import java.util.Calendar;

import com.google.gson.annotations.SerializedName;


/**
 * Tracciato delle fatture passive di base con conversione di nomi e tipi XML/REST
 * 
 * @author Aurelio D'Amico
 * @version 1.0 [Sep 21, 2016]
 *
 */
public class FatturaPassivaBase implements Serializable {
	private static final long serialVersionUID = 1L;

// Nuovo tracciato JSON 
/*
{
  "cd_cds":"000",
  "cd_unita_organizzativa":"000.106",
  "esercizio":2015,"pg_fattura_passiva":2,
  "dsFatturaPassiva":"NS. RIF.10326",
  "cdCdsOrigine":"000",
  "cdUoOrigine":"000.106",
  "tipoFatturaCompenso":"FATTURA",
  "dtRegistrazione":1421107200000,
  "cdTerzo":187638,"cognome":null,
  "nome":null,
  "ragioneSociale":"PONS PATENTS Y MARCAS INTERNACIONAL S.L.",
  "cambio":1,
  "cdDivisa":"EURO",
  "nr_fattura_fornitore":"P-FAC-0-00-002605",
  "dt_fattura_fornitore":1406073600000,
  "partita_iva":"B84921709",
  "codice_fiscale":"B84921709"
}
*/
		
// Vecchio tracciato XML
/*
<xs:complexType name="FatturaPassivaBase">
    <xs:sequence>
        <xs:element minOccurs="0" name="cd_cds" nillable="true" type="xs:string"/>
        <xs:element minOccurs="0" name="cd_cds_origine" nillable="true" type="xs:string"/>
        <xs:element minOccurs="0" name="cd_terzo" nillable="true" type="xs:int"/>        
        <xs:element minOccurs="0" name="cd_unita_organizzativa" nillable="true" type="xs:string"/>
        <xs:element minOccurs="0" name="cd_uo_origine" nillable="true" type="xs:string"/>
        <xs:element minOccurs="0" name="codice_fiscale" nillable="true" type="xs:string"/>
        <xs:element minOccurs="0" name="ds_fattura_passiva" nillable="true" type="xs:string"/>
        <xs:element minOccurs="0" name="dt_fattura_fornitore" nillable="true" type="xs:date"/>
        <xs:element minOccurs="0" name="esercizio" nillable="true" type="xs:int"/>
        <xs:element minOccurs="0" name="nr_fattura_fornitore" nillable="true" type="xs:string"/>
        <xs:element minOccurs="0" name="partita_iva" nillable="true" type="xs:string"/>
        <xs:element minOccurs="0" name="pg_fattura_passiva" nillable="true" type="xs:long"/>
        <xs:element minOccurs="0" name="tipoFatturaCompenso" nillable="true" type="xs:string"/>
    </xs:sequence>
</xs:complexType>

 */	
	@SerializedName("cd_cds")
	private String cd_cds;
	@SerializedName("cdCdsOrigine")
	private String cd_cds_origine;
	@SerializedName("cdTerzo")
	private int cd_terzo;	
	@SerializedName("cd_unita_organizzativa")
	private String cd_unita_organizzativa;
	@SerializedName("cdUoOrigine")
	private String cd_uo_origine;
	@SerializedName("codice_fiscale")
	private String codice_fiscale;
	@SerializedName("dsFatturaPassiva")
	private String ds_fattura_passiva;
	@SerializedName("dt_fattura_fornitore")
	private long dt_fattura_fornitore;
	@SerializedName("esercizio")
	private int esercizio;
	@SerializedName("nr_fattura_fornitore")
	private String nr_fattura_fornitore;
	@SerializedName("partita_iva")
	private String partita_iva;
	@SerializedName("pg_fattura_passiva")
	private long pg_fattura_passiva;
	@SerializedName("tipoFatturaCompenso")
	private String tipoFatturaCompenso;
	
	// SETTERS per la deserializzazione da REST
	
	public void setCd_cds(String cd_cds) {
		this.cd_cds = cd_cds;
	}
	public void setCd_cds_origine(String cd_cds_origine) {
		this.cd_cds_origine = cd_cds_origine;
	}
	public void setCd_terzo(int cd_terzo) {
		this.cd_terzo = cd_terzo;
	}
	public int getCd_terzo() {
		return cd_terzo;
	}	
	public void setCd_unita_organizzativa(String cd_unita_organizzativa) {
		this.cd_unita_organizzativa = cd_unita_organizzativa;
	}
	public void setCd_uo_origine(String cd_uo_origine) {
		this.cd_uo_origine = cd_uo_origine;
	}
	public void setCodice_fiscale(String codice_fiscale) {
		this.codice_fiscale = codice_fiscale;
	}
	public void setDs_fattura_passiva(String ds_fattura_passiva) {
		this.ds_fattura_passiva = ds_fattura_passiva;
	}
	public void setDt_fattura_fornitore(int dt_fattura_fornitore) {
		this.dt_fattura_fornitore = dt_fattura_fornitore;
	}
	public void setEsercizio(int esercizio) {
		this.esercizio = esercizio;
	}
	public void setNr_fattura_fornitore(String nr_fattura_fornitore) {
		this.nr_fattura_fornitore = nr_fattura_fornitore;
	}
	public void setPartita_iva(String partita_iva) {
		this.partita_iva = partita_iva;
	}
	public void setPg_fattura_passiva(int pg_fattura_passiva) {
		this.pg_fattura_passiva = pg_fattura_passiva;
	}
	public void setTipoFatturaCompenso(String tipoFatturaCompenso) {
		this.tipoFatturaCompenso = tipoFatturaCompenso;
	}

	// GETTERS per la compatibilità al precedente tracciato XML
	
	public String getCd_cds() {
		return cd_cds;
	}
	public String getCd_cds_origine() {
		return cd_cds_origine;
	}
	public String getCd_unita_organizzativa() {
		return cd_unita_organizzativa;
	}
	public String getCd_uo_origine() {
		return cd_uo_origine;
	}
	public String getCodice_fiscale() {
		return codice_fiscale;
	}
	public String getDs_fattura_passiva() {
		return ds_fattura_passiva;
	}
	public Calendar getDt_fattura_fornitore() {
		return getCalendar(dt_fattura_fornitore);
	}
	public int getEsercizio() {
		return esercizio;
	}
	public String getNr_fattura_fornitore() {
		return nr_fattura_fornitore;
	}
	public String getPartita_iva() {
		return partita_iva;
	}
	public long getPg_fattura_passiva() {
		return pg_fattura_passiva;
	}
	public String getTipoFatturaCompenso() {
		return tipoFatturaCompenso;
	}
	
	protected Calendar getCalendar(long milliseconds) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(milliseconds);
		return cal;		
	}
}
