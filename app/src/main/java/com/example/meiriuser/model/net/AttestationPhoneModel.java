package com.example.meiriuser.model.net;

/**
 * Created by admin on 2019/7/3.
 */

public class AttestationPhoneModel {
    private String passport;
    private String visa;
    private String divers_license;

    public String getPassport() {
        return passport;
    }

    public String getVisa() {
        return visa;
    }

    public String getDivers_license() {
        return divers_license;
    }



    public AttestationPhoneModel(String passport, String visa, String divers_license) {
        this.passport = passport;
        this.visa = visa;
        this.divers_license = divers_license;
    }


}
