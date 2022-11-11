package com.chrisnewland.jfrexplorer.model.tag;

public class Field
{
	private String type;
	private String contentType;
	private String name;
	private String label;
	private String description;
	private String relation;

	public Field(String type, String contentType, String name, String label, String description, String relation)
	{
		this.type = type;
		this.contentType = contentType;
		this.name = name;
		this.label = label;
		this.description = description;
		this.relation = relation;
	}

	@Override
	public String toString()
	{
		return "Field{" + "type='" + type + '\'' + ", contentType='" + contentType + '\'' + ", name='" + name + '\'' + ", label='"
				+ label + '\'' + ", description='" + description + '\'' + ", relation='" + relation + '\'' + '}';
	}

	public String getType()
	{
		return type;
	}

	public String getContentType()
	{
		return contentType;
	}

	public String getName()
	{
		return name;
	}

	public String getLabel()
	{
		return label;
	}

	public String getDescription()
	{
		return description;
	}

	public String getRelation()
	{
		return relation;
	}
}
