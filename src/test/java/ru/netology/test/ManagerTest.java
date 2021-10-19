package ru.netology.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.manager.Manager;
import ru.netology.repository.Repository;

import static org.junit.jupiter.api.Assertions.*;

class ManagerTest {
    Repository repository = new Repository();
    Manager manager = new Manager(repository);
    Book book1 = new Book(1, "Преступление и наказание", 300, "Достоевский");
    Book book3 = new Book(3, "Идиот", 200, "Достоевский");
    Book book2 = new Book(2, "Белая Гвардия", 200, "Булгаков");
    Product smartphone1 = new Smartphone(1, "Huawei", 20000, "H");
    Product smartphone2 = new Smartphone(2, "Samsung", 35000, "S");

    @BeforeEach
    public void setUp() {
        manager.add(book1);
        manager.add(book2);
        manager.add(book3);
        manager.add(smartphone1);
        manager.add(smartphone2);
        manager.searchBy("Отцы и дети");
        manager.searchBy("X");

    }

    @Test
    void shouldSearchBookByAuthor() {
        Product[] actual = manager.searchBy("Булгаков");
        Product[] expected = {book2};
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchBookByName() {
        Product[] actual = manager.searchBy("Преступление и наказание");
        Product[] expected = {book1};
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchSmartphoneByName() {
        Product[] actual = manager.searchBy("Huawei");
        Product[] expected = {smartphone1};
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchSmartphoneByManufacturer() {
        Product[] actual = manager.searchBy("S");
        Product[] expected = {smartphone2};
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldFailNoBookFound() {
        Product[] actual = manager.searchBy("Отцы и дети");
        Product[] expected = new Product[]{};
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldFailNoManufacturerFound() {
        Product[] actual = manager.searchBy("X");
        Product[] expected = new Product[]{};
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldFindBooksByAuthor() {
        Product[] actual = manager.searchBy("Достоевский");
        Product[] expected = {book1, book3};
        assertArrayEquals(expected, actual);
    }
}

