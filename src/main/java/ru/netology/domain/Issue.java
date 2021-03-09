package ru.netology.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Comparator;
import java.util.Set;
@AllArgsConstructor
@NoArgsConstructor
@Data

public class Issue implements Comparator<Issue> {
    private int id;
    private String author;
    private int date;
    private boolean isOpen;
    private Set<String> assignees;
    private Set<String> labels;
    private String projects;
    private String milestones;
    private Set<String> participants;

    public Issue(Comparator<Issue> first) {

    }


    @Override
    public int compare(Issue i1, Issue i2) {
        return i1.getDate()-i2.getDate();
    }



}
