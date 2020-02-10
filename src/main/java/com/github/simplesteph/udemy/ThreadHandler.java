package com.github.simplesteph.udemy;
//
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ThreadHandler {
    public static List<String> activeThreads = new ArrayList<>();

    public String manageThread() throws InterruptedException {
        String threadId = getAlphaNumericString(10);
        activeThreads.add(threadId);
        Instant start = Instant.now();
        System.out.println(activeThreads.size());
        if(activeThreads.size()<=5){
            Thread.sleep(1000L);
        }else{
            Thread.sleep(1000L*activeThreads.size());
        }
        Instant end = Instant.now();
        activeThreads.remove(threadId);
        return Duration.between(start, end).toString();
    }

    private String getAlphaNumericString(int n)
    {
        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";
        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());
            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }
        return sb.toString();
    }

}
