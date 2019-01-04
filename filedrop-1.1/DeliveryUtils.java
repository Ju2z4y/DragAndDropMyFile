import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;


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
	
	public void saveParam(Map<String, String> paramsMap) {

		try {
			Writer writer = new FileWriter("C:\\DeliveryTrack\\params\\params.json");
		    Gson gson = new GsonBuilder().create();
		    gson.toJson(paramsMap, writer);
		    writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Map<String, String> loadParam(boolean error) {
		Gson gson = new Gson();
		Type type = new TypeToken<Map<String, String>>(){}.getType();
		
		try {
			Reader reader = new FileReader("C:\\DeliveryTrack\\params\\params.json");
			Map<String, String> map = gson.fromJson(reader, type);
			System.out.println(map.toString());
			reader.close();
			if (error) {
				map.replace("Error", "true");
			} else {
				map.replace("Error", "false");
			}
			
			return map;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			
			createParams();
			return loadParam(true);
		} catch (IOException e) {
			return null;
		} catch (NullPointerException e) {
			createParams();
			return loadParam(true);
		}
	}
	
	public void createParams() {
		File folder1 = new File("C:\\DeliveryTrack");
		folder1.mkdir();
		File folder = new File("C:\\DeliveryTrack\\params");
		folder.mkdir();
		File livrer = new File("C:\\DeliveryTrack\\livrer");
		livrer.mkdir();
		File valider = new File("C:\\DeliveryTrack\\valider");
		valider.mkdir();
		File invalider = new File("C:\\DeliveryTrack\\invalider");
		invalider.mkdir();
		File archiver = new File("C:\\DeliveryTrack\\archiver");
		archiver.mkdir();
		File logs = new File("C:\\DeliveryTrack\\logs");
		logs.mkdir();
		Map<String, String> params = new HashMap<String, String>();
		params.put("Livraison", "C:\\DeliveryTrack\\livrer\\");
		params.put("Validation", "C:\\DeliveryTrack\\valider\\");
		params.put("Invalidation", "C:\\DeliveryTrack\\invalider\\");
		params.put("Archivage", "C:\\DeliveryTrack\\archiver\\");
		params.put("Logs", "C:\\DeliveryTrack\\logs\\");
		params.put("Error", "true");
		
		try {
			Writer writer = new FileWriter("C:\\DeliveryTrack\\params\\params.json");
		    Gson gson = new GsonBuilder().create();
		    gson.toJson(params, writer);
		    writer.close();
		} catch (IOException e) {
			
		}
	}
	
	public String loadLogsParam() {
		Gson gson = new Gson();
		Type type = new TypeToken<Map<String, String>>(){}.getType();
		
		try {
			Reader reader = new FileReader("C:\\DeliveryTrack\\params\\params.json");
			Map<String, String> map = gson.fromJson(reader, type);
			System.out.println(map.toString());
			reader.close();
			
			return map.get("Logs");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			return null;
		} catch (IOException e) {
			return null;
		} catch (NullPointerException e) {
			return null;
		}
	}
	
	public String checkPath(String path) {
		if (path.charAt(path.length()-1) == '\\') {
			return path;
		} else {
			return path + "\\";
		}
	}
}
//