# Mortagege Calculator
Final project for Java Fundamentals Bootcamp with zajavka.pl between July 2022 and October 2022. The purpose of this project was to show understanding of Object Oriented Programming concept and it's core functionality by applying some popular design patterns like Builder pattern.

## Project assumtions:

------------------------------------

### <u>Main goals</u>

    This project is made to simulate mortgage calculator (for local
    Polish market) which provides informations about hypothetical 
    interest rates, future historical payment time of mortgage 
    and general cost of mortgage.

    Program should provide ability to calculate total cost 
    of the mortgage when excess payment happens.

### <u>Program settup</u>

The mortgage calculator works as follows:

* Program sets default interest rates.
* User sets loan value one's want to take.
* Program returns total cost of mortgage including:
    1. number of installment
    2. interest cost
    3. credit end date
* Program calculates second scenario where installments
  are decreasing.
* If user overpaid value of installment during initial
  mortgage period , extra provision is charged
* Program is NOT including:
  * cost of insurances 
  * different WIBOR types
  * bank provision cost for giving the loan
  * cost of holding bank account and credit card

### <u>Input values:</u>

* loan value
* interest rates
* mortgage duration

### <u>Setup:</u>

* installment types:
    - fixed or,
    - decreasing
* loan cost per month (excluding interest rates)
* loan interest rates cost per month
* loan total cost including interest rates
* contracted period of time when user should not overpay
  loan
* provision cost value for user who overpaid installment
  during initial time of mortgage contract
* if the user overpays the installment, the installment
  cost continues unchanged and the credit end date will be shortened
* else the credit end date continues but the installment
  cost will decrease
    * [the duration of the loan / loan value = installment value]

### <u>Setup cases</u> ###

1. interest rates fixed, installment not overpaid
2. interest rates decreasing, installment not overpaid
3. interest rates fixed, installment is overpaid band the loan end date is fixed
4. interest rates decreasing, installment is overpaid and the loan end date is fixed
5. interest rates fixed, installment is overpaid and the loan end date is shortening
6. interest rates decreasing, installment is overpaid and the loan end date is shortening
7. installment is overpaid during initial time of mortgage contract and extra provision is charged
8. installment is overpaid while initial time of mortgage finished

### <u>Formula for calculating installments:</u>

#### Fixed:
```
    R = A * (q ^n) * (q - 1) / [(q ^ n) - 1]
    
    q = 1+ (b / 12) // q = 1 + (4,75% / 12) = 1,00392
```
*Legend:*

    R - installment value
    A - loan value
    n - number of installment to be paid (loan period 'l' -> multiplied by 12 months.
    b - interest rates per year (interest rates plus const of bank margin)
    C - total loan cost left to be paid

#### Decreasing installments:
```
    R = A * [1 + (N - n + 1) * (r / m)] / N
```
*Legend:*
    
    R - installment value
    A - loan value
    N - total number of installment to be paid
    n - number of installment
    r - interest rates per year
    m - months value (default 12)
    




