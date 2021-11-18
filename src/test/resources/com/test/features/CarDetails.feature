Feature: car details with reg number

  @carTaxCheck
  Scenario: Verify car make, model, color and year with given car reg number as input and compare the results with a file
    Given user reads the input file "car_input.txt" and extracts the car registration numbers
    When user enters the extracted registered numbers into cartaxcheck website and saves the details of
  |Make|
  |Model|
  |Colour|
  |Year |
    Then compared the details with the output file name "car_output.csv" and display any mismatches exists



