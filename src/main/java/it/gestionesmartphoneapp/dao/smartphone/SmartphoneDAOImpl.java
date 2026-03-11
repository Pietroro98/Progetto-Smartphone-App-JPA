package it.gestionesmartphoneapp.dao.smartphone;

import it.gestionesmartphoneapp.model.Smartphone;

import javax.persistence.EntityManager;
import java.util.List;

public class SmartphoneDAOImpl implements SmartphoneDAO {
    private EntityManager entityManager;

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Smartphone> findAll() throws Exception {
        return entityManager.createQuery("from Smartphone", Smartphone.class).getResultList();
    }

    @Override
    public Smartphone findById(Long id) throws Exception {
        return entityManager.find(Smartphone.class, id);
    }

    @Override
    public void update(Smartphone smartphoneInstance) throws Exception {
        if(smartphoneInstance == null) {
            throw new Exception("Smartphone instance is null");
        }
        smartphoneInstance = entityManager.merge(smartphoneInstance);
    }

    @Override
    public void insert(Smartphone smartphoneInstance) throws Exception {
        if(smartphoneInstance == null) {
            throw new Exception("Smartphone instance is null");
        }
        entityManager.persist(smartphoneInstance);
    }

    @Override
    public void delete(Long id) throws Exception {
        if (id == null) {
            throw new Exception("Problema valore in input");
        }
        entityManager.createQuery("delete from Smartphone where id=?1").setParameter(1, id).executeUpdate();
    }
}
