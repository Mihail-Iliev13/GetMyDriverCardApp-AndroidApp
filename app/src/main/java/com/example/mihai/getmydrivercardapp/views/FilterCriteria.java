package com.example.mihai.getmydrivercardapp.views;

public enum FilterCriteria {
    ID("ID"), NAME("Name"), DATE_OF_SUBMISSION("Date of submission"), STATUS("Status");

    private String criteriaStr;
    FilterCriteria (String criteriaStr) {
        this.criteriaStr = criteriaStr;
    }

    @Override
    public String toString() {
        return criteriaStr;
    }
}
