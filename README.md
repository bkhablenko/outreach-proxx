# outreach-proxx

[![CircleCI](https://circleci.com/gh/bkhablenko/outreach-proxx.svg?style=shield)](https://circleci.com/gh/bkhablenko/outreach-proxx)

**NOTE:** The UI is beyond terrible, but at least it gives you *some* way of interacting with the game :shrug:

![Screenshot](screenshot.png)

## How to run

```bash
./gradlew clean build -x test && docker compose run app
```

## TODO

- [ ] Run the build command with Docker Compose
- [ ] Test [Game](core/src/main/kotlin/com/github/bkhablenko/outreach/proxx/game/Game.kt) and [GameBoard](core/src/main/kotlin/com/github/bkhablenko/outreach/proxx/game/GameBoard.kt)

:warning: Due to time constraints, we focus on delivering a working product. As a result, test coverage suffers significantly.

The main game logic is difficult to test in its current form.
Therefore, we should consider breaking it down into smaller components to improve testability.

(For example, [this](https://github.com/bkhablenko/outreach-proxx/blob/4766fc1b77908593a763171e3644290cf263b517/core/src/main/kotlin/com/github/bkhablenko/outreach/proxx/game/GameBoard.kt#L25-L32) could be a component on its own.)
