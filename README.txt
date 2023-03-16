Compilation : 
javac -cp lib/json.jar -d build src/kk/calcul/**/*.java src/kk/gui/**/*.java src/kk/**/*.java src/**/*.java


Exécution partie Livre :
java -cp build:lib/json.jar application.Main

Exécution partie graph :
java -cp build kk.Demo
