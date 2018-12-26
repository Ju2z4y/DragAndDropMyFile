import java.io.File;

public class DeliveryMessages {
	
	public DeliveryMessages() {
		
	}
	
	public String deliveryValidation(File file, String way) {
		String message = "-------------------\nTransfert de : " + file.getName() +"\nDans le dossier :";
		
		switch(way) {
		  case "C:\\test\\livrer\\":
			  message += " Livraison\n";
		    break;
		  case "C:\\test\\valider\\":
			  message += " Valider\n";
		    break;
		  case "C:\\test\\invalider\\":
			  message += " Invalider\n";
		    break;
		  case "C:\\test\\archiver\\":
			  message += " Archiver\n";
		    break;
		  default:
		    message += " Non identifier\n";
		}
		
		return message;
	}
	
	public String deliveryValidation(File file, String way, String newName) {
		String message = "-------------------\nTransfert de : " + file.getName() +"\nDans le dossier :";
		
		switch(way) {
		  case "C:\\test\\livrer\\":
			  message += " Livraison\n";
		    break;
		  case "C:\\test\\valider\\":
			  message += " Valider\n";
		    break;
		  case "C:\\test\\invalider\\":
			  message += " Invalider\n";
		    break;
		  case "C:\\test\\archiver\\":
			  message += " Archiver\n";
		    break;
		  default:
		    message += " Non identifier\n";
		}
		
		message += "Sous le nouveau nom : " + newName + "\n";
		
		return message;
	}

	public String notAFile(File file) {
		String message = "------------------\nCeci n'est pas un fichier : " + file.getName() + "\nTransfert annulé\n";
		return message;
	}
}
