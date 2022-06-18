package br.com.alura.loja.dao;

import javax.persistence.EntityManager;

import br.com.alura.loja.model.Category;

public class CategoryDAO {
	
	private final EntityManager em;

	public CategoryDAO(EntityManager em) {
		this.em = em;
	}
	
	public void register(Category category) {
		this.em.persist(category);
	}
	
	public void update(Category category) {
		this.em.merge(category);
	}
	
	public void remove(Category category) {
		category = em.merge(category);
		this.em.remove(category);
	}

}
