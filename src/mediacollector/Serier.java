package mediacollector;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Serier {

    private final List<Serie> serier;

    public Serier() {
        serier = new ArrayList<>();
    }

    public void addSerie(Serie serie) {
        serier.add(serie);
    }

    public void removeSerie(int index) {
        serier.remove(index);

    }

    public Serie getSerie(int index) {
        return serier.get(index);
    }

    public List<Serie> getArrayList() {
        return serier;
    }

    public void readFromFile(String fileName) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader(fileName));
        String filRad;

        while ((filRad = in.readLine()) != null) {
            String[] post = filRad.split(",");
            Serie m = new Serie();
            m.setSerieTjanst(post[0]);
            m.setSerieNamn(post[1]);
            m.setSerieBetyg(post[2]);
            this.addSerie(m);
        }
        in.close();
    }

    public void writeToFile(String fileName) throws IOException {
        FileWriter fw = new FileWriter(fileName);
        BufferedWriter bw = new BufferedWriter(fw);
        for (int i = 0; i < serier.size(); i++) {

            Serie m = serier.get(i);
            String rad = m.getSerieTjanst() + "," + m.getSerieNamn() + "," + m.getSerieBetyg();
            bw.write(rad);
            bw.newLine();
        }
        bw.close();
    }
    
    public void skrivUtInfo() {
        System.out.println("Plats\tTjänst\t\t\tSeriens namn\t\tSeriens betyg");
        for (int i = 0; i < serier.size(); i++) {
            Serie m = serier.get(i);        
            String info = String.format("%-7s %-23s %-23s %s", i, m.getSerieTjanst(), m.getSerieNamn(), m.getSerieBetyg());
            System.out.println(info);
        }
    }

    public int getAntalSerier() {
        return serier.size();
    }
}
