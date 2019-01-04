import java.io.File;

public class DeliveryMessages {
	
	public DeliveryMessages() {
		
	}
	
	public String deliveryValidation(File file, String way) {
		String message = "Transfert de : " + file.getName() +"\nDans le dossier :" + way;
		
		return message += "\n-------------------\n";
	}
	
	public String deliveryValidation(File file, String way, String newName) {
		String message = "Transfert de : " + file.getName() +"\nDans le dossier :" + way;
		
		message += "\nSous le nouveau nom : " + newName + "\n-------------------\n";
		
		return message;
	}
	
	public String cancelValidation() {
		return "La dernière action à correctement été supprimée\n-----------------\n";
	}

	public String notAFile(File file) {
		String message = "Ceci n'est pas un fichier : " + file.getName() + "\nTransfert annulé\n------------------\n";
		return message;
	}
}
