package com.catalog.springframework.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.catalog.springframework.domain.Item;
import com.catalog.springframework.services.ItemService;

@RestController
@RequestMapping("/item")
@Api(value="catalog")
public class ItemController {

    private ItemService itemService;

    @Autowired
    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
    }

    @ApiOperation(value = "View a list of available items",response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @RequestMapping(value = "/list", method= RequestMethod.GET, produces = "application/json")
    public Iterable<Item> list(Model model){
        Iterable<Item> itemList = itemService.listAllItems();
        return itemList;
    }
    @ApiOperation(value = "Search an item by its number",response = Item.class)
    @RequestMapping(value = "/read/{id}", method= RequestMethod.GET, produces = "application/json")
    public Item readItem(@PathVariable Integer id, Model model){
       Item item = itemService.getItemById(id);
        return item;
    }

    @ApiOperation(value = "Add an item")
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity saveItem(@RequestBody Item item){
        itemService.addItem(item);
        return new ResponseEntity("Item saved successfully", HttpStatus.OK);
    }

    @ApiOperation(value = "Withdraw items from stock")
    @RequestMapping(value = "/withdraw/{id}", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity withdrawItem(@RequestParam Integer id, @RequestParam Integer withdraw){
        Item storedItem = itemService.getItemById(id);
        Integer amount = storedItem.getAmount();
        amount -= withdraw;
        storedItem.setAmount(amount);
        itemService.addItem(storedItem);
        return new ResponseEntity("Withdrawed Items successfully", HttpStatus.OK);
    }
    
    @ApiOperation(value = "Deposit items to stock")
    @RequestMapping(value = "/deposit/{id}", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity depositItem(@RequestParam Integer id, @RequestParam Integer deposit){
        Item storedItem = itemService.getItemById(id);
        Integer amount = storedItem.getAmount();
        amount += deposit;
        storedItem.setAmount(amount);
        itemService.addItem(storedItem);
        return new ResponseEntity("Deposited Items successfully", HttpStatus.OK);
    }
    
    @ApiOperation(value = "Delete an Item")
    @RequestMapping(value="/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity delete(@PathVariable Integer id){
        itemService.deleteItem(id);
        return new ResponseEntity("Item deleted successfully", HttpStatus.OK);
    }

}
