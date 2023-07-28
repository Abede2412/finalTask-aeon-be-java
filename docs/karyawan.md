# Karyawan API spec

## Insert Karyawan + detail

Endpoint : POST /v1/karyawan

Request Body :

```json
{
  "nama": "Abd Rahman",
  "alamat": "Jalan Merpati, RT 001 RW 001, Kelurahan, Kecamatan, Kabupaten, Provinsi",
  "dob": "10/10/2010",
  "status": "BELUM KAWIN",
  "jk": "LAKI-LAKI",
  "nik": "1234567890123456",
  "npwp": "123456789012345"
}
```

Response Body (Success) :
```json
{
  "data": {
    "id": 35,
    "nama": "Abd Rahman",
    "status": "BELUM KAWIN",
    "jk": "LAKI-LAKI",
    "alamat": "Jalan Merpati, RT 001 RW 001, Kelurahan, Kecamatan, Kabupaten, Provinsi",
    "dob": "2010-10-10",
    "detailKaryawan": {
      "id": 35,
      "nik": "1234567890123456",
      "npwp": "123456789012345"
    }
  },
  "paging": null,
  "errors": null
}
```

Response Body(Failed) :
```json
{
  "data": null,
  "paging": null,
  "errors": "message error"
}
```
## List Karyawan like by name

Endpoint : GET /v1/karyawan/list

Query Param :

- nama : String, optional
- page : Integer, start from 0, default 0, optional
- size : Integer default 10, optional

Response Body (Success) :
nama=rahman

```json
{
  "data": [
    {
      "id": 30,
      "nama": "RAHMAN",
      "status": "lajang Update",
      "jk": "laki-laki",
      "alamat": "pulau paus Update",
      "dob": "2012-12-12",
      "detailKaryawan": {
        "id": 30,
        "nik": "1234567890123456",
        "npwp": "123456789012345"
      }
    },
    {
      "id": 34,
      "nama": "Abd Rahman B. Tasrid Update",
      "status": "lajang Update",
      "jk": "laki-laki",
      "alamat": "Makassar Update",
      "dob": "2012-12-12",
      "detailKaryawan": {
        "id": 34,
        "nik": "1234567890123456",
        "npwp": "123456789012345"
      }
    },
    {
      "id": 35,
      "nama": "Abd Rahman",
      "status": "BELUM KAWIN",
      "jk": "LAKI-LAKI",
      "alamat": "Jalan Merpati, RT 001 RW 001, Kelurahan, Kecamatan, Kabupaten, Provinsi",
      "dob": "2010-10-10",
      "detailKaryawan": {
        "id": 35,
        "nik": "1234567890123456",
        "npwp": "123456789012345"
      }
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
Response Body(Failed) :
```json
{
  "data": null,
  "paging": null,
  "errors": "message error"
}
```

## Update Karyawan + detail

Endpoint : PUT /v1/karyawan

Request Body :

```json
{
  "id": 35,
  "nama": "ABD RAHMAN UPDATE",
  "alamat": "Jalan Merpati, RT 001 RW 001, Kelurahan, Kecamatan, Kabupaten, Provinsi",
  "dob": "10/10/2010",
  "status": "BELUM KAWIN",
  "jk": "LAKI-LAKI",
  "nik": "1234567890123456",
  "npwp": "123456789012345"
}
```

Response Body (Success) :
```json
{
  "data": {
    "id": 35,
    "nama": "ABD RAHMAN UPDATE",
    "status": "BELUM KAWIN",
    "jk": "LAKI-LAKI",
    "alamat": "Jalan Merpati, RT 001 RW 001, Kelurahan, Kecamatan, Kabupaten, Provinsi",
    "dob": "2010-10-10",
    "detailKaryawan": {
      "id": 35,
      "nik": "1234567890123456",
      "npwp": "123456789012345"
    }
  },
  "paging": null,
  "errors": null
}
```
Response Body(Failed) :
```json
{
  "data": null,
  "paging": null,
  "errors": "message error"
}
```

## GET By ID Karyawan

Endpoint : GET /v1/karyawan/{id}

Response Body (Success) :
```json
{
  "data": {
    "id": 35,
    "nama": "ABD RAHMAN UPDATE",
    "status": "BELUM KAWIN",
    "jk": "LAKI-LAKI",
    "alamat": "Jalan Merpati, RT 001 RW 001, Kelurahan, Kecamatan, Kabupaten, Provinsi",
    "dob": "2010-10-10",
    "detailKaryawan": {
      "id": 35,
      "nik": "1234567890123456",
      "npwp": "123456789012345"
    }
  },
  "paging": null,
  "errors": null
}
```
Response Body(Failed) :
```json
{
  "data": null,
  "paging": null,
  "errors": "404 NOT_FOUND \"Karyawan dengan id 1000 tidak ditemukan\""
}
```

