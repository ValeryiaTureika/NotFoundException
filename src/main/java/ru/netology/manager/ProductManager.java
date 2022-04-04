package ru.netology.manager;

import ru.netology.domain.Product;
import ru.netology.repository.ProductRepository;
import ru.netology.domain.Book;
import ru.netology.domain.TShirt;

public class ProductManager {
    private ProductRepository repo;

    public ProductManager(ProductRepository repository) {
        this.repo = repository;
    }

    public void add(Product item) {
        repo.save(item);
    }

    public void removeById(int id) {
        repo.removeById(id);
    }


    public Product[] searchBy(String text) {
        Product[] result = new Product[0];
        Product[] products = repo.findAll();
        for (Product product : products) {
            if (matches(product, text)) {
                Product[] tmp = new Product[result.length + 1];
                System.arraycopy(result, 0, tmp, 0, result.length);
                tmp[tmp.length - 1] = product;
                result = tmp;
            }
        }
        return result;
    }

    public boolean matches(Product product, String search) {
        if (product.getName().contains(search)) {
            return true;
        }
        if (product instanceof Book) {
            Book book = (Book) product;
            if (book.getAuthor().contains(search)) {
                return true;
            }
        }
        if (product instanceof TShirt) {
            TShirt tShirt = (TShirt) product;
            if (tShirt.getColor().contains(search)) {
                return true;
            }
        }
        return false;
    }
}
