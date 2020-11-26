package it.cnr.brevetti.sigla.fatture.attive;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

import com.google.gson.annotations.SerializedName;

/**
 * Tracciato delle fatture attive con conversione di nomi e tipi XML/JSON
 * 
 * @author Aurelio D'Amico
 * @version 1.0 [Sep 22, 2016]
 *
 */
public class FatturaAttiva extends FatturaAttivaBase implements Serializable {
	private static final long serialVersionUID = 1L;

// Nuovo tracciato JSON 
/*
{
 "cd_cds":"999",
 "cd_unita_organizzativa":"999.000",
 "esercizio":2012,
 "pg_fattura_attiva":8058,
 "progressivo_riga":1,
 "dsFatturaAttiva":"Brevetto n. TO2006A000778",
 "cdCdsOrigine":"507",
 "cdUoOrigine":"507.000",
 "tiFattura":"F",
 "dtRegistrazione":1334793600000,
 "cdTerzo":145808,
 "cognome":null,
 "nome":null,
 "ragioneSociale":"LPRO srl",
 "cambio":1,
 "cdDivisa":"EURO",
 "cdVoceIva":"21%",
 "dsVoceIva":"ALIQUOTA IVA 21%",
 "dsRigaFattura":"Brevetto n. TO2006A000778",
 "pgTrovato":10082,
 "imImponibile":2298,
 "fcIva":0,
 "imIva":482.7,
 "esercizioAccertamento":2012,
 "pgAccertamento":19273,
 "dtEmissioneAccertamento":1334534400000,
 "esercizioReversale":2012,
 "pgReversale":11127,
 "dtEmissioneReversale":1346071622000
}
*/

// Vecchio tracciato XML	
/*
<xs:complexType name="FatturaAttiva">
<xs:complexContent>
    <xs:extension base="ax29:FatturaAttivaBase">
        <xs:sequence>
            <xs:element minOccurs="0" name="cambio" nillable="true" type="xs:decimal"/>
            <xs:element minOccurs="0" name="cd_divisa" nillable="true" type="xs:string"/>
            <xs:element minOccurs="0" name="cd_voce_iva" nillable="true" type="xs:string"/>
          * <xs:element minOccurs="0" name="codice_fiscale" nillable="true" type="xs:string"/>
            <xs:element minOccurs="0" name="cognome" nillable="true" type="xs:string"/>
            <xs:element minOccurs="0" name="ds_riga_fattura" nillable="true" type="xs:string"/>
            <xs:element minOccurs="0" name="ds_voce_iva" nillable="true" type="xs:string"/>
            <xs:element minOccurs="0" name="dt_emissione_reversale" nillable="true" type="xs:date"/>
            <xs:element minOccurs="0" name="dt_registrazione" nillable="true" type="xs:date"/>
            <xs:element minOccurs="0" name="esercizio_reversale" nillable="true" type="xs:int"/>
            <xs:element minOccurs="0" name="im_imponibile" nillable="true" type="xs:decimal"/>
            <xs:element minOccurs="0" name="im_iva" nillable="true" type="xs:decimal"/>
            <xs:element minOccurs="0" name="nome" nillable="true" type="xs:string"/>
          * <xs:element minOccurs="0" name="partita_iva" nillable="true" type="xs:string"/>
            <xs:element minOccurs="0" name="pg_reversale" nillable="true" type="xs:long"/>
            <xs:element minOccurs="0" name="pg_trovato" nillable="true" type="xs:long"/>
            <xs:element minOccurs="0" name="progressivo_riga" nillable="true" type="xs:long"/>
            <xs:element minOccurs="0" name="ragione_sociale" nillable="true" type="xs:string"/>
            <xs:element minOccurs="0" name="ti_fattura" nillable="true" type="xs:string"/>        
        </xs:sequence>
    </xs:extension>
</xs:complexContent>
</xs:complexType>
*/	

	@SerializedName("cambio")
	private double cambio;
	@SerializedName("cdDivisa")
	private String cd_divisa;
	@SerializedName("cdVoceIva")
	private String cd_voce_iva;
	@SerializedName("cognome")
	private String cognome;
	@SerializedName("dsRigaFattura")
	private String ds_riga_fattura;
	@SerializedName("dsVoceIva")
	private String ds_voce_iva;
	@SerializedName("dtEmissioneAccertamento")
	private long dt_emissione_accertamento;
	@SerializedName("dtEmissioneReversale")
	private long dt_emissione_reversale;
	@SerializedName("dtRegistrazione")
	private long dt_registrazione;
	@SerializedName("esercizioAccertamento")
	private int esercizio_accertamento;
	@SerializedName("esercizioReversale")
	private int esercizio_reversale;
	@SerializedName("imImponibile")
	private double im_imponibile;
	@SerializedName("imIva")
	private double im_iva;
	@SerializedName("fcIva")
	private double fcIva;	
	@SerializedName("nome")
	private String nome;
	@SerializedName("pgAccertamento")
	private long pg_accertamento;
	@SerializedName("pgReversale")
	private long pg_reversale;
	@SerializedName("pgTrovato")
	private long pg_trovato;
	@SerializedName("progressivo_riga")
	private long progressivo_riga;	
	@SerializedName("ragioneSociale")
	private String ragione_sociale;
	@SerializedName("tiFattura")
	private String ti_fattura;
	@SerializedName("codice_fiscale")
	private String codice_fiscale;
	@SerializedName("partita_iva")
	private String partita_iva;
	
	// SETTERS per la deserializzazione da REST
	
	public void setCambio(double cambio) {
		this.cambio = cambio;
	}
	public void setCd_divisa(String cd_divisa) {
		this.cd_divisa = cd_divisa;
	}
	public void setCd_voce_iva(String cd_voce_iva) {
		this.cd_voce_iva = cd_voce_iva;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public void setDs_riga_fattura(String ds_riga_fattura) {
		this.ds_riga_fattura = ds_riga_fattura;
	}
	public void setDs_voce_iva(String ds_voce_iva) {
		this.ds_voce_iva = ds_voce_iva;
	}
	public void setDt_emissione_accertamento(long dt_emissione_accertamento) {
		this.dt_emissione_accertamento = dt_emissione_accertamento;
	}
	public void setDt_emissione_reversale(long dt_emissione_reversale) {
		this.dt_emissione_reversale = dt_emissione_reversale;
	}
	public void setDt_registrazione(long dt_registrazione) {
		this.dt_registrazione = dt_registrazione;
	}
	public void setEsercizio_accertamento(int esercizio_accertamento) {
		this.esercizio_accertamento = esercizio_accertamento;
	}
	public void setEsercizio_reversale(int esercizio_reversale) {
		this.esercizio_reversale = esercizio_reversale;
	}
	public void setIm_imponibile(double im_imponibile) {
		this.im_imponibile = im_imponibile;
	}
	public void setIm_iva(double im_iva) {
		this.im_iva = im_iva;
	}
	public void setFcIva(double fcIva) {
		this.fcIva = fcIva;
	}	
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setPg_accertamento(long pg_accertamento) {
		this.pg_accertamento = pg_accertamento;
	}
	public void setPg_reversale(long pg_reversale) {
		this.pg_reversale = pg_reversale;
	}
	public void setPg_trovato(long pg_trovato) {
		this.pg_trovato = pg_trovato;
	}
	public void setProgressivo_riga(long progressivo_riga) {
		this.progressivo_riga = progressivo_riga;
	}
	public void setRagione_sociale(String ragione_sociale) {
		this.ragione_sociale = ragione_sociale;
	}
	public void setTi_fattura(String ti_fattura) {
		this.ti_fattura = ti_fattura;
	}
	public void setCodice_fiscale(String codice_fiscale) {
		this.codice_fiscale = codice_fiscale;
	}
	public void setPartita_iva(String partita_iva) {
		this.partita_iva = partita_iva;
	}
	
	// GETTERS per la compatibilità al precedente tracciato XML
		
	public double getCambio() {
		return cambio;
	}
	public String getCd_divisa() {
		return cd_divisa;
	}
	public String getCd_voce_iva() {
		return cd_voce_iva;
	}
	public String getCognome() {
		return cognome;
	}
	public String getDs_riga_fattura() {
		return ds_riga_fattura;
	}
	public String getDs_voce_iva() {
		return ds_voce_iva;
	}
	public Calendar getDt_emissione_accertamento() {
		return getCalendar(dt_emissione_accertamento);
	}
	public Calendar getDt_emissione_reversale() {
		return getCalendar(dt_emissione_reversale);
	}
	public Calendar getDt_registrazione() {
		return getCalendar(dt_registrazione);
	}
	public int getEsercizio_accertamento() {
		return esercizio_accertamento;
	}
	public int getEsercizio_reversale() {
		return esercizio_reversale;
	}
	public BigDecimal getIm_imponibile() {
		return new BigDecimal(im_imponibile);
	}
	public BigDecimal getIm_iva() {
		return new BigDecimal(im_iva);
	}
	public BigDecimal getFcIva() {
		return new BigDecimal(fcIva);
	}	
	public String getNome() {
		return nome;
	}
	public long getPg_accertamento() {
		return pg_accertamento;
	}
	public long getPg_reversale() {
		return pg_reversale;
	}
	public long getPg_trovato() {
		return pg_trovato;
	}
	public long getProgressivo_riga() {
		return progressivo_riga;
	}
	public String getRagione_sociale() {
		return ragione_sociale;
	}
	public String getTi_fattura() {
		return ti_fattura;
	}
	public String getCodice_fiscale() {
		return codice_fiscale;
	}
	public String getPartita_iva() {
		return partita_iva;
	}	
}
