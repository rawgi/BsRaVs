##
## Abgabe fuer Aufgabe 3.1 "Zaehlen" und 3.2 "Aendern"
##

# Datenbereich:
.data
string_ausgabe: .asciiz "String:\n"
string_eingabe: .space 101
anzahl_grossbuchstaben: .asciiz "\nAnzahl Grossbuchstaben:\n"
anzahl_kleinbuchstaben: .asciiz "\nAnzahl Kleinbuchstaben:\n"
veraendert: .asciiz "\nVeraendert:\n"
zweimal_veraendert: .asciiz "\nZweimal veraendert:\n"

# Codebereich:
.text

# Prozedur "main":
main:
	# Prolog
	# Hier Prolog einfuegen, falls Ihr Code Register veraendert
	# (Tipp: Das wird er tun)
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
	la $a0, string_ausgabe
	li $v0, 4
	syscall

	la $a0, string_eingabe
	li $a1, 101
	li $v0, 8
	syscall

	la $a0, anzahl_grossbuchstaben
	li $v0, 4
	syscall
	jal gross

	la $a0, anzahl_kleinbuchstaben
	li $v0, 4
	syscall
	jal klein

	la $a0, veraendert
	li $v0, 4
	syscall
	la $a0, string_eingabe
	li $v0, 4
	syscall
	jal tausche
	la $a0, anzahl_grossbuchstaben
	li $v0, 4
	syscall
	jal gross
	la $a0, anzahl_kleinbuchstaben
	li $v0, 4
	syscall
	jal klein


	la $a0, zweimal_veraendert
	li $v0, 4
	syscall
	la $a0, string_eingabe
	li $v0, 4
	syscall
	jal tausche
	la $a0, anzahl_grossbuchstaben
	li $v0, 4
	syscall
	jal gross
	la $a0, anzahl_kleinbuchstaben
	li $v0, 4
	syscall
	jal klein

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


# Prozedur "gross":
#
# Parameter in Register $a0: Zeigt auf den String
# Ergebnis in Register $v0 ist die Anzahl der Grossbuchstaben
gross:
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

	# Bitte den Inhalt dieser Prozedur durch etwas Sinnvolles ersetzen
	la $t0, string_eingabe	#char* ptr = (char*)string;
	li $t2, 0

 schleife1:
	lb $t1, ($t0) 						#char zeichen = *ptr
# zeichen in $t1 groß? sonst jump zu endif1
 	li $t4, 64
	slt $t3, $t1, $t4 #buchstabe größer 64?
	bnez $t3, endif1 #wenn buchstabe nicht größer 64 -> jmp ifende
	li $t4, 91
	slt $t3, $t4, $t1 #buchstabe kleiner 91?
	bnez $t3, endif1 #wenn buchstabe nicht kleiner 91 -> jmp ifende
	addi $t2, $t2, 1					#zählen
 endif1:
	addi $t0, $t0, 1					#ptr++;
	bnez $t1, schleife1				#if(zeichen != 0) goto schleife

 	move $a0, $t2
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

	# Ruecksprung
	jr		$ra						# return result;


# Prozedur "klein":
#
# Parameter in Register $a0: Zeigt auf den String
# Ergebnis in Register $v0 ist die Anzahl der Kleinbuchstaben
klein:
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

	# Bitte den Inhalt dieser Prozedur durch etwas Sinnvolles ersetzen
	la $t0, string_eingabe	#char* ptr = (char*)string;
	li $t2, 0

 schleife2:
	lb $t1, ($t0) 						#char zeichen = *ptr
# zeichen in $t1 groß? sonst jump zu endif1
 	li $t4, 96
	slt $t3, $t1, $t4 #buchstabe größer 96?
	bnez $t3, endif2 #wenn buchstabe nicht größer 96 -> jmp ifende
	li $t4, 123
	slt $t3, $t4, $t1 #buchstabe kleiner 123?
	bnez $t3, endif2 #wenn buchstabe nicht kleiner 123 -> jmp ifende
	addi $t2, $t2, 1					#zählen
 endif2:
	addi $t0, $t0, 1					#ptr++;
	bnez $t1, schleife2				#if(zeichen != 0) goto schleife

 	move $a0, $t2
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

	# Ruecksprung
	jr		$ra						# return result;


# Prozedur "tausche":
#
# Parameter in Register $a0: Zeigt auf den String
# Ergebnis in Register $v0 zeigt auf den geaenderten String
tausche:
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

	# Bitte den Inhalt dieser Prozedur durch etwas Sinnvolles ersetzen
	la $t0, string_eingabe	#char* ptr = (char*)string;
	lb $t1, ($t0)

 schleife3:
# zeichen in $t1 groß? sonst jump zu endif1
 	li $t4, 64
	slt $t3, $t1, $t4 #buchstabe größer 64?
	bnez $t3, endif3 #wenn buchstabe nicht größer 64 -> jmp ifende
	li $t4, 91
	slt $t3, $t4, $t1 #buchstabe kleiner 91?
	bnez $t3, endif3 #wenn buchstabe nicht kleiner 91 -> jmp ifende
	addi $t1, $t1, 32
	sb		$t1, 0($t0)
	j endif4
 endif3:
 	li $t4, 96
 	slt $t3, $t1, $t4 #buchstabe größer 96?
 	bnez $t3, endif4 #wenn buchstabe nicht größer 96 -> jmp ifende
 	li $t4, 123
 	slt $t3, $t4, $t1 #buchstabe kleiner 123?
 	bnez $t3, endif4 #wenn buchstabe nicht kleiner 123 -> jmp ifende
	addi $t1, $t1, -32
	sb		$t1, 0($t0)
 endif4:
	addi $t0, $t0, 1					#ptr++;
	lb $t1, ($t0)
	bnez $t1, schleife3				#if(zeichen != 0) goto schleife

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

	# Ruecksprung
	jr		$ra						# return result;
