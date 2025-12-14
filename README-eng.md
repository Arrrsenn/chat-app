# Chat Application with Kafka and WebSocket

Real-time chat application built with Spring Boot, Apache Kafka, and WebSocket (STOMP).

## Architecture

```
Browser ──HTTP POST──→ Producer ──→ Kafka ──→ Consumer ──→ WebSocket ──→ Browsers
```

## Modules

### chat-api
Shared module containing common DTOs and interfaces used across services.

- `ChatMessage` — message model (username, content, roomId, timestamp)
- `MessageSender` — interface for sending messages
- `MessageReceiver` — interface for receiving messages

### chat-producer-service
REST API service for sending messages to Kafka.

- **Port:** 8085
- **Endpoint:** `POST /api/v1/send`
- Receives messages via HTTP and publishes them to Kafka topic

### chat-consumer-service
Service that consumes messages from Kafka and broadcasts them via WebSocket.

- **Port:** 8090
- **WebSocket endpoint:** `/ws`
- **STOMP destinations:** `/topic/chat/{roomId}`
- Listens to Kafka topic and pushes messages to subscribed clients

### chat-db
Database module (for future implementation).

## How It Works

1. **User sends a message** through the web interface
2. **Browser sends HTTP POST** request to Producer service
3. **Producer publishes** the message to Kafka topic
4. **Consumer receives** the message from Kafka
5. **Consumer broadcasts** the message via WebSocket to all clients subscribed to the room
6. **Browsers receive** the message in real-time

### Room System

Users can join different chat rooms. Each room has its own STOMP destination:
- `/topic/chat/general` — general chat
- `/topic/chat/room1` — room 1
- `/topic/chat/room2` — room 2

Messages sent to a room are only delivered to users subscribed to that room.

## Prerequisites

- Java 21
- Apache Kafka (running on localhost:9092)
- Maven

## Running the Application

### 1. Start Kafka

```bash
# Start Zookeeper
bin/zookeeper-server-start.sh config/zookeeper.properties

# Start Kafka
bin/kafka-server-start.sh config/server.properties
```

### 2. Build the project

```bash
mvn clean install
```

### 3. Start Producer Service

```bash
cd chat-producer-service
mvn spring-boot:run
```

### 4. Start Consumer Service

```bash
cd chat-consumer-service
mvn spring-boot:run
```

### 5. Open the chat

Navigate to `http://localhost:8090/` in your browser.

## API Reference

### Send Message

```http
POST http://localhost:8085/api/v1/send
Content-Type: application/json

{
    "username": "John",
    "content": "Hello!",
    "roomId": "general"
}
```

## Future Improvements

- [ ] **Database integration** — persist chat history using chat-db module
- [ ] **User authentication** — add login/registration with Spring Security
- [ ] **Private messages** — direct messaging between users
- [ ] **Message history** — load previous messages when joining a room
- [ ] **Typing indicators** — show when someone is typing
- [ ] **Online users list** — display who is currently in the room
- [ ] **File sharing** — ability to send images and files
- [ ] **Message reactions** — add emoji reactions to messages
- [ ] **Docker support** — containerize all services with docker-compose
- [ ] **Multiple Kafka partitions** — scale consumers for high load

## Tech Stack

- **Java 21**
- **Spring Boot 3.5.8**
- **Spring Kafka**
- **Spring WebSocket (STOMP)**
- **SockJS**
- **Apache Kafka**
- **Lombok**
- **Maven**