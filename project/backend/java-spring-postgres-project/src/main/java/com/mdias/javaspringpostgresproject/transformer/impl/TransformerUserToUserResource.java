package com.mdias.javaspringpostgresproject.transformer.impl;

import com.mdias.javaspringpostgresproject.domain.User;
import com.mdias.javaspringpostgresproject.resource.UserResource;
import com.mdias.javaspringpostgresproject.transformer.Transformer;

public class TransformerUserToUserResource implements Transformer<User, UserResource>{

	@Override
	public UserResource transform(User input) {
		return new UserResource(input.getName());
	}

}
