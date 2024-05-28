# **Library App**

## Descriere

**Library App** este o aplicație de gestionare a bibliotecilor care permite administrarea cărților, cititorilor, angajaților, secțiunilor, recenziilor, utilizatorilor, evenimentelor și împrumuturilor. Aplicația folosește baze de date pentru a salva informațiile legate de angajați, utilizatori, adrese și evenimente. Oferă un meniu interactiv care permite navigarea și gestionarea fiecărei clase.

## Funcționalități

- **Biblioteca**: Gestionarea informațiilor despre bibliotecă.
- **Carte**: Adăugarea și gestionarea cărților disponibile.
- **Cititor**: Gestionarea cititorilor înregistrați.
- **Angajat**: Gestionarea angajaților bibliotecii.
- **Secțiune**: Organizarea cărților pe secțiuni.
- **Recenzii**: Gestionarea recenziilor pentru cărți.
- **Utilizator**: Administrarea conturilor de utilizator.
- **Evenimente**: Organizarea și gestionarea evenimentelor.
- **Împrumut**: Gestionarea împrumuturilor de cărți.
- **Adresă**: Stocarea adreselor asociate utilizatorilor și evenimentelor.

**Configurați baza de date:**
  Asigurați-vă că baza de date este configurată și că ați actualizat fișierele de configurare corespunzătoare cu informațiile de conexiune la baza de date.

**Actualizați `pom.xml`:**

    Deschideți fișierul `pom.xml` și asigurați-vă că `artifactId` este setat corect (posibil sa trebuiasca sa schimbati numele proiectului, initial proiectul meu se numea AAAAAA) :
  
    ```xml
    <groupId>org.example</groupId>
    <artifactId>nume_proiect</artifactId>
    <version>1.0-SNAPSHOT</version>
    ```
