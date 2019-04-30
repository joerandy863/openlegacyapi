package guru.springframework.domain;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "The database generated item number (ID)")
    private Integer itemNo;
    @ApiModelProperty(notes = "The item name")
    private String name;
    @ApiModelProperty(notes = "The amount of items availables")
    private Integer amount;
    @ApiModelProperty(notes = "The inventory code of each item")
    private String inventoryCode;
	
    public Integer getItemNo() {
		return itemNo;
	}
	public void setItemNo(Integer itemNo) {
		this.itemNo = itemNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public String getInventoryCode() {
		return inventoryCode;
	}
	public void setInventoryCode(String inventoryCode) {
		this.inventoryCode = inventoryCode;
	}

}
