package com.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.dto.GoalDto;
import com.main.entity.Goal;
import com.main.repository.GoalRepository;

@Service
public class GoalServiceImpl implements GoalService{
	

	    @Autowired
	    private GoalRepository goalRepo;

	    @Override
	    public Goal createGoalService(GoalDto goalDto) {
	        Goal goal = new Goal();
	        goal.setUserId(goalDto.getUserId());
	        goal.setTargetAmount(goalDto.getTargetAmount());
	        goal.setCurrentAmount(goalDto.getCurrentAmount());
	        goal.setGoalName(goalDto.getDescription());
	        goal.setTargetDate(goalDto.getTargetDate());
	        return goalRepo.save(goal);
	    }

	    @Override
	    public Goal getGoalByIdService(int goalId) {
	        return goalRepo.findById(goalId)
	                .orElseThrow(() -> new RuntimeException("Goal not found with ID: " + goalId));
	    }

	    @Override
	    public Goal updateGoalService(int goalId, GoalDto goalDto) {
	        Goal goal = getGoalByIdService(goalId);  // Fetch the existing goal
	        goal.setTargetAmount(goalDto.getTargetAmount());
	        goal.setCurrentAmount(goalDto.getCurrentAmount());
	        goal.setGoalName(goalDto.getDescription());
	        goal.setTargetDate(goalDto.getTargetDate());
	        return goalRepo.save(goal);
	    }

	    @Override
	    public void deleteGoalService(int goalId) {
	        Goal goal = getGoalByIdService(goalId);
	        goalRepo.delete(goal);
	    }

	    @Override
	    public List<Goal> getUserGoalsService(int userId) {
	        return goalRepo.findByUserId(userId);
	    }

	    @Override
	    public double trackGoalProgressService(int goalId) {
	        Goal goal = getGoalByIdService(goalId);
	        return (goal.getCurrentAmount() / goal.getTargetAmount()) * 100;
	    }

}
