package com.mdias.javaspringpostgresproject.exception;

public class UserNotFoundGitHubException extends Exception {

	private static final long serialVersionUID = 7642667216479342376L;
	
	public UserNotFoundGitHubException(String message) {
		super(message);
	}

}
