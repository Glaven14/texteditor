# CSC 207: Text Editor

**Author**: Ian Gunn

## Resources Used

+ Project Assignment Page:
https://osera.cs.grinnell.edu/ttap/data-structures-labs/text-editor.html?search=
+ Wikipedia for a background on Gap Buffers:
https://en.wikipedia.org/wiki/Gap_buffer 
+ Unicode conversion of the black vertical rectangle:
https://www.ssec.wisc.edu/~tomw/java/unicode.html
+ Refresher on common String functions like concat and substring:
https://docs.oracle.com/en/java/javase/23/docs/api/java.base/java/lang/String.html#charAt(int)
+ Tips on how to convert a char to a string:
https://www.digitalocean.com/community/tutorials/convert-char-to-string-java
+ Lab 5: Testing Frameworks by Rex and Ian for the anatomy of a testing suit:
https://www.gradescope.com/courses/960277/assignments/5750698/submissions/306608280?view=files  


## SimpleStringBuffer Runtime Analysis:
1. The input to insert is a character (ch)
2. The critical operations in insert are substring and concat
3. f(n) = 5. where n is the length of the character - which will always be one.
4. Insert is O(C). Where C >= 5. 

Insert performs substring 2 times and concat 3 times regardless of the input. 
These operations are important because Strings are immutable which means these
operations are making a new string which, will add to the runtime significantly more
than simply changing the string would have.

## Changelog

_(TODO: fill me in with a log of your committed changes)_
