package com.example.alzbot;

public class People {
    String naamm;
    String livee;
    String agee;
    String placee;
    String timee;
    String relationn;
    String notess;
    String downloadUrl;

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public People(String naamm, String livee, String agee, String placee, String timee, String relationn, String notess, String downloadUrl) {
        this.naamm = naamm;
        this.livee = livee;
        this.agee = agee;
        this.placee = placee;
        this.timee = timee;
        this.relationn = relationn;
        this.notess = notess;
        this.downloadUrl = downloadUrl;
    }

    public String getNaamm() {
        return naamm;
    }

    public void setNaamm(String naamm) {
        this.naamm = naamm;
    }

    public String getLivee() {
        return livee;
    }

    public void setLivee(String livee) {
        this.livee = livee;
    }

    public String getAgee() {
        return agee;
    }

    public void setAgee(String agee) {
        this.agee = agee;
    }

    public String getPlacee() {
        return placee;
    }

    public void setPlacee(String placee) {
        this.placee = placee;
    }

    public String getTimee() {
        return timee;
    }

    public void setTimee(String timee) {
        this.timee = timee;
    }

    public String getRelationn() {
        return relationn;
    }

    public void setRelationn(String relationn) {
        this.relationn = relationn;
    }

    public String getNotess() {
        return notess;
    }

    public void setNotess(String notess) {
        this.notess = notess;
    }

    @Override
    public String toString() {
        return new String("***People*** \n"+
                "naam : "+naamm+"\n"+
                "livee : "+livee+"\n"+
                "agee : "+agee+"\n"+
                "placee : "+placee+"\n"+
                "timee : "+timee+"\n"+
                "relationn : "+relationn+"\n"+
                "notess : "+notess+"\n"+
                "downloadUrl : "+downloadUrl);
    }

    public People() {
    }
}
