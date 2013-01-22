<#include "header.ftl">

<h1>Register</h1>

<script>
$(function() {
	$("form").submit(function() {
	
		$.post(url_api + "/register",
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
      <label class="control-label" for="input01">uuid</label>
      <div class="controls">
        <input type="text" class="input-xlarge" id="uuid" name="uuid">
      </div>
    </div>
    
    <div class="control-group">
      <label class="control-label" for="input01">version</label>
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
      <label class="control-label" for="input01">email</label>
      <div class="controls">
        <input type="text" class="input-xlarge" id="email" name="email">
      </div>
    </div>

    <div class="control-group">
      <label class="control-label" for="input01">nickname</label>
      <div class="controls">
        <input type="text" class="input-xlarge" id="nickname" name="nickname">
      </div>
    </div>

    <div class="control-group">
      <label class="control-label" for="input01">gender</label>
      <div class="controls">
      	<select id="gender" name="gender">
      		<option>M</option>
      		<option>F</option>
      	</select>
      </div>
    </div>
    

	  <div class="form-actions">
	    <button type="submit" class="btn btn-primary">Save changes</button>
	    <button class="btn">Cancel</button>
	  </div>
    
  </fieldset>


</form>



<#include "footer.ftl">