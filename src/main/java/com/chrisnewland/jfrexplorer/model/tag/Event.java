package com.chrisnewland.jfrexplorer.model.tag;

import com.chrisnewland.jfrexplorer.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class Event
{
	private String name;
	private List<String> categoryList;
	private String label;
	private boolean thread;
	private boolean startTime;
	private boolean stackTrace;

	private List<Field> fieldList = new ArrayList<>();

	public Event(String name, String categories, String label, boolean thread, boolean startTime, boolean stackTrace)
	{
		this.name = name;
		this.categoryList = StringUtil.splitCommaString(categories);
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
		return "Event{" + "name='" + name + '\'' + ", categoryList=" + categoryList + ", label='" + label + '\'' + ", thread="
				+ thread + ", startTime=" + startTime + ", stackTrace=" + stackTrace + ", fieldList=" + fieldList + '}';
	}

	public String getName()
	{
		return name;
	}

	public List<String> getCategoryList()
	{
		return categoryList;
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