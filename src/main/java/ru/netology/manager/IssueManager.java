package ru.netology.manager;

import ru.netology.domain.Issue;
import ru.netology.repository.IssueRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

public class IssueManager {
    private IssueRepository repository;


    public void add (Issue issue){
        repository.add(issue);
    }

    public List<Issue> getAll(){
        return repository.getAll();
    }

    public List<Issue> openedIssue(){
        List<Issue> result =new ArrayList<>();
        for (Issue item : repository.getAll()){
            if (item.isOpen()){
                result.add(item);
            }
        }
        return result;
    }

    public List<Issue> closedIssue(){
        List<Issue> result = new ArrayList<>();
        for (Issue item : repository.getAll()){
            if (!item.isOpen()){
                result.add(item);
            }
        }
        return result;
    }
    public List<Issue> filterBy(Predicate<Issue> filter) {
        List<Issue> result = new ArrayList<>();
        for (Issue item : repository.getAll()) {
            if (filter.test(item)) {
                result.add(item);
            }
        }
        return result;
    }

    public List<Issue> filterByAuthor(String author) {
        Predicate<Issue> filter = f -> f.getAuthor().equals(author);
        List<Issue> result = filterBy(filter);
        return result;
    }


    public List<Issue> filterByLabel(Set<String> label) {
        Predicate<Issue> filter = f -> f.getLabels().equals(label);
        List<Issue> result = filterBy(filter);
        return result;
    }


    public List<Issue> filterByAssignee(Set<String> assignee) {
        Predicate<Issue> filter = f -> f.getAssignees().equals(assignee);
        List<Issue> result = filterBy(filter);
        return result;
    }
    public List<Issue> sortBy(Comparator<Issue> comparator) {
        List<Issue> result = repository.getAll();
        result.sort(comparator);
        return result;
    }


    public void openIssue(int id) {
        Issue item = repository.getById(id);
        if (item.isOpen()) {
            return;
        } else {
            item.setOpen(true);
        }
    }


    public void closeIssue(int id) {
        Issue item = repository.getById(id);
        if (!item.isOpen()) {
            return;
        } else {
            item.setOpen(false);
        }
    }

    public void addAll(List<Issue> issues) {
    }
}
