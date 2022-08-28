#Makefile Assignment 2
#MYZMBA002
#28 August 2022

#JAVAC+/user/bin/javac
.SUFFIXES:	.java	.class
SRCDIR=src
BINDIR=bin
JAVAC=javac

$(BINDIR)/%.class:$(SRCDIR)/%.java
		$(JAVAC) -d $(BINDIR)/ -cp $(BINDIR) $<

CLASSES=WordDictionary.class Score.class ScoreUpdater.class FallingWord.class Score.class CatchWord.class TypingTutor.class WordMover.classs

CLASS_FILES=$(CLASSES:%.class=$(BINDIR)/%.class)

default: $(CLASS_FILES)

clean:
		rm $(BINDIR)/*.class

run: $(CLASS_FILES)
	java -cp $(BINDIR) TypingTutorApp 

