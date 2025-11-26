# 🏥 Hospital Management System – JWT Authentication & Role-Based Authorization

A secure Spring Boot application implementing **JWT Authentication**, **Spring Security 6**, and **Role-Based Access Control**.  
This project demonstrates how to protect APIs using JWT tokens and assign roles such as **ADMIN, DOCTOR, RECEPTIONIST**.

---

## 🚀 Features

### 🔐 Authentication
- User Registration (`/auth/register`)
- Login & JWT Token Generation (`/auth/login`)
- Password encryption using BCrypt

### 🔑 Authorization
| API Route | Access |
|----------|--------|
| `/auth/**` | Public |
| `/api/v1/doctor/**` | ADMIN |
| `/api/v1/patient/**` | ADMIN, DOCTOR |
| `/api/v1/appointment/**` | ADMIN, RECEPTIONIST |
| `/api/v1/bill/**` | ADMIN, RECEPTIONIST |
| Others | Authenticated users |

### 🛡 Security Includes:
- Custom JWT Filter
- JWT Validation
- Stateless Authentication
- SecurityContext user authentication
- Clean and scalable structure

---

## 📦 Technologies Used
- Java 17+
- Spring Boot 3
- Spring Security 6
- Spring Data JPA
- JWT (JJWT 0.12.5)
- MySQL
- Lombok

---

## 📁 Project Structure

