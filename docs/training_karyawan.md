# Training Karyawan API Spec

## Insert Training Karyawan

Endpoint : POST /v1/training-karyawan

Request Body :

```json
{
  "karyawanId": 33,
  "trainingId": 22,
  "tanggalTraining": "10/10/2023"
}
```

Response Body (Success) :

```json
{
  "data": {
    "id": 21,
    "tanggalTraining": "2023-10-10",
    "karyawan": {
      "id": 33,
      "nama": "Sanji",
      "status": "lajang Update",
      "jk": "laki-laki",
      "alamat": "pulau paus Update",
      "dob": "2012-12-12",
      "detailKaryawan": {
        "id": 33,
        "nik": "1234567890123456",
        "npwp": "123456789012345"
      }
    },
    "training": {
      "id": 22,
      "namaPengajar": "PAK EKO",
      "tema": "Backend with NodeJs"
    }
  },
  "paging": null,
  "errors": null
}
```

Response Body (Failed) :
```json
{
  "data": null,
  "paging": null,
  "errors": "message error"
}
```

## List Training Karyawan 

Endpoint : GET /v1/training-karyawan/list

Query Param :
- nama-karyawan : String
- tema-training : String
- page : Integer, start from 0, default 0
- size : Integer, default 10

Response Body (Sukses):

nama-karyawan = luffy 
tema-training = golang

```json
{
  "data": [
    {
      "id": 2,
      "tanggalTraining": "2023-10-10",
      "karyawan": {
        "id": 31,
        "nama": "Luffy",
        "status": "lajang Update",
        "jk": "laki-laki",
        "alamat": "pulau paus Update",
        "dob": "2012-12-12",
        "detailKaryawan": {
          "id": 31,
          "nik": "1234567890123456",
          "npwp": "123456789012345"
        }
      },
      "training": {
        "id": 20,
        "namaPengajar": "PAK EKO",
        "tema": "Backend with java"
      }
    },
    {
      "id": 3,
      "tanggalTraining": "2023-10-10",
      "karyawan": {
        "id": 31,
        "nama": "Luffy",
        "status": "lajang Update",
        "jk": "laki-laki",
        "alamat": "pulau paus Update",
        "dob": "2012-12-12",
        "detailKaryawan": {
          "id": 31,
          "nik": "1234567890123456",
          "npwp": "123456789012345"
        }
      },
      "training": {
        "id": 21,
        "namaPengajar": "PAK EKO",
        "tema": "Backend with Golang"
      }
    },
    {
      "id": 4,
      "tanggalTraining": "2023-10-10",
      "karyawan": {
        "id": 31,
        "nama": "Luffy",
        "status": "lajang Update",
        "jk": "laki-laki",
        "alamat": "pulau paus Update",
        "dob": "2012-12-12",
        "detailKaryawan": {
          "id": 31,
          "nik": "1234567890123456",
          "npwp": "123456789012345"
        }
      },
      "training": {
        "id": 22,
        "namaPengajar": "PAK EKO",
        "tema": "Backend with NodeJs"
      }
    },
    {
      "id": 6,
      "tanggalTraining": "2023-10-10",
      "karyawan": {
        "id": 32,
        "nama": "Zorro",
        "status": "lajang Update",
        "jk": "laki-laki",
        "alamat": "pulau paus Update",
        "dob": "2012-12-12",
        "detailKaryawan": {
          "id": 32,
          "nik": "1234567890123456",
          "npwp": "123456789012345"
        }
      },
      "training": {
        "id": 21,
        "namaPengajar": "PAK EKO",
        "tema": "Backend with Golang"
      }
    },
    {
      "id": 9,
      "tanggalTraining": "2023-10-10",
      "karyawan": {
        "id": 33,
        "nama": "Sanji",
        "status": "lajang Update",
        "jk": "laki-laki",
        "alamat": "pulau paus Update",
        "dob": "2012-12-12",
        "detailKaryawan": {
          "id": 33,
          "nik": "1234567890123456",
          "npwp": "123456789012345"
        }
      },
      "training": {
        "id": 21,
        "namaPengajar": "PAK EKO",
        "tema": "Backend with Golang"
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

Response Body (Failed):
```json
{
  "data": null,
  "paging": null,
  "errors": "message error"
}
```