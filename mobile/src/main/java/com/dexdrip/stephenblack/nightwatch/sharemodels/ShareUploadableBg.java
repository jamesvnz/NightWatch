package com.dexdrip.stephenblack.nightwatch.sharemodels;

/**
 * Created by stephenblack on 8/10/15.
 */
public interface ShareUploadableBg {
    int getMgdlValue();
    long getEpochTimestamp(); //in milliseconds
    int getSlopeOrdinal();

    //Ordinals:
    //  0 - NONE
    //  1 - DOUBLE_UP
    //  2 - SINGLE_UP
    //  3 - UP_45
    //  4 - FLAT
    //  5 - DOWN_45
    //  6 - SINGLE_DOWN
    //  7 - DOUBLE_DOWN
    //  8 - NOT_COMPUTABLE
    //  9 - OUT_OF_RANGE
}
