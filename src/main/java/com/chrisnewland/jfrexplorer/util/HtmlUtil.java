package com.chrisnewland.jfrexplorer.util;

public class HtmlUtil
{

	public static CharSequence table(String cssClass, CharSequence headerRow, CharSequence rows)
	{
		return table(null, cssClass, headerRow, rows);
	}

	public static CharSequence table(String id, String cssClass, CharSequence headerRow, CharSequence rows)
	{
		StringBuilder builder = new StringBuilder();

		builder.append(openTag("table", id, cssClass)).append("\n");

		if (headerRow != null)
		{
			builder.append(openTag("thead")).append("\n");
			builder.append(headerRow);
			builder.append(closeTag("thead")).append("\n");
		}

		builder.append(openTag("tbody")).append("\n");
		builder.append(rows);
		builder.append(closeTag("tbody")).append("\n");

		builder.append(closeTag("table"));

		return builder;
	}

	public static CharSequence tr(String childTag, Object... children)
	{
		StringBuilder builder = new StringBuilder();

		builder.append(openTag("tr", null));

		for (Object child : children)
		{
			String childString = child == null ? "" : child.toString();

			builder.append(tag(childTag, childString, null));
		}

		builder.append(closeTag("tr")).append("\n");

		return builder;
	}

	public static CharSequence td(String content)
	{
		return td(content, null);
	}

	public static CharSequence td(String content, String cssClass)
	{
		return tag("td", content, cssClass);
	}

	public static CharSequence tag(String tag, String content, String cssClass)
	{
		StringBuilder builder = new StringBuilder();

		builder.append(openTag(tag, cssClass)).append(content).append(closeTag(tag));

		return builder;
	}

	private static CharSequence openTag(String tag)
	{
		return openTag(tag, null, null);
	}
	private static CharSequence openTag(String tag, String cssClass)
	{
		return openTag(tag, null, cssClass);
	}

	private static CharSequence openTag(String tag, String id, String cssClass)
	{
		StringBuilder builder = new StringBuilder();

		builder.append('<').append(tag);

		if (id != null)
		{
			builder.append(" id=\"").append(id).append("\"");
		}

		if (cssClass != null)
		{
			builder.append(" class=\"").append(cssClass).append("\"");
		}

		builder.append('>');

		return builder;
	}

	private static CharSequence closeTag(String tag)
	{
		StringBuilder builder = new StringBuilder();

		builder.append("</").append(tag).append('>');

		return builder;
	}
}