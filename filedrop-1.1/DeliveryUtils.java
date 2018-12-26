
public class DeliveryUtils {

	public String versionIncr(String fileName) {
		boolean isVersion = false;
		
		for (int i = 0; i<fileName.length(); i++) {
			if ((fileName.charAt(i) == 'v') || (fileName.charAt(i) == 'V')) {
				if (Character.isDigit(fileName.charAt(i+1))) {
					isVersion = true;
					int num = Character.getNumericValue(fileName.charAt(i+1));
					num++;
					StringBuilder fileNameBuild = new StringBuilder(fileName);
					fileNameBuild.setCharAt(i+1, Integer.toString(num).charAt(0));
					fileName = fileNameBuild.toString();
				}
			}
		}
		
		if (!isVersion) {
			String fileNameNoExt = fileName.substring(0, fileName.lastIndexOf('.'));
			String fileNameExt = fileName.substring(fileNameNoExt.length());
			fileName = fileNameNoExt +"_v1.0" + fileNameExt;
		}

		return fileName;
	}
}
//