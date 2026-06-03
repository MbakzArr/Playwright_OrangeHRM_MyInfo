# Team 6 - My Info Module Automation

OrangeHRM capstone project using Java, Playwright, and TestNG.

## Module
My Info - Personal Details, Profile Picture, Contact Details, Emergency Contacts, Dependents, Profile Persistence

## Stack
- Java 17
- Playwright 1.44
- TestNG 7.10
- Extent Reports 5.1
- Apache POI 5.2
- Maven

## Project Structure

```
team6_capstone/
├── pom.xml
├── testng.xml
└── src/
    ├── main/java/
    │   ├── base/
    │   │   ├── BaseTest.java
    │   │   └── BasePage.java
    │   ├── pages/
    │   │   ├── LoginPage.java
    │   │   ├── MyInfoPage.java
    │   │   ├── PersonalDetailsPage.java
    │   │   ├── ProfilePicPage.java
    │   │   ├── ContactDetailsPage.java
    │   │   ├── EmergencyContactPage.java
    │   │   └── DependentsPage.java
    │   └── utils/
    │       ├── ConfigReader.java
    │       ├── LocatorReader.java
    │       ├── WaitUtils.java
    │       ├── ScreenshotUtil.java
    │       └── ExtentManager.java
    └── test/
        ├── java/tests/
        │   ├── LoginTest.java
        │   ├── MyInfoTest.java
        │   ├── UploadImageTest.java
        │   ├── ContactDetailsTest.java
        │   ├── EmergencyContactTest.java
        │   ├── DependentsTest.java
        │   └── ProfilePersistenceTest.java
        └── resources/
            ├── config.properties
            ├── locators.properties
            └── testdata/
                └── profile.jpg
```

## Before Running

1. Make sure Java 17 and Maven are installed
2. Place a profile.jpg image in src/test/resources/testdata/
3. Run dependencies: mvn dependency:resolve

## How to Run

Full suite:
mvn clean test

Smoke only:
mvn clean test -Dgroups=smoke

Regression only:
mvn clean test -Dgroups=regression

Single test:
mvn clean test -Dtest=LoginTest

## Reports

After running, open the report at:
target/extent-report.html

Screenshots on failure are saved to:
target/screenshots/

## Test Order

Smoke runs first (LoginTest), then Regression in this order:
1. MyInfoTest
2. UploadImageTest
3. ContactDetailsTest
4. EmergencyContactTest
5. DependentsTest
6. ProfilePersistenceTest (always last - depends on data from above)

## Notes

- All tests share one browser session - login happens once
- ProfilePersistenceTest logs out, then re-logs in to verify data survives a session change
- If the OrangeHRM demo site is down, tests will skip gracefully
- Screenshots are taken automatically on any test failure
