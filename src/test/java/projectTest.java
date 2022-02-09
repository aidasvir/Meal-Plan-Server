//import static org.junit.Assert.assertFalse;
import org.junit.jupiter.api.Test;
import vcs.lt.project.Database;
import vcs.lt.project.HibernateDatabaseImpl;
import vcs.lt.project.PlanFormation;
import vcs.lt.project.Product;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Test.*;
class projectTest {

    PlanFormation planFormation = new PlanFormation();
    Database database = new HibernateDatabaseImpl();

    @Test
    void testFindProduct(){
        String productName = "salmon";
        Product productFull = database.getProductByName(productName);
        String productDb = productFull.getMeal();
        assertEquals(productName, productDb);
    }

    @Test
    void testAddProductToMeal(){


    }

}
