package it.xpug.tai.lesson11.chat.jetty;

public interface TaiRequest {
	String getTarget();
	String getParameter(String name);
	String getHeader(String name);
	boolean isPost();
}
