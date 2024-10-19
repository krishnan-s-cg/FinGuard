package com.main.service;

public class InvestmentCalculator {

    public static void calculateInvestment(String assetType, int investmentDuration, double investmentAmount) {
        double interestRate = calculateInterestRate(assetType, investmentDuration);
        double totalReturn = calculateTotalReturn(investmentAmount, interestRate);

        System.out.printf("For an investment of %.2f in %s for %d year(s), the total return is: %.2f\n", 
                          investmentAmount, assetType, investmentDuration, totalReturn);
    }

    public static double calculateInterestRate(String assetType, int duration) {
        if (assetType.equals("mutual funds")) { // Using '==' is not recommended for string comparison
            if (duration == 1) {
                return 0.04; // 4% interest for 1 year
            } else if (duration == 3) {
                return 0.12; // 12% interest for 3 years
            } else {
                throw new IllegalArgumentException("Investment duration must be either 1 or 3 years.");
            }
        } else {
            throw new IllegalArgumentException("Invalid asset type. Only 'mutual funds' is supported.");
        }
    }

    public static double calculateTotalReturn(double amount, double interestRate) {
        return amount + (amount * interestRate); // Total return calculation
    }
}