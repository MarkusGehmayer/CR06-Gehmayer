  ___ ___ _  _ __  __   ___   _____ ___ 
 / __| __| || |  \/  | /_\ \ / / __| _ \
| (_ | _|| __ | |\/| |/ _ \ V /| _||   /
 \___|___|_||_|_|  |_/_/ \_\ | |___|_|_\
----------------------------y------------------------- 
                                      
QUERIES -->
------------------------------------------------------    

::: show all students of the class X, which has the ID=Y :::
SELECT student.studentId, student.studentName, student.studentSurname, classRooms.className
FROM student
INNER JOIN classRooms
ON student.fk_classRoomId = classRooms.classRoomId

::: show student/teacher names & classrooms :::
SELECT student.studentName, student.studentSurname, classRooms.className, teacher.teacherName, teacher.teacherSurname 
FROM student
INNER JOIN classRooms
on student.fk_classRoomId = classRooms.classRoomId
INNER JOIN teacher on classRooms.fk_teacherId = teacher.teacherId

::: show all students of the class X; I do not know the class ID :::
SELECT classRooms.classRoomId, student.studentName, student.studentSurname
FROM student
INNER JOIN classRooms ON student.fk_classRoomId = classRooms.classRoomId
WHERE classRooms.classRoomId = 2;

::: show students for specific class name :::
SELECT classRooms.className, student.studentName, student.studentSurname
FROM student
INNER JOIN classRooms ON student.fk_classRoomId = classRooms.classRoomId
WHERE classRooms.className = "3b";

