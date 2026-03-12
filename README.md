# Fitness Management System APP

The goal of the application is to simulate a **fitness center management system** that allows internal staff to manage:
* clients
* trainers
* subscriptions
* workout classes
* reports

The application is implemented as a **console-based system**, with the main focus placed on **clean OOP design**, **extensibility**, and proper use of modern Java programming concepts.

---

## Project Structure

* **model** – domain entities such as `Client`, `Trainer`, `Subscription`, and `WorkoutClass`
* **service** – application logic and data management
* **menu** – console menus for navigating the application
* **util** – helper utilities
* **app** – application entry point

## Features

* Manage clients and their subscriptions
* Manage trainers and trainer types
* Manage workout classes and intensity levels
* Console menu navigation
* Generate basic reports

---

## Future Development

This project is intended to evolve into a **fully featured fitness application** inspired by modern platforms such as **ESX**.

Planned future features include:

### User Interface

A graphical interface that will replace the current console-based interaction.

### Client–Server Architecture

The system will be redesigned into a distributed application where:

* a backend service manages the business logic
* clients interact through a UI application or web interface

### AI-based Fitness Features

Planned AI-assisted components include:

**Gym Discovery Assistant**

* Automatically find nearby fitness centers
* Map-based interface
* Location-based recommendations

**Workout Recommendation Engine**

* AI-generated workout suggestions
* Personalized training plans
* Difficulty adaptation based on progress
