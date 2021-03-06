package test.WordConversion;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    Queue<Step> queue = new LinkedList<>();
    int count = 0;

    public int solution(String begin, String target, String[] words) {
        if (!targetExists(target, words)) return 0;

        boolean[] discovered = new boolean[words.length];

        queue.add(new Step(begin, discovered));
        while (queue.size() != 0) {
            bfs(target, words);
        }

        return count;
    }

    private boolean targetExists(String target, String[] words) {
        boolean exists = false;
        for (String word : words) {
            if (target.equals(word)) {
                exists = true;
            }
        }

        return exists;
    }

    private void bfs(String target, String[] words) {
        count++;
        int size = queue.size();

        for (int idx = 0; idx < size; idx++) {
            Step cur = queue.remove();

            for (int word = 0; word < words.length; word++) {
                if (checkOneCharDifference(cur.word, words[word]) && !cur.discovered[word]) {
                    if(target.equals(words[word])) {
                        queue.clear();
                        return;
                    }
                    boolean[] newDiscovered = cur.discovered.clone();
                    newDiscovered[word] = true;
                    queue.add(new Step(words[word], newDiscovered));
                }
            }
        }

    }

    private boolean checkOneCharDifference(String str1, String str2) {
        int count = 0;
        for (int idx = 0; idx < str1.length(); idx++) {
            if (str1.charAt(idx) != str2.charAt(idx)) {
                count++;
            }
        }

        return (count == 1);
    }
}

class Step {
    public String word;
    public boolean[] discovered;

    public Step(String word, boolean[] discovered) {
        this.word = word;
        this.discovered = discovered;
    }
}
