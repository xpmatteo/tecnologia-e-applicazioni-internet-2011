package it.xpug.tai.lesson11.chat.jetty;

import javax.servlet.http.HttpServletRequest;

public class TaiRequestFromServletRequest implements TaiRequest {

	private final HttpServletRequest servletRequest;

	public TaiRequestFromServletRequest(HttpServletRequest request) {
		this.servletRequest = request;
	}

	@Override
	public String getTarget() {
		return servletRequest.getPathInfo();
	}

	@Override
	public String getParameter(String name) {
		return servletRequest.getParameter(name);
	}

	@Override
	public String getHeader(String name) {
		return servletRequest.getHeader(name);
	}

	@Override
	public boolean isPost() {
		return "post".equalsIgnoreCase(servletRequest.getMethod());
	}

}
