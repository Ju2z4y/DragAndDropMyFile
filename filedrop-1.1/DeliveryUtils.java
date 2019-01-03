
public class DeliveryUtils {

	public String versionIncr(String fileName) {
		
		String fileNameNoVersion;
		String fileNameEnd;
		String fileVersion;
		String fileVersion2;
		int version;
		int version2;
		
		for (int i = 0; i<fileName.length(); i++) {
			if ((fileName.charAt(i) == 'v') || (fileName.charAt(i) == 'V')) {
				if (Character.isDigit(fileName.charAt(i+1))) {
					fileNameNoVersion = fileName.substring(0, i+1);
					fileNameEnd = fileName.substring(fileNameNoVersion.length());
					fileVersion = fileNameEnd.substring(0, fileNameEnd.lastIndexOf('.'));
					fileVersion = fileVersion.substring(0, fileVersion.lastIndexOf('.'));
					fileNameEnd = fileNameEnd.substring(fileVersion.length());
					fileVersion2 = fileNameEnd.substring(1, fileNameEnd.lastIndexOf('.'));
					fileNameEnd = fileNameEnd.substring(fileVersion2.length()+1);
					version2 = Integer.parseInt(fileVersion2);
					version2 = 0;
					version = Integer.parseInt(fileVersion);
					version++;
					fileName = fileNameNoVersion + version + "." + version2 + fileNameEnd;
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
	
	public String checkIfVersion(String fileName) throws DeliveryTrackException {
		boolean isVersion = false;
		
		for (int i = 0; i<fileName.length(); i++) {
			if ((fileName.charAt(i) == 'v') || (fileName.charAt(i) == 'V')) {
				if (Character.isDigit(fileName.charAt(i+1))) {
					if (fileName.charAt(i+2) == '.') {
						if (Character.isDigit(fileName.charAt(i+3))) {
							isVersion = true;
							if (fileName.charAt(i+4) == '.') {
								if (Character.isDigit(fileName.charAt(i+5))) {
									throw new DeliveryTrackException("La version du fichier n'est pas conforme");
								}
							}
						} else {
							throw new DeliveryTrackException("La version du fichier n'est pas conforme");
						}
					} else {
						throw new DeliveryTrackException("La version du fichier n'est pas conforme");
					}
					
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