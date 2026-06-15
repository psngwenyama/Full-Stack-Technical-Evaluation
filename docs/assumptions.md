# Project Assumptions

## Overview

The following assumptions were made during the implementation of the Enviro365 assessment.

## Assumptions

### 1. Database

An H2 in-memory database was used for simplicity and demonstration purposes.

In a production environment, H2 would be replaced with:

- MySQL
- PostgreSQL
- SQL Server

### 2. Seed Data

Sample investors, portfolios, and products are automatically inserted when the application starts.

This was done to simplify testing and demonstration.

### 3. Withdrawal Status

All newly created withdrawal notices are assigned a default status of:

```txt
APPROVED