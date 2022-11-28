package ir.maktab.repository;

import ir.maktab.data.entity.user.Student;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

public class StudentRepo {
    private static final StudentRepo studentRepo = new StudentRepo();

    private StudentRepo() {
    }

    public static StudentRepo getInstance() {
        return studentRepo;
    }

    public void save(Student student) {
        EntityManager em = EntityManagerFactoryProducer.emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(student);
        em.getTransaction().commit();
        em.close();
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
        List<Student> studentList = em.createQuery("FROM Student ").getResultList();
        em.getTransaction().commit();
        em.close();
        return studentList;
    }

    public Student getById(int id) {
        EntityManager em = EntityManagerFactoryProducer.emf.createEntityManager();
        em.getTransaction().begin();
        Student person = em.find(Student.class, id);
        em.getTransaction().commit();
        em.close();
        return person;
    }

    public Optional<Student> getByUserNameAndPassword(String username, String password) {
        EntityManager em = EntityManagerFactoryProducer.emf.createEntityManager();
        em.getTransaction().begin();//TODo is this . . . correct???
        Query query = em.createQuery("from Student s where s.accountInfo.username =:username and s.accountInfo.password =:password");
        query.setParameter("username", username);
        query.setParameter("password", password);
        Student person = (Student) query.getSingleResult();
        em.getTransaction().commit();
        em.close();
        return Optional.ofNullable(person);
    }
}
