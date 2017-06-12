package mongo.converters;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.bson.types.ObjectId;
import org.springframework.core.convert.converter.Converter;

import com.mongodb.DBObject;

import mongo.model.Note;
import mongo.model.NoteItem;

public class NoteReadConverter implements Converter<DBObject, Note> {

	@Override
	public Note convert(DBObject source) {
		DBObject dbo = (DBObject)source.get("items");
		List<NoteItem> items=null;
		if(dbo!=null) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
			items = new ArrayList<>();
			Set<String> keys = (Set<String>)dbo.keySet();
			for(String key: keys) {
				DBObject obj = (DBObject)dbo.get(key);
				Date date=null;
				try {
					date=sdf.parse((String) obj.get("checkedDate"));
				} catch (Exception e) {
					date=new Date();
				}
				NoteItem item = new NoteItem(Long.valueOf(key),
						 (Boolean)obj.get("checked"),
	                     date,
	                     (String) obj.get("itemName"),
	                     (String) obj.get("itemDescription"));
				items.add(item);
			}
		}
		SimpleDateFormat iso = new SimpleDateFormat();
		Note note=null;
		try {
			note = new Note((ObjectId) new ObjectId(((String)source.get("_id"))), 
							     (Date) source.get("createdDate"),
					             (Long) source.get("noteId"), 
					             (Boolean) source.get("checked"),
					             (Date) source.get("checkedDate"),
					             (String) source.get("name"),
					             (String) source.get("text"),
					             (String) source.get("username"),
					             (Boolean) source.get("archive"),
							     (Boolean) source.get("shared"),
					             (String) source.get("sharedForUsername"),
					             items);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    return note;
	}

}
