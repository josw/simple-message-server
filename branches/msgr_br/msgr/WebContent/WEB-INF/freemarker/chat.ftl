{"chat":[
<#if chat_items??>
<#list chat_items as item>
{
	"added":"${item.added?string("yyyy-MM-dd HH:mm:ss")}",
	"id":"${item.id}",
	"user":"${item.user}",
	"text":"${item.message}"
},
</#list>
</#if>
]}
<#--
<chat>
<#if chat_items??>
<#list chat_items as item>
<message added="${item.added?string("yyyy-MM-dd HH:mm:ss")}" id="${item.id}">
	<user>${item.user}</user>
	<text>${item.message}</text>
</message>
</#list>
</#if>
</chat>
-->