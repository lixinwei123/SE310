public class AlarmClockRadio {
    private Radio radio;
    private AlarmClock alarmClock;

    AlarmClockRadio(Integer currentHour, Integer currentMinute, Integer currentSecond, String currentAmPm,
                    Integer alarmHour, Integer alarmMinute, String alarmAmPm, String station){
        this.radio = new Radio(station);
        this.alarmClock = new AlarmClock(currentHour,currentMinute,currentSecond,currentAmPm,alarmHour,alarmMinute,alarmAmPm);
    }

    public void setRadioStation(String station){
        this.radio.setRadioStation(station);
    }

    public String getRadioStation(){
        return this.radio.getRadioStation();
    }

    public MyTime getCurrentTime(){
        return this.alarmClock.getCurrentTime();
    }

    public void setCurrentTime(MyTime time){
        this.alarmClock.setCurrentTime(time);
    }

    public void setAlarm(MyTime time){
        this.alarmClock.setAlarm(time);
    }

    public MyTime getAlarm(){
        return this.alarmClock.getAlarm();
    }

    public void setAlarmOn(){
        this.alarmClock.setAlarmOn();
    }

    public void setAlarmOff(){
        this.alarmClock.setAlarmOff();
    }

    public String getTimeInString(){
        return this.alarmClock.getTimeInString();
    }

    public void setRadioVolume(Integer vol){
        this.radio.setRadioVolume(vol);
    }

    public void addRadioStation(String station){
        this.radio.addRadioStation(station);
    }

    public void removeRadioStation(String station){
        this.radio.removeRadioStation(station);
    }

    public boolean isAlarmOn(){
        return this.alarmClock.isAlarmOn();
    }

    public void snooze(Integer minute){
       this.alarmClock.snooze(minute);
    }

    public void restoreAlarmTime(){
        this.alarmClock.restoreAlarmTime();
    }

    public void timerHelper(){
        while(this.getCurrentTime().getMinute() < this.getAlarm().getMinute()){
            while(getCurrentTime().getSecond() < 59){
                this.getCurrentTime().incSecond();
            }
            this.getCurrentTime().setSecond(0);
            this.getCurrentTime().incMinute();
            System.out.println ("time:" + this.getTimeInString());
        }
    }
}
