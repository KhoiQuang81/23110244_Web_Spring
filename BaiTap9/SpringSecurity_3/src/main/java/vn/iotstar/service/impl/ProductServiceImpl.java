package vn.iotstar.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.iotstar.entity.Product;
import vn.iotstar.repository.ProductRepository;
import vn.iotstar.service.ProductService;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository repository;

    public ProductServiceImpl(ProductRepository repo) {
        this.repository = repo;
    }

    @Override
    public Product save(Product product) {
        return repository.save(product);
    }

    @Override
    public List<Product> listAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Product get(Long id) {
        return repository.findById(id).get();
    }
}