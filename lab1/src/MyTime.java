/*
* A custom time class that has hours, minute and second with methods that are associated with these properties
* ©Kevin Li 2019 All Rights Reserved SE310
* */
public class MyTime {
    private Integer hour;
    private Integer minute;
    private Integer second;
    private String AMPM;
    MyTime(Integer hour, Integer minute, Integer second, String AMPM){
        setHour(hour);
        setMinute(minute);
        setSecond(second);
        setAMPM(AMPM);
    }

    public void incSecond(){
        this.second ++;
        if(this.second > 59){
            incMinute();
            setSecond(0);
        }
    }

    public void incMinute() {
        this.minute ++;
        if(this.minute > 59){
            setMinute(0);
            incHour();
        }
    }

    public void incHour() {
        this.hour ++;
        if(this.hour > 11){
            if(this.hour != 12){
                setHour(1);
            }else{
                if(this.AMPM == "AM"){
                    setAMPM("PM");
                } else{
                    setAMPM("AM");
                }
            }
        }
    }

    /* SETTERS */
    public void setHour(Integer hour){
        this.hour = hour;
    }

    public void setMinute(Integer minute){
        this.minute = minute;
    }


    public void setSecond(Integer second){
        this.second = second;
    }

    public void setAMPM(String AMPM){
        this.AMPM = AMPM;
    }

    /* GETTERS */
    public Integer getSecond(){
        return this.second;
    }

    public Integer getMinute(){
        return this.minute;
    }

    public Integer getHour(){
        return this.hour;
    }

    public String getAMPM(){
        return this.AMPM;
    }

    public String getTimeInString(){
        if(this.minute < 10){
            return this.hour + ":0" + this.minute + " " + this.AMPM;
        }
        else{
            return this.hour + ":" + this.minute + " " + this.AMPM;
        }
    }

    public Integer getHourIn24(){
        if(getAMPM() == "PM" && this.hour != 12){
            return this.hour + 12;
        }
        else{
            return this.hour;
        }
    }
}
