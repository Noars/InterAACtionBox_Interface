#!/bin/bash
while [ ! -e ~/TÚlÚchargements/close.txt ] && [ ! -e ~/Downloads/close.txt ]
do
    sleep 1
done
pkill chrome
rm -f ~/TÚlÚchargements/close.txt
rm -f ~/Downloads/close.txt
