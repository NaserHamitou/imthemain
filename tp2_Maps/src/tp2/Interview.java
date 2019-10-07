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

        //Iterateur
        Iterator<Integer> it1 = values.iterator();
        Iterator<Integer> it2 = values.iterator();

        if(!values.isEmpty()) { //Verifie qu'il y a des valeurs a match

            while (it1.hasNext()) {
                it2 = it1;
                int val1 = it1.next();
                while (it2.hasNext()) {
                    int val2 = it2.next();
                    if( val1 + val2 == targetSum )
                        pairs.add(new MatchingPair(val1, val2));
                }
            }
        }

        return pairs;
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

}

