package ir.maktab.repository;

import ir.maktab.data.entity.CreditCard;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class CreditCardRepo {
    private static final CreditCardRepo creditCardRepo = new CreditCardRepo();

    private CreditCardRepo() {
    }

    public static CreditCardRepo getInstance() {
        return creditCardRepo;
    }

    public void save(CreditCard creditCard) {
        EntityManager em = EntityManagerFactoryProducer.emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(creditCard);
        em.getTransaction().commit();
        em.close();
    }

    public void update(CreditCard creditCard) {
        EntityManager em = EntityManagerFactoryProducer.emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(creditCard);
        em.getTransaction().commit();
        em.close();
    }

    public void delete(CreditCard creditCard) {
        EntityManager em = EntityManagerFactoryProducer.emf.createEntityManager();
        em.getTransaction().begin();
        CreditCard deleteCard = em.find(CreditCard.class, creditCard.getId());
        em.remove(deleteCard);
        em.getTransaction().commit();
        em.close();
    }

    public CreditCard getByCardNumber(String cardNumber) {
        EntityManager em = EntityManagerFactoryProducer.emf.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("FROM CreditCard c WHERE c.cardNum=:cardNumber");
        query.setParameter("cardNumber",cardNumber);
        CreditCard creditCard = (CreditCard) query.getSingleResult();
        em.getTransaction().commit();
        em.close();
        return creditCard;
    }
}
