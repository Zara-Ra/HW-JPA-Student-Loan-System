package ir.maktab.repository.impl;

import ir.maktab.data.entity.user.Student;
import ir.maktab.repository.EntityManagerFactoryProducer;
import ir.maktab.repository.IRepository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

public class StudentRepo implements IRepository<Student> {
    private static final StudentRepo studentRepo = new StudentRepo();

    private StudentRepo() {
    }

    public static StudentRepo getInstance() {
        return studentRepo;
    }

    public void save(Student student) {
        try {
            EntityManager em = EntityManagerFactoryProducer.emf.createEntityManager();
            em.getTransaction().begin();
            em.persist(student);
            em.getTransaction().commit();
            em.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());//throw exception instead of printing an error here!
        }
    }

    public void update(Student student) {
        EntityManager em = EntityManagerFactoryProducer.emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(student);
        em.getTransaction().commit();
        em.close();
    }

    public void delete(Student student) {
        EntityManager em = EntityManagerFactoryProducer.emf.createEntityManager();
        em.getTransaction().begin();
        Student deleteStudent = em.find(Student.class, student.getId());
        em.remove(deleteStudent);
        em.getTransaction().commit();
        em.close();
    }

    public List<Student> getAll() {
        EntityManager em = EntityManagerFactoryProducer.emf.createEntityManager();
        em.getTransaction().begin();
        List<Student> studentList = em.createNamedQuery("getAllStudents").getResultList();
        em.getTransaction().commit();
        em.close();
        return studentList;
    }

    public Optional<Student> getByUserNameAndPassword(String username, String password) {
        Student person;
        try {
            EntityManager em = EntityManagerFactoryProducer.emf.createEntityManager();
            em.getTransaction().begin();
            Query query = em.createQuery("from Student s where s.accountInfo.username =:username " +
                    "and s.accountInfo.password =:password");
            query.setParameter("username", username);
            query.setParameter("password", password);
            person = (Student) query.getSingleResult();
            em.getTransaction().commit();
            em.close();
        } catch (NoResultException e) {
            person = null;
        }
        return Optional.ofNullable(person);
    }
}
