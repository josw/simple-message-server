<select name="method" id="method">
<option value=0>API LIST</option>
<option>register</option>
<option>dupEmail</option>
<option>dupNickname</option>
</select>
<script>
$('#method').change(function() {
	if ($(this).val() != 0)
	location.href=url_base+"/test/"+$(this).val();
});
</script>
