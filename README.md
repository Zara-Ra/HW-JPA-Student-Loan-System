# HW-JPA-Student-Loan-System
# Student Loan Management Console Application
## Overview
This console application is designed to manage and provide various types of loans, including Education loans, Tuition loans, and Housing loans, to students. Each loan type comes with specific eligibility criteria, ensuring that not every student is eligible for a loan at any given time.

## Key Features
**User Registration**: Students can register in the system by entering their information.

**Password Validation**: User passwords are required to meet specific criteria, including being 8 characters long, containing at least one uppercase letter, one lowercase letter, one special character, and one number. Password validation is performed using regular expressions (regex).

**Data Validation**: Beyond passwords, all other user-entered information, such as National Numbers, Credit Card details, and dates, is also rigorously validated to ensure data integrity.

**Loan Application Periods**: Students can apply for loans during two specific periods in the year, ensuring organized loan processing.

**Stepped Loan Installments**: Loan installment amounts vary annually, calculated with a 4% interest rate and a 20% annual increase. This ensures flexibility and manageable repayment plans.

**Degree-Specific Repayment**: Repayment schedules are tailored to students' academic degrees, whether they are pursuing a Bachelor's, Master's, Ph.D., or other degrees. Each degree type has a different repayment timeline.

## Technologies Used
**JPA and Hibernate**: The application utilizes JPA (Java Persistence API) with Hibernate as the persistence unit to interact with the database efficiently.

**JUnit Testing**: Comprehensive unit tests have been implemented using JUnit to ensure the reliability of the service layer methods.

**Composite Primary Key**: The Payment entity features a composite primary key, which is handled using the @IdClass annotation in JPA.

**Functional Interfaces**: In the 'util' package, a custom @FunctionalInterface has been defined, used for validating user-entered data effectively.
