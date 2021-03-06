package com.capg.msc.MyShoppingCart.service;

import java.util.*;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.msc.MyShoppingCart.bean.Product;
import com.capg.msc.MyShoppingCart.dao.ProductRepository;
import com.capg.msc.MyShoppingCart.exception.InvalidCategoryException;
import com.capg.msc.MyShoppingCart.exception.InvalidCostRangeException;

@Service
public class ProductServiceImpl implements ProductService {
	
	List<Product> list = new ArrayList<>();

	@Autowired
	ProductRepository repo;
	
	@Override
	public List<Product> getAllProducts(){
		return repo.findAll();
	}
	
	@Override
	@Transactional
	public Product insertProduct(Product p) {
		repo.save(p);
		return p;
	}
	
	@Override
	public Product getProductById(int id)  {
		return repo.findById(id).get();
		
	}

	@Override
	public List<Product> updateProductById(Product p) {
		Optional<Product> op = repo.findById(p.getProductId());
		if(op.isPresent()) {
			op.get().setProductName(p.getProductName());
			op.get().setProductCost(p.getProductCost());
			op.get().setStartRating(p.getStartRating());
			op.get().setCategory(p.getCategory());
			repo.save(op.get());
		}
		return repo.findAll();
	}

	@Override
	@Transactional
	public List<Product> deleteById(Product p) {
		Optional<Product> op = repo.findById(p.getProductId());
		if(op.isPresent()) {
			repo.delete(p);
		}
		return repo.findAll();
	}
	
	@Override
	public List<Product> getAllProductsByCategory(String category) {
		
		try {
			return repo.getProductByCategory(category);
		} catch (InvalidCategoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Product> getAllProductsByCategoryAndPrice(String category, int range1, int range2){
		
		try {
			return repo.getProductByCategoryAndPrice(category, range1, range2);
		} catch (InvalidCostRangeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}
