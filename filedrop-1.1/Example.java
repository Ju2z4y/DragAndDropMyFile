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

import javax.swing.JPanel;

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
        
        //javax.swing.border.TitledBorder dragBorder = new javax.swing.border.TitledBorder( "Drop 'em" );
        final javax.swing.JTextArea textLeft = new javax.swing.JTextArea();
        final javax.swing.JTextArea textCenter = new javax.swing.JTextArea();
        final javax.swing.JTextArea textRight = new javax.swing.JTextArea();
//        frame.getContentPane().add( 
//            new javax.swing.JScrollPane( textLeft ), 
//            java.awt.BorderLayout.WEST );        
        frame.getContentPane().add( 
            new javax.swing.JScrollPane( textCenter ), 
            java.awt.BorderLayout.CENTER );
//        frame.getContentPane().add( 
//            new javax.swing.JScrollPane( textCenter ), 
//            java.awt.BorderLayout.EAST );        
        
        new FileDrop( System.out, textLeft, /*dragBorder,*/ new FileDrop.Listener()
        {   public void filesDropped( java.io.File[] files )
            {   
        	String way = "C:\\test\\";
        	
        	for( int i = 0; i < files.length; i++ )
                {   try
                    {   textLeft.append( files[i].getCanonicalPath() + "\n" );
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
        
        new FileDrop( System.out, textCenter, /*dragBorder,*/ new FileDrop.Listener()
        {   public void filesDropped( java.io.File[] files )
            {   
        	String way = "C:\\test\\";
        	
        	for( int i = 0; i < files.length; i++ )
                {   try
                    {   textCenter.append( files[i].getCanonicalPath() + "\n" );
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
        
        new FileDrop( System.out, textRight, /*dragBorder,*/ new FileDrop.Listener()
        {   public void filesDropped( java.io.File[] files )
            {   
        	String way = "C:\\test\\";
        	
        	for( int i = 0; i < files.length; i++ )
                {   try
                    {   textRight.append( files[i].getCanonicalPath() + "\n" );
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

        frame.setSize(300,300);
//        frame.setBounds( 100, 100, 100, 100 );
        frame.setDefaultCloseOperation( frame.EXIT_ON_CLOSE );
        frame.setVisible(true);
    }   // end main



}
