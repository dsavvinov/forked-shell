# Software Design Course autumn 2016 
  
  Develop the architecture and implement a prototype command line interpreter
  
---
##  Supported commands:
   * cat
   * echo
   * wc
   * pwd
   * exit
  
---
  Supported strong and weak quoting, $, calling of external program, pipes
  
## Example
  
* > echo "Hello, world!" 
* Hello, world!
* > FILE=example.txt
* example.txt
* > cat $FILE
* Some example text
* > cat example.txt | wc
* 1 3 18
  
  
  