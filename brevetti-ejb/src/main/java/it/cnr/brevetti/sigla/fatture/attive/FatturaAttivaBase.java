package it.cnr.brevetti.sigla.fatture.attive;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Tracciato delle fatture attive di base con conversione di nomi e tipi XML/JSON
 * 
 * @author Aurelio D'Amico
 * @version 1.0 [Sep 22, 2016]
 *
 */
public class FatturaAttivaBase implements Serializable {
	private static final long serialVersionUID = 1L;

// Nuovo tracciato JSON 
/*
{
 "cd_cds":"999",
 "cd_unita_organizzativa":"999.000",
 "esercizio":2012,
 "pg_fattura_attiva":8058,
 "dsFatturaAttiva":"Brevetto n. TO2006A000778",
 "cdCdsOrigine":"507",
 "cdUoOrigine":"507.000",
 "tipoFattura":"F",
 "dtRegistrazione":1334793600000,
 "cdTerzo":145808,
 "cognome":null,
 "nome":null,
 "ragioneSociale":"LPRO srl",
 "cambio":1,
 "cdDivisa":"EURO"
}
*/

// Vecchio tracciato XML	
/*
<xs:complexType name="FatturaAttivaBase">
    <xs:sequence>
        <xs:element minOccurs="0" name="cd_cds" nillable="true" type="xs:string"/>
        <xs:element minOccurs="0" name="cd_cds_origine" nillable="true" type="xs:string"/>
        <xs:element minOccurs="0" name="cd_unita_organizzativa" nillable="true" type="xs:string"/>
        <xs:element minOccurs="0" name="cd_uo_origine" nillable="true" type="xs:string"/>
        <xs:element minOccurs="0" name="cd_terzo" nillable="true" type="xs:int"/>        
        <xs:element minOccurs="0" name="ds_fattura_attiva" nillable="true" type="xs:string"/>
      * <xs:element minOccurs="0" name="dt_emissione" nillable="true" type="xs:date"/>
        <xs:element minOccurs="0" name="esercizio" nillable="true" type="xs:int"/>
        <xs:element minOccurs="0" name="pg_fattura_attiva" nillable="true" type="xs:long"/>
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
	@SerializedName("dsFatturaAttiva")
	private String ds_fattura_attiva;
	@SerializedName("esercizio")
	private int esercizio;
	@SerializedName("pg_fattura_attiva")
	private long pg_fattura_attiva;
	@SerializedName("dt_emissione")
	private long dt_emissione;
	
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
	public void setCd_unita_organizzativa(String cd_unita_organizzativa) {
		this.cd_unita_organizzativa = cd_unita_organizzativa;
	}
	public void setCd_uo_origine(String cd_uo_origine) {
		this.cd_uo_origine = cd_uo_origine;
	}
	public void setDs_fattura_attiva(String ds_fattura_attiva) {
		this.ds_fattura_attiva = ds_fattura_attiva;
	}
	public void setEsercizio(int esercizio) {
		this.esercizio = esercizio;
	}
	public void setPg_fattura_attiva(long pg_fattura_attiva) {
		this.pg_fattura_attiva = pg_fattura_attiva;
	}
	public void setDt_emissione(long dt_emissione) {
		this.dt_emissione = dt_emissione;
	}

	// GETTERS per la compatibilità al precedente tracciato XML
		
	public Calendar getDt_emissione() {
		return getCalendar(dt_emissione);
	}
	public String getCd_cds() {
		return cd_cds;
	}
	public String getCd_cds_origine() {
		return cd_cds_origine;
	}
	public int getCd_terzo() {
		return cd_terzo;
	}	
	public String getCd_unita_organizzativa() {
		return cd_unita_organizzativa;
	}
	public String getCd_uo_origine() {
		return cd_uo_origine;
	}
	public String getDs_fattura_attiva() {
		return ds_fattura_attiva;
	}
	public int getEsercizio() {
		return esercizio;
	}
	public long getPg_fattura_attiva() {
		return pg_fattura_attiva;
	}

	protected Calendar getCalendar(long milliseconds) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(milliseconds);
		return cal;		
	}
	
}
