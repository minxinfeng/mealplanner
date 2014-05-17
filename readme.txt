>> IDE
	Eclipse Standard/SDK (We use Eclipse Java EE IDE for Web Developers -- Version: Kepler Service Release 2)

>> Install Maven & Jetty
	search in Eclipse Marketplace(Help)
	install Maven Development Tools 0.2.0
	install Eclipse Jetty 3.7.0

>> Import the project
	1. Import - Maven - Existing Maven Projects
	or
	2. Git - Project from Git (URL see the following)

>> Update the jar
	right click project:Maven-> Update Project

>> Add a properties file to folder(about DB)
	Create file your_pc_name.properties under folder src/main/resources/config/ as well as src/test/resources/config/. And the  content you can copy from asus-PC.properties

>> Run the project
	right click project: run as -> run jetty(default port is 8080)

>> Some URL
URL for restaurant to login
http://localhost:8080/mealplanner/web/login

URL for the app service
See the attachment /doc/Service_for_android.pdf

source code of app:
https://github.com/YangruiEmma/MealPlanner_Android

source code of server:
https://github.com/minxinfeng/mealplanner



