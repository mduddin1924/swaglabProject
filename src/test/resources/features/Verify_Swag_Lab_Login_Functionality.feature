Feature: Swag Lab Login Functionality Test

  Description:
    As a User
    I Want to Visit as a User
    So that I can see Home Page

  Background:
    Given user in the landing page

#  Scenario: Verify Swag Lab Login Functionality
#    # Given user in the landing page
#    And user enter username and password
#    Then verify the login button color and click on login button
#    And user click on menu button
#    Then user click on logout

#  Scenario Outline: Verify Swag Lab Login Functionality
#    # Given user in the landing page
#    And user enter "<userName>" and "<passWord>"
#    Then verify the login button color and click on login button
#    And user click on menu button
#    Then user click on logout

#    Examples:
#      | userName                | passWord     |
#      | standard_user           | secret_sauce |
#      | locked_out_user         | secret_sauce |
#      | problem_user            | secret_sauce |
#      | performance_glitch_user | secret_sauce |
#      | error_user              | secret_sauce |
#      | visual_user             | secret_sauce |


  Scenario Outline: Verify Swag Lab Login Functionality From Excel
    # Given user in the landing page
    And user enter id and password from sheet "<sheetName>" and row <rowNumber>
    Then verify the login button color and click on login button
    And user click on menu button
    Then user click on logout

    Examples:
      | sheetName    | rowNumber |
      | swgLoginData | 0         |
      | swgLoginData | 1         |
      | swgLoginData | 2         |
      | swgLoginData | 3         |
      | swgLoginData | 4         |
      | swgLoginData | 5         |






