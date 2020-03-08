#!/usr/bin/env python3

def even_or_odd(number):
  '''determines if number is odd or even'''
  if number % 2 == 0:
    return 'Even';
  else:
    return 'Odd';

userinput = input("enter a number to check odd or even:");

print (even_or_odd(int(userinput)));

