# Software Design Course autumn 2016 
  
  Develop the architecture and implement a prototype command line interpreter
  
  Tracker:      
  https://www.pivotaltracker.com/n/projects/1866561
  
---
##  Supported commands:
   * cat
   * echo
   * wc
   * pwd
   * exit
   * grep
  
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
  
  
  