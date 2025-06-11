# Kotlin OPML

[![Build Status](https://github.com/janseeger/kotlin-opml/actions/workflows/unit-tests.yml/badge.svg)](https://github.com/janseeger/kotlin-opml/actions/workflows/unit-tests.yml)
[![GitHub License](https://img.shields.io/github/license/janseeger/kotlin-opml)](https://github.com/janseeger/kotlin-opml/blob/main/LICENSE)
[![GitHub Tag](https://img.shields.io/github/v/tag/janseeger/kotlin-opml)](https://github.com/janseeger/kotlin-opml/tags)

A standalone library that allows easy parsing and writing of OPML formatted files.

# Usage

This library comes batteries-included. You can invoke the the application and pass a path to an OPML file, which it will then clean up and print statistics about to the console;

```shell
./amper run /some/path/to/an/opml/file
```

# Bugs and Issues

If you encounter an (somewhat valid) OPML file that this lib fails to process but you think it should anyway, please don't hesitate to file an issue.