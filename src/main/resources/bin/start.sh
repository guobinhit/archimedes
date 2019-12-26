#!/bin/bash -
source /etc/profile
HOME=$(cd $(dirname $0); pwd)
echo ${HOME}
cd ${HOME}/..
CLASSPATH="."
for jarfile in `ls lib/.`; do
    CLASSPATH="${CLASSPATH}:lib/$jarfile"
done
for jarfile in `ls archimedes-*.jar`; do
    CLASSPATH="${CLASSPATH}:$jarfile"
done
export CLASSPATH
nohup java  -Xms2g -Xmx2g -XX:+HeapDumpOnOutOfMemoryError -XX:-OmitStackTraceInFastThrow -Dappname=archimedes -Djava.awt.headless=true -XX:+UseParNewGC -XX:+UseConcMarkSweepGC -XX:CMSInitiatingOccupancyFraction=76 -XX:+UseCMSInitiatingOccupancyOnly -XX:+HeapDumpOnOutOfMemoryError -XX:+DisableExplicitGC -Dfile.encoding=UTF-8  com.hit.cggb.archimedes.ArchimedesApplication --spring.profiles.active=dev > nohup.out &
echo $! > ${HOME}/CATALINA_PID
echo "Tomcat started."