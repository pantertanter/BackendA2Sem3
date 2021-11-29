package facades;

import dtos.LibraryItemDTO;
import entities.LibraryItem;
import entities.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import static org.junit.jupiter.api.Assertions.*;

class UserFacadeTest {

    private static EntityManagerFactory emf;
    private static UserFacade facade;

    private static User u1;

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = UserFacade.getUserFacade(emf);
    }

    @BeforeEach
    void setUp() {
        EntityManager em = emf.createEntityManager();

        u1 = new User("test-man", "pwd");

        try {
            em.getTransaction().begin();
            em.persist(u1);
            em.getTransaction().commit();
        }
        finally {
            em.close();
        }
    }

    @AfterEach
    void tearDown() {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.createNativeQuery("DROP TABLE library_item");
            em.createNativeQuery("DROP TABLE user_roles");
            em.createNativeQuery("DROP TABLE users");
            em.createNativeQuery("DROP TABLE roles");
            em.getTransaction().commit();
        }
        finally {
            em.close();
        }
    }

    @Test
    void addBook() {
        LibraryItemDTO item = new LibraryItemDTO("OL679360W");
        facade.addBook(u1.getUserName(), item);

        EntityManager em = emf.createEntityManager();
        User user = em.find(User.class, u1.getUserName());
        assertEquals(item.getId(), user.getLibraryItems().get(0).getId());
        em.close();
    }
}