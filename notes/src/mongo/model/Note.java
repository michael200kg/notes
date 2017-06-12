package mongo.model;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import notes.restful.serializers.ObjectIdJsonSerializer;

import java.util.Date;

@Document( collection="notes" )
public class Note {

   @Id 
   @JsonSerialize(using=ObjectIdJsonSerializer.class)
   private ObjectId objId; 

   private Long noteId; 
   private Date createdDate;
   private Boolean checked;
   private Date checkedDate;
   private String name;
   private String text;
   private String username;
   private Boolean archive;
   private Boolean shared;
   private String sharedForUsername;
   @DBRef
   private List<NoteItem> items;

   @PersistenceConstructor
   public Note() {
	   this.objId = new ObjectId();
	   
   }
  
   public Note(ObjectId objId,
		       Date createdDate,
		       Long noteId, 
	           Boolean checked, 
	           Date checkedDate,
	           String name, 
	           String text,
	           String username,
	           Boolean archive,
	           Boolean shared,
               String sharedForUsername,
               List<NoteItem> items) {
      this.objId = objId;
      this.noteId = noteId;
      this.createdDate = createdDate;
      this.checked = checked;
      this.checkedDate = checkedDate;
      this.name = name;
      this.text = text;
      this.username = username;
      this.archive = archive;
      this.shared = shared;
      this.sharedForUsername = sharedForUsername;
      this.items = items;
   }   
   
   public Note(Long noteId, 
		       Date createdDate,
		       Boolean checked, 
		       Date checkedDate,
		       String name, 
		       String text,
		       String username,
		       Boolean archive,
		       Boolean shared,
               String sharedForUsername,
               List<NoteItem> items) {
	  this.objId = new ObjectId();
	  this.createdDate = createdDate;
	  this.noteId = noteId;
	  this.checked = checked;
	  this.checkedDate = checkedDate;
	  this.name = name;
	  this.text = text;
	  this.username = username;
	  this.archive = archive;
	  this.shared = shared;
	  this.sharedForUsername = sharedForUsername;
	  this.items = items;
   }
   


@Override
public String toString() {
	return "Note [objId=" + objId+ ", createDate=" + createdDate + ", noteId=" + noteId + ", checked=" + checked + ", checkedDate=" + checkedDate + ", name=" + name + ", text=" + text
			+ ", username=" + username + ", archive=" + archive + ", shared=" + shared + ", sharedForUsername=" + sharedForUsername + "]";
}

public ObjectId getObjId() {
	return objId;
}

public void setObjId(ObjectId objId) {
	this.objId = objId;
}

public Boolean getChecked() {
	return checked;
}
public void setChecked(Boolean checked) {
	this.checked = checked;
}

@JsonFormat(pattern="yyyy-MM-dd")
public Date getCheckedDate() {
	return checkedDate;
}
public void setCheckedDate(Date checkedDate) {
	this.checkedDate = checkedDate;
}



public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getText() {
	return text;
}
public void setText(String text) {
	this.text = text;
}

public List<NoteItem> getItems() {
	return items;
}
public void setItems(List<NoteItem> items) {
	this.items = items;
}


public Long getNoteId() {
	return noteId;
}

public void setNoteId(Long noteId) {
	this.noteId = noteId;
}

public String getUsername() {
	return username;
}

public void setUsername(String username) {
	this.username = username;
}

public Boolean getShared() {
	return shared;
}

public void setShared(Boolean shared) {
	this.shared = shared;
}

public String getSharedForUsername() {
	return sharedForUsername;
}

public void setSharedForUsername(String sharedForUsername) {
	this.sharedForUsername = sharedForUsername;
}

@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
public Date getCreatedDate() {
	return createdDate;
}

public void setCreatedDate(Date createdDate) {
	this.createdDate = createdDate;
}

public Boolean getArchive() {
	return archive;
}

public void setArchive(Boolean archive) {
	this.archive = archive;
}



   
}
