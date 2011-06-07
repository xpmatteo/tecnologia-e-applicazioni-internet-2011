package it.xpug.tai.lesson09.chat.jetty;

public interface TaiRequest {
	String getTarget();
	String getParameter(String name);
	String getMethod();
	String getHeader(String name);
}
