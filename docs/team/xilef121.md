---
  layout: default.md
  title: "Felix's Project Portfolio Page"
---

### Project: InternBook

InternBook is a desktop intern book application used for managing internship companies and contacts. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.
1) Added user stories and use cases in developer guide in v1.1
2) Created a sort command in v1.2 to allow users to sort list in alphabetical order. This included:
   1) Creating a SortCommand class and integrating it with the current InternBookParser class
   2) Added a test case for the SortCommand class
3) Integrated the Date class to AddCommand in v1.3. This included:
   1) Editing AddCommandParser and AddCommand to allow for adding of companies with dates
   2) Made dates an optional field when adding companies
   3) Modified the gui to display the dates when they are present
   4) Modified the gui to display "No phone number" when phone numbers are not present
   5) Modified the Messages class to show different messages when adding companies depending on whether phone or dates are present
4) Further implemented sort command in v1.3 to allow for different kind of sorting. This included:
   1) Creating an SortType enum for the different type of sort types for Company class
   2) Allowing sort command to take in a sort type as argument, allowing users to choose how they want list to be sorted
   3)Implemented 3 different kind of sorting,
      1) In ascending alphabetical order of company names
      2) In ascending order of company application start date
      3) In ascending order of company application end date
