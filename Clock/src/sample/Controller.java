package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Controller {
    @FXML
    private Line secondHand;
    @FXML
    private Line minuteHand;
    @FXML
    private Line hourHand;

    int X = 410;
    int Y = 400;

    Calendar calendar = new GregorianCalendar();

    @FXML
    private void initialize() {
        oneSecondCheckState.setCycleCount(Timeline.INDEFINITE);
        oneSecondCheckState.play();

        int seconds=calendar.get(Calendar.SECOND);
        int minutes=calendar.get(Calendar.MINUTE);
        int hours=calendar.get(Calendar.HOUR);

        Rotate rotateSecond = new Rotate();
        rotateSecond.setAngle((double)(360/60)*(double)seconds);
        rotateSecond.setPivotX(X);
        rotateSecond.setPivotY(Y);
        secondHand.getTransforms().addAll(rotateSecond);

        Rotate rotateMinute = new Rotate();
        rotateMinute.setAngle((double)(360/60)*((double)minutes+(double)seconds/(double)60));
        rotateMinute.setPivotX(X);
        rotateMinute.setPivotY(Y);
        minuteHand.getTransforms().addAll(rotateMinute);

        Rotate rotateHour = new Rotate();
        rotateHour.setAngle((double)(360/12)*((double)hours+(double)minutes/(double)60+(double)seconds/(double)3600));
        rotateHour.setPivotX(X);
        rotateHour.setPivotY(Y);
        hourHand.getTransforms().addAll(rotateHour);
    }

    Timeline oneSecondCheckState = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent event) {
            Rotate rotateSecond = new Rotate();
            rotateSecond.setAngle(360/60);
            rotateSecond.setPivotX(X);
            rotateSecond.setPivotY(Y);
            secondHand.getTransforms().addAll(rotateSecond);

            Rotate rotateMinute = new Rotate();
            rotateMinute.setAngle((double)360/(double)(60*60));
            rotateMinute.setPivotX(X);
            rotateMinute.setPivotY(Y);
            minuteHand.getTransforms().addAll(rotateMinute);

            Rotate rotateHour = new Rotate();
            rotateHour.setAngle((double)360/(double)(12*60*60));
            rotateHour.setPivotX(X);
            rotateHour.setPivotY(Y);
            hourHand.getTransforms().addAll(rotateHour);

            calendar.add(Calendar.SECOND, +1);
        }
    }));
}