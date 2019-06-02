package com.mdias.javaspringpostgresproject.transformer;

public interface Transformer<T, K> {
	K transform(T input);
}
