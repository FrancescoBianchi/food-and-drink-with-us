# food-and-drink-with-us
Simple Grails application that retrieves the list of people to invite to a party based on their distance from a given point.

The list of all candidate guests is stored on a local file. Every line of the file describes a guest using JSON format.

The location of the file is fixed and at design phase it was assumed that a tech-savvy will take care of updating that file.

Every malformed JSON object is skipped. 