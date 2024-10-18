//package com.main.service;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.main.dto.GoalDto;
//import com.main.entity.Goal;
//import com.main.repository.GoalRepository;
//
//@Service
//public class GoalServiceImpl implements GoalService{
//	
//
//	    @Autowired
//	    private GoalRepository goalRepo;
//
//	    @Override
//	    public Goal createGoal(GoalDto goalDto) {
//	        Goal goal = new Goal();
//	        goal.setUser(goalDto.getUserId());
//	        goal.setTargetAmount(goalDto.getTargetAmount());
//	        goal.setCurrentAmount(goalDto.getCurrentAmount());
//	        goal.setGoalName(goalDto.getDescription());
//	        goal.setTargetDate(goalDto.getTargetDate());
//	        return goalRepo.save(goal);
//	    }
//
//	    @Override
//	    public Goal getGoalById(int goalId) {
//	        return goalRepo.findById(goalId)
//	                .orElseThrow(() -> new RuntimeException("Goal not found with ID: " + goalId));
//	    }
//
//	    @Override
//	    public Goal updateGoal(int goalId, GoalDto goalDto) {
//	        Goal goal = getGoalById(goalId);  // Fetch the existing goal
//	        goal.setTargetAmount(goalDto.getTargetAmount());
//	        goal.setCurrentAmount(goalDto.getCurrentAmount());
//	        goal.setGoalName(goalDto.getDescription());
//	        goal.setTargetDate(goalDto.getTargetDate());
//	        return goalRepo.save(goal);
//	    }
//
//	    @Override
//	    public void deleteGoal(int goalId) {
//	        Goal goal = getGoalById(goalId);
//	        goalRepo.delete(goal);
//	    }
//
//	    @Override
//	    public List<Goal> getUserGoals(int userId) {
//	        return goalRepo.findByUserId(userId);
//	    }
//
//	    @Override
//	    public double trackGoalProgress(int goalId) {
//	        Goal goal = getGoalById(goalId);
//	        return (goal.getCurrentAmount() / goal.getTargetAmount()) * 100;
//	    }
//
//}
