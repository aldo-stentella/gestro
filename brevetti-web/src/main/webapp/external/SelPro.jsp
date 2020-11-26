<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="it.cnr.brevetti.ejb.entities.InventionDisclosure"%>
<%@page import="it.cnr.brevetti.util.UtilityFunctions"%>
<%@page import="java.util.List"%>
<%@page import="it.cnr.brevetti.util.Version"%>
<%@ include file="/WEB-INF/jsp/layout/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Consiglio Nazionale delle Ricerche - Unit&agrave; Valorizzazione della Ricerca</title>
<link href="../css/standard.css?<%=Version.getVersion()%>" rel="stylesheet" type="text/css" />
<link href="../css/reports.css?<%=Version.getVersion()%>" rel="stylesheet" type="text/css" />
<script language="JavaScript" type="text/JavaScript">
	function doEdit(id){
		self.location.href='GesPro.do?id='+id;
	}
</script>
</head>
<body onload="inizialize();">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td><img src="../images/headerUVR.png" /></td>
  </tr>
  <tr>
    <td align="center" valign="top"><table id="boxScheda">
        <tr>
          <td align="center"><h1>Comunicazione preliminare</h1>
            <h2>PROPOSTA DI BREVETTO O MARCHIO</h2>

              <table border="0" align="center" cellpadding="0" cellspacing="0">
                <tr>
                  <td class="Gscheda2-top">&nbsp;</td>
                </tr>
                <tr>
                  <td class="Gscheda2-mid">
                    <table width="100%">
                  	  <tr>
                  	    <td width="15%" align="right" class="even">Utente:</td>
                  	    <td width="70%" align="left" class="even"><%= session.getAttribute("sessioneEutente") %></td>
                  	    <td width="15%" align="right" class="even"><a href="Logout.do">Esci</a></td>
                  	  </tr>
                  	</table><br />
                  	<table width="98%" align="center" class="deep">
                  	  <tr>
                  	  	<th width="20">Id</th>
                  	    <th width="130">Tipologia</th>
                  	    <th>Titolo</th>
                  	    <th width="70">Data</th>
                  	    <th width="100">Stato</th>
                  	    <th width="10"><img src="../images/close.gif" alt="Chiudi" class="imaction" onclick="self.location.href='Home.do'" /> </th>
                  	  </tr>
<%
					
					List<InventionDisclosure> proposte = (List<InventionDisclosure>)request.getAttribute("proposte");
					String[] tipi = (String[])request.getAttribute("tipi");
					String[] stati = (String[])request.getAttribute("stati");
					String[] cls = {"","odd"};
					int i = 0;
					if(proposte!=null) for(InventionDisclosure id:proposte){
						
%>
                      <tr>
                      	<td class="<%=cls[1]%>"><%= id.getId() %></td>
                        <td class="<%=cls[1]%>"><%= tipi[id.getTipiTrovatoId()] %></td>
                        <td class="<%=cls[1]%>"><%= id.getTitolo() %></td>
                        <td class="<%=cls[1]%>"><%=  UtilityFunctions.itForm.format(id.getDataTrasmissione()) %></td>
                        <td colspan="2" class="<%=cls[1]%>"><img src="../images/<%= id.getStato() %>dset1n.gif" align="middle" vspace="9" />&nbsp;<span class="<%= id.getStato()==InventionDisclosure.DA_INTEGRARE?"buttonize":"" %>" onclick="<%= id.getStato()==InventionDisclosure.DA_INTEGRARE?"doEdit("+id.getId()+");":"" %>"><%= stati[id.getStato()] %></span></td>
                      </tr>
<%
						i = 1-i;
					}
%>
                    </table></td>
                 </tr>
                 <tr><td align="center" class="Gscheda2-mid"><input type="button" value="Chiudi" onclick="self.location='Home.do'" /></td>
                 </tr>
                <tr>
                  <td class="Gscheda2-bot">&nbsp;</td>
                </tr>
              </table>
            </td>
        </tr>
    </table></td>
  </tr>
  <tr>
    <td align="center" class="Gfooter"><strong>Consiglio Nazionale delle Ricerche</strong> -  P.le   Aldo Moro, 7 - 00185 Roma</td>
  </tr>
</table>
</body>
</html>
