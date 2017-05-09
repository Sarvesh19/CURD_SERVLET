package com.junald.session;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class HttpSessionTest implements HttpSessionListener {
	ServletContext ctx = null;
	static int total = 0, current = 0;

	public void sessionCreated(HttpSessionEvent e) {
		String sessionId = e.getSession().getId();
		System.out.println(sessionId);
		total++;
		current++;

		ctx = e.getSession().getServletContext();
		ctx.setAttribute("totalusers", total);
		ctx.setAttribute("currentusers", current);
	}

	public void sessionDestroyed(HttpSessionEvent e) {
		String sessionId = e.getSession().getId();
		System.out.println(sessionId);
		current--;
		ctx.setAttribute("currentusers", current);
	}

}