package mongo.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

//@Document(collection="items")
public class NoteItem {

 //  @Id
   private Long itemId;	
   private Boolean checked;
   private Date checkedDate;
   private String itemName;
   private String itemDescription;   
   
//   @PersistenceConstructor
   public NoteItem(Long itemId, Boolean checked, Date checkedDate, String itemName, String itemDescription) {
	this.itemId = itemId;
	this.checked = checked;
	this.checkedDate = checkedDate;
	this.itemName = itemName;
	this.itemDescription = itemDescription;
}

   public NoteItem() {}

public Long getItemId() {
	  return itemId;
   }
   public void setId(Long itemId) {
	  this.itemId = itemId;
   }
   public Boolean getChecked() {
	  return checked;
   }
   public void setChecked(Boolean checked) {
	  this.checked = checked;
   }
   public Date getCheckedDate() {
	  return checkedDate; 
   }
   public void setCheckedDate(Date checkedDate) {
	  this.checkedDate = checkedDate;
   }
   public String getItemName() {
	  return itemName;
   }
   public void setItemName(String itemName) {
	  this.itemName = itemName;
   }
   public String getItemDescription() {
	  return itemDescription; 
   } 
   public void setItemDescription(String itemDescription) {
	  this.itemDescription = itemDescription;
   }
   
}
