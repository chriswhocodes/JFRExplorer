package com.chrisnewland.jfrexplorer.model;

public enum TagType
{
	METADATA("MetaData"),
	EVENT("Event"),
	FIELD("Field"),
	TYPE("Type"),
	RELATION("Relation"),
	XML_TYPE("XmlType"),
	XML_CONTENT_TYPE("XmlContentType");

	private String tagName;

	TagType(String tagName)
	{
		this.tagName = tagName;
	}

	public String getTagName()
	{
		return tagName;
	}

	public static TagType forName(String tagName)
	{
		for (TagType tagType : values())
		{
			if (tagType.tagName.equals(tagName))
			{
				return tagType;
			}
		}

		throw new RuntimeException("No Tag for tagName:" + tagName);
	}
}