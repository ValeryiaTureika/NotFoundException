package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.TShirt;
import ru.netology.repository.NotFoundException;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProductManagerTest {

    private ProductRepository repo = new ProductRepository();

    private final Product first = new Product(1, "scarf", 250);
    private final Book second = new Book(2, "Магия утра. Как первый час дня определяет ваш успех", 500, "Хэл Элрод");
    private final TShirt third = new TShirt(3, "PUMA", 5500, "white");

    @BeforeEach
    void setUp() {
        repo.save(first);
        repo.save(second);
        repo.save(third);
    }

    @Test
    void shouldDeletingExistingElement() {
        Product[] expected = {first, second};
        repo.removeById(3);

        assertArrayEquals(expected, repo.findAll());
    }

    @Test
    void shouldDeletingNonExistingElement() {
        Product[] expected = {first, second, third};

        assertThrows(NotFoundException.class, () -> repo.removeById(8));
    }
}