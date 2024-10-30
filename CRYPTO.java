import javax.crypto.*;
import java.security.*;
import java.util.*;
import javax.crypto.spec.*;
import java.io.*;
import java.io.IOException;



public class CRYPTO {
    

    public static String Genert(){
        String base64EncodedKey = null ;
        while(base64EncodedKey == null) {
            try{

                KeyGenerator keyGen = KeyGenerator.getInstance("AES");
                keyGen.init(128);
                SecretKey secretKey = keyGen.generateKey();
                byte[] encodedKey = secretKey.getEncoded();
                base64EncodedKey = java.util.Base64.getEncoder().encodeToString(encodedKey);
        }catch(NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        }  
        return base64EncodedKey ;
    } 

    public static void encryptFile(String inputFile, String outputFile , String SECRET_KEY ) throws Exception {
               
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

            FileInputStream inputStream = new FileInputStream(inputFile);
            byte[] inputBytes = new byte[(int) new File(inputFile).length()];
            inputStream.read(inputBytes);

            byte[] encryptedBytes = cipher.doFinal(inputBytes);

            FileOutputStream outputStream = new FileOutputStream(outputFile);
            outputStream.write(encryptedBytes);

            inputStream.close();
            outputStream.close();
            System.out.println();

            System.out.println("LE FICHIER EST CHEFREE ");
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException |
                IllegalBlockSizeException | BadPaddingException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void decryptFile(String inputFile, String outputFile , String SECRET_KEY) throws Exception {
        
        
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);

            FileInputStream inputStream = new FileInputStream(inputFile);
            byte[] inputBytes = new byte[(int) new File(inputFile).length()];
            inputStream.read(inputBytes);

            byte[] decryptedBytes = cipher.doFinal(inputBytes);

            FileOutputStream outputStream = new FileOutputStream(outputFile);
            outputStream.write(decryptedBytes);

            inputStream.close();
            outputStream.close();
            System.out.println();

            System.out.println("LE FICHIER EST DECHEFREE ");
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException |
                IllegalBlockSizeException | BadPaddingException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        

        int valeur ;
        System.out.println("  -1- POUR CREE UN FICHIR                    :  \n  -2- POUR DECHEFREE UN FICHIER DEJA CHEFREE :  \n  -3- POUR CHEFREE UN FICHIER DEJA EXISTEE   : ");
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.println("------------------ VOTRE CHOIX ---------------------------");
        valeur  = scanner.nextInt();
        System.out.println("----------------------------------------------------------");
        while(valeur !=1  && valeur !=2 ){
            
            if(valeur!=3){
                System.out.println();
                System.out.print("SVP CHOISIS :  1 , 2 OU 3   ");
                System.out.println();
                System.out.println("------------------ VOTRE CHOIX ---------------------------");
                valeur = scanner.nextInt();
                System.out.println("----------------------------------------------------------");
                System.out.println();
            }else break  ;   

        }

        System.out.println();

        switch (valeur) {
        case 1 :
            //int cpt = 1 ;
            String chaine ;
            String nomfichier ;
            Scanner scr = new Scanner(System.in);
            System.out.println("-------------------LE NOM DE FICHIER  : ------------------ ");
            System.out.println();
            nomfichier = scr.nextLine();
            String mots = nomfichier.concat(".txt");
            String SECRETKEY = Genert();

            File FICHIERPRINCIPALE = new File("SECRETCODE.txt");
            FileWriter sc = new FileWriter(FICHIERPRINCIPALE,true);
            PrintWriter psc = new PrintWriter(sc) ;
            psc.println(mots+": "+SECRETKEY);
            psc.close(); 

            File fichier = new File(mots);
            FileWriter fw = new FileWriter(fichier);
            PrintWriter pw = new PrintWriter(fw);
            System.out.println();
            System.out.println("----------------------ENTREZ VOTRE CHAINE  : ------------- ");
            System.out.println();
            chaine = scr.nextLine();
            System.out.println();
            System.out.println("-----------------------------------------------------------");
            System.out.println();

            System.out.println("  -1- POUR CHEFREE UN FICHIER , SINON CLICK ENTREZ        :");
            System.out.println();
            Scanner aa = new Scanner(System.in);
            int b = aa.nextInt();
            if(b == 1 ){
                pw.print(chaine);
                pw.close();

                String inputFile = mots;
                String encryptedFile =nomfichier+"_encrypted.txt";               
                encryptFile(inputFile, encryptedFile,SECRETKEY);
                fichier.delete();
            }else{
                pw.print(chaine);
                pw.close();
            } 

            break ;
        case 2 :
            
            
            String folderPath = "D:\\anass";
            File folder = new File(folderPath);
            if (folder.isDirectory()){
                String[] files = folder.list();
                if (files != null){
                    for (String fileName : files){
                        if(fileName.endsWith("_encrypted.txt")){
                            System.out.println();
                            System.out.println(" FOLDERS : -----------------------------------------------");
                            System.out.println(fileName);
                            System.out.println();
                            Scanner scan = new Scanner(System.in);
                            System.out.println();
                            System.out.println("---------------------------------------------------------");
                            System.out.println(" LE NOM DE VOTRE FICHIER : ");
                            System.out.println();
                            String NameFolder = scan.nextLine();
                            System.out.println();
                            System.out.print(" SVP ENTREZ LA CLE DE DECHEFREMENT  : ");
                            String KEY = scan.nextLine();
                            String encryptedFile =NameFolder+"_encrypted.txt";

                            String decryptedFile = NameFolder +"_decrypted.txt";
                            decryptFile(encryptedFile, decryptedFile,KEY);
                        }
                    }
                }else{
                    System.out.println("LE DOSSIER EST VIDE .");
                }
            }else {
                System.out.println("LE CHEMIN ES INCORRECT.");
            }
            
            break ;     
         

        case 3 :
            
            
            String folderrPath = "D:\\anass";
            File folderr = new File(folderrPath);
            if (folderr.isDirectory()){
                String[] filess = folderr.list();
                if (filess != null){
                    for (String fileNamee : filess){
                        if(fileNamee.endsWith(".txt") && !fileNamee.contains("encrypted") && !fileNamee.contains("decrypted") && !fileNamee.contains("SECRETCODE")){
                            System.out.println(" --------------------------FICHIERS ------------------------------");
                            System.out.println();
                            System.out.println(fileNamee);
                            System.out.println();
                            System.out.println("-----------------------------------------------------------------");
                            System.out.println("ENTREZ LE NOM DE VOTRE FICHIER : ");
                            System.out.println();
                            Scanner Sccn = new Scanner(System.in);
                            String oldname = Sccn.nextLine();
                            String SSECRETKEY = Genert();
                            File fichierr = new File(oldname);
                            File FFICHIERPRINCIPALE = new File("SECRETCODE.txt");
                            FileWriter sssc = new FileWriter(FFICHIERPRINCIPALE,true);
                            PrintWriter ppsc = new PrintWriter(sssc) ;
                            ppsc.println(oldname+": "+SSECRETKEY);
                            ppsc.close();
                            String newname = oldname.replace(".txt","");

                            String iinputFile = oldname;
                            String eencryptedFile =newname+"_encrypted.txt";
                            encryptFile(iinputFile, eencryptedFile,SSECRETKEY);
                             fichierr.delete();
                        }
                    }
                }
            }else{
                System.out.println(" LE CHEMIN EST INCCORECT ");
            }
            break;
            
        }
    }
}

