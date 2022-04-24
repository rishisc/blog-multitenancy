package se.callista.blog.service.services;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.database.rider.core.api.dataset.DataSet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import se.callista.blog.service.annotation.SpringBootDbIntegrationTest;
import se.callista.blog.service.model.ProductValue;
import se.callista.blog.service.multi_tenancy.util.TenantContext;
import se.callista.blog.service.persistence.PostgresqlTestContainer;

@Testcontainers
@SpringBootDbIntegrationTest
public class ProductServiceTest {

    @Container
    private static final PostgresqlTestContainer POSTGRESQL_CONTAINER = PostgresqlTestContainer.getInstance();

    @Autowired
    private ProductService productService;

    @Test
    @DataSet(value = {"products.yml"})
    public void getProductForTenant1() {

        ProductValue product = TenantContext.withTenantId("tenant1",
            productService.getProduct(1))
            .block();
        assertThat(product.getName()).isEqualTo("Product 1");

    }

    @Test
    @DataSet(value = {"products.yml"})
    public void getProductForTenant2() {

        ProductValue product = TenantContext.withTenantId("tenant2",
                productService.getProduct(1))
            .block();
        assertThat(product).isNull();

    }

}
