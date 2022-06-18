package br.com.alura.loja.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.alura.loja.model.Product;

public class ProductDAO {

	private final EntityManager em;

	public ProductDAO(EntityManager em) {
		this.em = em;
	}

	public void register(Product product) {
		this.em.persist(product);
	}

	public void update(Product product) {
		this.em.merge(product);
	}

	public void remove(Product product) {
		product = em.merge(product);
		this.em.remove(product);
	}
	
	public Product searchById(Long id) {
		return em.find(Product.class, id);
	}
	
	public List<Product> searchAll() {
		String jpql = "SELECT p FROM Product p";
		return em.createQuery(jpql, Product.class).getResultList();
	}
	
	public List<Product> searchByName(String name) {
		String jpql = "SELECT p FROM Product p WHERE p.name = :name";
		return em.createQuery(jpql, Product.class)
				.setParameter("name", name)
				.getResultList();
	}
	
	public List<Product> searchByCategoryName(String name) {
		return em.createNamedQuery("Product.productsByCategory", Product.class)
				.setParameter("name", name)
				.getResultList();
	}
	
	public BigDecimal searchProductPriceWithName(String name) {
		String jpql = "SELECT p.price FROM Product p WHERE p.name = :name";
		return em.createQuery(jpql, BigDecimal.class)
				.setParameter("name", name)
				.getSingleResult();
	}


	public List<Product> searchByParameters(String name, BigDecimal price, LocalDate redistrationDate){
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Product> query = builder.createQuery(Product.class);
		Root<Product> from = query.from(Product.class);

		Predicate filters = builder.and();
		if(name != null && !name.trim().isEmpty()){
			filters = builder.and(filters, builder.equal(from.get("name"), name));
		}
		if(price != null){
			filters = builder.and(filters, builder.equal(from.get("price"), price));
		}
		if(redistrationDate != null){
			filters = builder.and(filters, builder.equal(from.get("registrationDate"), redistrationDate));
		}
		query.where(filters);

		return em.createQuery(query).getResultList();
	}
}
