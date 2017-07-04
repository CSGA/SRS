package srs.util;
import java.util.UUID;


public class UUIDUtil {

	public static String generate(){
		String uuid=UUID.randomUUID().toString();
		String uuidString=uuid.replace("-", "");
		return uuidString;
		
	}

}
