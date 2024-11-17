# CSC 305: Assignment-2

Deadline: 03 November 2024, 11:59 PM (10 points)

## Overview

As previously discussed, over the course of this quarter we will build and expand
the features of an online news aggregator. This project will, ultimately, pull news
articles from a variety of sources (as specified by the user) and display a subset of
those articles based on the user's specified interests.

At present, you have already implemented the functionality to parse the specified
JSON format and to display the extracted articles. For this assignment you will
leverage your existing design to provide flexibility in both the data source and the
parsing of that data. You will also take a step toward providing flexibility in how
extracted articles are processed.

## Course Learning Objectives

This assignment addresses the following course learning objectives.

### Direct

- Discuss the features of a standard OO language.
- Use Makefiles or a standard IDE.
- Develop and properly organize multi-source file projects.
- Use modularity in building a project.
- Use module-level testing.
- Use, at an intermediate level, a language-standard class library.

### Indirect

- Use a standard debugger.
- Describe single inheritance, including abstract base classes.
- Describe class members and methods.
- Use polymorphic methods and interface types.
- Use Application Frameworks

## Tasks

Satisfied with the NewsAPI format parser that you have already implemented, you
are tasked with expanding your code base to support multiple data sources (the
team has currently identified files and urls as sources of interest), multiple parsers
(presently only one more in addition to that already implemented).

### Setup

- Clone the repository and install the necessary libraries.
- Accept the GitHub Classroom Assignment 2 invitation
  https://classroom.github.com/a/jUGk-Ikl
- Clone the repository in your IDE using the URL specified for your repository.
- Add your files from Project Assignment 1 to this project. You can pull them from
  your other repository or copy them over (certainly one would normally work within
  a single repository, likely with branches, but separate repositories make grading for
  feedback easier).

### Task 1: Sources & Parsers & Processors

Your supervisor shares that the team has further considered the design of this news
aggregation library/service (note that you should, of course, be part of these design
discussions, but this is a course that is trying to encourage design thinking that will
allow for contributions to such discussions; as such, the details of the design are
left intentionally vague, but with subtle hints, to give you the freedom to explore).
These design discussions have identified a general structure that you are to
implement for this assignment. An overview of this structure is outlined below.

The core feature is the notion of a data parser. A parser returns a list of articles
(from the previous assignment) but may process those articles in some way before
returning that list. At present, the parser envisioned is one that retrieves the list of
articles from a data source by parsing it and building a list. This parser, to a degree,
is really just a data extraction parser (i.e., it extracts data from a source and
converts it into the desired format). Fortunately, your parser from the previous
assignment will (or should) work nicely for data sources exposing the NewsAPI
JSONformat.

However, the team has also identified a new (simpler) format (as illustrated in the
provided simple.json) that you are expected to support. In addition, you are also
expected to support the format for articles streamed from the NewsAPI url.

Depending on what data source and format you are using, you should be able to
use its complementary parser to extract the articles.

### Task 2: Testing

Expand your testing to include your new classes. Mock objects where appropriate,
but only mock those for which you know the expected behavior (i.e., do not try to
Mock an InputStream or Reader). Your unit tests will not actually retrieve the
contents at a live url (see below); to do so would rely on the environment (is there
an active network connection? Can we rely on, for example, eduroam, and the
response of the server for that url?)

Note that you do not necessarily have to use mocking for testing your program. It
is possible to design your program in a way such that mocking external
dependencies is not needed.

### Task 3: Main Method

Create a class with a main method to demonstrate the functionality of your parser.
More specifically, create complementary parsers that can parse the provided
newsapi.json file, along with the provided simple.json file, and the current US
headlines as retrieved from NewsAPI at URL `http://newsapi.org/v2/top-headlines?country=us&apiKey=<yourkey-here>` (you
should get your own apiKey
from the NewsAPI website (https://newsapi.org/), but can take it from the
command-line or a file that is not part of a commit if you are concerned about
sharing it in your repository).

Your main method should extract and gather the articles from each of these
sources. For each of the articles, print (on separate lines, similar to that shown
below) the title, description, time, and url; leave a blank line between each
article.

The following is sample output (your output need not be identical).

```
title: Facebook says government breakup of Instagram, WhatsApp would be ‘complete nonstarter’ - Fox Business
at: 2020-10-04T18:34:05Z
url: https://www.wsj.com/articles/facebook-says-government-breakup-of-instagram-whatsapp-would-be-complete-nonstarter-11601803800
The House Antitrust Subcommittee this month is expected to release the findings of its investigation into Facebook and other companies.
```

### Documentation

Provide a JavaDoc (https://www.oracle.com/technical-resources/articles/java/javadoc-tool.html) comment for each
non-private
method. The comment should include a brief description of the method's purpose,
a brief description of each parameter, and a description of the returned value (if
any).

## Submitting

Push your code to your repository by the deadline. You should be pushing to your
repository frequently, so this step should become automatic. Also, submit your
GitHub username on Canvas (this is the only Canvas submission you need to do for
this assignment) by the submission deadline.

| Grading Rubric        |           |
|-----------------------|-----------|
| Parser: Design        | 3 points  |
| Parser: Functionality | 2 points  |
| Testing               | 2 points  |
| Main method           | 2 points  |
| Documentation         | 1 point   |
| Total                 | 10 points |
