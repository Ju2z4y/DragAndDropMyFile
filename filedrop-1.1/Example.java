import java.awt.Color;
import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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
    	final DeliveryMessages message = new DeliveryMessages();
    	final DeliveryUtils utils = new DeliveryUtils();
    	
    	// Plan de classement
    	String livrer = "C:\\test\\livrer\\";
    	String valider = "C:\\test\\valider\\";
    	String invalider = "C:\\test\\invalider\\";
    	String archiver = "C:\\test\\archiver\\";
    	
    	
    	// Initialisation de l'interface graphique Swing et de ses composants.
        JFrame frame = new JFrame( "DeliveryTrack" );
        
        JPanel panelMain = new JPanel();
        panelMain.setLayout(new GridLayout(1,4));
        
        JTextArea jtf = new JTextArea();
        jtf.setBorder(BorderFactory.createLineBorder(Color.black));
        JTextArea jtf2 = new JTextArea();
        jtf2.setBorder(BorderFactory.createLineBorder(Color.black));
        JTextArea jtf3 = new JTextArea();
        jtf3.setBorder(BorderFactory.createLineBorder(Color.black));
        JTextArea jtf4 = new JTextArea();
        jtf4.setBorder(BorderFactory.createLineBorder(Color.black));
        
        JScrollPane elmt1 = new JScrollPane(jtf);
        JScrollPane elmt2 = new JScrollPane(jtf2);
        JScrollPane elmt3 = new JScrollPane(jtf3);
        JScrollPane elmt4 = new JScrollPane(jtf4);

        panelMain.add(elmt1);
        panelMain.add(elmt2);
        panelMain.add(elmt3);       
        panelMain.add(elmt4);       
        frame.getContentPane().add(panelMain);
        
        jtf.append("Livrer un document \n");
        jtf2.append("Valider un document \n");
        jtf3.append("Invalider un document \n");
        jtf4.append("Archiver un document \n");
        
        jtf.setEditable(false);
        jtf2.setEditable(false);
        jtf3.setEditable(false);
        jtf4.setEditable(false);
        
        jtf.setFont(jtf.getFont().deriveFont(15f));
        jtf2.setFont(jtf2.getFont().deriveFont(15f));
        jtf3.setFont(jtf3.getFont().deriveFont(15f));
        jtf4.setFont(jtf4.getFont().deriveFont(15f));
        
        Color gris = new Color(153,153,153);
        Color vert = new Color(0,255,51);
        Color rouge = new Color(255,51,51);
        Color bleu = new Color(51,51,255);
        
        jtf.setBackground(gris);
        jtf2.setBackground(vert);
        jtf3.setBackground(rouge);
        jtf4.setBackground(bleu);
        
        jtf.setLineWrap(true);
        jtf2.setLineWrap(true);
        jtf3.setLineWrap(true);
        jtf4.setLineWrap(true);
        
        // LIVRAISON BOX
        new FileDrop( System.out, jtf, /*dragBorder,*/ new FileDrop.Listener() {   
        	public void filesDropped( File[] files ) {   
	        	for( int i = 0; i < files.length; i++ ) {   
	        		try { // Récupération du fichier DnD
	                    File file = new File(files[i].getCanonicalPath());
	                    // Vérification que le DnD est un fichier
	                    if (file.isFile()) {
	                        // Gestion du transfert du fichier
	                        fileTransfert.delivery(file, livrer);
	                        // Génération de la trace de gestion du fichier
	                        fileTrace.generateTrace(file, livrer);
	                        // Information de l'utilisateur sur le mouvemnet du fichier
	                        jtf.append( message.deliveryValidation(file, livrer) );
	                    } else {
	                    	jtf.append( message.notAFile(file));
	                    }
	                } catch( IOException e ) {
	                    	System.out.println("Error : " + e.getMessage());
	                }
            	}   // end for: through each dropped file
        	}   // end filesDropped
        }); // end FileDrop.Listener
        
        // VALIDATION BOX
        new FileDrop( System.out, jtf2, /*dragBorder,*/ new FileDrop.Listener() {   
        	public void filesDropped( File[] files ) {   
        		for( int i = 0; i < files.length; i++ ) {
        			try { // Récupération du fichier DnD
	                    File file = new File(files[i].getCanonicalPath());
	                    // Vérification que le DnD est un fichier
	                    if (file.isFile()) {
		                    // Gestion du transfert du fichier
		                    fileTransfert.delivery(file, valider);
		                    // Génération de la trace de gestion du fichier
		                    fileTrace.generateTrace(file, valider);
		                    // Information de l'utilisateur sur le mouvemnet du fichier
		                    jtf2.append( message.deliveryValidation(file, valider) );
	                    } else {
	                    	jtf2.append( message.notAFile(file));
	                    }
                    }catch( IOException e ) {
                    	System.out.println("Error : " + e.getMessage());
                    }
                }   // end for: through each dropped file
            }   // end filesDropped
        }); // end FileDrop.Listener
        
        // INVALIDATION BOX
        new FileDrop( System.out, jtf3, /*dragBorder,*/ new FileDrop.Listener() {   
        	public void filesDropped( File[] files ) {   
        		for( int i = 0; i < files.length; i++ ) {   
        			try { // Récupération du fichier DnD
	                    File file = new File(files[i].getCanonicalPath());
	                    // Vérification que le DnD est un fichier
	                    if (file.isFile()) {
		                    // Gestion du transfert du fichier
		                    fileTransfert.delivery(file, invalider);
		                    // Génération de la trace de gestion du fichier
		                    fileTrace.generateTrace(file, invalider);
		                    // Information de l'utilisateur sur le mouvemnet du fichier
		                    jtf3.append( message.deliveryValidation(file, invalider) );
	                    } else {
	                    	jtf3.append( message.notAFile(file));
	                    }
                    }catch( IOException e ) {
                    	System.out.println("Error : " + e.getMessage());
                    }
                }   // end for: through each dropped file
            }   // end filesDropped
        }); // end FileDrop.Listener  
        
        // ARCHIVAGE BOX
        new FileDrop( System.out, jtf4, /*dragBorder,*/ new FileDrop.Listener() {   
        	public void filesDropped( File[] files ) {   
        		for( int i = 0; i < files.length; i++ ) {   
        			try { // Récupération du fichier DnD
	                    File file = new File(files[i].getCanonicalPath());
	                    // Vérification que le DnD est un fichier
	                    if (file.isFile()) {
	                    	String newFileName = utils.versionIncr(file.getName());
	                        // Gestion du transfert du fichier
	                        fileTransfert.delivery(file, archiver, newFileName);
	                        // Génération de la trace de gestion du fichier
	                        fileTrace.generateTrace(file, archiver, newFileName);
	                        // Information de l'utilisateur sur le mouvemnet du fichier
	                        jtf4.append( message.deliveryValidation(file, archiver, newFileName) );
	                    } else {
	                    	jtf4.append( message.notAFile(file));
	                    }
                    }catch( IOException e ) {
                    	System.out.println("Error : " + e.getMessage());
                    }
                }   // end for: through each dropped file
            }   // end filesDropped
        }); // end FileDrop.Listener

        frame.setSize(1200,300);
//        frame.setBounds( 100, 100, 100, 100 );
        frame.setDefaultCloseOperation( frame.EXIT_ON_CLOSE );
        frame.setVisible(true);
    }   // end main



}
	// Utilitaires débug
// Check up du fichier DnD
//System.out.println("Chemin absolu du fichier : " + file.getAbsolutePath());
//System.out.println("Nom du fichier : " + file.getName());
//System.out.println("Est-ce qu'il existe ? " + file.exists());
//System.out.println("Est-ce un répertoire ? " + file.isDirectory());
//System.out.println("Est-ce un fichier ? " + file.isFile());
//System.out.println("Taille ? " + file.length());
