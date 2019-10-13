import java.util.ArrayList;

public class Radio {
    private String currentRadioStation;
    private ArrayList<String> stations = new ArrayList();
    private Integer volume; //percentage, out of 100
    private Boolean isRadioOn;
    Radio(String station){
        setRadioStation(station);
        setRadioVolume(50);
        stations.add("1060 AM");
        stations.add("Classical Radio FM");
        stations.add("Rock Radio FM");
        stations.add("Pop Radio FM");
    }

    public void addRadioStation(String station){
        this.stations.add(station);
    }

    public void removeRadioStation(String station){
        for(var i = 0; i < stations.size(); i++){
            if(this.stations.get(i) == station){
                this.stations.remove(i);
            }
        }
    }
    //SETTERs
    public void setRadioOn(){
        this.isRadioOn = true;
    }

    public void setRadioOff(){
        this.isRadioOn = false;
    }

    public void setRadioStation(String s){
        this.currentRadioStation = s;
    }

    public void setRadioVolume(Integer vol){
        this.volume = vol;
    }

    //GETTERS
    public String getRadioStation(){
        return this.currentRadioStation;
    }

    public Boolean isRadioOn(){
        return this.isRadioOn;
    }
}
