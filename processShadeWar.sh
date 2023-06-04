#!/bin/bash

WAR_NAME=${1:-springbootangular.war}

cd target
unzip $WAR_NAME -d tmp2

cd tmp2
rm META-INF/ -fr
mv $(ls -I org -I WEB-INF) ./WEB-INF/classes

cd org
mv $(ls) ../WEB-INF/classes/org

cd ..
rmdir org

cd WEB-INF/lib
mv spring-* ..
mv logback-c* ..
rm *
mv ../*.jar .

cd ../..
jar -cf $WAR_NAME *
zip -d $WAR_NAME "META-INF/*"
rm WEB-INF/ -fr
mv $WAR_NAME ..

cd ..
rmdir tmp2

echo "War is shaded!"
