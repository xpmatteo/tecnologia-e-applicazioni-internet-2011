package it.xpug.tai.lesson04.gallery;

import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import freemarker.template.Configuration;
import freemarker.template.Template;

import static org.junit.Assert.assertEquals;


public class FreemarkerTest {
	@Test
	public void testFreemarker() throws Exception {
	    String templateString = "Hello, ${user}";
	    Template template = new Template("my template", 
	        new StringReader(templateString), new Configuration());

	    Map model = new HashMap();
	    model.put("user", "Tizius");

	    StringWriter writer = new StringWriter();
	    template.process(model, writer);
	    assertEquals("Hello, Tizius", writer.toString());
	}
	
	@Test
	public void loadFreemarkerTemplateFromFile() throws Exception {
		Configuration configuration = new Configuration();
		configuration.setDirectoryForTemplateLoading(new File("templates"));
		Template template = configuration.getTemplate("test.ftl");
		
		Map model = new HashMap();
		model.put("user", "Pippo");
		StringWriter writer = new StringWriter();
		template.process(model, writer );
		
		assertEquals("Goodbye, Pippo!", writer.toString());
	}
}
