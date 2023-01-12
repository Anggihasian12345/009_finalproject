/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pegawai.pegawaikantor;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import pegawai.pegawaikantor.exceptions.NonexistentEntityException;
import pegawai.pegawaikantor.exceptions.PreexistingEntityException;

/**
 *
 * @author Lenovo
 */
//nim 20200140009
//Anggi Hasian Nugraha Munthe
public class PegawaikantorJpaController implements Serializable {

    public PegawaikantorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("pegawai_pegawaikantor_jar_0.0.1-SNAPSHOTPU");
    
   
    

    PegawaikantorJpaController() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pegawaikantor pegawaikantor) throws PreexistingEntityException, Exception {
        if (pegawaikantor.getPegawaikantorPK() == null) {
            pegawaikantor.setPegawaikantorPK(new PegawaikantorPK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(pegawaikantor);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPegawaikantor(pegawaikantor.getPegawaikantorPK()) != null) {
                throw new PreexistingEntityException("Pegawaikantor " + pegawaikantor + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pegawaikantor pegawaikantor) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            pegawaikantor = em.merge(pegawaikantor);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                PegawaikantorPK id = pegawaikantor.getPegawaikantorPK();
                if (findPegawaikantor(id) == null) {
                    throw new NonexistentEntityException("The pegawaikantor with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(PegawaikantorPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pegawaikantor pegawaikantor;
            try {
                pegawaikantor = em.getReference(Pegawaikantor.class, id);
                pegawaikantor.getPegawaikantorPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pegawaikantor with id " + id + " no longer exists.", enfe);
            }
            em.remove(pegawaikantor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Pegawaikantor> findPegawaikantorEntities() {
        return findPegawaikantorEntities(true, -1, -1);
    }

    public List<Pegawaikantor> findPegawaikantorEntities(int maxResults, int firstResult) {
        return findPegawaikantorEntities(false, maxResults, firstResult);
    }

    private List<Pegawaikantor> findPegawaikantorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pegawaikantor.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Pegawaikantor findPegawaikantor(PegawaikantorPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pegawaikantor.class, id);
        } finally {
            em.close();
        }
    }

    public int getPegawaikantorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pegawaikantor> rt = cq.from(Pegawaikantor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
