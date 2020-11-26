<%@ include file="/WEB-INF/jsp/layout/taglibs.jsp"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<c:set var="lista" value="${sessionScope.sessioneUtente.menu}" />
<c:if test="${not empty lista}">
	<c:forEach items="${lista}" var="gruppo">
	  <tr>
		<td class="menuGroups"><b>${gruppo.nome}</b>
		  <ul>
			<c:forEach items="${gruppo.submenus}" var="funz">
				<c:if test="${funz.risorsa.absolute}">
					<c:set var="link" value="${funz.risorsa.nome}" />
					<li>
						<a href="${link}" target="_blank">
							<c:out value="${funz.nome}" />
						</a>
					</li>
				</c:if>
				<c:if test="${!funz.risorsa.absolute}">
					<c:set var="link" value="${path}${funz.risorsa.nome}" />
					<li>
						<a href="javascript:abandon('${link}')">
							<c:out value="${funz.nome}" />
						</a>
					</li>
				</c:if>
			</c:forEach>
		  </ul>
		</td>
	  </tr>
	</c:forEach>
</c:if>

