
package inheritance.expcopy;

import java.util.*;

class Course {
    String courseTitle;
    int time;

    Course(String title, int time) {
        this.courseTitle = title;
        this.time = time;
    }

    void display() {
        System.out.println("Course Title : " + courseTitle);
        System.out.println("Time Duration : " + time + " DAYS");
    }
}

/* ---------------- ONLINE COURSE ---------------- */

class OnlineCourse extends Course {
    static final int MAX_VIDEOS = 10;
    String platformName;
    List<String> videos = new ArrayList<>();

    OnlineCourse(String platform, String title, int time) {
        super(title, time);
        this.platformName = platform;
    }

    void insertVideo(String... names) {
        for (String v : names) {
            if (videos.size() == MAX_VIDEOS) {
                System.out.println("ERROR: Video limit reached!");
                return;
            }
            videos.add(v);
        }
    }

    void displayVideos() {
        display();
        System.out.println("Platform : " + platformName);
        System.out.println("Video Count : " + videos.size());
        System.out.println("------ VIDEOS ------");
        videos.forEach(System.out::println);
    }
}

/* ---------------- OFFLINE COURSE ---------------- */

class OfflineCourse extends Course {
    Scanner sc = new Scanner(System.in);

    String[] classes = {"BF 05", "BF 06", "BF 08"};
    String[] labs = {"BF 10", "BF 04", "BF 07", "BF 09A", "BF 09B"};

    Set<String> occupiedClass = new HashSet<>();
    Set<String> occupiedLab = new HashSet<>();

    OfflineCourse(String title, int time) {
        super(title, time);
    }

    boolean confirm() {
        System.out.print("Allocate? (y/n): ");
        return Character.toUpperCase(sc.next().charAt(0)) == 'Y';
    }

    void allocate(String[] available, Set<String> occupied, String type) {
        for (String room : available) {
            if (!occupied.contains(room)) {
                System.out.println(room + " " + type + " is free");
                if (confirm()) occupied.add(room);
                return;
            }
        }
        System.out.println("No " + type + " available");
    }

    void freeClass() {
        allocate(classes, occupiedClass, "Class");
    }

    void freeLab() {
        allocate(labs, occupiedLab, "Lab");
    }

    void deallocateClass(String c) {
        occupiedClass.remove(c.toUpperCase());
    }

    void deallocateLab(String l) {
        occupiedLab.remove(l.toUpperCase());
    }
}

/* ---------------- MAIN ---------------- */

public class InheritanceCopy_4 {
    public static void main(String[] args) {

        OnlineCourse on = new OnlineCourse("MX Player", "Java", 20);
        on.insertVideo(
                "Polymorphism", "Interface 1", "Interface 2",
                "Interface 3", "Strings", "Inheritance",
                "Abstraction", "Collections", "Exception", "JDBC"
        );
        on.displayVideos();

        OfflineCourse of = new OfflineCourse("OOPs", 1);
        of.freeClass();
        of.freeLab();
        of.deallocateLab("bf 10");
        of.freeLab();
    }
}
