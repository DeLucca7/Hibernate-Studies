package br.com.alura.loja.test;

import br.com.alura.loja.dao.CategoryDAO;
import br.com.alura.loja.dao.CustomerDAO;
import br.com.alura.loja.dao.OrderDAO;
import br.com.alura.loja.dao.ProductDAO;
import br.com.alura.loja.model.*;
import br.com.alura.loja.util.JPAUtil;
import br.com.alura.loja.vo.SalesReportVO;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class OrderRegistration {

    public static void main(String[] args) {
        fillDateBase();

        EntityManager em = JPAUtil.getEntityManager();
        ProductDAO productDAO = new ProductDAO(em);
        CustomerDAO customerDAO = new CustomerDAO(em);

        Product product = productDAO.searchById(1L);
        Product product2 = productDAO.searchById(2L);
        Product product3 = productDAO.searchById(3L);

        Customer customer = customerDAO.searchById(1L);

        em.getTransaction().begin();

        Order order = new Order(customer);
        order.adicionarItem(new OrderedItem(10, order, product));
        order.adicionarItem(new OrderedItem(40, order, product2));

        Order order2 = new Order(customer);
        order.adicionarItem(new OrderedItem(2, order, product3));

        OrderDAO orderDAO = new OrderDAO(em);
        orderDAO.register(order);
        orderDAO.register(order2);

        em.getTransaction().commit();

        BigDecimal amountSold = orderDAO.totalAmountSold();
        System.out.println("Valor total: " + amountSold);

        List<SalesReportVO> report = orderDAO.salesReport();
        report.forEach(System.out::println);
    }

    private static void fillDateBase() {
        Category cellphones = new Category("CELULARES");
        Category videogames = new Category("VIDEOGAMES");
        Category Technology = new Category("INFORMATICA");

        Product celphone = new Product("Xiaomi Redmi", "Muito legal", new BigDecimal("800"), cellphones);
        Product videogame = new Product("PS5", "Playstation 5", new BigDecimal("1000"), videogames);
        Product macbook = new Product("Macbook", "Macbook pro", new BigDecimal("1000"), Technology);

        Customer customer = new Customer("Rodrigo", "123456");

        EntityManager em = JPAUtil.getEntityManager();
        ProductDAO productDAO = new ProductDAO(em);
        CategoryDAO categoryDAO = new CategoryDAO(em);
        CustomerDAO customerDAO = new CustomerDAO(em);

        em.getTransaction().begin();

        categoryDAO.register(cellphones);
        categoryDAO.register(videogames);
        categoryDAO.register(Technology);
        productDAO.register(celphone);
        productDAO.register(videogame);
        productDAO.register(macbook);
        customerDAO.register(customer);

        em.getTransaction().commit();
        em.close();
    }
}
