# Vysvetlenie k súborom *.pvzlevel*
Súbory, ktoré pomáhajú vytvoriť Zombies  

## Príklad
```csv
5, 0
10, 0
10, 1
```

## Vysvetlenie
### Prvá časť
Prvá časť určuje časový delay  
Je to čas, ktorý musí uplynúť, než sa vykoná akcia  
V tomto prípade je to 5 sekúnd, 10 sekúnd a 10 sekúnd  

### Druhá časť
Druhá časť určuje, ktorý zombie sa má zobraziť  
Číslo je poradie v [zozname zombies](#zoznam-zombies)
V tomto prípade sa zobrazí Zombie, Zombie, Zombie Kuželka

## Zoznam zombies
| ID | Zombie                 |
|----|------------------------|
| 0  | Zombie                 |
| 1  | Zombie Kuželka         |
| 2  | Zombie Vedro           |
| 3  | Zombie Hlavný Tanečník |

## Príklad - súbor *5.zombiedata*
```csv
60, 0
20, 0
20, 3
30, 1
20, 3
40, 2
```

1. Počká sa 60 sekúnd, potom sa zobrazí sa *Zombie*
2. Počká sa 20 sekúnd, potom sa zobrazí sa *Zombie*
3. Počká sa 20 sekúnd, potom sa zobrazí sa *Zombie Hlavný Tanečník*
4. Počká sa 30 sekúnd, potom sa zobrazí sa *Zombie Kuželka*
5. Počká sa 20 sekúnd, potom sa zobrazí sa *Zombie Hlavný Tanečník*
6. Počká sa 40 sekúnd, potom sa zobrazí sa *Zombie Vedro*
