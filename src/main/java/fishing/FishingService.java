package fishing;

import dataSets.FishDataSet;
import dataSets.UserDataSet;
import database.DBException;
import database.DataBaseService;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Демьян on 09.01.2017.
 */
public class FishingService {
    private Random random = new Random();
    private List<FishDataSet> fishData;

    public FishingService(DataBaseService dataBaseService) {
        try {
            fishData = dataBaseService.getFishList();
        } catch (DBException e){
            System.out.println("ошибка при создании листа рыб");
        }
    }

    public FishDataSet getFish(){
        int luck = random.nextInt(100);
        for(FishDataSet fish:fishData){
            if (fish.getChance() >= luck){
                return fish;
            }
        }
        return null;
    }

}
