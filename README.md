# BalanceMath

BalanceMath is a small math experiment built around a custom operation.

## The rule

The operation is:

**A ◇ B = A + B + |A - B| / gcd(A, B)**

Here, **gcd** means the greatest common divisor — the largest whole number that divides both A and B.

### Example

Take:

**A = 12**  
**B = 8**

First:

- 12 + 8 = 20
- |12 - 8| = 4
- gcd(12, 8) = 4
- 4 / 4 = 1

So:

**12 ◇ 8 = 20 + 1 = 21**

Another example:

**9 ◇ 6 = 9 + 6 + |9 - 6| / gcd(9, 6)**

- 9 + 6 = 15
- |9 - 6| = 3
- gcd(9, 6) = 3
- 3 / 3 = 1

Therefore:

**9 ◇ 6 = 16**

## How the program works

Enter whole numbers for **A** and **B**, then press **Calculate**.

The program:

1. Reads the two numbers.
2. Finds their greatest common divisor.
3. Finds the absolute difference between them.
4. Divides the difference by the gcd.
5. Adds that result to A + B.
6. Shows the final answer.

There is also a **Test properties** button. Enter A, B, and C to compare:

**(A ◇ B) ◇ C**

with:

**A ◇ (B ◇ C)**

This lets you experiment with whether the operation is associative for the numbers you choose.

The calculator also checks:

**A ◇ B**

against:

**B ◇ A**

so you can see that the operation gives the same result when the two inputs are switched.

## Quick demo

Try these:

| A | B | Result |
|---:|---:|---:|
| 12 | 8 | 21 |
| 9 | 6 | 16 |
| 8 | 4 | 13 |
| 10 | 3 | 20 |

For example, with **8 and 4**:

**8 ◇ 4 = 8 + 4 + 4 / 4 = 13**

## What is this for?

BalanceMath is mainly an experiment. It gives you a way to play with the operation, test patterns, and see what happens with different numbers.

It is not being presented as proof that this operation is mathematically unique or that nobody has ever used an equivalent formula before.
