
public class DeliveryUtils {

	public String versionIncr(String fileName) {
		
		String fileNameNoVersion;
		String fileNameEnd;
		String fileVersion;
		int version;
		
		for (int i = 0; i<fileName.length(); i++) {
			if ((fileName.charAt(i) == 'v') || (fileName.charAt(i) == 'V')) {
				if (Character.isDigit(fileName.charAt(i+1))) {
					fileNameNoVersion = fileName.substring(0, i+1);
					fileNameEnd = fileName.substring(fileNameNoVersion.length());
					fileVersion = fileNameEnd.substring(0, fileNameEnd.lastIndexOf('.'));
					fileVersion = fileVersion.substring(0, fileVersion.lastIndexOf('.'));
					fileNameEnd = fileNameEnd.substring(fileVersion.length());
					version = Integer.parseInt(fileVersion);
					version++;
					fileName = fileNameNoVersion + version + fileNameEnd;
				}
			}
		}
		
		return fileName;
	}
	
	public String versionIncr2(String fileName) {
		
		String fileNameNoExt = fileName.substring(0, fileName.lastIndexOf('.'));
		String fileNameExt = fileName.substring(fileNameNoExt.length());
		String fileNameNoVersion = fileNameNoExt.substring(0, fileNameNoExt.lastIndexOf('.'));
		String fileNameVersion = fileNameNoExt.substring(fileNameNoVersion.length()+1);
		int version = Integer.parseInt(fileNameVersion);
		version ++;
		fileNameVersion = "." + version;
		fileName = fileNameNoVersion + fileNameVersion + fileNameExt;

		return fileName;
	}
	
	public String checkIfVersion(String fileName) {
		boolean isVersion = false;
		
		for (int i = 0; i<fileName.length(); i++) {
			if ((fileName.charAt(i) == 'v') || (fileName.charAt(i) == 'V')) {
				if (Character.isDigit(fileName.charAt(i+1))) {
					isVersion = true;
				}
			}
		}
		
		if (!isVersion) {
			fileName = addVersion(fileName);
		}
	
		return fileName;
	}
	
	private String addVersion(String fileName) {
		String fileNameNoExt = fileName.substring(0, fileName.lastIndexOf('.'));
		String fileNameExt = fileName.substring(fileNameNoExt.length());
		fileName = fileNameNoExt +"_v1.0" + fileNameExt;
		return fileName;
	}
}
//