package com.benoj.runningStats;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class RunningStatsApplication extends Application<RunningStatsConfiguration>{
    public static void main(final String[] args) throws Exception {
        new RunningStatsApplication().run(args);
    }

    @Override
    public void run(final RunningStatsConfiguration configuration, final Environment environment) throws Exception {

    }
}
