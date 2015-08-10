package com.benoj.runningStats.factories;

import com.benoj.runningStats.model.RaceResult;

import java.util.Random;
import java.util.UUID;

public class RaceResultFactory {

    private static RaceResultFactory instance = null;

    private int position = 1;
    private Random random = new Random();

    private RaceResultFactory(){
    }

    public static RaceResultFactory aRaceResult(){
        if(instance == null){
            instance = new RaceResultFactory();
        }
        return instance;
    }

    private String randomString(){
       return UUID.randomUUID().toString();
    }
    public RaceResult create(){
        return new RaceResult(position,random.nextInt(),randomString(),"M",randomString(),randomString(),"Y","00:28:00");
    }
}
