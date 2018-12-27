package edu.cmich.cmuwatch;

import android.util.Log;
import java.util.Calendar;
import java.util.Date;

public class GameCalendar{
    private Calendar currentCalendar;
    private static final int CURRENT_YEAR = 2019 - 1900;

    private String countdownTime = null;

    private static final Date[] DATES = {
            new Date(CURRENT_YEAR, Calendar.AUGUST, 31, 19, 0),
            new Date(CURRENT_YEAR, Calendar.SEPTEMBER, 9, 16, 0),
            new Date(CURRENT_YEAR, Calendar.SEPTEMBER, 16, 15, 30),
            new Date(CURRENT_YEAR, Calendar.SEPTEMBER, 23),
            new Date(CURRENT_YEAR, Calendar.SEPTEMBER, 30),
            new Date(CURRENT_YEAR, Calendar.OCTOBER, 7),
            new Date(CURRENT_YEAR, Calendar.OCTOBER, 14, 15, 30),
            new Date(CURRENT_YEAR, Calendar.OCTOBER, 21, 15, 0),
            new Date(CURRENT_YEAR, Calendar.NOVEMBER, 1, 20, 0),
            new Date(CURRENT_YEAR, Calendar.NOVEMBER, 8, 20, 0),
            new Date(CURRENT_YEAR, Calendar.NOVEMBER, 14),
            new Date(CURRENT_YEAR, Calendar.NOVEMBER, 24)

    };

    public GameCalendar(Calendar currentCal){
        this.currentCalendar = currentCal;
    }

    private static Calendar getGameCalendar(int gameNumber){
        Calendar gameCalendar = Calendar.getInstance();

        gameCalendar.setTime(DATES[gameNumber]);

        return gameCalendar;
    }

    private void getGameNumber() {
        int gameNumber = -1;
        Calendar gameCal;

        do{
            gameCal = GameCalendar.getGameCalendar(++gameNumber);
            currentCalendar.setTimeInMillis(System.currentTimeMillis());

        }while(currentCalendar.compareTo(gameCal) > 0);

        getTimeToDisplay(gameNumber);
    }

    private void getTimeToDisplay(int gameNumber) {
        Calendar nextGame = GameCalendar.getGameCalendar(gameNumber);
        int min = 0, hour = 0, day = 0;

        min = nextGame.get(Calendar.MINUTE) + 59 - currentCalendar.get(Calendar.MINUTE);
        hour = nextGame.get(Calendar.HOUR_OF_DAY) - 1 - currentCalendar.get(Calendar.HOUR_OF_DAY);
        day = nextGame.get(Calendar.DAY_OF_YEAR) - currentCalendar.get(Calendar.DAY_OF_YEAR);

        Log.d("Game", Integer.toString(nextGame.get(Calendar.DAY_OF_YEAR)));
        Log.d("Current", Integer.toString(currentCalendar.get(Calendar.DAY_OF_YEAR)));

        if (day == 1) {
            countdownTime = "Tomorrow is Game Day";
        } else if (day > 0){
            countdownTime = day + " days until Game Day";
        } else if (day == 0){
            //GAMEDAY
            if (hour > 0) {
                countdownTime = hour + "hr " + min + "min until Kickoff";
            } else if (min > 0) {
                countdownTime = min + "min until Kickoff";
            } else {
                countdownTime = "FIRE UP CHIPS!";
            }
        }
    }

    public String getCountdownTime() {
        getGameNumber();
        return countdownTime;
    }
}
