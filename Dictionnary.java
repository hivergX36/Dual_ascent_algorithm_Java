import java.util.ArrayList;

public class Dictionnary {

    int Dict_size;
    ArrayList<ArrayList<pair>> Dictionnary;

    Dictionnary(int size) {
        Dictionnary = new ArrayList<ArrayList<pair>>(size);
        for (int i = 0; i < size; i++) {
            Dictionnary.add(new ArrayList<pair>());
        }   
        this.Dict_size = size;

    }

    int hash(String key) {
        int hashword = key.hashCode();
        System.out.println("Hash code for key \"" + key + "\": " + hashword);
        int index = hashword % this.Dict_size;
        System.out.println("Index for key \"" + key + "\": " + index);
        return index;
    }

    public void insert(String key, float value) {
        int index = hash(key);
        System.out.println("Inserting key: " + key + " at index: " + index);
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

    void display_dictionary() {
        for (int index = 0; index < Dictionnary.size(); index++) {
            if (Dictionnary.get(index) != null) {
                for (int j = 0; j < Dictionnary.get(index).size(); j++) {
                    pair p = Dictionnary.get(index).get(j);
                    System.out.println("Key: " + p.key + ", Value: " + p.value);
                }
            }

        }
    }

}
