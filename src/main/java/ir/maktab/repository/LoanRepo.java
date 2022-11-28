package ir.maktab.repository;

import ir.maktab.data.entity.loans.Loan;

import javax.persistence.EntityManager;
import java.util.List;

public class LoanRepo {
    private static final LoanRepo loanRepo = new LoanRepo();

    private LoanRepo() {
    }

    public static LoanRepo getInstance() {
        return loanRepo;
    }

    public void save(Loan loan) {
        EntityManager em = EntityManagerFactoryProducer.emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(loan);
        em.getTransaction().commit();
        em.close();
    }

    public void update(Loan loan) {
        EntityManager em = EntityManagerFactoryProducer.emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(loan);
        em.getTransaction().commit();
        em.close();
    }

    public void delete(Loan loan) {
        EntityManager em = EntityManagerFactoryProducer.emf.createEntityManager();
        em.getTransaction().begin();
        Loan deleteLoan = em.find(Loan.class, loan.getId());
        em.remove(deleteLoan);
        em.getTransaction().commit();
        em.close();
    }

    public List<Loan> getAll() {
        EntityManager em = EntityManagerFactoryProducer.emf.createEntityManager();
        em.getTransaction().begin();
        List<Loan> loanList = em.createQuery("FROM Loan ").getResultList();
        em.getTransaction().commit();
        em.close();
        return loanList;
    }

    public Loan getById(int id) {
        EntityManager em = EntityManagerFactoryProducer.emf.createEntityManager();
        em.getTransaction().begin();
        Loan loan = em.find(Loan.class, id);
        em.getTransaction().commit();
        em.close();
        return loan;
    }
}
