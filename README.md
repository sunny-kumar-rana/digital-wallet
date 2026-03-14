# Digital Wallet (Java + Servlets + JDBC + Oracle XE)

A backend-focused **Digital Wallet system** built with **Java Servlets, JDBC, and Oracle XE**.
The project demonstrates secure money transfers using **database transactions and row-level locking**, along with a simple JSP-based frontend.

This project was built to demonstrate **backend system design, transactional integrity, and layered architecture** suitable for technical interviews.

---

## Features

* User registration
* Automatic wallet creation on signup
* Login with session-based authentication
* Check wallet balance
* Transfer funds between users
* Transaction history
* Row-level locking to prevent race conditions
* Atomic money transfers using database transactions
* JSP frontend for interacting with the backend

---

## Technology Stack

Backend

* Java
* Jakarta Servlets
* JDBC
* Oracle Database Express Edition (XE)

Frontend

* JSP
* HTML / CSS
* Basic JavaScript (AJAX for balance & transactions)

Build & Deployment

* Maven
* Apache Tomcat

---

## Project Architecture

The project follows a layered architecture:

```
Servlet Layer
     ↓
Service Layer
     ↓
DAO Layer
     ↓
Oracle Database
```

### Packages

```
com.wallet
 ├── servlet    → HTTP endpoints
 ├── service    → Business logic
 ├── dao        → Database operations
 ├── model      → Entity classes
 └── util       → Utility classes (DB connection)
```

---

## Database Schema

### Users

```
users
-----
id
name
email
password
created_at
```

### Wallets

```
wallets
-------
user_id
balance
updated_at
```

### Transactions

```
transactions
------------
id
sender_id
receiver_id
amount
status
created_at
```

---

## Key Engineering Concept

### Transaction-Safe Transfers

Money transfer uses a **single database transaction**:

1. Lock sender wallet row
2. Lock receiver wallet row
3. Validate balance
4. Update balances
5. Insert transaction record
6. Commit transaction

If any step fails, the entire transaction is **rolled back**.

Example SQL used:

```
SELECT user_id, balance
FROM wallets
WHERE user_id = ?
FOR UPDATE
```

This prevents race conditions and double spending.

---

## Setup Instructions

### 1. Clone Repository

```
git clone https://github.com/your-username/digital-wallet.git
```

---

### 2. Configure Database

Install **Oracle XE** and create tables:

```
users
wallets
transactions
```

Create sequences:

```
CREATE SEQUENCE users_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE transactions_seq START WITH 1000 INCREMENT BY 1;
```

---

### 3. Configure Database Connection

Edit:

```
com.wallet.util.DBConnection
```

```
jdbc:oracle:thin:@localhost:1521/XEPDB1
username
password
```

---

### 4. Build the Project

```
mvn clean package
```

This generates:

```
target/digital-wallet.war
```

---

### 5. Deploy to Tomcat

Copy the WAR to:

```
tomcat/webapps/
```

Start Tomcat.

---

### 6. Open Application

```
http://localhost:8080/digital-wallet/login.jsp
```

---

## Application Flow

```
Register
   ↓
Login
   ↓
Dashboard
   ↓
Transfer Money
   ↓
View Transaction History
```

---

## API Endpoints

| Endpoint        | Method | Description             |
| --------------- | ------ | ----------------------- |
| `/register`     | POST   | Register user           |
| `/login`        | POST   | Authenticate user       |
| `/balance`      | GET    | Retrieve wallet balance |
| `/transfer`     | POST   | Transfer funds          |
| `/transactions` | GET    | Get transaction history |

---

## Example Transfer Request

```
POST /transfer

receiverId=2
amount=100
```

---

## Security Notes

This project focuses on **transaction safety and backend architecture**.
In production systems the following should also be implemented:

* Password hashing
* JWT or OAuth authentication
* Rate limiting
* Input validation
* Ledger-based accounting
* Idempotent transfer APIs

---

## Future Improvements

Possible upgrades:

* REST API version
* React frontend
* Docker deployment
* Ledger-based accounting model
* Redis caching
* API authentication using JWT
* Microservice architecture

---

## Author

Built as a backend systems project demonstrating:

* JDBC transaction management
* concurrency-safe money transfers
* layered Java architecture
* servlet-based web application development
