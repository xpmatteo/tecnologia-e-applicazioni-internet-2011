package it.xpug.tai.lesson11.chat;

import it.xpug.tai.lesson11.chat.jetty.TaiRequest;

import java.util.HashMap;
import java.util.Map;

public class FakeTaiRequest implements TaiRequest {

	private Map<String, String> parameters = new HashMap<String, String>();
	private Map<String, String> headers = new HashMap<String, String>();
	private String method = "GET";
	private String target = "/";

	@Override
	public String getTarget() {
		return this.target ;
	}

	@Override
	public String getParameter(String name) {
		return parameters.get(name);
	}
	
	@Override
	public String getMethod() {
		return method;
	}

	@Override
	public String getHeader(String name) {
		return headers.get(name.toLowerCase());
	}

	public void setupParameter(String name, String value) {
		this.parameters .put(name, value);
	}

	public void setupMethod(String method) {
		this.method = method;
	}

	public void setupTarget(String string) {
		this.target = string;
	}

}
