package model;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Enregistreur {


    public static final File savedir = new File(new File(System.getProperty("user.home")), ".marathon");



    public void enregistrerScore(List<Record> recordToSave){
        try {
            if(!savedir.exists())
                Files.createDirectory(savedir.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        Records records = new Records();
        records.records = recordToSave;

        ObjectOutputStream oos = null;
        final FileOutputStream fichier;
        try {
            fichier = new FileOutputStream(savedir + "/RecordsMarathon.csv" );
            oos = new ObjectOutputStream(fichier);
            oos.writeObject(records);
            oos.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (oos != null) {
                    oos.flush();
                    oos.close();
                }
            } catch (final IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private class Records implements Serializable{
        public List<Record> records;
    }
}


