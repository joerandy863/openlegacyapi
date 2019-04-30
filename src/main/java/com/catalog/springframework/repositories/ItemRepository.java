package com.catalog.springframework.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.catalog.springframework.domain.Item;

@RepositoryRestResource
public interface ItemRepository extends CrudRepository<Item, Integer>{
}
