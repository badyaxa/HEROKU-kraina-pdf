# kraina-pdf

# for local development set
environment variable 
 SPRING_PROFILES_ACTIVE=h2

# for heroku 
 Procfile 


# check-and-notify-about-file-updates
- This application checks every hour the field 'Last-Modified' in the URL header.
- Notifies by telegram.
- Sends an email with a link and a file PDF attached to the letter.

# [localhost link (port 8077)](http://localhost:8077)
(to change go in application.yml)

# [herokuapp link (port 80)](https://kraina-pdf.herokuapp.com)

# about heroku sleep time
I remove the internal insomnia scheduler
and set up the service 'http://kaffeine.herokuapp.com/'
now my app sleep 6 hour from 22.00 (GMT+3)
