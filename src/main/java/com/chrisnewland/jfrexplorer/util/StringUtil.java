package com.chrisnewland.jfrexplorer.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StringUtil
{
	public static Map<String, String> getAttributes(String line)
	{
		Map<String, String> result = new HashMap<>();

		if (line != null)
		{
			int startPos = line.indexOf(' ');

			if (startPos == -1)
			{
				return result;
			}

			int endPos = line.length();

			if (line.endsWith("/>"))
			{
				endPos -= 2;
			}
			else
			{
				endPos--;
			}

			StringBuilder key = new StringBuilder();
			StringBuilder val = new StringBuilder();

			boolean inValue = false;

			for (int i = startPos; i < endPos; i++)
			{
				char c = line.charAt(i);

				switch (c)
				{
				case ' ':
					if (!inValue)
					{
						key.setLength(0);
					}
					else
					{
						val.append(' ');
					}
					break;
				case '"':
					if (inValue)
					{
						// finished attr
						result.put(key.toString(), val.toString());
						key.setLength(0);
						val.setLength(0);
						inValue = false;
					}
					else
					{
						inValue = true;
					}
					break;
				case '=':
					if (inValue)
					{
						val.append('=');
					}
					break;
				default:
					if (inValue)
					{
						val.append(c);
					}
					else
					{
						key.append(c);
					}
				}
			}
		}

		return result;
	}

	public static List<String> splitCommaString(String line)
	{
		String[] parts = line.split(",");

		List<String> result = new ArrayList<>();

		for (String part : parts)
		{
			result.add(part.trim());
		}

		return result;
	}

	public static boolean isClosingTag(String line, String tagName)
	{
		return line.equals("</" + tagName + ">");
	}
}