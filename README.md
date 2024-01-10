# Campionato In Pista

Piattaforma per la gestione di un campionato mondiale di automobili.

Questa webapp è il risultato di un progetto corsista del Corso di Basi di Dati (Laurea Triennale in informatica) dell'Università degli Studi di Salerno.

## Esecuzione del progetto Campionato In Pista in localhost su Windows

1. **Clona il repository:**  
   Apri il prompt dei comandi e esegui il comando:

   `git clone https://github.com/MiriamFerrara/CampionatoInPista.git`

   Questo scaricherà il repository del progetto nella tua directory locale.


2. **Naviga nella directory del progetto:**

   Utilizza il comando `cd` per spostarti nella directory appena clonata:

   `cd C:\Users\Utente\IdeaProjects\CampionatoInPista`


3. **Esegui la build con Maven:**

   Utilizza Maven per eseguire la build del progetto:

   `.\mvnw.cmd clean package` (se usi macOS, `./mvnw clean package`)

   Questo comando compila il progetto e genera un file JAR eseguibile.


4. **Avvia l'applicazione:**

   Avvia l'applicazione Spring Boot eseguendo il file JAR generato:

   `java -jar target\CampionatoInPista-0.0.1-SNAPSHOT.jar `

   Questo comando avvia il server web dell'applicazione.


5. **Apri il browser:**

   Apri il tuo browser web preferito e vai all'indirizzo

   `localhost:8080/CampionatoInPista/Homepage`.
