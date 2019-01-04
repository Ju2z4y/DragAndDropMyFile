import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class FileTrace {
	
    private List<String> lines;
    private String pathString = "C:\\DeliveryTrack\\logs\\";
	private Path pathLog = Paths.get(pathString + "logs.txt");
	private FileTransfert fileTransfert = new FileTransfert();
     
 	private static ArrayList<Action> actions = new ArrayList<Action>();
	
	public void generateTrace(File file, String way, String action) {
		Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyy hh:mm:ss");
        String formattedDate = formatter.format(date);
        String pathDest = way + file.getName();
    	lines = Arrays.asList(
    			"Action : " + action,
    			"Date : " + formattedDate, 
    			"Acteur : " + System.getProperty("user.name"), 
    			"Fichier : " + file.getName(), 
    			"Origine : " + file.getAbsolutePath().substring(0, file.getAbsolutePath().lastIndexOf("\\")+1), 
    			"Destination : " + way, 
    			"------------------------" );
        
    	actions.add(new Action(action, file.getAbsolutePath(), pathDest));
    	
		try {
			Files.write(pathLog, lines, Charset.forName("UTF-8"),
		            StandardOpenOption.CREATE, StandardOpenOption.APPEND);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void generateTrace(File file, String way, String action, String newName) {
		Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyy hh:mm:ss");
        String formattedDate = formatter.format(date);
        String pathDest = way + newName;
    	lines = Arrays.asList(
    			"Action : " + action,
    			"Date : " + formattedDate, 
    			"Acteur : " + System.getProperty("user.name"), 
    			"Fichier : " + file.getName(), 
    			"Origine : " + file.getAbsolutePath().substring(0, file.getAbsolutePath().lastIndexOf("\\")+1), 
    			"Destination : " + way, 
    			"Nouvelle version : " + newName, 
    			"------------------------" );
    	
    	if (action != "Annulation") {
    		actions.add(new Action(action, file.getAbsolutePath(), pathDest));	
    	}
        
		try {
			Files.write(pathLog, lines, Charset.forName("UTF-8"),
		            StandardOpenOption.CREATE, StandardOpenOption.APPEND);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String cancelLastAction() {
		if (actions.size() > 0) {
			Action actionToCancel = actions.get(actions.size()-1);
			File fileOrigin = new File(actionToCancel.getPathOrigin());
			File fileDest = new File(actionToCancel.getPathDest());
			if (actionToCancel.getAction() == "Livraison") {
				fileDest.delete();
			} else {
				fileTransfert.cancelDelivery(fileOrigin, fileDest);
			}
			generateTrace(fileOrigin, actionToCancel.getPathDest(), "Annulation", fileDest.getName());
			actions.remove(actions.size()-1);
			System.out.println("Actions Size : " + actions.size());
			System.out.println("Action : " + actionToCancel.toString());
			return actionToCancel.getAction();
		} else {
			return "NoActions";
		}

	}
	
    public void setPathString(String pathString) {
		this.pathString = pathString;
	}
}
