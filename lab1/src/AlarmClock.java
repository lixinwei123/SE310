public class AlarmClock extends Clock {
    private MyTime alarmTime;
    private Boolean isAlarmOn = false;
    private MyTime alarmTimeCopy; //make a copy of the original alarm so after snooze you can restore it
    AlarmClock(Integer currentHour, Integer currentMinute, Integer currentSecond, String currentAmPm, Integer alarmHour, Integer alarmMinute, String alarmAmPm ){
        super(currentHour,currentMinute,currentSecond, currentAmPm);
        this.alarmTime = new MyTime(alarmHour, alarmMinute, 0);
        this.alarmTimeCopy = new MyTime(alarmHour, alarmMinute, 0);;
    }

    public void setAlarm(MyTime t){
        this.alarmTime = t;
    }

    public MyTime getAlarm(){
        return this.alarmTime;
    }

    public void setAlarmOn(){
        this.isAlarmOn = true;
    }

    public void setAlarmOff(){
        this.isAlarmOn = false;
    }

    public boolean isAlarmOn(){
        return this.isAlarmOn;
    }

    public void snooze(Integer minute){
        setAlarmOff();
        setAlarm(new MyTime(this.alarmTime.getHour(),this.alarmTime.getMinute() + minute,this.alarmTime.getSecond()));
    }

    public void restoreAlarmTime(){
        setAlarm(alarmTimeCopy);
    }


}
