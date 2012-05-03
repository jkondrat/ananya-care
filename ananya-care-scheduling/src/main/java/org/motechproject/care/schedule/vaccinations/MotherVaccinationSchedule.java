package org.motechproject.care.schedule.vaccinations;

import java.util.ArrayList;

public enum MotherVaccinationSchedule {
    TT("TT Vaccination"),
    TTBooster("TT Booster"),
    Anc("Anc Visit"),
    Anc4("Anc4 Visit");

    private String vaccinationScheduleName;

    MotherVaccinationSchedule(String vaccinationScheduleName) {
        this.vaccinationScheduleName = vaccinationScheduleName;
    }

    public String getName() {
        return vaccinationScheduleName;
    }

    public static ArrayList<String> allVaccineNames() {
        ArrayList<String> vaccines = new ArrayList<String>();
        for (MotherVaccinationSchedule b : MotherVaccinationSchedule.values()) {
            vaccines.add(b.getName());
        }
        return vaccines;
    }
}

