public class Clock {
    private MyTime currentTime;
    private String AMPM;
    Clock(Integer hour, Integer minute, Integer second, String AMPM) {
       this.currentTime = new MyTime(hour,minute,second);
       this.AMPM = AMPM;
    }
    public MyTime getCurrentTime(){
        return currentTime;
    }

    public void setCurrentTime( MyTime t){
        this.currentTime = t;
    }

    public String getTimeInString(){
        if(this.currentTime.getMinute() < 10){
            return this.currentTime.getHour() + ":0" + this.currentTime.getMinute() + " " + this.AMPM;
        }
        else{
            return this.currentTime.getHour() + ":" + this.currentTime.getMinute() + " " + this.AMPM;
        }
    }

}
