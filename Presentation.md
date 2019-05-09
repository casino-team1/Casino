# Presentations


Umsetzung:

* Spiele sind voneinander unabhängig. Die Spiele interagieren mit der Hauptapplikation und bekommen Ihre Informationen und Ihre Abhängigkeiten von der Hauptapplikation mit gegeben.
* Somit können ganz einfach neue Spiele an die Applikaiton angehänt werden. Das neue Spiel muss einfach die Abstrakte Klasse Spiel implementieren und kann sofort angehängt werden.
* Ebenfalls können dadurch gloable Einstellungen und Regeln in der Hauptapplikation festgelegt werden, danach werden diese an die einzelnen Spiele weiter gegebenen.



Wichtige Mittel der Implementation:

* Bcrypt
  * Die Passwörter werden nach erfolgreicher Validierung und Registrierung des Nutzers mit bcrypt gehasht und in der Datenbank gespeichert. Dadurch können die Daten in der Datenbank auch bei erhalt nicht entschlüsselt werden.
* Ebenfalls haben wir die Registrierung mit einer Email umgesetzt.  Beim Erstellen eines neuen Spielers, bekommt dieser eine Email mit einem Zugangscode. Ohne diesen kann der Account nicht erstellt werden.



Datenbank:

* Tabellen:
  * User
  * Balance
  * Statistic
  * StatisticToPlayser
  * Game

User:
    Speichert Informationen über den Spieler, dessen gehashtes Passwort sowie seine Email-Adresse und seinen Nutzernamen. 

Balance:
    Bildet den Geldbeutel des Spielers ab. Hält information über sein Geld und seine Chips/Jetons. 

Statistic:

    Sicherung der Daten für die bereits gespielten Spiele. Umfasst den Einsatz, den Ausgang und die Änderung im Spielerkonto.





