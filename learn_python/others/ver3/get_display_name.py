#!/usr/bin/env python3

def get_name():
  ''' get and return name'''
  name = input("what is your name?")
  return name;

def say_name(name):
  ''' say a name '''
  print ('your name is {}'.format (name));

def get_and_say_name():
  ''' get and disply name'''
  name =  get_name();
  say_name(name);

get_and_say_name();

