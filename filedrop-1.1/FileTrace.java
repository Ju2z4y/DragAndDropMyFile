import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class FileTrace {
	
     private List<String> lines;
     private Path pathLog = Paths.get("C:\\test\\logs\\logs.txt");
	
	public void generateTrace(File file, String way) {
		Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyy hh:mm:ss");
        String formattedDate = formatter.format(date);
    	lines = Arrays.asList(
    			"Date : " + formattedDate, 
    			"Acteur : " + System.getProperty("user.name"), 
    			"Fichier : " + file.getName(), 
    			"Origine : " + file.getAbsolutePath().substring(0, file.getAbsolutePath().lastIndexOf("\\")+1), 
    			"Destination : " + way, 
    			"------------------------" );
        
		try {
			Files.write(pathLog, lines, Charset.forName("UTF-8"),
		            StandardOpenOption.CREATE, StandardOpenOption.APPEND);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void generateTrace(File file, String way, String newName) {
		Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyy hh:mm:ss");
        String formattedDate = formatter.format(date);
    	lines = Arrays.asList(
    			"Date : " + formattedDate, 
    			"Acteur : " + System.getProperty("user.name"), 
    			"Fichier : " + file.getName(), 
    			"Origine : " + file.getAbsolutePath().substring(0, file.getAbsolutePath().lastIndexOf("\\")+1), 
    			"Destination : " + way, 
    			"Nouvelle version : " + newName, 
    			"------------------------" );
        
		try {
			Files.write(pathLog, lines, Charset.forName("UTF-8"),
		            StandardOpenOption.CREATE, StandardOpenOption.APPEND);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
