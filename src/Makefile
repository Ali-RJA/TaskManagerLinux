JFLAGS = -g
JC = javac

.SUFFIXES: .java .class

.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
		  lab4.java \
		  Task.java\
		  Tasklist.java\
		  Utilities.java

default: classes

classes: $(CLASSES:.java=.class)
	
run:
	java lab4
