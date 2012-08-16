/**
 * Project: ThinkingInJAVA
 * 
 * File Created at 2011-5-20
 * $Id$
 * 
 * Copyright 1999-2100 Alibaba.com Corporation Limited.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Alibaba Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Alibaba.com.
 */
package com.liubida.ThinkingInJAVA.typeinfo;

import java.util.ArrayList;

/**
 * 
 * @author liubida
 */
class Person {
    public final String first;
    public final String last;
    public final String address;

    public Person(String firstName, String lastName, String address) {
        this.first = firstName;
        this.last = lastName;
        this.address = address;
    }

    public String toString() {
        return first + " " + last + ", " + address + "\n";
    }

    public static class NullPerson extends Person {
        public NullPerson() {
            super("None", "None", "None");
        }
    }

    public static final NullPerson NULL = new NullPerson();
}

class Position {
    private String title;
    private Person person;

    public Position(String jobTitle) {
        setTitle(jobTitle);
        setPerson(Person.NULL);
    }

    public Position(String jobTitle, Person employee) {
        this.setTitle(jobTitle);
        if (null == employee) {
            this.setPerson(Person.NULL);
        } else {
            this.setPerson(employee);
        }
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Person getPerson() {
        return person;
    }

    public String toString() {
        return "Position: " + title + " " + person;
    }
}

public class Staff extends ArrayList<Position> {
    private static final long serialVersionUID = 1L;

    public Staff(String... titles) {
        add(titles);
    }

    public boolean add(String title, Person person) {
        return add(new Position(title, person));
    }

    public void add(String... titles) {
        for (String s : titles) {
            add(new Position(s));
        }
    }

    public boolean positionAvailable(String title) {
        for (Position p : this) {
            if (p.getTitle().equals(title) && p.getPerson() == Person.NULL) {
                return true;
            }
        }
        return false;
    }

    public void fillPosition(String title, Person employee) {
        for (Position p : this) {
            if (p.getTitle().equals(title) && p.getPerson() == Person.NULL) {
                p.setPerson(employee);
                return;
            }
        }
        throw new RuntimeException("Position " + title + " not available");
    }

    public static void main(String[] args) {
        Staff staff = new Staff("President", "CTO", "Marketing Manager", "Product Manager",
                "Project Lead", "Software Engineer", "Software Engineer", "Software Engineer",
                "Software Engineer", "Test Engineer", "Technical Writer");
        staff.fillPosition("President", new Person("Me", "Last", "The Top, Lonely At"));
        staff.fillPosition("Project Lead", new Person("Janet", "Planner", "The Burbs"));
        if (staff.positionAvailable("Software Engineer"))
            staff.fillPosition("Software Engineer", new Person("Bob", "Coder", "Bright Light City"));
        System.out.println(staff);
    }
}
