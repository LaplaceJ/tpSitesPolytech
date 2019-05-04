<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ajout spécialité</title>
</head>

<body>

<h1> Ajout de spécialité </h1> 

<c:if test = "${erreurType == 'EcoleInconnueException'}"> 

</br> 
<h2> École inconnue : ${ecole}  </h2>

</c:if>  


<c:if  test = "${erreurType == 'SpecialiteExisteDejaException'}"> 

</br> 
<h2> La spécialité existe déja   </h2>
</br> 
</br> 

<h2> Les spécialités de  ${ecole.nom}  </h2>
	
	<ul>
		<c:forEach items= "${requestScope.listeSpe}" var="spe"> 
			
			<li> ${spe.nom} ( ${spe.acronyme} ) 
			</li> 
		
		</c:forEach> 
	</ul>
	
</c:if>  

<c:if test = "${erreurType == 'OK'}"> 

	</br> 
	<h2> Création de la spécialité ${nomSpe} dans école ${ecole} 	    </h2>
	</br> 
	</br> 
	
	<h2> Les spécialités de  ${ecole}  </h2>
	
	<ul>
		<c:forEach items="${requestScope.listeSpe}" var="spe"> 
			
			<li> ${spe.nom} ( ${spe.acronyme} ) </li> 
		
		</c:forEach> 
	</ul>
</c:if>  


</body>
</html>
