JCC = javac

default: jar

jar: Stocks.class
	@echo "Manifest-Version: 1.0" >> manifest.mf
	@echo "Main-Class: Stocks" >> manifest.mf
	@echo "" >> manifest.mf
	jar cmf manifest.mf Stocks.jar *.class *.java
	@echo "#!/usr/bin/java -jar" > Stocks
	cat Stocks.jar >> Stocks
	chmod +x Stocks

Stocks.class: Stocks.java
	$(JCC) *.java
clean:
	rm -f *.class
	rm -rf manifest.mf
	rm -rf *.jar
