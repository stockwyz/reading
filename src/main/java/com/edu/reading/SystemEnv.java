package com.edu.reading;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SystemEnv {
	@Value("${http.proxy}")
	private Boolean proxy;
	
	@PostConstruct
	public void init () {
    	if(proxy) {
	    	System.setProperty("http.proxyHost", "172.20.6.26");
	    	System.setProperty("http.proxyPort", "3128");
	    	System.setProperty("http.nonProxyHosts", "127.0.0.1 | localhost");
	    	
	    	System.setProperty("https.proxyHost", "172.20.6.26");
	    	System.setProperty("https.proxyPort", "3128");
	    	System.setProperty("https.nonProxyHosts", "127.0.0.1 | localhost");
    	}		
	}
}
