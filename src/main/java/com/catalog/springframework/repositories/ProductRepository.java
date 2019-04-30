package com.catalog.springframework.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.catalog.springframework.domain.Product;

@RepositoryRestResource
public interface ProductRepository extends CrudRepository<Product, Integer>{
}
