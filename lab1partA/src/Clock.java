public class Clock {
    private MyTime currentTime;
    Clock(Integer hour, Integer minute, Integer second, String AMPM) {
       this.currentTime = new MyTime(hour,minute,second, AMPM);
    }
    public MyTime getCurrentTime(){
        return currentTime;
    }

    public void setCurrentTime( MyTime t){
        this.currentTime = t;
    }

}
