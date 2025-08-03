// 代码生成时间: 2025-08-03 15:14:19
 * It showcases how to create a simple task that runs at fixed intervals.
 * The task in this example simply prints the current timestamp to the console.
 */

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ScheduledTask {

    // SimpleDateFormat for formatting the current timestamp
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    // Scheduled method to be executed at fixed intervals
    @Scheduled(fixedRate = 5000) // 5000 milliseconds (5 seconds)
    public void reportCurrentTime() {
        try {
            // Get the current timestamp and format it
            String currentTime = dateFormat.format(new Date());

            // Print the current timestamp to the console
            System.out.println("Current time: " + currentTime);
        } catch (Exception e) {
            // Handle any exceptions that might occur during the execution of the task
            System.err.println("An error occurred during the scheduled task: " + e.getMessage());
        }
    }

    // Main method to demonstrate the usage of the scheduled task
    public static void main(String[] args) {
        // Create an instance of the scheduled task scheduler
        ScheduledTask task = new ScheduledTask();

        // Start the scheduled task scheduler (this is a simple example and does not include actual scheduling logic)
        new Thread(task::reportCurrentTime).start();
    }
}
