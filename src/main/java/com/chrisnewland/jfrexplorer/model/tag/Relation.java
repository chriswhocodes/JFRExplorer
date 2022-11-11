package com.chrisnewland.jfrexplorer.model.tag;

public class Relation
{
	private String name;

	public Relation(String name)
	{
		this.name = name;
	}

	@Override
	public String toString()
	{
		return "Relation{" + "name='" + name + '\'' + '}';
	}

	public String getName()
	{
		return name;
	}
}