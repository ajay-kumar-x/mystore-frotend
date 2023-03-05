package org.mystore.repository;

import org.mystore.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findByCategoryOrderByIdDesc(String category);
    List<Product> findByCategoryAndSubcategoryOrderByIdDesc(String category, String subcategory);

}
