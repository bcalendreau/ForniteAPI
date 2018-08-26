# ForniteAPI
An API to get data directly from Epic Server with JSON

## Getting Started

### Prerequisites
You need an Epic account

### Setup
Use application.properties file to enter your login and password of your Epic account.
There you can specify a token as well if you want to limit the calls to your API.

### Usage

The API has one endpoint : Stats, that retrieve all the stats of a given player name for all platforms


#### Stats

Query params : 
  player 
  window (alltime or weekly for the current season)
  
example : stats?player=ashtk&window=alltime (no stats for consoles as I don't play on them !)

```js
{
    "pc": {
        "solo": {
            "stats": {
                "wins": 160,
                "top10": 527,
                "top25": 787,
                "score": 332093,
                "kills": 4550,
                "matches_played": 1516,
                "kill_death_ratio": 3.36,
                "kills_per_match": 3,
                "win_ratio": 10.55,
                "minutes_played": 11783
            }
        },
        "duo": {
            "stats": {
                "wins": 22,
                "top5": 55,
                "top12": 109,
                "score": 44784,
                "kills": 591,
                "matches_played": 323,
                "kill_death_ratio": 1.96,
                "kills_per_match": 1.83,
                "win_ratio": 6.81,
                "minutes_played": 2366
            }
        },
        "squad": {
            "stats": {
                "wins": 5,
                "top3": 14,
                "top6": 27,
                "score": 21118,
                "kills": 227,
                "matches_played": 137,
                "kill_death_ratio": 1.72,
                "kills_per_match": 1.66,
                "win_ratio": 3.65,
                "minutes_played": 885
            }
        }
    }
}
```
