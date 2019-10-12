public class MyTime {
    private int hour;
    private int minute;
    private int second;

    MyTime(Integer hour, Integer minute, Integer second){
        setHour(hour);
        setMinute(minute);
        setSecond(second);
    }

    public int getHour(){
        return this.hour;
    }

    public void setHour(Integer hour){
        this.hour = hour;
    }

    public int getMinute(){
        return this.minute;
    }

    public void setMinute(Integer minute){
        this.minute = minute;
    }

    public int getSecond(){
        return this.second;
    }

    public void setSecond(Integer second){
        this.second = second;
    }

    public void incSecond(){
        this.second ++;
    }

    public void incMinute() {this.minute ++;}
}
