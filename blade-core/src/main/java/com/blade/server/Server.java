/**
 * Copyright (c) 2015, biezhi 王爵 (biezhi.me@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.blade.server;

import java.util.EnumSet;

import javax.servlet.DispatcherType;

import org.eclipse.jetty.servlet.ServletContextHandler;

import com.blade.CoreFilter;

import blade.kit.log.Logger;

/**
 * 
 * <p>
 * Jetty服务
 * </p>
 *
 * @author	<a href="mailto:biezhi.me@gmail.com" target="_blank">biezhi</a>
 * @since	1.0
 */
public class Server {
	
	private static final Logger LOGGER = Logger.getLogger(Server.class);
	
	private int port = 9000;
	
	public Server(int port) {
		this.port = port;
	}
	
	public void run(String contextPath) throws Exception{
		
		org.eclipse.jetty.server.Server server = new org.eclipse.jetty.server.Server(port);
		
	    ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
	    context.setContextPath(contextPath);
	    context.setResourceBase(System.getProperty("java.io.tmpdir"));
	    context.addFilter(CoreFilter.class, "/*", EnumSet.of(DispatcherType.REQUEST));
        server.setHandler(context);
        
	    server.start();
	    LOGGER.info("Blade Server Listen on 0.0.0.0:" + port);
	    server.join();
	}
}