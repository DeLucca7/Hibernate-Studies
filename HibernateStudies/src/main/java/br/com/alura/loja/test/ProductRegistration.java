package br.com.alura.loja.test;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoryDAO;
import br.com.alura.loja.dao.ProductDAO;
import br.com.alura.loja.model.Category;
import br.com.alura.loja.model.Product;
import br.com.alura.loja.util.JPAUtil;

public class ProductRegistration {
	
	public static void main(String[] args) {
		registerProduct();
		EntityManager em = JPAUtil.getEntityManager();
		ProductDAO productDAO = new ProductDAO(em);
		
		Product p = productDAO.searchById(1L);
		System.out.println(p.getPrice());
		
		List<Product> allProducts = productDAO.searchByCategoryName("CELULARES");
		allProducts.forEach(p2 -> System.out.println(p.getName()));
	
		BigDecimal productPrice = productDAO.searchProductPriceWithName("Xiaomi Redmi");
		System.out.println("Preço do Produto: " + productPrice);
	}

	private static void registerProduct() {
		Category cellphones = new Category("CELULARES");
		Product cellphone = new Product("Xiaomi Redmi", "Muito legal", new BigDecimal("800"), cellphones );
		
		EntityManager em = JPAUtil.getEntityManager();
		ProductDAO productDAO = new ProductDAO(em);
		CategoryDAO categoryDAO = new CategoryDAO(em);
		
		em.getTransaction().begin();
		
		categoryDAO.register(cellphones);
		productDAO.register(cellphone);
		
		em.getTransaction().commit();
		em.close();
	}

}
