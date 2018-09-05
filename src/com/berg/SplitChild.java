package com.berg;

public class SplitChild {

    Integer start;
    Integer end;
    Integer pick;
    SplitChild one = null;
    SplitChild two = null;

    public SplitChild(Integer start, Integer end) {
        this.start = start;
        this.end = end;
        this.pick = 1 + start + SplitWay.r.nextInt(end - start);
    }

    public void populate() {
        if ((pick - 1) - start > 0) {
            one = new SplitChild(start, pick - 1);
        }
        if (end - pick > 0) {
            two = new SplitChild(pick, end);
        }
    }
}
