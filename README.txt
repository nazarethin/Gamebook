Compilation : 
javac -cp lib/json.jar -d build src/kk/calcul/**/*.java src/kk/gui/**/*.java src/kk/**/*.java src/**/*.java

derniere en date : 

javac -cp lib/json.jar -d build src/kk/calcul/**/*.java src/kk/geometrie/**/*.java src/kk/**/*.java src/view/kk/**/*.java

Exécution partie Livre :
java -cp build:lib/json.jar application.Main

Exécution partie graph :
java -cp build kk.Demo
