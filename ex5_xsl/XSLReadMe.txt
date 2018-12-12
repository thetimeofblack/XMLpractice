xsl is a kind of document based on the html document. It can transfer xml document into html document for data presentation by using xsl document type definition 

xslt <template>
<xsl:template> 元素用于构建模板。
match 属性用于关联 XML 元素和模板。match 属性也可用来为整个 XML 文档定义模板。match 属性的值是 XPath 表达式（举例，match="/" 定义整个文档）。
xslt <value-of>
<xsl:value-of> 元素用于提取某个 XML 元素的值，并把值添加到转换的输出流中
xslt <for-each>
XSL <xsl:for-each> 元素可用于选取指定的节点集中的每个 XML 元素：
xslt <sort>
如需对输出结果进行排序，只要简单地在 XSL 文件中的 <xsl:for-each> 元素内部添加一个 <xsl:sort> 元素：
xslt <if>     
<xsl:if test="expression">
...如果条件成立则输出...
</xsl:if>
xslt <choose>
基本运算符
= （等于）
!= （不等于）
&lt; （小于）
&gt; （大于）
