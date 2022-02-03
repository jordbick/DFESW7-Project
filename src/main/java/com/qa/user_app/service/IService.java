package com.qa.user_app.service;

import java.util.List;

public interface IService<T> {

	public List<T> getAll();
	public T getById(Long id);
	public T create(T item);
	public T update(Long id, T item);
	public void delete(Long id);
	
}
