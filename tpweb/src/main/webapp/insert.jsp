<%@page import="java.util.List"%>
<%@page import="model.Annuaire"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.persistence.EntityManagerFactory"%>

<jsp:useBean id="insertedAnnuaire" class="model.Annuaire" scope="request"/>
<jsp:setProperty name="insertedAnnuaire" property="*"/>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" type="text/css" href="select.css">
	<title>Select</title>
</head>
<body>
	<% EntityManagerFactory emf = Persistence.createEntityManagerFactory("tpWebJpa");
	   EntityManager em = emf.createEntityManager();
	%>
	<%
		em.getTransaction().begin();
		em.persist(insertedAnnuaire);
		em.getTransaction().commit();
		response.sendRedirect("selectAll.jsp");
	%>
	
	<!-- On vide la session de cette attribut pour effectuer une nouvelles rechercher (pour ne pas conserver les anciennes donées) -->
	<%session.removeAttribute("selectedAnnuaire"); %>
</body>
</html>