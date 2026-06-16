# Entity Relationship Diagram (ERD)

## Overview

The Enviro365 system manages investors, portfolios, products, and withdrawal notices.

---

## ERD Diagram

```txt
+------------------+
|    Investor      |
+------------------+
| PK Id            |
| FirstName        |
| LastName         |
| Email            |
| DateOfBirth      |
+--------+---------+
         |
         | 1
         |
         | *
+--------v---------+
|    Portfolio     |
+------------------+
| PK Id            |
| PortfolioName    |
| Balance          |
| FK InvestorId    |
+--------+---------+
         |
         | 1
         |
         | *
+--------v---------+
|     Product      |
+------------------+
| PK Id            |
| ProductName      |
| ProductType      |
| Balance          |
| FK PortfolioId   |
+--------+---------+
         |
         | 1
         |
         | *
+--------v---------------+
|   WithdrawalNotice     |
+------------------------+
| PK Id                  |
| Amount                 |
| Reason                 |
| Status                 |
| CreatedAt              |
| FK InvestorId          |
| FK PortfolioId         |
| FK ProductId           |
+------------------------+
```

---

## Entity Descriptions

### Investor

Represents an investor using the system.

Attributes:

* Id
* FirstName
* LastName
* Email
* DateOfBirth

Relationship:

* One Investor can own many Portfolios

---

### Portfolio

Represents an investment portfolio.

Attributes:

* Id
* PortfolioName
* Balance
* InvestorId

Relationship:

* Belongs to one Investor
* Contains many Products

---

### Product

Represents an investment product.

Attributes:

* Id
* ProductName
* ProductType
* Balance
* PortfolioId

Product Types:

* SAVINGS
* RETIREMENT
* INVESTMENT

Relationship:

* Belongs to one Portfolio
* Can have many Withdrawal Notices

---

### Withdrawal Notice

Represents a withdrawal request.

Attributes:

* Id
* Amount
* Reason
* Status
* CreatedAt
* InvestorId
* PortfolioId
* ProductId

Status Values:

* PENDING
* APPROVED
* REJECTED

Relationship:

* Linked to one Investor
* Linked to one Portfolio
* Linked to one Product

---

## Cardinality

### Investor → Portfolio

```txt
1 : Many
```

One investor can own multiple portfolios.

---

### Portfolio → Product

```txt
1 : Many
```

One portfolio can contain multiple products.

---

### Product → Withdrawal Notice

```txt
1 : Many
```

One product can have multiple withdrawal notices.

---

## Business Rules Reflected in the ERD

1. Investors must exist before withdrawals can be created.

2. Products belong to a portfolio.

3. Portfolios belong to an investor.

4. Retirement products can only be withdrawn from when the investor is older than 65 years.

5. Withdrawal amount may not exceed 90% of product balance.

6. Approved withdrawals reduce both product and portfolio balances.

---

## Conclusion

The ERD provides a clear representation of the relationships between investors, portfolios, products, and withdrawal notices while supporting all business requirements of the assessment.
