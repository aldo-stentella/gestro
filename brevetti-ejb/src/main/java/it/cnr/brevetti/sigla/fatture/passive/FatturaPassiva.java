package it.cnr.brevetti.sigla.fatture.passive;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

import com.google.gson.annotations.SerializedName;


	/**
	 * Tracciato delle fatture passive con conversione di nomi e tipi XML/JSON
	 * 
	 * @author Aurelio D'Amico
	 * @version 1.0 [Sep 21, 2016]
	 *
	 */
	public class FatturaPassiva extends FatturaPassivaBase implements Serializable {
		private static final long serialVersionUID = 1L;

// Nuovo tracciato JSON 
/*
{
 "cd_cds":"000",
 "cd_unita_organizzativa":"000.106",
 "esercizio":2015,
 "pg_fattura_passiva":2,
 "progressivo_riga":2,
 "dsFatturaPassiva":"NS. RIF.10326",
 "cdCdsOrigine":"000",
 "cdUoOrigine":"000.106",
 "tipoFatturaCompenso":"FATTURA",
 "dtRegistrazione":1421107200000,
 "cdTerzo":187638,
 "cognome":null,
 "nome":null,
 "ragioneSociale":"PONS PATENTS Y MARCAS INTERNACIONAL S.L.",
 "cambio":1,
 "cdDivisa":"EURO",
 "cdVoceIva":"FC",
 "dsVoceIva":"FUORI CAMPO IVA",
 "dsRigaFattura":"Servizi di cui all' articolo 7 TER",
 "pgTrovato":10326,
 "imImponibile":0,
 "imLordoPercipiente":0,
 "imNettoPercipiente":0,
 "imTotaleCompenso":0,
 "fcIva":31,
 "imIva":0,
 "esercizioObbligazione":2010,
 "pgObbligazione":9800002331,
 "dtEmissioneObbligazione":1420070400000,
 "esercizioMandato":2015,
 "pgMandato":174,
 "dtEmissioneMandato":1421923155000,
 "nr_fattura_fornitore":"P-FAC-0-00-002605",
 "dt_fattura_fornitore":1406073600000,
 "dt_pagamento_fondo_eco":null,
 "stato_pagamento_fondo_eco":"N",
 "partita_iva":"B84921709",
 "codice_fiscale":"B84921709"
}
*/
		
// Vecchio tracciato XML
/*
<xs:complexType name="FatturaPassiva">
    <xs:complexContent>
        <xs:extension base="ax213:FatturaPassivaBase">
            <xs:sequence>
                <xs:element minOccurs="0" name="cambio" nillable="true" type="xs:decimal"/>
                <xs:element minOccurs="0" name="cd_divisa" nillable="true" type="xs:string"/>
              * <xs:element minOccurs="0" name="cd_terzo_cessionario" nillable="true" type="xs:int"/>
                <xs:element minOccurs="0" name="cd_voce_iva" nillable="true" type="xs:string"/>
                <xs:element minOccurs="0" name="cognome" nillable="true" type="xs:string"/>
                <xs:element minOccurs="0" name="ds_riga_fattura" nillable="true" type="xs:string"/>
                <xs:element minOccurs="0" name="ds_voce_iva" nillable="true" type="xs:string"/>
                <xs:element minOccurs="0" name="dt_emissione_mandato" nillable="true" type="xs:date"/>
                <xs:element minOccurs="0" name="dt_pagamento_fondo_eco" nillable="true" type="xs:date"/>
                <xs:element minOccurs="0" name="dt_registrazione" nillable="true" type="xs:date"/>
                <xs:element minOccurs="0" name="esercizio_mandato" nillable="true" type="xs:int"/>
                <xs:element minOccurs="0" name="im_imponibile" nillable="true" type="xs:decimal"/>
                <xs:element minOccurs="0" name="im_iva" nillable="true" type="xs:decimal"/>
                <xs:element minOccurs="0" name="nome" nillable="true" type="xs:string"/>
                <xs:element minOccurs="0" name="pg_mandato" nillable="true" type="xs:long"/>
                <xs:element minOccurs="0" name="pg_trovato" nillable="true" type="xs:long"/>
                <xs:element minOccurs="0" name="progressivo_riga" nillable="true" type="xs:long"/>
                <xs:element minOccurs="0" name="ragione_sociale" nillable="true" type="xs:string"/>
                <xs:element minOccurs="0" name="stato_pagamento_fondo_eco" nillable="true" type="xs:string"/>
              * <xs:element minOccurs="0" name="ti_fattura" nillable="true" type="xs:string"/>
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
	@SerializedName("dtEmissioneMandato")
	private long dt_emissione_mandato;
	@SerializedName("dt_pagamento_fondo_eco")
	private long dt_pagamento_fondo_eco;
	@SerializedName("dtRegistrazione")
	private long dt_registrazione;
	@SerializedName("esercizioMandato")
	private int esercizio_mandato;
	@SerializedName("imImponibile")
	private double im_imponibile;
	@SerializedName("imIva")
	private double im_iva;
	@SerializedName("fcIva")
	private double fcIva;
	@SerializedName("nome")
	private String nome;
	@SerializedName("pgMandato")
	private long pg_mandato;
	@SerializedName("pgTrovato")
	private long pg_trovato;
	@SerializedName("progressivo_riga")
	private long progressivo_riga;
	@SerializedName("ragioneSociale")
	private String ragione_sociale;
	@SerializedName("stato_pagamento_fondo_eco")
	private String stato_pagamento_fondo_eco;
	
	// MISSING and FIXED
	@SerializedName("dtEmissioneObbligazione")
	private long dt_emissione_obbligazione_impegno;
	@SerializedName("esercizioObbligazione")
	private int esercizio_obbligazione_impegno;
	@SerializedName("pgObbligazione")
	private long pg_obbligazione_impegno;

	// SETTERs per la deserializzazione JSON		
	
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
	public void setDt_emissione_mandato(long dt_emissione_mandato) {
		this.dt_emissione_mandato = dt_emissione_mandato;
	}
	public void setDt_pagamento_fondo_eco(long dt_pagamento_fondo_eco) {
		this.dt_pagamento_fondo_eco = dt_pagamento_fondo_eco;
	}
	public void setDt_registrazione(long dt_registrazione) {
		this.dt_registrazione = dt_registrazione;
	}
	public void setEsercizio_mandato(int esercizio_mandato) {
		this.esercizio_mandato = esercizio_mandato;
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
	public void setPg_mandato(long pg_mandato) {
		this.pg_mandato = pg_mandato;
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
	public void setStato_pagamento_fondo_eco(String stato_pagamento_fondo_eco) {
		this.stato_pagamento_fondo_eco = stato_pagamento_fondo_eco;
	}
	// MISSING and FIXED
	public void setDt_emissione_obbligazione_impegno(long dt_emissione_obbligazione_impegno) {
		this.dt_emissione_obbligazione_impegno = dt_emissione_obbligazione_impegno;
	}
	public void setEsercizio_obbligazione_impegno(int esercizio_obbligazione_impegno) {
		this.esercizio_obbligazione_impegno = esercizio_obbligazione_impegno;
	}
	public void setPg_obbligazione_impegno(long pg_obbligazione_impegno) {
		this.pg_obbligazione_impegno = pg_obbligazione_impegno;
	}

	// GETTERs per la compatibilità con il tracciato precedente
	
	public BigDecimal getCambio() {
		return new BigDecimal(cambio);
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
	public Calendar getDt_emissione_mandato() {
		return getCalendar(dt_emissione_mandato);
	}
	public Calendar getDt_pagamento_fondo_eco() {
		return getCalendar(dt_pagamento_fondo_eco);
	}
	public Calendar getDt_registrazione() {
		return getCalendar(dt_registrazione);
	}
	public int getEsercizio_mandato() {
		return esercizio_mandato;
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
	public long getPg_mandato() {
		return pg_mandato;
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
	public String getStato_pagamento_fondo_eco() {
		return stato_pagamento_fondo_eco;
	}
	// MISSING and FIXED
	public Calendar getDt_emissione_obbligazione_impegno() {
		return getCalendar(dt_emissione_obbligazione_impegno);
	}
	public int getEsercizio_obbligazione_impegno() {
		return esercizio_obbligazione_impegno;
	}
	public long getPg_obbligazione_impegno() {
		return pg_obbligazione_impegno;		
	}
	public String getTi_fattura() {
		return getTipoFatturaCompenso();
	}
	// not used
	public int getCd_terzo_cessionario() {
		return 0;
	}
}
