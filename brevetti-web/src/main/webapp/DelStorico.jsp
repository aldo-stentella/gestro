<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="it.cnr.brevetti.ejb.entities.StoricoTitolarita"%>
<%@page import="it.cnr.brevetti.util.Version"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Staff</title>
<link rel="stylesheet" 	href="css/standard.css?<%=Version.getVersion()%>" type="text/css" />
<script type="text/javascript" language="javascript1.4">
<%
	String denominazione="";
	Integer id = null;
	if(request.getAttribute("act").toString().startsWith("close")){
%>
			window.opener.aggiornaTit();
			window.close();
<%		
	}else{
		StoricoTitolarita tit = (StoricoTitolarita)request.getAttribute("titolare");
		id = tit.getId();
		if(tit.getTipiTitolareId().intValue()==1)
			denominazione="il dipartimento <strong>"+tit.getDipartimento().getDescrizione()+"</strong>";
		else if(tit.getTipiTitolareId().intValue()==3)
			denominazione="l'inventore <strong>"+tit.getInventore().getCognome()+" "+tit.getInventore().getNome()+"</strong>";
		else if(tit.getTipiTitolareId().intValue()==4)
			denominazione="l'ente esterno <strong>"+tit.getEnteEsterno().getNome()+"</strong>";
	}
%>
</script>
</head>
<body>
<form action="DelStorico.do" method="post">
<table width="506" border="1" align="center" cellpadding="10" cellspacing="0">
  <tr>
	<td width="482" background="images/header.jpg"><br />
    <br />
    <br /></td>
  </tr>
  <tr>
    <td bgcolor="#FFFFEF">Eliminare <%=denominazione %> come titolare ?
    	<input type="hidden" name="id" value="<%= id %>" />
    </td>
  </tr>
  <tr>
    <td align="center"><input type="submit" name="button" value="Si" />
     &nbsp; 
    <input type="button" name="button2" value="No" onclick="self.close();" /></td>
  </tr>
</table>
</form>
</body>
</html>
