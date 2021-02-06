package facades;

import dtos.BankCustomerDTO;
import entities.BankCustomer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import utils.EMF_Creator;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class BankCustomerFacade {

    private static BankCustomerFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private BankCustomerFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static BankCustomerFacade getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new BankCustomerFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public BankCustomerDTO get(long id) {
        return new BankCustomerDTO(emf.createEntityManager().find(BankCustomer.class, id));
    }
    
    public List<BankCustomerDTO> getByLastname(String lastname) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<BankCustomer> q = em.createQuery("SELECT c FROM BankCustomer c WHERE c.lastName = :lastname", BankCustomer.class);
        q.setParameter("lastname", lastname);
        
        return BankCustomerDTO.ListToDTOs(q.getResultList());
    }
    
    public BankCustomer add(BankCustomer customer) {
        EntityManager em = emf.createEntityManager();
       em.getTransaction().begin();
       em.persist(customer);
       em.getTransaction().commit();
       
       return customer;
    }
    
    public List<BankCustomer> getAll() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<BankCustomer> q = em.createQuery("SELECT c FROM BankCustomer c", BankCustomer.class);
        
        return q.getResultList();
    }


}
