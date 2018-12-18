import java.awt.Color;
import java.awt.GridLayout;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * A simple example showing how to use {@link FileDrop}
 * @author Robert Harder, rob@iharder.net
 */
public class Example {
	
    


    /** Runs a sample program that shows dropped files */
    public static void main( String[] args )
    {
    	final FileTransfert fileTransfert = new FileTransfert();
    	final FileTrace fileTrace = new FileTrace();
    	
        javax.swing.JFrame frame = new javax.swing.JFrame( "FileDrop" );
        
        JPanel panelMain = new JPanel();
        panelMain.setLayout(new GridLayout(1,3));
        
        JTextArea jtf = new JTextArea();
        jtf.setBorder(BorderFactory.createLineBorder(Color.black));
        JTextArea jtf2 = new JTextArea();
        jtf2.setBorder(BorderFactory.createLineBorder(Color.black));
        JTextArea jtf3 = new JTextArea();
        jtf3.setBorder(BorderFactory.createLineBorder(Color.black));

        panelMain.add(jtf);
        panelMain.add(jtf2);
        panelMain.add(jtf3);       
        frame.getContentPane().add(panelMain);
        
        new FileDrop( System.out, jtf, /*dragBorder,*/ new FileDrop.Listener()
        {   public void filesDropped( java.io.File[] files )
            {   
        	String way = "C:\\test\\livrés\\";
        	jtf.append("Livrer");
        	for( int i = 0; i < files.length; i++ )
                {   try
                    {   jtf.append( files[i].getCanonicalPath() + "\n" );
                    // Récupération du fichier DnD
                    File file = new File(files[i].getCanonicalPath());
                    
                    //Check up du fichier DnD
                    System.out.println("Chemin absolu du fichier : " + file.getAbsolutePath());
                    System.out.println("Nom du fichier : " + file.getName());
                    System.out.println("Est-ce qu'il existe ? " + file.exists());
                    System.out.println("Est-ce un répertoire ? " + file.isDirectory());
                    System.out.println("Est-ce un fichier ? " + file.isFile());
                    System.out.println("Taille ? " + file.length());
                    
                    // Gestion du transfert du fichier
                    fileTransfert.delivery(file, way);
                    // Génération de la trace de gestion du fichier
                    fileTrace.generateTrace(file, way);

                    }   // end try
                    catch( java.io.IOException e ) {
                    	System.out.println("Error : " + e.getMessage());
                    }
                }   // end for: through each dropped file
            }   // end filesDropped
        }); // end FileDrop.Listener
        
        new FileDrop( System.out, jtf2, /*dragBorder,*/ new FileDrop.Listener()
        {   public void filesDropped( java.io.File[] files )
            {   
        	String way = "C:\\test\\validés\\";
        	jtf.append("Valider");
        	for( int i = 0; i < files.length; i++ )
                {   try
                    {   jtf2.append( files[i].getCanonicalPath() + "\n" );
                    // Récupération du fichier DnD
                    File file = new File(files[i].getCanonicalPath());
                    
                    //Check up du fichier DnD
                    System.out.println("Chemin absolu du fichier : " + file.getAbsolutePath());
                    System.out.println("Nom du fichier : " + file.getName());
                    System.out.println("Est-ce qu'il existe ? " + file.exists());
                    System.out.println("Est-ce un répertoire ? " + file.isDirectory());
                    System.out.println("Est-ce un fichier ? " + file.isFile());
                    System.out.println("Taille ? " + file.length());
                    
                    // Gestion du transfert du fichier
                    fileTransfert.delivery(file, way);
                    // Génération de la trace de gestion du fichier
                    fileTrace.generateTrace(file, way);

                    }   // end try
                    catch( java.io.IOException e ) {
                    	System.out.println("Error : " + e.getMessage());
                    }
                }   // end for: through each dropped file
            }   // end filesDropped
        }); // end FileDrop.Listener
        
        new FileDrop( System.out, jtf3, /*dragBorder,*/ new FileDrop.Listener()
        {   public void filesDropped( java.io.File[] files )
            {   
        	String way = "C:\\test\\invalidés\\";
        	jtf.append("Invalider");
        	for( int i = 0; i < files.length; i++ )
                {   try
                    {   jtf3.append( files[i].getCanonicalPath() + "\n" );
                    // Récupération du fichier DnD
                    File file = new File(files[i].getCanonicalPath());
                    
                    //Check up du fichier DnD
                    System.out.println("Chemin absolu du fichier : " + file.getAbsolutePath());
                    System.out.println("Nom du fichier : " + file.getName());
                    System.out.println("Est-ce qu'il existe ? " + file.exists());
                    System.out.println("Est-ce un répertoire ? " + file.isDirectory());
                    System.out.println("Est-ce un fichier ? " + file.isFile());
                    System.out.println("Taille ? " + file.length());
                    
                    // Gestion du transfert du fichier
                    fileTransfert.delivery(file, way);
                    // Génération de la trace de gestion du fichier
                    fileTrace.generateTrace(file, way);

                    }   // end try
                    catch( java.io.IOException e ) {
                    	System.out.println("Error : " + e.getMessage());
                    }
                }   // end for: through each dropped file
            }   // end filesDropped
        }); // end FileDrop.Listener        

        frame.setSize(900,300);
//        frame.setBounds( 100, 100, 100, 100 );
        frame.setDefaultCloseOperation( frame.EXIT_ON_CLOSE );
        frame.setVisible(true);
    }   // end main



}
