package ru.nsu.kudryavtsev.andrey.scoreUtils;

import java.util.Comparator;

public class ScoreComparator implements Comparator<ScoreNode>
{
    @Override
    public int compare(ScoreNode x, ScoreNode y) {
        if (x.getScore() < y.getScore()) {
            return 1;
        }
        if (x.getScore() > y.getScore()) {
            return -1;
        }
        return 0;
    }
}
