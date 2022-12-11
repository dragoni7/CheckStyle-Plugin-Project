# CPT_S 422 SamuelPlugin



## Check Information

## Definitions

Halstead Metrics: 

Based on: https://www.geeksforgeeks.org/software-engineering-halsteads-software-metrics/

Comments are not considered.

The identifier and function declarations are not considered

All the variables and constants are considered operands.

Global variables used in different modules of the same program are counted as multiple occurrences of the same variable.

Functions calls are considered as operators.

All looping statements e.g., do {…} while ( ), while ( ) {…}, for ( ) {…}, all control statements e.g., if ( ) {…}, if ( ) {…} else {…}, etc. are considered as operators.

In control construct switch ( ) {case:…}, switch as well as all the case statements are considered as operators.

The reserve words like return, default, continue, break, sizeof, etc., are considered as operators.

All the brackets, commas, and terminators are considered as operators.

The unary and binary occurrence of “+” and “-” are dealt separately. Similarly “*” (multiplication operator) are dealt separately.
In the array variables such as “array-name [index]” “array-name” and “index” are considered as operands and [ ] is considered as operator.

In the structure variables such as “struct-name, member-name” or “struct-name -> member-name”, struct-name, member-name are taken as operands and ‘.’, ‘->’ are taken as operators. Some names of member elements in different structure variables are counted as unique operands.

Comment Lines:

For block comments '/*' and '*/' are counted as comment lines.

Expressions:

Any token considered as an equation by checkstyle token type.
