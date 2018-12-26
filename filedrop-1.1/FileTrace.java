import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class FileTrace {
	
     private List<String> lines;
     private Path pathLog = Paths.get("C:\\test\\logs\\logs.txt");
	
	public void generateTrace(File file, String way) {
    	lines = Arrays.asList(
    			"Fichier : " + file.getName(), 
    			"Date : " + new Date().toString(), 
    			"Acteur : " + System.getProperty("user.name"), 
    			"Origine : " + file.getAbsolutePath(), 
    			"Destination : " + way, 
    			"------------------------" );
        
		try {
			Files.write(pathLog, lines, Charset.forName("UTF-8"), StandardOpenOption.APPEND);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void generateTrace(File file, String way, String newName) {
    	lines = Arrays.asList(
    			"Fichier : " + file.getName(), 
    			"Date : " + new Date().toString(), 
    			"Acteur : " + System.getProperty("user.name"), 
    			"Origine : " + file.getAbsolutePath(), 
    			"Destination : " + way, 
    			"Nouvelle version : " + newName,
    			"------------------------" );
        
		try {
			Files.write(pathLog, lines, Charset.forName("UTF-8"), StandardOpenOption.APPEND);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
