#Makefile Assignment 1
#MYZMBA002
#14 August 2022

#JAVAC+/user/bin/javac
.SUFFIXES:	.java	.class
SRCDIR=src
BINDIR=bin
JAVAC=javac

$(BINDIR)/%.class:$(SRCDIR)/%.java
		$(JAVAC) -d $(BINDIR)/ -cp $(BINDIR) $<

CLASSES=WordDictionary.class Score.class ScoreUpdater.class FallingWord.class WordMover.class HungryWordMover.class CatchWord.class GamePanel.class TypingTutorApp.class

CLASS_FILES=$(CLASSES:%.class=$(BINDIR)/%.class)

default: $(CLASS_FILES)

clean:
		rm $(BINDIR)/*.class

run: $(CLASS_FILES)
	java -cp $(BINDIR) TypingTutorApp

