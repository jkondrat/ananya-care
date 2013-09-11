package org.motechproject.carereporting.domain.types;

public enum ReportType {
    BarChart(1), LineChart(2), PieChart(3), ClusteredBarChart(4);

    private Integer value;

    ReportType(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
