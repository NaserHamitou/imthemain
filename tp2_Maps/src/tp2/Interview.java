package tp2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Interview {
    /**
     * Finds all pairs within values which sum up to targetSum
     * @param values All possible values that can form a pair (can contain duplicates)
     * @param targetSum Pairs should add up to this
     * @return A collection containing all valid pairs with no permutations, but all combinations (empty collection if none found)
     */
    public Collection<MatchingPair> matchingPairs(Collection<Integer> values, Integer targetSum) {

        //Initialisation de collection des MatchingPairs
        Collection<MatchingPair> pairs = new ArrayList<MatchingPair>();


        //--------------------------  Comment utiliser hashmap ?  --------------------------------//


        //Iterateur
        Iterator<Integer> it = values.iterator();

        if(!values.isEmpty()) {

           while (it.hasNext()){

           }

        }

        return pairs;

        //Code non utiliser
        /*
        while (it1.hasNext()) {
                it2 = it1;
                int val1 = it1.next();
                while (it2.hasNext()) {
                    int val2 = it2.next();
                    if( val1 + val2 == targetSum )
                        pairs.add(new MatchingPair(val1, val2));
                }
            }
        */
        /////////////////////////////////////////////////
        /*
        while (it.hasNext()){
            int val1 = it.next();
            int val2 = it.next();

            if(val1 + val2 == targetSum){
                pairs.add(new MatchingPair(val1, val2));
                return pairs;
            }


        }*/
    }


    //Node Definition
    static class Node<Value1, Value2> {
        final Value1 key;
        Value2 data;
        LinkedHashMap.Node next; // Pointer to the next node within a Linked List

        Node(Value1 key, Value2 data)
        {
            this.key = key;
            this.data = data;
            next = null;
        }
    }

}

