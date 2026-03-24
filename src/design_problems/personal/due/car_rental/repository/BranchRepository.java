package design_problems.personal.due.car_rental.repository;

import design_problems.personal.due.car_rental.model.Branch;

import java.util.HashMap;
import java.util.Map;

public class BranchRepository {
    private final Map<String, Branch> branchMap = new HashMap<>();

    public void addBranch(Branch branch){
        branchMap.put(branch.getId(), branch);
    }

    public Branch getBranch(String id){
        return branchMap.get(id);
    }
}