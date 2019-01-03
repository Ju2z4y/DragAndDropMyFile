import java.util.Date;

public class Action {

	private String action;
	private String pathOrigin;
	private String pathDest;
	
	public Action(String action, String pathOrigin, String pathDest) {
		this.action = action;
		this.pathOrigin = pathOrigin;
		this.pathDest = pathDest;
	}
	
	public String toString() {
		String result = "Action " + action + "\nChemin d'origine : " + pathOrigin + "\nChemin d'arrivée : " + pathDest;
		return result;
		
	}
	
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getPathOrigin() {
		return pathOrigin;
	}

	public void setPathOrigin(String pathOrigin) {
		this.pathOrigin = pathOrigin;
	}

	public String getPathDest() {
		return pathDest;
	}

	public void setPathDest(String pathDest) {
		this.pathDest = pathDest;
	}	

}
