@echo off
cd /d "%~dp0"
echo Compiling...
javac -d ../bin src\main\*.java src\model\*.java src\model\gameobjects\*.java src\model\world\*.java src\controller\*.java src\view\*.java
echo Running...
java -cp ../bin main.Main
pause