#!/bin/bash
HOME=$(cd $(dirname $0); pwd)
if [ -f "${HOME}/CATALINA_PID" ]
then
    PID=`cat ${HOME}/CATALINA_PID`
    ps -p $PID > /dev/null
    if [ $? -ne 0 ]
    then
        echo stop
    else
        echo running
    fi
else
    echo stop
fi