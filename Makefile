all:
	javac -classpath jsoup-1.8.1.jar src/*.java

run:
	java -classpath bin:src:jsoup-1.8.1.jar Main http://acm.timus.ru 3 500 /Users/den/Desktop/tmp/