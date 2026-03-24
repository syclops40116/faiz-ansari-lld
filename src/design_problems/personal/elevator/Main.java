package design_problems.personal.elevator;

import design_problems.personal.elevator.service.ElevatorSystem;

public class Main {
    public static void main(String[] args) {
        ElevatorSystem system = new ElevatorSystem(2);

        system.requestLift(2, 8);
        system.requestLift(3, 1);
        system.requestLift(6, 9);
        system.requestLift(7, 0);

        // Simulation loop
        for (int i = 0; i < 10; i++) {

            System.out.println("---- Step " + i + " ----");

            system.step();

            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println("exception occurred");
            }
        }
    }
}
