package org.motechproject.care.schedule.service;

public enum MilestoneType {

    Measles("Measles", "measles"),
    Hep0("Hep 0", "hep_0"),
    Hep1("Hep 1", "hep_1"),
    Hep2("Hep 2", "hep_2"),
    Hep3("Hep 3", "hep_3"),
    DPT1("DPT 1", "dpt_1"),
    DPT2("DPT 2", "dpt_2"),
    DPT3("DPT 3", "dpt_3"),
    DPTBooster("DPT Booster", "dpt_booster"),
    OPV0("OPV 0", "opv_0"),

    TT1("TT 1", "tt_1"),
    TT2("TT 2", "tt_2"),
    VitaminA("Vita", "vita_1"),
    Bcg("Bcg", "bcg"),
    Anc1("Anc 1", "anc_1"),
    Anc2("Anc 2", "anc_2"),
    Anc3("Anc 3", "anc_3"),
    Anc4("Anc 4", "anc_4");

    private String name;
    private String taskId;

    MilestoneType(String name, String taskId) {
        this.name = name;
        this.taskId = taskId;
    }

    public String toString(){
        return name;
    }

    public static MilestoneType forType(String type) {
        MilestoneType[] values = MilestoneType.values();
        for(MilestoneType milestoneType: values) {
            if(milestoneType.name.equals(type)) {
                return milestoneType;
            }
        }
        return null;
    }

    public String getTaskId() {
        return taskId;
    }

    public String getName() {
        return name;
    }
}
