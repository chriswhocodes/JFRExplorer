package com.chrisnewland.jfrexplorer.model.tag;

import com.chrisnewland.jfrexplorer.util.HtmlUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Event
{
	private String name;
	private String category;
	private String label;
	private boolean thread;
	private boolean startTime;
	private boolean stackTrace;

	private List<Field> fieldList = new ArrayList<>();

	public Event(String name, String category, String label, boolean thread, boolean startTime, boolean stackTrace)
	{
		this.name = name;
		this.category = category == null ? null : category.replace("Java Virtual Machine", "JVM").replace(", ", " ->  ");
		this.label = label;
		this.thread = thread;
		this.startTime = startTime;
		this.stackTrace = stackTrace;
	}

	public void addField(Field field)
	{
		fieldList.add(field);
	}

	@Override
	public String toString()
	{
		return "Event{" + "name='" + name + '\'' + ", category=" + category + ", label='" + label + '\'' + ", thread=" + thread
				+ ", startTime=" + startTime + ", stackTrace=" + stackTrace + ", fieldList=" + fieldList + '}';
	}

	public static CharSequence rowHeader()
	{
		return HtmlUtil.tr("th", "Name", "Category", "Label", "Thread", "StartTime", "StackTrace", "Fields");
	}

	public CharSequence toRow()
	{
		return HtmlUtil.tr("td", name, category, label, thread, startTime, stackTrace, getFieldHtml());
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

	public boolean isThread()
	{
		return thread;
	}

	public boolean isStartTime()
	{
		return startTime;
	}

	public boolean isStackTrace()
	{
		return stackTrace;
	}

	public List<Field> getFieldList()
	{
		return fieldList;
	}
}