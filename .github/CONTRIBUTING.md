# Contributing

This is a personal project maintained by [@Joako601](https://github.com/Joako601). Bug reports and suggestions are welcome as [issues](https://github.com/Joako601/market-backend/issues). Pull requests are reviewed on a best-effort basis, so open an issue first for anything larger than a small fix.

## Set up

You need Java 21 and PostgreSQL 14 or later. The Gradle wrapper downloads Gradle for you.

```sh
git clone https://github.com/Joako601/market-backend.git
cd market-backend
createdb -U postgres market
psql -U postgres -d market -f db/market.sql
cp src/main/resources/application-dev.properties.example src/main/resources/application-dev.properties
```

Edit `application-dev.properties` and set your PostgreSQL password. Git ignores that file, so the password stays local.

## Build and test

```sh
./gradlew build    # compile and run the tests
./gradlew test     # tests only
./gradlew bootRun  # start the server on port 8090
```

The tests start the full Spring context, so PostgreSQL must be running with the `market` database. CI does the same with a PostgreSQL service container; see [ci.yml](workflows/ci.yml).

Code style follows [.editorconfig](../.editorconfig). There is no formatter or linter to run.

## Commits

Commit messages follow [Conventional Commits](https://www.conventionalcommits.org/en/v1.0.0/). They drive the version number and the changelog, so the type matters:

| Type | Use it for | Release |
|---|---|---|
| `feat` | a new endpoint or behavior | minor |
| `fix` | a bug fix | patch |
| `feat!` or a `BREAKING CHANGE:` footer | a change that breaks existing clients | minor while at `0.x` |
| `docs`, `chore`, `ci`, `test`, `refactor` | everything else | none |

Write the subject in lowercase and in the imperative, under 72 characters, without a final period:

```text
fix(purchase): reject purchases without items
```

## Branches and pull requests

- Branch from `main` and name the branch after the change, for example `fix/empty-purchase`.
- Keep one logical change per commit.
- CI must pass before merge.

## Releases

[release-please](https://github.com/googleapis/release-please) keeps a release pull request open with the next version and the changelog. Merging it tags the release and publishes it on GitHub. Do not edit `version.txt` or `CHANGELOG.md` by hand.
