<#include "header.ftl">
<style>
.maze { width:18px; height:18px;float:left;padding:0 }
.mazet { width:18px; height:18px; }

.a {border-left:1px solid}
.b {border-right:1px solid}
.c {border-top:1px solid}
.d {border-bottom:1px solid}
.f {float:left}
.cb {clear:both}
</style>


<h1>Index</h1>



<#if maze??>
<table border=0 margin=0>
<#list maze as line>
<tr>
  <#list line as item>
  
	<td class="mazet<#if item.n == 1> a</#if><#if item.e == 1> d</#if><#if item.w == 1> c</#if><#if item.s == 1> b</#if>"></td>
  </#list>
</tr>
</#list>
</table>
</#if>



<tr><td style="border:1px solid;width:10px;height:10px;"></td></tr>
</table>


<#include "footer.ftl">