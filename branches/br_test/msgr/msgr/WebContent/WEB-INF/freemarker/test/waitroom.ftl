<#include "header.ftl">

<h1>WaitRoom</h1>

<script>
$(function() {
	$("form").submit(function() {
	
		var url = ($('#order').val()==1)?"/member/wait":"/member/wait/around";
		
		$.post(url_api + url,
		$(this).serialize(), 
		function(data) {
			$("#result").val(JSON.stringify(data, undefined, 2));
		});
	
	return false;
	});
});
</script>
<p>
<form class="form-horizontal">

  <fieldset>

    
    <div class="control-group">
      <label class="control-label" for="aid">version</label>
      <div class="controls">
      	<select id="aid" name="aid">
      		<#if apps??>
      		<#list apps as app>
      			<option>${app.aid}</option>
      		</#list>
      		</#if>
      	</select>
      </div>
    </div>
    
    <div class="control-group">
      <label class="control-label" for="rtoken">rtoken</label>
      <div class="controls">
        <input type="text" class="input-xlarge" id="rtoken" name="rtoken">
      </div>
    </div>
    
    <div class="control-group">
      <label class="control-label" for="order">순서</label>
      <div class="controls">
      	<select id="order" name="order">
      		<option value=1>최근 접속자</option>
      		<option value=2>거리</option>
      	</select>
      </div>
    </div>
    

    <div class="control-group">
      <label class="control-label" for="page">page</label>
      <div class="controls">
        <input type="text" class="input-xlarge" id="page" name="page">
      </div>
    </div>

    <div class="control-group">
      <label class="control-label" for="distance">거리</label>
      <div class="controls">
        <input type="text" class="input-xlarge" id="distance" name="distance" value=10>
      </div>
    </div>


	  <div class="form-actions">
	    <button type="submit" class="btn btn-primary">Submit</button>
	    <button class="btn">Cancel</button>
	  </div>
    
  </fieldset>


</form>
</p>



<#include "footer.ftl">