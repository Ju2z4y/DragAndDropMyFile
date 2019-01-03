import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileTransfert {
	
	private BufferedInputStream bis;
	private BufferedOutputStream bos;
	
    private byte[] buf = new byte[10240];
    private int longueur = 0;
	
	public void deliveryAndRemove(File file, String way) {
		
	    try {
			bis = new BufferedInputStream(new FileInputStream(file));
		    bos = new BufferedOutputStream(new FileOutputStream(new File(way + file.getName())));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        try {
    	    while((longueur = bis.read(buf)) > 0){
    	    	bos.write(buf, 0, longueur);
    	    }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     
        try {
			bis.close();
            bos.close();
            file.delete();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void delivery(File file, String way, String newFileName) {
		
	    try {
			bis = new BufferedInputStream(new FileInputStream(file));
		    bos = new BufferedOutputStream(new FileOutputStream(new File(way + newFileName)));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        try {
    	    while((longueur = bis.read(buf)) > 0){
    	    	bos.write(buf, 0, longueur);
    	    }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     
        try {
			bis.close();
            bos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void deliveryAndRemove(File file, String way, String newFileName) {
		
	    try {
			bis = new BufferedInputStream(new FileInputStream(file));
		    bos = new BufferedOutputStream(new FileOutputStream(new File(way + newFileName)));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        try {
    	    while((longueur = bis.read(buf)) > 0){
    	    	bos.write(buf, 0, longueur);
    	    }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     
        try {
			bis.close();
            bos.close();
            file.delete();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	

}
