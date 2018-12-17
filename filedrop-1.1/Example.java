import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * A simple example showing how to use {@link FileDrop}
 * @author Robert Harder, rob@iharder.net
 */
public class Example {


    /** Runs a sample program that shows dropped files */
    public static void main( String[] args )
    {
        javax.swing.JFrame frame = new javax.swing.JFrame( "FileDrop" );
        //javax.swing.border.TitledBorder dragBorder = new javax.swing.border.TitledBorder( "Drop 'em" );
        final javax.swing.JTextArea text = new javax.swing.JTextArea();
        frame.getContentPane().add( 
            new javax.swing.JScrollPane( text ), 
            java.awt.BorderLayout.CENTER );
        
        new FileDrop( System.out, text, /*dragBorder,*/ new FileDrop.Listener()
        {   public void filesDropped( java.io.File[] files )
            {   for( int i = 0; i < files.length; i++ )
                {   try
                    {   text.append( files[i].getCanonicalPath() + "\n" );
                    
                    File file = new File(files[i].getCanonicalPath());
                    
                    System.out.println("Chemin absolu du fichier : " + file.getAbsolutePath());
                    System.out.println("Nom du fichier : " + file.getName());
                    System.out.println("Est-ce qu'il existe ? " + file.exists());
                    System.out.println("Est-ce un répertoire ? " + file.isDirectory());
                    System.out.println("Est-ce un fichier ? " + file.isFile());
                    System.out.println("Taille ? " + file.length());
                    
                    if (file.isFile()) {
                    	System.out.println("YES");
                    } else {
                    	System.out.println("NO");
                    }
                    
                    
                    BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
                    BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File("C:\\test\\" + file.getName())));
                    byte[] buf = new byte[10240];
                    
                    int longueur = 0;
                   
                    
                    while((longueur = bis.read(buf)) > 0){
                        bos.write(buf, 0, longueur);
                        System.out.println("got");
                     }
                    

                    bis.close();

                    bos.close();
                    
                    
                    }   // end try
                    catch( java.io.IOException e ) {
                    	System.out.println(e.getMessage());
                    }
                }   // end for: through each dropped file
            }   // end filesDropped
        }); // end FileDrop.Listener

        frame.setBounds( 100, 100, 300, 400 );
        frame.setDefaultCloseOperation( frame.EXIT_ON_CLOSE );
        frame.setVisible(true);
    }   // end main



}
