package com.nttdata.agni.service;

import org.springframework.context.ApplicationEvent;

/**
 * This is an optional class used in publishing application events.
 * This can be used to inject events into the Spring Boot audit management endpoint.
 */
public class SystemServiceEvent extends ApplicationEvent {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	public SystemServiceEvent(Object source) {
		super(source);
		// TODO Auto-generated constructor stub
	}

	

    public String toString() {
        return "My SystemService Event";
    }
}