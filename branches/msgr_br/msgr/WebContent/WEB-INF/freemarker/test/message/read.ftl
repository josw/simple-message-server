<#include "../header.ftl">

<script>
$(function() {
	$("form").submit(function() {
	
		$.post(url_api + "/message/read",
		$(this).serialize(), 
		function(data) {
			$("#result").val(JSON.stringify(data, undefined, 2));
		});
	
	return false;
	});
});
</script>

<form class="form-horizontal">

  <fieldset>
	<legend>read message</legend>
    <div class="control-group">
      <label class="control-label" for="input01">rtoken</label>
      <div class="controls">
        <input type="text" class="input-xlarge" id="rtoken" name="rtoken">
      </div>
    </div>
    
    <div class="control-group">
      <label class="control-label" for="input01">room id</label>
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
      <label class="control-label" for="input01">rid</label>
      <div class="controls">
        <input type="text" class="input-xlarge" id="rid" name="rid">
      </div>
    </div>

    <div class="control-group">
      <label class="control-label" for="input01">msid</label>
      <div class="controls">
        <input type="text" class="input-xlarge" id="msid" name="msid">
      </div>
    </div>

	  <div class="form-actions">
	    <button type="submit" class="btn btn-primary">Submit</button>
	    <button class="btn">Cancel</button>
	  </div>
    
  </fieldset>


</form>

<textarea id="result"></textarea>


<#include "../footer.ftl">