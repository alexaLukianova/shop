package edu.karazin.shop.dao;

import java.util.List;

import edu.karazin.shop.model.Product;

public interface ProductDao {

	Product findById(Long id);

	List<Product> search(String searchText);

	Product save(Product prod);

	void delete(Long id);
}
