package design_problems.personal.atm_machine.inventory;

import java.util.HashMap;
import java.util.Map;

public class CashInventory {
    private final int[] denominations = {2000, 500, 100};

    Map<Integer, Integer> noteCountMap;

    int availableCash;

    public CashInventory() {
        availableCash = 0;
        noteCountMap = new HashMap<>();
        for(int denomination: denominations) {
            noteCountMap.put(denomination, 0);
        }
    }

    public void loadCash(int denomination, int count) {
        noteCountMap.put(denomination, noteCountMap.get(denomination) + count);
        availableCash += denomination * count;
    }

    public void dispenseAmount(int amount) {
        if(amount > availableCash) {
            throw new RuntimeException("ATM has insufficient cash");
        }

        if(!canDispense(amount)) {
            throw new RuntimeException("Cannot dispense requested amount with available denominations.");
        }

        for(int denomination: denominations) {
            if(amount <= 0) break;

            int requiredNotes = amount / denomination;
            int availableNotes = noteCountMap.get(denomination);

            int notesToUse = Math.min(requiredNotes, availableNotes);

            if (notesToUse > 0) {
                noteCountMap.put(denomination, noteCountMap.get(denomination) - notesToUse);
                System.out.println("Dispensed " + notesToUse + " x " + denomination);
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    System.out.println("Exception occurred");
                }
            }

            amount -= notesToUse * denomination;
        }
    }

    public boolean canDispense(int amount) {
        for(int denomination: denominations) {
            if(amount <= 0) break;

            int requiredNotes = amount / denomination;
            int availableNotes = noteCountMap.get(denomination);

            int notesToUse = Math.min(requiredNotes, availableNotes);

            amount -= notesToUse * denomination;
        }
        return amount == 0;
    }

    public int getAvailableCash() {
        return this.availableCash;
    }
}
