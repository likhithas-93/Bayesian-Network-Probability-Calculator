import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class bnet {
    public static char tableValues[] = {'B', 'E', 'A', 'J', 'M'};

    public static void main(String[] args) {
        if (args.length < 1 || args.length > 6) {
            System.out.println("Invalid number of arguments");
            System.exit(0);
        }
        Boolean isGiven = false;
        Map<Character, ArrayList<Boolean>> numerator = new HashMap<Character, ArrayList<Boolean>>();
        Map<Character, ArrayList<Boolean>> possibilities = new HashMap<Character, ArrayList<Boolean>>();
        for (int i = 0; i < args.length; i++) {
            ArrayList<Boolean> probableVal = new ArrayList<Boolean>();
            if (args[i].equals("given")) {
                isGiven = true;
                continue;
            }
            if (args[i].charAt(1) == 't') {
                probableVal.add(true);
            } else {
                probableVal.add(false);
            }
            if (isGiven == false) {
                possibilities.put(args[i].charAt(0), probableVal);

            } else {
                numerator.put(args[i].charAt(0), probableVal);
            }
        }
        /*
        Add all the truthTable values of numerator to the Possible Values.
        And also any missing truthTable values of {'B', 'E', 'A', 'J', 'M'}  to the Possible Values
         */
        possibilities.putAll(numerator);
        possibilities.putAll(addMissingTruthTablesToGivenNetwork(possibilities));
        numerator.putAll(addMissingTruthTablesToGivenNetwork(numerator));
        double numeratorAns = 0.0;
        double denominatorAns = 0.0;
        /*
        If there's no 'given' in the input String. Compute only numerator
         */
        BayesianNetwork bn = new BayesianNetwork();
        for (int p = 0; p < possibilities.get(tableValues[0]).size(); p++) {
            for (int q = 0; q < possibilities.get(tableValues[1]).size(); q++) {
                for (int r = 0; r < possibilities.get(tableValues[2]).size(); r++) {
                    for (int s = 0; s < possibilities.get(tableValues[3]).size(); s++) {
                        for (int t = 0; t < possibilities.get(tableValues[4]).size(); t++) {
                            /*
                            Computing the probabilities each Possibility with their truth table values;
                            i.e possibilities.get('B', 'E', 'A', 'J', 'M').get(truthtableValue)
                             */
                            numeratorAns += bn.computeProbability(possibilities.get(tableValues[0]).get(p), possibilities.get(tableValues[1]).get(q), possibilities.get(tableValues[2]).get(r), possibilities.get(tableValues[3]).get(s), possibilities.get(tableValues[4]).get(t));
                        }
                    }
                }
            }
        }
        if (isGiven) {
            for (int i = 0; i < numerator.get(tableValues[0]).size(); i++) {
                for (int j = 0; j < numerator.get(tableValues[1]).size(); j++) {
                    for (int k = 0; k < numerator.get(tableValues[2]).size(); k++) {
                        for (int l = 0; l < numerator.get(tableValues[3]).size(); l++) {
                            for (int m = 0; m < numerator.get(tableValues[4]).size(); m++) {
                                denominatorAns += bn.computeProbability(numerator.get(tableValues[0]).get(i), numerator.get(tableValues[1]).get(j), numerator.get(tableValues[2]).get(k), numerator.get(tableValues[3]).get(l), numerator.get(tableValues[4]).get(m));
                            }
                        }
                    }
                }
            }
            /*
            Prob is input contains 'given'
            */
            numeratorAns = numeratorAns / denominatorAns;
        }

        for (int s = 0; s < args.length; s++) {
            System.out.print(args[s] + " ");
        }
        System.out.println("\nProbability = " + numeratorAns);
    }

    public static Map<Character, ArrayList<Boolean>> addMissingTruthTablesToGivenNetwork(Map<Character, ArrayList<Boolean>> currentNetwork) {
        Map<Character, ArrayList<Boolean>> totalNetwork = new HashMap<Character, ArrayList<Boolean>>();
        ArrayList<Boolean> truthTable = new ArrayList<Boolean>();
        truthTable.add(true);
        truthTable.add(false);
        for (char ch : tableValues) {
            /*
            If the current given network does not have any of the truthTable values of {'B', 'E', 'A', 'J', 'M'}
            then, Add the respective TruthTable
             */
            if (!(currentNetwork.containsKey(ch))) {
                totalNetwork.put(ch, truthTable);
            }
        }
        return totalNetwork;
    }
}