import java.util.ArrayList;

public class Dictionnary {

    ArrayList<ArrayList<pair>> Dictionnary;

    Dictionnary(int size) {
        Dictionnary = new ArrayList<ArrayList<pair>>(size);

    }

    int hash(String key) {
        int hashword = key.hashCode();
        int index = hashword % Dictionnary.size();
        return index;
    }

    public void insert(String key, float value) {
        int index = hash(key);
        pair newpair = new pair(key, value);
        if (Dictionnary.get(index) == null) {
            Dictionnary.set(index, new ArrayList<pair>());
        }
        for (int i = 0; i < Dictionnary.get(index).size(); i++) {
            if (Dictionnary.get(index).get(i).key.equals(key)) {
                Dictionnary.get(index).get(i).value = value;
                System.out.println("Key already exists. Value updated.");
                return;
            }
        }

        Dictionnary.get(index).add(newpair);

    }

    public void remove(String key) {
        int index = hash(key);
        if (Dictionnary.get(index) != null) {
            for (int i = 0; i < Dictionnary.get(index).size(); i++) {
                if (Dictionnary.get(index).get(i).key.equals(key)) {
                    Dictionnary.get(index).remove(i);
                    System.out.println("Key removed.");
                    return;
                }
            }
        }

    }

    public float get(String key) {
        int index = hash(key);
        if (Dictionnary.get(index) != null) {
            for (int i = 0; i < Dictionnary.get(index).size(); i++) {
                if (Dictionnary.get(index).get(i).key.equals(key)) {
                    return Dictionnary.get(index).get(i).value;
                }
            }
        }
        System.out.println("Key not found.");
        return -1;

    }

}
