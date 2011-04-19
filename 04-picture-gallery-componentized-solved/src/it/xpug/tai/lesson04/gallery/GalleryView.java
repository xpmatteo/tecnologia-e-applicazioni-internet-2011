package it.xpug.tai.lesson04.gallery;

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
//	private String[] fileNames;
	private PictureUrl pictureUrl = new PictureUrl();
	private Picture[] pictures;

	public GalleryView(String title, Picture[] pictures) {
		this.title = title;
		this.pictures = pictures;
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
		for (Picture picture : pictures) {
			result.add(pictureUrl.toUrl(picture));
		}
		return result;
	}
}
