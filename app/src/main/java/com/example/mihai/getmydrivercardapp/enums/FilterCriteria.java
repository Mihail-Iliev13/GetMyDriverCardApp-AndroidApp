package com.example.mihai.getmydrivercardapp.enums;

public enum FilterCriteria {
    ID("ID"), NAME("Name"), DATE_OF_SUBMISSION("Date of submission"), STATUS("Status"),SHOW_ALL("Show all");

    private String criteriaStr;
    FilterCriteria (String criteriaStr) {
        this.criteriaStr = criteriaStr;
    }

    @Override
    public String toString() {
        return criteriaStr;
    }
}
