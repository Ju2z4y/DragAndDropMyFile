import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;

/**
 * A simple example showing how to use {@link FileDrop}
 * @author Robert Harder, rob@iharder.net
 */
public class Example {
	
	private static Map<String, String> paramsMap = new HashMap<String, String>();

	private static String livraison = "Livraison";
	private static String validation = "Validation";
	private static String invalidation = "Invalidation";
	private static String archivage = "Archivage";

    /** Runs a sample program that shows dropped files */
    public static void main( String[] args )
    {	
    	final FileTransfert fileTransfert = new FileTransfert();
    	final FileTrace fileTrace = new FileTrace();
    	final DeliveryMessages message = new DeliveryMessages();
    	final DeliveryUtils utils = new DeliveryUtils();
    	
    	paramsMap = utils.loadParam(false);
    	
    	boolean planDeClassementVide;
    	
    	if(paramsMap.get("Error") == "true") {
    		planDeClassementVide = true;
    	} else {
    		planDeClassementVide = false;
    	}
    	
    	fileTrace.setPathString(paramsMap.get("Logs"));
    	
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
        JButton cancel = new JButton("Annuler la dernière action");
        panel.add(cancel);
        
        panelMain.add(elmt1);
        panelMain.add(elmt2);
        panelMain.add(elmt3);
        panelMain.add(elmt4);
        
        frame.getContentPane().add(BorderLayout.NORTH, mb);
        frame.getContentPane().add(BorderLayout.CENTER, panelMain);
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        
        jtf.append("Livrer un document \n-------------------\n"
        		+ "Déposer ici un fichier à livrer.(Copie)\n"
        		+ "Celui ci doit être NON versionné \nou versionné au format vX.X uniquement.\n\n"
        		+ "Incrémentation du versionning automatique.\n"
        		+ "Exemple : 2 versements du fichier exemple.doc = exemple_v1.0.doc \net exemple_v1.1.doc."
        		+ "\n-------------------\n");
        jtf2.append("Valider un document \n-------------------\n"
        		+ "Déposer ici un fichier à valider.\n(Déplacement)"
        		+ "\n-------------------\n");
        jtf3.append("Invalider un document \n-------------------\n"
        		+ "Déposer ici un fichier à invalider.\n(Déplacement)"
        		+ "\n-------------------\n");
        jtf4.append("Archiver un document \n-------------------\n"
        		+ "Déposer ici un fichier à archiver.\n(Déplacement)"
        		+ "\n-------------------\n");
        
        jtf.setEditable(false);
        jtf2.setEditable(false);
        jtf3.setEditable(false);
        jtf4.setEditable(false);
        
        jtf.setFont(jtf.getFont().deriveFont(15f));
        jtf2.setFont(jtf2.getFont().deriveFont(15f));
        jtf3.setFont(jtf3.getFont().deriveFont(15f));
        jtf4.setFont(jtf4.getFont().deriveFont(15f));
        
        Color gris = new Color(207,207,207);
        Color vert = new Color(0,227,151);
        Color rouge = new Color(225,151,151);
        Color bleu = new Color(121,121,255);
        
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
		                    	File checkFile = new File(paramsMap.get(livraison) + fileNewName);

		                    	while (checkFile.exists()) {
		                    		fileNewName = utils.versionIncr2(fileNewName);
		                    		checkFile = new File(paramsMap.get(livraison) + fileNewName);
		                    		nameModified = true;
		                    	}

		                        // Gestion du transfert du fichier
		                        fileTransfert.delivery(file, paramsMap.get(livraison), fileNewName);
		                        // Génération de la trace de gestion du fichier
		                        fileTrace.generateTrace(file, paramsMap.get(livraison), livraison, fileNewName);
		                    	
		                        // Information de l'utilisateur sur le mouvemnet du fichier
		                        jtf.append( message.deliveryValidation(file, paramsMap.get(livraison), fileNewName));

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
		                    fileTransfert.deliveryAndRemove(file, paramsMap.get(validation));
		                    // Génération de la trace de gestion du fichier
		                    fileTrace.generateTrace(file, paramsMap.get(validation), validation);
		                    // Information de l'utilisateur sur le mouvemnet du fichier
		                    jtf2.append( message.deliveryValidation(file, paramsMap.get(validation)) );
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
		                    fileTransfert.deliveryAndRemove(file, paramsMap.get(invalidation));
		                    // Génération de la trace de gestion du fichier
		                    fileTrace.generateTrace(file, paramsMap.get(invalidation), invalidation);
		                    // Information de l'utilisateur sur le mouvemnet du fichier
		                    jtf3.append( message.deliveryValidation(file, paramsMap.get(invalidation)) );
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
	                        fileTransfert.deliveryAndRemove(file, paramsMap.get(archivage), newFileName);
	                        // Génération de la trace de gestion du fichier
	                        fileTrace.generateTrace(file, paramsMap.get(archivage), archivage, newFileName);
	                        // Information de l'utilisateur sur le mouvemnet du fichier
	                        jtf4.append( message.deliveryValidation(file, paramsMap.get(archivage), newFileName) );
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
            	String livrer = JOptionPane.showInputDialog(frame,
                        "Où dois-je livrer ?", null);
            	livrer = utils.checkPath(livrer);
            	paramsMap.replace(livraison, livrer);
            	utils.saveParam(paramsMap);
            	
            }
        });
        
        m22.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent evt) {
        		String valider = JOptionPane.showInputDialog(frame,
        				"Où dois-je valider ?", null);
        		valider = utils.checkPath(valider);
            	paramsMap.replace(validation, valider);
            	utils.saveParam(paramsMap);
        	}
        });
        
        m33.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent evt) {
        		String invalider = JOptionPane.showInputDialog(frame,
        				"Où dois-je invalider ?", null);
        		invalider = utils.checkPath(invalider);
            	paramsMap.replace(invalidation, invalider);
            	utils.saveParam(paramsMap);
        	}
        });
        
        m44.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent evt) {
        		String archiver = JOptionPane.showInputDialog(frame,
        				"Où dois-je archiver ?", null);
        		archiver = utils.checkPath(archiver);
            	paramsMap.replace(archivage, archiver);
            	utils.saveParam(paramsMap);
        	}
        });
        
        cancel.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent evt) {
        		String actionCancelled = fileTrace.cancelLastAction();
        		
        		switch (actionCancelled)
        		{
        		  case "Livraison":
	    		    jtf.append(message.cancelValidation());
	    		    break;           
        		  case "Validation":
	    			  jtf2.append(message.cancelValidation());
	    			  break;           
        		  case "Invalidation":
        			  jtf3.append(message.cancelValidation());
        			  break;           
        		  case "Archivage":
        			  jtf4.append(message.cancelValidation());
        			  break;           
        		  default:
        			  JOptionPane.showMessageDialog(frame, "Acune action enregistrée");       
        		}
        		
        	}
        });

        

        frame.setSize(1200,400);
//        frame.setBounds( 100, 100, 100, 100 );
        frame.setDefaultCloseOperation( frame.EXIT_ON_CLOSE );
        frame.setVisible(true);
        if (planDeClassementVide) {
        	JOptionPane.showMessageDialog(frame, "ATTENTION : Plan de classement par défault chargé."); 
        }
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
