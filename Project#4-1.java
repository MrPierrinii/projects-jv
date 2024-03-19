// Project #4
// University of Maryland
// Cedrick Pierre 


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Project4 extends Application {

    private TextField interval1StartField;
    private TextField interval1EndField;
    private TextField interval2StartField;
    private TextField interval2EndField;
    private TextField timeField;
    private Label resultLabel;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Interval and Time Checker");

        interval1StartField = new TextField();
        interval1StartField.setPromptText("Interval 1 Start");
        interval1EndField = new TextField();
        interval1EndField.setPromptText("Interval 1 End");
        interval2StartField = new TextField();
        interval2StartField.setPromptText("Interval 2 Start");
        interval2EndField = new TextField();
        interval2EndField.setPromptText("Interval 2 End");
        timeField = new TextField();
        timeField.setPromptText("Time (HH:MM AM/PM)");

        Button compareButton = new Button("CompareIntervals");
        compareButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                compareIntervals();
            }
        });

        Button checkButton = new Button("CheckTime");
        checkButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                checkTime();
            }
        });

        resultLabel = new Label();

        VBox vBox = new VBox();
        vBox.getChildren().addAll(interval1StartField, interval1EndField, interval2StartField, interval2EndField,
                timeField, compareButton, checkButton, resultLabel);

        Scene scene = new Scene(vBox, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void compareIntervals() {
        try {
            // Parse input and create interval objects
            Interval<Time> interval1 = createInterval(interval1StartField.getText(), interval1EndField.getText());
            Interval<Time> interval2 = createInterval(interval2StartField.getText(), interval2EndField.getText());

            // Compare intervals
            if (interval1.subinterval(interval2)) {
                resultLabel.setText("Interval 1 is a sub-interval of interval 2");
            } else if (interval2.subinterval(interval1)) {
                resultLabel.setText("Interval 2 is a sub-interval of interval 1");
            } else if (interval1.overlaps(interval2)) {
                resultLabel.setText("The intervals overlap");
            } else {
                resultLabel.setText("The intervals are disjoint");
            }
        } catch (InvalidTimeException e) {
            resultLabel.setText("Error: " + e.getMessage());
        }
    }

    private void checkTime() {
        try {
            // Parse input and create interval objects
            Interval<Time> interval1 = createInterval(interval1StartField.getText(), interval1EndField.getText());
            Interval<Time> interval2 = createInterval(interval2StartField.getText(), interval2EndField.getText());
            Time time = createTime(timeField.getText());

            // Check if time is within intervals
            if (interval1.within(time) && interval2.within(time)) {
                resultLabel.setText("Both intervals contain the time " + time);
            } else if (interval1.within(time)) {
                resultLabel.setText("Only interval 1 contains the time " + time);
            } else if (interval2.within(time)) {
                resultLabel.setText("Only interval 2 contains the time " + time);
            } else {
                resultLabel.setText("Neither interval contains the time " + time);
            }
        } catch (InvalidTimeException e) {
            resultLabel.setText("Error: " + e.getMessage());
        }
    }

    private Interval<Time> createInterval(String start, String end) throws InvalidTimeException {
        Time startTime = createTime(start);
        Time endTime = createTime(end);
        return new Interval<>(startTime, endTime);
    }

    private Time createTime(String timeString) throws InvalidTimeException {
        try {
            String[] parts = timeString.split(" ");
            if (parts.length != 3 || !parts[1].equals(":")) {
                throw new InvalidTimeException("Invalid time format: " + timeString);
            }

            String[] timeParts = parts[0].split(":");
            if (timeParts.length != 2) {
                throw new InvalidTimeException("Invalid time format: " + timeString);
            }

            int hours = Integer.parseInt(timeParts[0]);
            int minutes = Integer.parseInt(timeParts[1]);
            String meridian = parts[2];

            if (hours < 1 || hours > 12 || minutes < 0 || minutes > 59 || (!meridian.equals("AM") && !meridian.equals("PM"))) {
                throw new InvalidTimeException("Invalid time format: " + timeString);
            }

            return new Time(hours, minutes, meridian);
        } catch (NumberFormatException e) {
            throw new InvalidTimeException("Invalid time format: " + timeString);
        }
    }
}

class Interval<T extends Comparable<T>> {
    private final T start;
    private final T end;

    public Interval(T start, T end) {
        this.start = start;
        this.end = end;
    }

    public boolean within(T point) {
        return (point.compareTo(start) >= 0 && point.compareTo(end) <= 0);
    }

    public boolean subinterval(Interval<T> interval) {
        return (start.compareTo(interval.start) <= 0 && end.compareTo(interval.end) >= 0);
    }

    public boolean overlaps(Interval<T> interval) {
        return (start.compareTo(interval.end) <= 0 && end.compareTo(interval.start) >= 0);
    }
}

class Time implements Comparable<Time> {
    private final int hours;
    private final int minutes;
    private final String meridian;

    public Time(int hours, int minutes, String meridian) {
        this.hours = hours;
        this.minutes = minutes;
        this.meridian = meridian;
    }

    public int compareTo(Time other) {
        // Implement comparison logic
        if (this.hours != other.hours) {
            return Integer.compare(this.hours, other.hours);
        } else if (this.minutes != other.minutes) {
            return Integer.compare(this.minutes, other.minutes);
        } else {
            return this.meridian.compareTo(other.meridian);
        }
    }

    public String toString() {
        return String.format("%02d:%02d %s", hours, minutes, meridian);
    }
}

class InvalidTimeException extends Exception {
    public InvalidTimeException(String message) {
        super(message);
    }
}
