# Contributing to DashboardFx

Thanks for taking the time to contribute to DashboardFx.
This guide explains how to prepare the project, make a focused change, and open a pull request.

## Prerequisites

Before working on the project, install:

- Git
- Java 23 or newer
- An IDE with Java and Gradle support

You do not need to install Gradle globally. Use the Gradle Wrapper included in this repository.

## Fork and Clone

Fork the repository on GitHub, then clone your fork with submodules:

```bash
git clone --recursive https://github.com/<your-username>/DashboardFx.git
cd DashboardFx
```

The `--recursive` option clones the project submodules too:

- `glad`
- `presentation`
- `blockcode`

If you already cloned the project without submodules, initialize them with:

```bash
git submodule update --init --recursive
```

## Create a Branch

Create your working branch from the latest `alpha` branch:

```bash
git switch alpha
git pull origin alpha
git switch -c <your-branch-name>
```

Pull requests should target the `alpha` branch unless the maintainers request a different target.

## Build and Run

On Windows, build and run the project with:

```powershell
.\gradlew.bat build
.\gradlew.bat run
```

On Linux or macOS, use:

```bash
./gradlew build
./gradlew run
```

Run the build before opening a pull request to make sure the project still compiles.

## Keep Changes Focused

Each pull request should focus on one task.
Avoid mixing unrelated changes in the same pull request.

Please do not include:

- generated files
- IDE-only files
- unrelated formatting changes
- accidental submodule updates
- changes from another task

If your task does not require changing a submodule, leave `glad`, `presentation`, and `blockcode` untouched.

## Commit Your Work

Check your changes before committing:

```bash
git status
git diff
```

Stage only the files related to your task:

```bash
git add CONTRIBUTING.md
git commit -m "docs: add contributing guide"
```

## Open a Pull Request

Push your branch:

```bash
git push -u origin <your-branch-name>
```

Then open a pull request against `alpha`.
In the pull request description, include:

- what changed
- why the change is useful
- how you checked or tested it

Thank you for helping improve DashboardFx.
