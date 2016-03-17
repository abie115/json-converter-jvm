#Task #2: JSONConverter

Konwerter POJO to Json

Program uruchamiamy:
<br>
``` mvn compile exec:java```
<br>
<br>
Pliki .json w katalogu jsonFile. Json również wyświetla się w konsoli. 
Porównanie średniego czasu konwertowania do Jsona przez mój konwerter oraz przez bibliotekę Gson.
Średni czas dla obu sposóbów konwertowania wyświetla się w konsoli.
Konwerter obsługuje pola publiczne i prywatne, typy proste, tablice, listy bez zagnieżdżeń.

Przykładowy wynik użycia mojego konwertera:
```
{"Animal": {
	"name": "Delfin",
	"age": 5,
	"food": [
		"gruszka",
		"glony",
		"marchewka",
		"pietruszka"
	],
	"number": [
		1,
		2,
		45
	],
	"weight": 50.45,
	"vegetarian": false,
	"listKeeper": [
		"Kowalski",
		"Wiaderko"
	],
	"empty": [

	],
	"listArray": [
		12,
		15
	]
}}
```
##Test
Maszyna na której został wykonany test:
```
CPU:  Intel Core i5 2.5GHz
RAM: 6GB
OS: Windows 7 Home Premium 64-bit
Java wersja: 1.8.0_73
```

Program iterował w pęli 1000 razy. Następnie każda z metod została wywołana 10 razy. Skrajne wyniki zostały pominięte. Czas konwertowania został uśredniony.
<br>
```
Sredni czas mojego konwertera: 4.24151155E7 ns
Sredni czas Gson konwerter: 1.4237906E7 ns
```



