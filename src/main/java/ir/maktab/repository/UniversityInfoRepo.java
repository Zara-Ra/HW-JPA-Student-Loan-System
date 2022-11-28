package ir.maktab.repository;

import ir.maktab.data.entity.user.Student;
import ir.maktab.data.entity.user.UniversityInfo;

import javax.persistence.EntityManager;

public class UniversityInfoRepo {
    private static final UniversityInfoRepo unversityInfoRepo = new UniversityInfoRepo();

    private UniversityInfoRepo() {
    }

    public static UniversityInfoRepo getInstance() {
        return unversityInfoRepo;
    }

    public void save(UniversityInfo universityInfo) {
        EntityManager em = EntityManagerFactoryProducer.emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(universityInfo);
        em.getTransaction().commit();
        em.close();
    }

    public void update(UniversityInfo universityInfo) {
        EntityManager em = EntityManagerFactoryProducer.emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(universityInfo);
        em.getTransaction().commit();
        em.close();
    }

    public void delete(UniversityInfo universityInfo) {
        EntityManager em = EntityManagerFactoryProducer.emf.createEntityManager();
        em.getTransaction().begin();
        Student deleteStudent = em.find(Student.class, universityInfo.getId());
        em.remove(deleteStudent);
        em.getTransaction().commit();
        em.close();
    }
}
