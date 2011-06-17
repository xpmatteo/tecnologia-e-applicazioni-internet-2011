package it.xpug.tai.lesson11.chat.html;

import org.junit.Test;

import static name.vaccari.matteo.html.Assert.assertDomEquals;


public class ElementTest {

	@Test
	public void returnsEmptyTag() throws Exception {
		assertDomEquals("<div></div>", new Element("div").toHtml());
	}
	
	@Test
	public void addNestedElement() throws Exception {
		Element element = new Element("div").add(new Element("p"));
		assertDomEquals("<div><p></p></div>", element.toHtml());
	}
	
	@Test
	public void addNestedElements() throws Exception {
		Element element = new Element("div") {{
			add(new Element("p"));
			add(new Element("img"));
		}};
		assertDomEquals("<div><p/><img/></div>", element.toHtml());
	}
	
	@Test
	public void addOneAttribute() throws Exception {
		Element element = new Element("div") {{
			with("id", "foobar");
		}};
	
		assertDomEquals("<div id='foobar'></div>", element.toHtml());
	}
	
	@Test
	public void addText() throws Exception {
		Element element = new Element("div") {{
			text("foobar");
		}};
	
		assertDomEquals("<div>foobar</div>", element.toHtml());
	}
}
