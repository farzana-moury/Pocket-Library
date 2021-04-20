# Pocket-Library
A library application that reads from api provided by openlibrary.org


## Overview
Pocket Library is a virtual library app that utilizes Open Library's search API. It is also helpful in the sense that it helps you keep track of what books you've read and haven't read. Explore thousands of books provided by Open Library and add them to your bookshelf!

## Features 
* Polished Design with beautiful colour scheme
* Easy Navigation through navigation drawer layout
* Catalog page where you can look for books 
* Publishers page which contains famous publishers and their websites.
* Adding books from the catalog to your bookshelf
* Settings that allows you to edit information, manage books and ratings

## Getting Started 
To get started, follow the steps below to ensure you can properly launch the application to start using it.

**Prerequisites**

* Download the latest version of [Android Studio](https://developer.android.com/studio)
* Download [Git](https://git-scm.com/downloads) 
* Target SDK version
  * API 30: Android 10.0+ (R)
  * API 29: Android 10.0 (Q)

**Steps**
1. Unzip the file to start setup. Follow the dialog box as it guides you through the setup process 

2. Once setup is complete, choose a path for installation. This could be your desktop, for example

3. The studio need to download SDK components. Click next to download all SDK components

4. Click finish

5. Start a new android project 

6. Select Tools > SDK Manager to select an SDK. Choose API an level of 23 or above to target most Android Devices. I recommend the latest API

7. Select Tools > AVD Manager to run the application on a virtual device

8. Your Android Studio is now ready to launch the program!

### Run
**Open the terminal to run the following commands**

* Move to a directory you would like to store this project

  `cd \directory`

* Clone the project

  `git clone https://github.com/farzana-moury/Pocket-Library.git`

**You are now ready to launch the app**

## Using the App
### All Screens 
1. Welcome screen - contains Pocket Library introduction and location

![home page](app_pages/home%20page.png)

2. Catalog screen - contains recyclerview which returns books from the API

![catalog page](app_pages/catalog%20page.png)

3. Publishers screen - contains a viewpager2 which displays publisher information and their websites

![publishers page](app_pages/publishers%20page.png)

4. My books screen - acts as a "bookshelf" which holds all the books you've borrowed

![my books page](app_pages/my%20books%20page.png)

5. Credits screen - all assets, api and information resources credited here. You can also contact me.

![credits page](app_pages/credits%20page.png)


### Using the API
1. Navigate to the Catalog page

![navigation drawer - catalog](app_pages/select%20catalog.png)

2. Search for a book

![searching for book](app_pages/search%20for%20book.png)

3. Borrow the book to add it to your bookshelf

![borrow a book](app_pages/borrow%20book.png)

4. You can now find the book in the My books page


### Viewing Publishers
1. Navigate to the Publishers page

![publishers page](app_pages/publishers%20page.png)

2. Swipe between publishers to view their information

![publishers page 2](app_pages/swipe%20through.png)


### Rating a book
1. Navigate to the My books page

![my books page](app_pages/my%20books%20page.png)

2. Click on a rating from a particular book

![change rating 1](app_pages/change%20rating%201.png)

3. An alert will show asking for input. Provide your rating and you're all set

![change rating 2](app_pages/change%20rating%202.png)


### Returning a book
1. Navigate to the My books page

![my books page](app_pages/my%20books%20page.png)

2. Return the book by clicking the return button. The book will no longer remain in your bookshelf

![return book](app_pages/return%20book.png)

### Settings
1. To access the settings, click the more options icon in the top right of the header

![settings access](app_pages/settings%201.png)

2. The following options will show

![settings options](app_pages/settings%202.png)

3. To edit your username, click "Change username"

![change username](app_pages/change%20username.png)

3. To edit your email, click "Change email"

![change email](app_pages/change%20email.png)

4. To reset all ratings you provided for your books, click "Reset ratings"

![reset ratings](app_pages/reset%20ratings.png)


5. To clear all of the books from the bookshelf, click "Clear books"

![clear books](app_pages/clear%20books.png)

## Compatibility Chart
| **Compatible**      | **Target SDK**            | **Devices**        |
| --------------------|:-------------------------:| :-----------------:|
| Yes                 | API 30: Android 11        |   Pixel C (Tablet)       |
| Yes                 | API 30: Android 11        |   Pixel XL (Mobile)        |
| Yes                 | API 30: Android 11        |   Pixel 2 (Mobile)         |

## Authors

| **Name**      | **Account**   | **Email**          |
| ------------- |:---------------:| :-----------------:|
| Farzana Moury | [farzana-moury](https://github.com/farzana-moury) | w0737626@myscc.ca |

## License

This project is licensed under the [Apache 2.0](https://choosealicense.com/licenses/apache-2.0/) License

## Acknowledgements

Cai Filiault (Professor)

