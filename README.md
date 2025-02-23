# CSC 207: Text Editor

**Author**: Ian Gunn

## Resources Used

+ _(TODO: fill me in)_
+ ...
+ ...


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
