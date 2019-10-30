package yekebagci.batu.model;

public class Salon {

    private Long salonId;

    private String salonAdi;

    private Integer en;

    private Integer boy;

    private Long sahibi;

    private String kayitDurumu;

    public Long getSalonId() {
        return salonId;
    }

    public void setSalonId(Long salonId) {
        this.salonId = salonId;
    }

    public String getSalonAdi() {
        return salonAdi;
    }

    public void setSalonAdi(String salonAdi) {
        this.salonAdi = salonAdi;
    }

    public Integer getEn() {
        return en;
    }

    public void setEn(Integer en) {
        this.en = en;
    }

    public Integer getBoy() {
        return boy;
    }

    public void setBoy(Integer boy) {
        this.boy = boy;
    }

    public Long getSahibi() {
        return sahibi;
    }

    public void setSahibi(Long sahibi) {
        this.sahibi = sahibi;
    }

    public String getKayitDurumu() {
        return kayitDurumu;
    }

    public void setKayitDurumu(String kayitDurumu) {
        this.kayitDurumu = kayitDurumu;
    }
}
