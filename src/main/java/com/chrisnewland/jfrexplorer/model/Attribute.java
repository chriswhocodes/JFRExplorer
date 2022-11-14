package com.chrisnewland.jfrexplorer.model;

import com.chrisnewland.jfrexplorer.util.HtmlUtil;

public class Attribute
{
	private String name;
	private Object value;

	public Attribute(String name, Object value)
	{
		this.name = name;
		this.value = value;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public void setValue(Object value)
	{
		this.value = value;
	}

	public static CharSequence rowHeader()
	{
		return HtmlUtil.tr("th", "Name", "Value");
	}

	public CharSequence toRow()
	{
		return HtmlUtil.tr("td", name, value);
	}

	@Override
	public String toString()
	{
		return "Attribute{" + "name='" + name + '\'' + ", value=" + value + '}';
	}
}
