
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.shyiko.mysql.binlog.BinaryLogClient;
import com.github.shyiko.mysql.binlog.BinaryLogClient.EventListener;
import com.github.shyiko.mysql.binlog.event.Event;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import or.CDCEvent;
import or.manager.CDCEventManager;

public class Readlog {

	public static void main(String[] args) throws IOException {
		BinaryLogClient client = new BinaryLogClient("localhost",3307,"root", "poiuyt");
		client.registerEventListener(new EventListener() {

			@Override
			public void onEvent(Event event) {
	                    
	                    Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
	                    String string = gson.toJson(event.getData());
	                    /* ObjectMapper mapper=new ObjectMapper();
	                    String string = null;
						try {
							string = mapper.writeValueAsString(ce);
						} catch (JsonProcessingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}*/
	                    System.out.println(event.getData()); 
	                
	            
	        }

		});
		client.connect();
	}
}
