package ir.maktab.repository.impl;

import ir.maktab.data.entity.Payment;
import ir.maktab.data.entity.loans.Loan;
import ir.maktab.data.entity.user.Person;
import ir.maktab.data.entity.user.Student;
import ir.maktab.repository.EntityManagerFactoryProducer;
import ir.maktab.repository.IRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class PaymentRepo implements IRepository<Payment> {
    private static final PaymentRepo paymentRepo = new PaymentRepo();

    private PaymentRepo() {
    }

    public static PaymentRepo getInstance() {
        return paymentRepo;
    }

    public void save(Payment payment) {
        EntityManager em = EntityManagerFactoryProducer.emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(payment);
        em.getTransaction().commit();
        em.close();
    }

    public void update(Payment payment) {
        EntityManager em = EntityManagerFactoryProducer.emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(payment);
        em.getTransaction().commit();
        em.close();
    }

    public void delete(Payment payment) {
        EntityManager em = EntityManagerFactoryProducer.emf.createEntityManager();
        em.getTransaction().begin();
        Payment deletePayment = find(payment.getStudent(), payment.getLoan());
        em.remove(deletePayment);
        em.getTransaction().commit();
        em.close();
    }

    public List<Payment> getAll() {
        EntityManager em = EntityManagerFactoryProducer.emf.createEntityManager();
        em.getTransaction().begin();
        List<Payment> paymentList = em.createNamedQuery("getAllPayments").getResultList();
        em.getTransaction().commit();
        em.close();
        return paymentList;
    }

    public Payment find(Student student, Loan loan) {
        EntityManager em = EntityManagerFactoryProducer.emf.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("from Payment p where p.student=:student and p.loan=:loan");
        query.setParameter("student", student);
        query.setParameter("loan", loan);
        Payment singleResult = (Payment) query.getSingleResult();
        em.getTransaction().commit();
        em.close();
        return singleResult;
    }

    public List<Payment> findByNationalNumber(Person person) {
        EntityManager em = EntityManagerFactoryProducer.emf.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("FROM Payment p where p.student.name=:name and p.student.familyName=:family " +
                "and p.student.nationalNum=:nationalNum");
        query.setParameter("name", person.getName());
        query.setParameter("family", person.getFamilyName());
        query.setParameter("nationalNum", person.getNationalNum());
        List<Payment> paymentList = query.getResultList();
        em.getTransaction().commit();
        em.close();
        return paymentList;
    }
}
