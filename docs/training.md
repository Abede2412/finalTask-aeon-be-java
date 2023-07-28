# Training API spec

## Insert training

Endpoint : POST /v1/training

Request Body :

```json
{
  "namaPengajar":"PAK EKO",
  "tema":"Backend with NodeJs"
}
```
Response Body (Success) :

```json
{
  "data": {
    "id": 23,
    "namaPengajar": "PAK EKO",
    "tema": "Backend with NodeJs"
  },
  "paging": null,
  "errors": null
}
```
Response Body (Failde):

```json
{
  "data": null,
  "paging": null,
  "errors": "namaPengajar: Nama pengajar tidak boleh kosong"
}
```

## Update training

Endpoint : PUT /v1/training

Request Body :

```json
{
  "id": 23,
  "namaPengajar": "PAK EKO UPDATE",
  "tema": "Backend with NodeJs UPDATE"
}
```

Response Body (Success) :

```json
{
  "data": {
    "id": 23,
    "namaPengajar": "PAK EKO UPDATE",
    "tema": "Backend with NodeJs UPDATE"
  },
  "paging": null,
  "errors": null
}
```
Response Body (Failed):

```json
{
  "data": null,
  "paging": null,
  "errors": "tema: Tema pelatihan tidak boleh kosong"
}
```

## List training like by nama, Pagination

Endpoint : GET /v1/training/list

Query Param :
- tema : String, optional
- page : Integer, start from 0, default 0
- size : Integer, default 10

Response Body (Success) :

```json
{
  "data": [
    {
      "id": 22,
      "namaPengajar": "PAK EKO",
      "tema": "Backend with NodeJs"
    },
    {
      "id": 24,
      "namaPengajar": "PAK EKO UPDATE",
      "tema": "Backend with NodeJs UPDATE"
    },
    {
      "id": 25,
      "namaPengajar": "PAK EKO UPDATE 24",
      "tema": "Backend with NodeJs UPDATE"
    },
    {
      "id": 23,
      "namaPengajar": "PAK EKO UPDATE",
      "tema": "Backend with NodeJs UPDATE"
    }
  ],
  "paging": {
    "currentPage": 0,
    "totalPage": 1,
    "size": 10
  },
  "errors": null
}
```
Response Body (Failed):

```json
{
  "data": null,
  "paging": null,
  "errors": "message error"
}
```

## Get By Id Training

Endpoint : GET /v1/training/{ID}

Response Body (Success) :

```json
{
  "data": {
    "id": 20,
    "namaPengajar": "PAK EKO",
    "tema": "Backend with java"
  },
  "paging": null,
  "errors": null
}
```
Response Body (Failed):

```json
{
  "data": null,
  "paging": null,
  "errors": "message error"
}
```