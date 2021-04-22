package DateTime;

//Correct answer. The pattern incorrectly uses mm instead of MM in the first part of the pattern. mm is for minutes.
// In addition, uuu pattern prints out 4 digit year.
//
//        Question 1:
//        Given:

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Quiz {
    public static void main(String[] args) {

        DateTimeFormatter dateFormatter =
                DateTimeFormatter.ofPattern("mm/dd/uuu hh:mm a"); // Line 1 mm is for minutes MM is for Month

        LocalDateTime d = LocalDateTime.of(2020, 4, 10, 10, 10); // Line 2

        System.out.println(MessageFormat.format("Date  = {0}",
                dateFormatter.format(d)));  // Line 3 Date  = 10/10/2020 10:10 am

        DateTimeFormatter dateFormatter1 =
                DateTimeFormatter.ofPattern("MM/DD/uuu hh:mm a"); // Line 1 MM is for Month DD -> Number of Day in an year
        System.out.println(dateFormatter1.format(LocalDateTime.now())); // 04/112/2021 04:55 pm DD prints 112 here
    }
}