package ru.netology.manager;

import com.google.gson.JsonObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Issue;
import ru.netology.repository.IssueRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class IssueManagerTest {
    private IssueManager manager = new IssueManager(new IssueRepository());

    Issue first = new Issue(1, "Jon", 20022020, true, Set.of("User3"), Set.of("Task", "Build"), "Test", null, null);
    Issue second = new Issue(2, "Don", 14022020, true, Set.of("User3", "User2"), Set.of("Task", "Invalid"), "Test", null, null);
    Issue third = new Issue(3, "Jon", 10032020, false, Set.of("User3"), Set.of("Task"), "Test", null, null);
    Issue fourth = new Issue(4, "Jon", 15032020, true, Set.of("User2", "User3"), Set.of("Bug", "In Progress"), "Test", null, null);
    Issue fifth = new Issue(5, "Don", 23032020, false, Set.of("User1"), Set.of("Question", "Invalid"), "Test", null, null);
    Issue sixth = new Issue(6, "Jon", 30042020, true, Set.of("User1"), Set.of("Bug", "Blocked"), "Test", null, null);


    @Nested
    public class Empty {

    }

    @Test
    void shouldAddIssue() {
        manager.add(new Issue(first));
    }

    @Test
    void shouldShowOpenedIssue() {
        List<Issue> expected = List.of();
        List<Issue> actual = manager.openedIssue();
        assertEquals(expected, actual);
    }

    @Test
    void shouldShowClosedIssue() {
        List<Issue> expected = List.of();
        List<Issue> actual = manager.closedIssue();
        assertEquals(expected, actual);
    }

    @Test
    void shouldFilterByAuthor() {
        String filter = "Jon";
        List<Issue> expected = List.of();
        List<Issue> actual = manager.filterByAuthor(filter);
        assertEquals(expected, actual);
    }

    @Test
    void shouldFilterByLabel() {
        Set<String> filter = Set.of("Bug", "Blocked");
        List<Issue> expected = List.of();
        List<Issue> actual = manager.filterByLabel(filter);
        assertEquals(expected, actual);
    }

    @Test
    void shouldFilterByAssignee() {
        Set<String> filter = Set.of("User2", "User3");
        List<Issue> expected = List.of();
        List<Issue> actual = manager.filterByAssignee(filter);
        assertEquals(expected, actual);
    }

    @Nested
    public class SingleItem {
        @BeforeEach
        void setUp() {
            manager.add(third);
        }

        @Test
        void shouldAddIssue() {
            manager.add(second);
            List<Issue> expected = List.of(third, second);
            List<Issue> actual = manager.getAll();
            assertEquals(expected, actual);
        }

        @Test
        void shouldShowOpenedIssue() {
            List<Issue> expected = List.of();
            List<Issue> actual = manager.openedIssue();
            assertEquals(expected, actual);
        }

        @Test
        void shouldShowClosedIssue() {
            List<Issue> expected = List.of(third);
            List<Issue> actual = manager.closedIssue();
            assertEquals(expected, actual);
        }

        @Test
        void shouldFilterByAuthor() {
            String filter = "Jon";
            List<Issue> expected = List.of(fourth);
            List<Issue> actual = manager.filterByAuthor(filter);
            assertEquals(expected, actual);
        }

        @Test
        void shouldFilterByLabel() {
            Set<String> filter = Set.of("Task");
            List<Issue> expected = List.of(third);
            List<Issue> actual = manager.filterByLabel(filter);
            assertEquals(expected, actual);
        }

        @Test
        void shouldFilterByAssignee() {
            Set<String> filter = Set.of("User3", "User2");
            List<Issue> expected = List.of();
            List<Issue> actual = manager.filterByAssignee(filter);
            assertEquals(expected, actual);
        }

        @Test
        void shouldOpenById() {
            manager.openIssue(5);
            List<Issue> expected = List.of(fifth);
            List<Issue> actual = manager.openedIssue();
            assertEquals(expected, actual);
        }

        @Test
        void shouldCloseById() {
            manager.closeIssue(3);
            List<Issue> expected = List.of(third);
            List<Issue> actual = manager.closedIssue();
            assertEquals(expected, actual);
        }


        @Test
        void shouldAddMultipleIssue() {
            manager.addAll(List.of(new Issue(), new Issue()));

        }
        @Nested
        public class MultipleItem {
            @BeforeEach
            void setUp() {
                manager.add(first);
                manager.add(second);
                manager.add(third);
                manager.add(fourth);
                manager.add(fifth);
            }

            @Test
            void shouldAddIssue() {
                manager.add(sixth);
                List<Issue> expected = List.of(first, second, third, fourth, fifth, sixth);
                List<Issue> actual = manager.getAll();
                assertEquals(expected, actual);
            }

            @Test
            void shouldShowOpenedIssue() {
                List<Issue> expected = List.of(first, second, fourth);
                List<Issue> actual = manager.openedIssue();
                assertEquals(expected, actual);
            }

            @Test
            void shouldShowClosedIssue() {
                List<Issue> expected = List.of(third, fifth);
                List<Issue> actual = manager.closedIssue();
                assertEquals(expected, actual);
            }

            @Test
            void shouldFilterByAuthor() {
                String filter = "Jon";
                List<Issue> expected = List.of(first, third, fourth);
                List<Issue> actual = manager.filterByAuthor(filter);
                assertEquals(expected, actual);
            }

            @Test
            void shouldFilterByLabel() {
                Set<String> filter = Set.of("Task", "Build");
                List<Issue> expected = List.of(first);
                List<Issue> actual = manager.filterByLabel(filter);
                assertEquals(expected, actual);
            }

            @Test
            void shouldFilterByAssignee() {
                Set<String> filter = Set.of("Don");
                List<Issue> expected = List.of(second, fifth);
                List<Issue> actual = manager.filterByAssignee(filter);
                assertEquals(expected, actual);
            }




            @Test
        void sortBy() {
        }
    }
}