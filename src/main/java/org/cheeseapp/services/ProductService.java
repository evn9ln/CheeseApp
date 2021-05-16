package org.cheeseapp.services;

import org.cheeseapp.domain.Product;
import org.cheeseapp.domain.ProductStatistic;
import org.cheeseapp.domain.Set;
import org.cheeseapp.repos.ProductRepo;
import org.cheeseapp.repos.SetRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service

public class ProductService {

    public static ArrayList<ProductStatistic> getProductsStatistic(ProductRepo productRepo, SetRepo setRepo) {
        Iterable<Product> allProducts = productRepo.findAll();
        ArrayList<ProductStatistic> allProductStatistic = new ArrayList<ProductStatistic>();
        for (Product product : allProducts) {
            int count = 0;
            Iterable<Set> allSetsWithProductName = setRepo.findAllByProductId(product);
            for (Set set : allSetsWithProductName) {
                count += set.getNumber();

            }
            ProductStatistic productStatistic = new ProductStatistic(product.getId(),
                    product.getName(), count, product.getPrice() * count);
            allProductStatistic.add(productStatistic);
        }
        return allProductStatistic;
    }

    public static Boolean addProduct(String name, Integer price, String description, String
            imgLink, ProductRepo productRepo) {
        if (price == null || name == "")
            return false;

        Product product = new Product(name, price, description, imgLink);
        productRepo.save(product);
        return true;
    }

    public static void changeProduct(Product product, ProductRepo productRepo) {
        Optional<Product> productFromDb = productRepo.findById(product.getId());
        if (!productFromDb.isEmpty()) {
            Product newProduct = productFromDb.get();
            newProduct.setProduct(product);
            productRepo.save(newProduct);
        }
    }
}
