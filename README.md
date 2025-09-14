# Football World Cup Scoreboard

A simple in-memory library to manage a live football World Cup scoreboard.  
This project includes a small console interface for demonstration and JUnit tests to ensure correctness.

## Features

- Start a game (home and away teams, initial score 0-0)
- Update score of a game
- Finish a game (remove from scoreboard)
- Get summary of all games, sorted by:
    1. Total score (home + away) descending
    2. For games with same total score, the most recently added comes first
- Display a nicely formatted scoreboard in the console

## Assumptions

- Cannot start a game with the **same team** for both home and away
- Cannot start **duplicate games** (same home & away teams)
- Scores must be **non-negative integers**
- In-memory storage only (no database or external storage)
- The scoreboard is intended for **single-threaded use**, concurrency is not handled
- Display output assumes a **console with monospaced font** for alignment