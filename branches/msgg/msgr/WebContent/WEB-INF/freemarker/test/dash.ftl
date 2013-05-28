<#include "header.ftl">

<h1>Dash</h1>

<script>
$(function() {
	$("form").submit(function() {
	
		$.post(url_api + "/message/dash",
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
      <label class="control-label" for="tomid">tomid</label>
      <div class="controls">
        <input type="text" class="input-xlarge" id="tomid" name="tomid">
      </div>
    </div>
    
    <div class="control-group">
      <label class="control-label" for="rid">rid</label>
      <div class="controls">
        <input type="text" class="input-xlarge" id="rid" name="rid">
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