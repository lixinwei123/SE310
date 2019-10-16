public class AlarmClockRadio {
    private Radio radio;
    private AlarmClock alarmClock;

    AlarmClockRadio(Integer currentHour, Integer currentMinute, Integer currentSecond, String currentAmPm,
                    Integer alarmHour, Integer alarmMinute, String alarmAmPm, String station){
        this.radio = new Radio(station);
        this.alarmClock = new AlarmClock(currentHour,currentMinute,currentSecond,currentAmPm,alarmHour,alarmMinute,alarmAmPm);
    }
    //This method prints out every minute until the current time is the same as alarm time both hourly and minutely.
    public void timerHelper(){
        while(this.getCurrentTime().getHourIn24() < this.getAlarm().getHourIn24() || this.getCurrentTime().getMinute() < this.getAlarm().getMinute()){
            while(getCurrentTime().getSecond() < 60){
                this.getCurrentTime().incSecond();
                if(getCurrentTime().getSecond() == 0){
                    break;
                }
            }
            System.out.println ("time:" + this.getCurrentTime().getTimeInString());
        }
    }

    public void addRadioStation(String station){
        this.radio.addRadioStation(station);
    }

    public void removeRadioStation(String station){
        this.radio.removeRadioStation(station);
    }

    public void snooze(Integer minute){
        this.alarmClock.snooze(minute);
    }

    public void restoreAlarmTime(){
        this.alarmClock.restoreAlarmTime();
    }

    /*SETTERS*/
    public void setRadioOn(){
        this.radio.setRadioOn();
    }

    public void setRadioOff(){
        this.radio.setRadioOff();
    }

    public void setRadioStation(String station){
        this.radio.setRadioStation(station);
    }

    public void setCurrentTime(MyTime time){
        this.alarmClock.setCurrentTime(time);
    }

    public void setAlarm(MyTime time){
        this.alarmClock.setAlarm(time);
    }

    public void setAlarmOn(){
        this.alarmClock.setAlarmOn();
    }

    public void setAlarmOff(){
        this.alarmClock.setAlarmOff();
    }

    public void setRadioVolume(Integer vol){
        this.radio.setRadioVolume(vol);
    }

    /*GETTERS*/
    public boolean isAlarmOn(){
        return this.alarmClock.isAlarmOn();
    }

    public Boolean isRadioOn(){
        return this.radio.isRadioOn();
    }

    public MyTime getAlarm(){
        return this.alarmClock.getAlarm();
    }

    public String getRadioStation(){
        return this.radio.getRadioStation();
    }

    public MyTime getCurrentTime(){
        return this.alarmClock.getCurrentTime();
    }
}
