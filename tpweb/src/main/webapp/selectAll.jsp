<%@page import="java.util.List"%>
<%@page import="model.Annuaire"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.persistence.EntityManagerFactory"%>

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
	<h1>Contenu Table</h1>
	<%
	    List<Annuaire> resultList = em.createNamedQuery("Annuaire.findAll").getResultList();
		String content = "<table class=\"tableau\"><tr>" + Annuaire.toStringColumnTab() + "</tr>";
		for (Annuaire a : resultList){
			content += "<tr>" + a.toStringTab() + "<td><a href=\"http://localhost:8080/tpWebJpa/delete.jsp?num=" + a.getNum() + "\">Del</a></td></tr>";
		}
		content += "</table>";
	%>
	<%=content%>
	
	<h1>Formulaire de recherche</h1>
	
	<form method="post" action="selectByCriteria.jsp">
    	<input name="table" type="hidden" value="annuaire">
  		<input placeholder="Nom" name="nom" type="text">
  		<input placeholder="Prenom" name="prenom" type="text">
  		<input placeholder="Sexe" name="sexe" type="text">
  		<input placeholder="Fonction" name="fonction" type="text">
  	  	  	  	
  		<button type="reset" value="Reset">Effacer</button>
  		<button type="submit" value="Submit">Envoyer</button>
  	</form>
  	
  
  	<h1>Formulaire d'insertion</h1>
	
	<form method="post" action="insert.jsp">
    	<input name="table" type="hidden" value="annuaire">
  		<input placeholder="Nom" name="nom" type="text">
  		<input placeholder="Prenom" name="prenom" type="text">
  		<input placeholder="Sexe" name="sexe" type="text">
  		<input placeholder="Tel" name="tel" type="number">
  		<input placeholder="Fonction" name="fonction" type="text">
  	  	  	  	
  		<button type="reset" value="Reset">Effacer</button>
  		<button type="submit" value="Submit">Envoyer</button>
  	</form>
</body>
</html>