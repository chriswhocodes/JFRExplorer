package com.chrisnewland.jfrexplorer.model.tag;

import com.chrisnewland.jfrexplorer.util.HtmlUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

	static final String[] FIELD_COLUMNS = new String[] { "Name", "Type", "ContentType", "Label", /*"Relation",*/ "Description" };

	public static CharSequence rowHeader(String[] columns)
	{
		return HtmlUtil.tr("th", columns);
	}

	public CharSequence toRow(Set<String> columns)
	{
		List<Object> values = new ArrayList<>();

		if (columns.contains("Name"))
		{
			values.add(name);
		}
		if (columns.contains("Type"))
		{
			values.add(type);
		}
		if (columns.contains("ContentType"))
		{
			values.add(contentType);
		}
		if (columns.contains("Label"))
		{
			values.add(label);
		}
//		if (columns.contains("Relation"))
//		{
//			values.add(relation);
//		}
		if (columns.contains("Description"))
		{
			values.add(description);
		}

		return HtmlUtil.tr("td", values.toArray());
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
