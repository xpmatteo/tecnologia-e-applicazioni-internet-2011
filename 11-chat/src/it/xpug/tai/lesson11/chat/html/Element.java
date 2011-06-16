package it.xpug.tai.lesson11.chat.html;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.String.format;

public class Element {

	private final List<Element> nestedElements = new ArrayList();
	private final String name;
	private final Map<String, String> attributes = new HashMap();
	private String textContent;

	public Element(String name) {
		this.name = name;
	}
	
	public Element text(String text) {
		this.textContent = text;
		return this;
	}
	
	public Element add(Element element) {
		nestedElements.add(element);
		return this;
	}

	public Element with(String attributeName, String attributeValue) {
		attributes.put(attributeName, attributeValue);
		return this;
	}

	public String toHtml() {
		String html = startTag();
		if (null != textContent) {
			html += textContent;
		} else {
			html += tagContents();
		}
		html += endTag();
		return html;
	}

	private String tagContents() {
		String html = "";
		for (Element element : nestedElements) {
			html += element.toHtml();
		}
		return html;
	}

	private String endTag() {
		return format("</%s>", name);
	}

	private String startTag() {
		return format("<%s%s>", name, attributesList());
	}
	
	private String attributesList() {
		String result = "";
		for (String attributeName : attributes.keySet()) {
			result += format(" %s='%s'", attributeName, attributes.get(attributeName));
		}
		return result;
	}
}
