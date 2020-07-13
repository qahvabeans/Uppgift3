package mediacollector;

import java.io.IOException;
import java.util.Scanner;
/**
 * Todo:
 * 1. Snygga till listan.
 * 2. Ändra betyg till double, alternativt bara ta bort.
 * 3. Lägga in val av media-typ också.
 * 4. Förbättra felhantering då bokstäver läggs in i menyn istället för siffror.
 * 
 * @author Jonas
 */

public class MediaCollectionDriver {

    public static void main(String[] args) {
        Serier serieRegister = new Serier();
        MediaCollectionDriver meny = new MediaCollectionDriver();
        
    meny.setSerieRegister(serieRegister);

        try {
            serieRegister.readFromFile(MediaCollectionDriver.FILNAMN);
        } catch (IOException ex) {
            System.out.println("Filen kunde ej läsas, skapar en ny!");
            try {serieRegister.writeToFile(MediaCollectionDriver.FILNAMN);
            } catch (IOException ex2) {
            }
        }

        int menyVal = 0;
        while (menyVal != 5) {
            menyVal = meny.visaMeny();
            switch (menyVal) {
                case 1:
                    meny.visaSerier();
                    break;
                case 2:
                    meny.laggTillSerie();
                    break;

                case 3:
                    meny.uppdateraSerie();
                    break;
                case 4:
                    meny.taBortSerie();
                    break;
                case 5:
                    meny.avsluta();
                    break;
                default:
                    System.out.println("Ogiltigt val. Försök igen.");

            }

        }

    }
    
    private Serier serieRegister;
    private final Scanner inMatning;
    static String FILNAMN = "mediacollection.csv";

    public MediaCollectionDriver() {
    inMatning = new Scanner(System.in).useDelimiter("\\n");
    }

    public int visaMeny() {

        System.out.println("------ Meny ------\n");
        System.out.println("1.\tVisa serier");
        System.out.println("2.\tLägg till en ny serie");
        System.out.println("3.\tUppdatera befintlig serie");
        System.out.println("4.\tTa bort serie");
        System.out.println("5.\tAvsluta\n\n");
        System.out.print("Ange siffra som motvarar menyval: ");

        int valStr = inMatning.nextInt();

        return valStr;
    }

    public Serier getSerieRegister() {
        return serieRegister;
    }

    public void setSerieRegister(Serier serieRegister) {
        this.serieRegister = serieRegister;
    }

    public void visaSerier() {
        System.out.println("######################################");
        System.out.println("### Befintliga serier i registret ####");
        System.out.println("######################################");
        System.out.println("");
        serieRegister.skrivUtInfo();
        System.out.println("");
    }

    public void laggTillSerie() {
        System.out.println("######################################");
        System.out.println("######## Lägg till ny serie ##########");
        System.out.println("######################################");
        System.out.println("");
        System.out.print("Ange vilken tjänst serien finns på: ");
        String tjanst = inMatning.next();

        System.out.print("Ange seriens namn: ");
        String namn = inMatning.next();

        System.out.print("Ange ditt betyg på serien: ");
        String betyg = inMatning.next();

        Serie nySerie = new Serie();

        nySerie.setSerieTjanst(tjanst);
        nySerie.setSerieNamn(namn);
        nySerie.setSerieBetyg(betyg);
        serieRegister.addSerie(nySerie);

    }

    public void taBortSerie() {
        System.out.println("######################################");
        System.out.println("##### Ta bort serie ur registret #####");
        System.out.println("######################################");
        System.out.println("");
        serieRegister.skrivUtInfo();
        System.out.print("Ange numret för den serie du vill ta bort: ");
        int valdSerie = inMatning.nextInt();

        int antalSerier = serieRegister.getAntalSerier();

        if (valdSerie < antalSerier) {

            Serie serieSomSkaRaderas = serieRegister.getSerie(valdSerie);
            
            System.out.println("Vill du radera serien: "            
                    + serieSomSkaRaderas.getSerieNamn()
                    + "? Ange (J)a eller (N)ej");
            System.out.print("> ");
            String val = inMatning.next().toLowerCase();

            if (val.equals("j")) {
                serieRegister.removeSerie(valdSerie);
                System.out.println("Serien togs bort.");
            } else {
                System.out.println("Felaktig inmatning, åtgärden avbröts.");
            }
        } else {
            System.out.println("Felaktig inmatning, åtgärden avbröts.");
        }

    }
    public void uppdateraSerie() {
        System.out.println("######################################");
        System.out.println("#### Uppdatera serie i registret #####");
        System.out.println("######################################");
        System.out.println("");
        serieRegister.skrivUtInfo();
        System.out.print("Ange numret för den serien du vill ta uppdatera: ");
        int valdSerie = inMatning.nextInt();

        int antalSerier = serieRegister.getAntalSerier();

        if (valdSerie < antalSerier) {

            Serie serieSomSkaUppdateras = serieRegister.getSerie(valdSerie);
            System.out.println("Vill du uppdatera serien: "
                    + serieSomSkaUppdateras.getSerieNamn()
                    + "? Ange (J)a eller (N)ej");
            System.out.print("> ");
            String val = inMatning.next().toLowerCase();

            if (val.equals("j")) {
        
                System.out.print("Vad vill du uppdatera?");
                System.out.println("(T)jänst, (N)amn eller (B)etyg");
                System.out.print("> ");
                String val2 = inMatning.next().toLowerCase();
                if (val2.equals("t")) {
                System.out.println("Ange ny tjänst: ");
                System.out.print("> ");
                String tjanst = inMatning.next();
                serieSomSkaUppdateras.setSerieTjanst(tjanst);
                } else if (val2.equals("n")) {
                System.out.println("Ange nytt namn: ");
                System.out.print("> ");
                String namn = inMatning.next();
                serieSomSkaUppdateras.setSerieNamn(namn);
                } else if (val2.equals("b")) {
                System.out.println("Ange nytt betyg: ");
                System.out.print("> ");
                String betyg = inMatning.next();
                serieSomSkaUppdateras.setSerieBetyg(betyg);
                } else {
                    System.out.println("Felaktig inmatning.");
                }
            } else {
                System.out.println("Ingen uppdatering genomfördes");
            }
        } else {
            System.out.println("Felaktig inmatning, åtgärden avbröts!");
        }

    }
    public void avsluta() {
        System.out.println("######################################");
        System.out.println("######## Avslutar programmet #########");
        System.out.println("######################################");
        System.out.println("");
        try {
            serieRegister.writeToFile(MediaCollectionDriver.FILNAMN);
            System.out.println("Din MediaCollection sparades.");
        } catch (IOException ex) {
        }

    }
}
