package Test.bot.utils.jdr;

import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Simerron on 01/02/2017.
 * Last edited by Simerron on 01/02/2017
 */
public class feuillePersonnage implements Serializable {
    private User player;
    private String name;
    private HashMap<String, Integer> stats = new HashMap<>() ;
    private int lifePoints, manaPoints;
    private HashMap<String, Integer> comp = new HashMap<>();
    private HashMap<String, Integer> inv = new HashMap<>();
    private long exp;
    private String race;
    private int money[] = new int[3];

    public long getExp() {return exp;}
    public String getRace() {return race;}
    public int getPo() {return money[0];}
    public int getPa() {return money[1];}
    public int getPc() {return money[2];}

    public feuillePersonnage(String name, MessageReceivedEvent e) {
        setName(name);
        this.player = e.getAuthor();
    }

//Les Méthodes GET
    public User getPlayer() {return player;}
    private String getName() {return name;}
    private int getLifePoint() {return lifePoints;}
    private int getManaPoints() {return manaPoints;}
    private List<String> getStatNames() {return new ArrayList<String>(this.stats.keySet());}
    private int getStatValue(String carac) {return this.stats.get(carac);}
    private List<String> getCompNames() {return new ArrayList<String>(comp.keySet());}
    private int getCompValue(String comp) {return this.comp.get(comp);}
    private List<String> getInvNames() {return new ArrayList<String>(inv.keySet());}
    private int getInvNb(String obj) {return this.inv.get(obj);}


//Les Méthodes SET
    public void setName(String name) {this.name = name;}
    public void setExp(long exp) {this.exp = exp;}
    public void setRace(String race) {this.race = race;}
    public void setMoney(int[] money) {this.money = money;}
    public void setLifePoints(int lifePoints) {this.lifePoints = lifePoints;}
    public void setManaPoints(int manaPoints) {this.manaPoints = manaPoints;}
    public void setStats(String carac, Integer valeur) {
        if (this.stats.containsKey(carac)){this.stats.replace(carac, valeur);}
        else {this.stats.put(carac, valeur);}}
    public void setComp(String comp, Integer valeur) {
        if (this.comp.containsKey(comp)){this.comp.replace(comp, valeur);}
        else {this.comp.put(comp, valeur);}}
    public void setInv(String obj, Integer valeur) {
        if (this.inv.containsKey(obj)){this.inv.replace(obj, valeur);}
        else {this.inv.put(obj, valeur);}
    }

//Les Méthodes d'écritures
    public String getHeader(){
    return getName() + " " + getRace() +"\n"+
            "Pv : "+getLifePoint() + "  Pm : " + getManaPoints() +"\n"+
            "Exp : " + getExp() + "\n";}

    public  String getMoney(){
        return "Po : "+getPo() + " Pa : "+getPa() + " Pc : "+getPc()+"\n";
    }
    public String getStats () {
        String stats = "";
        for (String carac : getStatNames()){
            stats += carac + " : " + getStatValue(carac) + "\n";}return stats;}

    public String getComps () {
        String strComp = "";
        for (String comp : getCompNames()){
            strComp += comp + " : " + getCompValue(comp) + "\n";}return strComp;}

    public String getInv () {
        String inv = "";
        for (String obj : getInvNames()){
            inv += obj + " : " + getInvNb(obj) + "\n";}return inv;}
}
