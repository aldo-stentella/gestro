package it.cnr.brevetti.depositi.actionForms;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;

import it.cnr.brevetti.util.UtilityFunctions;
import it.cnr.brevetti.validators.AbstractValidatorForm;

public class DepositoForm extends AbstractValidatorForm {

	private static final long serialVersionUID = 1L;

		private Integer id;
		private Integer nsrif;
		private Integer statoId;
		private String  numeroDeposito;
		private String dataDepositoX;
		private Integer idiomiId;
		private String  numPubblicazione;
		private String dataPubblicazioneX;
		private String  numRilascioDep;
		private String dataRilascioDepX;
		private String dataAbbandonoX;
		private String  notaAbbandono;
		private Integer primo;
		private Integer studioBrevettualeId;
		private String denom;
		private String note;
		private Integer tipoId;
		private Long key;
		private String[] listaNaz;
		private String[] listaReg;
		private Integer paesi;
		private String titolariId0;
		private String titolariId1;
		private String titolariId2;	
		private String titolariId3;
		private String titolariId4;
		private String titolariId5;
		private String titolariId6;	
		private String titolariId7;
		private String titolariId8;
		private String titolariId9;
		private String titolariId10;
		private String titolariId11;
		private String titolariId12;	
		private String titolariId13;
		private String titolariId14;	
		private BigDecimal percent0;
		private BigDecimal percent1;
		private BigDecimal percent2;
		private BigDecimal percent3;
		private BigDecimal percent4;
		private BigDecimal percent5;
		private BigDecimal percent6;
		private BigDecimal percent7;
		private BigDecimal percent8;
		private BigDecimal percent9;
		private BigDecimal percent10;
		private BigDecimal percent11;
		private BigDecimal percent12;
		private BigDecimal percent13;
		private BigDecimal percent14;
		private Integer tipiDataUfficialeId;
		private String dataUfficialeX;
		private Integer anniValidita;
		private Integer tipiEstinzioneId;
		
		public String getTitolariId0() {
			return titolariId0;
		}
		public void setTitolariId0(String titolariId0) {
			this.titolariId0 = titolariId0;
		}
		public String getTitolariId1() {
			return titolariId1;
		}
		public void setTitolariId1(String titolariId1) {
			this.titolariId1 = titolariId1;
		}
		public String getTitolariId2() {
			return titolariId2;
		}
		public void setTitolariId2(String titolariId2) {
			this.titolariId2 = titolariId2;
		}
		public String getTitolariId3() {
			return titolariId3;
		}
		public void setTitolariId3(String titolariId3) {
			this.titolariId3 = titolariId3;
		}
		public String getTitolariId4() {
			return titolariId4;
		}
		public void setTitolariId4(String titolariId4) {
			this.titolariId4 = titolariId4;
		}
		public String getTitolariId5() {
			return titolariId5;
		}
		public void setTitolariId5(String titolariId5) {
			this.titolariId5 = titolariId5;
		}
		public String getTitolariId6() {
			return titolariId6;
		}
		public void setTitolariId6(String titolariId6) {
			this.titolariId6 = titolariId6;
		}
		public String getTitolariId7() {
			return titolariId7;
		}
		public void setTitolariId7(String titolariId7) {
			this.titolariId7 = titolariId7;
		}
		public String getTitolariId8() {
			return titolariId8;
		}
		public void setTitolariId8(String titolariId8) {
			this.titolariId8 = titolariId8;
		}
		public String getTitolariId9() {
			return titolariId9;
		}
		public void setTitolariId9(String titolariId9) {
			this.titolariId9 = titolariId9;
		}
		public String getTitolariId10() {
			return titolariId10;
		}
		public void setTitolariId10(String titolariId10) {
			this.titolariId10 = titolariId10;
		}
		public String getTitolariId11() {
			return titolariId11;
		}
		public void setTitolariId11(String titolariId11) {
			this.titolariId11 = titolariId11;
		}
		public String getTitolariId12() {
			return titolariId12;
		}
		public void setTitolariId12(String titolariId12) {
			this.titolariId12 = titolariId12;
		}
		public String getTitolariId13() {
			return titolariId13;
		}
		public void setTitolariId13(String titolariId13) {
			this.titolariId13 = titolariId13;
		}
		public String getTitolariId14() {
			return titolariId14;
		}
		public void setTitolariId14(String titolariId14) {
			this.titolariId14 = titolariId14;
		}
		public BigDecimal getPercent0() {
			return percent0;
		}
		public void setPercent0(BigDecimal percent0) {
			this.percent0 = percent0;
		}
		public BigDecimal getPercent1() {
			return percent1;
		}
		public void setPercent1(BigDecimal percent1) {
			this.percent1 = percent1;
		}
		public BigDecimal getPercent2() {
			return percent2;
		}
		public void setPercent2(BigDecimal percent2) {
			this.percent2 = percent2;
		}
		public BigDecimal getPercent3() {
			return percent3;
		}
		public void setPercent3(BigDecimal percent3) {
			this.percent3 = percent3;
		}
		public BigDecimal getPercent4() {
			return percent4;
		}
		public void setPercent4(BigDecimal percent4) {
			this.percent4 = percent4;
		}
		public BigDecimal getPercent5() {
			return percent5;
		}
		public void setPercent5(BigDecimal percent5) {
			this.percent5 = percent5;
		}
		public BigDecimal getPercent6() {
			return percent6;
		}
		public void setPercent6(BigDecimal percent6) {
			this.percent6 = percent6;
		}
		public BigDecimal getPercent7() {
			return percent7;
		}
		public void setPercent7(BigDecimal percent7) {
			this.percent7 = percent7;
		}
		public BigDecimal getPercent8() {
			return percent8;
		}
		public void setPercent8(BigDecimal percent8) {
			this.percent8 = percent8;
		}
		public BigDecimal getPercent9() {
			return percent9;
		}
		public void setPercent9(BigDecimal percent9) {
			this.percent9 = percent9;
		}
		public BigDecimal getPercent10() {
			return percent10;
		}
		public void setPercent10(BigDecimal percent10) {
			this.percent10 = percent10;
		}
		public BigDecimal getPercent11() {
			return percent11;
		}
		public void setPercent11(BigDecimal percent11) {
			this.percent11 = percent11;
		}
		public BigDecimal getPercent12() {
			return percent12;
		}
		public void setPercent12(BigDecimal percent12) {
			this.percent12 = percent12;
		}
		public BigDecimal getPercent13() {
			return percent13;
		}
		public void setPercent13(BigDecimal percent13) {
			this.percent13 = percent13;
		}
		public BigDecimal getPercent14() {
			return percent14;
		}
		public void setPercent14(BigDecimal percent14) {
			this.percent14 = percent14;
		}
		public Date getDataAbbandono() {
			try {
				return UtilityFunctions.itForm.parse(dataAbbandonoX);
			} catch (ParseException e) {
				return null;
			} catch (NullPointerException e) {
				return null;
			}
		}
		public void setDataAbbandono(Date dataAbbandono) {
			try {
				this.dataAbbandonoX = UtilityFunctions.itForm.format(dataAbbandono);
			} catch (NullPointerException e) {
				this.dataAbbandonoX = null;
			}
		}
		public Date getDataDeposito() {
			try {
				return UtilityFunctions.itForm.parse(dataDepositoX);
			} catch (ParseException e) {
				return null;
			} catch (NullPointerException e) {
				return null;
			}
		}
		public void setDataDeposito(Date dataDeposito) {
			try {
				this.dataDepositoX = UtilityFunctions.itForm.format(dataDeposito);
			} catch (NullPointerException e) {
				this.dataDepositoX = null;
			}
		}
		public Date getDataPubblicazione() {
			try {
				return UtilityFunctions.itForm.parse(dataPubblicazioneX);
			} catch (ParseException e) {
				return null;
			} catch (NullPointerException e) {
				return null;
			}
		}
		public void setDataPubblicazione(Date dataPubblicazione) {
			try {
				this.dataPubblicazioneX = UtilityFunctions.itForm.format(dataPubblicazione);
			} catch (NullPointerException e) {
				this.dataPubblicazioneX = null;
			}
		}
		public Date getDataRilascioDep() {
			try {
				return UtilityFunctions.itForm.parse(dataRilascioDepX);
			} catch (ParseException e) {
				return null;
			} catch (NullPointerException e) {
				return null;
			}
		}
		public void setDataRilascioDep(Date dataRilascioDep) {
			try {
				this.dataRilascioDepX = UtilityFunctions.itForm.format(dataRilascioDep);
			} catch (NullPointerException e) {
				this.dataRilascioDepX = null;
			}
		}
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public Integer getIdiomiId() {
			return idiomiId;
		}
		public void setIdiomiId(Integer idiomiId) {
			this.idiomiId = idiomiId;
		}
		public Integer getStatoId() {
			return statoId;
		}
		public void setStatoId(Integer statoId) {
			this.statoId = statoId;
		}
		public String getNotaAbbandono() {
			return notaAbbandono;
		}
		public void setNotaAbbandono(String notaAbbandono) {
			this.notaAbbandono = notaAbbandono;
		}
		public Integer getNsrif() {
			return nsrif;
		}
		public void setNsrif(Integer nsrif) {
			this.nsrif = nsrif;
		}
		public String getNumeroDeposito() {
			return numeroDeposito;
		}
		public void setNumeroDeposito(String numeroDeposito) {
			this.numeroDeposito = numeroDeposito;
		}
		public String getNumPubblicazione() {
			return numPubblicazione;
		}
		public void setNumPubblicazione(String numPubblicazione) {
			this.numPubblicazione = numPubblicazione;
		}
		public String getNumRilascioDep() {
			return numRilascioDep;
		}
		public void setNumRilascioDep(String numRilascioDep) {
			this.numRilascioDep = numRilascioDep;
		}
		public Integer getPrimo() {
			return primo;
		}
		public void setPrimo(Integer primo) {
			this.primo = primo;
		}
		public Integer getStudioBrevettualeId() {
			return studioBrevettualeId;
		}
		public void setStudioBrevettualeId(Integer studioBrevettualeId) {
			this.studioBrevettualeId = studioBrevettualeId;
		}
		public String getNote() {
			return note;
		}
		public void setNote(String note) {
			this.note = note;
		}
		public Integer getTipoId() {
			return tipoId;
		}
		public void setTipoId(Integer tipoId) {
			this.tipoId = tipoId;
		}
		public String getDenom() {
			return denom;
		}
		public void setDenom(String denom) {
			this.denom = denom;
		}
		public Long getKey() {
			return key;
		}
		public void setKey(Long key) {
			this.key = key;
		}
		public String[] getListaNaz() {
			return listaNaz;
		}
		public void setListaNaz(String[] listaNaz) {
			this.listaNaz = listaNaz;
		}
		public String[] getListaReg() {
			return listaReg;
		}
		public void setListaReg(String[] listaReg) {
			this.listaReg = listaReg;
		}
		public String getDataDepositoX() {
			return dataDepositoX;
		}
		public void setDataDepositoX(String dataDepositoX) {
			this.dataDepositoX = dataDepositoX;
		}
		public String getDataPubblicazioneX() {
			return dataPubblicazioneX;
		}
		public void setDataPubblicazioneX(String dataPubblicazioneX) {
			this.dataPubblicazioneX = dataPubblicazioneX;
		}
		public String getDataRilascioDepX() {
			return dataRilascioDepX;
		}
		public void setDataRilascioDepX(String dataRilascioDepX) {
			this.dataRilascioDepX = dataRilascioDepX;
		}
		public String getDataAbbandonoX() {
			return dataAbbandonoX;
		}
		public void setDataAbbandonoX(String dataAbbandonoX) {
			this.dataAbbandonoX = dataAbbandonoX;
		}
		public Integer getPaesi() {
			return paesi;
		}
		public void setPaesi(Integer paesi) {
			this.paesi = paesi;
		}
		public Integer getTipiDataUfficialeId() {
			return tipiDataUfficialeId;
		}
		public void setTipiDataUfficialeId(Integer tipiDataUfficialeId) {
			this.tipiDataUfficialeId = tipiDataUfficialeId;
		}
		public Date getDataUfficiale() {
			try {
				return UtilityFunctions.itForm.parse(dataUfficialeX);
			} catch (ParseException e) {
				return null;
			} catch (NullPointerException e) {
				return null;
			}
		}
		public void setDataUfficiale(Date dataUfficiale) {
			try {
				this.dataUfficialeX = UtilityFunctions.itForm.format(dataUfficiale);
			} catch (NullPointerException e) {
				this.dataUfficialeX = null;
			}
		}
		public String getDataUfficialeX() {
			return dataUfficialeX;
		}
		public void setDataUfficialeX(String dataUfficialeX) {
			this.dataUfficialeX = dataUfficialeX;
		}
		public Integer getAnniValidita() {
			return anniValidita;
		}
		public void setAnniValidita(Integer anniValidita) {
			this.anniValidita = anniValidita;
		}
		public Integer getTipiEstinzioneId() {
			return tipiEstinzioneId;
		}
		public void setTipiEstinzioneId(Integer tipiEstinzioneId) {
			this.tipiEstinzioneId = tipiEstinzioneId;
		}
		
}
