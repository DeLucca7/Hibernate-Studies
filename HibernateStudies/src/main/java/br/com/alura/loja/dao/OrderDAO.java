package br.com.alura.loja.dao;

import br.com.alura.loja.model.Order;
import br.com.alura.loja.vo.SalesReportVO;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class OrderDAO {

    private final EntityManager em;

    public OrderDAO(EntityManager em){
        this.em = em;
    }

    public void register(Order order){
        this.em.persist(order);
    }

    public BigDecimal totalAmountSold(){
        String jpql = "SELECT SUM(p.totalPrice) FROM Order p";
        return em.createQuery(jpql, BigDecimal.class)
                .getSingleResult();
    }

    public List<SalesReportVO> salesReport(){
        String jpql = "SELECT new br.com.alura.loja.vo.SalesReportVO("
                + "product.name, SUM(item.amount), MAX(order.date)) "
                + "FROM Order order "
                + "JOIN order.items item "
                + "JOIN item.product product "
                + "GROUP BY product.name "
                + "ORDER BY item.amount DESC";
        return em.createQuery(jpql, SalesReportVO.class)
                .getResultList();
    }

    public Order searchOrderWithClient(Long id){
        return em.createQuery("SELECT p FROM Order p JOIN FETCH p.customer WHERE p.id = :id", Order.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}
