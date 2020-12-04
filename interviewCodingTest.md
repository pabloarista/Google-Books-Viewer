# otf-android-take-home
otf-android-take-home

Use the google books API to build an Android app to query and view different types of books.

API: 

https://www.googleapis.com/books/v1/volumes?q=cats
q="cats" (query term returns first 40 results)

https://www.googleapis.com/books/v1/volumes?q=cats&startIndex=21&maxResults=20 
q="cats" (query term)
startIndex = 21 (start index)
maxResults = 20 (number of results returned)

Requirements:

1. Create a search keyword input screen with edit box and search button.

2. When the search button is pressed query the API and display a nicely formatted list of returned books, on a separate screen, showing small thumbnail, title, authors, and publish date (any books without a thumbnail should show a placeholder image). The list should be paginated in groups of 20 items (Show a "Load More" button at bottom of list that when clicked appends the next set of items to the list).

3. When a searched list item is clicked, launch a nicely formatted details view that shows the details above plus the description and link to the webLinkReader (see returned json data from API) that when clicked opens the link so user can read the book.  It is desirable to make it seem as though the launched external webpage is part of the native app.

4. All of your code should be checked into a github repo.  Please reply with the repo address.

5. You must code this project using Kotlin and Android Studio.

6. You are encouraged to use any useful third party pods.

7. Checkin screenshots and videos of your working app in a folder named videos_images/.

8. You have 24 hours to check in your final code from the time you receive this coding test.

9. You are encouraged to commit incremental updates as you finish key parts of the app.

10. Well commented code is desired

Bonus Item:

1. Provide unit test coverage that tests the pagination logic.

If you have any questions feel free to contact dross@orangetheory.com.

After completing please send comments/info to:

dross@orangetheory.com

tshirazi@orangetheory.com

ishashkov@orangetheory.com

kpeart@orangetheory.com