package it.cnr.brevetti.trovati.actionForms;

import it.cnr.brevetti.util.UtilityFunctions;
import it.cnr.brevetti.validators.AbstractValidatorForm;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

/**
 * @author astentella
 * 
 *
 */
public class TrovatoForm extends AbstractValidatorForm {
	
	@Override
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		if(nextab==0)
			return super.validate(mapping, request);
		else
			return new ActionErrors();
	}

	private static final long serialVersionUID = 1L;
	
	private int nextab = 1;

	private Integer nsrif;
	private Integer dipartimentoTitolare;
	private Integer tipiTrovatoId;
	private String tipiTrovatoDescrizione;
	private String  titolo;
	private String  descrizione;
	private String  usi;
	private String  vantaggi;
	private String  parolaChiave;
	private String  titoloInglese;
	private String  descrizioneInglese;
	private String  usiInglese;
	private String  vantaggiInglese;
	private String  parolaChiaveInglese;
	private Integer statoPraticaId;
	private String  note;
	private Integer cessioneDiritti;
	private String cessioneDirittiDescrizione;
	private String dataRichiesta; //data_dom_rich_deposito
	private String inventore00;
	private String inventore01;
	private String inventore02;
	private String inventore03;
	private String inventore04;
	private String inventore05;
	private String inventore06;
	private String inventore07;
	private String inventore08;
	private String inventore09;
	private String inventore10;
	private String inventore11;
	private String inventore12;
	private String inventore13;
	private String inventore14;
	private Integer inventoriId00;
	private Integer inventoriId01;
	private Integer inventoriId02;
	private Integer inventoriId03;
	private Integer inventoriId04;
	private Integer inventoriId05;
	private Integer inventoriId06;
	private Integer inventoriId07;
	private Integer inventoriId08;
	private Integer inventoriId09;
	private Integer inventoriId10;
	private Integer inventoriId11;
	private Integer inventoriId12;
	private Integer inventoriId13;
	private Integer inventoriId14;

	private Integer inventoreIndex;
	private String invRifDescrizione;
	private String istituto0;
	private String istituto1;
	private String istituto2;	
	private String istituto3;
	private Integer istitutiId0;
	private Integer istitutiId1;
	private Integer istitutiId2;	
	private Integer istitutiId3;

	private String titolare0;
	private String titolare1;
	private String titolare2;
	private String titolare3;
	private String titolare4;
	private String titolare5;
	private String titolare6;
	private String titolare7;
	private String titolare8;
	private String titolare9;
	private String titolare10;
	private String titolare11;
	private String titolare12;
	private String titolare13;
	private String titolare14;
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
	private Integer updClas;
	private Integer updTecn;
	private Integer updTit;
	private Integer updIst;
	private Integer updInv;
	private Integer updDep;
	private Integer updDoc;
	private Integer dipartimentiId;
	private String dipartimentoNome;
	private String utcr;
	private String utva;
	private String dataCreazione;
	private String dataVariazione;
	private Integer rev;
	private String utentiId;
	private String utentiNominativo;
	private Integer provvisorio;
	private Integer pubblicato = 1;
	private Integer respinto;
	private String notaRespinto;

	public void reset(ActionMapping arg0, HttpServletRequest arg1) {
	System.out.println("...invocato metodo reset() su TrovatoForm");
	super.reset(arg0, arg1);
	}
	
	public void reset(ActionMapping arg0, ServletRequest arg1) {
		System.out.println("...invocato metodo reset() su TrovatoForm");
		super.reset(arg0, arg1);
	}

	public int getNextab() {
		return nextab;
	}

	public void setNextab(int nextab) {
		this.nextab = nextab;
	}


	public Integer getCessioneDiritti() {
		return cessioneDiritti;
	}

	public void setCessioneDiritti(Integer cessioneDiritti) {
		this.cessioneDiritti = cessioneDiritti;
	}

	public Date getDataDomRichDeposito() {
		try {
			return UtilityFunctions.itForm.parse(dataRichiesta);
		} catch (ParseException e) {
			return null;
		} catch (NullPointerException e) {
			return null;
		}
	}
	
	public void setDataDomRichDeposito(Date date) {
		try {
			dataRichiesta = UtilityFunctions.itForm.format(date);
		} catch (NullPointerException e) {
			dataRichiesta = null;
		}
	}
	
	public String getDataRichiesta() {
		return dataRichiesta;
	}

	public void setDataRichiesta(String dataRichiesta) {
		this.dataRichiesta = dataRichiesta;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getDescrizioneInglese() {
		return descrizioneInglese;
	}

	public void setDescrizioneInglese(String descrizioneInglese) {
		this.descrizioneInglese = descrizioneInglese;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Integer getNsrif() {
		return nsrif;
	}

	public void setNsrif(Integer nsrif) {
		this.nsrif = nsrif;
	}

	public String getParolaChiave() {
		return parolaChiave;
	}

	public void setParolaChiave(String parolaChiave) {
		this.parolaChiave = parolaChiave;
	}

	public String getParolaChiaveInglese() {
		return parolaChiaveInglese;
	}

	public void setParolaChiaveInglese(String parolaChiaveInglese) {
		this.parolaChiaveInglese = parolaChiaveInglese;
	}



	public Integer getStatoPraticaId() {
		return statoPraticaId;
	}

	public void setStatoPraticaId(Integer statoPraticaId) {
		this.statoPraticaId = statoPraticaId;
	}

	public Integer getTipiTrovatoId() {
		return tipiTrovatoId;
	}

	public void setTipiTrovatoId(Integer tipiTrovato) {
		this.tipiTrovatoId = tipiTrovato;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getTitoloInglese() {
		return titoloInglese;
	}

	public void setTitoloInglese(String titoloInglese) {
		this.titoloInglese = titoloInglese;
	}

	public String getUsi() {
		return usi;
	}

	public void setUsi(String usi) {
		this.usi = usi;
	}

	public String getUsiInglese() {
		return usiInglese;
	}

	public void setUsiInglese(String usiInglese) {
		this.usiInglese = usiInglese;
	}

	public String getVantaggi() {
		return vantaggi;
	}

	public void setVantaggi(String vantaggi) {
		this.vantaggi = vantaggi;
	}

	public String getVantaggiInglese() {
		return vantaggiInglese;
	}

	public void setVantaggiInglese(String vantaggiInglese) {
		this.vantaggiInglese = vantaggiInglese;
	}

	public String getInventore00() {
		return inventore00;
	}

	public void setInventore00(String inventore00) {
		this.inventore00 = inventore00;
	}

	public String getInventore01() {
		return inventore01;
	}

	public void setInventore01(String inventore01) {
		this.inventore01 = inventore01;
	}

	public String getInventore02() {
		return inventore02;
	}

	public void setInventore02(String inventore02) {
		this.inventore02 = inventore02;
	}

	public String getInventore03() {
		return inventore03;
	}

	public void setInventore03(String inventore03) {
		this.inventore03 = inventore03;
	}

	public String getInventore04() {
		return inventore04;
	}

	public void setInventore04(String inventore04) {
		this.inventore04 = inventore04;
	}

	public String getInventore05() {
		return inventore05;
	}

	public void setInventore05(String inventore05) {
		this.inventore05 = inventore05;
	}

	public String getInventore06() {
		return inventore06;
	}

	public void setInventore06(String inventore06) {
		this.inventore06 = inventore06;
	}

	public String getInventore07() {
		return inventore07;
	}

	public void setInventore07(String inventore07) {
		this.inventore07 = inventore07;
	}

	public String getInventore08() {
		return inventore08;
	}

	public void setInventore08(String inventore08) {
		this.inventore08 = inventore08;
	}

	public String getInventore09() {
		return inventore09;
	}

	public void setInventore09(String inventore09) {
		this.inventore09 = inventore09;
	}

	public String getInventore10() {
		return inventore10;
	}

	public void setInventore10(String inventore10) {
		this.inventore10 = inventore10;
	}

	public String getInventore11() {
		return inventore11;
	}

	public void setInventore11(String inventore11) {
		this.inventore11 = inventore11;
	}

	public String getInventore12() {
		return inventore12;
	}

	public void setInventore12(String inventore12) {
		this.inventore12 = inventore12;
	}

	public String getInventore13() {
		return inventore13;
	}

	public void setInventore13(String inventore13) {
		this.inventore13 = inventore13;
	}

	public String getInventore14() {
		return inventore14;
	}

	public void setInventore14(String inventore14) {
		this.inventore14 = inventore14;
	}

	public Integer getInventoriId00() {
		return inventoriId00;
	}

	public void setInventoriId00(Integer inventoriId00) {
		this.inventoriId00 = inventoriId00;
	}

	public Integer getInventoriId01() {
		return inventoriId01;
	}

	public void setInventoriId01(Integer inventoriId01) {
		this.inventoriId01 = inventoriId01;
	}

	public Integer getInventoriId02() {
		return inventoriId02;
	}

	public void setInventoriId02(Integer inventoriId02) {
		this.inventoriId02 = inventoriId02;
	}

	public Integer getInventoriId03() {
		return inventoriId03;
	}

	public void setInventoriId03(Integer inventoriId03) {
		this.inventoriId03 = inventoriId03;
	}

	public Integer getInventoriId04() {
		return inventoriId04;
	}

	public void setInventoriId04(Integer inventoriId04) {
		this.inventoriId04 = inventoriId04;
	}

	public Integer getInventoriId05() {
		return inventoriId05;
	}

	public void setInventoriId05(Integer inventoriId05) {
		this.inventoriId05 = inventoriId05;
	}

	public Integer getInventoriId06() {
		return inventoriId06;
	}

	public void setInventoriId06(Integer inventoriId06) {
		this.inventoriId06 = inventoriId06;
	}

	public Integer getInventoriId07() {
		return inventoriId07;
	}

	public void setInventoriId07(Integer inventoriId07) {
		this.inventoriId07 = inventoriId07;
	}

	public Integer getInventoriId08() {
		return inventoriId08;
	}

	public void setInventoriId08(Integer inventoriId08) {
		this.inventoriId08 = inventoriId08;
	}

	public Integer getInventoriId09() {
		return inventoriId09;
	}

	public void setInventoriId09(Integer inventoriId09) {
		this.inventoriId09 = inventoriId09;
	}

	public Integer getInventoriId10() {
		return inventoriId10;
	}

	public void setInventoriId10(Integer inventoriId10) {
		this.inventoriId10 = inventoriId10;
	}

	public Integer getInventoriId11() {
		return inventoriId11;
	}

	public void setInventoriId11(Integer inventoriId11) {
		this.inventoriId11 = inventoriId11;
	}

	public Integer getInventoriId12() {
		return inventoriId12;
	}

	public void setInventoriId12(Integer inventoriId12) {
		this.inventoriId12 = inventoriId12;
	}

	public Integer getInventoriId13() {
		return inventoriId13;
	}

	public void setInventoriId13(Integer inventoriId13) {
		this.inventoriId13 = inventoriId13;
	}

	public Integer getInventoriId14() {
		return inventoriId14;
	}

	public void setInventoriId14(Integer inventoriId14) {
		this.inventoriId14 = inventoriId14;
	}


	public String getIstituto0() {
		return istituto0;
	}

	public void setIstituto0(String istituto0) {
		this.istituto0 = istituto0;
	}

	public String getIstituto1() {
		return istituto1;
	}

	public void setIstituto1(String istituto1) {
		this.istituto1 = istituto1;
	}

	public String getIstituto2() {
		return istituto2;
	}

	public void setIstituto2(String istituto2) {
		this.istituto2 = istituto2;
	}

	public String getIstituto3() {
		return istituto3;
	}

	public void setIstituto3(String istituto3) {
		this.istituto3 = istituto3;
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

	public String getTitolare0() {
		return titolare0;
	}

	public void setTitolare0(String titolare0) {
		this.titolare0 = titolare0;
	}

	public String getTitolare1() {
		return titolare1;
	}

	public void setTitolare1(String titolare1) {
		this.titolare1 = titolare1;
	}

	public String getTitolare2() {
		return titolare2;
	}

	public void setTitolare2(String titolare2) {
		this.titolare2 = titolare2;
	}

	public String getTitolare3() {
		return titolare3;
	}

	public void setTitolare3(String titolare3) {
		this.titolare3 = titolare3;
	}

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

	public Integer getInventoreIndex() {
		return inventoreIndex;
	}

	public void setInventoreIndex(Integer inventoreIndex) {
		this.inventoreIndex = inventoreIndex;
	}

	public Integer getIstitutiId0() {
		return istitutiId0;
	}

	public void setIstitutiId0(Integer istitutiId0) {
		this.istitutiId0 = istitutiId0;
	}

	public Integer getIstitutiId1() {
		return istitutiId1;
	}

	public void setIstitutiId1(Integer istitutiId1) {
		this.istitutiId1 = istitutiId1;
	}

	public Integer getIstitutiId2() {
		return istitutiId2;
	}

	public void setIstitutiId2(Integer istitutiId2) {
		this.istitutiId2 = istitutiId2;
	}

	public Integer getIstitutiId3() {
		return istitutiId3;
	}

	public void setIstitutiId3(Integer istitutiId3) {
		this.istitutiId3 = istitutiId3;
	}

	public Integer getUpdClas() {
		return updClas;
	}

	public void setUpdClas(Integer updClas) {
		this.updClas = updClas;
	}

	public Integer getUpdInv() {
		return updInv;
	}

	public void setUpdInv(Integer updInv) {
		this.updInv = updInv;
	}

	public Integer getUpdIst() {
		return updIst;
	}

	public void setUpdIst(Integer updIst) {
		this.updIst = updIst;
	}

	public Integer getUpdTit() {
		return updTit;
	}

	public void setUpdTit(Integer updTit) {
		this.updTit = updTit;
	}

	public Integer getUpdDep() {
		return updDep;
	}

	public void setUpdDep(Integer updDep) {
		this.updDep = updDep;
	}

	public Integer getDipartimentoTitolare() {
		return dipartimentoTitolare;
	}

	public void setDipartimentoTitolare(Integer dipartimento) {
		this.dipartimentoTitolare = dipartimento;
	}

	public String getTipiTrovatoDescrizione() {
		return tipiTrovatoDescrizione;
	}

	public void setTipiTrovatoDescrizione(String tipiTrovatoDescrizione) {
		this.tipiTrovatoDescrizione = tipiTrovatoDescrizione;
	}

	public String getCessioneDirittiDescrizione() {
		return cessioneDirittiDescrizione;
	}

	public void setCessioneDirittiDescrizione(String cessioneDirittiDescrizione) {
		this.cessioneDirittiDescrizione = cessioneDirittiDescrizione;
	}

	public String getInvRifDescrizione() {
		return invRifDescrizione;
	}

	public void setInvRifDescrizione(String invRifDescrizione) {
		this.invRifDescrizione = invRifDescrizione;
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

	public String getTitolare10() {
		return titolare10;
	}

	public void setTitolare10(String titolare10) {
		this.titolare10 = titolare10;
	}

	public String getTitolare11() {
		return titolare11;
	}

	public void setTitolare11(String titolare11) {
		this.titolare11 = titolare11;
	}

	public String getTitolare12() {
		return titolare12;
	}

	public void setTitolare12(String titolare12) {
		this.titolare12 = titolare12;
	}

	public String getTitolare13() {
		return titolare13;
	}

	public void setTitolare13(String titolare13) {
		this.titolare13 = titolare13;
	}

	public String getTitolare14() {
		return titolare14;
	}

	public void setTitolare14(String titolare14) {
		this.titolare14 = titolare14;
	}

	public String getTitolare4() {
		return titolare4;
	}

	public void setTitolare4(String titolare4) {
		this.titolare4 = titolare4;
	}

	public String getTitolare5() {
		return titolare5;
	}

	public void setTitolare5(String titolare5) {
		this.titolare5 = titolare5;
	}

	public String getTitolare6() {
		return titolare6;
	}

	public void setTitolare6(String titolare6) {
		this.titolare6 = titolare6;
	}

	public String getTitolare7() {
		return titolare7;
	}

	public void setTitolare7(String titolare7) {
		this.titolare7 = titolare7;
	}

	public String getTitolare8() {
		return titolare8;
	}

	public void setTitolare8(String titolare8) {
		this.titolare8 = titolare8;
	}

	public String getTitolare9() {
		return titolare9;
	}

	public void setTitolare9(String titolare9) {
		this.titolare9 = titolare9;
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

	public Integer getUpdTecn() {
		return updTecn;
	}

	public void setUpdTecn(Integer updTecn) {
		this.updTecn = updTecn;
	}

	public void setDipartimentiId(Integer dipartimentiId) {
		this.dipartimentiId = dipartimentiId;
	}

	public Integer getDipartimentiId() {
		return dipartimentiId;
	}

	public void setDipartimentoNome(String dipartimentoNome) {
		this.dipartimentoNome = dipartimentoNome;
	}

	public String getDipartimentoNome() {
		return dipartimentoNome;
	}

	public String getUtcr() {
		return utcr;
	}

	public void setUtcr(String utcr) {
		this.utcr = utcr;
	}

	public String getUtva() {
		return utva;
	}

	public void setUtva(String utva) {
		this.utva = utva;
	}

	public Date getDacr() {
		try {
			return UtilityFunctions.itForm.parse(dataCreazione);
		} catch (ParseException e) {
			return null;
		} catch (NullPointerException e) {
			return null;
		}
	}

	public void setDacr(Date dacr) {
		try {
			dataCreazione = UtilityFunctions.itForm.format(dacr);
		} catch (NullPointerException e) {
			dataCreazione = null;
		}
	}
	
	public String getDataCreazione() {
		return dataCreazione;
	}
	
	public void setDataCreazione(String dataCreazione) {
		this.dataCreazione = dataCreazione;
	}

	public Date getDuva() {
		try {
			return UtilityFunctions.itForm.parse(dataVariazione);
		} catch (ParseException e) {
			return null;
		} catch (NullPointerException e) {
			return null;
		}
	}

	public void setDuva(Date duva) {
		try {
			dataVariazione = UtilityFunctions.itForm.format(duva);
		} catch (NullPointerException e) {
			dataVariazione = null;
		}
	}
	
	public String getDataVariazione() {
		return dataVariazione;
	}
	
	public void setDataVariazione(String dataVariazione) {
		this.dataVariazione = dataVariazione;
	}

	public Integer getRev() {
		return rev;
	}

	public void setRev(Integer rev) {
		this.rev = rev;
	}

	public String getUtentiNominativo() {
		return utentiNominativo;
	}

	public void setUtentiNominativo(String utentiNominativo) {
		this.utentiNominativo = utentiNominativo;
	}

	public String getUtentiId() {
		return utentiId;
	}

	public void setUtentiId(String utentiId) {
		this.utentiId = utentiId;
	}

	public Integer getProvvisorio() {
		return provvisorio;
	}

	public void setProvvisorio(Integer provvisorio) {
		this.provvisorio = provvisorio;
	}

	public Integer getPubblicato() {
		return pubblicato;
	}

	public void setPubblicato(Integer pubblicato) {
		this.pubblicato = pubblicato;
	}

	public Integer getUpdDoc() {
		return updDoc;
	}

	public void setUpdDoc(Integer updDoc) {
		this.updDoc = updDoc;
	}

	public Integer getRespinto() {
		return respinto;
	}

	public void setRespinto(Integer respinto) {
		this.respinto = respinto;
	}

	public String getNotaRespinto() {
		return notaRespinto;
	}

	public void setNotaRespinto(String notaRespinto) {
		this.notaRespinto = notaRespinto;
	}
	
}
