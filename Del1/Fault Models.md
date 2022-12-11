# Fault Models

## 1. BaseHalsteadCheck
- 1.1 Count paring operators as two, such as (), {}, [], etc.
- 1.2 Count operand in equation with unary operators wrong: a = -2 + 3.
- 1.3 Failure to ignore comments.
- 1.4 Failure to ignore identifier and function declaration.
- 1.5 Failure to count brackets, commas, and terminators as operators.
- 1.6 Improper counting of array variable.
- 1.7 One operand and operator.
- 1.8 All unique operands and operators.
- 1.9 Multiple same operands and operators.
- 1.10 Counting operand or operator within string object.
- 1.11 No operators or operands.

## 2. HalsteadDifficultyCheck
- 2.1 No operands or operators.
- 2.2 One or more operand and operator.

## 3. HalsteadEffortCheck
- 3.1 No operands or operators.
- 3.2 One or more operand and operator.

## 4. HalsteadLengthCheck
- 4.1 No operands or operators.
- 4.2 One or more operand and operator.

## 5. HalsteadVocabularyCheck
- 5.1 No operands or operators.
- 5.2 One or more operand and operator.

## 6. HalsteadVolumeCheck
- 6.1 No operands or operators.
- 6.2 One or more operand and operator.

## 7. NumberCommentLinesCheck
- 7.1 No comment lines.
- 7.2 One or more comment lines.
- 7.3 Comment lines and block comments.
- 7.4 Only block comments.
- 7.5 Counting '//' string.
- 7.6 Counting '/*' or '*/' string.

## 8. NumberCommentsCheck
- 8.1 No comments.
- 8.2 One or more comments.
- 8.3 Comments and block comments.
- 8.4 Only block comments.
- 8.5 Counting block comments as multiple comments.
- 8.6 Counting '//' string.
- 8.7 Counting '/*' or '*/' string.

## 9. NumberExpressionsCheck
- 9.1 No expressions.
- 9.2 One expression.
- 9.3 Multiple expressions.
- 9.4 Counting expression in string.
- 9.5 Counting expression in comment.

## 10. NumberLoopStatementsCheck
- 10.1 No loops.
- 10.2 One loop.
- 10.3 Multiple loops.
- 10.4 Counting loop in string.
- 10.5 Counting loop in comment.
- 10.6 Not counting nested loops seperately.

## 11. OperandAmountCheck
- covered by BaseHalsteadCheck tests.

## 12. OperatorAmountCheck
- covered by BaseHalsteadCheck tests.
