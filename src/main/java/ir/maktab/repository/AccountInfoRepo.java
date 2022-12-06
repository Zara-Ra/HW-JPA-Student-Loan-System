package ir.maktab.repository;

import ir.maktab.data.entity.user.AccountInfo;
import ir.maktab.data.entity.user.Student;

import javax.persistence.EntityManager;

public class AccountInfoRepo {
    private static final AccountInfoRepo accountInfoRepo = new AccountInfoRepo();

    private AccountInfoRepo() {
    }

    public static AccountInfoRepo getInstance() {
        return accountInfoRepo;
    }

    public void save(AccountInfo accountInfo) {
        EntityManager em = EntityManagerFactoryProducer.emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(accountInfo);
        em.getTransaction().commit();
        em.close();
    }

    public void update(AccountInfo accountInfo) {
        EntityManager em = EntityManagerFactoryProducer.emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(accountInfo);
        em.getTransaction().commit();
        em.close();
    }

    public void delete(AccountInfo accountInfo) {
        EntityManager em = EntityManagerFactoryProducer.emf.createEntityManager();
        em.getTransaction().begin();
        Student deleteStudent = em.find(Student.class, accountInfo.getId());
        em.remove(deleteStudent);
        em.getTransaction().commit();
        em.close();
    }
}
