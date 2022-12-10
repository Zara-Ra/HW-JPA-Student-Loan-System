package ir.maktab.repository.impl;

import ir.maktab.data.entity.Payment;
import ir.maktab.data.entity.Repayment;
import ir.maktab.repository.EntityManagerFactoryProducer;
import ir.maktab.repository.IRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class RepaymentRepo implements IRepository<Repayment> {
    private static final RepaymentRepo repaymentRepo = new RepaymentRepo();

    private RepaymentRepo() {
    }

    public static RepaymentRepo getInstance() {
        return repaymentRepo;
    }

    public void save(Repayment repayment) {
        EntityManager em = EntityManagerFactoryProducer.emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(repayment);
        em.getTransaction().commit();
        em.close();
    }

    public void update(Repayment repayment) {
        EntityManager em = EntityManagerFactoryProducer.emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(repayment);
        em.getTransaction().commit();
        em.close();
    }

    public void delete(Repayment repayment) {
        EntityManager em = EntityManagerFactoryProducer.emf.createEntityManager();
        em.getTransaction().begin();
        Repayment deletePayment = em.find(Repayment.class, repayment.getId());
        em.remove(deletePayment);
        em.getTransaction().commit();
        em.close();
    }

    public List<Repayment> getAll(){
        EntityManager em = EntityManagerFactoryProducer.emf.createEntityManager();
        em.getTransaction().begin();
        List<Repayment> repaymentList = em.createQuery("FROM Repayment ").getResultList();
        em.getTransaction().commit();
        em.close();
        return repaymentList;
    }
    public List<Repayment> getAll(Payment payment) {
        EntityManager em = EntityManagerFactoryProducer.emf.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("FROM Repayment r WHERE r.payment=:payment");
        query.setParameter("payment", payment);
        List<Repayment> repaymentList = query.getResultList();
        em.getTransaction().commit();
        em.close();
        return repaymentList;
    }

    public Repayment getByNumber(int num) {
        EntityManager em = EntityManagerFactoryProducer.emf.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("FROM Repayment r WHERE r.repaymentNum=:num");
        query.setParameter("num",num);
        Repayment repayment = (Repayment) query.getSingleResult();
        em.getTransaction().commit();
        em.close();
        return repayment;
    }
}
