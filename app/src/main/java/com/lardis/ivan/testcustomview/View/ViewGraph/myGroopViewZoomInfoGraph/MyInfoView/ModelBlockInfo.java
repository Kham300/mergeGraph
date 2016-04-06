package com.lardis.ivan.testcustomview.View.ViewGraph.myGroopViewZoomInfoGraph.MyInfoView;

/**
 * Created by i.larin on 05.04.2016.
 */
public class ModelBlockInfo {

    public String getInfoDlit() {
        return infoDlit;
    }

    public void setInfoDlit(String infoDlit) {
        this.infoDlit = infoDlit;
    }

    public String getInfoPodxod() {
        return infoPodxod;
    }

    public void setInfoPodxod(String infoPodxod) {
        this.infoPodxod = infoPodxod;
    }

    public String getInfoRaz() {
        return infoRaz;
    }

    public void setInfoRaz(String infoRaz) {
        this.infoRaz = infoRaz;
    }

    public String getInfoSozheno() {
        return infoSozheno;
    }

    public void setInfoSozheno(String infoSozheno) {
        this.infoSozheno = infoSozheno;
    }

    public String getInfoPuls() {
        return infoPuls;
    }

    public void setInfoPuls(String infoPuls) {
        this.infoPuls = infoPuls;
    }

    public String getInfoXmlLayout() {
        return infoXmlLayout;
    }

    public void setInfoXmlLayout(String infoXmlLayout) {
        this.infoXmlLayout = infoXmlLayout;
    }

    String infoDlit,infoPodxod,infoRaz,infoSozheno,infoPuls,infoXmlLayout;

    public ModelBlockInfo(String infoDlit, String infoPodxod, String infoRaz, String infoSozheno, String infoPuls) {
        this.infoDlit = infoDlit;
        this.infoPodxod = infoPodxod;
        this.infoRaz = infoRaz;
        this.infoSozheno = infoSozheno;
        this.infoPuls = infoPuls;

    }
}
