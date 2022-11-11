package com.chrisnewland.jfrexplorer;

import com.chrisnewland.jfrexplorer.model.Model;
import com.chrisnewland.jfrexplorer.model.TagType;
import com.chrisnewland.jfrexplorer.model.tag.*;
import com.chrisnewland.jfrexplorer.util.StringUtil;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MetadataProcessor
{
	private static final String ATTR_NAME = "name";
	private static final String ATTR_LABEL = "label";
	private static final String ATTR_CATEGORY = "category";
	private static final String ATTR_START_TIME = "startTime";
	private static final String ATTR_STACK_TRACE = "stackTrace";
	private static final String ATTR_THREAD = "thread";
	private static final String ATTR_TYPE = "type";
	private static final String ATTR_CONTENT_TYPE = "contentType";
	private static final String ATTR_DESCRIPTION = "description";
	private static final String ATTR_RELATION = "relation";
	private static final String ATTR_JAVA_TYPE = "javaType";
	private static final String ATTR_UNSIGNED = "unsigned";
	private static final String ATTR_PARAMETER_TYPE = "parameterType";
	private static final String ATTR_FIELD_TYPE = "fieldType";
	private static final String ATTR_ANNOTATION = "annotation";

	private Iterator<String> iterator;

	private Map<String, String> attrMap;

	private Path metadataPath;

	public static void main(String[] args) throws Exception
	{
		MetadataProcessor metadataProcessor = new MetadataProcessor(Paths.get(args[0]));

		Model model = metadataProcessor.process();

		System.out.println(model);
	}

	public MetadataProcessor(Path metadataPath)
	{
		this.metadataPath = metadataPath;
	}

	private Model process() throws Exception
	{
		List<String> lines = Files.readAllLines(metadataPath);

		iterator = lines.iterator();

		boolean metadataStarted = false;

		Model model = new Model();

		TagType tagType = null;

		while (iterator.hasNext())
		{
			String line = getNextLine();

			if (!line.isEmpty())
			{
				try
				{
					tagType = getTag(line);
				}
				catch (Exception e)
				{
					if (metadataStarted)
					{
						throw e;
					}
					else
					{
						continue;
					}
				}

				switch (tagType)
				{
				case METADATA:
					metadataStarted = true;
					break;

				case EVENT:
					model.addEvent(handleEvent());
					break;

				case FIELD:
					model.addField(handleField());
					break;

				case TYPE:
					model.addType(handleType());
					break;

				case RELATION:
					model.addRelation(handleRelation());
					break;

				case XML_TYPE:
					model.addXmlType(handleXmlType());
					break;

				case XML_CONTENT_TYPE:
					model.addXmlContentType(handleXmlContentType());
					break;
				}

				if (attrMap != null && !attrMap.isEmpty())
				{
					System.out.println(line);
					for (Map.Entry<String, String> entry : attrMap.entrySet())
					{
						System.out.println("unprocessed:" + entry);
					}
				}
			}
		}

		return model;
	}

	private String getNextLine()
	{
		String line = iterator.next();

		while (!line.endsWith(">"))
		{
			line += iterator.next();
		}

		line = line.trim();

		//System.out.println(line);

		attrMap = StringUtil.getAttributes(line);

		return line;
	}

	private TagType getTag(String line)
	{
		int spacePos = line.indexOf(' ');

		if (spacePos == -1)
		{
			throw new RuntimeException("Could not get tag from " + line);
		}

		String tagName = line.substring(1, spacePos);

		return TagType.forName(tagName);
	}

	private Event handleEvent()
	{
		Event event = new Event(getStringAttr(ATTR_NAME), getStringAttr(ATTR_CATEGORY), getStringAttr(ATTR_LABEL),
				getBooleanAttr(ATTR_THREAD), getBooleanAttr(ATTR_START_TIME), getBooleanAttr(ATTR_STACK_TRACE));

		String nextLine = getNextLine();

		while (!StringUtil.isClosingTag(nextLine, TagType.EVENT.getTagName()))
		{
			Field field = handleField();

			event.addField(field);

			nextLine = getNextLine();
		}

		return event;
	}

	private Type handleType()
	{
		Type type = new Type(getStringAttr(ATTR_NAME));

		String nextLine = getNextLine();

		while (!StringUtil.isClosingTag(nextLine, TagType.TYPE.getTagName()))
		{
			Field field = handleField();

			type.addField(field);

			nextLine = getNextLine();
		}

		return type;
	}

	private Field handleField()
	{
		return new Field(getStringAttr(ATTR_TYPE), getStringAttr(ATTR_CONTENT_TYPE), getStringAttr(ATTR_NAME),
				getStringAttr(ATTR_LABEL), getStringAttr(ATTR_DESCRIPTION), getStringAttr(ATTR_RELATION));
	}

	private Relation handleRelation()
	{
		return new Relation(getStringAttr(ATTR_NAME));
	}

	private XmlType handleXmlType()
	{
		return new XmlType(getStringAttr(ATTR_NAME), getStringAttr(ATTR_CONTENT_TYPE), getStringAttr(ATTR_JAVA_TYPE),
				getBooleanAttr(ATTR_UNSIGNED), getStringAttr(ATTR_PARAMETER_TYPE), getStringAttr(ATTR_FIELD_TYPE));
	}

	private XmlContentType handleXmlContentType()
	{
		return new XmlContentType(getStringAttr(ATTR_NAME), getStringAttr(ATTR_ANNOTATION));
	}

	private String getStringAttr(String name)
	{
		return attrMap.remove(name);
	}

	private boolean getBooleanAttr(String name)
	{
		String value = attrMap.remove(name);

		return (value != null) ? Boolean.parseBoolean(value) : false;
	}
}