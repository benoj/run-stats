package com.benoj.runningStats;

import com.benoj.runningStats.model.RaceResult;

import java.util.List;

public interface ResultsWriter {
    void write(List<RaceResult> results);
}
