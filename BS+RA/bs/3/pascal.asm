##
## Abgabe fuer Aufgabe 3.4 "Rekursion"
##

# Datenbereich:
.data
n_ausgabe: .asciiz "Zahl n groesser oder gleich 0:\n"
k_ausgabe: .asciiz "Zahl k groesser oder gleich 0 und kleiner oder gleich n:\n"
ergebnis: .asciiz "ergebnis:\n"

# Codebereich:
.text

# Prozedur "main":
main:
	# Prolog
	addi $sp, $sp, -36
	sw $ra, 0($sp)
	sw $s0, 4($sp)
	sw $s1, 8($sp)
	sw $s2, 12($sp)
	sw $s3, 16($sp)
	sw $s4, 20($sp)
	sw $s5, 24($sp)
	sw $s6, 28($sp)
	sw $s7, 32($sp)

	# Hier Code einfuegen. Bitte in der Main Prozedur nur
	# Ein- und Ausgabeaufgaben durchfuehren. Berechnungen
	# bitte in neuen Prozeduren machen
	la $a0, n_ausgabe
	li $v0, 4
	syscall

	#n einlesen
	li $v0, 5
	syscall
	move $t0, $v0

	la $a0, k_ausgabe
	li $v0, 4
	syscall

	#k einlesen
	li $v0, 5
	syscall

	#n und k in parameter schreiben und pascal aufrufen
	move $a0, $t0
	move $a1, $v0
	jal pascal
	move $t0, $v0

	la $a0, ergebnis
	li $v0, 4
	syscall

	move $a0, $t0
	li $v0, 1
	syscall

	# Epilog
	lw $ra, 0($sp)
	lw $s0, 4($sp)
	lw $s1, 8($sp)
	lw $s2, 12($sp)
	lw $s3, 16($sp)
	lw $s4, 20($sp)
	lw $s5, 24($sp)
	lw $s6, 28($sp)
	lw $s7, 32($sp)
	addi $sp, $sp, 36

	# Ende
	jr		$ra


# Prozedur "pascal":
#
# Argument $a0 Paramter "int n"
# Argument $a1 Paramter "int k"
# Ergebnis in $v0
pascal:
	# Prolog
	addi $sp, $sp, -16
	sw $ra, 0($sp)
	sw $a0, 4($sp)
	sw $a1, 8($sp)
	sw $s1, 12($sp)

	li $s1, 0

	# Bitte den Inhalt dieser Prozedur durch etwas Sinnvolles ersetzen!

	#überprüfungen für k
	beq $a0, $a1, abbruch
	beq $zero, $a1, abbruch

	#pascal(n-1,k)
	addi $a0, $a0, -1
	jal pascal
	add $s1, $s1, $v0

	#pascal(n-1,k-1)
	addi $a1, $a1, -1
	jal pascal

	#return regebnis1+regebnis2
	add $v0, $s1, $v0
	j ende
 abbruch:
 	#return 1
	li $v0, 1
 ende:

	# Epilog
	lw $ra, 0($sp)
	lw $a0, 4($sp)
	lw $a1, 8($sp)
	lw $s1, 12($sp)
	addi $sp, $sp, 16
	# Ruecksprung
	jr		$ra
