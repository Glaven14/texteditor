# CSC 207: Text Editor

**Author**: Ian Gunn

## CHANGES:
* implemente the functionality to save to the file.
* Added my changlog
* Fixed time complexity analysis
* Changed GapBufferTests to accurately represent what should happen.
* Should pass all unit tests. 

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
+ Help from PM figuring out command line errors (a space between exec: and java that shouldn't
be there and how to run checkstyle with mvn checkstyle:check).


## SimpleStringBuffer Runtime Analysis:
1. The input to insert is a character (ch).
2. The critical operations in insert are substring and concat.
3. f(n) = 5n. where n is the length of the String.
4. Insert is O(n). Where C >= 5. 

Insert performs substring 2 times and concat 3 times regardless of the input. 
These operations are important because Strings are immutable which means these
operations are making a new string and copying each of n characters into the string which, will add to the runtime significantly more
than simply changing the string would have.

## GapBuffer Runtime Analysis:
1. The input to insert is a character (ch).
2. The critical operations in insert are assignments to the buffer array.
3. f(n) = 1 best case and n worst case, where n is the size of the backing array.
4. Insert is O(1). 

Insert performs a constant amount of array assignments if the array is not yet full, leading to its constant time complexity.
However, if the backing array is full, we must make a new array twice its size and copy all of the original elements into the new array leading
to a worst case runtime that is linear. This second case doesn't happen very often tho, so it is more efficient than SimpleStringBuffer.

## Changelog
Full Changelog: https://github.com/Glaven14/texteditor/commits/newTag
