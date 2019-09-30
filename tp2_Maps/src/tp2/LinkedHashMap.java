package tp2;

public class LinkedHashMap<KeyType, DataType> {

    private static final double COMPRESSION_FACTOR = 2; // 50%
    private static final int DEFAULT_CAPACITY = 20;
    private static final int CAPACITY_INCREASE_FACTOR = 2;

    private Node<KeyType, DataType>[] map;
    private int capacity; //n dans lexemple
    private int size = 0;

    public LinkedHashMap() {
        capacity = DEFAULT_CAPACITY;
        map = new Node[DEFAULT_CAPACITY];  //"tableau" de node
    }

    public LinkedHashMap(int capacity) {
        this.capacity = capacity;
        map = new Node[capacity];
    }

    /**
     * Finds the index attached to a particular key
     * @param key Value used to access to a particular instance of a DataType within map
     * @return The index value attached to a particular key
     */
    private int getIndex(KeyType key){
        int keyHash = key.hashCode() % capacity;
        return keyHash < 0 ? -keyHash : keyHash;
    }

    private boolean shouldRehash() {
        return size * COMPRESSION_FACTOR > capacity;
    }

    /** TODO
     * Increases capacity by CAPACITY_INCREASE_FACTOR (multiplication) and
     * reassigns all contained values within the new map
     */
    private void rehash() {

        if(shouldRehash()){
            Node<KeyType, DataType>[] tempMap = new Node[capacity * CAPACITY_INCREASE_FACTOR];
            capacity *= CAPACITY_INCREASE_FACTOR;

            for(int i = 0;i < tempMap.length; i++)
                tempMap[i] = null;

            for(int j = 0; j < map.length; j++){
                tempMap[j] = map[j];
            }

            map = tempMap;
        }
    }

    public int size() {
        return size;
    }

    public int getCapacity(){
        return capacity;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /** TODO
     * Finds if map contains a key
     * @param key Key which we want to know if exists within map
     * @return if key is already used in map
     */
    public boolean containsKey(KeyType key) {

        for(int i = 0; i < map.length; i++){   //Might want to check also the nodes in the same index (in the linkedlist) (USE NEXT() FONCTION)
            if(map[i] != null) {
                if (map[i].key == key)
                    return true;
            }
        }

        return false;
    }

    /** TODO
     * Finds the value attached to a key
     * @param key Key which we want to have its value
     * @return DataType instance attached to key (null if not found)
     */
    public DataType get(KeyType key) { //meme principe qu'en haut mais il faut retourne le datetype a la place dun bool

        if(!isEmpty()) {

            for (int i = 0; i < map.length; i++) {
                if (map[i] != null) {
                    if (getIndex(map[i].key) == getIndex(key)) {
                        for (Node n = map[i]; n != null; n = n.next) {
                            String a = (String) n.key;
                            String b = (String) key;
                            if (a == b) {      //it ignores this ??
                                System.out.println(n.data);
                                System.out.println(n.key);
                                return (DataType) n.data;
                            }
                        }
                    }
                }
            }

        }
        System.out.println("NOPE");
        return  null;

    }

    /** TODO
     * Assigns a value to a key
     * @param key Key which will have its value assigned or reassigned
     * @return Old DataType instance at key (null if none existed)
     */
    public DataType put(KeyType key, DataType value) { //Looks like it's working

        //Verifier si map est vide et ajouter le node
        if(isEmpty() == true){
            map[getIndex(key)] = new Node<KeyType,DataType>(key,value);
            size++;
            System.out.println(map[getIndex(key)].data);
            System.out.println(map[getIndex(key)].key);
            System.out.println("INDEX : " + getIndex(key));
            return null;
        }

        DataType oldValue;

        //Trouver l'index correspondant et le node qui contient la bonne key
        for(int i = 0; i < map.length; i++) {
            if (map[i] != null) {
                if (getIndex(map[i].key) == getIndex(key)) {
                    for (Node n = map[i]; n != null; n = n.next) {
                        if (n.key == key) {
                            oldValue = (DataType) n.data;
                            n.data = value;
                            System.out.println(n.data);
                            System.out.println(map[getIndex(key)].key);
                            System.out.println("INDEX : " + getIndex(key));
                            return oldValue;
                        }

                        //Ajouter un nouveau node pour un meme index
                        else if (n.next == null) {
                            map[getIndex(key)].next = new Node<KeyType, DataType>(key, value);
                            System.out.println("YEET");
                            return null;
                        }
                    }
                }
                //Assigner un node a un index vide
                else {
                    map[getIndex(key)] = new Node<KeyType, DataType>(key, value);
                    System.out.println(map[getIndex(key)].data);
                    System.out.println(map[getIndex(key)].key);
                    System.out.println("INDEX : " + getIndex(key));
                    size++;
                    return null;
                }
            }
        }

        return null;
    }

    /** TODO
     * Removes the node attached to a key
     * @param key Key which is contained in the node to remove
     * @return Old DataType instance at key (null if none existed)
     */
    public DataType remove(KeyType key) {

        DataType oldData;

        for(int i = 0; i < map.length; i++){
            if(map[i] != null) {
                if (map[i].key == key) {
                    oldData = map[i].data;
                    map[i] = null;
                    size--;
                    return oldData;
                }
            }
        }
        return null;
    }

    /** TODO
     * Removes all nodes contained within the map
     */
    public void clear() {
        for(int i = 0; i < map.length; i++)
            map[i] = null;
        size = 0;
    }


    static class Node<KeyType, DataType> {
        final KeyType key;
        DataType data;
        Node next; // Pointer to the next node within a Linked List

        Node(KeyType key, DataType data)
        {
            this.key = key;
            this.data = data;
            next = null;
        }
    }
}

