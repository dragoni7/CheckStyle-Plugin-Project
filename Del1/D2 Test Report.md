# Test Report

# Coverage
Coverage was determined by Eclipse's built in coverage tool.

## BaseHalsteadCheck
Coverage: 100%
## HalsteadDifficultyCheck
Coverage: 100%
## HalsteadEffortCheck
Coverage: 100%
## HalsteadLengthCheck
Coverage: 100%
## HalsteadVocabularyCheck
Coverage: 100%
## HalsteadVolumeCheck
Coverage: 100%
## NumberCommentLinesCheck
Coverage: 100%
## NumberCommentsCheck
Coverage: 100%
## NumberExpressionsCheck
Coverage: 100%
## NumberLoopStatementsCheck
Coverage: 100%
## OperandAmountCheck
Coverage: 100%
## OperatorAmountCheck
Coverage: 100%
## HalsteadMath
Coverage: 97%

# Test Cases
Tests cases written for check classes with branch and statement coverage in mind. Test for True and False in each branch were created. Commments indicating the branch's line location and T or F coverage was included with each test. For instance, for several Halstead checks, branches were tested by passing the acceptable token, and then an unacceptable token.
For HalsteadMath tests, branch and statement coverage was also the goal.

# Results

Tests for HalsteadMath uncovered a bug when inputting numbers less than 0 for all functions. This was fixed with additional conditionals along with appropriate tests for each function.
Tests for HalsteadMath also uncovered formatting errors in functions that returned doubles. This was fixed by using BigDecimal in those functions
Tests for HalsteadMath also uncovered incorrect formulas for HalsteadLength. This was fixed by changing the equation

Overall full coverage of current check code was achieved


