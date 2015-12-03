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
4.1 a)	Die ausgaben der verschiedenen Threads sind völlig zufällig und unvollständig, da die threads nicht der reihe nach, sondern irgendwie auf die ausgabezugreifen.
	Da printf() erst bei einem /n ausgegeben wird, und die threads die ausgabe immer clearen, wird fast nichts ausgegeben.

