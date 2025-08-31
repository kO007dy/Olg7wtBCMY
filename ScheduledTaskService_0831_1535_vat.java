// 代码生成时间: 2025-08-31 15:35:46
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.Date;

// Enable Scheduling for this class
@EnableScheduling
@Service
public class ScheduledTaskService {
    
    // Define a scheduled task annotated with @Scheduled
    // The cron expression specifies the schedule pattern: every 5 seconds
    @Scheduled(fixedRate = 5000)
    public void executeScheduledTask() {
        try {
            // Get the current date and time
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
            String currentTime = dateFormat.format(new Date());

            // Log the current time to indicate the execution of the scheduled task
            System.out.println("Scheduled task executed at: " + currentTime);

            // Your task logic goes here
            // For example, perform some database operations, file processing, etc.

        } catch (Exception ex) {
            // Handle any exceptions that may occur during task execution
            System.err.println("Error executing scheduled task: " + ex.getMessage());
        }
    }
    
    // You can define other methods here as needed
    
    // For example, a method to trigger the task manually
    public void triggerTaskManually() {
        executeScheduledTask();
    }
}
