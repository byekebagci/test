package yekebagci.batu.model;

public class SalonDetay {

    private Long detayId;

    private Integer x ;

    private Integer y ;

    private Integer kisi_sayisi ;

    private String kayitDurumu;

    private Long salonId;

    public Long getDetayId() {
        return detayId;
    }

    public void setDetayId(Long detayId) {
        this.detayId = detayId;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Integer getKisi_sayisi() {
        return kisi_sayisi;
    }

    public void setKisi_sayisi(Integer kisi_sayisi) {
        this.kisi_sayisi = kisi_sayisi;
    }

    public String getKayitDurumu() {
        return kayitDurumu;
    }

    public void setKayitDurumu(String kayitDurumu) {
        this.kayitDurumu = kayitDurumu;
    }

    public Long getSalonId() {
        return salonId;
    }

    public void setSalonId(Long salonId) {
        this.salonId = salonId;
    }
}
