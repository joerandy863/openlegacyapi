package guru.springframework.services;


import guru.springframework.domain.Item;;

public interface ItemService {
    Iterable<Item> listAllItems();

    Item getItemByItemNo(Integer id);

    Item addItem(Item item);

    void deleteItem(Integer id);
}
