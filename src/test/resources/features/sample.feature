Feature: Sample

  Scenario Outline: Open a webpage
    Given I have browser and i open a "<url>"
    And  I Navigate to portal and Identify Broken link On the poratl
    Examples:
      | url                 | title       |
      | https://appwrk.com/ | Breakout La |