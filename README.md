# CSV to JSON Converter

Built by Joshua Steele, 2021-12-22.

## How to Run the program

...

## Task

Write a program in a language of your choice that can convert a csv file to JSON.

You can decide how the user selects the csv and how the results are displayed. Please include your reasoning for the
language and user experience you choose. The csv will be in the following format (the image is included as an example to
reference):

![Example CSV](./img/example-csv.png)

- Objects will be separated by a blank line (lines 7 and 19)
- Each name/value pair will be one of the following:
    - A name in one column with its value in the next column (lines 1, 3, 4, 5, 8, etc.)
    - A name in one column followed by a blank cell (lines 2 and 9). This means the value is the object that starts in
      the next row, which will be indented
    - A name in one column with an array in the next column, separated by carats (lines 6 and 18)
- Nested objects are one column to the right from their value (lines 3-5, 10-12 and 13-16)
- Nested objects end when the next name/value pair is to the left of the nested object

## Example

The output JSON for the above example should look similar to the following:

```json
[
  {
    "Name": "Leo",
    "Work": {
      "Title": "Software Developer",
      "Employer": "Argis Solutions",
      "Years Employed": 5
    },
    "Hobbies": [
      "Painting",
      "Fishing"
    ]
  },
  {
    "Name": "Martha",
    "Work": {
      "Title": "Server Manager",
      "Employer": "Example Company",
      "Address": {
        "Street": "555 Example St.",
        "City": "Denver",
        "State": "CO",
        "Zip Code": 80014
      },
      "Years Employed": 12
    },
    "Hobbies": [
      "Snowboarding",
      "Reading",
      "Hiking"
    ]
  }
]
```

