package br.com.alura.loja.test;

import br.com.alura.loja.dao.CategoryDAO;
import br.com.alura.loja.dao.ProductDAO;
import br.com.alura.loja.model.Category;
import br.com.alura.loja.model.Product;
import br.com.alura.loja.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;

public class CriteriaTest {

    public static void main(String[] args) {
        fillDataBase();
        EntityManager em = JPAUtil.getEntityManager();
        ProductDAO productDAO = new ProductDAO(em);

        productDAO.searchByParameters(null, null, LocalDate.now());
    }
    private static void fillDataBase() {
        Category cellphones = new Category("CELULARES");
        Category videogames = new Category("VIDEOGAMES");
        Category technology = new Category("INFORMATICA");

        Product cellphone = new Product("Xiaomi Redmi", "Muito legal", new BigDecimal("800"), cellphones);
        Product videogame = new Product("PS5", "Playstation 5", new BigDecimal("1000"), videogames);
        Product macbook = new Product("Macbook", "Macbook pro", new BigDecimal("1000"), technology);


        EntityManager em = JPAUtil.getEntityManager();
        ProductDAO productDAO = new ProductDAO(em);
        CategoryDAO categoryDAO = new CategoryDAO(em);

        em.getTransaction().begin();

        categoryDAO.register(cellphones);
        categoryDAO.register(videogames);
        categoryDAO.register(technology);
        productDAO.register(cellphone);
        productDAO.register(videogame);
        productDAO.register(macbook);

        em.getTransaction().commit();
        em.close();
    }

}
