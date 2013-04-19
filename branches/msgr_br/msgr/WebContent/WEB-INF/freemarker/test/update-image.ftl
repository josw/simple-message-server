<#include "header.ftl">


<header>
<script type="text/javascript" src="${url.js}/jquery.form.js"></script>
</header>

<h1>UpdateImage</h1>

<script>
$(function() {
	$("form").attr('action',url_api + "/member/update/image");
/*	
	$("form").ajaxForm({
		success : function (o) {
			alert (o);		
		
		}
	});
*/
});
</script>
<p>
<form class="form-horizontal" enctype="multipart/form-data" method="post" name="mform" action="http://localhost:8080/msgr/member/update/image">

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
      <label class="control-label" for="page">file</label>
      <div class="controls">
        <input type="file" class="input-xlarge" id="afile" name="afile">
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