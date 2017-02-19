# Wikiracer

## Installation
### Requirements 
* Java JDK 1.8+

### Procedure
Unzip, change into project directory, and run:
```
./gradlew build -x test
```

Then to run:
```
java -jar build/libs/wikiracer-1.0-SNAPSHOT <input>
```

## Problem statement
Wikiracing is a game that people play on Wikipedia. Given a starting article and an ending article,
the objective of a wikirace is to get from the starting article to the ending article by only clicking
on links occurring in the main bodies of wikipedia articles (not including the side navigation bar or
the category footer).

Write a wikiracing bot, which will take race specifications in the form of a JSON object:
```javascript
{
    ''start'': ''<starting article>'',
    ''end'': ''<ending article>''
}
```

and which will return the results of the race in the form of a JSON object:
```javascript
{
    ''start'': ''<starting article>'',
    ''end'': ''<ending article>'',
    ''path'': [
        ''<starting article>'',
        ''<article at step 1>'',
        ''<article at step 2>'',
        .
        .
        .
        ''<article at step n-1>'',
        ''<ending article>''
    ]
}
```

Each article will be identified with a fully expanded URL. So, for example, the Wikipedia article
about “World War II” will be represented by the URL https://en.wikipedia.org/wiki/World_War_II

## Solution Discussion

### Libraries
 * **OkHttp3** - To simplify handling the HTTP requests
 * **json** - To handle parsing of the input and building the output JSON strings
 * **jsoup** - To easily parse the HTML and peform DOM queries
 
### Approach
My approach was to perform a breadth-first search from the start link to the end
link. This would guarantee that the path found would be minimum length. 

**Steps:** 
 1. Fetch the document for the start URL
 2. Find the div with the ID *mw-content-text* and search within it, 
 since that is where the body of
 the article is, excluding nav and footer sections.
 3. Find all ``<a>`` links with an href that starts with /wiki/ since these are
 links to other wiki articles. We want to avoid visiting external links.
 4. For each link, check if it is the target
    1. If it is the target, walk the path back up to the start to find the path
    2. Else, check if we've already processed this link and if not queue it to 
    be processed.
 5. Repeat 1-4 for each link in the queue until the target is found.
 
#### Caveats
1. If the target link cannot be reached from the start link, this program will not
terminate. 
2. Links are not visited in the order in which they appear in the document, so
this may result in some unintuitive results although they would still be 
correct according to the spec.

#### Improvements
1. Perhaps having a max search depth or a timeout so that the program will
always terminate.
2. Use a thread pool and worker threads to process links in parallel in order to
improve performance.

