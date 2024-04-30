package pl.zabrze.zs10.myapplicationretrofit3a;

import com.google.gson.annotations.SerializedName;

public class Pytanie {
    private String tresc;
    @SerializedName("odpa")
    private String odpA;
    @SerializedName("odpb")
    private String odpB;
    @SerializedName("odpc")
    private String odpC;
    private int poprawna;

    public String getTresc() {
        return tresc;
    }

    public String getOdpA() {
        return odpA;
    }

    public String getOdpB() {
        return odpB;
    }

    public String getOdpC() {
        return odpC;
    }

    public int getPoprawna() {
        return poprawna;
    }
}
