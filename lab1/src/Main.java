public class Main {

    public static void main(String[] args) {
         AlarmClockRadio ALR = new AlarmClockRadio(8,0,0,"AM",8,5,"AM","1060 AM");
        // AlarmClockRadio ALR = new AlarmClockRadio(8,56,0,"PM",9,3,"AM","1060 AM");
         ALR.setAlarmOn();
         if(ALR.isAlarmOn()){
             System.out.println ("Initial Time:" + ALR.getTimeInString() + " The radio was turned on and is playing " + ALR.getRadioStation());
             ALR.timerHelper();
             System.out.println ("Buzz Buzz Buzz");
             System.out.println ("Snooze was pressed");
             ALR.snooze(9);
             ALR.timerHelper();
             System.out.println ("Buzz Buzz Buzz");
             System.out.println ("The alarm was shut off.");
             ALR.restoreAlarmTime();
             System.out.println (ALR.getAlarm().getMinute() + " " + ALR.getAlarm().getSecond());
         }

    }

}
