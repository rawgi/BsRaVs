#!/bin/sh

echo "* Aufraeumen alter Prozesse:"
killall leser schreiber

echo "* Anlegen/Loeschen des SHM:"
# Ihr Skript-Code hier:
# ...

./create test

./schreiber test a 10 999&

./schreiber test b 5 999&

./schreiber test c 15 90&

./leser test

./destroy test
