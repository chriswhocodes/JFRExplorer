package com.chrisnewland.jfrexplorer.model.tag;

import java.util.ArrayList;
import java.util.List;

public class Type
{
	private String name;

	private List<Field> fieldList = new ArrayList<>();

	public Type(String name)
	{
		this.name = name;
	}

	public void addField(Field field)
	{
		fieldList.add(field);
	}

	@Override
	public String toString()
	{
		return "Type{" + "name='" + name + '\'' + ", fieldList=" + fieldList + '}';
	}

	public String getName()
	{
		return name;
	}

	public List<Field> getFieldList()
	{
		return fieldList;
	}
}