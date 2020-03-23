#!/bin/bash
# version 3 cleanup

LOG_DIR=/var/log
ROOT_UID=0    # only user with userid 0 have root privileges
LINES=50
E_XCD=86      # cannot change direcotory
E_NOTROOT=87  # non-root exit error

if [ "$UID" -ne "$ROOT_UID" ]
then
  echo "Must be root to run this script"
  exit $E_NOTROOT
fi


if [ -n "$1" ]
then 
    lines=$1
else
    lines=$LINES
fi


cd $LOG_DIR

if [ `pwd` != "$LOG_DIR" ]
then 
    echo "cannot change to $LOG_DIR."
    exit $E_XCD
fi

tail -n $lines messages > mesg.temp
mv mesg.temp message

cat /dev/null > wtmp
echo "log files cleaned up"

exit 0

