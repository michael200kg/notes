package mongo.converters;

import java.text.SimpleDateFormat;

import org.springframework.core.convert.converter.Converter;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import mongo.model.Note;

public class NoteWriteConverter implements Converter<Note, DBObject> {

	@Override
	public DBObject convert(Note source) {
		SimpleDateFormat iso = new SimpleDateFormat();
		DBObject dbo = new BasicDBObject();
		dbo.put("_id", source.getObjId().toHexString());
		dbo.put("createdDate", source.getCreatedDate());
		dbo.put("noteId", source.getNoteId());
	    dbo.put("checked", source.getChecked());
	    dbo.put("checkedDate", source.getCheckedDate());
	    dbo.put("name", source.getName());
	    dbo.put("text", source.getText());
	    dbo.put("username",source.getUsername());
	    dbo.put("archive", source.getArchive());
	    dbo.put("shared",source.getShared());
	    dbo.put("sharedForUsername",source.getSharedForUsername());
	    if(source.getItems()!=null){
	    	DBObject dboItems = new BasicDBObject();
	    	SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
	    	source.getItems().forEach(item->{
	    		                 DBObject dboi = new BasicDBObject();                 
	    		                 dboi.put("checked",item.getChecked());
	    		                 dboi.put("checkedDate",sdf.format(item.getCheckedDate()));
	    		                 dboi.put("itemName",item.getItemName());
	    		                 dboi.put("itemDescription",item.getItemDescription());
	    		                 dboItems.put(item.getItemId().toString(), dboi);
	    		                 
	    	});
	    	dbo.put("items", dboItems);
	    }

	    return dbo;
	}

}
