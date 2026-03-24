package design_problems.personal.heap;

public class Main {
    static class Student implements Comparable<Student> {
        int marks;
        String name;

        public Student(String name, int marks) {
            this.name = name;
            this.marks = marks;
        }

        @Override
        public int compareTo(Student s) {
            return this.marks - s.marks;
        }

        @Override
        public String toString() {
            return "Student: " + name + " with marks: " + marks;
        }
    }

    public static void main(String[] args) {
        MinHeap<Student> maxHeap = new MinHeap<>();

        for(int i = 0; i < 5; i++) {
            maxHeap.add(new Student("S" + i, i * 10 + 100));
        }

        while(!maxHeap.isEmpty()) {
            System.out.println(maxHeap.poll());
        }
    }
}
