package guru.springframework.services;

import guru.springframework.domain.Item;
import guru.springframework.repositories.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private ItemRepository itemRepository;

    @Autowired
    public void setItemRepository(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public Iterable<Item> listAllItems() {
        logger.debug("listAllItems called");
        return itemRepository.findAll();
    }

    @Override
    public Item getItemById(Integer id) {
        logger.debug("getItemById called");
        return itemRepository.findById(id).orElse(null);
    }

    @Override
    public Item addItem(Item product) {
        logger.debug("saveProduct called");
        return itemRepository.save(product);
    }

    @Override
    public void deleteItem(Integer id) {
        logger.debug("deleteProduct called");
        itemRepository.deleteById(id);
    }
}
