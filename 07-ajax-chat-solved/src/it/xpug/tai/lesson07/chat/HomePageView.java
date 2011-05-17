package it.xpug.tai.lesson07.chat;


import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class HomePageView {
	
	public String toHtml() throws IOException {
		Configuration configuration = new Configuration();
		configuration.setDirectoryForTemplateLoading(new File("templates"));
		Template template = configuration.getTemplate("index.ftl");

		Map model = new HashMap();
		StringWriter writer = new StringWriter();
		try {
			template.process(model, writer);
		} catch (TemplateException e) {
			throw new RuntimeException(e);
		}		
		return writer.toString();
	}

}
