import java.io.File;

public class DeliveryMessages {
	
	public DeliveryMessages() {
		
	}
	
	public String deliveryValidation(File file, String way) {
		String message = "Transfert de : " + file.getName() +"\nDans le dossier :";
		
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
		
		return message += "-------------------\n";
	}
	
	public String deliveryValidation(File file, String way, String newName) {
		String message = "Transfert de : " + file.getName() +"\nDans le dossier :" + way;
		
		message += "\nSous le nouveau nom : " + newName + "\n-------------------\n";
		
		return message;
	}

	public String notAFile(File file) {
		String message = "Ceci n'est pas un fichier : " + file.getName() + "\nTransfert annulé\n------------------\n";
		return message;
	}
}
