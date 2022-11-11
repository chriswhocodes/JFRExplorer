package com.chrisnewland.jfrexplorer.model.tag;

public class XmlType
{
	private String name;

	private String contentType;
	private String javaType;
	private boolean unsigned;
	private String parameterType;
	private String fieldType;

	public XmlType(String name, String contentType, String javaType, boolean unsigned, String parameterType, String fieldType)
	{
		this.name = name;
		this.contentType = contentType;
		this.javaType = javaType;
		this.unsigned = unsigned;
		this.parameterType = parameterType;
		this.fieldType = fieldType;
	}

	@Override
	public String toString()
	{
		return "XmlType{" + "name='" + name + '\'' + ", contentType='" + contentType + '\'' + ", javaType='" + javaType + '\''
				+ ", unsigned=" + unsigned + ", parameterType='" + parameterType + '\'' + ", fieldType='" + fieldType + '\'' + '}';
	}

	public String getName()
	{
		return name;
	}

	public String getContentType()
	{
		return contentType;
	}

	public String getJavaType()
	{
		return javaType;
	}

	public boolean isUnsigned()
	{
		return unsigned;
	}

	public String getParameterType()
	{
		return parameterType;
	}

	public String getFieldType()
	{
		return fieldType;
	}
}
