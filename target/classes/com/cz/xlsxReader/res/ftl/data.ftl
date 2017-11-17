<div class="panel panel-default">

	<div class="panel-heading" style="text-align: center">
		<img src="public/img/3smartDevice/${name}.png" width="200px;"/><br>
		<h2><span class="label label-success" >${title}</span></h2>
	</div>

	<table class="table">  
	
		<#list specses as spece>
		<tr> 
			<td style="font-weight: bold;">${spece.key}</td>
			<td >${spece.value}</td>
		</tr>
		</#list>
		
	</table>
	
</div>	

<script type="text/javascript">
    $('.cz_hide').prev().hide();
</script>	