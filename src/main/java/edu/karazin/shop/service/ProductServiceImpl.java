package edu.karazin.shop.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import edu.karazin.shop.model.Product;

@Service
public class ProductServiceImpl implements ProductService {

	// TODO: Move data to Data Base
	@SuppressWarnings("unchecked")
	private static final List<Product> PRODUCTS = new ArrayList() {{
			add(new Product(1L, "apple", ""));
			add(new Product(2L, "apricot", ""));
			add(new Product(3L, "banana", ""));
			add(new Product(4L, "grape", ""));
		    add(new Product(5L, "cherry", ""));
	}};

	@Override
	public Product getProduct(Long id) {
		return findById(id);
	}

	@Override
	public List<Product> searchProducts(String searchText) {
		if (searchText == null || searchText.trim().isEmpty()) {
			return PRODUCTS;
		}

		List<Product> results = new ArrayList<>();
		for (Product p : PRODUCTS) {
			if (containsWithNullCheck(p.getTitle(), searchText) || containsWithNullCheck(p.getDescription(), searchText)) {
				results.add(p);
			}
		}
		return results;
	}

	@Override
	@Transactional
	public Long addProduct(Product prod) {
		// auto increment emulation
		long maxId = 0L;
		for (Product p : PRODUCTS) {
			if (p.getId() > maxId) {
				maxId = p.getId();
			}
		}

		// insert to DB emulation
		prod.setId(++maxId);
		PRODUCTS.add(prod);
		return prod.getId();
	}

	@Override
	@Transactional
	public void updateProduct(Product prod) {
		removeProduct(prod.getId());
		PRODUCTS.add(prod);
	}

	@Override
	@Transactional
	public void removeProduct(Long id) {
		Product p = findById(id);
		if (p != null) {
			PRODUCTS.remove(p);
		}
	}

	private boolean containsWithNullCheck(String source, String searchText) {
		if (source == null || source.trim().isEmpty()) {
			return false;
		}
		return source.toLowerCase().contains(searchText.toLowerCase());
	}

	private Product findById(Long id) {
		for (Product p : PRODUCTS) {
			if (p.getId() == id) {
				return p;
			}
		}
		return null;
	}
}
