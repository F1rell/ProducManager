package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Product;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;

class ProductManagerTest {
    private ProductRepository repository = new ProductRepository();
    private ProductManager manager = new ProductManager(repository);

    private Product product1 = new Product(1, "book1", 100);
    private Product product2 = new Product(2, "book2", 200);
    private Product product3 = new Product(3, "book3", 300);
    private Product product4 = new Product(4, "book4", 400);
    private Product product5 = new Product(5, "book5", 500);

    @BeforeEach
    public void addProd() {
        manager.add(product1);
        manager.add(product2);
        manager.add(product3);
        manager.add(product4);
        manager.add(product5);
    }

    @Test
    void saveProduct() {

        Product[] expected = {product1, product2, product3, product4, product5};
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    void findById() {

        Product expected = product4;
        Product actual = repository.findById(4);
        assertEquals(expected, actual);
    }

    @Test
    void removeById() {
        repository.removeById(3);
        Product[] expected = {product1, product2, product4, product5};
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    void searchBy() {
        Product[] expected = {product3};
        Product[] actual = manager.searchBy("book3");
        assertArrayEquals(expected, actual);
    }

    @Test
    void searchByFiveProducts() {
        Product[] expected = {product1, product2, product3, product4, product5};
        Product[] actual = manager.searchBy("book");
        assertArrayEquals(expected, actual);
    }

    @Test
    void searchByNotCorrectProduct() {

        Product[] expected = new Product[]{};
        Product[] actual = manager.searchBy("Ни один товар не подходит");
        assertArrayEquals(expected, actual);
    }
}
