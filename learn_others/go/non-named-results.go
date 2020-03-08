package main

import "fmt"

func split(sum int) (int, int) {
  var x int
  var y int
  x = sum * 4 / 9
  y = sum -x
  return x, y
}

func main() {
  fmt.Println(split(27))
}
