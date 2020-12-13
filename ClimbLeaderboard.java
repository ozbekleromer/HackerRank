/*
Hackerrank Link: https://www.hackerrank.com/challenges/climbing-the-leaderboard/problem
*/

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class ClimbLeaderboard {

    /*
     * Complete the 'climbingLeaderboard' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY ranked
     *  2. INTEGER_ARRAY player
     */

    public static List<Integer> climbingLeaderboard(List<Integer> ranked, List<Integer> player) {
    // Write your code here
        List<Integer> ranks = new ArrayList<>(); 
        
        int rank = 1;
        boolean isAdded = false;
        int j=0;
        for(; j<ranked.size(); j++) {
            if(j != 0) {
                if(!ranked.get(j-1).equals(ranked.get(j))) rank++;
            }
            if(player.get(0) >= ranked.get(j)) {
                ranks.add(rank);
                isAdded = true;
                break;   
            }
        }
        if(!isAdded) ranks.add(++rank);
            
        j--;
        for(int i=1; i<player.size(); i++) {
            isAdded = false;
            if(player.get(i-1).equals(player.get(i))) {
                ranks.add(ranks.get(i-1));
                continue;
            }
            for(; j>=0; j--) {
                if(j == ranked.size()-1 || !ranked.get(j+1).equals(ranked.get(j))) rank--;
                if(player.get(i) < ranked.get(j)) {
                    ranks.add(++rank);
                    isAdded = true;
                    break;   
                }
            }
            if(!isAdded) ranks.add(rank);
        }
        
        return ranks;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int rankedCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> ranked = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        int playerCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> player = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        List<Integer> result = ClimbLeaderboard.climbingLeaderboard(ranked, player);

        bufferedWriter.write(
            result.stream()
                .map(Object::toString)
                .collect(joining("\n"))
            + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
