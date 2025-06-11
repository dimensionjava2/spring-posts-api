# Prueba Técnica - Backend Developer
## API de Gestión de Posts de Usuarios

### Objetivo

Desarrollar una API REST que consuma la API externa de JSONPlaceholder para gestionar posts de usuarios. El candidato debe demostrar su capacidad para integrar servicios externos usando Feign Client, mapear datos entre diferentes DTOs y crear endpoints funcionales.

### Stack Tecnológico

- Java 11+
- Spring Boot
- Spring Cloud OpenFeign
- Maven
- Lombok

### Descripción del Problema

Se requiere crear una API que actúe como intermediario entre clientes y la API pública de JSONPlaceholder (https://jsonplaceholder.typicode.com). La API debe permitir obtener información de usuarios junto con sus posts, con la opción de incluir el conteo de comentarios.

### Integración Externa

La API debe consumir los siguientes endpoints de JSONPlaceholder:

- **GET** `/users/{userId}` - Obtener información de usuario
- **GET** `/users/{userId}/posts` - Obtener posts de un usuario
- **GET** `/posts/{postId}/comments` - Obtener comentarios de un post

### Endpoints Obligatorios a Implementar

Los siguientes endpoints son **críticos** y serán evaluados principalmente:

#### 1. Obtener Posts de Usuario (POST)
- **POST** `/posts/api/v1/user/posts`
- Recibe un `PostsRequest` en el body
- Debe retornar información del usuario y sus posts
- Si `includeComments` es true, incluir el conteo de comentarios por post

#### 2. Resumen de Posts de Usuario (GET) - Endpoint Bonus
- **GET** `/posts/api/v1/user/{userId}/summary?includeComments={boolean}`
- Parámetro `includeComments` opcional (default: false)
- Funcionalidad idéntica al endpoint POST pero usando parámetros de URL

### Reglas de Negocio Esenciales

1. **Validación de Usuario**: Verificar que el usuario existe antes de buscar sus posts
2. **Mapeo de Datos**: Transformar los DTOs externos a DTOs de respuesta internos
3. **Conteo Condicional**: Solo incluir `commentsCount` si `includeComments` es true
4. **Logging**: Implementar logs informativos para el seguimiento de peticiones

### Ejemplos de Peticiones y Respuestas

**Petición POST:**
```json
{
  "userId": 1,
  "includeComments": true
}
```

**Respuesta esperada:**
```json
{
  "user": {
    "id": 1,
    "name": "Leanne Graham",
    "email": "Sincere@april.biz"
  },
  "totalPosts": 10,
  "posts": [
    {
      "id": 1,
      "title": "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
      "body": "quia et suscipit...",
      "commentsCount": 5
    }
  ]
}
```

**Petición GET:**
```
GET /posts/api/v1/user/1/summary?includeComments=false
```

---

**Tiempo Estimado: 60 minutos**

**Recursos Útiles:**
- Documentación JSONPlaceholder: https://jsonplaceholder.typicode.com/
- Usuarios de prueba disponibles: 1-10
