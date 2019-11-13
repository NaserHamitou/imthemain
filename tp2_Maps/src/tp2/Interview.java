package tp2;

import java.util.ArrayList;
import java.util.Collection;

public class Interview {
    /**
     * Finds all pairs within values which sum up to targetSum
     * @param values All possible values that can form a pair (can contain duplicates)
     * @param targetSum Pairs should add up to this
     * @return A collection containing all valid pairs with no permutations, but all combinations (empty collection if none found)
     */
    public Collection<MatchingPair> matchingPairs(Collection<Integer> values, Integer targetSum) {

        //Initialisation de collection des MatchingPairs et HashMap
        Collection<MatchingPair> pairs = new ArrayList<MatchingPair>();
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>();


        //Verifie si element est dans hashmap
        for (Integer n: values) {

            Integer a = targetSum - n;

            if( map.containsKey(a.toString())){
                for(int i = 0; i < map.get(a.toString()); i++)
                    pairs.add(new MatchingPair(a,n));
            }

            if(map.containsKey(n.toString()))
                map.put(n.toString(), map.get(n.toString()));
            else
                map.put(n.toString(),1);
        }


        return pairs;

    }

}

