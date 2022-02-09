package vcs.lt.project;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Root;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.json.JSONArray;

import javax.sound.midi.Soundbank;
import java.util.List;

public class HibernateDatabaseImpl implements Database {

    private SessionFactory sessionFactory;
    User user;
    public HibernateDatabaseImpl() {
        Configuration configuration = new Configuration();
        configuration.setProperty("hibernate.connection.username", "root");
        configuration.setProperty("hibernate.connection.password", "");
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        configuration.setProperty("hibernate.hbm2ddl.auto", "update");
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/meal_plan_db");
        configuration.addAnnotatedClass(Product.class);
        configuration.addAnnotatedClass(User.class);
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }


    @Override
    public void insertProduct(String type, String meal, double calories, double carbs, double protein, double fats, double fiber) {
        EntityManager manager = sessionFactory.createEntityManager();
        manager.getTransaction().begin();
        manager.persist(new Product(type, meal, calories, carbs, protein, fats, fiber)); // metodas padeda sukurti nauja objekta ir paims duomenis ir isaugos duombazeje
        manager.getTransaction().commit(); // uztikrina kad arba visos uzklausos ivykdomos arba nei viena
        manager.close();
    }


    @Override
    public List<Product> getProducts() {
        EntityManager manager = sessionFactory.createEntityManager();
        manager.getTransaction().begin();
        List<Product> product = manager.createQuery("from Product", Product.class).getResultList();

        manager.getTransaction().commit();
        manager.close();
        return product;
    }

    @Override
    public List<Product> getProductsByType(String type) {
        EntityManager manager = sessionFactory.createEntityManager();
        manager.getTransaction().begin();

        TypedQuery<Product> query = manager.createQuery("SELECT u FROM Product u WHERE u.type LIKE :search", Product.class);
        query.setParameter("search", "%" + type + "%");
        List<Product> product = query.getResultList();

        manager.getTransaction().commit();
        manager.close();
        return product;
    }

    @Override
    public Product getProductByName(String meal) {
        EntityManager manager = sessionFactory.createEntityManager();
        manager.getTransaction().begin();

        TypedQuery<Product> query = manager.createQuery("SELECT u FROM Product u WHERE u.meal LIKE :search", Product.class);
        query.setParameter("search",  meal );
        Product product = query.getSingleResult();

        manager.getTransaction().commit();
        manager.close();
        return product;
    }

    @Override
    public User getUser(String username, String password) {
        EntityManager manager = sessionFactory.createEntityManager();
        manager.getTransaction().begin();

        TypedQuery<User> query = manager.createQuery("SELECT u FROM User u WHERE u.username=:username AND u.password=:password", User.class);
        query.setParameter("username", username);
        query.setParameter("password", password);

        try {
            user = query.getSingleResult();
        } catch (Exception e) {
            user = null;
        }

        manager.getTransaction().commit();
        manager.close();
        return user;
    }

    @Override
    public List<User> getUsers() {
        EntityManager manager = sessionFactory.createEntityManager();
        manager.getTransaction().begin();
        List<User> users = manager.createQuery("from User", User.class).getResultList();

        manager.getTransaction().commit();
        manager.close();
        return users;
    }

    @Override
    public String updatePlan(JSONArray array) {

        System.out.println(user.getUsername());

        EntityManager manager = sessionFactory.createEntityManager();
        manager.getTransaction().begin();

        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
        CriteriaUpdate<User> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(User.class);
        Root<User> userRoot = criteriaUpdate.from(User.class);
        criteriaUpdate.set(userRoot.get("plan"), array.toString());
        criteriaUpdate.where(criteriaBuilder.equal(userRoot.get("username"), user.getUsername()));
        manager.createQuery(criteriaUpdate).executeUpdate();

        manager.getTransaction().commit();
        manager.close();

        return "success";
    }

    @Override
    public String getUserPlan() {
        EntityManager manager = sessionFactory.createEntityManager();
        manager.getTransaction().begin();
        // uzklausa:
        TypedQuery<User> query = manager.createQuery("SELECT u FROM User u WHERE u.username LIKE :search", User.class);


        query.setParameter("search",  user.getUsername());
        User user = query.getSingleResult();
        String plan = user.getPlan();

        manager.getTransaction().commit();
        manager.close();
        return plan;
    }

    @Override
    public void createUser(String username, String password) {
        EntityManager manager = sessionFactory.createEntityManager();
        manager.getTransaction().begin();

        manager.persist(new User(username, password));
        manager.getTransaction().commit();

        manager.close();
    }

}
