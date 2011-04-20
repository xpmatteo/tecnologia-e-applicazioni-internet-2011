package it.xpug.tai.lesson05.gallery.pages;


import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class GalleryView {
	
	private final String title;
	private final String[] fileNames;
	private PictureUrl pictureUrl = new PictureUrl();

	public GalleryView(String title, String[] fileNames) {
		this.title = title;
		this.fileNames = fileNames;
	}

	public String toHtml() throws IOException {
		Configuration configuration = new Configuration();
		configuration.setDirectoryForTemplateLoading(new File("templates"));
		Template template = configuration.getTemplate("gallery_index.ftl");

		Map model = new HashMap();
		model.put("title", title);
		model.put("pictures", picturesList());
		
		StringWriter writer = new StringWriter();
		try {
			template.process(model, writer);
		} catch (TemplateException e) {
			throw new RuntimeException(e);
		}		
		return writer.toString();
	}

	private List<String> picturesList() {
		List<String> result = new ArrayList<String>();
		String[] files = fileNames;
		for (int i = 0; i < files.length; i++) {
			if (files[i].endsWith(".jpg")) {
				result.add(pictureUrl.toUrl(files[i]));
			}
		}
		return result;
	}
}
