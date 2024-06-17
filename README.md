### Called a procedure using springboot

All the logic to call the procedure is written in  - /src/main/java/com/example/effigosp/service/impl

### [click here](https://github.com/Harshit-kumar24/stored-procedure-demo/blob/main/src/main/java/com/example/effigosp/service/impl/EmployeeServiceImpl.java) to see the logic




### Procedure to be called

```sql
CREATE OR REPLACE PROCEDURE public.insert_employee(
	in_name character varying,
	in_age bigint,
	in_role character varying)
LANGUAGE 'plpgsql'
AS $BODY$
BEGIN
    INSERT INTO employee(name, age, role)
    VALUES (in_name, in_age, in_role);
    
    RAISE NOTICE 'Employee inserted successfully: %', in_name;
END;
$BODY$;
```

### input passed

```json
{
    "name":"john doe",
    "age":30,
    "role":"sde"
}
```
### Change in DB

![image](https://github.com/Harshit-kumar24/stored-procedure-demo/assets/108082088/885a71a5-aadb-4ba7-9232-85b6d7852239)
