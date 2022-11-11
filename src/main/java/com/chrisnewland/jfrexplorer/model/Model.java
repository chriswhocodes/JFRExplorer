package com.chrisnewland.jfrexplorer.model;

import com.chrisnewland.jfrexplorer.model.tag.*;

import java.util.ArrayList;
import java.util.List;

public class Model
{
	private List<Event> eventList = new ArrayList<>();
	private List<Field> fieldList = new ArrayList<>();
	private List<Relation> relationList = new ArrayList<>();
	private List<Type> typeList = new ArrayList<>();
	private List<XmlType> xmlTypeList = new ArrayList<>();
	private List<XmlContentType> xmlContentTypeList = new ArrayList<>();

	public void addEvent(Event event)
	{
		eventList.add(event);
	}

	public void addField(Field field)
	{
		fieldList.add(field);
	}

	public void addRelation(Relation relation)
	{
		relationList.add(relation);
	}

	public void addType(Type type)
	{
		typeList.add(type);
	}

	public void addXmlType(XmlType xmlType)
	{
		xmlTypeList.add(xmlType);
	}

	public void addXmlContentType(XmlContentType xmlContentType)
	{
		xmlContentTypeList.add(xmlContentType);
	}

	@Override
	public String toString()
	{
		return "Model{" + "eventList=" + eventList + ", fieldList=" + fieldList + ", relationList=" + relationList + ", typeList="
				+ typeList + ", xmlTypeList=" + xmlTypeList + ", xmlContentTypeList=" + xmlContentTypeList + '}';
	}
}