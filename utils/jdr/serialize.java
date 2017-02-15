package Test.bot.utils.jdr;

import java.io.*;

/**
 * Created by Simerron on 12/02/2017.
 * Last edited by Simerron on 12/02/2017
 */
public class serialize {
    public void save(dataBase obj) {
        ObjectOutputStream oos;
        try {
            oos = new ObjectOutputStream(
                    new BufferedOutputStream(
                            new FileOutputStream(
                                    new File("personnages.db"))));
            //Nous allons écrire chaque objet Game dans le fichier
            oos.writeObject(obj);
            //Ne pas oublier de fermer le flux !
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return;
    }

    public dataBase retreive() {
        ObjectInputStream ois;
        dataBase response;
        //On récupère maintenant les données !
        try {
            ois = new ObjectInputStream(
                    new BufferedInputStream(
                            new FileInputStream(
                                    new File("personnages.db"))));
            response = (dataBase) ois.readObject();
            ois.close();
            return response;
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
            return null;
        }

    }
}
