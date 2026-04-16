# CigarWhiskey-KotlinApp
## The Purpose
- In my free time, I find joy in smoking premium cigars and drinking fine spirits in good company. The cigars I smoke, and buy, and the whiskey I collect is all stored in my phone's photo gallery.
- While all photos are in a single folder, it becomes time consuming to scroll through the few thousand photos to find a specific cigar or whiskey that may only have one photo referencing it. This app will fix this problem.
- The app will allow photos for the cigars and whiskey to be grouped in a few different ways, such as brand, country of origin, proof for whiskey, and size for cigars.
- Along with the grouping, the cigars will have their own rating system.
	- Cigars are based off the following categories: draw, burn, construction, flavors, aroma, smoke production, and experience.
- The whiskey, on the other hand, will be aromatic and flavor notes picked up from the nose, palate, and finish. There are fewer avenues to rank whiskey than there is for cigars, from my experience.
## The Developer's Path
- I believe an Android application is a good way to test the ability of any software developer, along with new avenues for a dev to learn.
- It takes the groundwork of what has been learned, in both college classes and free time, to create a product which may be used by more than the maker.
- Most of my experience has involved Java with backend and simple command line interfaces, C++ for data structure education, and frontend environments with HTML/CSS/JavaScript. With an Android application, it will allow me to join the concepts of frontend, backend, and everything in between to create an application I can use in my daily life.
## The Primary App Screens
### Home Screen
- Most recent cigar and whiskey review
- List of highest rated cigar and whiskey reviews from user
- Random cigar and whiskey brand to give user option to read about the brand
### My Cigars
~~#### If 2+ Humidors~~
~~- Initial screen will display 3+ options:~~
  ~~- View All Cigars (across all humidors)~~
	~~- View Humidor *name1* Cigars~~
	~~- View Humidor *name2* Cigars~~
	~~- Continue trend for every other humidor~~
#### If 1 Humidor or looking inside chosen Humidor
- Initially show all cigars
- Allow user to view cigars through filters:
	- Brand
	- Country of Origin
	- Chosen length / ring gauge
		- Length min to length max
		- Ring gauge min to ring gauge max
### My Whiskey
- Initially show all whiskeys
- Allow users to view whiskeys through filters:
	- Brand (drop down menu)
	- Location of origin (drop down menu)
		- Country if non-American, Country/State if American
	- Proof (user input)
### Cigar Brands
- Initially show all brands in alphabetical order
- Filter based on country of origin
### Whiskey Brands
- Initially show all brands in alphabetical order
- Filter based on country (and region) of origin
### Cigar Reviews
- This will allow the user to view previous reviews and add a new review
	- The new review will be done through a button to come to the new review screen
	- Old reviews will be brought to a different screen, which will show the full results the user originally chose (and eventually their own notes once that is implemented)
### Whiskey Reviews
- Same as Cigar Reviews, this will allow the user to view previous reviews and add a new review
	- The new review will be done through a button to come to the new review screen
	- Old reviews will be brought to a different screen, which will show the full results the user originally chose (and eventually their own notes once that is implemented)
## The Secondary App Screens
### New Cigar Reviews
- Each rating category will have a line with hash marks, from 0 to 10, to allow the user to select the rating
- Cigar brand and name are within the same card
- Cigar length and ring gauge are within the same card
- A cigar's draw, burn, construction, flavors, aroma, smoke production, and experience ratings will have their own cards
	- The user will (eventually) have the option to type their own notes to attach to this new review, which will be its own card with a text box
### New Whiskey Reviews
- Much simpler than the Cigar Reviews, as there is less to rate
- Whiskey brand, name, and proof are within the same card
- There will be a rating scale (1-10)
	- Flavors, aroma, viscosity, 
### Add Cigars
- This will allow the user to add cigars to their chosen humidor(s)
- User will be able to choose:
	- Cigar Brand/Name/Origin
	- Length & Ring Gauge
	- Humidor & Quantity
### Add Whiskey
- This will allow the user to add whiskey to their collection
- Users will be able to choose:
	- Whiskey Brand/Name
	- Location/Origin
	- Proof
## Database
- To allow long term storage of the cigar/whiskey collections and reviews, there needs to be a database stored onto the device's local storage
- The app is using Room, which implements an SQLite database
	- The main types of Entities are `MyCigars`, `MyWhiskey`, `CigarReviews`, `WhiskeyReviews`, and `MyHumidor`
## Long Term Plan
- Currently, the humidor objects are put on standby, until the app is in a closer state of completion. I was having a difficult time initializing the first `humidor` object and adding cigars to it. The humidor itself is not a requirement for the app to server its intended purpose, so I am leaving it for the time being.
- Once the app is in a fully working state, I plan to use an AWS instance to allow users to attach their collections to a cloud environment.
- With this cloud environment, they would be able to add friends and view those friends' collection of cigars and whiskey, along with viewing their friends' reviews of cigars smoked and whiskey drank
- I am also planning on building a website, which would have access to the cloud and give users the option to view/add to their collections or reviews through their web browser.
