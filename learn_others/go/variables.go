package main

import "fmt"

var c, python, java bool

func main () {
  var i int
  fmt.Println(i, c, python, java)
  java = true
  fmt.Println(i, c, python, java)
}
