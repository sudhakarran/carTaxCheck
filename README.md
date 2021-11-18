1. If the test is running from mac machine, make sure the compatible chromedriver is placed in the location '/usr/local/bin'
2. If the error "“chromedriver” cannot be opened because the developer cannot be verified" is coming to open the browser
    run this command from terminal as sudo user 'xattr -d com.apple.quarantine /usr/local/bin/chromedriver'
3. To execute the test, run the command 'mvn clean test'
4. Once the test is executed the report report.html is generated in target directory
