package com.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.dto.GoalDto;
import com.main.entity.Goal;
import com.main.service.GoalService;

@RestController
@RequestMapping("/api/goals")
public class GoalController {

	 @Autowired
	    private GoalService goalService;

	    // Create a new goal
	    @PostMapping
	    public ResponseEntity<Goal> createGoal(@RequestBody GoalDto goalDto) {
	        Goal createdGoal = goalService.createGoalService(goalDto);
	        return new ResponseEntity<>(createdGoal, HttpStatus.CREATED);
	    }

	    // Get a goal by ID
	    @GetMapping("/{goalId}")
	    public ResponseEntity<Goal> getGoalById(@PathVariable int goalId) {
	        Goal goal = goalService.getGoalByIdService(goalId);
	        return new ResponseEntity<>(goal, HttpStatus.OK);
	    }

	    // Update a goal
	    @PutMapping("/{goalId}")
	    public ResponseEntity<Goal> updateGoal(@PathVariable int goalId, @RequestBody GoalDto goalDto) {
	        Goal updatedGoal = goalService.updateGoalService(goalId, goalDto);
	        return new ResponseEntity<>(updatedGoal, HttpStatus.OK);
	    }

	    // Delete a goal
	    @DeleteMapping("/{goalId}")
	    public ResponseEntity<Void> deleteGoal(@PathVariable int goalId) {
	        goalService.deleteGoalService(goalId);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }

	    // Get all goals for a user
	    @GetMapping("/user/{userId}")
	    public ResponseEntity<List<Goal>> getUserGoals(@PathVariable int userId) {
	        List<Goal> goals = goalService.getUserGoalsService(userId);
	        return new ResponseEntity<>(goals, HttpStatus.OK);
	    }

	    // Track progress of a specific goal
	    @GetMapping("/{goalId}/progress")
	    public ResponseEntity<Double> trackGoalProgress(@PathVariable int goalId) {
	        double progress = goalService.trackGoalProgressService(goalId);
	        return new ResponseEntity<>(progress, HttpStatus.OK);
           }

}
