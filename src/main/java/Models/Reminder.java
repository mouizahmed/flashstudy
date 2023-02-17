package Models;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Reminder {

    private Timer timer;
    private TimerTask task;
    private Date deadline;
    private long interval;
    private String message;

    public Reminder(Date deadline, long interval, String message) {
        this.deadline = deadline;
        this.interval = interval;
        this.message = message;
    }

    public void start() {
        timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                long remainingTime = deadline.getTime() - System.currentTimeMillis();
                if (remainingTime <= 0) {
                    System.out.println(message);
                    timer.cancel();
                } else if (remainingTime <= interval) {
                    System.out.println(message);
                }
            }
        };
        timer.scheduleAtFixedRate(task, 0, interval);
    }

    public void stop() {
        if (timer != null) {
            timer.cancel();
        }
    }
}
