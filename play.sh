#!/bin/bash
echo "Compiling..."
javac -d bin src/main/*.java src/model/*.java src/model/gameobjects/*.java src/model/world/*.java src/controller/*.java src/view/*.java
echo "Running..."
java -cp bin main.Main
read -p "Press enter to continue"
