# Sports-Fanbook

This is a simple application for sports enthusiast to track their favourite team game history. You can search your team using their 
full or partial name and all teams names starting with the search term will display in a list view, alongwith the sports that team 
is related to. Further you can select your team from the list and watch its last 5 games history.

Future work :

1. Favourites feature : When a user clicks on “heart” ie.. he adds a team to his favourite list, this favourite list will be shown every time the use opens the app and before he even searches anything in the search bar. Thus making users' life easy.

2. Using the “/eventsnext/” api endpoint we can fetch for upcoming games of users favourite list and send push notifications to users when the match is about to be telecasted. 
Ex : Fetch the next 5 events for a team https://www.thesportsdb.com/api/v1/json/1/eventsnext.php?id=133604

3. Match Highlights : using the  “/eventshighlights/” to show the event highlight (vidoe) 



# Monetization Stratergy 
Please read to the file on this link
https://drive.google.com/open?id=1ccfNLeZuSTWHSJ0ilzKmRxl7gRwAcNUv

# Technology Stack
		 
   	Kotlin
    MVVM
    Coroutines
    Retrofit2
    Dagger2
	Android Architecture Components
 	 -DataBiniding
	 -LiveData
	 -Room
	Recycler View
	
# UI 
	Material Design Principles
	Supports Dark Mode and Light Mode < See preview images>
# Reference
API :: https://www.thesportsdb.com/api.php

UI  :: https://material.io/
# App Screenshots

# DarkMode : 
![Search Screen](/images/search_darkmode.jpg)

![Team List Screen](/images/teamlist_darkmode.jpg)

![Game History Screen](/images/matchhistory_darkmode.jpg)

# LightMode : 

![Search Screen](/images/search.jpg)

![Team List Screen](/images/teamlist.jpg)

![Game History Screen](/images/gamehistory.jpg)

