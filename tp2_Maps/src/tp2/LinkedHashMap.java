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
    private void rehash() { // Verifier size++ dans put et autre

            Node<KeyType, DataType>[] tempMap = map;
            map = new Node[capacity* CAPACITY_INCREASE_FACTOR];
            capacity *= CAPACITY_INCREASE_FACTOR;
            size = 0;

             for(int i = 0; i < tempMap.length; i++){
                for(Node<KeyType,DataType> n = tempMap[i]; n != null; n = n.next)
                    put(n.key,n.data);

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

        for(Node n = map[getIndex(key)]; n != null; n = n.next) {
            if(n.key.equals(key))
                return true;
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

            for (Node n = map[getIndex(key)]; n != null; n = n.next) {
                if (n.key.equals(key)) {
                    return (DataType) n.data;
                }
            }
        }
        return  null;

    }

    /** TODO
     * Assigns a value to a key
     * @param key Key which will have its value assigned or reassigned
     * @return Old DataType instance at key (null if none existed)
     */
    public DataType put(KeyType key, DataType value) { //Looks like it's working

        int index = getIndex(key);

        //Verifier si map est vide et ajouter le node
        if(isEmpty() == true){
            map[index] = new Node<KeyType,DataType>(key,value);
            size++;
            return null;
        }

        DataType oldValue;

        //Trouver l'index correspondant et le node qui contient la bonne key  (REFAIRE LA FACON DE CHERCHER PLUS CLEAN)
        for(int i = 0; i < map.length; i++) {
            if (map[i] != null) {
                if (getIndex(map[i].key) == getIndex(key)) {
                    for (Node n = map[getIndex(key)]; n != null; n = n.next) {
                        if (n.key.equals(key)) {       //Reassign date
                            oldValue = (DataType) n.data;
                            n.data = value;
                            return oldValue;
                        }

                        //Ajouter un nouveau node pour un meme index
                        else if (n.next == null) {
                            if(shouldRehash())
                                rehash();
                            n.next = new Node<KeyType, DataType>(key, value);
                            return null;
                        }
                    }
                }
                //Assigner un node a un index vide
                else {
                    if(shouldRehash()) {
                        rehash();
                        put(key,value);
                        size++;
                        return null;
                    }

                    map[getIndex(key)] = new Node<KeyType, DataType>(key, value);
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

        for(Node n = map[getIndex(key)]; n != null; n = n.next ) {
            if(n.key.equals(key)) {
                oldData = (DataType) n.data;
                n = n.next;
                size--;
                return oldData;
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


