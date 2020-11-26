<%@page import="it.cnr.brevetti.util.Version"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" 	href="css/standard.css?<%=Version.getVersion()%>" type="text/css" />
<title>Brevetti CNR - Gestione trovati</title>
<script type="text/javascript" src="scripts/standard.js?<%=Version.getVersion()%>"></script>
<script type="text/javascript">
DocDom = (document.getElementById?true:false);
DocAll = (document.all?true:false);
DocStr=''
if (DocAll) DocStr="return document.all[id]"
if (DocDom) DocStr="return document.getElementById(id)"
GetRef=new Function("id", DocStr)
if (DocStr=='') { DynWrite=new Function("return false") } else {
  DynWrite=new Function("id", "S", "GetRef(id).innerHTML=S; return true")
}

function inizialize(){
		DynWrite('nsrif',window.opener.document.forms[0].nsrif.value);
		DynWrite('dataRichiesta',window.opener.document.forms[0].dataRichiesta.value);
		DynWrite('tipiTrovatoDescrizione',window.opener.document.forms[0].tipiTrovatoDescrizione.value);
		DynWrite('cessioneDirittiDescrizione',window.opener.document.forms[0].cessioneDirittiDescrizione.value);
		DynWrite('studioBrevettualeDescrizione',window.opener.document.forms[0].studioBrevettualeDescrizione.value);
		DynWrite('titolo',window.opener.document.forms[0].titolo.value);
		DynWrite('numDeposito',window.opener.document.forms[0].numDeposito.value);
		DynWrite('dataDeposito',window.opener.document.forms[0].dataDeposito.value);
		DynWrite('numPct',window.opener.document.forms[0].numPct.value);
		DynWrite('dataPct',window.opener.document.forms[0].dataPct.value);
		DynWrite('numEpc',window.opener.document.forms[0].numEpc.value);
		DynWrite('dataEpc',window.opener.document.forms[0].dataEpc.value);
		DynWrite('note',window.opener.document.forms[0].note.value);
		DynWrite('dataAbbandono',window.opener.document.forms[0].dataAbbandono.value);
		var tit = window.opener.document.forms[0].titolari.options;
		var tits = "<ul class='printList'>";
		for(var i=0; i<tit.length; i++){
			tits = tits + "<li>"+tit[i].text+"</li>";
		}
		tits = tits + "</ul>"
		DynWrite('titolari',tits);
		var invs = "<ol class='printList'>";
		for(var i=0; i<15; i++){
			if(window.opener.document.forms[0].elements[i+1].value == ""){
				break;
			}
			if(window.opener.document.forms[0].elements[i+1].value == window.opener.document.forms[0].invRifDescrizione.value){
				invs = invs + "<li><b>"+window.opener.document.forms[0].elements[i+1].value+"</b></li>";
			} else {
				invs = invs + "<li>"+window.opener.document.forms[0].elements[i+1].value+"</li>";
			}
		}
		if(invs=="<ol class='printList'>"){
			invs = "";
		} else {
			invs = invs + "</ol>"
		}
		
		DynWrite('invRifDescrizione',invs);
		window.print();
}
</script>
</head>
<body onload="inizialize()">
<div class="center">
  <table width="100%" class="pagetable">
    <tr>
      <td width="100%" class="pagetable">

        <div class="titolo">Gestione Trovati: scheda del trovato</div>
        <br/>
        <table width='100%' border='0' cellpadding='0' cellspacing='0'>
          <tr id='tabs'><td id='tabon'>&nbsp;</td></tr>
        </table>        <table width="100%" border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td id="area" valign="top"><form name="datiTrovato" method="post" action="/brevetti/InfoTrov.do"><table width="100%" border="0">
                <tr>

                  <td><table width="96%" border="0" align="center">
              <tr>
                        <td width="416" align="left">NsRIF</td>
                  <td width="6">&nbsp;</td>
                <td width="416" align="left">Data richiesta deposito</td>
              </tr>
              <tr>

                <td align="left"><table class="imaction"><tr><td><div class="wide" id="nsrif" >&nbsp;</div></td></tr></table></td>
                <td>&nbsp;</td>
                <td align="left"><table class="imaction"><tr><td><div id="dataRichiesta" >&nbsp;</div></td></tr></table></td>
              </tr>
              <tr>
                <td height="2" colspan="3"><img src="images/spacer.gif" width="2" height="2" /></td>
                </tr>
              <tr>
                <td align="left">Tipologia</td>

                <td>&nbsp;</td>
                  <td align="left">Cessione diritti di propriet&agrave;</td>
                  </tr>
              <tr>
                <td align="left"><table class="imaction"><tr><td><div id="tipiTrovatoDescrizione" >&nbsp;</div></td></tr></table></td>
                <td>&nbsp;</td>
                <td align="left"><table class="imaction"><tr><td><div id="cessioneDirittiDescrizione" >&nbsp;</div></td></tr></table></td>
                </tr>
              <tr>
                <td height="2" colspan="3"><img src="images/spacer.gif" width="2" height="2" /></td>
              </tr>
              <tr>
                <td align="left">Studio brevettuale</td>
                <td>&nbsp;</td>
                  <td align="left">Data abbandono</td>
                </tr>

              <tr>
                <td align="left"><table class="imaction"><tr><td><div id="studioBrevettualeDescrizione" >&nbsp;</div></td></tr></table></td>
                <td>&nbsp;</td>
                <td align="left"><table class="imaction"><tr><td><div id="dataAbbandono" >&nbsp;</div></td></tr></table></td>
              </tr>
              <tr>
                <td height="2" colspan="3"><img src="images/spacer.gif" width="2" height="2" /></td>
              </tr>
              <tr>

                <td align="left">Titolo</td>
                <td>&nbsp;</td>
                  <td align="left">Titolarit&agrave;</td>
                </tr>
              <tr>
                <td align="left"><table class="imaction"><tr><td><div id="titolo" >&nbsp;</div></td></tr></table></td>
                <td>&nbsp;</td>

                <td align="left" valign="top"><table class="imaction"><tr><td><div id="titolari" >&nbsp;</div></td></tr></table></td>
              </tr>
               <tr>
                <td height="2" colspan="3"><img src="images/spacer.gif" width="2" height="2" /></td>
              </tr>
                            <tr>
                <td align="left">Note</td>

                <td>&nbsp;</td>
                <td align="left">Elenco inventori</td>
              </tr>
              <tr>
                <td align="left" valign="top"><table class="imaction"><tr><td><div id="note">&nbsp;</div></td></tr></table></td>
                <td>&nbsp;</td>
                <td align="left" valign="top"><table class="imaction"><tr><td><div id="invRifDescrizione" >&nbsp;</div></td></tr></table></td>
              </tr>
              <tr>
                <td height="2" colspan="3"><img src="images/spacer.gif" width="2" height="2" /></td>
                </tr>
              <tr>
                <td align="left">N&deg; primo deposito</td>

                <td>&nbsp;</td>
                <td align="left">Data primo deposito</td>
              </tr>
              <tr>
                <td align="left"><table class="imaction"><tr><td><div id="numDeposito" >&nbsp;</div></td></tr></table></td>
                <td>&nbsp;</td>
                <td align="left"><table class="imaction"><tr><td><div id="dataDeposito" >&nbsp;</div></td></tr></table></td>
              </tr>

              <tr>
                <td height="2" colspan="3"><img src="images/spacer.gif" width="2" height="2" /></td>
              </tr>
              <tr>
                <td align="left">N&deg; deposito internazionale</td>
                <td>&nbsp;</td>
                <td align="left">Data  deposito internazionale</td>
              </tr>
              <tr>
                <td align="left"><table class="imaction"><tr><td><div id="numPct" >&nbsp;</div></td></tr></table></td>
                <td>&nbsp;</td>
                <td align="left"><table class="imaction"><tr><td><div id="dataPct" >&nbsp;</div></td></tr></table></td>
              </tr>
              <tr>
                <td height="2" colspan="3"><img src="images/spacer.gif" width="2" height="2" /></td>
              </tr>

                            <tr>
                <td align="left">N&deg; deposito europeo</td>
                <td>&nbsp;</td>
                <td align="left">Data  deposito europeo</td>
              </tr>
              <tr>
                <td align="left"><table class="imaction"><tr><td><div id="numEpc" >&nbsp;</div></td></tr></table></td>

                <td>&nbsp;</td>
                <td align="left"><table class="imaction"><tr><td><div id="dataEpc" >&nbsp;</div></td></tr></table></td>
              </tr>
              <tr>
                <td height="2" colspan="3"><img src="images/spacer.gif" width="2" height="2" /></td>
              </tr>
            </table></td>
			  </tr>
			</table>
			</form>			</td>
          </tr>

          <tr>
            <td height="4"><img src="images/spacer.gif" width="4" height="4" /></td>
          </tr>
          <tr>
            <td align="center">
            	<input type="button" value="Stampa" onclick="window.print();" />
            	&nbsp;&nbsp;&nbsp;
            	<input type="button" value="Chiudi" onclick="window.close();" />
			</td>
          </tr>
        </table>        </td>
    </tr>
  </table>
</div>
</body>
</html>
