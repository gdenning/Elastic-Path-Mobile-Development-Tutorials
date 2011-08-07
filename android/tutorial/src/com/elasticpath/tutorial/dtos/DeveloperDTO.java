package com.elasticpath.tutorial.dtos;

import java.io.Serializable;

public class DeveloperDTO implements Serializable {
	private String name;
	private String verb;

	public DeveloperDTO(final String name, final String verb) {
		this.name = name;
		this.verb =  verb;
	}
	public String getName() {
		return name;
	}
	public void setName(final String name) {
		this.name = name;
	}
	public String getVerb() {
		return verb;
	}
	public void setVerb(final String verb) {
		this.verb = verb;
	}
}
