package com.chrisnewland.jfrexplorer;

import com.chrisnewland.jfrexplorer.model.Model;
import com.chrisnewland.jfrexplorer.model.tag.Event;
import com.chrisnewland.jfrexplorer.util.HtmlUtil;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class HtmlGenerator
{
	public static void generateHtml(Model model, Path resourcesBasePath, Path outputPath)
	{
		try
		{
			String template = readTemplate(resourcesBasePath.resolve("templates"), "template.html");

			StringBuilder eventRows = new StringBuilder();

			for(Event event : model.getEventList())
			{
				eventRows.append(event.toRow());
			}

			CharSequence eventTable = HtmlUtil.table("myTable", "display cell-border", Event.rowHeader(), eventRows);

			template = template.replace("%TITLE%", "JFR Events");
			template = template.replace("%BODY%", eventTable);
			template = template.replace("$ALLCOLUMNS", "[0,1,2,3,4,5,6]");
			template = template.replace("$SORTCOLUMNS", "[]");

			Files.write(outputPath, template.getBytes(StandardCharsets.UTF_8));
		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}

	private static String readTemplate(Path templateDir, String name) throws IOException
	{
		return readTemplate(templateDir.resolve(name));
	}

	public static String readTemplate(Path templatePath) throws IOException
	{
		return new String(Files.readAllBytes(templatePath), StandardCharsets.UTF_8);
	}
}