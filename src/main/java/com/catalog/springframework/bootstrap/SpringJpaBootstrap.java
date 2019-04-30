package com.catalog.springframework.bootstrap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.catalog.springframework.domain.Item;
import com.catalog.springframework.repositories.ItemRepository;

@Component
public class SpringJpaBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private ItemRepository itemRepository;


    private Logger log = LogManager.getLogger(SpringJpaBootstrap.class);

    @Autowired
    public void setItemRepository(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }
    
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        loadItems();
    }

    private void loadItems() {
        Item laptop = new Item();
        laptop.setName("Laptop");
        laptop.setAmount(50);
        laptop.setInventoryCode("0123456789");
        itemRepository.save(laptop);

        log.info("Laptop Saved- id: " + laptop.getId());

        Item router = new Item();
        router.setName("Router");
        router.setAmount(28);
        router.setInventoryCode("9856214785");
        itemRepository.save(router);

        log.info("Router Saved- id: " + router.getId());
        
        Item tv = new Item();
        tv.setName("50-inch TV");
        tv.setAmount(7);
        tv.setInventoryCode("4758698726");
        itemRepository.save(tv);

        log.info("50-inh TV Saved- id: " + tv.getId());
        
        
    }

}