package it.gestionesmartphoneapp.dao.app;

import it.gestionesmartphoneapp.model.App;

import javax.persistence.EntityManager;
import java.util.List;

public class AppDAOImpl implements AppDAO
{
    private EntityManager entityManager;

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<App> findAll() throws Exception {
        return entityManager.createQuery("from App", App.class).getResultList();
    }

    @Override
    public App findById(Long id) throws Exception {
        return entityManager.find(App.class, id);
    }

    @Override
    public void update(App appInstance) throws Exception {
        if(appInstance == null) {
            throw new Exception("App instance is null");
        }
        appInstance = entityManager.merge(appInstance);
    }

    @Override
    public void insert(App appInstance) throws Exception {
        if(appInstance == null) {
            throw new Exception("App instance is null");
        }
        entityManager.persist(appInstance);
    }

    @Override
    public void delete(Long id) throws Exception {
        if (id == null)
        {
            throw new Exception("Problema valore in input");
        }
        entityManager.createQuery("delete from App where id=?1").setParameter(1, id).executeUpdate();

    }
}
