<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Locale"%>
<%@page import="it.cnr.brevetti.trovati.javabeans.TrovatoJB"%>
<%@page import="it.cnr.brevetti.ejb.entities.Trovato"%>
<%@page import="java.util.Iterator"%>
<%@page import="it.cnr.brevetti.ejb.entities.Istituto"%>
<%@page import="it.cnr.brevetti.ejb.entities.Titolarita"%>
<%@page import="it.cnr.brevetti.ejb.entities.Inventore"%>
<%@page import="it.cnr.brevetti.ejb.entities.DepEst"%>
<%@page import="it.cnr.brevetti.util.Version"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" 	href="../css/standard.css?<%=Version.getVersion()%>" type="text/css" />
<title>Brevetti CNR - Gestione trovati</title>
<%
	SimpleDateFormat itForm = new SimpleDateFormat("dd/MM/yyyy", Locale.ITALIAN);
	String nsrif = request.getParameter("nsrif");
	String dip = request.getParameter("dip");
	TrovatoJB tjb = TrovatoJB.getInstance();
	Trovato tro = tjb.getTrovato(Integer.decode(nsrif),Integer.decode(dip));
	String istituti = "";
	String titolari = "";
	String inventori = "";
	DepEst primoDep = new DepEst();
	DepEst pct = new DepEst();
	DepEst epc = new DepEst();
	String nazioni = "";
	if(tro.getIstituti()!=null) for(Iterator i = tro.getIstituti().iterator(); i.hasNext();){
		Istituto ist =(Istituto)i.next();
		istituti = istituti.concat("<li>"+ist.getNome()+" ("+ist.getIstitutoSigla()+")</li>");
	}
	if(tro.getTitolarita()!=null) for(Iterator i = tro.getTitolarita().iterator(); i.hasNext();){
		Titolarita tit =(Titolarita)i.next();
		if (tit.getTipiTitolareId().intValue()==1)
			titolari = titolari.concat("<li>dipartimento "+tit.getDipartimento().getDescrizione()+"</li>");
	    else if (tit.getTipiTitolareId().intValue()==3)
			titolari = titolari.concat("<li>"+tit.getInventore().getCognome()+" "+tit.getInventore().getNome()+" (inventore)</li>");
		else if (tit.getTipiTitolareId().intValue()==4)
			titolari = titolari.concat("<li>"+tit.getEnteEsterno().getNome()+"</li>");
			
	}
	if(tro.getInventori()!=null) for(Iterator i = tro.getInventori().iterator(); i.hasNext();){
		Inventore inv =(Inventore)i.next();
		inventori = inventori.concat("<li>"+inv.getCognome()+" "+inv.getNome()+"</li>");
	}
	if(tro.getDepEst()!=null) for(DepEst dep : tro.getDepEst()){
		if(dep.getPrimo().intValue()==1)
			primoDep=dep;
		if(dep.getTipoId() == DepEst.PCT)
			pct = dep;
		else if(dep.getTipoId() == DepEst.EPC)
			epc = dep;
		else {
			if(nazioni.length()>0)	nazioni = nazioni.concat(" - ");
			nazioni = nazioni.concat(tjb.getStato(dep.getStatoId()).getNome());
		}
	}
	String[] type = new String[9];
	type[1]= "Patents";
	type[3]= "Copyright protection";
	type[4]= "Trade mark protection";
	type[5]= "Utility models";
	type[6]= "Plant varieties protection";
	type[7]= "Software";
	type[8]= "Know-how";

%>
</head>
<body>
<div class="center">
  <table width="60%" class="pagetable">
    <tr>
      <td width="100%" class="pagetable">

        <div class="titolo">Gestione Trovati: scheda del trovato</div>
        <br/>
        <table width='100%' border='0' cellpadding='0' cellspacing='0'>
          <tr id='tabs'><td id='tabon'>&nbsp;</td></tr>
        </table>        <table width="100%" border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td id="area" valign="top"><table width="100%" border="0">
                <tr>

                  <td><table width="96%" border="0" align="center">
              <tr>
                        <td width="416" rowspan="2" align="left" valign="top">CNR reference Nr.
                          <div class="wide"><%= tro.getNsrif() %></div></td>
                  <td width="6" valign="top">&nbsp;</td>
                <td width="416" align="left" valign="top">IP Rights</td>
              </tr>
              <tr>

                <td valign="top">&nbsp;</td>
                <td align="left" valign="top"><input type="text" readonly="true" size="52" value="<%= type[tro.getTipiTrovatoId().intValue()] %>" /></td>
              </tr>
              <tr>
                <td height="2" colspan="3" valign="top"><img src="images/spacer.gif" width="2" height="2" /></td>
                </tr>
              <tr>
                <td align="left" valign="top">Title</td>

                <td valign="top">&nbsp;</td>
                  <td align="left" valign="top">Applications</td>
                  </tr>
              <tr>
                <td align="left" valign="top"><textarea readonly="true" cols="40" rows="3"><%= tro.getTitoloInglese() %></textarea></td>
                <td valign="top">&nbsp;</td>
                <td align="left" valign="top"><textarea readonly="true" cols="40" rows="3"><%= tro.getUsiInglese()!=null?tro.getUsiInglese():"" %></textarea></td>
                </tr>
              <tr>
                <td height="2" colspan="3" valign="top"><img src="images/spacer.gif" width="2" height="2" /></td>
              </tr>
              <tr>
                <td align="left" valign="top">Description</td>
                <td valign="top">&nbsp;</td>
                  <td align="left" valign="top">Advantages</td>
                </tr>

              <tr>
                <td align="left" valign="top"><textarea readonly="true" cols="40" rows="4"><%= tro.getDescrizioneInglese()!=null?tro.getDescrizioneInglese():"" %></textarea></td>
                <td valign="top">&nbsp;</td>
                <td align="left" valign="top"><textarea readonly="true" cols="40" rows="4"><%= tro.getVantaggiInglese()!=null?tro.getVantaggiInglese():"" %></textarea></td>
              </tr>
              <tr>
                <td height="2" colspan="3" valign="top"><img src="images/spacer.gif" width="2" height="2" /></td>
              </tr>
              <tr>

                <td align="left" valign="top">Envolved research institutes</td>
                <td valign="top">&nbsp;</td>
                  <td align="left" valign="top">Applicants</td>
                </tr>
              <tr>
                <td align="left" valign="top" class="imaction"><%= "<ul>"+istituti+"</ul>" %></td>

                <td valign="top">&nbsp;</td>

                <td align="left" valign="top" class="imaction"><%= "<ul>"+titolari+"</ul>" %></td>
              </tr>
               <tr>
                <td height="2" colspan="3" valign="top"><img src="images/spacer.gif" width="2" height="2" /></td>
              </tr>
                            <tr>
                <td align="left" valign="top">Keywords</td>

                <td valign="top">&nbsp;</td>
                <td align="left" valign="top">Inventors</td>
              </tr>
              <tr>
                <td align="left" valign="top"><textarea readonly="true" cols="40" rows="3"><%= tro.getParolaChiaveInglese()!=null?tro.getParolaChiaveInglese():"" %></textarea></td>
                <td valign="top">&nbsp;</td>
                <td align="left" valign="top" class="imaction"><%= "<ul>"+inventori+"</ul>" %></td>
              </tr>
              <tr>
                <td height="2" colspan="3" valign="top"><img src="images/spacer.gif" width="2" height="2" /></td>
                </tr>
              <tr>
                <td align="left" valign="top">Priority Nr.</td>

                <td valign="top">&nbsp;</td>
                <td align="left" valign="top">Application date</td>
              </tr>
              <tr>
                <td align="left" valign="top"><input type="text" readonly="true" size="52" value="<%= primoDep.getNumeroDeposito()!=null?primoDep.getNumeroDeposito():"" %>" /></td>
                <td valign="top">&nbsp;</td>
                <td align="left" valign="top"><input type="text" readonly="true" size="52" value="<%= primoDep.getDataDeposito()!=null?itForm.format(primoDep.getDataDeposito()):"" %>" /></td>
              </tr>

              <tr>
                <td height="2" colspan="3" valign="top"><img src="images/spacer.gif" width="2" height="2" /></td>
              </tr>
              <tr>
                <td align="left" valign="top">PCT Nr.</td>
                <td valign="top">&nbsp;</td>
                <td align="left" valign="top">PCT date</td>
              </tr>
              <tr>
                <td align="left" valign="top"><input type="text" readonly="true" size="52" value="<%= pct.getNumeroDeposito()!=null ? pct.getNumeroDeposito():"" %>" /></td>
                <td valign="top">&nbsp;</td>
                <td align="left" valign="top"><input type="text" readonly="true" size="52" value="<%= pct.getDataDeposito()!=null?itForm.format(pct.getDataDeposito()):"" %>" /></td>
              </tr>
              <tr>
                <td height="2" colspan="3" valign="top"><img src="images/spacer.gif" width="2" height="2" /></td>
              </tr>

                            <tr>
                <td align="left" valign="top">EPC Nr.</td>
                <td valign="top">&nbsp;</td>
                <td align="left" valign="top">EPC date</td>
              </tr>
              <tr>
                <td align="left" valign="top"><input type="text" readonly="true" size="52" value="<%= epc.getNumeroDeposito()!=null?epc.getNumeroDeposito():"" %>" /></td>

                <td valign="top">&nbsp;</td>
                <td align="left" valign="top"><input type="text" readonly="true" size="52" value="<%= epc.getDataDeposito()!=null?itForm.format(epc.getDataDeposito()):"" %>" /></td>
              </tr>
              <tr>
                <td height="2" colspan="3" valign="top"><img src="images/spacer.gif" width="2" height="2" /></td>
              </tr>
              <tr>
                <td colspan="3" align="left" valign="top">Designed states</td>
                </tr>
              <tr>
                <td colspan="3" align="left" valign="top"><textarea cols="80" rows="3" readonly="true"><%= nazioni %></textarea></td>
                </tr>
              <tr>
                <td height="2" colspan="3" valign="top"><img src="images/spacer.gif" width="2" height="2" /></td>
              </tr>
            </table></td>
			  </tr>
			</table>
			</td>
          </tr>

          <tr>
            <td height="4"><img src="images/spacer.gif" width="4" height="4" /></td>
          </tr>
          <tr>
            <td align="center">
            	<input type="button" value="Chiudi" onclick="window.close();" />
			</td>
          </tr>
        </table>        </td>
    </tr>
  </table>
</div>
</body>
</html>
