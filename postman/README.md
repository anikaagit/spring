# TimeCapsule API — Postman Testing

Base URL: `http://localhost:8585/api/data`

> Note: during local development in WSL, requests were sent to the WSL 
> network IP (e.g. `http://172.28.111.221:8585/api/data`) instead of 
> `localhost` due to a WSL↔Windows localhost forwarding issue. The API 
> itself is configured to run on `localhost:8585/api/data` as specified.

## Endpoints

### GET /api/data — Get all time capsules
**Response: 200 OK**
```json
[
  {
    "id": 1,
    "title": "Message to 2030",
    "message": "Hope AI is friendly by now",
    "author": "You",
    "unlockDate": "2030-01-01",
    "opened": false
  }
]
```

### GET /api/data/{id} — Get one time capsule by ID
**Response: 200 OK**
```json
{
  "id": 1,
  "title": "Message to 2030",
  "message": "Hope AI is friendly by now",
  "author": "You",
  "unlockDate": "2030-01-01",
  "opened": false
}
```
**Response if not found: 404 Not Found**

### POST /api/data — Create a new time capsule
**Request body:**
```json
{
  "title": "Message to 2030",
  "message": "Hope AI is friendly by now",
  "author": "You",
  "unlockDate": "2030-01-01",
  "isOpened": false
}
```
**Response: 201 Created**
```json
{
  "id": 1,
  "title": "Message to 2030",
  "message": "Hope AI is friendly by now",
  "author": "You",
  "unlockDate": "2030-01-01",
  "opened": false
}
```

### PUT /api/data/{id} — Update an existing time capsule
**Request body:**
```json
{
  "title": "Updated Capsule",
  "message": "New message inside",
  "author": "You",
  "unlockDate": "2031-01-01",
  "isOpened": true
}
```
**Response: 200 OK** (returns updated object)
**Response if not found: 404 Not Found**

### DELETE /api/data/{id} — Delete a time capsule
**Response: 200 OK** (empty body)
**Response if not found: 404 Not Found**

## Error handling
- `404 Not Found` — returned when a requested ID doesn't exist (GET/PUT/DELETE)
- `500 Internal Server Error` — returned if save/delete operations throw an exception