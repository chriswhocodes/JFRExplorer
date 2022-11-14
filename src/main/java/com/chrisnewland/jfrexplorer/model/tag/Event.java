package com.chrisnewland.jfrexplorer.model.tag;

import com.chrisnewland.jfrexplorer.model.Attribute;
import com.chrisnewland.jfrexplorer.util.HtmlUtil;

import java.util.*;

public class Event
{
	private String name;
	private String category;
	private String label;

	private List<Attribute> attributeList = new ArrayList<>();

	private List<Field> fieldList = new ArrayList<>();

	public Event(String name, String category, String label, Map<String, String> unprocessedAttributes)
	{
		this.name = name;
		this.category = category == null ? null : category.replace("Java Virtual Machine", "JVM").replace(", ", " ->  ");
		this.label = label;

		for (Map.Entry<String, String> entry : unprocessedAttributes.entrySet())
		{
			this.attributeList.add(new Attribute(entry.getKey(), entry.getValue()));
		}
	}

	public void addField(Field field)
	{
		fieldList.add(field);
	}

	@Override
	public String toString()
	{
		return "Event{" + "name='" + name + '\'' + ", category=" + category + ", label='" + label + '\'' + ", attributeList="
				+ attributeList + ", fieldList=" + fieldList + '}';
	}

	public static CharSequence rowHeader()
	{
		return HtmlUtil.tr("th", "Name", "Category", "Label", "Attributes", "Fields");
	}

	public CharSequence toRow()
	{
		return HtmlUtil.tr("td", name, category, label, getAttributesHtml(), getFieldHtml());
	}

	private CharSequence getAttributesHtml()
	{
		StringBuilder attributeRows = new StringBuilder();

		for (Attribute attribute : attributeList)
		{
			attributeRows.append(attribute.toRow());
		}

		return HtmlUtil.table("attributeTable", Attribute.rowHeader(), attributeRows);
	}

	private CharSequence getFieldHtml()
	{
		StringBuilder fieldRows = new StringBuilder();

		boolean[] hasValue = new boolean[Field.FIELD_COLUMNS.length];

		for (Field field : fieldList)
		{
			if (field.getName() != null)
			{
				hasValue[0] = true;
			}
			if (field.getType() != null)
			{
				hasValue[1] = true;
			}
			if (field.getContentType() != null)
			{
				hasValue[2] = true;
			}
			if (field.getLabel() != null)
			{
				hasValue[3] = true;
			}
			//			if (field.getRelation() != null)
			//			{
			//				hasValue[4] = true;
			//			}
			if (field.getDescription() != null)
			{
				hasValue[4] = true;
			}
		}

		List<String> usedColumns = new ArrayList<>();

		for (int i = 0; i < Field.FIELD_COLUMNS.length; i++)
		{
			if (hasValue[i])
			{
				usedColumns.add(Field.FIELD_COLUMNS[i]);
			}
		}

		Set<String> columnSet = new HashSet<>(usedColumns);

		for (Field field : fieldList)
		{
			fieldRows.append(field.toRow(columnSet));
		}

		return HtmlUtil.table("fieldTable", Field.rowHeader(usedColumns.toArray(new String[usedColumns.size()])), fieldRows);
	}

	public String getName()
	{
		return name;
	}

	public String getCategory()
	{
		return category;
	}

	public String getLabel()
	{
		return label;
	}
}