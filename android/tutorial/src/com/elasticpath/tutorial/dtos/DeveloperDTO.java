package com.elasticpath.tutorial.dtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DeveloperDTO implements Serializable {
	private String name;
	private String verb;
	private List<String> emails = new ArrayList<String>();

	public DeveloperDTO() {
		
	}
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
	public List<String> getEmails() {
		return emails;
	}
}
