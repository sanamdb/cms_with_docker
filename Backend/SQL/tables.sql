select * from users;

update users set role='ROLE_ADMIN' where id='mdsanau'; 

select * from otp;

select * from campus;
select * from department where id='f9c69635-7f4a-4480-b610-0d5b02110395';
select * from campus_department where department_id='f9c69635-7f4a-4480-b610-0d5b02110395';
select * from student_personal_information;
select * from admission_applications;
select * from batch;
select DISTINCT SUBJECT from question where department_id='f9c69635-7f4a-4480-b610-0d5b02110395';
select * from examination where id='c0cc74be-3b33-4635-bb6c-45efb69f2ad1';
select * from examination_question_id;
select * from exam_answer_sheet;
select * from exam_answer_sheet_answer_sheet;
select * from exam_result;
select * from admission;
