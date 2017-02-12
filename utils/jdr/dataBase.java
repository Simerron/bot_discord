package Test.bot.utils.jdr;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by Simerron on 03/02/2017.
 * Last edited by Simerron on 03/02/2017
 */
public class dataBase implements Serializable {
    public HashMap<String, feuillePersonnage> db = new HashMap<>();

    public void retrieveData() {
        //Récupérer les donnée sérialisé
    }
}
