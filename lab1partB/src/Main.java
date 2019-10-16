public class Main {

    public static void main(String[] args) {
         AlarmClockRadio ALR = new AlarmClockRadio(8,58,0,"AM",9,3,"AM","1060 AM");
         ALR.setAlarmOn();
         ALR.setRadioOn();
         System.out.println ("Initial Time:" + ALR.getCurrentTime().getTimeInString() + " The radio was turned on and is playing " + ALR.getRadioStation());
         ALR.timerHelper(); //loop first time
         System.out.println ("The radio is playing " + ALR.getRadioStation());
         System.out.println ("Snooze was pressed");
         ALR.snooze(9);
         ALR.timerHelper(); //loop second time
         System.out.println ("The radio is playing " + ALR.getRadioStation());
         System.out.println ("The alarm was shut off.");
         ALR.restoreAlarmTime(); //restore original alarm state

    }

}
