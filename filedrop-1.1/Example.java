import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.DefaultCaret;

/**
 * A simple example showing how to use {@link FileDrop}
 * @author Robert Harder, rob@iharder.net
 */
public class Example {
	
	// Plan de classement
	private static String livrer = "C:\\test\\livrer\\";
	private static String valider = "C:\\test\\valider\\";
	private static String invalider = "C:\\test\\invalider\\";
	private static String archiver = "C:\\test\\archiver\\";

    /** Runs a sample program that shows dropped files */
    public static void main( String[] args )
    {	
    	final FileTransfert fileTransfert = new FileTransfert();
    	final FileTrace fileTrace = new FileTrace();
    	final DeliveryMessages message = new DeliveryMessages();
    	final DeliveryUtils utils = new DeliveryUtils();
    	

    	
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
        
        // Scroll down automatically
        DefaultCaret caret = (DefaultCaret)jtf.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        DefaultCaret caret2 = (DefaultCaret)jtf2.getCaret();
        caret2.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        DefaultCaret caret3 = (DefaultCaret)jtf3.getCaret();
        caret3.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        DefaultCaret caret4 = (DefaultCaret)jtf4.getCaret();
        caret4.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        
        JScrollPane elmt1 = new JScrollPane(jtf);
        JScrollPane elmt2 = new JScrollPane(jtf2);
        JScrollPane elmt3 = new JScrollPane(jtf3);
        JScrollPane elmt4 = new JScrollPane(jtf4);
        
        // Creating the MenuBar and adding components
        JMenuBar mb = new JMenuBar();
        JMenu m1 = new JMenu("Options");
        mb.add(m1);
        JMenuItem m11 = new JMenuItem("Changer l'adresse de livraison");
        JMenuItem m22 = new JMenuItem("Changer l'adresse de validation");
        JMenuItem m33 = new JMenuItem("Changer l'adresse de invalidation");
        JMenuItem m44 = new JMenuItem("Changer l'adresse d'archivage");
        m1.add(m11);
        m1.add(m22);
        m1.add(m33);
        m1.add(m44);
        
        // Création du Bouton RollBack
        JPanel panel = new JPanel(); // the panel is not visible in output
        JButton send = new JButton("Annuler la dernière action");
        panel.add(send);
        
        panelMain.add(elmt1);
        panelMain.add(elmt2);
        panelMain.add(elmt3);
        panelMain.add(elmt4);
        
        frame.getContentPane().add(BorderLayout.NORTH, mb);
        frame.getContentPane().add(BorderLayout.CENTER, panelMain);
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        
        jtf.append("Livrer un document \n-------------------\n"
        		+ "Déposer ici un fichier à livrer.(Copie)\n"
        		+ "Celui ci doit être NON versionné ou versionné au format vX.X uniquement.\n\n"
        		+ "Incrémentation du versionning automatique.\n"
        		+ "Exemple : 2 versements du fichier exemple.doc = exemple_v1.0.doc et exemple_v1.1.doc."
        		+ "\n-------------------\n");
        jtf2.append("Valider un document \n-------------------\n"
        		+ "Déposer ici un fichier à valider.(Déplacement)"
        		+ "\n-------------------\n");
        jtf3.append("Invalider un document \n-------------------\n"
        		+ "Déposer ici un fichier à invalider.(Déplacement)"
        		+ "\n-------------------\n");
        jtf4.append("Archiver un document \n-------------------\n"
        		+ "Déposer ici un fichier à archiver.(Déplacement)"
        		+ "\n-------------------\n");
        
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
	                    String fileNewName = file.getName();
	                    boolean nameModified = false;

	                    // Vérification que le DnD est un fichier
	                    if (file.isFile()) {

	                    		fileNewName = utils.checkIfVersion(fileNewName);
		                    	File checkFile = new File(livrer + fileNewName);

		                    	while (checkFile.exists()) {
		                    		fileNewName = utils.versionIncr2(fileNewName);
		                    		checkFile = new File(livrer + fileNewName);
		                    		nameModified = true;
		                    	}

		                        // Gestion du transfert du fichier
		                        fileTransfert.delivery(file, livrer, fileNewName);
		                        // Génération de la trace de gestion du fichier
		                        fileTrace.generateTrace(file, livrer, fileNewName);
		                    	
		                        // Information de l'utilisateur sur le mouvemnet du fichier
		                        jtf.append( message.deliveryValidation(file, livrer, fileNewName));

	                    	// Vérifcation de l'existance du fichier dans le dossier d'arrivée.
	                    	

	                    } else {
	                    	jtf.append( message.notAFile(file));
	                    }
	                } catch( IOException e ) {
	                    	System.out.println("Error : " + e.getMessage());
	                    	
	                } catch (DeliveryTrackException e){
                		System.out.println(e.getMessage());
                		jtf.append("Le fichier doit être sans version, ou versionné au format 'vX.X'. Cela prévient les erreurs de parsing.\n--------------------\n");
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
		                    fileTransfert.deliveryAndRemove(file, valider);
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
		                    fileTransfert.deliveryAndRemove(file, invalider);
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
	                        fileTransfert.deliveryAndRemove(file, archiver, newFileName);
	                        // Génération de la trace de gestion du fichier
	                        fileTrace.generateTrace(file, archiver, newFileName);
	                        // Information de l'utilisateur sur le mouvemnet du fichier
	                        jtf4.append( message.deliveryValidation(file, archiver, newFileName) );
	                    } else {
	                    	jtf4.append( message.notAFile(file));
	                    }
                    } catch( IOException e ) {
                    	System.out.println("Error : " + e.getMessage());
                    } catch ( NumberFormatException e ) {
                    	jtf4.append("Le fichier doit être sans version, ou versionné au format 'vX.X'. Cela prévient les erreurs de parsing.\n--------------------\n");
                    } catch ( StringIndexOutOfBoundsException e ) {
                    	jtf4.append("Le fichier doit être sans version, ou versionné au format 'vX.X'. Cela prévient les erreurs de parsing.\n--------------------\n");
                    }
                }   // end for: through each dropped file
            }   // end filesDropped
        }); // end FileDrop.Listener
        
        m11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
            	livrer = JOptionPane.showInputDialog(frame,
                        "Où dois-je livrer ?", null);
            }
        });
        
        m22.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent evt) {
        		livrer = JOptionPane.showInputDialog(frame,
        				"Où dois-je valider ?", null);
        	}
        });
        
        m33.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent evt) {
        		livrer = JOptionPane.showInputDialog(frame,
        				"Où dois-je invalider ?", null);
        	}
        });
        
        m44.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent evt) {
        		livrer = JOptionPane.showInputDialog(frame,
        				"Où dois-je archiver ?", null);
        	}
        });

        frame.setSize(1200,400);
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
