package br.com.alura.loja.test;

import br.com.alura.loja.dao.CategoryDAO;
import br.com.alura.loja.dao.CustomerDAO;
import br.com.alura.loja.dao.OrderDAO;
import br.com.alura.loja.dao.ProductDAO;
import br.com.alura.loja.model.*;
import br.com.alura.loja.util.JPAUtil;
import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class PerformanceQueries {

    public static void main(String[] args) {
        fillDataBase();

        EntityManager em = JPAUtil.getEntityManager();
        OrderDAO orderDAO = new OrderDAO(em);
        Order order = orderDAO.searchOrderWithClient(1L);
        em.close();
        System.out.println(order.getCustomer().getName());
    }

    private static void fillDataBase() {
        Category cellphones = new Category("CELULARES");
        Category videogames = new Category("VIDEOGAMES");
        Category technology = new Category("INFORMATICA");

        Product cellphone = new Product("Xiaomi Redmi", "Muito legal", new BigDecimal("800"), cellphones);
        Product videogame = new Product("PS5", "Playstation 5", new BigDecimal("1000"), videogames);
        Product macbook = new Product("Macbook", "Macbook pro", new BigDecimal("1000"), technology);

        Customer customer = new Customer("Rodrigo", "123456");

        EntityManager em = JPAUtil.getEntityManager();
        ProductDAO productDAO = new ProductDAO(em);
        CategoryDAO categoryDAO = new CategoryDAO(em);
        CustomerDAO customerDAO = new CustomerDAO(em);

        em.getTransaction().begin();

        categoryDAO.register(cellphones);
        categoryDAO.register(videogames);
        categoryDAO.register(technology);
        productDAO.register(cellphone);
        productDAO.register(videogame);
        productDAO.register(macbook);
        customerDAO.register(customer);

        em.getTransaction().commit();
        em.close();
    }
}
