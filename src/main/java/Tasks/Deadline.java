package Tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.Locale;

public class Deadline extends Task {

    //private String deadline;
    private LocalDateTime localDateTime;

    public Deadline(String input, String deadline) {
        super(input);
        String[] strings = deadline.split("/");
        int dayOfMonth = Integer.parseInt(strings[0]);
        int month = Integer.parseInt(strings[1]);
        String[] yearTime = strings[2].split(" ");
        int year = Integer.parseInt(yearTime[0]);
        String time = yearTime[1];
        String hour = time.substring(0, 2);
        String minute = time.substring(2);
        LocalDate localDate = LocalDate.of(year, month, dayOfMonth);
        LocalTime localTime = LocalTime.of(Integer.parseInt(hour), Integer.parseInt(minute));
        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
        this.localDateTime = localDateTime;
    }

    @Override
    public String displayTask() {
        String cross ="";
        if (super.getDone()) {
            cross = "[X]";
        } else {
            cross = "[ ]";
        }
        String month = this.localDateTime.getMonth().getDisplayName(
                TextStyle.valueOf("SHORT"), new Locale("English"));
        String dayOfMonth = String.valueOf(this.localDateTime.getDayOfMonth());
        String year = String.valueOf(this.localDateTime.getYear());
        String time = this.localDateTime.toLocalTime().toString();
        return "[D]" + cross + " " + super.getInput()
                + " (by: " + month + " " + dayOfMonth + " " + year + " " + time + ")\n";
    }
}
