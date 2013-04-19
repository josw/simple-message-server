<#include "header.ftl">

<h1>Friend Add/Remove</h1>

<script>
$(function() {
	$("form").submit(function() {
	
		var url = ($('#add').val()==1)?"/member/friends/add":"/member/friends/delete";
		
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
      <label class="control-label" for="add">순서</label>
      <div class="controls">
      	<select id="add" name="add">
      		<option value=1>추가</option>
      		<option value=2>삭제</option>
      	</select>
      </div>
    </div>
    

    <div class="control-group">
      <label class="control-label" for="friend_mid">friend_mid</label>
      <div class="controls">
        <input type="text" class="input-xlarge" id="friend_mid" name="friend_mid">
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