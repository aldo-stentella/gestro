<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="it.cnr.brevetti.ejb.entities.Classificazione"%>
<%@page import="it.cnr.brevetti.ejb.entities.Dipartimento"%>
<html xmlns="http://www.w3.org/1999/xhtml"><!-- InstanceBegin template="/Templates/Gcatalogo.dwt.jsp" codeOutsideHTMLIsLocked="false" -->
<%@page import="it.cnr.brevetti.util.Version"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!-- InstanceBeginEditable name="doctitle" -->
<title>Consiglio Nazionale delle Ricerche - Catalogo Brevetti e Propriet&agrave; Intellettuale</title>
<!-- InstanceEndEditable -->
<link href="css/standard.css?<%=Version.getVersion()%>" rel="stylesheet" type="text/css" />
<link href="css/reports.css?<%=Version.getVersion()%>" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="scripts/standard.js?<%=Version.getVersion()%>"></script>
<!-- InstanceBeginEditable name="head" -->
<script language="JavaScript">
<!--
<%
List<Classificazione> list1 = (List<Classificazione>)request.getAttribute("list1");
List<Classificazione> list3 = (List<Classificazione>)request.getAttribute("list3");
String liv2 = (String)request.getAttribute("liv2");
String liv3 = (String)request.getAttribute("liv3");
String[] deps = (String[])request.getAttribute("deps");
List dcount = (List)request.getAttribute("dcount");
Long[]totIPC = (Long[])request.getAttribute("totIPC");
Long[]totTMC = (Long[])request.getAttribute("totTMC");
%>
var livs2 = new Array(<%=liv2%>);
var livs3 = new Array(<%=liv3%>);
var voce = "";	

function inizialize() {
	popolate2(0); 
	popolate3(0);
	document.form.liv3.style.setProperty("background", "#FFFFFF", null);
	document.form.liv4.style.setProperty("background", "#FFFFFF", null);
}

function popolate2(ind){
	var liv2 = livs2[ind];
	while(document.form.liv2.options.length>0){
		if(document.all){
			document.form.liv2.options.remove(0);
		} else {
			document.form.liv2.options[0] = null;
		}
	}
	for(var i=0; i<liv2.length; i++){
		voce = liv2[i].toString();
		document.form.liv2.options[i]=new Option(voce.substring(0,voce.indexOf('|')),voce.substr(voce.indexOf('|')+1));
	}
	document.form.liv3.selectedIndex = 0;
	document.form.liv4.selectedIndex = 0;
	while(document.form.liv4.options.length>1){
		if(document.all){
			document.form.liv4.options.remove(1);
		} else {
			document.form.liv4.options[1] = null;
		}
	}
	document.form.liv1.style.setProperty("background", "#FEFCE2", null);
	document.form.liv2.style.setProperty("background", "#FEFCE2", null);
	document.form.liv3.style.setProperty("background", "#FFFFFF", null);
	document.form.liv4.style.setProperty("background", "#FFFFFF", null);
}

function popolate3(ind){
	var liv3 = livs3[ind];
	while(document.form.liv4.options.length>0){
		if(document.all){
			document.form.liv4.options.remove(0);
		} else {
			document.form.liv4.options[0] = null;
		}
	}
	for(var i=0; i<liv3.length; i++){
		voce = liv3[i].toString();
		document.form.liv4.options[i]=new Option(voce.substring(0,voce.indexOf('|')),voce.substr(voce.indexOf('|')+1));
	}
	document.form.liv1.selectedIndex = 0;
	document.form.liv2.selectedIndex = 0;
	while(document.form.liv2.options.length>1){
		if(document.all){
			document.form.liv2.options.remove(1);
		} else {
			document.form.liv2.options[1] = null;
		}
	}
	document.form.liv1.style.setProperty("background", "#FFFFFF", null);
	document.form.liv2.style.setProperty("background", "#FFFFFF", null);
	document.form.liv3.style.setProperty("background", "#FEFCE2", null);
	document.form.liv4.style.setProperty("background", "#FEFCE2", null);
}

function dropDown(target, arrow){
	var temp = 	GetObj(target).style.display;
	if(temp=='block'){
		GetObj(target).style.display = 'none';
		GetObj(arrow).src = 'images/Catalog/title-left.png';
	}else{
		GetObj(target).style.display = 'block';
		GetObj(arrow).src = 'images/Catalog/title-down.png';
	}
}
//-->
</script>
<!-- InstanceEndEditable -->
</head>
<body onload="inizialize();">
<table width="950px" border="0" align="center" cellpadding="0" cellspacing="6">
  <tr>
    <td valign="bottom" id="Gheader"><hr align="right" width="775" size="1" noshade color="#ffffff" class="GdottedLine" />
    <div id="link2cnr"><a href="http://www.cnr.it">&laquo; back to cnr.it</a></div></td>
  </tr>
  <tr>
    <td valign="top"><!-- InstanceBeginEditable name="content" -->
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td>
   	   <form name="form" method="post" action="Catalogo.do">
          <table width="914" border="0" cellpadding="0" cellspacing="0" id="Gtab">
          <tr><td><p><br/>
            Ricerca</p>
          </td></tr>
      <tr>
        <td class="Gscheda-top">&nbsp;</td>
        </tr>
        <tr><td class="Gscheda-mid"><table width="914" border="0" cellpadding="10" cellspacing="0">
      <tr>
        <td width="162">parole chiave:</td>
        <td colspan="2"><input name="keywords" type="text" size="60" maxlength="630" /></td>
      </tr>
      <tr>
        <td valign="top">nelle categorie:</td>
        <td colspan="2"><select name="liv1" onchange="popolate2(this.value);" class="Gcombo">
        	<option value="0">Tutte le categorie</option>
<%
		for(Iterator<Classificazione> i = list1.iterator(); i.hasNext();){
			Classificazione cl1 = i.next();
			%>
			<option value='<%=cl1.getId() %>' ><%=cl1.getNome().substring(cl1.getNome().indexOf("-")+2) %></option>
<%
		}
%>
        </select><br/>
        <select name="liv2" class="Gcombo">
        </select>        </td>
      </tr>
      <tr>
        <td valign="top">nei settori tecnologici:</td>
        <td colspan="2"><select name="liv3" onchange="popolate3(this.selectedIndex);" class="Gcombo">
        	<option value="0">Tutti i settori</option>
<%
		for(Iterator<Classificazione> i = list3.iterator(); i.hasNext();){
			Classificazione cl1 = i.next();
			%>
			<option value='<%=cl1.getId() %>' ><%=cl1.getNome() %></option>
<%
		}
%>
        </select><br/>
        <select name="liv4" class="Gcombo">
        </select>        </td>
      </tr>
      <tr>
        <td valign="top">nei dipartimenti:</td>
        <td width="520" valign="top"><select name="dipartimenti" class="Gcombo">
          <option value="0">Tutti i dipartimenti</option>
<%
				for (int i = 1; i < deps.length; i++) {
					String string = deps[i];
					if(string==null || string.length()==0) break;
%>
					<option value='<%=i %>' ><%=string %></option>
<%
				}
%>
        </select></td>
        <td width="172" align="center">
        	<input type="image" src="images/Catalog/search-button.png" />
        </td>
      </tr></table>
      <tr><td class="Gscheda-bot">&nbsp;</td></tr>
    </table>
    </form>    </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>
      <table width="914" border="0" cellpadding="0" cellspacing="0" id="GtabDrop">
      <tr><td><p onclick="dropDown('tabCategorie','arrCat')"><br />Naviga per categoria</p><img src="images/Catalog/title-left.png" id="arrCat" onclick="dropDown('tabCategorie','arrCat')" /></td>
      </tr>
      <tr>
        <td class="Gscheda-top">&nbsp;</td>
        </tr>
        <tr><td class="Gscheda-mid">
        <table id="tabCategorie" style="display: none;">
      	<tr>
          <td valign="top">
      		<ul class="expand">
<%
		int dim = 0;
		for(int j=0; j<totIPC.length; j++){
			if(totIPC[j]!=null && totIPC[j]!=0) dim++;
		}
		dim = (dim+1) / 2;
		int xx = 0;
		for(Iterator<Classificazione> i = list1.iterator(); i.hasNext();){
			Classificazione cl1 = i.next();
			if(totIPC[xx]!=0){
%>
         		<li><a href="Catalogo.do?liv1=<%= cl1.getId() %>" > <%= cl1.getNome().substring(cl1.getNome().indexOf("-")+2) %>  [<%=totIPC[xx] %>]</a></li>
<%
				dim--;
			}
			xx++;
			if(dim==0){
				dim--;
%>
	        </ul></td>
		    <td valign="top">
		      <ul class="expand">
		<%

			}
		}
		
%>
			</ul>		  </td>
		</tr></table></td></tr>
        <tr><td class="Gscheda-bot">&nbsp;</td></tr>
      </table>    </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>
      <table width="914" border="0" cellpadding="0" cellspacing="0" id="GtabDrop">
      <tr><td><p onclick="dropDown('tabTecnologie', 'arrTec')"><br />Naviga per settore tecnologico</p><img src="images/Catalog/title-left.png" id="arrTec" onclick="dropDown('tabTecnologie', 'arrTec')" /></td>
      </tr>
            <tr>
        <td class="Gscheda-top">&nbsp;</td>
        </tr>
        <tr><td class="Gscheda-mid">
        <table id="tabTecnologie" style="display: none;">
        <tr>
          <td valign="top" width="50%">
            <ul class="expand">
<%
		dim = 0;
		for(int j=0; j<totTMC.length; j++){
			if(totTMC[j]!=null && totTMC[j]!=0) dim++;
		}
		dim = (dim+1) / 2;
		xx = 0;
		for(Iterator<Classificazione> i = list3.iterator(); i.hasNext();){
			Classificazione cl1 = i.next();
			if(totTMC[xx]!=0){
%>
         		<li><a href="Catalogo.do?liv3=<%= cl1.getId() %>" > <%= cl1.getNome() %>  [<%=totTMC[xx] %>] </a></li>
<%
				dim--;
			}
			xx++;
			if(dim==0){
				dim--;
%>
            </ul></td>
	        <td valign="top">
	          <ul class="expand">
	<%

						}
		}
%>
		  </ul>		</td>
	  </tr>
      </table>
      </td></tr>
      <tr><td class="Gscheda-bot">&nbsp;</td></tr>
    </table>  </td></tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>
      <table width="914" border="0" cellpadding="0" cellspacing="0" id="GtabDrop" >
      <tr><td><p onclick="dropDown('tabDipartimenti', 'arrDip')"><br />Naviga per dipartimento</p><img src="images/Catalog/title-left.png" id="arrDip" onclick="dropDown('tabDipartimenti', 'arrDip')" /></td>
      </tr>
        <tr>
        <td class="Gscheda-top">&nbsp;</td>
        </tr>
        <tr><td class="Gscheda-mid">
        <table id="tabDipartimenti" style="display: none;">
        <tr>
          <td valign="top">
      		<ul class="expand">
<%
				dim = dcount.size()/2;
				xx = 0;
				for(Iterator it=dcount.iterator(); it.hasNext();){
					Object[] dp = (Object[])it.next();
					xx++;
%>
				<li><a href="Catalogo.do?dipartimenti=<%= ((Long)dp[0])!=0?xx:0 %>" ><%=deps[((Integer)dp[1])]%> [<%=dp[0] %>]</a></li>
<%
					if(dim==xx){
%>
            </ul></td>
          <td valign="top">
            <ul class="expand">
<%

					}
				}
%>
          </ul></td>
        </tr>
      </table>
      </td></tr>
      <tr><td class="Gscheda-bot">&nbsp;</td></tr>
    </table></td>
  </tr>
</table>
<!-- InstanceEndEditable --></td>
  </tr>
  <tr>
    <td align="right" class="Gfooter"><hr align="right" width="775" size="1" noshade color="#880010" />fonte dati: &quot;Gestione Trovati&quot; | <a href="https://brevetti.cnr.it" target="_blank">brevetti.cnr.it</a> | <a href="http://www.cnr.it" target="_blank">www.cnr.it</a> | Consiglio Nazionale delle Ricerche |  P.le   Aldo Moro, 7 | 00185 Roma</td>
  </tr>
</table>
</body>
<!-- InstanceEnd --></html>
