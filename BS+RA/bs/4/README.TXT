Praktikum Betriebssystem und Rechnerarchitektur
================================================

Aufgabe 4 -- Threads
---------------------

Bitte benutzen Sie die vorhandenen Dateien und benennen Sie diese nicht um,
da nur so eine schnelle Ueberpruefung der Abgaben sichergestellt werden kann!


Verzeichnisstruktur
--------------------

- README.TXT        Diese Datei

- Makefile          Makefile

- gibaus.c          Aufgabe 4.1 (a)
- gibaus_sem.c      Aufgabe 4.1 (b)
- gibaus_mutex.c    Aufgabe 4.1 (c)

- philosophen.c     Aufgabe 4.2 (a)
- philosophen.txt   Aufgabe 4.2 (c)   <--- Ihre Beobachtungen zu den Philosophen

- baeckerei.c       Aufgabe 4.3 (a,b) <--- auch mehrere Baecker erlaubt


Beobachtungen:
4.1 a) Die ausgaben der verschiedenen Threads sind völlig zufällig, da die threads nicht der reihe nach, sondern irgendwie auf die Ausgabe zugreifen.
4.1 c) Beim Ausführen kann ich keinen Unterschied der beiden Varianten feststellen.

4.3 b) um einen zweiten bäcker zu ermöglichen, müsste ich einen weiteren Thread für einen zweiten bäcker erzeugen und in den methoden eier, mehl und milch den bäcker mittels broadcast statt signal wecken.
