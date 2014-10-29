crawler
=======
Сборка и запуск: есть Makefile.

javac -version

javac 1.8.0_20

Реализован самый тупой подход: на каждом слое графа запускаем несколько потоков, разгребающих "очередь".
Список урлов храню в ConcurrentHashMap, впрочем все равно потом все портится локом для изменения счетчика. 
Lockfree решение не придумал. Название файла - {uuid}.html. В самом конце выводится время работы в миллисекундах

Два запуска в 5 потоков:

MacBook-Pro-Denis:crawler den$ make run

java -classpath bin:src:jsoup-1.8.1.jar Main http://acm.timus.ru 3 500 /Users/den/Desktop/tmp/

Failed to load page with url http://urfu.ru/en/home/

Failed to load page with url http://icpcres.ecs.baylor.edu/onlinejudge/

Failed to load page with url http://karelia.snarknews.info/trial.cgi?data=macros/day&menu=index&head=2008s&round=06&sbname=2008s

17420


MacBook-Pro-Denis:crawler den$ make run

java -classpath bin:src:jsoup-1.8.1.jar Main http://acm.timus.ru 3 500 /Users/den/Desktop/tmp/

Failed to load page with url http://urfu.ru/en/home/

Failed to load page with url http://icpcres.ecs.baylor.edu/onlinejudge/

Failed to load page with url http://karelia.snarknews.info/trial.cgi?data=macros/day&menu=index&head=2008s&round=06&sbname=2008s

19457


Два запуска в 1 поток:

MacBook-Pro-Denis:crawler den$ make run

java -classpath bin:src:jsoup-1.8.1.jar Main http://acm.timus.ru 3 500 /Users/den/Desktop/tmp/ 1

Failed to load page with url http://karelia.snarknews.info/trial.cgi?data=macros/day&menu=index&head=2008s&round=06&sbname=2008s

Failed to load page with url http://urfu.ru/en/home/

Failed to load page with url http://icpcres.ecs.baylor.edu/onlinejudge/

39714


MacBook-Pro-Denis:crawler den$ make run

java -classpath bin:src:jsoup-1.8.1.jar Main http://acm.timus.ru 3 500 /Users/den/Desktop/tmp/ 1

Failed to load page with url http://karelia.snarknews.info/trial.cgi?data=macros/day&menu=index&head=2008s&round=06&sbname=2008s

Failed to load page with url http://urfu.ru/en/home/

Failed to load page with url http://icpcres.ecs.baylor.edu/onlinejudge/

40679
