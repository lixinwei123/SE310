/*
* T
* */
public class AlarmClock extends Clock {
    private MyTime alarmTime;
    private MyTime alarmTimeCopy; //make a copy of the original alarm so after snooze you can restore it
    private Boolean isAlarmOn = false;
    AlarmClock(Integer currentHour, Integer currentMinute, Integer currentSecond, String currentAmPm, Integer alarmHour, Integer alarmMinute, String alarmAmPm ){
        super(currentHour,currentMinute,currentSecond, currentAmPm);
        this.alarmTime = new MyTime(alarmHour, alarmMinute, 0, alarmAmPm);
        this.alarmTimeCopy = new MyTime(alarmHour, alarmMinute, 0, alarmAmPm);;
    }
    //Accepts a custom minute
    public void snooze(Integer minute){
        setAlarmOff();
        setAlarm(new MyTime(this.alarmTime.getHour(),this.alarmTime.getMinute() + minute,this.alarmTime.getSecond(),this.alarmTime.getAMPM()));
    }
    //restore previous alarm that is store
    public void restoreAlarmTime(){
        setAlarm(alarmTimeCopy);
    }

    /*SETTERS*/
    public void setAlarm(MyTime t){
        this.alarmTime = t;
    }

    public void setAlarmOn(){
        this.isAlarmOn = true;
    }

    public void setAlarmOff(){
        this.isAlarmOn = false;
    }

    /*GETTERES*/
    public boolean isAlarmOn(){
        return this.isAlarmOn;
    }

    public MyTime getAlarm(){
        return this.alarmTime;
    }
}
