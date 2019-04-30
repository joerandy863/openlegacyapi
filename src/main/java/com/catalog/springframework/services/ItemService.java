package com.catalog.springframework.services;


import com.catalog.springframework.domain.Item;;

public interface ItemService {
    Iterable<Item> listAllItems();

    Item getItemById(Integer id);

    Item addItem(Item item);

    void deleteItem(Integer id);
}
