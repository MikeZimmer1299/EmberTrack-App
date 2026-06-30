# EmberTrack
## The Purpose
- In my free time, I find joy in smoking premium cigars and drinking fine spirits in good company. The cigars I smoke, and buy, and the whiskey I collect is all stored in my phone's photo gallery.
- While all photos are in a single folder, it becomes time consuming to scroll through the few thousand photos to find a specific cigar or whiskey that may only have one photo referencing it. This app will fix this problem.
- EmberTrack is purpose built for cigar and whiskey enjoyers. It allows the user to store their collection of cigars and whiskey in an organized manner, along with saving reviews they have for cigars or whiskey they've enjoyed.
- The rating system for both is as follows:
	- Cigars are based off the following categories: draw, burn, construction, flavors, aroma, smoke production, and experience, and then they have a separate section that allows the user to type their own notes on the cigar.
	- The whiskey, on the other hand, will be aromatic and flavor notes picked up from the nose, flavor, and finish.
- For both categories, there are also things to denote from lists: who made the cigar/whiskey, the name of the product, where it came from, and others.
## The Developer's Path
- I believe an Android application is a good way to test the ability of any software developer, along with new avenues for a dev to learn.
- It takes the groundwork of what has been learned, in both college classes and free time, to create a product which may be used by more than the maker.
- Most of my experience has involved Java with backend and simple command line interfaces, C++ for data structure education, and frontend environments with HTML/CSS/JavaScript. With an Android application, it will allow me to join the concepts of frontend, backend, and everything in between to create an application I can use in my daily life.
## The Primary App Screens
### Home Screen
- Most recent cigar and whiskey review
- 3 most recently added cigars to user's collection
- 3 most recently added whiskeys to user's collection
### My Cigars
- Initially show all cigars
- Allow user to view cigars through filters:
	- Brand
	- Name
	- Country of Origin
### My Whiskey
- Initially show all whiskeys
- Allow users to view whiskeys through filters:
	- Brand (drop down menu)
	- Location of origin (drop down menu)
		- Country if non-American, Country/State if American
	- Proof (user input)
### Cigar Reviews
- This will allow the user to view previous reviews and add a new review
	- The new review is done through a button to come to the new review screen
	- Old reviews will be brought to a different screen, which will show the full results the user originally chose and noted
### Whiskey Reviews
- Same as Cigar Reviews, this will allow the user to view previous reviews and add a new review
	- The new review will be done through a button to come to the new review screen
	- Old reviews will be brought to a different screen, which will show the full results the user originally chose and noted
## The Secondary App Screens
### New Cigar Reviews
- Each rating category has rounded edge blocks, from 1 to 10, to allow the user to select the rating
- Cigar brand and name are within the same card
- Cigar length and ring gauge are within the same card
- A cigar's draw, burn, construction, flavors, aroma, smoke production, and experience ratings will have their own cards
- The user is also able to add their own personal notes to store their thoughts on the cigar enjoyed
### New Whiskey Reviews
- Much simpler than the Cigar Reviews, as there is less to rate
- Whiskey brand, name, and proof are within the same card
- There are separate text boxes to leave thoughts on the following:
	- Flavors, aroma, finish
- There is a rating scale, exactly like for Cigar Reviews, that allows the user to rate the whiskey from 1-10 based on their overall enjoyment
### Add Cigars
- This will allow the user to add cigars to their chosen humidor(s)
- User will be able to choose:
	- Cigar Brand/Name/Origin
	- Length & Ring Gauge
	- Quantity
### Add Whiskey
- This will allow the user to add whiskey to their collection
- Users will be able to choose:
	- Whiskey Brand/Name
	- Location/Origin
	- Proof
	- Size
## Database
- To allow long term storage of the cigar/whiskey collections and reviews, there needs to be a database stored onto the device's local storage
- The app is using Room, which implements an SQLite database
	- The main types of Entities are `MyCigars`, `MyWhiskey`, `CigarReviews`, and `WhiskeyReviews`
## Long Term Plan
- Once the app is in a fully working state, I plan to use an AWS instance to allow users to attach their collections to a cloud environment.
  - I am deciding on Prisma or Drizzle ORM for the database management. Prisma seems to be the most popular option while doing research, so I may decide on that.
  - For frontend/backend communication, I will be using Axios library for making API calls.
- With this cloud environment, they would be able to add friends and view those friends' collection of cigars and whiskey, along with viewing their friends' reviews of cigars smoked and whiskey drank
- I am also planning on building a website, which would have access to the cloud and give users the option to view/add to their collections or reviews through their web browser.
  - For the website's access to the database, I will be using Express.js to connect the frontend to the SQLite database. For a simple project like this, Express is the best option to work alongside Node.js.
  - Because I am also utilizing Vite, I will be utilizing Tailwind CSS as well.