package guru.springframework.controllers;

import guru.springframework.domain.Item;
import guru.springframework.services.ItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/item")
@Api(value="openlegacy", description="Operations pertaining to items in Open Legacy")
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
    @ApiOperation(value = "Search an item with an Item Number",response = Item.class)
    @RequestMapping(value = "/read/{id}", method= RequestMethod.GET, produces = "application/json")
    public Item readItem(@PathVariable Integer itemNo, Model model){
       Item item = itemService.getItemByItemNo(itemNo);
        return item;
    }

    @ApiOperation(value = "Add an item")
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity saveItem(@RequestBody Item item){
        itemService.addItem(item);
        return new ResponseEntity("Item saved successfully", HttpStatus.OK);
    }

    @ApiOperation(value = "Update quantity in stock")
    @RequestMapping(value = "/quantity/{id}", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity updateQuantityItem(@PathVariable Integer id, @RequestBody Item item, @PathVariable Integer quantity){
        Item storedItem = itemService.getItemByItemNo(id);
        Integer amount = storedItem.getAmount();
        amount += quantity;
        storedItem.setAmount(amount);
        itemService.addItem(storedItem);
        return new ResponseEntity("Item quantity updated successfully", HttpStatus.OK);
    }
    
    @ApiOperation(value = "Delete an Item")
    @RequestMapping(value="/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity delete(@PathVariable Integer itemNo){
        itemService.deleteItem(itemNo);
        return new ResponseEntity("Item deleted successfully", HttpStatus.OK);
    }

}
