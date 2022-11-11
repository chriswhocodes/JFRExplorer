package com.chrisnewland.jfrexplorer.model.tag;

import com.chrisnewland.jfrexplorer.util.StringUtil;

import java.util.List;

public class XmlContentType
{
	private String name;
	private List<String> annotationList;

	public XmlContentType(String name, String annotations)
	{
		this.name = name;
		this.annotationList = StringUtil.splitCommaString(annotations);
	}

	@Override
	public String toString()
	{
		return "XmlContentType{" + "name='" + name + '\'' + ", annotation='" + annotationList + '\'' + '}';
	}

	public String getName()
	{
		return name;
	}

	public List<String> getAnnotationList()
	{
		return annotationList;
	}
}