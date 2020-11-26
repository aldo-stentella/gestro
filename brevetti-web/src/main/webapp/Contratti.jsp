<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="it.cnr.brevetti.util.UtilityFunctions"%>
<%@page import="it.cnr.brevetti.ejb.entities.DocumentoInfo"%>
<%@page import="java.util.List"%>
<%@page import="it.cnr.brevetti.util.Version"%>
<%@ include file="/WEB-INF/jsp/layout/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Gestione trovati - documenti associati</title>
<link href="css/standard.css?<%=Version.getVersion()%>" rel="stylesheet" type="text/css" />
<script language="JavaScript" type="text/JavaScript">
	function scarica(id, nome){
		var ref = window.open('download/all/'+nome+'?id='+id,'popup_dwl','height=550,width=850,scrollbars=yes,toolbar=no,status=no,location=no,screenX=100,screenY=20,top=20,left=100','fullscreen=no');
	}
</script>
<c:if test="${not empty act}">
	<script type="text/javascript" language="javascript">
		var schede = window.opener;
		while (schede.name != 'pag'){
			if(schede.name == 'popup_attach')	//popup aperto per cancellazione allegato
				schede.location.reload();
			schede = schede.opener;
		}
		schede.document.forms[0].updDoc.value=1
		window.close();
	</script>
</c:if>

<c:if test="${!param.readOnly}">
<script language="JavaScript" type="text/JavaScript">
	function elimina(id,nome){
		if(confirm('Confermare l\'eliminazione del documento '+nome)){
			var ref = window.open('DelContratti.do?id='+id,'popup_eld','height=550,width=850,scrollbars=yes,toolbar=no,status=no,location=no,screenX=100,screenY=20,top=20,left=100','fullscreen=no');
		}
	}
	
	function doSubmit(){
		if(document.forms[0].update.value==1){
			document.forms[0].submit();
		}
	}
</script>
</c:if>
</head>

<body>
<table class="area">
  <c:if test="${!param.readOnly}">
  <tr>
    <td width="122">Nuovo documento:</td>
    <td width="335"><form enctype="multipart/form-data" method="post" action="Contratti.do">
    <input type="hidden" name="tipoDocumentoId" value="<%=request.getParameter("tipoDocumentoId")%>" />
    <input type="hidden" name="entita" value="<%=request.getParameter("entita")%>" />
    <input type="hidden" name="nsrif" value="<%=request.getParameter("nsrif")%>" />
      <input type="file" name="fileField" onchange="document.forms[0].update.value=1;" />
      <input type="button" value="Aggiungi &gt;" onclick="doSubmit();" />
      <input type="hidden" name="update" value="0" />
    </form></td>
  </tr>
  </c:if>
  <tr>
    <td colspan="2">&nbsp;</td>
  </tr>
  <tr><td colspan="2" align="center"><table width="90%" border="1" cellpadding="1" cellspacing="2" class="deep">
    <tr>
      <td colspan="3">Repository documenti di tipo: <strong><c:out value="${tipoDocumento}" /></strong></td>
      </tr>
<%
	List<DocumentoInfo>docs = (List<DocumentoInfo>)request.getAttribute("docs");
	if(docs==null || docs.size()==0){
%>
    <tr valign="bottom">
      <td colspan="3" align="center">- - - nessun documento presente - - -</td>
    </tr>
<%
	} else for(DocumentoInfo td:docs){
		String nome = td.getNomeFile();
		String typeIcon = UtilityFunctions.decodeType(nome);
%>
    <tr valign="bottom">
      <td width="6%" align="center"><a href="#"><img src="images/icons/<%= typeIcon %>" alt="" width="16" height="16" onclick="scarica(<%=td.getDocumentoId() %>, '<%= nome %>'); " /></a></td>
      <td width="90%" align="left"><a href="#" onclick="scarica(<%=td.getDocumentoId() %>, '<%= nome %>');" ><%= nome %></a></td>
      <td width="4%" align="left"><img src="images/delete.gif" alt="" width="13" height="16" class="imaction" onclick="elimina(<%=td.getDocumentoId() %>, '<%= nome %>');" /></td>
    </tr>
<%
	}
%>
  </table></td></tr>
  <tr><td align="center" colspan="2"><input type="button" value="Chiudi" onclick="self.close();" /> </td></tr>
</table>
<br/>
</body>
</html>
