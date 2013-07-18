package org.motechproject.carereporting.enums;

public enum ReportType {
    BarChart(1), LineChart(2), PieChart(3);

    private Integer value;

    ReportType(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
