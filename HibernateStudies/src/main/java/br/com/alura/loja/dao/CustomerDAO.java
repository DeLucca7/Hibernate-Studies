package br.com.alura.loja.dao;

import br.com.alura.loja.model.Customer;

import javax.persistence.EntityManager;

public class CustomerDAO {

    private final EntityManager em;

    public CustomerDAO(EntityManager em){
        this.em = em;
    }

    public void register(Customer customer){
        this.em.persist(customer);
    }

    public Customer searchById(Long id) {
        return em.find(Customer.class, id);
    }
}
