#!/bin/bash

HOME=$(cd $(dirname $0); pwd)
if [ -f "${HOME}/CATALINA_PID" ]
then
    pid=`cat ${HOME}/CATALINA_PID`
    if [ ! -z "$pid" ];then
        kill $pid
    fi
fi
echo "stop success"
