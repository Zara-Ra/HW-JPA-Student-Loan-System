package ir.maktab.repository;

import ir.maktab.data.entity.Payment;
import ir.maktab.data.entity.Repayment;
import ir.maktab.data.entity.loans.Loan;
import ir.maktab.data.entity.user.Student;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class RepaymentRepo {
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
        Repayment deletePayment = em.find(Repayment.class,repayment.getId());
        em.remove(deletePayment);
        em.getTransaction().commit();
        em.close();
    }

    public List<Repayment> getAll() {
        EntityManager em = EntityManagerFactoryProducer.emf.createEntityManager();
        em.getTransaction().begin();
        List<Repayment> repaymentList = em.createQuery("FROM Repayment ").getResultList();
        em.getTransaction().commit();
        em.close();
        return repaymentList;
    }

    public Repayment getById(int id) {
        EntityManager em = EntityManagerFactoryProducer.emf.createEntityManager();
        em.getTransaction().begin();
        Repayment repayment = em.find(Repayment.class, id);
        em.getTransaction().commit();
        em.close();
        return repayment;
    }

    public Repayment getByNumber(int num){
        EntityManager em = EntityManagerFactoryProducer.emf.createEntityManager();
        em.getTransaction().begin();
        Repayment repayment = (Repayment) em.createQuery("FROM Repayment r WHERE r.repaymentNum=:num").getSingleResult();
        em.getTransaction().commit();
        em.close();
        return repayment;
    }
}
