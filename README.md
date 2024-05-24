Job Board is a complete RESTAPI job application. JWT authentication has been used to secure user and company profiles.
The primary entities present in my project are :
1) Company
2) Job
3) Review
4) Applications
5) User
6) Role

The complete flow of the application is as follows:
In my application, I have used role based authentication i.e. a specific role gets assigned to each user while doing the registration.
Roles include : USER, ADMIN, SUPER_ADMIN.
Now lets understand the permissions for each role.

1) **ADMIN view** :
Let's say a user John is in the Talent Acquisition team of XYZ company. He wants to post job openings and hire a candidate from my application.
First of all John will create an admin account in the application. (Registration as ADMIN)
Now, John will create a company named XYZ on my application.
John will have full access of that particular company page. He can update the company details, post job openings, accept or reject a particular application.

2) **USER view** : 
Let's say a user Charles is looking for a job and wants to check and apply to relevant openings present on my appplication.
First of all Charles will create an user account in the application. (Registration as USER)
Now, Charles can view different companies, review a particular company, apply to multiple job openings & keep a track of his application.

3) **SUPER_ADMIN view** : 
Now, I have the SUPER_ADMIN access of the application.
I will have full control over the application. As we know a user can review a company and submit a feedback, when a company receives lot of negative reviews saying that particular company is fraud then me as a SUPER_ADMIN can delete that company from my application.

**This was the entire flow of the application. Now let's understand the relational mappings present in different entities.**
1) User and Company has One to one mapping i.e. a user with ADMIN role can create only one company page on this application.
2) Company and Job has One to many mapping i.e a company can have multiple job openings associated with.
3) Job and Application has One to many mapping i.e. a job can have multiple applications from different  users.

**Now some security related concerns have also been taken care of in this application :**
1) A user with USER role can submit only one application to a particular job opening.
2) A user with ADMIN role can only update or view the applications of a job opening associated with his company only i.e he can't access the company page which is not created by him.
3) A user with USER role can't access the other applications present from different users for a particular job opening.

**Java mail sender has also been used here :**
When some user registers on this app then he/she gets an email. (Registration success mail)
When some user submits an application then he/she gets an email. (Application received mail)
When admin accepts or rejects a particular application then user who had submitted that application will receive and email. (Application update mail)

**Technologies & Tools Used :** Java, Kotlin, Springboot, JWT authentication, Java mail sender, SQL database, MySQL workbench, Postman.

**To setup and use this application on your local machine,**
Install and setup MySQL workbench, Postman on your system.
Clone this repository.
In src/main, create a resources folder and in that create a application.properties file.
Setup your own database and email sender configuration in that file.

**You are good to go :)**
